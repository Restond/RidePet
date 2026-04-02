/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.ReflectionUtils;
/*     */ import org.bukkit.Bukkit;
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
/*     */ public enum PackageType
/*     */ {
/* 101 */   MINECRAFT_SERVER("net.minecraft.server." + getServerVersion()),
/* 102 */   CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion()),
/* 103 */   CRAFTBUKKIT_BLOCK(CRAFTBUKKIT, "block"),
/* 104 */   CRAFTBUKKIT_CHUNKIO(CRAFTBUKKIT, "chunkio"),
/* 105 */   CRAFTBUKKIT_COMMAND(CRAFTBUKKIT, "command"),
/* 106 */   CRAFTBUKKIT_CONVERSATIONS(CRAFTBUKKIT, "conversations"),
/* 107 */   CRAFTBUKKIT_ENCHANTMENS(CRAFTBUKKIT, "enchantments"),
/* 108 */   CRAFTBUKKIT_ENTITY(CRAFTBUKKIT, "entity"),
/* 109 */   CRAFTBUKKIT_EVENT(CRAFTBUKKIT, "event"),
/* 110 */   CRAFTBUKKIT_GENERATOR(CRAFTBUKKIT, "generator"),
/* 111 */   CRAFTBUKKIT_HELP(CRAFTBUKKIT, "help"),
/* 112 */   CRAFTBUKKIT_INVENTORY(CRAFTBUKKIT, "inventory"),
/* 113 */   CRAFTBUKKIT_MAP(CRAFTBUKKIT, "map"),
/* 114 */   CRAFTBUKKIT_METADATA(CRAFTBUKKIT, "metadata"),
/* 115 */   CRAFTBUKKIT_POTION(CRAFTBUKKIT, "potion"),
/* 116 */   CRAFTBUKKIT_PROJECTILES(CRAFTBUKKIT, "projectiles"),
/* 117 */   CRAFTBUKKIT_SCHEDULER(CRAFTBUKKIT, "scheduler"),
/* 118 */   CRAFTBUKKIT_SCOREBOARD(CRAFTBUKKIT, "scoreboard"),
/* 119 */   CRAFTBUKKIT_UPDATER(CRAFTBUKKIT, "updater"),
/* 120 */   CRAFTBUKKIT_UTIL(CRAFTBUKKIT, "util");
/*     */   
/*     */   private final String path;
/*     */   
/*     */   PackageType(String path) {
/* 125 */     this.path = path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 133 */     return this.path;
/*     */   }
/*     */   
/*     */   public Class<?> getClass(String className) throws ClassNotFoundException {
/* 137 */     return Class.forName(this + "." + className);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return this.path;
/*     */   }
/*     */   
/*     */   public static String getServerVersion() {
/* 147 */     return Bukkit.getServer().getClass().getPackage().getName().substring(23);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\ReflectionUtils$PackageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */