/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ import de.slikey.effectlib.EffectLib;
/*     */ import de.slikey.effectlib.EffectManager;
/*     */ import de.slikey.effectlib.effect.AtomEffect;
/*     */ import de.slikey.effectlib.effect.BleedEffect;
/*     */ import de.slikey.effectlib.effect.DnaEffect;
/*     */ import de.slikey.effectlib.effect.VortexEffect;
/*     */ import de.slikey.effectlib.util.ParticleEffect;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class EffectLibSupport {
/*     */   public static EffectLib effectlib;
/*     */   
/*     */   public EffectLibSupport() {
/*  19 */     effectlib = (EffectLib)Bukkit.getPluginManager().getPlugin("EffectLib");
/*  20 */     this.effectManager = new EffectManager((Plugin)effectlib);
/*     */   }
/*     */   private EffectManager effectManager;
/*     */   public void doAtomEffect(Entity t, ParticleEffect pN, int psN, ParticleEffect pO, int psO, int nO, int r, float rN, double velocity, int rotation, int period, int iterations) {
/*  24 */     AtomEffect e = new AtomEffect(this.effectManager);
/*     */     
/*  26 */     e.setTargetEntity(t);
/*  27 */     e.setEntity(t);
/*  28 */     e.particleNucleus = pN;
/*  29 */     e.particleOrbital = pO;
/*  30 */     e.particlesNucleus = psN;
/*  31 */     e.particlesOrbital = psO;
/*  32 */     e.radiusNucleus = rN;
/*  33 */     e.period = period;
/*  34 */     e.radius = r;
/*  35 */     e.iterations = iterations;
/*  36 */     e.orbitals = nO;
/*  37 */     e.rotation = rotation;
/*     */     
/*  39 */     e.start();
/*     */   }
/*     */   
/*     */   public void doAtomEffect(Location t, ParticleEffect pN, int psN, ParticleEffect pO, int psO, int nO, int r, float rN, double velocity, int rotation, int period, int iterations) {
/*  43 */     AtomEffect e = new AtomEffect(this.effectManager);
/*     */     
/*  45 */     e.setTargetLocation(t);
/*  46 */     e.setLocation(t);
/*  47 */     e.particleNucleus = pN;
/*  48 */     e.particleOrbital = pO;
/*  49 */     e.particlesNucleus = psN;
/*  50 */     e.particlesOrbital = psO;
/*  51 */     e.radiusNucleus = rN;
/*  52 */     e.period = period;
/*  53 */     e.radius = r;
/*  54 */     e.iterations = iterations;
/*  55 */     e.orbitals = nO;
/*  56 */     e.rotation = rotation;
/*     */     
/*  58 */     e.start();
/*     */   }
/*     */   
/*     */   public void doBleedEffect(LivingEntity l, int iterations, int period) {
/*  62 */     BleedEffect e = new BleedEffect(this.effectManager);
/*     */     
/*  64 */     e.setTargetEntity((Entity)l);
/*  65 */     e.period = period;
/*  66 */     e.iterations = iterations;
/*  67 */     e.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doDNAEffect(Location l, Location t, ParticleEffect pH, int psH, ParticleEffect pB1, ParticleEffect pB2, int psB, double radials, float r, float length, float growth, float baseInterval, int interval, int ticks) {
/*  76 */     DnaEffect e = new DnaEffect(this.effectManager);
/*     */     
/*  78 */     e.setLocation(l);
/*  79 */     e.setTargetLocation(t);
/*  80 */     e.particleHelix = pH;
/*  81 */     e.particlesHelix = psH;
/*  82 */     e.particleBase1 = pB1;
/*  83 */     e.particleBase1 = pB2;
/*  84 */     e.particlesBase = psB;
/*  85 */     e.radials = radials;
/*  86 */     e.radius = r;
/*  87 */     e.length = length;
/*  88 */     e.grow = growth;
/*  89 */     e.baseInterval = baseInterval;
/*  90 */     e.period = interval;
/*  91 */     e.iterations = ticks;
/*     */     
/*  93 */     e.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doVortexLocationEffect(Location l, String p, int h, int hC, double radials, float r, float growth, int interval, int ticks) {
/* 101 */     VortexEffect e = new VortexEffect(this.effectManager);
/*     */     
/* 103 */     e.setLocation(l);
/* 104 */     e.particle = ParticleEffect.valueOf(p);
/* 105 */     e.helixes = h;
/* 106 */     e.circles = hC;
/* 107 */     e.radials = radials;
/* 108 */     e.radius = r;
/* 109 */     e.grow = growth;
/* 110 */     e.period = interval;
/* 111 */     e.iterations = ticks;
/*     */     
/* 113 */     e.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticleEffect getParticleEffect(String s) {
/* 118 */     switch (s.toUpperCase()) {
/*     */       case "EXPLODE":
/* 120 */         return ParticleEffect.EXPLOSION_NORMAL;
/*     */       case "LARGEEXPLODE":
/* 122 */         return ParticleEffect.EXPLOSION_LARGE;
/*     */       case "HUGEEXPLOSION":
/* 124 */         return ParticleEffect.EXPLOSION_HUGE;
/*     */       case "FIREWORKSSPARK":
/* 126 */         return ParticleEffect.FIREWORKS_SPARK;
/*     */       case "BUBBLE":
/* 128 */         return ParticleEffect.WATER_BUBBLE;
/*     */       case "SPLASH":
/* 130 */         return ParticleEffect.WATER_SPLASH;
/*     */       case "WAKE":
/* 132 */         return ParticleEffect.WATER_WAKE;
/*     */       case "SUSPENDED":
/* 134 */         return ParticleEffect.SUSPENDED;
/*     */       case "DEPTHSUSPEND":
/* 136 */         return ParticleEffect.SUSPENDED_DEPTH;
/*     */       case "MAGICCRIT":
/* 138 */         return ParticleEffect.CRIT_MAGIC;
/*     */       case "SMOKE":
/* 140 */         return ParticleEffect.SMOKE_NORMAL;
/*     */       case "LARGESMOKE":
/* 142 */         return ParticleEffect.SMOKE_LARGE;
/*     */       case "INSTANTSPELL":
/* 144 */         return ParticleEffect.SPELL_INSTANT;
/*     */       case "MOBSPELL":
/* 146 */         return ParticleEffect.SPELL_MOB;
/*     */       case "MOBSPELLAMBIENT":
/* 148 */         return ParticleEffect.SPELL_MOB_AMBIENT;
/*     */       case "WITCHMAGIC":
/* 150 */         return ParticleEffect.SPELL_WITCH;
/*     */       case "DRIPWATER":
/* 152 */         return ParticleEffect.DRIP_WATER;
/*     */       case "DRIPLAVA":
/* 154 */         return ParticleEffect.DRIP_LAVA;
/*     */       case "ANGRYVILLAGER":
/* 156 */         return ParticleEffect.VILLAGER_ANGRY;
/*     */       case "HAPPYVILLAGER":
/* 158 */         return ParticleEffect.VILLAGER_HAPPY;
/*     */       case "TOWNAURA":
/* 160 */         return ParticleEffect.TOWN_AURA;
/*     */       case "ENCHANTMENTTABLE":
/* 162 */         return ParticleEffect.ENCHANTMENT_TABLE;
/*     */       case "FLAME":
/* 164 */         return ParticleEffect.FLAME;
/*     */       case "LAVA":
/* 166 */         return ParticleEffect.LAVA;
/*     */       case "FOOTSTEP":
/* 168 */         return ParticleEffect.FOOTSTEP;
/*     */       case "REDDUST":
/* 170 */         return ParticleEffect.REDSTONE;
/*     */       case "SNOWBALLPOOF":
/* 172 */         return ParticleEffect.SNOWBALL;
/*     */       case "SLIME":
/* 174 */         return ParticleEffect.SLIME;
/*     */       case "HEART":
/* 176 */         return ParticleEffect.HEART;
/*     */       case "BARRIER":
/* 178 */         return ParticleEffect.BARRIER;
/*     */       case "CLOUD":
/* 180 */         return ParticleEffect.CLOUD;
/*     */       case "SNOWSHOVEL":
/* 182 */         return ParticleEffect.SNOW_SHOVEL;
/*     */       case "DROPLET":
/* 184 */         return ParticleEffect.WATER_DROP;
/*     */       case "MOBAPPEARANCE":
/* 186 */         return ParticleEffect.MOB_APPEARANCE;
/*     */     } 
/* 188 */     return ParticleEffect.valueOf(s);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\EffectLibSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */