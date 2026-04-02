/*    */ package lumine.xikage.mythicmobs.mobs.entities.properties;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.EntityPropertySet;
/*    */ import org.bukkit.entity.Ageable;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public class AgeableProperty
/*    */   implements EntityPropertySet {
/*    */   private boolean forceAdult;
/*    */   private boolean forceBaby;
/* 13 */   private int age = -1;
/*    */   private boolean lockAge;
/*    */   
/*    */   public AgeableProperty(MythicConfig mc) {
/* 17 */     if (mc.isSet("Options.Adult")) {
/* 18 */       boolean b = mc.getBoolean("Options.Adult");
/*    */       
/* 20 */       if (b == true) {
/* 21 */         this.forceAdult = true;
/* 22 */         this.forceBaby = false;
/*    */       } else {
/* 24 */         this.forceAdult = false;
/* 25 */         this.forceBaby = true;
/*    */       } 
/* 27 */     } else if (mc.isSet("Options.Baby")) {
/* 28 */       boolean b = mc.getBoolean("Options.Baby");
/*    */       
/* 30 */       if (b == true) {
/* 31 */         this.forceAdult = false;
/* 32 */         this.forceBaby = true;
/*    */       } else {
/* 34 */         this.forceAdult = true;
/* 35 */         this.forceBaby = false;
/*    */       } 
/*    */     } else {
/* 38 */       this.forceAdult = false;
/* 39 */       this.forceBaby = false;
/*    */       try {
/* 41 */         this.age = mc.getInteger("Options.Age", -1);
/* 42 */       } catch (Exception ex) {
/* 43 */         MythicLogger.errorEntityConfig(null, mc, "Age must be an integer");
/*    */       } 
/*    */     } 
/* 46 */     this.lockAge = mc.getBoolean("Options.AgeLock", false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyProperties(Entity entity) {
/* 52 */     Ageable ageable = (Ageable)entity;
/*    */     
/* 54 */     if (this.forceAdult) {
/* 55 */       ageable.setAdult();
/* 56 */     } else if (this.forceBaby) {
/* 57 */       ageable.setBaby();
/* 58 */     } else if (this.age > -1) {
/* 59 */       ageable.setAge(this.age);
/*    */     } 
/*    */     
/* 62 */     ageable.setAgeLock(this.lockAge);
/*    */     
/* 64 */     return (Entity)ageable;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\entities\properties\AgeableProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */