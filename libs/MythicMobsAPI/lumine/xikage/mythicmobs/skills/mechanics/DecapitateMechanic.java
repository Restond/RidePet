/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.inventory.meta.SkullMeta;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "decapitate", aliases = {"dropHead"}, description = "Drops the target entity's head")
/*    */ public class DecapitateMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   public DecapitateMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 26 */     if (!target.isPlayer()) return true;
/*    */     
/* 28 */     ItemStack skull = new ItemStack(VolatileMaterial.PLAYER_HEAD, 1, (short)3);
/* 29 */     SkullMeta meta = (SkullMeta)skull.getItemMeta();
/*    */     
/* 31 */     meta.setOwner(target.asPlayer().getName());
/* 32 */     skull.setItemMeta((ItemMeta)meta);
/*    */     
/* 34 */     Location location = BukkitAdapter.adapt(target.getLocation());
/* 35 */     location.getWorld().dropItem(location, skull);
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DecapitateMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */