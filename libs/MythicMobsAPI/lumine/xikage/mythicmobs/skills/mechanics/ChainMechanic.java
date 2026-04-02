/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "chain", version = "4.8", description = "Casts a metaskill that bounces between targets")
/*     */ public class ChainMechanic
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill
/*     */ {
/*  37 */   protected Skill onBounceSkill = null;
/*     */ 
/*     */   
/*     */   @MythicField(name = "onBounce", aliases = {"ob"}, version = "4.8", description = "The skill that bounces between targets")
/*     */   protected String onBounceSkillName;
/*     */   
/*     */   @MythicField(name = "bounces", aliases = {"b"}, defValue = "2", version = "4.8", description = "How many times the chain should bounce")
/*     */   protected PlaceholderInt bounces;
/*     */   
/*     */   @MythicField(name = "delay", aliases = {"d"}, defValue = "1", version = "4.8", description = "The delay between bounces")
/*     */   protected PlaceholderInt bounceDelay;
/*     */   
/*     */   @MythicField(name = "radius", aliases = {"r"}, defValue = "5", version = "4.8", description = "How far the skill will bounce to a new target")
/*     */   protected PlaceholderDouble bounceRadius;
/*     */   
/*     */   protected BounceSelector bounceSelector;
/*     */   
/*     */   @MythicField(name = "hitSelf", aliases = {"hs"}, defValue = "false", version = "4.8", description = "Whether the chain should affect the caster")
/*     */   protected boolean hitSelf = false;
/*     */   
/*     */   @MythicField(name = "hitTarget", aliases = {"ht"}, defValue = "true", version = "4.8", description = "Whether the chain should do the initial from the caster to the first target")
/*     */   protected boolean hitTarget = true;
/*     */   
/*     */   @MythicField(name = "hitPlayers", aliases = {"hp"}, defValue = "true", version = "4.8", description = "Whether the chain should bounce to players")
/*     */   protected boolean hitPlayers = true;
/*     */   
/*     */   @MythicField(name = "hitNonPlayers", aliases = {"hnp"}, defValue = "false", version = "4.8", description = "Whether the chain should bounce to non-players")
/*     */   protected boolean hitNonPlayers = false;
/*     */   
/*     */   protected String bounceConditionString;
/*     */   
/*     */   @MythicField(name = "bounceConditions", aliases = {"conditions"}, defValue = "NONE", version = "4.8", description = "Conditions applied to the bounce target")
/*  69 */   protected List<SkillCondition> bounceConditions = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChainMechanic(String skill, MythicLineConfig mlc) {
/*  79 */     super(skill, mlc);
/*     */     
/*  81 */     this.onBounceSkillName = mlc.getString(new String[] { "onbounceskill", "onbounce", "ob", "ontickskill", "ontick", "ot", "skill", "s", "meta", "m" });
/*     */     
/*  83 */     this.bounces = mlc.getPlaceholderInteger(new String[] { "bounces", "b" }, 2, new String[0]);
/*  84 */     this.bounceDelay = mlc.getPlaceholderInteger(new String[] { "bouncedelay", "bd", "delay", "interval", "i", "d" }, 1, new String[0]);
/*  85 */     this.bounceRadius = mlc.getPlaceholderDouble(new String[] { "bounceradius", "bouncerange", "radius", "range", "r" }, 5.0D, new String[0]);
/*     */     
/*  87 */     this.hitSelf = mlc.getBoolean(new String[] { "hitself", "hs" }, false);
/*  88 */     this.hitPlayers = mlc.getBoolean(new String[] { "hitplayers", "hp" }, true);
/*  89 */     this.hitNonPlayers = mlc.getBoolean(new String[] { "hitnonplayers", "hnp" }, false);
/*  90 */     this.hitTarget = mlc.getBoolean(new String[] { "hittarget", "ht" }, true);
/*     */     
/*  92 */     this.bounceConditionString = mlc.getString(new String[] { "bounceconditions", "conditions", "cond", "c" }, "null", new String[0]);
/*     */     
/*  94 */     getPlugin().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onBounceSkillName != null) {
/*     */             Optional<Skill> maybeSkill = getPlugin().getSkillManager().getSkill(this.onBounceSkillName);
/*     */             if (!maybeSkill.isPresent()) {
/*     */               MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "onBounce skill is required and was not found");
/*     */               return;
/*     */             } 
/*     */             this.onBounceSkill = maybeSkill.get();
/*     */             if (this.bounceConditionString != null) {
/*     */               this.bounceConditions = getPlugin().getSkillManager().getConditions(this.bounceConditionString);
/*     */               if (this.bounceConditions == null) {
/*     */                 MythicLogger.log("Failed bounce conditions");
/*     */               } else {
/*     */                 MythicLogger.log("Loaded " + this.bounceConditions.size() + " bounce conditions");
/*     */               } 
/*     */             } else {
/*     */               MythicLogger.log("Loaded no bounce conditions");
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 121 */     new ChainTracker(this, data, target);
/* 122 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ChainMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */