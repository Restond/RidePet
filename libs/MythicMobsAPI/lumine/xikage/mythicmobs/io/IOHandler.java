/*    */ package lumine.xikage.mythicmobs.io;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ public class IOHandler
/*    */ {
/*    */   public static List<File> getAllFiles(String directoryName) {
/* 14 */     File directory = new File(directoryName);
/*    */     
/* 16 */     List<File> resultList = new ArrayList<>();
/*    */     
/* 18 */     File[] fList = directory.listFiles();
/*    */     
/* 20 */     for (File file : fList) {
/* 21 */       if (file.isFile() && (file.getName().endsWith(".yml") || file.getName().endsWith(".txt"))) {
/* 22 */         resultList.add(file);
/* 23 */         MythicMobs.debug(1, "Loading file: " + file.getAbsolutePath());
/* 24 */       } else if (file.isDirectory()) {
/* 25 */         resultList.addAll(getAllFiles(file.getAbsolutePath()));
/*    */       } 
/*    */     } 
/* 28 */     return resultList;
/*    */   }
/*    */   
/*    */   public static String getList(String s, File[] list) {
/* 32 */     for (File f : list) {
/* 33 */       s = s + f.getName() + ", ";
/*    */     }
/* 35 */     return s;
/*    */   }
/*    */   
/*    */   public static <T extends JavaPlugin> List<IOLoader<T>> getSaveLoad(T plugin, List<File> itemFiles, String s) {
/* 39 */     List<IOLoader<T>> list = new ArrayList<>();
/* 40 */     for (File f : itemFiles) {
/* 41 */       list.add(new IOLoader((JavaPlugin)plugin, f, s));
/*    */     }
/* 43 */     return list;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\io\IOHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */