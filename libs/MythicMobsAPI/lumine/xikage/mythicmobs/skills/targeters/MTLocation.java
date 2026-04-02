/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTLocation extends ILocationSelector {
/* 14 */   protected double x = 0.0D;
/* 15 */   protected double y = 0.0D;
/* 16 */   protected double z = 0.0D;
/*    */   
/*    */   public MTLocation(MythicLineConfig mlc) {
/* 19 */     super(mlc);
/*    */     
/* 21 */     String coords = mlc.getString(new String[] { "location", "loc", "l", "c" }, null, new String[0]);
/*    */     
/* 23 */     if (coords != null) {
/* 24 */       String[] split = coords.split(",");
/*    */       
/*    */       try {
/* 27 */         this.x = Double.parseDouble(split[0]);
/* 28 */         this.y = Double.parseDouble(split[1]);
/* 29 */         this.z = Double.parseDouble(split[2]);
/* 30 */       } catch (Exception ex) {
/* 31 */         MythicLogger.errorTargeterConfig((SkillTargeter)this, mlc, "The 'location' attribute is required and must be in the format l=x,y,z.");
/*    */         return;
/*    */       } 
/*    */     } else {
/* 35 */       this.x = mlc.getDouble("x", 0.0D);
/* 36 */       this.y = mlc.getDouble("y", 0.0D);
/* 37 */       this.z = mlc.getDouble("z", 0.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 43 */     SkillCaster am = data.getCaster();
/* 44 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 46 */     AbstractLocation l = MythicMobs.inst().server().newLocation(am.getEntity().getWorld(), this.x, this.y, this.z);
/* 47 */     targets.add(l);
/*    */     
/* 49 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */