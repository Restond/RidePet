/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import java.lang.reflect.Field;
/*     */ import java.math.BigInteger;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.util.BlockIterator;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class MythicUtil {
/*     */   public static final float DEGTORAD = 0.017453292F;
/*     */   public static final float RADTODEG = 57.29578F;
/*     */   
/*     */   public static boolean matchNumber(String s, double i) {
/*  27 */     if (s == null) return false;
/*     */     
/*     */     try {
/*  30 */       s = s.replace(" ", "");
/*     */       
/*  32 */       if (s.contains("=")) {
/*  33 */         double d = Double.parseDouble(s.substring(1));
/*     */         
/*  35 */         if (i == d) return true; 
/*  36 */       } else if (s.contains(">")) {
/*  37 */         double d = Double.parseDouble(s.substring(1));
/*     */         
/*  39 */         if (i > d) return true; 
/*  40 */       } else if (s.contains("<")) {
/*  41 */         double d = Double.parseDouble(s.substring(1));
/*     */         
/*  43 */         if (i < d) return true; 
/*  44 */       } else if (s.contains("to")) {
/*  45 */         String[] split = s.split("to");
/*     */         
/*  47 */         double d1 = Double.parseDouble(split[0]);
/*  48 */         double d2 = Double.parseDouble(split[1]);
/*     */         
/*  50 */         if (i >= d1 && i <= d2) return true; 
/*  51 */       } else if (s.contains("-")) {
/*  52 */         String[] split = s.split("-");
/*     */         
/*  54 */         double d1 = Double.parseDouble(split[0]);
/*  55 */         double d2 = Double.parseDouble(split[1]);
/*     */         
/*  57 */         if (i >= d1 && i <= d2) return true; 
/*     */       } else {
/*  59 */         double d = Double.parseDouble(s);
/*     */         
/*  61 */         if (i == d) return true; 
/*     */       } 
/*  63 */       return false;
/*  64 */     } catch (Exception ex) {
/*  65 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getPrivateField(String fieldName, Class clazz, Object object) {
/*  73 */     Object o = null;
/*     */ 
/*     */     
/*     */     try {
/*  77 */       Field field = clazz.getDeclaredField(fieldName);
/*     */       
/*  79 */       field.setAccessible(true);
/*     */       
/*  81 */       o = field.get(object);
/*     */     }
/*  83 */     catch (NoSuchFieldException e) {
/*     */       
/*  85 */       e.printStackTrace();
/*     */     }
/*  87 */     catch (IllegalAccessException e) {
/*     */       
/*  89 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  92 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPrivateField(String fieldName, Class clazz, Object object, Object value) {
/*     */     try {
/* 101 */       Field field = clazz.getDeclaredField(fieldName);
/*     */       
/* 103 */       field.setAccessible(true);
/* 104 */       field.set(object, value);
/*     */     
/*     */     }
/* 107 */     catch (NoSuchFieldException e) {
/*     */       
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     catch (IllegalAccessException e) {
/*     */       
/* 113 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static LivingEntity getTargetedEntity(Player player) {
/*     */     BlockIterator bi;
/* 120 */     int range = 32;
/* 121 */     List<Entity> ne = player.getNearbyEntities(range, range, range);
/*     */ 
/*     */     
/* 124 */     List<LivingEntity> entities = new ArrayList<>();
/* 125 */     for (Entity en : ne) {
/* 126 */       if (en instanceof LivingEntity) {
/* 127 */         entities.add((LivingEntity)en);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 132 */     LivingEntity target = null;
/*     */ 
/*     */     
/*     */     try {
/* 136 */       bi = new BlockIterator((LivingEntity)player, range);
/* 137 */     } catch (IllegalStateException e) {
/* 138 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     while (bi.hasNext()) {
/* 147 */       Block b = bi.next();
/* 148 */       int bx = b.getX();
/* 149 */       int by = b.getY();
/* 150 */       int bz = b.getZ();
/* 151 */       if (!b.getType().isTransparent()) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 156 */       for (LivingEntity e : entities) {
/* 157 */         Location l = e.getLocation();
/* 158 */         double ex = l.getX();
/* 159 */         double ey = l.getY();
/* 160 */         double ez = l.getZ();
/* 161 */         if (bx - 0.75D <= ex && ex <= bx + 1.75D && bz - 0.75D <= ez && ez <= bz + 1.75D && (by - 1) <= ey && ey <= by + 2.5D) {
/*     */           
/* 163 */           target = e;
/*     */ 
/*     */           
/* 166 */           if (target != null && target instanceof Player && ((Player)target).getGameMode() == GameMode.CREATIVE) {
/* 167 */             target = null;
/*     */             
/*     */             continue;
/*     */           } 
/* 171 */           return target;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendFakeBlockChange(Player player, Block block, Material mat) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static void restoreFakeBlockChange(Player player, Block block) {}
/*     */ 
/*     */   
/*     */   public static void rotateVector(Vector v, float degrees) {
/* 190 */     double rad = Math.toRadians(degrees);
/* 191 */     double sin = Math.sin(rad);
/* 192 */     double cos = Math.cos(rad);
/* 193 */     double x = v.getX() * cos - v.getZ() * sin;
/* 194 */     double z = v.getX() * sin + v.getZ() * cos;
/* 195 */     v.setX(x);
/* 196 */     v.setZ(z);
/*     */   }
/*     */   
/*     */   public static Double calculateLaunchAngle(Location from, Location to, double v, double elevation, double g) {
/* 200 */     Vector vector = from.clone().subtract(to).toVector();
/* 201 */     Double distance = Double.valueOf(Math.sqrt(Math.pow(vector.getX(), 2.0D) + Math.pow(vector.getZ(), 2.0D)));
/*     */     
/* 203 */     double v2 = Math.pow(v, 2.0D);
/* 204 */     double v4 = Math.pow(v, 4.0D);
/*     */     
/* 206 */     double check = g * (g * Math.pow(distance.doubleValue(), 2.0D) + 2.0D * elevation * v2);
/*     */     
/* 208 */     if (v4 < check) {
/* 209 */       return null;
/*     */     }
/* 211 */     return Double.valueOf(Math.atan((v2 - Math.sqrt(v4 - check)) / g * distance.doubleValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static double calculateHangtime(double launchAngle, double v, double elev, double g) {
/* 216 */     double a = v * Math.sin(launchAngle);
/* 217 */     double b = -2.0D * g * elev;
/*     */     
/* 219 */     if (Math.pow(a, 2.0D) + b < 0.0D) {
/* 220 */       return 0.0D;
/*     */     }
/*     */     
/* 223 */     return (a + Math.sqrt(Math.pow(a, 2.0D) + b)) / g;
/*     */   }
/*     */   
/*     */   public static Vector normalizeVector(Vector victor) {
/* 227 */     double mag = Math.sqrt(Math.pow(victor.getX(), 2.0D) + Math.pow(victor.getY(), 2.0D) + Math.pow(victor.getZ(), 2.0D));
/* 228 */     if (mag != 0.0D) return victor.multiply(1.0D / mag); 
/* 229 */     return victor.multiply(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Location moveBukkit(Location loc, Vector offset) {
/* 236 */     return moveBukkit(loc, offset.getX(), offset.getY(), offset.getZ());
/*     */   }
/*     */   public static Location moveBukkit(Location loc, double dx, double dy, double dz) {
/* 239 */     Vector off = rotateBukkit(loc.getYaw(), loc.getPitch(), dx, dy, dz);
/* 240 */     double x = loc.getX() + off.getX();
/* 241 */     double y = loc.getY() + off.getY();
/* 242 */     double z = loc.getZ() + off.getZ();
/* 243 */     return new Location(loc.getWorld(), x, y, z, loc.getYaw(), loc.getPitch());
/*     */   }
/*     */   public static Vector rotateBukkit(float yaw, float pitch, Vector value) {
/* 246 */     return rotateBukkit(yaw, pitch, value.getX(), value.getY(), value.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vector rotateBukkit(float yaw, float pitch, double x, double y, double z) {
/* 251 */     float angle = yaw * 0.017453292F;
/* 252 */     double sinyaw = Math.sin(angle);
/* 253 */     double cosyaw = Math.cos(angle);
/*     */     
/* 255 */     angle = pitch * 0.017453292F;
/* 256 */     double sinpitch = Math.sin(angle);
/* 257 */     double cospitch = Math.cos(angle);
/*     */     
/* 259 */     double newx = 0.0D;
/* 260 */     double newy = 0.0D;
/* 261 */     double newz = 0.0D;
/* 262 */     newz -= x * cosyaw;
/* 263 */     newz -= y * sinyaw * sinpitch;
/* 264 */     newz -= z * sinyaw * cospitch;
/* 265 */     newx += x * sinyaw;
/* 266 */     newx -= y * cosyaw * sinpitch;
/* 267 */     newx -= z * cosyaw * cospitch;
/* 268 */     newy += y * cospitch;
/* 269 */     newy -= z * sinpitch;
/*     */     
/* 271 */     return new Vector(newx, newy, newz);
/*     */   }
/*     */   
/*     */   public static AbstractLocation move(AbstractLocation loc, AbstractVector offset) {
/* 275 */     return move(loc, offset.getX(), offset.getY(), offset.getZ());
/*     */   }
/*     */   public static AbstractLocation move(AbstractLocation loc, double dx, double dy, double dz) {
/* 278 */     AbstractVector off = rotate(loc.getYaw(), loc.getPitch(), dx, dy, dz);
/* 279 */     double x = loc.getX() + off.getX();
/* 280 */     double y = loc.getY() + off.getY();
/* 281 */     double z = loc.getZ() + off.getZ();
/* 282 */     return new AbstractLocation(loc.getWorld(), x, y, z, loc.getYaw(), loc.getPitch());
/*     */   }
/*     */   public static AbstractVector rotate(float yaw, float pitch, AbstractVector value) {
/* 285 */     return rotate(yaw, pitch, value.getX(), value.getY(), value.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbstractVector rotate(float yaw, float pitch, double x, double y, double z) {
/* 290 */     float angle = yaw * 0.017453292F;
/* 291 */     double sinyaw = Math.sin(angle);
/* 292 */     double cosyaw = Math.cos(angle);
/*     */     
/* 294 */     angle = pitch * 0.017453292F;
/* 295 */     double sinpitch = Math.sin(angle);
/* 296 */     double cospitch = Math.cos(angle);
/*     */     
/* 298 */     double newx = 0.0D;
/* 299 */     double newy = 0.0D;
/* 300 */     double newz = 0.0D;
/* 301 */     newz -= x * cosyaw;
/* 302 */     newz -= y * sinyaw * sinpitch;
/* 303 */     newz -= z * sinyaw * cospitch;
/* 304 */     newx += x * sinyaw;
/* 305 */     newx -= y * cosyaw * sinpitch;
/* 306 */     newx -= z * cosyaw * cospitch;
/* 307 */     newy += y * cospitch;
/* 308 */     newy -= z * sinpitch;
/*     */     
/* 310 */     return new AbstractVector(newx, newy, newz);
/*     */   }
/*     */   
/*     */   public static UUID getUUIDFromString(String s) {
/* 314 */     String md5 = getMD5(s);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 320 */     String uuid = md5.substring(0, 8) + "-" + md5.substring(8, 12) + "-" + md5.substring(12, 16) + "-" + md5.substring(16, 20) + "-" + md5.substring(20);
/*     */     
/* 322 */     return UUID.fromString(uuid);
/*     */   }
/*     */   
/*     */   public static String getMD5(String input) {
/*     */     try {
/* 327 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 328 */       byte[] messageDigest = md.digest(input.getBytes());
/* 329 */       BigInteger number = new BigInteger(1, messageDigest);
/* 330 */       String hashtext = number.toString(16);
/*     */       
/* 332 */       while (hashtext.length() < 32) {
/* 333 */         hashtext = "0" + hashtext;
/*     */       }
/* 335 */       return hashtext;
/*     */     }
/* 337 */     catch (NoSuchAlgorithmException e) {
/* 338 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String[] wrapString(String s, int l) {
/* 343 */     String r = "";
/* 344 */     String d = "&&br&&";
/* 345 */     int ldp = 0;
/*     */     
/* 347 */     for (String t : s.split(" ", -1)) {
/* 348 */       if (r.length() - ldp + t.length() > l) {
/* 349 */         r = r + d + t;
/* 350 */         ldp = r.length() + 1;
/*     */       } else {
/* 352 */         r = r + (r.isEmpty() ? "" : " ") + t;
/*     */       } 
/*     */     } 
/* 355 */     return r.split(d);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\MythicUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */