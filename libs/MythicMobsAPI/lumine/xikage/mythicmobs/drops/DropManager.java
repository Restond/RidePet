/*    */ package lumine.xikage.mythicmobs.drops;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*    */ import io.lumine.xikage.mythicmobs.drops.MythicDropTable;
/*    */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*    */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class DropManager
/*    */ {
/*    */   private final MythicMobs core;
/* 24 */   private ConcurrentHashMap<String, DropTable> dropTables = new ConcurrentHashMap<>();
/* 25 */   private ConcurrentHashMap<String, MythicDropTable> legacyDropTables = new ConcurrentHashMap<>();
/*    */ 
/*    */   
/*    */   private List<Runnable> secondPass;
/*    */ 
/*    */   
/*    */   public void loadDropTables() {
/* 32 */     IOLoader<MythicMobs> defaultDroptables = new IOLoader((JavaPlugin)MythicMobs.inst(), "ExampleDropTables.yml", "DropTables");
/* 33 */     List<File> droptableFiles = IOHandler.getAllFiles(defaultDroptables.getFile().getParent());
/* 34 */     List<IOLoader<MythicMobs>> droptableLoaders = IOHandler.getSaveLoad((JavaPlugin)MythicMobs.inst(), droptableFiles, "DropTables");
/*    */     
/* 36 */     this.dropTables.clear();
/* 37 */     this.legacyDropTables.clear();
/*    */     
/* 39 */     for (IOLoader<MythicMobs> sl : droptableLoaders) {
/* 40 */       for (String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/* 41 */         if (sl.getCustomConfig().getStringList(s + ".Drops") != null) {
/* 42 */           String file = sl.getFile().getName();
/* 43 */           MythicConfig mc = new MythicConfig(s, sl.getCustomConfig());
/* 44 */           DropTable dt = new DropTable(file, s, mc);
/* 45 */           this.dropTables.put(s, dt);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     runSecondPass();
/*    */   }
/*    */   public DropManager(MythicMobs core) {
/* 58 */     this.secondPass = new ArrayList<>();
/*    */     this.core = core;
/*    */   } public void runSecondPass() {
/* 61 */     MythicMobs.debug(1, "Doing second pass on " + this.secondPass.size() + " drops.");
/* 62 */     this.secondPass.forEach(r -> r.run());
/* 63 */     this.secondPass.clear();
/*    */   }
/*    */   
/*    */   public void queueSecondPass(Runnable r) {
/* 67 */     this.secondPass.add(r);
/*    */   }
/*    */   
/*    */   public Optional<DropTable> getDropTable(String name) {
/* 71 */     return Optional.ofNullable(this.dropTables.getOrDefault(name, null));
/*    */   }
/*    */   
/*    */   public Collection<DropTable> getDropTables() {
/* 75 */     return this.dropTables.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void Drop(Location loc, int exp, List<ItemStack> drops) {
/* 80 */     for (ItemStack is : drops) {
/* 81 */       loc.getWorld().dropItemNaturally(loc, is);
/*    */     }
/*    */     
/* 84 */     if (exp != 0) {
/* 85 */       int i = exp % 4;
/* 86 */       int per = (exp - exp % 4) / 4;
/* 87 */       for (int y = 0; y < 4; y++) {
/*    */         
/* 89 */         ExperienceOrb eo = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
/* 90 */         eo.setExperience(per);
/*    */       } 
/* 92 */       if (i != 0) {
/*    */         
/* 94 */         ExperienceOrb eo = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
/* 95 */         eo.setExperience(i);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\DropManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */