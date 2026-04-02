/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "posearmorstand", aliases = {"armorstandpose"}, description = "Poses the target armor stand")
/*     */ public class PoseArmorStandMechanic
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill
/*     */ {
/*  22 */   private Optional<RandomDouble> headPoseP = Optional.empty();
/*  23 */   private Optional<RandomDouble> headPoseY = Optional.empty();
/*  24 */   private Optional<RandomDouble> headPoseA = Optional.empty();
/*     */   
/*  26 */   private Optional<RandomDouble> bodyPoseP = Optional.empty();
/*  27 */   private Optional<RandomDouble> bodyPoseY = Optional.empty();
/*  28 */   private Optional<RandomDouble> bodyPoseA = Optional.empty();
/*     */   
/*  30 */   private Optional<RandomDouble> lArmPoseP = Optional.empty();
/*  31 */   private Optional<RandomDouble> lArmPoseY = Optional.empty();
/*  32 */   private Optional<RandomDouble> lArmPoseA = Optional.empty();
/*     */   
/*  34 */   private Optional<RandomDouble> rArmPoseP = Optional.empty();
/*  35 */   private Optional<RandomDouble> rArmPoseY = Optional.empty();
/*  36 */   private Optional<RandomDouble> rArmPoseA = Optional.empty();
/*     */   
/*  38 */   private Optional<RandomDouble> lLegPoseP = Optional.empty();
/*  39 */   private Optional<RandomDouble> lLegPoseY = Optional.empty();
/*  40 */   private Optional<RandomDouble> lLegPoseA = Optional.empty();
/*     */   
/*  42 */   private Optional<RandomDouble> rLegPoseP = Optional.empty();
/*  43 */   private Optional<RandomDouble> rLegPoseY = Optional.empty();
/*  44 */   private Optional<RandomDouble> rLegPoseA = Optional.empty();
/*     */   
/*     */   public PoseArmorStandMechanic(String skill, MythicLineConfig mlc) {
/*  47 */     super(skill, mlc);
/*     */     
/*  49 */     String headPose = mlc.getString(new String[] { "head", "h" }, null, new String[0]);
/*  50 */     if (headPose != null) {
/*     */       try {
/*  52 */         String[] split = headPose.split(",");
/*  53 */         this.headPoseP = Optional.of(new RandomDouble(split[0]));
/*  54 */         this.headPoseY = Optional.of(new RandomDouble(split[1]));
/*  55 */         this.headPoseA = Optional.of(new RandomDouble(split[2]));
/*  56 */       } catch (Exception ex) {
/*  57 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/*  58 */         if (ConfigManager.debugLevel > 0) {
/*  59 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  64 */     String bodyPose = mlc.getString(new String[] { "body", "b" }, null, new String[0]);
/*  65 */     if (bodyPose != null) {
/*     */       try {
/*  67 */         String[] split = bodyPose.split(",");
/*  68 */         this.bodyPoseP = Optional.of(new RandomDouble(split[0]));
/*  69 */         this.bodyPoseY = Optional.of(new RandomDouble(split[1]));
/*  70 */         this.bodyPoseA = Optional.of(new RandomDouble(split[2]));
/*  71 */       } catch (Exception ex) {
/*  72 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/*  73 */         if (ConfigManager.debugLevel > 0) {
/*  74 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  79 */     String lArmPose = mlc.getString(new String[] { "leftarm", "la" }, null, new String[0]);
/*  80 */     if (lArmPose != null) {
/*     */       try {
/*  82 */         String[] split = lArmPose.split(",");
/*  83 */         this.lArmPoseP = Optional.of(new RandomDouble(split[0]));
/*  84 */         this.lArmPoseY = Optional.of(new RandomDouble(split[1]));
/*  85 */         this.lArmPoseA = Optional.of(new RandomDouble(split[2]));
/*  86 */       } catch (Exception ex) {
/*  87 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/*  88 */         if (ConfigManager.debugLevel > 0) {
/*  89 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  94 */     String rArmPose = mlc.getString(new String[] { "rightarm", "ra" }, null, new String[0]);
/*  95 */     if (rArmPose != null) {
/*     */       try {
/*  97 */         String[] split = rArmPose.split(",");
/*  98 */         this.rArmPoseP = Optional.of(new RandomDouble(split[0]));
/*  99 */         this.rArmPoseY = Optional.of(new RandomDouble(split[1]));
/* 100 */         this.rArmPoseA = Optional.of(new RandomDouble(split[2]));
/* 101 */       } catch (Exception ex) {
/* 102 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/* 103 */         if (ConfigManager.debugLevel > 0) {
/* 104 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 109 */     String lLegPose = mlc.getString(new String[] { "leftleg", "ll" }, null, new String[0]);
/* 110 */     if (lLegPose != null) {
/*     */       try {
/* 112 */         String[] split = lLegPose.split(",");
/* 113 */         this.lLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 114 */         this.lLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 115 */         this.lLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 116 */       } catch (Exception ex) {
/* 117 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/* 118 */         if (ConfigManager.debugLevel > 0) {
/* 119 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 124 */     String rLegPose = mlc.getString(new String[] { "rightleg", "rl" }, null, new String[0]);
/* 125 */     if (rLegPose != null) {
/*     */       try {
/* 127 */         String[] split = rLegPose.split(",");
/* 128 */         this.rLegPoseP = Optional.of(new RandomDouble(split[0]));
/* 129 */         this.rLegPoseY = Optional.of(new RandomDouble(split[1]));
/* 130 */         this.rLegPoseA = Optional.of(new RandomDouble(split[2]));
/* 131 */       } catch (Exception ex) {
/* 132 */         MythicMobs.error("ArmorStandPose Mechanic is configured incorrectly: must be in format: pitch,yaw,angle");
/* 133 */         if (ConfigManager.debugLevel > 0) {
/* 134 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 142 */     Entity entity = target.getBukkitEntity();
/*     */     
/* 144 */     if (!(entity instanceof ArmorStand)) return false;
/*     */     
/* 146 */     ArmorStand e = (ArmorStand)entity;
/*     */     
/* 148 */     if (this.headPoseP.isPresent()) {
/* 149 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.headPoseP.get()).get(), (float)((RandomDouble)this.headPoseY.get()).get(), (float)((RandomDouble)this.headPoseA.get()).get());
/* 150 */       e.setHeadPose(a);
/*     */     } 
/*     */     
/* 153 */     if (this.bodyPoseP.isPresent()) {
/* 154 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.bodyPoseP.get()).get(), (float)((RandomDouble)this.bodyPoseY.get()).get(), (float)((RandomDouble)this.bodyPoseA.get()).get());
/* 155 */       e.setBodyPose(a);
/*     */     } 
/*     */     
/* 158 */     if (this.lArmPoseP.isPresent()) {
/* 159 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.lArmPoseP.get()).get(), (float)((RandomDouble)this.lArmPoseY.get()).get(), (float)((RandomDouble)this.lArmPoseA.get()).get());
/* 160 */       e.setLeftArmPose(a);
/*     */     } 
/*     */     
/* 163 */     if (this.rArmPoseP.isPresent()) {
/* 164 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.rArmPoseP.get()).get(), (float)((RandomDouble)this.rArmPoseY.get()).get(), (float)((RandomDouble)this.rArmPoseA.get()).get());
/* 165 */       e.setRightArmPose(a);
/*     */     } 
/*     */     
/* 168 */     if (this.lLegPoseP.isPresent()) {
/* 169 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.lLegPoseP.get()).get(), (float)((RandomDouble)this.lLegPoseY.get()).get(), (float)((RandomDouble)this.lLegPoseA.get()).get());
/* 170 */       e.setLeftLegPose(a);
/*     */     } 
/*     */     
/* 173 */     if (this.rLegPoseP.isPresent()) {
/* 174 */       EulerAngle a = new EulerAngle((float)((RandomDouble)this.rLegPoseP.get()).get(), (float)((RandomDouble)this.rLegPoseY.get()).get(), (float)((RandomDouble)this.rLegPoseA.get()).get());
/* 175 */       e.setRightLegPose(a);
/*     */     } 
/*     */     
/* 178 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PoseArmorStandMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */