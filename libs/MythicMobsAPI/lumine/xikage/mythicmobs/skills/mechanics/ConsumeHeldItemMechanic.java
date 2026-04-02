/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.ArtifactsSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "removeHeldItem", aliases = {"consumeHeldItem", "takeHeldItem"}, description = "Removes an amount of the target's held item")
/*    */ public class ConsumeHeldItemMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/*    */   private int amount;
/*    */   
/*    */   public ConsumeHeldItemMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/*    */     
/* 25 */     this.amount = mlc.getInteger(new String[] { "amount", "a" }, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 30 */     if (!data.getCaster().getEntity().isPlayer()) return false;
/*    */     
/* 32 */     Optional<Object> maybeHeld = data.getMetadata("used-slot");
/* 33 */     if (maybeHeld.isPresent()) {
/* 34 */       int itemSlot = ((Integer)maybeHeld.get()).intValue();
/* 35 */       Player player1 = (Player)BukkitAdapter.adapt(data.getCaster().getEntity());
/* 36 */       ItemStack itemStack = player1.getInventory().getItem(itemSlot);
/*    */       
/* 38 */       if (itemStack != null) {
/* 39 */         int newAmount = itemStack.getAmount() - this.amount;
/*    */         
/* 41 */         if (newAmount <= 0) {
/* 42 */           getPlugin().getVolatileCodeHandler().getItemHandler().destroyItem(itemStack);
/* 43 */           if (MythicMobs.inst().getCompatibility().getArtifacts().isPresent()) {
/* 44 */             ((ArtifactsSupport)MythicMobs.inst().getCompatibility().getArtifacts().get()).reparseWeapon(data.getCaster().getEntity().asPlayer());
/*    */           }
/*    */         } else {
/* 47 */           itemStack.setAmount(newAmount);
/*    */         } 
/* 49 */         player1.updateInventory();
/*    */       } 
/*    */       
/* 52 */       return true;
/*    */     } 
/* 54 */     Player player = (Player)data.getCaster().getEntity().getBukkitEntity();
/* 55 */     ItemStack item = player.getInventory().getItemInMainHand();
/*    */     
/* 57 */     if (item != null) {
/* 58 */       int newAmount = item.getAmount() - this.amount;
/*    */       
/* 60 */       if (newAmount <= 0) {
/* 61 */         getPlugin().getVolatileCodeHandler().getItemHandler().destroyItem(item);
/* 62 */         player.updateInventory();
/* 63 */         if (MythicMobs.inst().getCompatibility().getArtifacts().isPresent()) {
/* 64 */           ((ArtifactsSupport)MythicMobs.inst().getCompatibility().getArtifacts().get()).reparseWeapon(data.getCaster().getEntity().asPlayer());
/*    */         }
/*    */       } else {
/* 67 */         item.setAmount(newAmount);
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ConsumeHeldItemMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */