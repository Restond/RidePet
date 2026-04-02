/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.ConsoleTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.CustomTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.LivingInRadiusTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.LivingInWorldTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.LivingNearOriginTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.LivingNearTargetLocationTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MPEntity;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MPLocation;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MPMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTLocation;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTMobsInRadius;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTNearestPlayer;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPassenger;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPlayerLocationsInRadius;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPlayersInRing;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPlayersInWorld;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPlayersNearTargetLocations;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTPlayersOnServer;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MTRandomThreatTarget;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.MobsNearOriginTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.PlayersNearOriginTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.ThreatTablePlayersTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.ThreatTableTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.VanillaTargeter;
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
/*     */ public abstract class SkillTargeter
/*     */ {
/*     */   protected MythicLineConfig config;
/*     */   
/*     */   protected static MythicMobs getPlugin() {
/*  59 */     return MythicMobs.inst();
/*     */   }
/*     */   
/*     */   public SkillTargeter(MythicLineConfig mlc) {
/*  63 */     this.config = mlc;
/*     */   }
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
/*     */   public static io.lumine.xikage.mythicmobs.skills.SkillTargeter getMythicTargeter(String search, MythicLineConfig mlc) {
/*  77 */     if (search == null) return null;
/*     */     
/*  79 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "? Matching MythicTargeter type to " + search, new Object[0]);
/*     */     
/*  81 */     ImmutableMap immutableMap = getPlugin().getSkillManager().getTargeters();
/*  82 */     if (immutableMap.containsKey(search.toUpperCase())) {
/*  83 */       Class<? extends io.lumine.xikage.mythicmobs.skills.SkillTargeter> clazz = (Class<? extends io.lumine.xikage.mythicmobs.skills.SkillTargeter>)immutableMap.get(search.toUpperCase());
/*     */       
/*     */       try {
/*  86 */         return clazz.getConstructor(new Class[] { MythicLineConfig.class }).newInstance(new Object[] { mlc });
/*  87 */       } catch (Exception e) {
/*  88 */         MythicLogger.error("Failed to construct targeter {0}", new Object[] { search });
/*  89 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     switch (search.toUpperCase()) { case "LIVINGINRADIUS": case "ENTITIESINRADIUS": case "LIVINGENTITIESINRADIUS": case "ALLINRADIUS":
/*     */       case "EIR":
/*  95 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new LivingInRadiusTargeter(mlc);
/*     */       case "LIVINGINWORLD": case "ENTITIESINWORLD": case "LIVINGENTITIESINWORLD": case "ALLINWORLD": case "EIW":
/*  97 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new LivingInWorldTargeter(mlc);
/*     */       case "LIVINGNEARTARGETLOCATION": case "LNTL": case "LIVINGENTITIESNEARTARGETLOCATION":
/*  99 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new LivingNearTargetLocationTargeter(mlc);
/*     */       case "LOCATION": case "LOC": case "L":
/* 101 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTLocation(mlc);
/*     */       case "MOBSINRADIUS": case "MOBS": case "MIR":
/* 103 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTMobsInRadius(mlc);
/*     */       case "NEARESTPLAYER":
/* 105 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTNearestPlayer(mlc);
/*     */       case "PASSENGER":
/*     */       case "RIDER":
/* 108 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPassenger(mlc);
/*     */       case "PLAYERSNEARTARGETLOCATION": case "PNTL":
/* 110 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPlayersNearTargetLocations(mlc);
/*     */       case "PLAYERLOCATIONSSINRADIUS": case "LOCATIONRADIUS": case "PLR": case "PLIR":
/* 112 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPlayerLocationsInRadius(mlc);
/*     */       case "PLAYERSINRING": case "PRING":
/* 114 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPlayersInRing(mlc);
/*     */       case "PLAYERSINWORLD": case "WORLD":
/* 116 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPlayersInWorld(mlc);
/*     */       case "PLAYERSONSERVER": case "SERVER":
/* 118 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTPlayersOnServer(mlc);
/*     */       case "THREATTABLE": case "THREATTARGETS":
/*     */       case "TT":
/* 121 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new ThreatTableTargeter(mlc);
/*     */       case "THREATTABLEPLAYERS": case "PLAYERSINTHREATTABLE": case "TTP":
/* 123 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new ThreatTablePlayersTargeter(mlc);
/*     */       case "THREATTABLERANDOMTARGET": case "RANDOMTHREATTARGET": case "RTT":
/* 125 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MTRandomThreatTarget(mlc);
/*     */       case "PLAYERSNEARORIGIN":
/*     */       case "PLAYERSNEARSOURCE":
/* 128 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new PlayersNearOriginTargeter(mlc);
/*     */       case "ENTITIESNEARORIGIN": case "ENTITIESNEARSOURCE": case "ENO": case "NEARORIGIN":
/* 130 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new LivingNearOriginTargeter(mlc);
/*     */       case "MOBSNEARORIGIN": case "MOBSNEARSOURCE":
/* 132 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MobsNearOriginTargeter(mlc);
/*     */       case "PARENTENTITIES":
/*     */       case "PE":
/* 135 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MPEntity(mlc);
/*     */       case "PARENTLOCATIONS": case "PL":
/* 137 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MPLocation(mlc);
/*     */       case "PARENTMETA": case "PM":
/* 139 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new MPMeta(mlc);
/*     */       
/*     */       case "CONSOLE":
/*     */       case "NONE":
/* 143 */         return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new ConsoleTargeter(mlc); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     if (search.substring(1, 2).equals("[")) {
/* 149 */       switch (search.substring(0, 1).toLowerCase()) { case "p": case "r": case "a":
/*     */         case "e":
/* 151 */           return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new VanillaTargeter(mlc, mlc.getLine()); }
/*     */ 
/*     */     
/*     */     }
/* 155 */     return (io.lumine.xikage.mythicmobs.skills.SkillTargeter)new CustomTargeter(search.toUpperCase(), mlc);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */