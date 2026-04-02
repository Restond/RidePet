/*      */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2;
/*      */ 
/*      */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*      */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*      */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*      */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*      */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ai.WrappedPathfinder;
/*      */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*      */ import io.lumine.xikage.mythicmobs.util.reflections.VersionCompliantReflections;
/*      */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*      */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*      */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.PathfinderHolder;
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import net.minecraft.server.v1_13_R2.EntityCreature;
/*      */ import net.minecraft.server.v1_13_R2.EntityHuman;
/*      */ import net.minecraft.server.v1_13_R2.EntityInsentient;
/*      */ import net.minecraft.server.v1_13_R2.EntityIronGolem;
/*      */ import net.minecraft.server.v1_13_R2.EntityMonster;
/*      */ import net.minecraft.server.v1_13_R2.EntityVillager;
/*      */ import net.minecraft.server.v1_13_R2.EntityWolf;
/*      */ import net.minecraft.server.v1_13_R2.IMonster;
/*      */ import net.minecraft.server.v1_13_R2.IRangedEntity;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoal;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalArrowAttack;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalAvoidTarget;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalBowShoot;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalBreakDoor;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalEatTile;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalFleeSun;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalFloat;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalHurtByTarget;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalLeapAtTarget;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalLookAtPlayer;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalMoveIndoors;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalMoveThroughVillage;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalMoveTowardsRestriction;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalMoveTowardsTarget;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalNearestAttackableTarget;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalOpenDoor;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalPanic;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalRandomFly;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalRandomLookaround;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalRandomStroll;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalRestrictOpenDoor;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalRestrictSun;
/*      */ import net.minecraft.server.v1_13_R2.PathfinderGoalSelector;
/*      */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity;
/*      */ import org.bukkit.entity.Creature;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class VolatileAIHandler_v1_13_R2
/*      */   implements VolatileAIHandler
/*      */ {
/*   82 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_GOALS = new ConcurrentHashMap<>();
/*   83 */   private final Map<String, Class<? extends PathfinderAdapter>> AI_TARGETS = new ConcurrentHashMap<>();
/*      */   
/*      */   public VolatileAIHandler_v1_13_R2(VolatileCodeHandler handler) {
/*   86 */     Set<Class<?>> customGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*      */     
/*   88 */     for (Class<?> clazz : customGoalClasses) {
/*      */       try {
/*   90 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*   91 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*      */         
/*   93 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*   94 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/*   95 */           for (String alias : aliases) {
/*   96 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*      */           }
/*      */         } 
/*   99 */       } catch (Exception ex) {
/*  100 */         MythicLogger.error("Failed to load custom AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*      */       } 
/*      */     } 
/*      */     
/*  104 */     Set<Class<?>> wrappedGoalClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.goals")).getTypesAnnotatedWith(MythicAIGoal.class);
/*      */     
/*  106 */     for (Class<?> clazz : wrappedGoalClasses) {
/*      */       try {
/*  108 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*  109 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*      */         
/*  111 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*  112 */           this.AI_GOALS.put(name.toUpperCase(), clazz);
/*  113 */           for (String alias : aliases) {
/*  114 */             this.AI_GOALS.put(alias.toUpperCase(), clazz);
/*      */           }
/*      */         } 
/*  117 */       } catch (Exception ex) {
/*  118 */         MythicLogger.error("Failed to load wrapped AI goal {0}", new Object[] { clazz.getCanonicalName() });
/*      */       } 
/*      */     } 
/*      */     
/*  122 */     Set<Class<?>> customTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.mobs.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*      */     
/*  124 */     for (Class<?> clazz : customTargeterClasses) {
/*      */       try {
/*  126 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*  127 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*      */         
/*  129 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*  130 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/*  131 */           for (String alias : aliases) {
/*  132 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*      */           }
/*      */         } 
/*  135 */       } catch (Exception ex) {
/*  136 */         MythicLogger.error("Failed to load custom AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*      */       } 
/*      */     } 
/*      */     
/*  140 */     Set<Class<?>> wrappedTargeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.ai.targeters")).getTypesAnnotatedWith(MythicAIGoal.class);
/*      */     
/*  142 */     for (Class<?> clazz : wrappedTargeterClasses) {
/*      */       try {
/*  144 */         String name = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).name();
/*  145 */         String[] aliases = ((MythicAIGoal)clazz.<MythicAIGoal>getAnnotation(MythicAIGoal.class)).aliases();
/*      */         
/*  147 */         if (PathfinderAdapter.class.isAssignableFrom(clazz)) {
/*  148 */           this.AI_TARGETS.put(name.toUpperCase(), clazz);
/*  149 */           for (String alias : aliases) {
/*  150 */             this.AI_TARGETS.put(alias.toUpperCase(), clazz);
/*      */           }
/*      */         } 
/*  153 */       } catch (Exception ex) {
/*  154 */         MythicLogger.error("Failed to load wrapped AI targeter {0}", new Object[] { clazz.getCanonicalName() });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTarget(LivingEntity entity, LivingEntity target) {
/*  162 */     if (entity instanceof Creature) {
/*      */       try {
/*  164 */         ((Creature)entity).setTarget(target);
/*  165 */       } catch (Exception exception) {}
/*      */     } else {
/*      */       try {
/*  168 */         ((EntityInsentient)((CraftLivingEntity)entity).getHandle()).setGoalTarget(((CraftLivingEntity)target).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);
/*  169 */       } catch (Exception exception) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPathfinderGoals(LivingEntity entity, List<String> aiMods) {
/*  176 */     MythicMobs.debug(2, "aiGoalSelectorHandler activated...");
/*      */     try {
/*  178 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*      */       
/*  180 */       Field goalsField = EntityInsentient.class.getDeclaredField("goalSelector");
/*  181 */       goalsField.setAccessible(true);
/*  182 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*      */       
/*  184 */       int i = 0, j = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  190 */       for (String str : aiMods) {
/*  191 */         String data, data2; Field listField; Set list; float speed; i++;
/*  192 */         String[] split = str.split(" ");
/*      */         
/*  194 */         if (split[0].matches("[0-9]*")) {
/*      */           
/*  196 */           j = Integer.parseInt(split[0]);
/*  197 */           goal = split[1];
/*      */           
/*  199 */           if (split.length > 2) {
/*  200 */             data = split[2];
/*      */           } else {
/*  202 */             data = null;
/*      */           } 
/*  204 */           if (split.length > 3) {
/*  205 */             data2 = split[3];
/*      */           } else {
/*  207 */             data2 = null;
/*      */           } 
/*      */         } else {
/*  210 */           j = i;
/*  211 */           goal = split[0];
/*      */           
/*  213 */           if (split.length > 1) {
/*  214 */             data = split[1];
/*      */           } else {
/*  216 */             data = null;
/*      */           } 
/*  218 */           if (split.length > 2) {
/*  219 */             data2 = split[2];
/*      */           } else {
/*  221 */             data2 = null;
/*      */           } 
/*      */         } 
/*  224 */         MythicLineConfig mlc = new MythicLineConfig(goal);
/*  225 */         String goal = mlc.getKey();
/*      */ 
/*      */         
/*  228 */         if (this.AI_GOALS.containsKey(goal.toUpperCase())) {
/*  229 */           Class<? extends PathfinderAdapter> clazz = this.AI_GOALS.get(goal.toUpperCase());
/*      */           
/*      */           try {
/*  232 */             if (clazz.isAssignableFrom(Pathfinder.class)) {
/*  233 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/*  234 */               goals.a(j, createAIGoal(pathfinder)); continue;
/*      */             } 
/*  236 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/*  237 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*      */             
/*  239 */             if (holder.isValid()) {
/*  240 */               PathfinderGoal pathfinder = holder.create();
/*  241 */               goals.a(j, pathfinder); continue;
/*      */             } 
/*  243 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*      */ 
/*      */             
/*      */             continue;
/*  247 */           } catch (Exception|Error ex) {
/*  248 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/*  249 */             ex.printStackTrace();
/*      */           } 
/*      */         } 
/*      */         
/*  253 */         switch (goal.toLowerCase()) { case "reset":
/*      */           case "clear":
/*  255 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/*  256 */             listField.setAccessible(true);
/*  257 */             list = (Set)listField.get(goals);
/*  258 */             list.clear();
/*  259 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/*  260 */             listField.setAccessible(true);
/*  261 */             list = (Set)listField.get(goals);
/*  262 */             list.clear();
/*      */ 
/*      */           
/*      */           case "arrowattack":
/*  266 */             if (e instanceof IRangedEntity)
/*  267 */               goals.a(j, (PathfinderGoal)new PathfinderGoalArrowAttack((IRangedEntity)e, 1.0D, 20, 60, 15.0F)); 
/*      */           case "skeletonbowattack":
/*      */           case "bowshoot":
/*      */           case "bowmaster":
/*  271 */             if (e instanceof IRangedEntity) {
/*  272 */               goals.a(j, (PathfinderGoal)new PathfinderGoalBowShoot((EntityMonster)e, 1.0D, 20, 15.0F));
/*      */             }
/*      */           
/*      */           case "breakdoor":
/*  276 */             goals.a(j, (PathfinderGoal)new PathfinderGoalBreakDoor(e));
/*      */           
/*      */           case "eatgrass":
/*  279 */             goals.a(j, (PathfinderGoal)new PathfinderGoalEatTile(e));
/*      */           case "fleegolems":
/*      */           case "runfromgolems":
/*  282 */             if (e instanceof EntityCreature) {
/*  283 */               float distance = 16.0F;
/*  284 */               double d = 1.2D;
/*  285 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  286 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  287 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityIronGolem.class, distance, 1.0D, d));
/*      */             } 
/*      */           
/*      */           case "fleeplayers":
/*      */           case "runfromplayers":
/*  292 */             if (e instanceof EntityCreature) {
/*  293 */               float distance = 16.0F;
/*  294 */               double d = 1.2D;
/*  295 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  296 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  297 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityHuman.class, distance, 1.0D, d));
/*      */             } 
/*      */           
/*      */           case "fleemonsters":
/*      */           case "runfrommonsters":
/*  302 */             if (e instanceof EntityCreature) {
/*  303 */               float distance = 16.0F;
/*  304 */               double d = 1.2D;
/*  305 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  306 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  307 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityMonster.class, distance, 1.0D, d));
/*      */             } 
/*      */           
/*      */           case "fleeotherfaction":
/*      */           case "runfromotherfaction":
/*  312 */             if (e instanceof EntityCreature) {
/*  313 */               float distance = 16.0F;
/*  314 */               double d = 1.2D;
/*  315 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  316 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  317 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityInsentient.class, distance, 1.0D, d, targetEntity -> {
/*      */                       try {
/*      */                         UUID mobUUID = paramEntityInsentient.getUniqueID();
/*      */ 
/*      */                         
/*      */                         UUID targetUUID = ((EntityInsentient)targetEntity).getUniqueID();
/*      */                         
/*      */                         Optional<ActiveMob> maybeMob = MythicMobs.inst().getMobManager().getActiveMob(targetUUID);
/*      */                         
/*      */                         if (!maybeMob.isPresent()) {
/*      */                           return true;
/*      */                         }
/*      */                         
/*      */                         ActiveMob am = maybeMob.get();
/*      */                         
/*      */                         return am.hasFaction() ? (!((ActiveMob)MythicMobs.inst().getMobManager().getActiveMob(mobUUID).get()).getFaction().equals(am.getFaction())) : true;
/*  333 */                       } catch (Exception ex) {
/*      */                         ex.printStackTrace();
/*      */                         return false;
/*      */                       } 
/*      */                     }));
/*      */             } 
/*      */           
/*      */           case "fleevillagers":
/*      */           case "runfromvillagers":
/*  342 */             if (e instanceof EntityCreature) {
/*  343 */               float distance = 16.0F;
/*  344 */               double d = 1.2D;
/*  345 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  346 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  347 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityVillager.class, distance, 1.0D, d));
/*      */             } 
/*      */           
/*      */           case "fleewolves":
/*      */           case "runfromwolves":
/*  352 */             if (e instanceof EntityCreature) {
/*  353 */               float distance = 6.0F;
/*  354 */               double d = 1.2D;
/*  355 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/*  356 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/*  357 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityWolf.class, distance, 1.0D, d));
/*      */             } 
/*      */ 
/*      */           
/*      */           case "fleesun":
/*  362 */             if (e instanceof EntityCreature) {
/*  363 */               goals.a(j, (PathfinderGoal)new PathfinderGoalFleeSun((EntityCreature)e, 1.0D));
/*      */             }
/*      */           
/*      */           case "float":
/*      */           case "swim":
/*  368 */             goals.a(j, (PathfinderGoal)new PathfinderGoalFloat(e));
/*      */           
/*      */           case "gotolocation":
/*      */           case "goto":
/*      */             try {
/*  373 */               String[] sp = data.split(",");
/*  374 */               int x = Integer.valueOf(sp[0]).intValue();
/*  375 */               int y = Integer.valueOf(sp[1]).intValue();
/*  376 */               int z = Integer.valueOf(sp[2]).intValue();
/*      */               
/*  378 */               float f = 1.0F;
/*  379 */               if (data2 != null) {
/*  380 */                 f = Float.valueOf(data2).floatValue();
/*      */               }
/*      */               
/*  383 */               AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/*      */               
/*  385 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToLocation(this, e, l, f));
/*  386 */             } catch (Exception ex) {
/*  387 */               ex.printStackTrace();
/*      */             } 
/*      */           
/*      */           case "gotoowner":
/*      */             try {
/*  392 */               float distance = 4.0F;
/*  393 */               if (data != null) {
/*  394 */                 distance = Float.valueOf(data2).floatValue();
/*      */               }
/*  396 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToOwner(this, e, (distance * distance)));
/*  397 */             } catch (Exception ex) {
/*  398 */               ex.printStackTrace();
/*      */             } 
/*      */           
/*      */           case "gotoparent":
/*      */             try {
/*  403 */               float distance = 4.0F;
/*  404 */               if (data != null) {
/*  405 */                 distance = Float.valueOf(data2).floatValue();
/*      */               }
/*  407 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToParent(this, e, (distance * distance)));
/*  408 */             } catch (Exception ex) {
/*  409 */               ex.printStackTrace();
/*      */             } 
/*      */           
/*      */           case "lookatplayers":
/*  413 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLookAtPlayer(e, EntityHuman.class, 5.0F, 1.0F));
/*      */           
/*      */           case "leapattarget":
/*  416 */             speed = 0.3F;
/*  417 */             if (data != null) {
/*  418 */               speed = Float.valueOf(data).floatValue();
/*      */             }
/*  420 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLeapAtTarget(e, speed));
/*      */           
/*      */           case "spiderattack":
/*  423 */             if (e instanceof EntityCreature) {
/*  424 */               goals.a(j, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack((EntityCreature)e, EntityCreature.class));
/*      */             }
/*      */           
/*      */           case "moveindoors":
/*  428 */             if (e instanceof EntityCreature) {
/*  429 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveIndoors((EntityCreature)e));
/*      */             }
/*      */           
/*      */           case "movethroughvillage":
/*  433 */             if (e instanceof EntityCreature) {
/*  434 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveThroughVillage((EntityCreature)e, 0.6D, true));
/*      */             }
/*      */           
/*      */           case "movetowardsrestriction":
/*  438 */             if (e instanceof EntityCreature) {
/*  439 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)e, 0.6D));
/*      */             }
/*      */           
/*      */           case "movetowardstarget":
/*  443 */             if (e instanceof EntityCreature) {
/*  444 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsTarget((EntityCreature)e, 0.9D, 32.0F));
/*      */             }
/*      */           case "opendoor":
/*      */           case "opendoors":
/*  448 */             goals.a(j, (PathfinderGoal)new PathfinderGoalOpenDoor(e, true));
/*      */           
/*      */           case "panic":
/*  451 */             if (e instanceof EntityCreature) {
/*  452 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPanic((EntityCreature)e, 1.25D));
/*      */             }
/*      */ 
/*      */           
/*      */           case "patrol":
/*      */           case "patrolroute":
/*      */             try {
/*  459 */               List<AbstractLocation> points = new ArrayList<>();
/*      */               
/*  461 */               String[] split2 = data.split(";");
/*      */               
/*  463 */               for (String s : split2) {
/*  464 */                 String[] split3 = s.split(",");
/*      */                 
/*  466 */                 int x = Integer.valueOf(split3[0]).intValue();
/*  467 */                 int y = Integer.valueOf(split3[1]).intValue();
/*  468 */                 int z = Integer.valueOf(split3[2]).intValue();
/*      */                 
/*  470 */                 AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/*  471 */                 points.add(l);
/*      */               } 
/*      */               
/*  474 */               float speed2 = 1.0F;
/*  475 */               if (data2 != null) {
/*  476 */                 speed = Float.valueOf(data2).floatValue();
/*      */               }
/*  478 */               goals.a(j, (PathfinderGoal)new PathfinderGoalPatrolRoute(this, e, points, speed2));
/*  479 */             } catch (Exception ex) {
/*  480 */               ex.printStackTrace();
/*      */             } 
/*      */           
/*      */           case "randomlookaround":
/*      */           case "lookaround":
/*  485 */             goals.a(j, (PathfinderGoal)new PathfinderGoalRandomLookaround(e));
/*      */           
/*      */           case "randomstroll":
/*  488 */             if (e instanceof EntityCreature) {
/*  489 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)e, 1.0D));
/*      */             }
/*      */           
/*      */           case "randomfly":
/*  493 */             if (e instanceof EntityCreature) {
/*  494 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRandomFly((EntityCreature)e, 1.0D));
/*      */             }
/*      */           case "restrictopendoor":
/*      */           case "closedoors":
/*  498 */             if (e instanceof EntityCreature) {
/*  499 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictOpenDoor((EntityCreature)e));
/*      */             }
/*      */           
/*      */           case "restrictsun":
/*  503 */             if (e instanceof EntityCreature) {
/*  504 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictSun((EntityCreature)e));
/*      */             } }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*  511 */     } catch (Exception ex) {
/*  512 */       MythicMobs.error("An error occurred while adding an AIGoalSelector, enable debugging for a stack trace.");
/*  513 */       if (ConfigManager.debugLevel > 0) {
/*  514 */         ex.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTargetGoals(LivingEntity entity, List<String> aiMods) {
/*  522 */     MythicMobs.debug(2, "aiTargetSelectorHandler activated...");
/*      */     try {
/*  524 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*      */       
/*  526 */       Field goalsField = EntityInsentient.class.getDeclaredField("targetSelector");
/*  527 */       goalsField.setAccessible(true);
/*  528 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*      */       
/*  530 */       int i = 0, j = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  536 */       for (String str : aiMods) {
/*  537 */         String data; Field listField; Set list; i++;
/*  538 */         String[] split = str.split(" ");
/*      */         
/*  540 */         if (split[0].matches("[0-9]*")) {
/*      */           
/*  542 */           j = Integer.parseInt(split[0]);
/*  543 */           goal = split[1];
/*      */           
/*  545 */           if (split.length > 2) {
/*  546 */             data = split[2];
/*      */           } else {
/*  548 */             data = "";
/*      */           } 
/*      */         } else {
/*  551 */           j = i;
/*  552 */           goal = split[0];
/*      */           
/*  554 */           if (split.length > 1) {
/*  555 */             data = split[1];
/*      */           } else {
/*  557 */             data = "";
/*      */           } 
/*      */         } 
/*      */         
/*  561 */         MythicLineConfig mlc = new MythicLineConfig(goal);
/*  562 */         String goal = mlc.getKey();
/*      */ 
/*      */         
/*  565 */         if (this.AI_TARGETS.containsKey(goal.toUpperCase())) {
/*  566 */           Class<? extends PathfinderAdapter> clazz = this.AI_TARGETS.get(goal.toUpperCase());
/*      */           
/*      */           try {
/*  569 */             if (clazz.isAssignableFrom(Pathfinder.class)) {
/*  570 */               Pathfinder pathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/*  571 */               goals.a(j, createAIGoal(pathfinder)); continue;
/*      */             } 
/*  573 */             WrappedPathfinder wrappedPathfinder = clazz.getConstructor(new Class[] { AbstractEntity.class, String.class, MythicLineConfig.class }).newInstance(new Object[] { BukkitAdapter.adapt((Entity)entity), str, mlc });
/*  574 */             PathfinderHolder holder = (PathfinderHolder)wrappedPathfinder;
/*      */             
/*  576 */             if (holder.isValid()) {
/*  577 */               PathfinderGoal pathfinder = holder.create();
/*  578 */               goals.a(j, pathfinder); continue;
/*      */             } 
/*  580 */             MythicLogger.error("AI pathfinder {0} is not valid for this mob type", new Object[] { goal });
/*      */ 
/*      */             
/*      */             continue;
/*  584 */           } catch (Exception|Error ex) {
/*  585 */             MythicLogger.error("Failed to construct AI pathfinder {0}", new Object[] { goal });
/*  586 */             ex.printStackTrace();
/*      */           } 
/*      */         } 
/*      */         
/*  590 */         switch (goal.toLowerCase()) { case "reset":
/*      */           case "clear":
/*  592 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/*  593 */             listField.setAccessible(true);
/*  594 */             list = (Set)listField.get(goals);
/*  595 */             list.clear();
/*  596 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/*  597 */             listField.setAccessible(true);
/*  598 */             list = (Set)listField.get(goals);
/*  599 */             list.clear();
/*      */           
/*      */           case "ownerhurttarget":
/*      */           case "ownertarget":
/*  603 */             if (e instanceof net.minecraft.server.v1_13_R2.EntityTameableAnimal) {
/*  604 */               goals.a(j, (PathfinderGoal)new PathfinderGoalHurtByTarget((EntityCreature)e, false, new Class[0]));
/*      */             }
/*      */           
/*      */           case "monsters":
/*  608 */             if (e instanceof EntityCreature) {
/*  609 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*      */             }
/*      */           
/*      */           case "players":
/*  613 */             if (e instanceof EntityCreature) {
/*  614 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityHuman.class, true));
/*      */             }
/*      */           
/*      */           case "villagers":
/*  618 */             if (e instanceof EntityCreature) {
/*  619 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityVillager.class, false));
/*      */             }
/*      */           case "iron_golems":
/*      */           case "golems":
/*  623 */             if (e instanceof EntityCreature) {
/*  624 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityIronGolem.class, false));
/*      */             }
/*      */           
/*      */           case "otherfaction":
/*  628 */             if (e instanceof EntityCreature) {
/*  629 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true));
/*  630 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityHuman.class, 0, true));
/*      */             } 
/*      */           
/*      */           case "otherfactionmonsters":
/*  634 */             if (e instanceof EntityCreature) {
/*  635 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*      */             }
/*      */           
/*      */           case "otherfactionvillagers":
/*  639 */             if (e instanceof EntityCreature) {
/*  640 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityVillager.class, 0, false));
/*      */             }
/*      */           
/*      */           case "specificfaction":
/*  644 */             if (e instanceof EntityCreature) {
/*  645 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, false));
/*      */             }
/*      */           
/*      */           case "specificfactionmonsters":
/*  649 */             if (e instanceof EntityCreature) {
/*  650 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, true, false, IMonster.d));
/*      */             } }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*  657 */     } catch (Exception ex) {
/*  658 */       MythicMobs.error("An error has occurred, enable debugging for a stack trace.");
/*  659 */       if (ConfigManager.debugLevel > 0) {
/*  660 */         ex.printStackTrace();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PathfinderGoal createAIGoal(Pathfinder goal) {
/* 1016 */     return (PathfinderGoal)new CustomAIAdapter_v1_13_R2(this, goal);
/*      */   }
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\VolatileAIHandler_v1_13_R2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */