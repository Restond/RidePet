/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
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
/*     */ @MythicMechanic(author = "Ashijin", name = "totem", aliases = {"toteme", "t"}, description = "Creates a static totem projectile at the target")
/*     */ public class TotemMechanic
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill
/*     */ {
/*  29 */   protected Optional<Skill> onTickSkill = Optional.empty();
/*  30 */   protected Optional<Skill> onHitSkill = Optional.empty();
/*  31 */   protected Optional<Skill> onEndSkill = Optional.empty();
/*  32 */   protected Optional<Skill> onStartSkill = Optional.empty();
/*     */   
/*     */   protected String onTickSkillName;
/*     */   protected String onHitSkillName;
/*     */   protected String onEndSkillName;
/*     */   protected String onStartSkillName;
/*     */   protected int tickInterval;
/*     */   protected float ticksPerSecond;
/*     */   protected int maxCharges;
/*     */   protected float hitRadius;
/*     */   protected float verticalHitRadius;
/*     */   protected float duration;
/*     */   protected float YOffset;
/*     */   protected boolean hitTarget = true;
/*     */   protected boolean hitPlayers = false;
/*     */   protected boolean hitNonPlayers = false;
/*     */   protected boolean hitTargetOnly = false;
/*     */   
/*     */   public TotemMechanic(String skill, MythicLineConfig mlc) {
/*  51 */     super(skill, mlc);
/*     */     
/*  53 */     this.onTickSkillName = mlc.getString(new String[] { "ontickskill", "ontick", "ot", "skill", "s", "meta", "m" });
/*  54 */     this.onHitSkillName = mlc.getString(new String[] { "onhitskill", "onhit", "oh" });
/*  55 */     this.onEndSkillName = mlc.getString(new String[] { "onendskill", "onend", "oe" });
/*  56 */     this.onStartSkillName = mlc.getString(new String[] { "onstartskill", "onstart", "os" });
/*     */     
/*  58 */     this.tickInterval = mlc.getInteger(new String[] { "interval", "int", "i" }, 4);
/*  59 */     this.ticksPerSecond = 20.0F / this.tickInterval;
/*     */     
/*  61 */     this.maxCharges = mlc.getInteger(new String[] { "charges", "ch", "c" }, 0);
/*     */     
/*  63 */     this.hitRadius = mlc.getFloat("horizontalradius", 1.25F);
/*  64 */     this.hitRadius = mlc.getFloat("hradius", this.hitRadius);
/*  65 */     this.hitRadius = mlc.getFloat("hr", this.hitRadius);
/*  66 */     this.hitRadius = mlc.getFloat("r", this.hitRadius);
/*     */     
/*  68 */     this.duration = mlc.getFloat("maxduration", 10.0F);
/*  69 */     this.duration = mlc.getFloat("md", this.duration);
/*  70 */     this.duration *= 50.0F;
/*     */     
/*  72 */     this.verticalHitRadius = mlc.getFloat("verticalradius", this.hitRadius);
/*  73 */     this.verticalHitRadius = mlc.getFloat("vradius", this.verticalHitRadius);
/*  74 */     this.verticalHitRadius = mlc.getFloat("vr", this.verticalHitRadius);
/*     */     
/*  76 */     this.YOffset = mlc.getFloat("yoffset", 1.0F);
/*  77 */     this.YOffset = mlc.getFloat("yo", this.YOffset);
/*     */     
/*  79 */     this.hitPlayers = mlc.getBoolean("hitplayers", false);
/*  80 */     this.hitPlayers = mlc.getBoolean("hp", this.hitPlayers);
/*     */     
/*  82 */     this.hitNonPlayers = mlc.getBoolean("hitnonplayers", false);
/*  83 */     this.hitNonPlayers = mlc.getBoolean("hnp", this.hitNonPlayers);
/*     */     
/*  85 */     this.hitTarget = mlc.getBoolean("hittarget", true);
/*  86 */     this.hitTarget = mlc.getBoolean("ht", this.hitTarget);
/*     */     
/*  88 */     this.hitTargetOnly = mlc.getBoolean("hittargetonly", false);
/*  89 */     this.hitTargetOnly = mlc.getBoolean("hittargetonly", this.hitTargetOnly);
/*     */     
/*  91 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onTickSkillName != null)
/*     */             this.onTickSkill = MythicMobs.inst().getSkillManager().getSkill(this.onTickSkillName); 
/*     */           MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onTickSkillName);
/*     */           if (this.onHitSkillName != null)
/*     */             this.onHitSkill = MythicMobs.inst().getSkillManager().getSkill(this.onHitSkillName); 
/*     */           MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onHitSkillName);
/*     */           if (this.onEndSkillName != null)
/*     */             this.onEndSkill = MythicMobs.inst().getSkillManager().getSkill(this.onEndSkillName); 
/*     */           MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onEndSkillName);
/*     */           if (this.onStartSkillName != null)
/*     */             this.onStartSkill = MythicMobs.inst().getSkillManager().getSkill(this.onStartSkillName); 
/*     */           MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onStartSkillName);
/*     */         }); } public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*     */     try {
/* 106 */       new TotemTracker(this, data, target);
/* 107 */       return true;
/* 108 */     } catch (Exception ex) {
/* 109 */       MythicMobs.inst().handleException(ex);
/* 110 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\TotemMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */