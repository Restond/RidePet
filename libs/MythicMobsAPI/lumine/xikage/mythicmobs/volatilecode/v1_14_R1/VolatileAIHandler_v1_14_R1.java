/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1;
/*     */ 
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfinder;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.VersionCompliantReflections;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.CustomAIAdapter;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.PathfinderHolder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.server.v1_14_R1.EntityCreature;
/*     */ import net.minecraft.server.v1_14_R1.EntityHuman;
/*     */ import net.minecraft.server.v1_14_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_14_R1.EntityIronGolem;
/*     */ import net.minecraft.server.v1_14_R1.EntityMonster;
/*     */ import net.minecraft.server.v1_14_R1.EntityTameableAnimal;
/*     */ import net.minecraft.server.v1_14_R1.EntityVillager;
/*     */ import net.minecraft.server.v1_14_R1.EntityWolf;
/*     */ import net.minecraft.server.v1_14_R1.EnumDifficulty;
/*     */ import net.minecraft.server.v1_14_R1.IRangedEntity;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoal;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalArrowAttack;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalAvoidTarget;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalBowShoot;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalBreakDoor;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalDoorOpen;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalEatTile;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalFleeSun;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalLeapAtTarget;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalMoveThroughVillage;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalMoveTowardsRestriction;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalOwnerHurtByTarget;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalPanic;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalRestrictSun;
/*     */ import net.minecraft.server.v1_14_R1.PathfinderGoalSelector;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity;
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
/*     */ public class VolatileAIHandler_v1_14_R1
/*     */   implements VolatileAIHandler
/*     */ {
/*  74 */   private Reflector<EntityInsentient> refEntityInsentient = new Reflector(EntityInsentient.class, new String[] { "goalSelector", "targetSelector" });
/*  75 */   private Reflector<PathfinderGoalSelector> refGoalSelector = new Reflector(PathfinderGoalSelector.class, new String[] { "c", "d" });
/*     */   
/*  77 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_GOALS = new ConcurrentHashMap<>();
/*  78 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_TARGETS = new ConcurrentHashMap<>();
/*     */   
/*     */   public VolatileAIHandler_v1_14_R1(VolatileCodeHandler handler) {
/*  81 */     Set<Class<?>> customGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/*  83 */     for (Class<?> clazz : customGoalClasses) {
/*     */       try {
/*  85 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*  86 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/*  88 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*  89 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/*  90 */           for (String alias : aliases) {
/*  91 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/*  94 */       } catch (Exception ex) {
/*  95 */         MythicLogger.error("Failed to load custom AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     Set<Class<?>> wrappedGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 101 */     for (Class<?> clazz : wrappedGoalClasses) {
/*     */       try {
/* 103 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 104 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 106 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 107 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/* 108 */           for (String alias : aliases) {
/* 109 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 112 */       } catch (Exception ex) {
/* 113 */         MythicLogger.error("Failed to load wrapped AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     Set<Class<?>> customTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 119 */     for (Class<?> clazz : customTargeterClasses) {
/*     */       try {
/* 121 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 122 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 124 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 125 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/* 126 */           for (String alias : aliases) {
/* 127 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 130 */       } catch (Exception ex) {
/* 131 */         MythicLogger.error("Failed to load custom AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     Set<Class<?>> wrappedTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*     */     
/* 137 */     for (Class<?> clazz : wrappedTargeterClasses) {
/*     */       try {
/* 139 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/* 140 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*     */         
/* 142 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/* 143 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/* 144 */           for (String alias : aliases) {
/* 145 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 148 */       } catch (Exception ex) {
/* 149 */         MythicLogger.error("Failed to load wrapped AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(LivingEntity entity, LivingEntity target) {
/* 156 */     if (entity instanceof Creature) {
/*     */       try {
/* 158 */         ((Creature)entity).setTarget(target);
/* 159 */       } catch (Exception exception) {}
/*     */     } else {
/*     */       try {
/* 162 */         ((EntityInsentient)((CraftLivingEntity)entity).getHandle()).setGoalTarget(((CraftLivingEntity)target).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);
/* 163 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void navigateToLocation(AbstractEntity entity, AbstractLocation destination, double maxDistance) {
/* 169 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/* 172 */     EntityInsentient e = (EntityInsentient)((CraftLivingEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 173 */     e.getNavigation().a(destination.getX(), destination.getY(), destination.getZ(), 1.0D);
/*     */   }
/*     */   
/*     */   public void clearPathfinderGoals(AbstractEntity entity) {
/* 177 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/* 180 */     EntityInsentient e = (EntityInsentient)((CraftLivingEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 181 */     PathfinderGoalSelector goals = (PathfinderGoalSelector)this.refEntityInsentient.get(e, "goalSelector");
/*     */     
/* 183 */     ((Map)this.refGoalSelector.get(goals, "c")).clear();
/* 184 */     ((Set)this.refGoalSelector.get(goals, "d")).clear();
/*     */   }
/*     */   
/*     */   public void clearPathfinderTargets(AbstractEntity entity) {
/* 188 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/* 191 */     EntityInsentient e = (EntityInsentient)((CraftLivingEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 192 */     PathfinderGoalSelector goals = (PathfinderGoalSelector)this.refEntityInsentient.get(e, "targetSelector");
/*     */     
/* 194 */     ((Map)this.refGoalSelector.get(goals, "c")).clear();
/* 195 */     ((Set)this.refGoalSelector.get(goals, "d")).clear();
/*     */   }
/*     */   
/*     */   public void addPathfindersGoal(int index, AbstractEntity entity, PathfinderGoal goal, Predicate<AbstractEntity> validator) {
/* 199 */     if (validator != null && 
/* 200 */       !validator.test(entity)) {
/*     */       return;
/*     */     }
/*     */     
/* 204 */     EntityInsentient nmsEntity = (EntityInsentient)((CraftLivingEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 205 */     PathfinderGoalSelector goals = (PathfinderGoalSelector)this.refEntityInsentient.get(nmsEntity, "");
/*     */     
/*     */     try {
/* 208 */       goals.a(index, goal);
/* 209 */     } catch (Exception ex) {
/* 210 */       MythicLogger.error("Failed to apply pathfinder goal");
/* 211 */       if (ConfigManager.debugLevel > 0) {
/* 212 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPathfinderGoals(LivingEntity entity, List<String> aiMods) {
/* 220 */     MythicMobs.debug(2, "aiGoalSelectorHandler activated...");
/*     */     try {
/* 222 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/* 224 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)this.refEntityInsentient.get(e, "goalSelector");
/*     */       
/* 226 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 232 */       for (String str : aiMods) {
/* 233 */         String data, data2; float speed; i++;
/* 234 */         String[] split = str.split(" ");
/*     */         
/* 236 */         if (split[0].matches("[0-9]*")) {
/*     */           
/* 238 */           j = Integer.parseInt(split[0]);
/* 239 */           goal = split[1];
/*     */           
/* 241 */           if (split.length > 2) {
/* 242 */             data = split[2];
/*     */           } else {
/* 244 */             data = null;
/*     */           } 
/* 246 */           if (split.length > 3) {
/* 247 */             data2 = split[3];
/*     */           } else {
/* 249 */             data2 = null;
/*     */           } 
/*     */         } else {
/* 252 */           j = i;
/* 253 */           goal = split[0];
/*     */           
/* 255 */           if (split.length > 1) {
/* 256 */             data = split[1];
/*     */           } else {
/* 258 */             data = null;
/*     */           } 
/* 260 */           if (split.length > 2) {
/* 261 */             data2 = split[2];
/*     */           } else {
/* 263 */             data2 = null;
/*     */           } 
/*     */         } 
/*     */         
/* 267 */         MythicLineConfig mlc = new MythicLineConfig(MythicLineConfig.unparseBlock(goal));
/* 268 */         String goal = mlc.getKey();
/*     */ 
/*     */         
/* 271 */         if (this.AI_GOALS.containsKey(goal.toUpperCase())) {
/* 272 */           Class<? extends PathfinderAdapter> clazz = this.AI_GOALS.get(goal.toUpperCase());
/*     */           
/*     */           try {
/* 275 */             if (Pathfinder.class.isAssignableFrom(clazz)) {
/* 276 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 277 */               goals.a(j, CustomAIAdapter.create(pathfinder)); continue;
/*     */             } 
/* 279 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 280 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*     */             
/* 282 */             if (holder.isValid()) {
/* 283 */               PathfinderGoal pathfinder = holder.create();
/* 284 */               goals.a(j, pathfinder); continue;
/*     */             } 
/* 286 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*     */ 
/*     */             
/*     */             continue;
/* 290 */           } catch (Exception|Error ex) {
/* 291 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/* 292 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/* 296 */         switch (goal.toLowerCase()) {
/*     */           case "reset":
/*     */           case "clear":
/* 299 */             ((Map)this.refGoalSelector.get(goals, "c")).clear();
/* 300 */             ((Set)this.refGoalSelector.get(goals, "d")).clear();
/*     */ 
/*     */           
/*     */           case "arrowattack":
/* 304 */             if (e instanceof IRangedEntity)
/* 305 */               goals.a(j, (PathfinderGoal)new PathfinderGoalArrowAttack((IRangedEntity)e, 1.0D, 20, 60, 15.0F)); 
/*     */           case "skeletonbowattack":
/*     */           case "bowshoot":
/*     */           case "bowmaster":
/* 309 */             if (e instanceof IRangedEntity) {
/* 310 */               goals.a(j, (PathfinderGoal)new PathfinderGoalBowShoot((EntityMonster)e, 1.0D, 20, 15.0F));
/*     */             }
/*     */           
/*     */           case "breakdoor":
/* 314 */             goals.a(j, (PathfinderGoal)new PathfinderGoalBreakDoor(e, p -> true));
/*     */           
/*     */           case "eatgrass":
/* 317 */             goals.a(j, (PathfinderGoal)new PathfinderGoalEatTile(e));
/*     */           case "fleegolems":
/*     */           case "runfromgolems":
/* 320 */             if (e instanceof EntityCreature) {
/* 321 */               float distance = 16.0F;
/* 322 */               double d = 1.2D;
/* 323 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 324 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 325 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityIronGolem.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleeplayers":
/*     */           case "runfromplayers":
/* 330 */             if (e instanceof EntityCreature) {
/* 331 */               float distance = 16.0F;
/* 332 */               double d = 1.2D;
/* 333 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 334 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 335 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityHuman.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleevillagers":
/*     */           case "runfromvillagers":
/* 340 */             if (e instanceof EntityCreature) {
/* 341 */               float distance = 16.0F;
/* 342 */               double d = 1.2D;
/* 343 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 344 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 345 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityVillager.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleewolves":
/*     */           case "runfromwolves":
/* 350 */             if (e instanceof EntityCreature) {
/* 351 */               float distance = 6.0F;
/* 352 */               double d = 1.2D;
/* 353 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 354 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 355 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityWolf.class, distance, 1.0D, d));
/*     */             } 
/*     */ 
/*     */           
/*     */           case "fleesun":
/* 360 */             if (e instanceof EntityCreature) {
/* 361 */               goals.a(j, (PathfinderGoal)new PathfinderGoalFleeSun((EntityCreature)e, 1.0D));
/*     */             }
/*     */           
/*     */           case "float":
/*     */           case "swim":
/* 366 */             goals.a(j, (PathfinderGoal)new PathfinderGoalFloat(e));
/*     */ 
/*     */           
/*     */           case "gotoparent":
/*     */             try {
/* 371 */               float distance = 4.0F;
/* 372 */               if (data != null) {
/* 373 */                 distance = Float.valueOf(data2).floatValue();
/*     */               }
/* 375 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToParent(this, e, (distance * distance)));
/* 376 */             } catch (Exception ex) {
/* 377 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "lookatplayers":
/* 381 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLookAtPlayer(e, EntityHuman.class, 5.0F, 1.0F));
/*     */           
/*     */           case "leapattarget":
/* 384 */             speed = 0.3F;
/* 385 */             if (data != null) {
/* 386 */               speed = Float.valueOf(data).floatValue();
/*     */             }
/* 388 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLeapAtTarget(e, speed));
/*     */           
/*     */           case "spiderattack":
/* 391 */             if (e instanceof EntityCreature) {
/* 392 */               goals.a(j, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack((EntityCreature)e, EntityCreature.class));
/*     */             }
/*     */           
/*     */           case "movethroughvillage":
/* 396 */             if (e instanceof EntityCreature) {
/* 397 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveThroughVillage((EntityCreature)e, 0.6D, false, 4, () -> false));
/*     */             }
/*     */ 
/*     */ 
/*     */           
/*     */           case "movetowardsrestriction":
/* 403 */             if (e instanceof EntityCreature) {
/* 404 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)e, 0.6D));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case "opendoor":
/*     */           case "opendoors":
/* 415 */             goals.a(j, (PathfinderGoal)new PathfinderGoalDoorOpen(e, true));
/*     */           
/*     */           case "panic":
/* 418 */             if (e instanceof EntityCreature) {
/* 419 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPanic((EntityCreature)e, 1.25D));
/*     */             }
/*     */ 
/*     */           
/*     */           case "patrol":
/*     */           case "patrolroute":
/*     */             try {
/* 426 */               List<AbstractLocation> points = new ArrayList<>();
/*     */               
/* 428 */               String[] split2 = data.split(";");
/*     */               
/* 430 */               for (String s : split2) {
/* 431 */                 String[] split3 = s.split(",");
/*     */                 
/* 433 */                 int x = Integer.valueOf(split3[0]).intValue();
/* 434 */                 int y = Integer.valueOf(split3[1]).intValue();
/* 435 */                 int z = Integer.valueOf(split3[2]).intValue();
/*     */                 
/* 437 */                 AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/* 438 */                 points.add(l);
/*     */               } 
/*     */               
/* 441 */               float speed2 = 1.0F;
/* 442 */               if (data2 != null) {
/* 443 */                 speed = Float.valueOf(data2).floatValue();
/*     */               }
/* 445 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPatrolRoute(this, e, points, speed2));
/* 446 */             } catch (Exception ex) {
/* 447 */               ex.printStackTrace();
/*     */             } 
/*     */ 
/*     */           
/*     */           case "restrictsun":
/* 452 */             if (e instanceof EntityCreature) {
/* 453 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictSun((EntityCreature)e));
/*     */             }
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 460 */     } catch (Exception ex) {
/* 461 */       MythicMobs.error("An error occurred while adding an AIGoalSelector, enable debugging for a stack trace.");
/* 462 */       if (ConfigManager.debugLevel > 0) {
/* 463 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetGoals(LivingEntity entity, List<String> aiMods) {
/* 471 */     MythicMobs.debug(2, "aiTargetSelectorHandler activated...");
/*     */     try {
/* 473 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/* 475 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)this.refEntityInsentient.get(e, "targetSelector");
/*     */       
/* 477 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 483 */       for (String str : aiMods) {
/* 484 */         String data; i++;
/* 485 */         String[] split = str.split(" ");
/*     */         
/* 487 */         if (split[0].matches("[0-9]*")) {
/*     */           
/* 489 */           j = Integer.parseInt(split[0]);
/* 490 */           goal = split[1];
/*     */           
/* 492 */           if (split.length > 2) {
/* 493 */             data = split[2];
/*     */           } else {
/* 495 */             data = "";
/*     */           } 
/*     */         } else {
/* 498 */           j = i;
/* 499 */           goal = split[0];
/*     */           
/* 501 */           if (split.length > 1) {
/* 502 */             data = split[1];
/*     */           } else {
/* 504 */             data = "";
/*     */           } 
/*     */         } 
/*     */         
/* 508 */         MythicLineConfig mlc = new MythicLineConfig(goal);
/* 509 */         String goal = mlc.getKey();
/*     */ 
/*     */         
/* 512 */         if (this.AI_TARGETS.containsKey(goal.toUpperCase())) {
/* 513 */           Class<? extends PathfinderAdapter> clazz = this.AI_TARGETS.get(goal.toUpperCase());
/*     */           
/*     */           try {
/* 516 */             if (clazz.isAssignableFrom(Pathfinder.class)) {
/* 517 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 518 */               goals.a(j, CustomAIAdapter.create(pathfinder)); continue;
/*     */             } 
/* 520 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/* 521 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*     */             
/* 523 */             if (holder.isValid()) {
/* 524 */               PathfinderGoal pathfinder = holder.create();
/* 525 */               goals.a(j, pathfinder); continue;
/*     */             } 
/* 527 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*     */ 
/*     */             
/*     */             continue;
/* 531 */           } catch (Exception|Error ex) {
/* 532 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/* 533 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/* 537 */         switch (goal.toLowerCase()) { case "reset":
/*     */           case "clear":
/* 539 */             ((Map)this.refGoalSelector.get(goals, "c")).clear();
/* 540 */             ((Set)this.refGoalSelector.get(goals, "d")).clear();
/*     */           
/*     */           case "ownerhurttarget":
/*     */           case "ownertarget":
/* 544 */             if (e instanceof EntityTameableAnimal) {
/* 545 */               goals.a(j, (PathfinderGoal)new PathfinderGoalOwnerHurtByTarget((EntityTameableAnimal)e));
/*     */             }
/*     */           
/*     */           case "monsters":
/* 549 */             if (e instanceof EntityCreature) {
/* 550 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityInsentient.class, 0, true, false, entityliving -> entityliving instanceof net.minecraft.server.v1_14_R1.IMonster));
/*     */             }
/*     */           
/*     */           case "players":
/* 554 */             if (e instanceof EntityCreature) {
/* 555 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityHuman.class, true));
/*     */             }
/*     */           
/*     */           case "villagers":
/* 559 */             if (e instanceof EntityCreature) {
/* 560 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityVillager.class, false));
/*     */             }
/*     */           case "iron_golems":
/*     */           case "golems":
/* 564 */             if (e instanceof EntityCreature) {
/* 565 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityIronGolem.class, false));
/*     */             }
/*     */           
/*     */           case "otherfaction":
/* 569 */             if (e instanceof EntityCreature) {
/* 570 */               String faction = data;
/*     */               
/* 572 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityInsentient.class, 0, true, false, targetEntity -> {
/*     */                       try {
/*     */                         UUID mobUUID = paramEntityInsentient.getUniqueID();
/*     */ 
/*     */                         
/*     */                         UUID targetUUID = ((EntityInsentient)targetEntity).getUniqueID();
/*     */ 
/*     */                         
/*     */                         Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(targetUUID);
/*     */                         
/*     */                         if (!maybeMob.isPresent()) {
/*     */                           return true;
/*     */                         }
/*     */                         
/*     */                         ActiveMob am = maybeMob.get();
/*     */                         
/*     */                         return am.hasFaction() ? (!((ActiveMob)MythicMobs.inst().getMobManager().getActiveMob(mobUUID).get()).getFaction().equals(paramString)) : true;
/* 589 */                       } catch (Exception ex) {
/*     */                         ex.printStackTrace();
/*     */                         return false;
/*     */                       } 
/*     */                     }));
/*     */             } 
/*     */           
/*     */           case "otherfactionmonsters":
/* 597 */             if (e instanceof EntityCreature) {
/* 598 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityInsentient.class, 0, true, false, targetEntity -> {
/*     */                       try {
/*     */                         if (targetEntity instanceof net.minecraft.server.v1_14_R1.IMonster) {
/*     */                           UUID mobUUID = paramEntityInsentient.getUniqueID();
/*     */ 
/*     */                           
/*     */                           UUID targetUUID = ((EntityInsentient)targetEntity).getUniqueID();
/*     */                           
/*     */                           Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(targetUUID);
/*     */                           
/*     */                           if (!maybeMob.isPresent()) {
/*     */                             return true;
/*     */                           }
/*     */                           
/*     */                           ActiveMob am = maybeMob.get();
/*     */                           
/*     */                           return am.hasFaction() ? (!((ActiveMob)MythicMobs.inst().getMobManager().getActiveMob(mobUUID).get()).getFaction().equals(am.getFaction())) : true;
/*     */                         } 
/*     */                         
/*     */                         return false;
/* 618 */                       } catch (Exception ex) {
/*     */                         ex.printStackTrace();
/*     */                         return false;
/*     */                       } 
/*     */                     }));
/*     */             }
/*     */           
/*     */           case "specificfaction":
/* 626 */             if (e instanceof EntityCreature) {
/* 627 */               String faction = data;
/*     */               
/* 629 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityInsentient.class, 0, true, false, targetEntity -> {
/*     */                       try {
/*     */                         UUID mobUUID = paramEntityInsentient.getUniqueID();
/*     */ 
/*     */                         
/*     */                         UUID targetUUID = ((EntityInsentient)targetEntity).getUniqueID();
/*     */ 
/*     */                         
/*     */                         Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(targetUUID);
/*     */                         
/*     */                         if (!maybeMob.isPresent()) {
/*     */                           return true;
/*     */                         }
/*     */                         
/*     */                         ActiveMob am = maybeMob.get();
/*     */                         
/*     */                         return am.hasFaction() ? am.getFaction().equals(paramString) : false;
/* 646 */                       } catch (Exception ex) {
/*     */                         ex.printStackTrace();
/*     */                         return false;
/*     */                       } 
/*     */                     }));
/*     */             } 
/*     */           
/*     */           case "specificfactionmonsters":
/* 654 */             if (e instanceof EntityCreature) {
/* 655 */               String faction = data;
/*     */               
/* 657 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(e, EntityInsentient.class, 0, true, false, targetEntity -> {
/*     */                       try {
/*     */                         if (targetEntity instanceof net.minecraft.server.v1_14_R1.IMonster) {
/*     */                           UUID mobUUID = paramEntityInsentient.getUniqueID();
/*     */ 
/*     */                           
/*     */                           UUID targetUUID = ((EntityInsentient)targetEntity).getUniqueID();
/*     */ 
/*     */                           
/*     */                           Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(targetUUID);
/*     */                           
/*     */                           if (!maybeMob.isPresent()) {
/*     */                             return true;
/*     */                           }
/*     */                           
/*     */                           ActiveMob am = maybeMob.get();
/*     */                           
/*     */                           return am.hasFaction() ? am.getFaction().equals(paramString) : true;
/*     */                         } 
/*     */                         
/*     */                         return false;
/* 678 */                       } catch (Exception ex) {
/*     */                         ex.printStackTrace();
/*     */                         return false;
/*     */                       } 
/*     */                     }));
/*     */             }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 690 */     } catch (Exception ex) {
/* 691 */       MythicMobs.error("An error has occurred, enable debugging for a stack trace.");
/* 692 */       if (ConfigManager.debugLevel > 0)
/* 693 */         ex.printStackTrace(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\VolatileAIHandler_v1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */