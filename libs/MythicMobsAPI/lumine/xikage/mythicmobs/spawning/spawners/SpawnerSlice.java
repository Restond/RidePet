/*    */ package lumine.xikage.mythicmobs.spawning.spawners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Effect;
/*    */ 
/*    */ public class SpawnerSlice
/*    */ {
/*    */   protected MythicSpawner spawner;
/*    */   protected AbstractLocation origin;
/*    */   protected int offset_x;
/*    */   protected int offset_y;
/*    */   protected int offset_z;
/* 18 */   public static List<io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice> CutBuffer = new ArrayList<>();
/*    */   public static boolean cut = true;
/*    */   
/*    */   public static void CutSpawners(List<MythicSpawner> msl, AbstractLocation base) {
/* 22 */     CutBuffer.clear();
/*    */     
/* 24 */     for (MythicSpawner ms : msl) {
/* 25 */       CutBuffer.add(new io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice(ms, base));
/*    */     }
/* 27 */     cut = true;
/*    */   }
/*    */   
/*    */   public static boolean PasteSpawners(AbstractLocation base) {
/* 31 */     if (CutBuffer.size() == 0) {
/* 32 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 37 */     if (cut == true) {
/* 38 */       for (io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice ss : CutBuffer) {
/* 39 */         AbstractLocation l = ss.getRelativeLocation(base);
/* 40 */         if (MythicMobs.inst().getSpawnerManager().moveSpawner(ss.getSpawner(), l)) {
/* 41 */           BukkitAdapter.adapt(l).getWorld().playEffect(BukkitAdapter.adapt(l), Effect.MOBSPAWNER_FLAMES, 0);
/* 42 */           BukkitAdapter.adapt(l).getWorld().playEffect(BukkitAdapter.adapt(l), Effect.EXTINGUISH, 0);
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 47 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean Undo() {
/* 51 */     if (CutBuffer.size() == 0) {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 57 */     if (cut == true) {
/* 58 */       for (io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerSlice ss : CutBuffer) {
/* 59 */         AbstractLocation l = ss.getOriginalLocation();
/* 60 */         if (MythicMobs.inst().getSpawnerManager().moveSpawner(ss.getSpawner(), l)) {
/* 61 */           BukkitAdapter.adapt(l.getWorld()).playEffect(BukkitAdapter.adapt(l), Effect.MOBSPAWNER_FLAMES, 0);
/* 62 */           BukkitAdapter.adapt(l.getWorld()).playEffect(BukkitAdapter.adapt(l), Effect.EXTINGUISH, 0);
/*    */         } 
/*    */       } 
/*    */     }
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public SpawnerSlice(MythicSpawner ms, AbstractLocation base) {
/* 70 */     this.spawner = ms;
/*    */     
/* 72 */     AbstractLocation l = ms.getLocation();
/*    */     
/* 74 */     this.offset_x = l.getBlockX() - base.getBlockX();
/* 75 */     this.offset_y = l.getBlockY() - base.getBlockY();
/* 76 */     this.offset_z = l.getBlockZ() - base.getBlockZ();
/* 77 */     this.origin = ms.getLocation();
/*    */   }
/*    */   
/*    */   public MythicSpawner getSpawner() {
/* 81 */     return this.spawner;
/*    */   }
/*    */   
/*    */   public AbstractLocation getOriginalLocation() {
/* 85 */     return this.origin;
/*    */   }
/*    */   
/*    */   public AbstractLocation getRelativeLocation(AbstractLocation base) {
/* 89 */     AbstractLocation l = new AbstractLocation(base.getWorld(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 91 */     l.setX((base.getBlockX() + this.offset_x));
/* 92 */     l.setY((base.getBlockY() + this.offset_y));
/* 93 */     l.setZ((base.getBlockZ() + this.offset_z));
/*    */     
/* 95 */     return l;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\spawners\SpawnerSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */