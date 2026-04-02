/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
/*     */ import org.bukkit.entity.Projectile;
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
/*     */ public class ProjectileTracker
/*     */   implements Runnable
/*     */ {
/*     */   private Task task;
/*     */   private SkillMetadata data;
/*     */   private SkillCaster caster;
/*     */   private AbstractEntity projectile;
/*     */   private boolean hasEnded = false;
/*     */   private float power;
/*     */   
/*     */   public ProjectileTracker(SkillMetadata caster, AbstractEntity projectile, float power) {
/* 251 */     this.caster = caster.getCaster();
/* 252 */     this.data = caster;
/* 253 */     this.projectile = projectile;
/* 254 */     this.power = power;
/* 255 */     this.task = Schedulers.async().runRepeating(this, 0L, ShootMechanic.this.tickInterval);
/*     */   }
/*     */   
/*     */   public void run() {
/* 259 */     if (!this.projectile.isValid()) {
/* 260 */       doEndSkill();
/* 261 */       stop();
/*     */     } 
/* 263 */     if (this.projectile.getBukkitEntity() instanceof Projectile && ((Projectile)this.projectile.getBukkitEntity()).isOnGround()) {
/* 264 */       doEndSkill();
/*     */     }
/* 266 */     if (ShootMechanic.this.onTickSkill.isPresent() && (
/* 267 */       (Skill)ShootMechanic.this.onTickSkill.get()).usable(this.data, null)) {
/* 268 */       ((Skill)ShootMechanic.this.onTickSkill.get()).execute(null, this.caster, null, this.projectile.getLocation().clone(), null, null, this.power);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void doEndSkill() {
/* 274 */     if (!this.hasEnded && ShootMechanic.this.onEndSkill.isPresent()) {
/* 275 */       if (((Skill)ShootMechanic.this.onEndSkill.get()).usable(this.data, null)) {
/* 276 */         ((Skill)ShootMechanic.this.onEndSkill.get()).execute(null, this.caster, null, this.projectile.getLocation().clone(), null, null, this.power);
/*     */       }
/* 278 */       this.hasEnded = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stop() {
/* 283 */     this.task.terminate();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootMechanic$ProjectileTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */