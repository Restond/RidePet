/*    */ package lumine.xikage.mythicmobs.skills.damage;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
/*    */ 
/*    */ public abstract class DamagingMechanic
/*    */   extends SkillMechanic {
/*    */   protected static boolean noloop = false;
/*    */   protected boolean ignoresArmor;
/*    */   protected boolean preventImmunity;
/*    */   protected boolean preventKnockback;
/*    */   protected String element;
/*    */   
/*    */   public DamagingMechanic(String line, MythicLineConfig mlc) {
/* 19 */     super(line, mlc);
/* 20 */     this.ASYNC_SAFE = false;
/*    */     
/* 22 */     this.ignoresArmor = mlc.getBoolean(new String[] { "ignorearmor", "ia", "i" }, false);
/* 23 */     this.preventImmunity = mlc.getBoolean(new String[] { "preventimmunity", "pi" }, false);
/* 24 */     this.preventKnockback = mlc.getBoolean(new String[] { "preventknockback", "pkb", "pk" }, false);
/* 25 */     this.element = mlc.getString(new String[] { "element", "e", "damagetype", "type" }, null, new String[0]);
/*    */   }
/*    */   
/*    */   protected void doDamage(SkillCaster caster, AbstractEntity target, double amount) {
/* 29 */     DamageMetadata meta = new DamageMetadata(caster, amount, this.element, Boolean.valueOf(this.ignoresArmor), Boolean.valueOf(this.preventImmunity), Boolean.valueOf(this.preventKnockback));
/*    */     
/* 31 */     SkillAdapter.get().doDamage(meta, target);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\damage\DamagingMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */