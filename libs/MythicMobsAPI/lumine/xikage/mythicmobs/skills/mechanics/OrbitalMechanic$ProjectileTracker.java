/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.OrbitalMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.HitBox;
/*     */ import io.lumine.xikage.mythicmobs.util.VectorUtils;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ProjectileTracker
/*     */   extends Aura.AuraTracker
/*     */   implements IParentSkill, Runnable
/*     */ {
/*     */   private float power;
/* 109 */   private int iteration = 0;
/* 110 */   private int step = 0;
/*     */   
/* 112 */   private Set<AbstractEntity> inRange = ConcurrentHashMap.newKeySet();
/* 113 */   private HashSet<AbstractEntity> targets = new HashSet<>();
/* 114 */   private Map<AbstractEntity, Long> immune = new HashMap<>();
/*     */   
/*     */   public ProjectileTracker(SkillMetadata data, AbstractEntity entity) {
/* 117 */     super((Aura)paramOrbitalMechanic, entity, data);
/*     */     
/* 119 */     this.power = data.getPower();
/*     */     
/* 121 */     start();
/*     */   }
/*     */   
/*     */   public ProjectileTracker(SkillMetadata data, AbstractLocation location) {
/* 125 */     super((Aura)paramOrbitalMechanic, location, data);
/*     */     
/* 127 */     this.power = data.getPower();
/*     */     
/* 129 */     start();
/*     */   }
/*     */   
/*     */   public AbstractLocation getLocation() {
/* 133 */     if (this.entity.isPresent())
/* 134 */       return ((AbstractEntity)this.entity.get()).getLocation().add(OrbitalMechanic.this.xOffset, OrbitalMechanic.this.yOffset, OrbitalMechanic.this.zOffset); 
/* 135 */     if (this.location.isPresent()) {
/* 136 */       return ((AbstractLocation)this.location.get()).clone().add(OrbitalMechanic.this.xOffset, OrbitalMechanic.this.yOffset, OrbitalMechanic.this.zOffset);
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void auraStart() {
/* 144 */     if (OrbitalMechanic.this.hitSelf || OrbitalMechanic.this.hitPlayers || OrbitalMechanic.this.hitNonPlayers) {
/* 145 */       this.inRange.addAll(MythicMobs.inst().getEntityManager().getLivingEntities(this.skillMetadata.getOrigin().getWorld()));
/* 146 */       this.inRange.removeIf(e -> {
/*     */             if (e != null) {
/*     */               if (!OrbitalMechanic.this.hitSelf && e.getUniqueId().equals(this.skillMetadata.getCaster().getEntity().getUniqueId())) {
/*     */                 return true;
/*     */               }
/*     */               
/*     */               if (!OrbitalMechanic.this.hitPlayers && e.isPlayer()) {
/*     */                 return true;
/*     */               }
/*     */               
/*     */               if (!OrbitalMechanic.this.hitNonPlayers && !e.isPlayer()) {
/*     */                 return true;
/*     */               }
/*     */             } else {
/*     */               return true;
/*     */             } 
/*     */             return false;
/*     */           });
/*     */     } 
/* 165 */     executeAuraSkill(OrbitalMechanic.access$000(OrbitalMechanic.this), this.skillMetadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void auraTick() {
/* 173 */     AbstractLocation location = getLocation();
/* 174 */     double inc = 6.283185307179586D / OrbitalMechanic.this.points;
/* 175 */     double angle = this.step * inc;
/* 176 */     AbstractVector v = new AbstractVector(0, 0, 0);
/* 177 */     v.setX(Math.cos(angle) * OrbitalMechanic.this.radius);
/* 178 */     v.setZ(Math.sin(angle) * OrbitalMechanic.this.radius);
/*     */     
/* 180 */     VectorUtils.rotateVector(v, OrbitalMechanic.this.xRotation, OrbitalMechanic.this.yRotation, OrbitalMechanic.this.zRotation);
/*     */     
/* 182 */     if (OrbitalMechanic.this.rotate) {
/* 183 */       VectorUtils.rotateVector(v, OrbitalMechanic.this.angularVelocityX * this.step, OrbitalMechanic.this.angularVelocityY * this.step, OrbitalMechanic.this.angularVelocityZ * this.step);
/*     */     }
/*     */     
/* 186 */     location = location.add(v);
/* 187 */     executeAuraSkill(OrbitalMechanic.access$100(OrbitalMechanic.this), this.skillMetadata.deepClone().setOrigin(location));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (this.inRange != null) {
/*     */       
/* 194 */       HitBox hitBox = new HitBox(location, OrbitalMechanic.this.hitRadius, OrbitalMechanic.this.verticalHitRadius);
/* 195 */       for (AbstractEntity e : this.inRange) {
/* 196 */         if (!e.isDead() && hitBox.contains(e.getLocation().add(0.0D, 0.6D, 0.0D))) {
/*     */           
/* 198 */           this.targets.add(e);
/* 199 */           this.immune.put(e, Long.valueOf(System.currentTimeMillis()));
/*     */           break;
/*     */         } 
/*     */       } 
/* 203 */       this.immune.entrySet().removeIf(entry -> (((Long)entry.getValue()).longValue() < System.currentTimeMillis() - 2000L));
/*     */       
/* 205 */       if (this.targets.size() > 0) {
/* 206 */         if (OrbitalMechanic.this.onHitSkill.isPresent()) {
/* 207 */           SkillMetadata sData = this.skillMetadata.deepClone();
/* 208 */           sData.setEntityTargets((HashSet)this.targets.clone());
/* 209 */           sData.setOrigin(location.clone());
/*     */           
/* 211 */           if (((Skill)OrbitalMechanic.this.onHitSkill.get()).isUsable(sData)) {
/* 212 */             ((Skill)OrbitalMechanic.this.onHitSkill.get()).execute(sData);
/*     */           }
/*     */         } 
/* 215 */         this.targets.clear();
/* 216 */         consumeCharge();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     this.step++;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OrbitalMechanic$ProjectileTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */