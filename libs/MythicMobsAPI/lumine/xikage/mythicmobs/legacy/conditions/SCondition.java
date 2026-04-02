/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.ConditionDay;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.ConditionDistanceFromSpawn;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.ConditionHeight;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.ConditionTargetLineOfSight;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.ConditionWorld;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.InvalidCondition;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class SCondition {
/* 12 */   private static HashMap<String, Class<? extends io.lumine.xikage.mythicmobs.legacy.conditions.SCondition>> conditions = new HashMap<>(); public abstract boolean check(Location paramLocation, LivingEntity paramLivingEntity, String paramString);
/*    */   public static io.lumine.xikage.mythicmobs.legacy.conditions.SCondition getSpawningConditionByName(String name) {
/*    */     Class<InvalidCondition> clazz1;
/* 15 */     Class<? extends io.lumine.xikage.mythicmobs.legacy.conditions.SCondition> clazz = conditions.get(name.toLowerCase());
/* 16 */     if (clazz == null) {
/* 17 */       clazz1 = InvalidCondition.class;
/*    */       try {
/* 19 */         return (io.lumine.xikage.mythicmobs.legacy.conditions.SCondition)clazz1.newInstance();
/* 20 */       } catch (Exception e) {
/* 21 */         return null;
/*    */       } 
/*    */     } 
/*    */     try {
/* 25 */       return (io.lumine.xikage.mythicmobs.legacy.conditions.SCondition)clazz1.newInstance();
/* 26 */     } catch (Exception e) {
/* 27 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   static {
/* 32 */     conditions.put("biome", ConditionBiome.class);
/* 33 */     conditions.put("day", ConditionDay.class);
/* 34 */     conditions.put("dawn", ConditionDawn.class);
/* 35 */     conditions.put("distancefromspawn", ConditionDistanceFromSpawn.class);
/* 36 */     conditions.put("dusk", ConditionDusk.class);
/* 37 */     conditions.put("haspotioneffect", ConditionHasPotionEffect.class);
/* 38 */     conditions.put("height", ConditionHeight.class);
/* 39 */     conditions.put("heightabove", ConditionHeightAbove.class);
/* 40 */     conditions.put("heightbelow", ConditionHeightBelow.class);
/* 41 */     conditions.put("holding", ConditionHolding.class);
/* 42 */     conditions.put("inregion", ConditionInRegion.class);
/* 43 */     conditions.put("notinregion", ConditionNotInRegion.class);
/* 44 */     conditions.put("inblock", ConditionInBlock.class);
/* 45 */     conditions.put("incombat", ConditionInCombat.class);
/* 46 */     conditions.put("inside", ConditionInside.class);
/* 47 */     conditions.put("lastsignal", ConditionLastSignal.class);
/* 48 */     conditions.put("level", ConditionLevel.class);
/* 49 */     conditions.put("lightlevel", ConditionLightLevel.class);
/* 50 */     conditions.put("lightlevelabove", ConditionLightLevelAbove.class);
/* 51 */     conditions.put("lightlevelbelow", ConditionLightLevelBelow.class);
/* 52 */     conditions.put("lunarphase", ConditionLunarPhase.class);
/* 53 */     conditions.put("mobtype", ConditionMobType.class);
/* 54 */     conditions.put("mobsinchunk", ConditionMobsInChunk.class);
/* 55 */     conditions.put("mobsinworld", ConditionMobsInWorld.class);
/* 56 */     conditions.put("night", ConditionNight.class);
/* 57 */     conditions.put("offgcd", ConditionOffGCD.class);
/* 58 */     conditions.put("onblock", ConditionOnBlock.class);
/* 59 */     conditions.put("outside", ConditionOutside.class);
/* 60 */     conditions.put("playerkills", ConditionPlayerKills.class);
/* 61 */     conditions.put("playerwithin", ConditionPlayerWithin.class);
/* 62 */     conditions.put("playernotwithin", ConditionPlayerNotWithin.class);
/* 63 */     conditions.put("raining", ConditionRaining.class);
/* 64 */     conditions.put("stance", ConditionStance.class);
/* 65 */     conditions.put("sunny", ConditionSunny.class);
/* 66 */     conditions.put("targetdistance", ConditionTargetDistance.class);
/* 67 */     conditions.put("targetwithin", ConditionTargetWithin.class);
/* 68 */     conditions.put("targetnotwithin", ConditionTargetNotWithin.class);
/* 69 */     conditions.put("targetinlineofsight", ConditionTargetLineOfSight.class);
/* 70 */     conditions.put("targetnotinlineofsight", ConditionTargetLineOfSight.class);
/* 71 */     conditions.put("thundering", ConditionThundering.class);
/* 72 */     conditions.put("threatwithin", ConditionTTWithin.class);
/* 73 */     conditions.put("world", ConditionWorld.class);
/* 74 */     conditions.put("worldtime", ConditionWorldTime.class);
/*    */     
/* 76 */     conditions.put("score", ConditionScore.class);
/* 77 */     conditions.put("globalscore", ConditionScoreGlobal.class);
/* 78 */     conditions.put("mobscore", ConditionScoreMob.class);
/* 79 */     conditions.put("targetscore", ConditionScoreTarget.class);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\SCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */