/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setgravity", aliases = {"setusegravity"}, description = "ets whether gravity affects the target entity")
/*    */ public class SetUseGravityMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected boolean b;
/*    */   
/*    */   public SetUseGravityMechanic(String skill, MythicLineConfig mlc) {
/* 22 */     super(skill, mlc);
/*    */     
/* 24 */     this.b = mlc.getBoolean(new String[] { "gravity", "g", "use", "u", "bool", "b" }, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 29 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 30 */       if (target.isLiving()) {
/* 31 */         LivingEntity e = (LivingEntity)BukkitAdapter.adapt(target);
/* 32 */         e.setGravity(this.b);
/*    */       } 
/* 34 */     } else if (target.getBukkitEntity().getType() == EntityType.ARMOR_STAND) {
/* 35 */       ArmorStand armorStand = (ArmorStand)BukkitAdapter.adapt(target);
/* 36 */       armorStand.setGravity(this.b);
/*    */     } 
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetUseGravityMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */