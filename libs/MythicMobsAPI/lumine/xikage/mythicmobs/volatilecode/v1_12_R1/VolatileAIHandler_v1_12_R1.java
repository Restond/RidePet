/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfinder;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.VersionCompliantReflections;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai.PathfinderHolder;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.server.v1_12_R1.EntityCreature;
/*     */ import net.minecraft.server.v1_12_R1.EntityHuman;
/*     */ import net.minecraft.server.v1_12_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_12_R1.EntityIronGolem;
/*     */ import net.minecraft.server.v1_12_R1.EntityMonster;
/*     */ import net.minecraft.server.v1_12_R1.EntityVillager;
/*     */ import net.minecraft.server.v1_12_R1.EntityWolf;
/*     */ import net.minecraft.server.v1_12_R1.IMonster;
/*     */ import net.minecraft.server.v1_12_R1.IRangedEntity;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoal;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalArrowAttack;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalAvoidTarget;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalBowShoot;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalBreakDoor;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalEatTile;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalFleeSun;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalFloat;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalHurtByTarget;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalLeapAtTarget;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalMoveIndoors;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalMoveThroughVillage;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalMoveTowardsRestriction;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalMoveTowardsTarget;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalNearestAttackableTarget;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalOpenDoor;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalPanic;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalRandomFly;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalRandomLookaround;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalRestrictOpenDoor;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalRestrictSun;
/*     */ import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
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
/*     */ public class VolatileAIHandler_v1_12_R1
/*     */   implements VolatileAIHandler
/*     */ {
/*  80 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_GOALS = new ConcurrentHashMap<>();
/*  81 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_TARGETS = new ConcurrentHashMap<>();
/*     */   
/*     */   public VolatileAIHandler_v1_12_R1(VolatileCodeHandler handler) {
/*  84 */     Set<Class<?>> customGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/*  86 */     for (Class<?> clazz : customGoalClasses) {
/*     */       try {
/*  88 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*  89 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/*  91 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*  92 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/*  93 */           for (String alias : aliases) {
/*  94 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/*  97 */       } catch (Exception ex) {
/*  98 */         MythicLogger.error("Failed to load custom AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     Set<Class<?>> wrappedGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 104 */     for (Class<?> clazz : wrappedGoalClasses) {
/*     */       try {
/* 106 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 107 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 109 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 110 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/* 111 */           for (String alias : aliases) {
/* 112 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 115 */       } catch (Exception ex) {
/* 116 */         MythicLogger.error("Failed to load wrapped AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     Set<Class<?>> customTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 122 */     for (Class<?> clazz : customTargeterClasses) {
/*     */       try {
/* 124 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 125 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 127 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 128 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/* 129 */           for (String alias : aliases) {
/* 130 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 133 */       } catch (Exception ex) {
/* 134 */         MythicLogger.error("Failed to load custom AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     Set<Class<?>> wrappedTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 140 */     for (Class<?> clazz : wrappedTargeterClasses) {
/*     */       try {
/* 142 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 143 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 145 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 146 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/* 147 */           for (String alias : aliases) {
/* 148 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 151 */       } catch (Exception ex) {
/* 152 */         MythicLogger.error("Failed to load wrapped AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(LivingEntity entity, LivingEntity target) {
/* 159 */     if (entity instanceof Creature) {
/*     */       try {
/* 161 */         ((Creature)entity).setTarget(target);
/* 162 */       } catch (Exception exception) {}
/*     */     } else {
/*     */       try {
/* 165 */         ((EntityInsentient)((CraftLivingEntity)entity).getHandle()).setGoalTarget(((CraftLivingEntity)target).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);
/* 166 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPathfinderGoals(LivingEntity entity, List<String> aiMods) {
/* 173 */     MythicMobs.debug(2, "aiGoalSelectorHandler activated...");
/*     */     try {
/* 175 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/* 177 */       Field goalsField = EntityInsentient.class.getDeclaredField("goalSelector");
/* 178 */       goalsField.setAccessible(true);
/* 179 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*     */       
/* 181 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       for (String str : aiMods) {
/* 188 */         String data, data2; Field listField; Set list; float speed; i++;
/* 189 */         String[] split = str.split(" ");
/*     */         
/* 191 */         if (split[0].matches("[0-9]*")) {
/*     */           
/* 193 */           j = Integer.parseInt(split[0]);
/* 194 */           goal = split[1];
/*     */           
/* 196 */           if (split.length > 2) {
/* 197 */             data = split[2];
/*     */           } else {
/* 199 */             data = null;
/*     */           } 
/* 201 */           if (split.length > 3) {
/* 202 */             data2 = split[3];
/*     */           } else {
/* 204 */             data2 = null;
/*     */           } 
/*     */         } else {
/* 207 */           j = i;
/* 208 */           goal = split[0];
/*     */           
/* 210 */           if (split.length > 1) {
/* 211 */             data = split[1];
/*     */           } else {
/* 213 */             data = null;
/*     */           } 
/* 215 */           if (split.length > 2) {
/* 216 */             data2 = split[2];
/*     */           } else {
/* 218 */             data2 = null;
/*     */           } 
/*     */         } 
/* 221 */         MythicLineConfig mlc = new MythicLineConfig(goal);
/* 222 */         String goal = mlc.getKey();
/*     */ 
/*     */         
/* 225 */         if (this.AI_GOALS.containsKey(goal.toUpperCase())) {
/* 226 */           Class<? extends PathfinderAdapter> clazz = this.AI_GOALS.get(goal.toUpperCase());
/*     */           
/*     */           try {
/* 229 */             if (clazz.isAssignableFrom(Pathfinder.class)) {
/* 230 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 231 */               goals.a(j, createAIGoal(pathfinder)); continue;
/*     */             } 
/* 233 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 234 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*     */             
/* 236 */             if (holder.isValid()) {
/* 237 */               PathfinderGoal pathfinder = holder.create();
/* 238 */               goals.a(j, pathfinder); continue;
/*     */             } 
/* 240 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*     */ 
/*     */             
/*     */             continue;
/* 244 */           } catch (Exception|Error ex) {
/* 245 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/* 246 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/* 250 */         switch (goal.toLowerCase()) { case "reset":
/*     */           case "clear":
/* 252 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/* 253 */             listField.setAccessible(true);
/* 254 */             list = (Set)listField.get(goals);
/* 255 */             list.clear();
/* 256 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/* 257 */             listField.setAccessible(true);
/* 258 */             list = (Set)listField.get(goals);
/* 259 */             list.clear();
/*     */ 
/*     */           
/*     */           case "arrowattack":
/* 263 */             if (e instanceof IRangedEntity)
/* 264 */               goals.a(j, (PathfinderGoal)new PathfinderGoalArrowAttack((IRangedEntity)e, 1.0D, 20, 60, 15.0F)); 
/*     */           case "skeletonbowattack":
/*     */           case "bowshoot":
/*     */           case "bowmaster":
/* 268 */             if (e instanceof IRangedEntity) {
/* 269 */               goals.a(j, (PathfinderGoal)new PathfinderGoalBowShoot((EntityMonster)e, 1.0D, 20, 15.0F));
/*     */             }
/*     */           
/*     */           case "breakdoor":
/* 273 */             goals.a(j, (PathfinderGoal)new PathfinderGoalBreakDoor(e));
/*     */           
/*     */           case "eatgrass":
/* 276 */             goals.a(j, (PathfinderGoal)new PathfinderGoalEatTile(e));
/*     */           case "fleegolems":
/*     */           case "runfromgolems":
/* 279 */             if (e instanceof EntityCreature) {
/* 280 */               float distance = 16.0F;
/* 281 */               double d = 1.2D;
/* 282 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 283 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 284 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityIronGolem.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleeplayers":
/*     */           case "runfromplayers":
/* 289 */             if (e instanceof EntityCreature) {
/* 290 */               float distance = 16.0F;
/* 291 */               double d = 1.2D;
/* 292 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 293 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 294 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityHuman.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleevillagers":
/*     */           case "runfromvillagers":
/* 299 */             if (e instanceof EntityCreature) {
/* 300 */               float distance = 16.0F;
/* 301 */               double d = 1.2D;
/* 302 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 303 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 304 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityVillager.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleewolves":
/*     */           case "runfromwolves":
/* 309 */             if (e instanceof EntityCreature) {
/* 310 */               float distance = 6.0F;
/* 311 */               double d = 1.2D;
/* 312 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 313 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 314 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityWolf.class, distance, 1.0D, d));
/*     */             } 
/*     */ 
/*     */           
/*     */           case "fleesun":
/* 319 */             if (e instanceof EntityCreature) {
/* 320 */               goals.a(j, (PathfinderGoal)new PathfinderGoalFleeSun((EntityCreature)e, 1.0D));
/*     */             }
/*     */           
/*     */           case "float":
/*     */           case "swim":
/* 325 */             goals.a(j, (PathfinderGoal)new PathfinderGoalFloat(e));
/*     */           
/*     */           case "gotolocation":
/*     */           case "goto":
/*     */             try {
/* 330 */               String[] sp = data.split(",");
/* 331 */               int x = Integer.valueOf(sp[0]).intValue();
/* 332 */               int y = Integer.valueOf(sp[1]).intValue();
/* 333 */               int z = Integer.valueOf(sp[2]).intValue();
/*     */               
/* 335 */               float f = 1.0F;
/* 336 */               if (data2 != null) {
/* 337 */                 f = Float.valueOf(data2).floatValue();
/*     */               }
/*     */               
/* 340 */               AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/*     */               
/* 342 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToLocation(this, e, l, f));
/* 343 */             } catch (Exception ex) {
/* 344 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "gotoowner":
/*     */             try {
/* 349 */               float distance = 4.0F;
/* 350 */               if (data != null) {
/* 351 */                 distance = Float.valueOf(data2).floatValue();
/*     */               }
/* 353 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToOwner(this, e, (distance * distance)));
/* 354 */             } catch (Exception ex) {
/* 355 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "gotoparent":
/*     */             try {
/* 360 */               float distance = 4.0F;
/* 361 */               if (data != null) {
/* 362 */                 distance = Float.valueOf(data2).floatValue();
/*     */               }
/* 364 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToParent(this, e, (distance * distance)));
/* 365 */             } catch (Exception ex) {
/* 366 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "lookatplayers":
/* 370 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLookAtPlayer(e, EntityHuman.class, 5.0F, 1.0F));
/*     */           
/*     */           case "leapattarget":
/* 373 */             speed = 0.3F;
/* 374 */             if (data != null) {
/* 375 */               speed = Float.valueOf(data).floatValue();
/*     */             }
/* 377 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLeapAtTarget(e, speed));
/*     */           
/*     */           case "spiderattack":
/* 380 */             if (e instanceof EntityCreature) {
/* 381 */               goals.a(j, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack((EntityCreature)e, EntityCreature.class));
/*     */             }
/*     */           
/*     */           case "moveindoors":
/* 385 */             if (e instanceof EntityCreature) {
/* 386 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveIndoors((EntityCreature)e));
/*     */             }
/*     */           
/*     */           case "movethroughvillage":
/* 390 */             if (e instanceof EntityCreature) {
/* 391 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveThroughVillage((EntityCreature)e, 0.6D, true));
/*     */             }
/*     */           
/*     */           case "movetowardsrestriction":
/* 395 */             if (e instanceof EntityCreature) {
/* 396 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)e, 0.6D));
/*     */             }
/*     */           
/*     */           case "movetowardstarget":
/* 400 */             if (e instanceof EntityCreature) {
/* 401 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsTarget((EntityCreature)e, 0.9D, 32.0F));
/*     */             }
/*     */           case "opendoor":
/*     */           case "opendoors":
/* 405 */             goals.a(j, (PathfinderGoal)new PathfinderGoalOpenDoor(e, true));
/*     */           
/*     */           case "panic":
/* 408 */             if (e instanceof EntityCreature) {
/* 409 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPanic((EntityCreature)e, 1.25D));
/*     */             }
/*     */ 
/*     */           
/*     */           case "patrol":
/*     */           case "patrolroute":
/*     */             try {
/* 416 */               List<AbstractLocation> points = new ArrayList<>();
/*     */               
/* 418 */               String[] split2 = data.split(";");
/*     */               
/* 420 */               for (String s : split2) {
/* 421 */                 String[] split3 = s.split(",");
/*     */                 
/* 423 */                 int x = Integer.valueOf(split3[0]).intValue();
/* 424 */                 int y = Integer.valueOf(split3[1]).intValue();
/* 425 */                 int z = Integer.valueOf(split3[2]).intValue();
/*     */                 
/* 427 */                 AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/* 428 */                 points.add(l);
/*     */               } 
/*     */               
/* 431 */               float speed2 = 1.0F;
/* 432 */               if (data2 != null) {
/* 433 */                 speed = Float.valueOf(data2).floatValue();
/*     */               }
/* 435 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPatrolRoute(this, e, points, speed2));
/* 436 */             } catch (Exception ex) {
/* 437 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "randomlookaround":
/*     */           case "lookaround":
/* 442 */             goals.a(j, (PathfinderGoal)new PathfinderGoalRandomLookaround(e));
/*     */           
/*     */           case "randomstroll":
/* 445 */             if (e instanceof EntityCreature) {
/* 446 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)e, 1.0D));
/*     */             }
/*     */           
/*     */           case "randomfly":
/* 450 */             if (e instanceof EntityCreature) {
/* 451 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRandomFly((EntityCreature)e, 1.0D));
/*     */             }
/*     */           case "restrictopendoor":
/*     */           case "closedoors":
/* 455 */             if (e instanceof EntityCreature) {
/* 456 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictOpenDoor((EntityCreature)e));
/*     */             }
/*     */           
/*     */           case "restrictsun":
/* 460 */             if (e instanceof EntityCreature) {
/* 461 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictSun((EntityCreature)e));
/*     */             } }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 468 */     } catch (Exception ex) {
/* 469 */       MythicMobs.error("An error occurred while adding an AIGoalSelector, enable debugging for a stack trace.");
/* 470 */       if (ConfigManager.debugLevel > 0) {
/* 471 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetGoals(LivingEntity entity, List<String> aiMods) {
/* 479 */     MythicMobs.debug(2, "aiTargetSelectorHandler activated...");
/*     */     try {
/* 481 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/* 483 */       Field goalsField = EntityInsentient.class.getDeclaredField("targetSelector");
/* 484 */       goalsField.setAccessible(true);
/* 485 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*     */       
/* 487 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 493 */       for (String str : aiMods) {
/* 494 */         String data; Field listField; Set list; i++;
/* 495 */         String[] split = str.split(" ");
/*     */         
/* 497 */         if (split[0].matches("[0-9]*")) {
/*     */           
/* 499 */           j = Integer.parseInt(split[0]);
/* 500 */           goal = split[1];
/*     */           
/* 502 */           if (split.length > 2) {
/* 503 */             data = split[2];
/*     */           } else {
/* 505 */             data = "";
/*     */           } 
/*     */         } else {
/* 508 */           j = i;
/* 509 */           goal = split[0];
/*     */           
/* 511 */           if (split.length > 1) {
/* 512 */             data = split[1];
/*     */           } else {
/* 514 */             data = "";
/*     */           } 
/*     */         } 
/*     */         
/* 518 */         MythicLineConfig mlc = new MythicLineConfig(goal);
/* 519 */         String goal = mlc.getKey();
/*     */ 
/*     */         
/* 522 */         if (this.AI_TARGETS.containsKey(goal.toUpperCase())) {
/* 523 */           Class<? extends PathfinderAdapter> clazz = this.AI_TARGETS.get(goal.toUpperCase());
/*     */           
/*     */           try {
/* 526 */             if (clazz.isAssignableFrom(Pathfinder.class)) {
/* 527 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 528 */               goals.a(j, createAIGoal(pathfinder)); continue;
/*     */             } 
/* 530 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 531 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*     */             
/* 533 */             if (holder.isValid()) {
/* 534 */               PathfinderGoal pathfinder = holder.create();
/* 535 */               goals.a(j, pathfinder); continue;
/*     */             } 
/* 537 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*     */ 
/*     */             
/*     */             continue;
/* 541 */           } catch (Exception|Error ex) {
/* 542 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/* 543 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/* 547 */         switch (goal.toLowerCase()) { case "reset":
/*     */           case "clear":
/* 549 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/* 550 */             listField.setAccessible(true);
/* 551 */             list = (Set)listField.get(goals);
/* 552 */             list.clear();
/* 553 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/* 554 */             listField.setAccessible(true);
/* 555 */             list = (Set)listField.get(goals);
/* 556 */             list.clear();
/*     */           
/*     */           case "ownerhurttarget":
/*     */           case "ownertarget":
/* 560 */             if (e instanceof net.minecraft.server.v1_12_R1.EntityTameableAnimal) {
/* 561 */               goals.a(j, (PathfinderGoal)new PathfinderGoalHurtByTarget((EntityCreature)e, false, new Class[0]));
/*     */             }
/*     */           
/*     */           case "monsters":
/* 565 */             if (e instanceof EntityCreature) {
/* 566 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*     */             }
/*     */           
/*     */           case "players":
/* 570 */             if (e instanceof EntityCreature) {
/* 571 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityHuman.class, true));
/*     */             }
/*     */           
/*     */           case "villagers":
/* 575 */             if (e instanceof EntityCreature) {
/* 576 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityVillager.class, false));
/*     */             }
/*     */           case "iron_golems":
/*     */           case "golems":
/* 580 */             if (e instanceof EntityCreature) {
/* 581 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityIronGolem.class, false));
/*     */             }
/*     */           
/*     */           case "otherfaction":
/* 585 */             if (e instanceof EntityCreature) {
/* 586 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true));
/* 587 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityHuman.class, 0, true));
/*     */             } 
/*     */           
/*     */           case "otherfactionmonsters":
/* 591 */             if (e instanceof EntityCreature) {
/* 592 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*     */             }
/*     */           
/*     */           case "otherfactionvillagers":
/* 596 */             if (e instanceof EntityCreature) {
/* 597 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityVillager.class, 0, false));
/*     */             }
/*     */           
/*     */           case "specificfaction":
/* 601 */             if (e instanceof EntityCreature) {
/* 602 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, false));
/*     */             }
/*     */           
/*     */           case "specificfactionmonsters":
/* 606 */             if (e instanceof EntityCreature) {
/* 607 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, true, false, IMonster.d));
/*     */             } }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 614 */     } catch (Exception ex) {
/* 615 */       MythicMobs.error("An error has occurred, enable debugging for a stack trace.");
/* 616 */       if (ConfigManager.debugLevel > 0) {
/* 617 */         ex.printStackTrace();
/*     */       }
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathfinderGoal createAIGoal(Pathfinder goal) {
/* 917 */     return (PathfinderGoal)new CustomAIAdapter_v1_12_R1(this, goal);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\VolatileAIHandler_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */