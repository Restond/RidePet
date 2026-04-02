/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "teleportto", aliases = {"tpt", "teleportlocation", "tpl"}, description = "Teleports the target entity to a location")
/*    */ public class TeleportToMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderString world;
/*    */   protected String coords;
/* 19 */   protected double x = 0.0D;
/* 20 */   protected double y = 0.0D;
/* 21 */   protected double z = 0.0D;
/*    */   protected double yaw;
/*    */   
/*    */   public TeleportToMechanic(String line, MythicLineConfig mlc) {
/* 25 */     super(line, mlc);
/* 26 */     this.ASYNC_SAFE = false;
/*    */     
/* 28 */     this.coords = mlc.getString(new String[] { "location", "loc", "l", "coordinates", "c" }, null, new String[0]);
/*    */     
/* 30 */     if (this.coords != null) {
/* 31 */       String[] split = this.coords.split(",");
/*    */       
/*    */       try {
/* 34 */         this.x = Double.parseDouble(split[0]);
/* 35 */         this.y = Double.parseDouble(split[1]);
/* 36 */         this.z = Double.parseDouble(split[2]);
/* 37 */       } catch (Exception ex) {
/* 38 */         MythicLogger.errorMechanicConfig(this, mlc, "The 'coordinates' attribute must be in the format c=x,y,z.");
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 43 */     this.world = mlc.getPlaceholderString(new String[] { "world", "w" }, null, new String[0]);
/* 44 */     this.yaw = mlc.getDouble(new String[] { "yaw", "y" }, 0.0D);
/* 45 */     this.pitch = mlc.getDouble(new String[] { "pitch", "p" }, 0.0D);
/*    */     
/* 47 */     if (this.coords == null && this.world == null) {
/* 48 */       MythicLogger.errorMechanicConfig(this, mlc, "Either the 'coordinates' attribute or 'world' attribute is required.");
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected double pitch;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     AbstractLocation location;
/* 57 */     if (this.world == null) {
/* 58 */       location = new AbstractLocation(target.getWorld(), this.x, this.y, this.z);
/* 59 */     } else if (this.coords == null) {
/* 60 */       location = new AbstractLocation(MythicMobs.inst().server().getWorld(this.world.get((PlaceholderMeta)data, target)), target.getLocation().getX(), target.getLocation().getY(), target.getLocation().getZ());
/*    */     } else {
/* 62 */       location = new AbstractLocation(MythicMobs.inst().server().getWorld(this.world.get((PlaceholderMeta)data, target)), this.x, this.y, this.z);
/*    */     } 
/* 64 */     location.setPitch((float)this.pitch);
/* 65 */     location.setYaw((float)this.yaw);
/*    */     
/* 67 */     target.teleport(location);
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\TeleportToMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */