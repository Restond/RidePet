/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenericCaster
/*    */   implements SkillCaster
/*    */ {
/*    */   private AbstractEntity entity;
/*    */   private boolean doingDamage = false;
/* 16 */   private int gcd = 0;
/*    */   
/*    */   public GenericCaster(AbstractEntity entity) {
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractEntity getEntity() {
/* 24 */     return this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractLocation getLocation() {
/* 29 */     return this.entity.getLocation();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUsingDamageSkill(boolean b) {
/* 34 */     this.doingDamage = b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUsingDamageSkill() {
/* 39 */     return this.doingDamage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 44 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPower() {
/* 49 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGlobalCooldown() {
/* 54 */     return this.gcd;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGlobalCooldown(int gcd) {
/* 59 */     this.gcd = gcd;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\GenericCaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */