/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ 
/*     */ import com.sk89q.worldedit.bukkit.BukkitWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGuardSix
/*     */   implements WorldGuardSupport.WorldGuardAdapter
/*     */ {
/*     */   int version;
/*     */   Class<?> class_WorldGuardPlugin;
/*     */   Class<?> class_WorldGuard;
/*     */   Class<?> class_FlagRegistry;
/*     */   Class<?> class_RegionContainer;
/*     */   Class<?> class_RegionManager;
/*     */   Class<?> class_ApplicableRegionSet;
/*     */   Class<?> class_EntityType;
/*     */   Class<?> class_Vector;
/*     */   Class<?> class_BukkitUtils;
/*     */   Method class_WorldGuardPlugin_getFlagRegistry;
/*     */   Method class_WorldGuard_getRegionContainer;
/*     */   Method class_RegionContainer_getRegionManager;
/*     */   Method class_RegionManager_getApplicableRegions;
/*     */   Method class_RegionManager_getApplicableRegionsIDs;
/*     */   Method class_RegionManager_getRegion;
/*     */   Method class_ApplicationRegionSet_getRegions;
/*     */   Method class_EntityType_getName;
/*     */   Method toVector;
/*     */   Object worldguard;
/*     */   Object flag_registry;
/*     */   
/*     */   public WorldGuardSix(Plugin worldguard) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 130 */     this.version = 6;
/*     */     
/* 132 */     load_classes();
/*     */ 
/*     */     
/* 135 */     this.worldguard = worldguard;
/*     */     
/* 137 */     this.flag_registry = this.class_WorldGuardPlugin.getMethod("getFlagRegistry", new Class[0]).invoke(worldguard, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocationInRegions(AbstractLocation loc, String region) {
/* 143 */     Location l = BukkitAdapter.adapt(loc);
/*     */     
/* 145 */     Object region_manager = null;
/* 146 */     Object applicable_region_set = null;
/*     */     
/*     */     try {
/* 149 */       region_manager = this.class_RegionContainer_getRegionManager.invoke(this.class_WorldGuard_getRegionContainer.invoke(this.worldguard, new Object[0]), new Object[] { l.getWorld() });
/* 150 */       if (region_manager != null) {
/* 151 */         Object vector = this.toVector.invoke(null, new Object[] { l });
/* 152 */         applicable_region_set = this.class_RegionManager_getApplicableRegionsIDs.invoke(region_manager, new Object[] { vector });
/*     */       } 
/* 154 */     } catch (Exception e) {
/* 155 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 158 */     if (applicable_region_set == null)
/*     */     {
/* 160 */       return false;
/*     */     }
/*     */     
/* 163 */     List<String> set = (List<String>)applicable_region_set;
/* 164 */     String[] regions = region.split(",");
/*     */     
/* 166 */     for (String str : set) {
/* 167 */       for (String r : regions) {
/*     */         
/* 169 */         if (r.equals(str)) {
/* 170 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 174 */     return false;
/*     */   }
/*     */   
/*     */   private void load_classes() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
/* 178 */     ClassLoader class_loader = getClass().getClassLoader();
/* 179 */     this.class_FlagRegistry = class_loader.loadClass("com.sk89q.worldguard.protection.flags.registry.FlagRegistry");
/* 180 */     this.class_RegionManager = class_loader.loadClass("com.sk89q.worldguard.protection.managers.RegionManager");
/* 181 */     this.class_ApplicableRegionSet = class_loader.loadClass("com.sk89q.worldguard.protection.ApplicableRegionSet");
/*     */     
/* 183 */     this.class_WorldGuardPlugin = class_loader.loadClass("com.sk89q.worldguard.bukkit.WorldGuardPlugin");
/* 184 */     this.class_WorldGuard = this.class_WorldGuardPlugin;
/* 185 */     this.class_RegionContainer = class_loader.loadClass("com.sk89q.worldguard.bukkit.RegionContainer");
/* 186 */     this.class_RegionContainer_getRegionManager = this.class_RegionContainer.getMethod("get", new Class[] { World.class });
/* 187 */     this.class_EntityType = class_loader.loadClass("org.bukkit.entity.EntityType");
/* 188 */     this.class_BukkitUtils = class_loader.loadClass("com.sk89q.worldguard.bukkit.BukkitUtil");
/*     */     
/* 190 */     this.toVector = this.class_BukkitUtils.getMethod("toVector", new Class[] { Location.class });
/* 191 */     this.class_Vector = class_loader.loadClass("com.sk89q.worldedit.Vector");
/* 192 */     this.class_WorldGuardPlugin_getFlagRegistry = this.class_WorldGuardPlugin.getMethod("getFlagRegistry", new Class[0]);
/* 193 */     this.class_WorldGuard_getRegionContainer = this.class_WorldGuard.getMethod("getRegionContainer", new Class[0]);
/* 194 */     this.class_RegionManager_getApplicableRegions = this.class_RegionManager.getMethod("getApplicableRegions", new Class[] { this.class_Vector });
/* 195 */     this.class_RegionManager_getApplicableRegionsIDs = this.class_RegionManager.getMethod("getApplicableRegionsIDs", new Class[] { this.class_Vector });
/* 196 */     this.class_RegionManager_getRegion = this.class_RegionManager.getMethod("getRegion", new Class[] { String.class });
/* 197 */     this.class_ApplicationRegionSet_getRegions = this.class_ApplicableRegionSet.getMethod("getRegions", new Class[0]);
/* 198 */     this.class_EntityType_getName = this.class_EntityType.getMethod("getName", new Class[0]);
/*     */   }
/*     */   
/*     */   public Object getFlagRegistry() {
/* 202 */     return this.flag_registry;
/*     */   }
/*     */   
/*     */   public Object getApplicableRegions(World world, Vector vector) {
/* 206 */     Object region_manager = null;
/* 207 */     Object applicable_region_set = null;
/* 208 */     Object regions = null;
/*     */     try {
/* 210 */       region_manager = this.class_RegionContainer_getRegionManager.invoke(this.class_WorldGuard_getRegionContainer.invoke(this.worldguard, new Object[0]), new Object[] { (this.version > 6) ? new BukkitWorld(world) : world });
/* 211 */       if (region_manager != null) {
/* 212 */         applicable_region_set = this.class_RegionManager_getApplicableRegions.invoke(region_manager, new Object[] { vector });
/* 213 */         if (applicable_region_set != null) {
/* 214 */           regions = this.class_ApplicationRegionSet_getRegions.invoke(applicable_region_set, new Object[0]);
/*     */         }
/*     */       } 
/* 217 */     } catch (Exception e) {
/* 218 */       e.printStackTrace();
/*     */     } 
/* 220 */     return regions;
/*     */   }
/*     */   
/*     */   public Object getApplicableRegionIDs(World world, Vector vector) {
/* 224 */     Object region_manager = null;
/* 225 */     Object applicable_region_set = null;
/* 226 */     Object regions = null;
/*     */ 
/*     */     
/*     */     try {
/* 230 */       region_manager = this.class_RegionContainer_getRegionManager.invoke(this.class_WorldGuard_getRegionContainer.invoke(this.worldguard, new Object[0]), new Object[] { (this.version > 6) ? new BukkitWorld(world) : world });
/* 231 */       if (region_manager != null) {
/* 232 */         applicable_region_set = this.class_RegionManager_getApplicableRegions.invoke(region_manager, new Object[] { vector });
/* 233 */         if (applicable_region_set != null) {
/* 234 */           regions = this.class_ApplicationRegionSet_getRegions.invoke(applicable_region_set, new Object[0]);
/*     */         }
/*     */       } 
/* 237 */     } catch (Exception e) {
/* 238 */       e.printStackTrace();
/*     */     } 
/* 240 */     return regions;
/*     */   }
/*     */   
/*     */   public Object getRegion(World world, String region_name) {
/* 244 */     Object region_manager = null;
/* 245 */     Object region = null;
/*     */     try {
/* 247 */       region_manager = this.class_RegionContainer_getRegionManager.invoke(this.class_WorldGuard_getRegionContainer.invoke(this.worldguard, new Object[0]), new Object[] { (this.version > 6) ? new BukkitWorld(world) : world });
/* 248 */       if (region_manager != null) region = this.class_RegionManager_getRegion.invoke(region_manager, new Object[] { region_name }); 
/* 249 */     } catch (Exception e) {
/* 250 */       e.printStackTrace();
/*     */     } 
/* 252 */     return region;
/*     */   }
/*     */   
/*     */   public String class_EntityType_getName(Object entity_type) {
/* 256 */     String s1 = new String();
/*     */     try {
/* 258 */       s1 = (String)this.class_EntityType_getName.invoke(entity_type, new Object[0]);
/* 259 */     } catch (Exception e) {
/* 260 */       e.printStackTrace();
/*     */     } 
/* 262 */     if (s1.startsWith("minecraft:")) s1 = s1.substring(10); 
/* 263 */     return s1;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\WorldGuardSupport$WorldGuardSix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */