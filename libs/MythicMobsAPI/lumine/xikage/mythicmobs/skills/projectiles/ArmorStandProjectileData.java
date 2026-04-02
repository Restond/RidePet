/*     */ package lumine.xikage.mythicmobs.skills.projectiles;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArmorStandProjectileData
/*     */ {
/*     */   private boolean isMarker = false;
/*     */   private boolean has_arms = true;
/*     */   private boolean has_gravity = true;
/*     */   private boolean invisible = false;
/*     */   private boolean small = false;
/*  18 */   private String item_head = null;
/*  19 */   private String item_body = null;
/*  20 */   private String item_legs = null;
/*  21 */   private String item_feet = null;
/*  22 */   private String item_hand = null;
/*  23 */   private String item_offhand = null;
/*     */   
/*  25 */   private Optional<RandomDouble> headPoseP = Optional.empty();
/*  26 */   private Optional<RandomDouble> headPoseY = Optional.empty();
/*  27 */   private Optional<RandomDouble> headPoseA = Optional.empty();
/*     */   
/*  29 */   private Optional<RandomDouble> bodyPoseP = Optional.empty();
/*  30 */   private Optional<RandomDouble> bodyPoseY = Optional.empty();
/*  31 */   private Optional<RandomDouble> bodyPoseA = Optional.empty();
/*     */   
/*  33 */   private Optional<RandomDouble> lArmPoseP = Optional.empty();
/*  34 */   private Optional<RandomDouble> lArmPoseY = Optional.empty();
/*  35 */   private Optional<RandomDouble> lArmPoseA = Optional.empty();
/*     */   
/*  37 */   private Optional<RandomDouble> rArmPoseP = Optional.empty();
/*  38 */   private Optional<RandomDouble> rArmPoseY = Optional.empty();
/*  39 */   private Optional<RandomDouble> rArmPoseA = Optional.empty();
/*     */   
/*  41 */   private Optional<RandomDouble> lLegPoseP = Optional.empty();
/*  42 */   private Optional<RandomDouble> lLegPoseY = Optional.empty();
/*  43 */   private Optional<RandomDouble> lLegPoseA = Optional.empty();
/*     */   
/*  45 */   private Optional<RandomDouble> rLegPoseP = Optional.empty();
/*  46 */   private Optional<RandomDouble> rLegPoseY = Optional.empty();
/*  47 */   private Optional<RandomDouble> rLegPoseA = Optional.empty();
/*     */   
/*     */   public ArmorStandProjectileData(MythicLineConfig mc) {
/*  50 */     this.has_arms = mc.getBoolean("hasarms", false);
/*  51 */     this.small = mc.getBoolean("small", false);
/*  52 */     this.isMarker = mc.getBoolean("marker", false);
/*  53 */     this.item_head = mc.getString("head", null);
/*  54 */     this.item_body = mc.getString("body", null);
/*  55 */     this.item_legs = mc.getString("legs", null);
/*  56 */     this.item_feet = mc.getString("feet", null);
/*  57 */     this.item_hand = mc.getString("hand", null);
/*  58 */     this.item_offhand = mc.getString("offhand", null);
/*     */     
/*  60 */     String headPose = mc.getString("headpose", null);
/*     */     
/*  62 */     if (headPose != null) {
/*     */       try {
/*  64 */         String[] split = headPose.split(",");
/*  65 */         this.headPoseP = Optional.of(new RandomDouble(split[0]));
/*  66 */         this.headPoseY = Optional.of(new RandomDouble(split[1]));
/*  67 */         this.headPoseA = Optional.of(new RandomDouble(split[2]));
/*  68 */       } catch (Exception ex) {
/*  69 */         if (ConfigManager.debugLevel > 0) {
/*  70 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  75 */     String bodyPose = mc.getString("bodypose", null);
/*  76 */     if (bodyPose != null) {
/*     */       try {
/*  78 */         String[] split = bodyPose.split(",");
/*  79 */         this.bodyPoseP = Optional.of(new RandomDouble(split[0]));
/*  80 */         this.bodyPoseY = Optional.of(new RandomDouble(split[1]));
/*  81 */         this.bodyPoseA = Optional.of(new RandomDouble(split[2]));
/*  82 */       } catch (Exception ex) {
/*     */         
/*  84 */         if (ConfigManager.debugLevel > 0) {
/*  85 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  90 */     String lArmPose = mc.getString("leftarmpose", null);
/*  91 */     if (lArmPose != null) {
/*     */       try {
/*  93 */         String[] split = lArmPose.split(",");
/*  94 */         this.lArmPoseP = Optional.of(new RandomDouble(split[0]));
/*  95 */         this.lArmPoseY = Optional.of(new RandomDouble(split[1]));
/*  96 */         this.lArmPoseA = Optional.of(new RandomDouble(split[2]));
/*  97 */       } catch (Exception ex) {
/*     */         
/*  99 */         if (ConfigManager.debugLevel > 0) {
/* 100 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 105 */     String rArmPose = mc.getString("rightarmpose", null);
/* 106 */     if (rArmPose != null) {
/*     */       try {
/* 108 */         String[] split = rArmPose.split(",");
/* 109 */         this.rArmPoseP = Optional.of(new RandomDouble(split[0]));
/* 110 */         this.rArmPoseY = Optional.of(new RandomDouble(split[1]));
/* 111 */         this.rArmPoseA = Optional.of(new RandomDouble(split[2]));
/* 112 */       } catch (Exception ex) {
/*     */         
/* 114 */         if (ConfigManager.debugLevel > 0) {
/* 115 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 120 */     String lLegPose = mc.getString("leftlegpose", null);
/* 121 */     if (lLegPose != null) {
/*     */       try {
/* 123 */         String[] split = lLegPose.split(",");
/* 124 */         this.lLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 125 */         this.lLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 126 */         this.lLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 127 */       } catch (Exception ex) {
/*     */         
/* 129 */         if (ConfigManager.debugLevel > 0) {
/* 130 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 135 */     String rLegPose = mc.getString("rightlegpose", null);
/* 136 */     if (rLegPose != null)
/*     */       try {
/* 138 */         String[] split = rLegPose.split(",");
/* 139 */         this.rLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 140 */         this.rLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 141 */         this.rLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 142 */       } catch (Exception ex) {
/*     */         
/* 144 */         if (ConfigManager.debugLevel > 0)
/* 145 */           ex.printStackTrace(); 
/*     */       }  
/*     */   }
/*     */   
/*     */   public void spawn(AbstractLocation location) {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\projectiles\ArmorStandProjectileData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */