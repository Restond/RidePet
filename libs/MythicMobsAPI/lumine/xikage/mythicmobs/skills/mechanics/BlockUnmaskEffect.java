/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "blockUnmask", aliases = {"effect:blockUnmask", "e:blockunmask"}, description = "Unmasks any nearby blocks that have been masked")
/*    */ public class BlockUnmaskEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedLocationSkill
/*    */ {
/*    */   private Material mat;
/*    */   private int radius;
/*    */   private boolean sphere;
/*    */   private boolean noAir;
/*    */   private int radiusSq;
/*    */   
/*    */   public BlockUnmaskEffect(String skill, MythicLineConfig mlc) {
/* 29 */     super(skill, mlc);
/* 30 */     this.ASYNC_SAFE = false;
/*    */     
/* 32 */     this.radius = mlc.getInteger(new String[] { "radius", "r" }, 0);
/* 33 */     String shape = mlc.getString(new String[] { "shape", "s" }, "SPHERE", new String[0]).toUpperCase();
/*    */     
/* 35 */     if (shape.equals("SPHERE")) {
/* 36 */       this.sphere = true;
/*    */     } else {
/* 38 */       this.sphere = false;
/*    */     } 
/*    */     
/* 41 */     if (this.radius < 0) this.radius = 0; 
/* 42 */     this.radiusSq = this.radius * this.radius;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 47 */     playEffect(target);
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public void playEffect(AbstractLocation location) {
/* 52 */     Location l = BukkitAdapter.adapt(location);
/*    */     
/* 54 */     if (this.radius == 0) {
/* 55 */       for (Player p : l.getWorld().getPlayers()) {
/* 56 */         p.sendBlockChange(l, l.getBlock().getType(), l.getBlock().getData());
/*    */       }
/*    */     } else {
/* 59 */       for (Location ll : getBlocksInRadius(l)) {
/* 60 */         for (Player p : l.getWorld().getPlayers()) {
/* 61 */           p.sendBlockChange(ll, ll.getBlock().getType(), ll.getBlock().getData());
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private List<Location> getBlocksInRadius(Location l) {
/* 68 */     List<Location> blocks = new ArrayList<>();
/* 69 */     for (int x = -this.radius; x <= this.radius; x++) {
/* 70 */       for (int y = -this.radius; y <= this.radius; y++) {
/* 71 */         for (int z = -this.radius; z <= this.radius; z++) {
/* 72 */           Location newloc = new Location(l.getWorld(), l.getX() + x, l.getY() + y, l.getZ() + z);
/* 73 */           if (this.sphere != true || 
/* 74 */             l.distanceSquared(newloc) <= this.radiusSq)
/*    */           {
/*    */             
/* 77 */             if (this.noAir == true && !newloc.getBlock().getType().equals(Material.AIR)) {
/* 78 */               blocks.add(newloc);
/* 79 */             } else if (!this.noAir) {
/* 80 */               blocks.add(newloc);
/*    */             } 
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 86 */     return blocks;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BlockUnmaskEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */