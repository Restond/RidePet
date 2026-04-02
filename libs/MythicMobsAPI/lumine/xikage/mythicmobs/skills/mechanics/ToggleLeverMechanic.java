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
/*    */ @MythicMechanic(author = "Ashijin", name = "togglelever", aliases = {"lever"}, description = "Toggles a lever at the target location")
/*    */ public class ToggleLeverMechanic
/*    */   extends SkillMechanic implements ITargetedLocationSkill, INoTargetSkill {
/*    */   protected int x;
/*    */   protected int y;
/*    */   
/*    */   public ToggleLeverMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/* 21 */     this.ASYNC_SAFE = false;
/*    */     
/* 23 */     this.ticks = mlc.getInteger(new String[] { "duration", "d" }, 20);
/*    */     
/* 25 */     String coords = mlc.getString("location");
/* 26 */     coords = mlc.getString("loc", coords);
/* 27 */     coords = mlc.getString("l", coords);
/*    */     
/* 29 */     if (coords != null) {
/* 30 */       String[] split = coords.split(",");
/*    */       
/*    */       try {
/* 33 */         this.x = Integer.parseInt(split[0]);
/* 34 */         this.y = Integer.parseInt(split[1]);
/* 35 */         this.z = Integer.parseInt(split[2]);
/* 36 */       } catch (Exception ex) {
/* 37 */         MythicMobs.error("PushButton skill coordinates are configured incorrectly in skill string: " + skill);
/*    */         return;
/*    */       } 
/*    */     } else {
/* 41 */       this.x = mlc.getInteger("x", 0);
/* 42 */       this.y = mlc.getInteger("y", 0);
/* 43 */       this.z = mlc.getInteger("z", 0);
/*    */     } 
/*    */   }
/*    */   protected int z; protected int ticks;
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 49 */     SkillAdapter.get().toggleLever(data.getCaster(), new AbstractLocation(data.getCaster().getEntity().getLocation().getWorld(), this.x, this.y, this.z), this.ticks);
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 55 */     SkillAdapter.get().toggleLever(data.getCaster(), target, this.ticks);
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ToggleLeverMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */