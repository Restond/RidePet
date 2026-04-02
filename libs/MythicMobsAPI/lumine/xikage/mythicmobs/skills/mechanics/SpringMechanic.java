/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpringMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   private int tickDuration;
/*    */   private Material type;
/*    */   
/*    */   public SpringMechanic(String skill, MythicLineConfig mlc) {
/* 25 */     super(skill, mlc);
/* 26 */     this.ASYNC_SAFE = false;
/*    */     
/* 28 */     this.tickDuration = mlc.getInteger(new String[] { "duration", "d" }, 40);
/*    */     
/* 30 */     String strType = mlc.getString(new String[] { "type", "t" }, "water", new String[0]);
/*    */     
/* 32 */     if (strType.equalsIgnoreCase("lava")) {
/* 33 */       this.type = Material.LAVA;
/* 34 */     } else if (strType.equalsIgnoreCase("water")) {
/* 35 */       this.type = Material.WATER;
/*    */     } else {
/* 37 */       this.type = Material.WATER;
/*    */     } 
/*    */     
/* 40 */     if (this.tickDuration <= 0) this.tickDuration = 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 45 */     playEffect(data.getCaster(), target);
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 51 */     playEffect(data.getCaster(), target.getLocation());
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void playEffect(SkillCaster am, AbstractLocation target) {
/* 57 */     Block block = BukkitAdapter.adapt(target).getBlock();
/* 58 */     if (block.getType() == Material.AIR) {
/* 59 */       block.setType(this.type, true);
/* 60 */       new Animator(this, target);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpringMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */