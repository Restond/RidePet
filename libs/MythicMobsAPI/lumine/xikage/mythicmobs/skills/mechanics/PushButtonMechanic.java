/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "pushbutton", aliases = {"buttonpush"}, description = "Pushes a button at the target location")
/*    */ public class PushButtonMechanic extends SkillMechanic implements ITargetedLocationSkill, INoTargetSkill {
/*    */   protected int x;
/*    */   protected int y;
/*    */   protected int z;
/*    */   
/*    */   public PushButtonMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/* 21 */     this.ASYNC_SAFE = false;
/*    */     
/* 23 */     String coords = mlc.getString("location");
/* 24 */     coords = mlc.getString("loc", coords);
/* 25 */     coords = mlc.getString("l", coords);
/*    */     
/* 27 */     if (coords != null) {
/* 28 */       String[] split = coords.split(",");
/*    */       
/*    */       try {
/* 31 */         this.x = Integer.parseInt(split[0]);
/* 32 */         this.y = Integer.parseInt(split[1]);
/* 33 */         this.z = Integer.parseInt(split[2]);
/* 34 */       } catch (Exception ex) {
/* 35 */         MythicMobs.error("PushButton skill coordinates are configured incorrectly in skill string: " + skill);
/*    */         return;
/*    */       } 
/*    */     } else {
/* 39 */       this.x = mlc.getInteger("x", 0);
/* 40 */       this.y = mlc.getInteger("y", 0);
/* 41 */       this.z = mlc.getInteger("z", 0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 47 */     SkillAdapter.get().pushButton(data.getCaster(), new AbstractLocation(data.getCaster().getEntity().getLocation().getWorld(), this.x, this.y, this.z));
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 53 */     SkillAdapter.get().pushButton(data.getCaster(), target);
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PushButtonMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */