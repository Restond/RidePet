/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Events;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ChainMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*     */ import java.util.HashSet;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
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
/*     */ public class ChainTracker
/*     */   implements IParentSkill, Runnable, Terminable
/*     */ {
/*     */   protected SkillMetadata data;
/*     */   AbstractEntity currentBounce;
/*     */   UUID currentBounceUUID;
/*     */   AbstractLocation currentOrigin;
/*     */   protected final double bounceRadius;
/*     */   protected final double bounceRadiusSq;
/*     */   protected final int bounceDelay;
/* 127 */   protected final TerminableRegistry components = TerminableRegistry.create(); protected int bouncesRemaining; protected float power;
/*     */   
/*     */   public AbstractEntity getCurrentBounce() {
/* 130 */     return this.currentBounce; }
/* 131 */   public UUID getCurrentBounceUUID() { return this.currentBounceUUID; } public AbstractLocation getCurrentOrigin() {
/* 132 */     return this.currentOrigin;
/*     */   }
/* 134 */   public double getBounceRadius() { return this.bounceRadius; }
/* 135 */   public double getBounceRadiusSq() { return this.bounceRadiusSq; } public int getBounceDelay() {
/* 136 */     return this.bounceDelay;
/*     */   }
/* 138 */   public int getBouncesRemaining() { return this.bouncesRemaining; } public float getPower() {
/* 139 */     return this.power;
/*     */   }
/* 141 */   protected HashSet<AbstractEntity> potentialTargets = new HashSet<>();
/*     */   
/*     */   public ChainTracker(SkillMetadata data, AbstractEntity target) {
/* 144 */     this.data = data;
/* 145 */     this.data.setCallingEvent(this);
/* 146 */     this.power = data.getPower();
/*     */     
/* 148 */     this.currentBounce = data.getCaster().getEntity();
/* 149 */     this.currentBounceUUID = this.currentBounce.getUniqueId();
/* 150 */     this.currentOrigin = data.getCaster().getLocation();
/*     */     
/* 152 */     this.bouncesRemaining = ChainMechanic.this.bounces.get((PlaceholderMeta)data);
/* 153 */     this.bounceRadius = ChainMechanic.this.bounceRadius.get((PlaceholderMeta)data);
/* 154 */     this.bounceRadiusSq = Math.pow(this.bounceRadius, 2.0D);
/* 155 */     this.bounceDelay = ChainMechanic.this.bounceDelay.get((PlaceholderMeta)data);
/*     */     
/* 157 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ Chain fired by Entity {0}: skill = {1}", new Object[] { data.getCaster().getEntity().getName(), ChainMechanic.access$000(this$0) });
/*     */     
/* 159 */     start(target);
/*     */   }
/*     */   
/*     */   public void start(AbstractEntity target) {
/* 163 */     evaluatePotentialTargets();
/* 164 */     this.components.accept(Events.subscribe(EntityDeathEvent.class)
/* 165 */         .filter(event -> event.getEntity().getUniqueId().equals(this.currentBounceUUID))
/* 166 */         .handler(event -> {
/*     */             this.currentOrigin = this.currentBounce.getLocation();
/*     */             
/*     */             this.currentBounce = null;
/*     */           }));
/* 171 */     if (ChainMechanic.this.hitTarget) {
/* 172 */       executeChainSkill(target);
/*     */     } else {
/* 174 */       this.potentialTargets.remove(target);
/* 175 */       this.currentBounce = target;
/* 176 */       this.currentBounceUUID = target.getUniqueId();
/* 177 */       this.currentOrigin = target.getLocation();
/*     */     } 
/* 179 */     if (this.potentialTargets.size() > 0) {
/* 180 */       this.components.accept(Schedulers.sync().runRepeating(this, this.bounceDelay, this.bounceDelay));
/*     */     } else {
/* 182 */       terminate();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean executeChainSkill(AbstractEntity target) {
/* 187 */     if (ChainMechanic.this.onBounceSkill == null) return true;
/*     */     
/* 189 */     this.data = this.data.deepClone();
/*     */     
/* 191 */     this.data.setEntityTarget(target);
/* 192 */     this.data.setOrigin(this.currentOrigin);
/*     */     
/* 194 */     this.potentialTargets.remove(target);
/*     */     
/* 196 */     if (ChainMechanic.this.onBounceSkill.isUsable(this.data)) {
/* 197 */       VariableRegistry vars = this.data.getVariables();
/*     */       
/* 199 */       vars.putInt("chain-bounces", this.bouncesRemaining);
/*     */       
/* 201 */       ChainMechanic.this.onBounceSkill.execute(this.data);
/*     */       
/* 203 */       this.currentBounce = target;
/* 204 */       this.currentBounceUUID = target.getUniqueId();
/* 205 */       this.currentOrigin = target.getLocation();
/* 206 */       return true;
/*     */     } 
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 213 */     if (!this.components.hasTerminated()) {
/* 214 */       this.potentialTargets.clear();
/*     */     }
/* 216 */     return this.components.terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 221 */     AbstractEntity nextTarget = getNextTarget();
/*     */     
/* 223 */     if (nextTarget != null && executeChainSkill(nextTarget)) {
/* 224 */       if (--this.bouncesRemaining <= 0) {
/* 225 */         terminate();
/*     */       }
/*     */     } else {
/* 228 */       terminate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled() {
/* 234 */     terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 239 */     return this.components.hasTerminated();
/*     */   }
/*     */   
/*     */   private void evaluatePotentialTargets() {
/* 243 */     double maxDistance = Math.pow(this.bounceRadius * this.bouncesRemaining * 2.0D, 2.0D);
/* 244 */     if (ChainMechanic.this.hitSelf || ChainMechanic.this.hitPlayers || ChainMechanic.this.hitNonPlayers) {
/* 245 */       this.potentialTargets.addAll(AbstractSkill.getPlugin().getEntityManager().getLivingEntities(this.data.getCaster().getLocation().getWorld()));
/* 246 */       this.potentialTargets.removeIf(e -> (e == null) ? true : ((this.currentOrigin.distanceSquared(e.getLocation()) > paramDouble) ? true : (
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 251 */           (!e.isLiving() || e.isCitizensNPC()) ? true : (
/*     */           
/* 253 */           (!ChainMechanic.this.hitSelf && e.getUniqueId().equals(this.data.getCaster().getEntity().getUniqueId())) ? true : (
/*     */           
/* 255 */           (!ChainMechanic.this.hitPlayers && e.isPlayer()) ? true : (
/*     */           
/* 257 */           (!ChainMechanic.this.hitNonPlayers && !e.isPlayer())))))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractEntity getNextTarget() {
/* 266 */     AbstractEntity target = null;
/* 267 */     double range = 0.0D;
/*     */     
/* 269 */     for (AbstractEntity entity : this.potentialTargets) {
/* 270 */       if (entity == null) {
/*     */         continue;
/*     */       }
/* 273 */       AbstractLocation origin = (this.currentBounce != null) ? this.currentBounce.getLocation() : this.currentOrigin;
/* 274 */       double nextRange = origin.distanceSquared(entity.getLocation());
/* 275 */       if (nextRange > this.bounceRadiusSq) {
/*     */         continue;
/*     */       }
/* 278 */       if (ChainMechanic.this.bounceConditions != null) {
/* 279 */         boolean ok = true;
/* 280 */         for (SkillCondition condition : ChainMechanic.this.bounceConditions) {
/* 281 */           if (!condition.evaluateToEntity(this.currentBounce, entity)) {
/* 282 */             ok = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 286 */         if (!ok)
/*     */           continue; 
/* 288 */       }  if (target == null) {
/* 289 */         target = entity;
/* 290 */         range = this.currentBounce.getLocation().distanceSquared(entity.getLocation());
/*     */         continue;
/*     */       } 
/* 293 */       if (nextRange < range) {
/* 294 */         target = entity;
/* 295 */         range = nextRange;
/*     */       } 
/*     */     } 
/* 298 */     return target;
/*     */   }
/*     */   
/*     */   public void modifyPower(float p) {
/* 302 */     this.power *= p;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ChainMechanic$ChainTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */