/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.EggManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ public class EggListeners
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void EggEvent(PlayerInteractEvent e) {
/*  32 */     if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*     */       
/*  34 */       if (MythicMobs.inst().getMinecraftVersion() > 8 && 
/*  35 */         e.getHand() != EquipmentSlot.HAND) {
/*     */         return;
/*     */       }
/*     */       
/*  39 */       ItemStack i = e.getItem();
/*     */       
/*  41 */       if (i == null) {
/*     */         return;
/*     */       }
/*  44 */       if (i.getType() != VolatileMaterial.SPAWN_EGG) {
/*     */         return;
/*     */       }
/*  47 */       if (!i.getItemMeta().hasLore()) {
/*     */         return;
/*     */       }
/*     */       
/*  51 */       MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "Detected Monster Egg usage, checking for Mythic Egg...", new Object[0]);
/*     */       
/*  53 */       List<String> list = e.getItem().getItemMeta().getLore();
/*  54 */       if (((String)list.get(0)).equals(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "A Mythical Egg that can")) {
/*  55 */         e.setCancelled(true);
/*     */         
/*  57 */         MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Mythic Egg used. Attempting to spawn mob...", new Object[0]);
/*  58 */         MythicMob mm = EggManager.getMythicMobFromEgg(list.get(2));
/*  59 */         if (mm == null) {
/*  60 */           MythicLogger.debug(MythicLogger.DebugLevel.INFO, "! Eggs mob type was invalid", new Object[0]);
/*     */           
/*     */           return;
/*     */         } 
/*  64 */         ItemStack eggItem = e.getItem().clone();
/*  65 */         if (eggItem.getAmount() > 1) {
/*  66 */           eggItem.setAmount(eggItem.getAmount() - 1);
/*  67 */           e.getPlayer().setItemInHand(eggItem);
/*     */         } else {
/*  69 */           e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
/*     */         } 
/*     */         
/*  72 */         if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*  73 */           MythicLogger.debug(MythicLogger.DebugLevel.INFO, "| Egg was on right-clicked block. Spawning mob at block.", new Object[0]);
/*     */           
/*  75 */           Location location = e.getClickedBlock().getLocation().clone();
/*  76 */           location.setY(location.getY() + 1.0D);
/*     */           
/*  78 */           MythicMobs.inst().getMobManager().spawnMob(mm.getInternalName(), location);
/*  79 */           MythicLogger.debug(MythicLogger.DebugLevel.INFO, "+ Mythic Mob {0} was spawned from an egg.", new Object[] { mm.getInternalName() });
/*     */         } else {
/*  81 */           MythicLogger.debug(MythicLogger.DebugLevel.INFO, "| Egg was thrown. Spawning mob at egg in 3 seconds...", new Object[0]);
/*  82 */           Player player = e.getPlayer();
/*  83 */           Item egg = e.getPlayer().getWorld().dropItem(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1.4D, player.getLocation().getZ()), eggItem);
/*     */           
/*  85 */           Vector dir = player.getLocation().getDirection();
/*  86 */           Vector vec = (new Vector(dir.getX(), dir.getY(), dir.getZ())).multiply(1);
/*  87 */           egg.setPickupDelay(32767);
/*  88 */           egg.setTicksLived(5800);
/*  89 */           egg.setVelocity(vec);
/*     */           
/*  91 */           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)new Object(this, mm, egg), 50L);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void DispenseEggEvent(BlockDispenseEvent e) {
/* 106 */     if (e.getBlock().getType() == Material.DROPPER)
/*     */       return; 
/* 108 */     ItemStack i = e.getItem();
/*     */     
/* 110 */     if (i == null) {
/*     */       return;
/*     */     }
/* 113 */     if (i.getType() != VolatileMaterial.SPAWN_EGG) {
/*     */       return;
/*     */     }
/* 116 */     if (!i.getItemMeta().hasLore()) {
/*     */       return;
/*     */     }
/*     */     
/* 120 */     MythicMobs.debug(3, "Detected Monster Egg being dispensed, parsing Mythic Egg...");
/*     */     
/* 122 */     List<String> list = e.getItem().getItemMeta().getLore();
/* 123 */     if (((String)list.get(0)).equals(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "A Mythical Egg that can")) {
/* 124 */       MythicMobs.debug(2, "Mythic Egg dispensed. Attempting to spawn mob...");
/* 125 */       MythicMob mm = EggManager.getMythicMobFromEgg(list.get(2));
/* 126 */       if (mm == null) {
/* 127 */         MythicMobs.debug(2, "-- The egg's mob was invalid?");
/*     */         return;
/*     */       } 
/* 130 */       Location location = e.getBlock().getLocation().clone();
/*     */       
/* 132 */       if (e.getBlock().getData() == 8) { location.setY(location.getY() - 1.0D); }
/* 133 */       else if (e.getBlock().getData() == 9) { location.setY(location.getY() + 1.0D); }
/* 134 */       else if (e.getBlock().getData() == 10) { location.setZ(location.getZ() - 1.0D); }
/* 135 */       else if (e.getBlock().getData() == 11) { location.setZ(location.getZ() + 1.0D); }
/* 136 */       else if (e.getBlock().getData() == 12) { location.setX(location.getX() - 1.0D); }
/* 137 */       else if (e.getBlock().getData() == 13) { location.setX(location.getX() + 1.0D); }
/* 138 */       else { location.setY(location.getY() + 1.0D); }
/*     */       
/* 140 */       MythicMobs.inst().getMobManager().spawnMob(mm.getInternalName(), location);
/*     */       
/* 142 */       MythicMobs.debug(1, "Mythic Mob " + mm.getInternalName() + " was spawned from a dispensed egg.");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\EggListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */