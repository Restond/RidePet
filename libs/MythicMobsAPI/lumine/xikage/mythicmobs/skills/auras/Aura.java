/*     */ package lumine.xikage.mythicmobs.skills.auras;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Aura
/*     */   extends SkillMechanic
/*     */ {
/*  34 */   protected Optional<String> auraName = Optional.empty();
/*     */   
/*     */   protected int charges;
/*     */   
/*     */   protected int interval;
/*     */   protected int duration;
/*     */   protected int maxStacks;
/*     */   protected boolean mergeAll;
/*     */   protected boolean mergeSameCaster;
/*     */   protected boolean overwriteAll;
/*     */   protected boolean overwriteCaster;
/*     */   protected boolean refreshDuration;
/*  46 */   protected transient AbstractBossBar barTimer = null;
/*     */   protected boolean showBarTimer;
/*  48 */   protected PlaceholderString barTimerDisplay = null;
/*  49 */   protected AbstractBossBar.BarColor barTimerColor = null;
/*  50 */   protected AbstractBossBar.BarStyle barTimerStyle = null;
/*     */   
/*     */   protected boolean cancelOnGiveDamage;
/*     */   
/*     */   protected boolean cancelOnTakeDamage;
/*     */   
/*     */   protected boolean cancelOnDeath;
/*     */   protected boolean cancelOnTeleport;
/*     */   protected boolean cancelOnChangeWorld;
/*     */   protected boolean cancelOnSkillCast;
/*     */   protected boolean cancelOnQuit;
/*     */   protected boolean doEndSkillOnTerminate;
/*  62 */   protected Optional<Skill> onStartSkill = Optional.empty();
/*  63 */   protected Optional<Skill> onTickSkill = Optional.empty();
/*  64 */   protected Optional<Skill> onEndSkill = Optional.empty();
/*     */   protected String onStartSkillName;
/*     */   
/*     */   public Aura(String line, MythicLineConfig mlc) {
/*  68 */     super(line, mlc);
/*     */     
/*  70 */     String auraName = mlc.getString(new String[] { "auraname", "buffname", "debuffname" }, null, new String[0]);
/*     */     
/*  72 */     if (auraName != null) {
/*  73 */       this.auraName = Optional.of(auraName);
/*     */     }
/*     */     
/*  76 */     this.charges = mlc.getInteger(new String[] { "charges", "c" }, 0);
/*  77 */     this.interval = mlc.getInteger(new String[] { "interval", "i" }, 1);
/*  78 */     this.duration = mlc.getInteger(new String[] { "ticks", "t", "duration", "d", "time", "t" }, 200);
/*  79 */     this.maxStacks = mlc.getInteger(new String[] { "maxstacks", "ms" }, 1);
/*     */     
/*  81 */     this.mergeAll = mlc.getBoolean(new String[] { "mergeall", "ma" }, false);
/*  82 */     this.overwriteAll = mlc.getBoolean(new String[] { "overwriteall", "overwrite", "ow" }, false);
/*  83 */     this.overwriteCaster = mlc.getBoolean(new String[] { "overwritesamecaster", "osc", "oc" }, false);
/*  84 */     this.mergeSameCaster = mlc.getBoolean(new String[] { "mergesamecaster", "msc", "mc" }, (!this.mergeAll && !this.overwriteAll && !this.overwriteCaster));
/*  85 */     this.refreshDuration = mlc.getBoolean(new String[] { "refreshduration", "rd" }, true);
/*     */     
/*  87 */     this.showBarTimer = mlc.getBoolean(new String[] { "showbartimer", "bartimer", "bt" }, false);
/*  88 */     if (this.showBarTimer) {
/*  89 */       this.barTimerDisplay = mlc.getPlaceholderString(new String[] { "bartimerdisplay", "bartimertext" }, "<skill.var.aura-name>", new String[0]);
/*  90 */       String barTimerColor = mlc.getString("bartimercolor", "RED");
/*  91 */       String barTimerStyle = mlc.getString("bartimerstyle", "SOLID");
/*     */       
/*     */       try {
/*  94 */         this.barTimerColor = AbstractBossBar.BarColor.valueOf(barTimerColor);
/*  95 */       } catch (Exception ex) {
/*  96 */         this.barTimerColor = AbstractBossBar.BarColor.RED;
/*     */       } 
/*     */       
/*     */       try {
/* 100 */         this.barTimerStyle = AbstractBossBar.BarStyle.valueOf(barTimerStyle);
/* 101 */       } catch (Exception ex) {
/* 102 */         this.barTimerStyle = AbstractBossBar.BarStyle.SOLID;
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     this.cancelOnGiveDamage = mlc.getBoolean(new String[] { "cancelongivedamage", "cogd" }, false);
/* 107 */     this.cancelOnTakeDamage = mlc.getBoolean(new String[] { "cancelontakedamage", "cotd" }, false);
/* 108 */     this.cancelOnDeath = mlc.getBoolean(new String[] { "cancelondeath", "cod" }, true);
/* 109 */     this.cancelOnTeleport = mlc.getBoolean(new String[] { "cancelonteleport", "cot" }, false);
/* 110 */     this.cancelOnChangeWorld = mlc.getBoolean(new String[] { "cancelonchangeworld", "cocw" }, false);
/* 111 */     this.cancelOnSkillCast = mlc.getBoolean(new String[] { "cancelonskilluse", "cosu" }, false);
/* 112 */     this.cancelOnQuit = mlc.getBoolean(new String[] { "cancelonquit", "coq" }, true);
/*     */     
/* 114 */     this.doEndSkillOnTerminate = mlc.getBoolean(new String[] { "doendskillonterminate", "desot", "alwaysrunendskill", "ares" }, true);
/*     */     
/* 116 */     this.onStartSkillName = mlc.getString(new String[] { "onstartskill", "onstart", "os" });
/* 117 */     this.onTickSkillName = mlc.getString(new String[] { "ontickskill", "ontick", "ot" });
/* 118 */     this.onEndSkillName = mlc.getString(new String[] { "onendskill", "onend", "oe" });
/*     */     
/* 120 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onStartSkillName != null)
/*     */             this.onStartSkill = MythicMobs.inst().getSkillManager().getSkill(this.onStartSkillName); 
/*     */           if (this.onTickSkillName != null)
/*     */             this.onTickSkill = MythicMobs.inst().getSkillManager().getSkill(this.onTickSkillName); 
/*     */           if (this.onEndSkillName != null)
/*     */             this.onEndSkill = MythicMobs.inst().getSkillManager().getSkill(this.onEndSkillName); 
/*     */         });
/*     */   }
/*     */   
/*     */   protected String onTickSkillName;
/*     */   protected String onEndSkillName;
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\auras\Aura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */