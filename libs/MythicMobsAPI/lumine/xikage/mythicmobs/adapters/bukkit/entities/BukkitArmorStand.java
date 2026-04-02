/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ 
/*     */ public class BukkitArmorStand
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 2;
/*     */   private boolean isMarker = false;
/*     */   private boolean has_arms = true;
/*     */   private boolean has_gravity = true;
/*     */   private boolean invisible = false;
/*     */   private boolean small = false;
/*  29 */   private String item_head = null;
/*  30 */   private String item_body = null;
/*  31 */   private String item_legs = null;
/*  32 */   private String item_feet = null;
/*  33 */   private String item_hand = null;
/*  34 */   private String item_offhand = null;
/*     */   
/*  36 */   private Optional<RandomDouble> headPoseP = Optional.empty();
/*  37 */   private Optional<RandomDouble> headPoseY = Optional.empty();
/*  38 */   private Optional<RandomDouble> headPoseA = Optional.empty();
/*     */   
/*  40 */   private Optional<RandomDouble> bodyPoseP = Optional.empty();
/*  41 */   private Optional<RandomDouble> bodyPoseY = Optional.empty();
/*  42 */   private Optional<RandomDouble> bodyPoseA = Optional.empty();
/*     */   
/*  44 */   private Optional<RandomDouble> lArmPoseP = Optional.empty();
/*  45 */   private Optional<RandomDouble> lArmPoseY = Optional.empty();
/*  46 */   private Optional<RandomDouble> lArmPoseA = Optional.empty();
/*     */   
/*  48 */   private Optional<RandomDouble> rArmPoseP = Optional.empty();
/*  49 */   private Optional<RandomDouble> rArmPoseY = Optional.empty();
/*  50 */   private Optional<RandomDouble> rArmPoseA = Optional.empty();
/*     */   
/*  52 */   private Optional<RandomDouble> lLegPoseP = Optional.empty();
/*  53 */   private Optional<RandomDouble> lLegPoseY = Optional.empty();
/*  54 */   private Optional<RandomDouble> lLegPoseA = Optional.empty();
/*     */   
/*  56 */   private Optional<RandomDouble> rLegPoseP = Optional.empty();
/*  57 */   private Optional<RandomDouble> rLegPoseY = Optional.empty();
/*  58 */   private Optional<RandomDouble> rLegPoseA = Optional.empty();
/*     */ 
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  62 */     this.has_arms = mc.getBoolean("Options.HasArms", false);
/*  63 */     this.has_gravity = mc.getBoolean("Options.HasGravity", false);
/*  64 */     this.small = mc.getBoolean("Options.Small", false);
/*  65 */     this.invisible = mc.getBoolean("Options.Invisible", false);
/*  66 */     this.isMarker = mc.getBoolean("Options.Marker", false);
/*  67 */     this.item_head = mc.getString("Options.ItemHead", null);
/*  68 */     this.item_body = mc.getString("Options.ItemBody", null);
/*  69 */     this.item_legs = mc.getString("Options.ItemLegs", null);
/*  70 */     this.item_feet = mc.getString("Options.ItemFeet", null);
/*  71 */     this.item_hand = mc.getString("Options.ItemHand", null);
/*  72 */     this.item_offhand = mc.getString("Options.ItemOffhand", null);
/*     */     
/*  74 */     String headPose = mc.getString("Pose.Head", null);
/*     */     
/*  76 */     if (headPose != null) {
/*     */       try {
/*  78 */         String[] split = headPose.split(",");
/*  79 */         this.headPoseP = Optional.of(new RandomDouble(split[0]));
/*  80 */         this.headPoseY = Optional.of(new RandomDouble(split[1]));
/*  81 */         this.headPoseA = Optional.of(new RandomDouble(split[2]));
/*  82 */       } catch (Exception ex) {
/*  83 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/*  84 */         if (ConfigManager.debugLevel > 0) {
/*  85 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  90 */     String bodyPose = mc.getString("Pose.Body", null);
/*  91 */     if (bodyPose != null) {
/*     */       try {
/*  93 */         String[] split = bodyPose.split(",");
/*  94 */         this.bodyPoseP = Optional.of(new RandomDouble(split[0]));
/*  95 */         this.bodyPoseY = Optional.of(new RandomDouble(split[1]));
/*  96 */         this.bodyPoseA = Optional.of(new RandomDouble(split[2]));
/*  97 */       } catch (Exception ex) {
/*  98 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/*  99 */         if (ConfigManager.debugLevel > 0) {
/* 100 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 105 */     String lArmPose = mc.getString("Pose.LeftArm", null);
/* 106 */     if (lArmPose != null) {
/*     */       try {
/* 108 */         String[] split = lArmPose.split(",");
/* 109 */         this.lArmPoseP = Optional.of(new RandomDouble(split[0]));
/* 110 */         this.lArmPoseY = Optional.of(new RandomDouble(split[1]));
/* 111 */         this.lArmPoseA = Optional.of(new RandomDouble(split[2]));
/* 112 */       } catch (Exception ex) {
/* 113 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/* 114 */         if (ConfigManager.debugLevel > 0) {
/* 115 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 120 */     String rArmPose = mc.getString("Pose.RightArm", null);
/* 121 */     if (rArmPose != null) {
/*     */       try {
/* 123 */         String[] split = rArmPose.split(",");
/* 124 */         this.rArmPoseP = Optional.of(new RandomDouble(split[0]));
/* 125 */         this.rArmPoseY = Optional.of(new RandomDouble(split[1]));
/* 126 */         this.rArmPoseA = Optional.of(new RandomDouble(split[2]));
/* 127 */       } catch (Exception ex) {
/* 128 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/* 129 */         if (ConfigManager.debugLevel > 0) {
/* 130 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 135 */     String lLegPose = mc.getString("Pose.LeftLeg", null);
/* 136 */     if (lLegPose != null) {
/*     */       try {
/* 138 */         String[] split = lLegPose.split(",");
/* 139 */         this.lLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 140 */         this.lLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 141 */         this.lLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 142 */       } catch (Exception ex) {
/* 143 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/* 144 */         if (ConfigManager.debugLevel > 0) {
/* 145 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 150 */     String rLegPose = mc.getString("Pose.RightLeg", null);
/* 151 */     if (rLegPose != null) {
/*     */       try {
/* 153 */         String[] split = rLegPose.split(",");
/* 154 */         this.rLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 155 */         this.rLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 156 */         this.rLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 157 */       } catch (Exception ex) {
/* 158 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pose, must be in format pitch,yaw,angle");
/* 159 */         if (ConfigManager.debugLevel > 0) {
/* 160 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/* 168 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
/*     */     
/* 170 */     e = applyOptions(e);
/*     */     
/* 172 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/* 177 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
/*     */     
/* 179 */     e = applyOptions(e);
/*     */     
/* 181 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/* 186 */     ArmorStand e = (ArmorStand)entity;
/*     */     
/* 188 */     if (this.has_arms) {
/* 189 */       e.setArms(true);
/*     */     }
/* 191 */     if (this.has_gravity) {
/* 192 */       e.setGravity(true);
/*     */     } else {
/* 194 */       e.setGravity(false);
/*     */     } 
/* 196 */     if (this.invisible) {
/* 197 */       e.setVisible(false);
/*     */     }
/* 199 */     if (this.small) {
/* 200 */       e.setSmall(true);
/*     */     }
/* 202 */     if (this.isMarker) {
/* 203 */       e.setMarker(true);
/*     */     }
/*     */     
/* 206 */     if (this.item_head != null) {
/* 207 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_head);
/* 208 */       if (maybeItem.isPresent()) {
/* 209 */         MythicItem mi = maybeItem.get();
/* 210 */         e.setHelmet(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 214 */     if (this.item_body != null) {
/* 215 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_body);
/* 216 */       if (maybeItem.isPresent()) {
/* 217 */         MythicItem mi = maybeItem.get();
/* 218 */         e.setChestplate(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 222 */     if (this.item_legs != null) {
/* 223 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_legs);
/* 224 */       if (maybeItem.isPresent()) {
/* 225 */         MythicItem mi = maybeItem.get();
/* 226 */         e.setLeggings(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 230 */     if (this.item_feet != null) {
/* 231 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_feet);
/* 232 */       if (maybeItem.isPresent()) {
/* 233 */         MythicItem mi = maybeItem.get();
/* 234 */         e.setBoots(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     if (this.item_hand != null) {
/* 239 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_hand);
/* 240 */       if (maybeItem.isPresent()) {
/* 241 */         MythicItem mi = maybeItem.get();
/* 242 */         e.setItemInHand(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 246 */     if (this.item_offhand != null) {
/* 247 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.item_offhand);
/* 248 */       if (maybeItem.isPresent()) {
/* 249 */         MythicItem mi = maybeItem.get();
/* 250 */         e.getEquipment().setItemInOffHand(BukkitAdapter.adapt(mi.generateItemStack(1)));
/*     */       } 
/*     */     } 
/*     */     
/* 254 */     if (this.headPoseP.isPresent()) {
/* 255 */       double P = ((RandomDouble)this.headPoseP.get()).get() * Math.PI / 180.0D;
/* 256 */       double Y = ((RandomDouble)this.headPoseY.get()).get() * Math.PI / 180.0D;
/* 257 */       double A = ((RandomDouble)this.headPoseA.get()).get() * Math.PI / 180.0D;
/* 258 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 259 */       e.setHeadPose(a);
/*     */     } 
/*     */     
/* 262 */     if (this.bodyPoseP.isPresent()) {
/* 263 */       double P = ((RandomDouble)this.bodyPoseP.get()).get() * Math.PI / 180.0D;
/* 264 */       double Y = ((RandomDouble)this.bodyPoseY.get()).get() * Math.PI / 180.0D;
/* 265 */       double A = ((RandomDouble)this.bodyPoseA.get()).get() * Math.PI / 180.0D;
/* 266 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 267 */       e.setBodyPose(a);
/*     */     } 
/*     */     
/* 270 */     if (this.lArmPoseP.isPresent()) {
/* 271 */       double P = ((RandomDouble)this.lArmPoseP.get()).get() * Math.PI / 180.0D;
/* 272 */       double Y = ((RandomDouble)this.lArmPoseY.get()).get() * Math.PI / 180.0D;
/* 273 */       double A = ((RandomDouble)this.lArmPoseA.get()).get() * Math.PI / 180.0D;
/* 274 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 275 */       e.setLeftArmPose(a);
/*     */     } 
/*     */     
/* 278 */     if (this.rArmPoseP.isPresent()) {
/* 279 */       double P = ((RandomDouble)this.rArmPoseP.get()).get() * Math.PI / 180.0D;
/* 280 */       double Y = ((RandomDouble)this.rArmPoseY.get()).get() * Math.PI / 180.0D;
/* 281 */       double A = ((RandomDouble)this.rArmPoseA.get()).get() * Math.PI / 180.0D;
/* 282 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 283 */       e.setRightArmPose(a);
/*     */     } 
/*     */     
/* 286 */     if (this.lLegPoseP.isPresent()) {
/* 287 */       double P = ((RandomDouble)this.lLegPoseP.get()).get() * Math.PI / 180.0D;
/* 288 */       double Y = ((RandomDouble)this.lLegPoseY.get()).get() * Math.PI / 180.0D;
/* 289 */       double A = ((RandomDouble)this.lLegPoseA.get()).get() * Math.PI / 180.0D;
/* 290 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 291 */       e.setLeftLegPose(a);
/*     */     } 
/*     */     
/* 294 */     if (this.rLegPoseP.isPresent()) {
/* 295 */       double P = ((RandomDouble)this.rLegPoseP.get()).get() * Math.PI / 180.0D;
/* 296 */       double Y = ((RandomDouble)this.rLegPoseY.get()).get() * Math.PI / 180.0D;
/* 297 */       double A = ((RandomDouble)this.rLegPoseA.get()).get() * Math.PI / 180.0D;
/* 298 */       EulerAngle a = new EulerAngle(P, Y, A);
/* 299 */       e.setRightLegPose(a);
/*     */     } 
/* 301 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 306 */     return e instanceof ArmorStand;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 311 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */