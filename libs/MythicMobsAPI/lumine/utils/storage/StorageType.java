/*    */ package lumine.utils.storage;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum StorageType
/*    */ {
/* 10 */   YAML("YAML", new String[] { "yaml", "yml" }),
/* 11 */   JSON("JSON", new String[] { "json", "flatfile", "file"
/*    */     
/*    */     }),
/* 14 */   MARIADB("MariaDB", new String[] { "mariadb" }),
/* 15 */   MYSQL("MySQL", new String[] { "mysql" }),
/* 16 */   POSTGRESQL("PostgreSQL", new String[] { "postgresql"
/*    */     
/*    */     }),
/* 19 */   SQLITE("SQLite", new String[] { "sqlite" });
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final List<String> identifiers;
/*    */ 
/*    */   
/*    */   StorageType(String name, String... identifiers) {
/* 27 */     this.name = name;
/* 28 */     this.identifiers = (List<String>)ImmutableList.copyOf((Object[])identifiers);
/*    */   }
/*    */   
/*    */   public static io.lumine.utils.storage.StorageType parse(String name, io.lumine.utils.storage.StorageType def) {
/* 32 */     for (io.lumine.utils.storage.StorageType t : values()) {
/* 33 */       for (String id : t.getIdentifiers()) {
/* 34 */         if (id.equalsIgnoreCase(name)) {
/* 35 */           return t;
/*    */         }
/*    */       } 
/*    */     } 
/* 39 */     return def;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */   
/*    */   public List<String> getIdentifiers() {
/* 47 */     return this.identifiers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\storage\StorageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */