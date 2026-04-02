/*    */ package lumine.utils.timingslib;
/*    */ 
/*    */ import io.lumine.utils.timingslib.MCTiming;
/*    */ import io.lumine.utils.timingslib.TimingType;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class TimingManager {
/*    */   private static TimingType timingProvider;
/* 12 */   private static final Object LOCK = new Object();
/*    */   
/*    */   private final Plugin plugin;
/* 15 */   private final Map<String, MCTiming> timingCache = new HashMap<>(0);
/*    */   
/*    */   private TimingManager(Plugin plugin) {
/* 18 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public static io.lumine.utils.timingslib.TimingManager of(Plugin plugin) {
/* 23 */     return new io.lumine.utils.timingslib.TimingManager(plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming ofStart(String name) {
/* 28 */     return ofStart(name, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming ofStart(String name, MCTiming parent) {
/* 33 */     return of(name, parent).startTiming();
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming of(String name) {
/* 38 */     return of(name, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCTiming of(String name, MCTiming parent) {
/* 43 */     if (timingProvider == null) {
/* 44 */       synchronized (LOCK) {
/* 45 */         if (timingProvider == null) {
/*    */           try {
/* 47 */             Class<?> clazz = Class.forName("co.aikar.timings.Timing");
/* 48 */             Method startTiming = clazz.getMethod("startTiming", new Class[0]);
/* 49 */             if (startTiming.getReturnType() != clazz) {
/* 50 */               timingProvider = TimingType.MINECRAFT_18;
/*    */             } else {
/* 52 */               timingProvider = TimingType.MINECRAFT;
/*    */             } 
/* 54 */           } catch (ClassNotFoundException|NoSuchMethodException ignored1) {
/*    */             try {
/* 56 */               Class.forName("org.spigotmc.CustomTimingsHandler");
/* 57 */               timingProvider = TimingType.SPIGOT;
/* 58 */             } catch (ClassNotFoundException ignored2) {
/* 59 */               timingProvider = TimingType.EMPTY;
/*    */             } 
/*    */           } 
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 67 */     if (timingProvider.useCache()) {
/* 68 */       MCTiming timing; synchronized (this.timingCache) {
/* 69 */         String lowerKey = name.toLowerCase();
/* 70 */         timing = this.timingCache.get(lowerKey);
/* 71 */         if (timing == null) {
/* 72 */           timing = timingProvider.newTiming(this.plugin, name, parent);
/* 73 */           this.timingCache.put(lowerKey, timing);
/*    */         } 
/*    */       } 
/* 76 */       return timing;
/*    */     } 
/*    */     
/* 79 */     return timingProvider.newTiming(this.plugin, name, parent);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\timingslib\TimingManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */