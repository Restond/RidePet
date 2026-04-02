/*     */ package lumine.utils.reflection;
/*     */ 
/*     */ import io.lumine.utils.reflection.Reflector;
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
/*     */ 
/*     */ 
/*     */ public enum PackageType
/*     */ {
/* 291 */   MINECRAFT_SERVER("net.minecraft.server." + getServerVersion()),
/* 292 */   CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion()),
/* 293 */   CRAFTBUKKIT_BLOCK(CRAFTBUKKIT, "block"),
/* 294 */   CRAFTBUKKIT_CHUNKIO(CRAFTBUKKIT, "chunkio"),
/* 295 */   CRAFTBUKKIT_COMMAND(CRAFTBUKKIT, "command"),
/* 296 */   CRAFTBUKKIT_CONVERSATIONS(CRAFTBUKKIT, "conversations"),
/* 297 */   CRAFTBUKKIT_ENCHANTMENS(CRAFTBUKKIT, "enchantments"),
/* 298 */   CRAFTBUKKIT_ENTITY(CRAFTBUKKIT, "entity"),
/* 299 */   CRAFTBUKKIT_EVENT(CRAFTBUKKIT, "event"),
/* 300 */   CRAFTBUKKIT_GENERATOR(CRAFTBUKKIT, "generator"),
/* 301 */   CRAFTBUKKIT_HELP(CRAFTBUKKIT, "help"),
/* 302 */   CRAFTBUKKIT_INVENTORY(CRAFTBUKKIT, "inventory"),
/* 303 */   CRAFTBUKKIT_MAP(CRAFTBUKKIT, "map"),
/* 304 */   CRAFTBUKKIT_METADATA(CRAFTBUKKIT, "metadata"),
/* 305 */   CRAFTBUKKIT_POTION(CRAFTBUKKIT, "potion"),
/* 306 */   CRAFTBUKKIT_PROJECTILES(CRAFTBUKKIT, "projectiles"),
/* 307 */   CRAFTBUKKIT_SCHEDULER(CRAFTBUKKIT, "scheduler"),
/* 308 */   CRAFTBUKKIT_SCOREBOARD(CRAFTBUKKIT, "scoreboard"),
/* 309 */   CRAFTBUKKIT_UPDATER(CRAFTBUKKIT, "updater"),
/* 310 */   CRAFTBUKKIT_UTIL(CRAFTBUKKIT, "util");
/*     */   
/*     */   private final String path;
/*     */   
/*     */   PackageType(String path) {
/* 315 */     this.path = path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 323 */     return this.path;
/*     */   }
/*     */   
/*     */   public Class<?> getClass(String className) throws ClassNotFoundException {
/* 327 */     return Class.forName(this + "." + className);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 333 */     return this.path;
/*     */   }
/*     */   
/*     */   public static String getServerVersion() {
/* 337 */     return Bukkit.getServer().getClass().getPackage().getName().substring(23);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\reflection\Reflector$PackageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */