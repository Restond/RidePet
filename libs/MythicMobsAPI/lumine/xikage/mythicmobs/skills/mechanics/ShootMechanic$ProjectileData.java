/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProjectileData
/*     */ {
/*     */   private SkillCaster caster;
/*     */   private int damage;
/*     */   private Optional<Skill> onHitSkill;
/*     */   private Optional<Skill> onEndSkill;
/*     */   private float power;
/*     */   
/*     */   public ProjectileData(SkillCaster caster, int damage, Optional<Skill> onHitSkill, Optional<Skill> onEndSkill, float power) {
/* 203 */     this.caster = caster;
/* 204 */     this.damage = damage;
/* 205 */     this.onHitSkill = onHitSkill;
/* 206 */     this.onEndSkill = onEndSkill;
/* 207 */     this.power = power;
/*     */   }
/*     */   
/*     */   public SkillCaster getCaster() {
/* 211 */     return this.caster;
/*     */   }
/*     */   
/*     */   public int getDamage() {
/* 215 */     return this.damage;
/*     */   }
/*     */   
/*     */   public float getPower() {
/* 219 */     return this.power;
/*     */   }
/*     */   
/*     */   public void executeHitSkill(AbstractEntity projectile, AbstractEntity target) {
/* 223 */     HashSet<AbstractEntity> t = Sets.newHashSet();
/* 224 */     t.add(target);
/* 225 */     SkillMetadata meta = new SkillMetadata(SkillTrigger.ATTACK, this.caster, target, projectile.getLocation(), t, null, this.power);
/*     */     
/* 227 */     if (this.onHitSkill.isPresent() && (
/* 228 */       (Skill)this.onHitSkill.get()).usable(meta, null)) {
/* 229 */       ((Skill)this.onHitSkill.get()).execute(meta);
/*     */     }
/*     */     
/* 232 */     if (this.onEndSkill.isPresent())
/*     */     {
/* 234 */       if (((Skill)this.onEndSkill.get()).usable(meta, null))
/* 235 */         ((Skill)this.onEndSkill.get()).execute(null, this.caster, null, projectile.getLocation().clone(), null, null, this.power); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootMechanic$ProjectileData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */