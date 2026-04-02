/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:geyser", aliases = {"geyser", "e:geyser"}, description = "Creates a geyser at the target location")
/*    */ public class GeyserEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   private int height;
/*    */   private int tickInterval;
/*    */   private Material type;
/*    */   
/*    */   public GeyserEffect(String skill, MythicLineConfig mlc) {
/* 34 */     super(skill, mlc);
/* 35 */     this.ASYNC_SAFE = false;
/*    */     
/* 37 */     this.height = mlc.getInteger(new String[] { "height", "h" }, 3);
/* 38 */     this.tickInterval = mlc.getInteger(new String[] { "interval", "i", "speed", "s" }, 10);
/*    */     
/* 40 */     String strType = mlc.getString(new String[] { "type", "t" }, "water", new String[0]);
/*    */     
/* 42 */     if (strType.equalsIgnoreCase("lava")) {
/* 43 */       this.type = VolatileMaterial.LAVA;
/* 44 */     } else if (strType.equalsIgnoreCase("water")) {
/* 45 */       this.type = VolatileMaterial.WATER;
/*    */     } else {
/* 47 */       this.type = VolatileMaterial.WATER;
/*    */     } 
/*    */     
/* 50 */     if (this.height <= 0) this.height = 1;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 55 */     playEffect(data.getCaster(), target);
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 61 */     playEffect(data.getCaster(), target.getLocation());
/* 62 */     return true;
/*    */   }
/*    */   
/*    */   protected void playEffect(SkillCaster am, AbstractLocation target) {
/* 66 */     List<AbstractPlayer> players = target.getWorld().getPlayersNearLocation(target, 50);
/* 67 */     new Animator(this, target, players);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\GeyserEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */