/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.TotemMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.HitBox;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ class TotemTracker
/*     */   implements IParentSkill, Runnable
/*     */ {
/*     */   private SkillMetadata data;
/*     */   private boolean cancelled = false;
/*     */   private SkillCaster am;
/*     */   private float power;
/*     */   private long startTime;
/*     */   private AbstractLocation currentLocation;
/*     */   private int charges;
/*     */   private Task task;
/*     */   private HashSet<AbstractEntity> targets;
/*     */   private List<AbstractEntity> inRange;
/*     */   private Map<AbstractEntity, Long> immune;
/* 130 */   private int counter = 0;
/*     */   
/*     */   public TotemTracker(SkillMetadata data, AbstractEntity target) {
/* 133 */     this.cancelled = false;
/*     */     
/* 135 */     this.data = data;
/* 136 */     this.data.setCallingEvent(this);
/* 137 */     this.am = data.getCaster();
/* 138 */     this.power = data.getPower();
/* 139 */     this.charges = paramTotemMechanic.maxCharges;
/*     */     
/* 141 */     this.startTime = System.currentTimeMillis();
/*     */     
/* 143 */     if (data.getOrigin() != null) {
/* 144 */       this.currentLocation = data.getOrigin().clone();
/*     */     } else {
/* 146 */       this.currentLocation = this.am.getEntity().getLocation().clone();
/*     */     } 
/*     */     
/* 149 */     if (paramTotemMechanic.YOffset != 0.0F) {
/* 150 */       this.currentLocation.setY(this.currentLocation.getY() + paramTotemMechanic.YOffset);
/*     */     }
/*     */     
/* 153 */     MythicMobs.debug(3, "------ Initializing projectile skill");
/*     */     
/* 155 */     this.task = Schedulers.sync().runRepeating(this, 0L, paramTotemMechanic.tickInterval);
/*     */     
/* 157 */     if (paramTotemMechanic.hitPlayers || paramTotemMechanic.hitNonPlayers || paramTotemMechanic.hitTarget) {
/* 158 */       this.inRange = this.currentLocation.getWorld().getLivingEntities();
/* 159 */       Iterator<AbstractEntity> iter = this.inRange.iterator();
/*     */ 
/*     */       
/* 162 */       while (iter.hasNext()) {
/* 163 */         AbstractEntity e = iter.next();
/* 164 */         MythicMobs.debug(4, "-------- Added entity " + e.getName());
/* 165 */         if (e.getUniqueId().equals(this.am.getEntity().getUniqueId())) {
/* 166 */           iter.remove();
/* 167 */           MythicMobs.debug(4, "-------- Removed entity " + e.getName() + ": is self");
/*     */           continue;
/*     */         } 
/* 170 */         if (!paramTotemMechanic.hitPlayers && e.isPlayer() && !e.equals(target)) {
/* 171 */           iter.remove();
/* 172 */           MythicMobs.debug(4, "-------- Removed entity " + e.getName() + ": is player");
/*     */           continue;
/*     */         } 
/* 175 */         if (!paramTotemMechanic.hitNonPlayers && (!e.isPlayer() || e.equals(target))) {
/* 176 */           iter.remove();
/* 177 */           MythicMobs.debug(4, "-------- Removed entity " + e.getName() + ": is non-player");
/*     */         } 
/*     */       } 
/*     */       
/* 181 */       if (paramTotemMechanic.hitTarget == true) {
/* 182 */         this.inRange.add(target);
/*     */       }
/*     */     } 
/*     */     
/* 186 */     this.targets = new HashSet<>();
/* 187 */     this.immune = new HashMap<>();
/*     */     
/* 189 */     if (paramTotemMechanic.onStartSkill.isPresent() && (
/* 190 */       (Skill)paramTotemMechanic.onStartSkill.get()).isUsable(data)) {
/* 191 */       SkillMetadata sData = data.deepClone();
/*     */       
/* 193 */       sData.setLocationTarget(this.currentLocation);
/* 194 */       sData.setOrigin(this.currentLocation.clone());
/*     */       
/* 196 */       ((Skill)paramTotemMechanic.onStartSkill.get()).execute(sData);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 203 */     if (this.cancelled == true)
/* 204 */       return;  if (TotemMechanic.this.duration > 0.0F && (float)this.startTime + TotemMechanic.this.duration < (float)System.currentTimeMillis()) {
/* 205 */       stop();
/*     */       
/*     */       return;
/*     */     } 
/* 209 */     this.counter++;
/*     */     
/* 211 */     if (this.inRange != null) {
/* 212 */       MythicMobs.debug(4, "-------- Checking if entities in HitBox");
/* 213 */       HitBox hitBox = new HitBox(this.currentLocation, TotemMechanic.this.hitRadius, TotemMechanic.this.verticalHitRadius);
/* 214 */       for (int i = 0; i < this.inRange.size(); i++) {
/* 215 */         AbstractEntity e = this.inRange.get(i);
/* 216 */         if (!e.isDead() && hitBox.contains(e.getLocation().add(0.0D, 0.6D, 0.0D))) {
/* 217 */           MythicMobs.debug(4, "---------- Target " + e.getName() + " is in HitBox!");
/* 218 */           this.targets.add(e);
/*     */           
/* 220 */           if (TotemMechanic.this.maxCharges > 0) {
/* 221 */             this.charges--;
/*     */             
/* 223 */             if (this.charges <= 0) {
/* 224 */               doHit(this.targets);
/* 225 */               stop(); return;
/*     */             } 
/*     */             break;
/*     */           } 
/* 229 */           this.inRange.remove(i);
/* 230 */           this.immune.put(e, Long.valueOf(System.currentTimeMillis()));
/*     */           
/*     */           break;
/*     */         } 
/* 234 */         MythicMobs.debug(4, "---------- Target " + e.getName() + " is NOT in HitBox!");
/*     */       } 
/*     */       
/* 237 */       Iterator<Map.Entry<AbstractEntity, Long>> iter = this.immune.entrySet().iterator();
/* 238 */       while (iter.hasNext()) {
/* 239 */         Map.Entry<AbstractEntity, Long> entry = iter.next();
/* 240 */         if (((Long)entry.getValue()).longValue() < System.currentTimeMillis() - 2000L) {
/* 241 */           iter.remove();
/* 242 */           this.inRange.add(entry.getKey());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     if (TotemMechanic.this.onTickSkill.isPresent() && (
/* 248 */       (Skill)TotemMechanic.this.onTickSkill.get()).isUsable(this.data)) {
/* 249 */       SkillMetadata sData = this.data.deepClone();
/* 250 */       AbstractLocation location = this.currentLocation.clone();
/* 251 */       HashSet<AbstractLocation> targets = new HashSet<>();
/* 252 */       targets.add(location);
/* 253 */       sData.setLocationTargets(targets);
/* 254 */       sData.setOrigin(location);
/*     */       
/* 256 */       ((Skill)TotemMechanic.this.onTickSkill.get()).execute(sData);
/*     */     } 
/*     */     
/* 259 */     if (this.targets.size() > 0) {
/* 260 */       doHit((HashSet<AbstractEntity>)this.targets.clone());
/*     */     }
/* 262 */     this.targets.clear();
/*     */   }
/*     */   
/*     */   public void doHit(HashSet<AbstractEntity> targets) {
/* 266 */     if (TotemMechanic.this.onHitSkill.isPresent() && (
/* 267 */       (Skill)TotemMechanic.this.onHitSkill.get()).isUsable(this.data)) {
/* 268 */       SkillMetadata sData = this.data.deepClone();
/* 269 */       sData.setEntityTargets(targets);
/* 270 */       sData.setOrigin(this.currentLocation.clone());
/* 271 */       ((Skill)TotemMechanic.this.onHitSkill.get()).execute(sData);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void stop() {
/* 277 */     if (TotemMechanic.this.onEndSkill.isPresent() && (
/* 278 */       (Skill)TotemMechanic.this.onEndSkill.get()).isUsable(this.data)) {
/* 279 */       SkillMetadata sData = this.data.deepClone();
/* 280 */       ((Skill)TotemMechanic.this.onEndSkill.get()).execute(sData.setOrigin(this.currentLocation).setLocationTarget(this.currentLocation));
/*     */     } 
/*     */     
/* 283 */     this.task.terminate();
/*     */     
/* 285 */     this.cancelled = true;
/*     */     
/* 287 */     if (this.inRange != null) {
/* 288 */       this.inRange.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 301 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\TotemMechanic$TotemTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */