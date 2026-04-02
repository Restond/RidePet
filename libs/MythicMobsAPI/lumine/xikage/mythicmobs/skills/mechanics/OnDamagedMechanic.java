/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "ondamaged", description = "Applies an aura to the target that triggers a skill when they take damage")
/*     */ public class OnDamagedMechanic
/*     */   extends Aura
/*     */   implements ITargetedEntitySkill
/*     */ {
/*  35 */   protected Optional<Skill> onDamagedSkill = Optional.empty();
/*     */   
/*     */   protected String onAttackSkillName;
/*     */   
/*     */   @MythicField(name = "cancelEvent", aliases = {"ce"}, defValue = "false", version = "4.6", description = "Whether or not to cancel the event that triggered the aura")
/*     */   protected boolean cancelDamage;
/*     */   
/*     */   protected boolean modDamage = false;
/*     */   protected double damageSub;
/*     */   protected double damageMult;
/*  45 */   private Map<String, Double> damageModifiers = new HashMap<>();
/*     */   
/*     */   public OnDamagedMechanic(String skill, MythicLineConfig mlc) {
/*  48 */     super(skill, mlc);
/*     */     
/*  50 */     this.onAttackSkillName = mlc.getString(new String[] { "ondamagedskill", "ondamaged", "od", "onhitskill", "onhit", "oh" });
/*     */     
/*  52 */     this.cancelDamage = mlc.getBoolean(new String[] { "cancelevent", "ce", "canceldamage", "cd" }, false);
/*  53 */     this.damageSub = mlc.getDouble(new String[] { "damagesub", "sub", "s" }, 0.0D);
/*  54 */     this.damageMult = mlc.getDouble(new String[] { "damagemultiplier", "multiplier", "m" }, 1.0D);
/*     */     
/*  56 */     PlaceholderString strDamageMod = mlc.getPlaceholderString(new String[] { "damagemodifiers", "damagemods", "damagemod" }, null, new String[0]);
/*     */     
/*  58 */     if (strDamageMod != null) {
/*  59 */       String[] lstDamageMod = strDamageMod.toString().split(",");
/*  60 */       for (String dm : lstDamageMod) {
/*     */         try {
/*  62 */           String[] split = dm.split(" ");
/*  63 */           String type = split[0];
/*  64 */           double mod = Double.valueOf(split[1]).doubleValue();
/*     */           
/*  66 */           this.damageModifiers.put(type, Double.valueOf(mod));
/*  67 */         } catch (Exception ex) {
/*  68 */           MythicLogger.errorMechanicConfig((SkillMechanic)this, mlc, "Invalid syntax for DamageModifier");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     if (this.damageSub != 0.0D || this.damageMult != 1.0D || this.damageModifiers.size() > 0) {
/*  74 */       this.modDamage = true;
/*     */     }
/*     */     
/*  77 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onAttackSkillName != null)
/*     */             this.onDamagedSkill = MythicMobs.inst().getSkillManager().getSkill(this.onAttackSkillName); 
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  84 */     new Tracker(this, data, target);
/*  85 */     return true;
/*     */   }
/*     */   protected double calculateDamage(AbstractEntity entity, EntityDamageByEntityEvent event) {
/*     */     String damageType;
/*  89 */     if (this.cancelDamage == true) {
/*  90 */       return 0.0D;
/*     */     }
/*  92 */     double damage = event.getFinalDamage();
/*     */     
/*  94 */     Optional<Object> maybeData = entity.getMetadata("skill-damage");
/*  95 */     if (maybeData.isPresent()) {
/*  96 */       DamageMetadata data = (DamageMetadata)maybeData.get();
/*  97 */       damageType = (data.getElement() == null) ? "SKILL" : data.getElement();
/*     */     } else {
/*     */       
/* 100 */       damageType = event.getCause().toString();
/*     */     } 
/* 102 */     double mod = ((Double)this.damageModifiers.getOrDefault(damageType.toUpperCase(), Double.valueOf(1.0D))).doubleValue();
/* 103 */     return (damage - this.damageSub) * this.damageMult * mod;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnDamagedMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */