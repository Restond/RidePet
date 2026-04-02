/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setspeed", description = "Sets the speed attribute of the target entity")
/*    */ public class SetSpeedMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected float speed;
/*    */   protected Type type;
/*    */   
/*    */   public SetSpeedMechanic(String skill, MythicLineConfig mlc) {
/* 25 */     super(skill, mlc);
/*    */     
/* 27 */     String strType = mlc.getString(new String[] { "type", "t" }, "WALK", new String[0]).toUpperCase();
/* 28 */     this.speed = mlc.getFloat(new String[] { "speed", "s", "amount", "a", "value", "v", "multiplier", "m" }, 1.0F);
/*    */     
/* 30 */     if (strType.contains("FLY")) {
/* 31 */       this.type = Type.FLYING;
/*    */     } else {
/* 33 */       this.type = Type.WALKING;
/*    */     } 
/*    */     
/* 36 */     float defaultSpeed = (this.type == Type.FLYING) ? 0.1F : 0.2F;
/*    */     
/* 38 */     this.speed = 1.0F * defaultSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 43 */     if (target.isPlayer()) {
/* 44 */       Player p = (Player)BukkitAdapter.adapt(target);
/*    */       
/* 46 */       if (this.type == Type.FLYING) {
/* 47 */         p.setFlySpeed(this.speed);
/*    */       } else {
/* 49 */         p.setWalkSpeed(this.speed);
/*    */       } 
/*    */     } 
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetSpeedMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */