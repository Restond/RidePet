/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Events;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.PrisonMechanic;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Prison
/*     */   implements Terminable
/*     */ {
/*     */   private AbstractEntity prisoner;
/*     */   private AbstractLocation location;
/*  70 */   private ArrayList<Block> prisonBlocks = new ArrayList<>();
/*     */   
/*     */   private Terminable listener;
/*     */   
/*     */   public Prison(AbstractEntity prisoner) {
/*  75 */     this.prisoner = prisoner;
/*     */     
/*  77 */     int x = prisoner.getLocation().getBlockX();
/*  78 */     int y = prisoner.getLocation().getBlockY();
/*  79 */     int z = prisoner.getLocation().getBlockZ();
/*     */     
/*  81 */     this.location = new AbstractLocation(prisoner.getWorld(), (x < 0) ? (x - 0.5D) : (x + 0.5D), y + 0.5D, (z < 0) ? (z - 0.5D) : (z + 0.5D), prisoner.getLocation().getYaw(), prisoner.getLocation().getPitch());
/*     */     
/*  83 */     this.listener = (Terminable)Events.subscribe(BlockBreakEvent.class).handler(event -> {
/*     */           if (this.prisonBlocks.contains(event.getBlock())) {
/*     */             event.setCancelled(true);
/*     */             
/*     */             if (PrisonMechanic.this.breakable) {
/*     */               event.getBlock().setType(Material.AIR);
/*     */             }
/*     */           } 
/*     */         });
/*  92 */     prisoner.teleport(this.location);
/*  93 */     imprison();
/*  94 */     Schedulers.sync().runLater(() -> terminate(), paramPrisonMechanic.duration);
/*     */   }
/*     */   
/*     */   private void imprison() {
/*  98 */     Entity target = BukkitAdapter.adapt(this.prisoner);
/*  99 */     Block feet = target.getLocation().getBlock();
/*     */ 
/*     */     
/* 102 */     Block temp = feet.getRelative(1, 0, 0);
/* 103 */     if (temp.getType() == Material.AIR) {
/* 104 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 106 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 108 */     temp = feet.getRelative(1, 1, 0);
/* 109 */     if (temp.getType() == Material.AIR) {
/* 110 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 112 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 114 */     temp = feet.getRelative(-1, 0, 0);
/* 115 */     if (temp.getType() == Material.AIR) {
/* 116 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 118 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 120 */     temp = feet.getRelative(-1, 1, 0);
/* 121 */     if (temp.getType() == Material.AIR) {
/* 122 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 124 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 126 */     temp = feet.getRelative(0, 0, 1);
/* 127 */     if (temp.getType() == Material.AIR) {
/* 128 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 130 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 132 */     temp = feet.getRelative(0, 1, 1);
/* 133 */     if (temp.getType() == Material.AIR) {
/* 134 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 136 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 138 */     temp = feet.getRelative(0, 0, -1);
/* 139 */     if (temp.getType() == Material.AIR) {
/* 140 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 142 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 144 */     temp = feet.getRelative(0, 1, -1);
/* 145 */     if (temp.getType() == Material.AIR) {
/* 146 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 148 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 150 */     temp = feet.getRelative(0, -1, 0);
/* 151 */     if (temp.getType() == Material.AIR) {
/* 152 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 154 */       this.prisonBlocks.add(temp);
/*     */     } 
/* 156 */     temp = feet.getRelative(0, 2, 0);
/* 157 */     if (temp.getType() == Material.AIR) {
/* 158 */       temp.setType(PrisonMechanic.this.material);
/*     */       
/* 160 */       this.prisonBlocks.add(temp);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 166 */     for (Block block : this.prisonBlocks) {
/* 167 */       if (PrisonMechanic.this.material == block.getType()) {
/* 168 */         block.setType(Material.AIR);
/*     */       }
/*     */     } 
/* 171 */     return this.listener.terminate();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PrisonMechanic$Prison.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */