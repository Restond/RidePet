/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_8_R3.EntityCreature;
/*     */ import net.minecraft.server.v1_8_R3.EntityHuman;
/*     */ import net.minecraft.server.v1_8_R3.EntityInsentient;
/*     */ import net.minecraft.server.v1_8_R3.EntityIronGolem;
/*     */ import net.minecraft.server.v1_8_R3.EntityVillager;
/*     */ import net.minecraft.server.v1_8_R3.IMonster;
/*     */ import net.minecraft.server.v1_8_R3.IRangedEntity;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoal;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalArrowAttack;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalAvoidTarget;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalBreakDoor;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalEatTile;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalFleeSun;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalLeapAtTarget;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalMoveIndoors;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalMoveThroughVillage;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsRestriction;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsTarget;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalOpenDoor;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalRestrictOpenDoor;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalRestrictSun;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
/*     */ import org.bukkit.entity.Creature;
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
/*     */ public class VolatileAIHandler_v1_8_R3
/*     */   implements VolatileAIHandler
/*     */ {
/*     */   public VolatileAIHandler_v1_8_R3(VolatileCodeHandler handler) {}
/*     */   
/*     */   public void setTarget(LivingEntity entity, LivingEntity target) {
/*  65 */     if (target == null)
/*     */       return; 
/*  67 */     if (entity instanceof Creature) {
/*  68 */       ((Creature)entity).setTarget(target);
/*     */     } else {
/*     */       try {
/*  71 */         ((EntityInsentient)((CraftLivingEntity)entity).getHandle()).setGoalTarget(((CraftLivingEntity)target).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);
/*  72 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPathfinderGoals(LivingEntity entity, List<String> aiMods) {
/*  79 */     MythicMobs.debug(2, "aiGoalSelectorHandler activated...");
/*     */     try {
/*  81 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/*  83 */       Field goalsField = EntityInsentient.class.getDeclaredField("goalSelector");
/*  84 */       goalsField.setAccessible(true);
/*  85 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*     */       
/*  87 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       for (String str : aiMods) {
/*  93 */         String goal, data, data2; Field listField; List list; float speed; i++;
/*  94 */         String[] split = str.split(" ");
/*     */         
/*  96 */         if (split[0].matches("[0-9]*")) {
/*     */           
/*  98 */           j = Integer.parseInt(split[0]);
/*  99 */           goal = split[1];
/*     */           
/* 101 */           if (split.length > 2) {
/* 102 */             data = split[2];
/*     */           } else {
/* 104 */             data = null;
/*     */           } 
/* 106 */           if (split.length > 3) {
/* 107 */             data2 = split[3];
/*     */           } else {
/* 109 */             data2 = null;
/*     */           } 
/*     */         } else {
/* 112 */           j = i;
/* 113 */           goal = split[0];
/*     */           
/* 115 */           if (split.length > 1) {
/* 116 */             data = split[1];
/*     */           } else {
/* 118 */             data = null;
/*     */           } 
/* 120 */           if (split.length > 2) {
/* 121 */             data2 = split[2];
/*     */           } else {
/* 123 */             data2 = null;
/*     */           } 
/*     */         } 
/*     */         
/* 127 */         MythicMobs.debug(3, "#" + j + ". Adding GoalSelector " + goal + " with data X");
/*     */         
/* 129 */         switch (goal.toLowerCase()) { case "reset":
/*     */           case "clear":
/* 131 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/* 132 */             listField.setAccessible(true);
/* 133 */             list = (List)listField.get(goals);
/* 134 */             list.clear();
/* 135 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/* 136 */             listField.setAccessible(true);
/* 137 */             list = (List)listField.get(goals);
/* 138 */             list.clear();
/*     */ 
/*     */           
/*     */           case "arrowattack":
/* 142 */             if (e instanceof IRangedEntity) {
/* 143 */               goals.a(j, (PathfinderGoal)new PathfinderGoalArrowAttack((IRangedEntity)e, 1.0D, 20, 60, 15.0F));
/*     */             }
/*     */           
/*     */           case "breakdoor":
/* 147 */             goals.a(j, (PathfinderGoal)new PathfinderGoalBreakDoor(e));
/*     */           
/*     */           case "eatgrass":
/* 150 */             goals.a(j, (PathfinderGoal)new PathfinderGoalEatTile(e));
/*     */           case "fleegolems":
/*     */           case "runfromgolems":
/* 153 */             if (e instanceof EntityCreature) {
/* 154 */               float distance = 16.0F;
/* 155 */               double d = 1.2D;
/* 156 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 157 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 158 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityIronGolem.class, distance, 1.0D, d));
/*     */             } 
/*     */           case "fleeplayers":
/*     */           case "runfromplayers":
/* 162 */             if (e instanceof EntityCreature) {
/* 163 */               float distance = 16.0F;
/* 164 */               double d = 1.2D;
/* 165 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 166 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 167 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityHuman.class, distance, 1.0D, d));
/*     */             } 
/*     */           case "fleevillagers":
/*     */           case "runfromvillagers":
/* 171 */             if (e instanceof EntityCreature) {
/* 172 */               float distance = 16.0F;
/* 173 */               double d = 1.2D;
/* 174 */               if (data != null) distance = Float.valueOf(data).floatValue(); 
/* 175 */               if (data2 != null) d = Float.valueOf(data2).floatValue(); 
/* 176 */               goals.a(j, (PathfinderGoal)new PathfinderGoalAvoidTarget((EntityCreature)e, EntityVillager.class, distance, 1.0D, d));
/*     */             } 
/*     */           
/*     */           case "fleesun":
/* 180 */             if (e instanceof EntityCreature) {
/* 181 */               goals.a(j, (PathfinderGoal)new PathfinderGoalFleeSun((EntityCreature)e, 1.0D));
/*     */             }
/*     */           case "float":
/*     */           case "swim":
/* 185 */             goals.a(j, (PathfinderGoal)new PathfinderGoalFloat(e));
/*     */           
/*     */           case "gotolocation":
/*     */           case "goto":
/*     */             try {
/* 190 */               String[] sp = data.split(",");
/* 191 */               int x = Integer.valueOf(sp[0]).intValue();
/* 192 */               int y = Integer.valueOf(sp[1]).intValue();
/* 193 */               int z = Integer.valueOf(sp[2]).intValue();
/*     */               
/* 195 */               float f = 1.0F;
/* 196 */               if (data2 != null) {
/* 197 */                 f = Float.valueOf(data2).floatValue();
/*     */               }
/*     */               
/* 200 */               AbstractLocation l = new AbstractLocation(BukkitAdapter.adapt(entity.getWorld()), x, y, z);
/*     */               
/* 202 */               goals.a(j, (PathfinderGoal)new PathfinderGoalGoToLocation(this, e, l, f));
/* 203 */             } catch (Exception ex) {
/* 204 */               ex.printStackTrace();
/*     */             } 
/*     */           
/*     */           case "lookatplayers":
/* 208 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLookAtPlayer(e, EntityHuman.class, 5.0F, 1.0F));
/*     */           
/*     */           case "leapattarget":
/* 211 */             speed = 0.3F;
/* 212 */             if (data != null) {
/* 213 */               speed = Float.valueOf(data).floatValue();
/*     */             }
/* 215 */             goals.a(j, (PathfinderGoal)new PathfinderGoalLeapAtTarget(e, speed));
/*     */           
/*     */           case "meleeattack":
/* 218 */             if (e instanceof EntityCreature) {
/* 219 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMeleeAttack((EntityCreature)e, 1.0D, true));
/*     */             }
/*     */           
/*     */           case "spiderattack":
/* 223 */             if (e instanceof EntityCreature) {
/* 224 */               goals.a(j, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack((EntityCreature)e, EntityCreature.class));
/*     */             }
/*     */           
/*     */           case "moveindoors":
/* 228 */             if (e instanceof EntityCreature) {
/* 229 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveIndoors((EntityCreature)e));
/*     */             }
/*     */           
/*     */           case "movethroughvillage":
/* 233 */             if (e instanceof EntityCreature) {
/* 234 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveThroughVillage((EntityCreature)e, 0.6D, true));
/*     */             }
/*     */           
/*     */           case "movetowardsrestriction":
/* 238 */             if (e instanceof EntityCreature) {
/* 239 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)e, 0.6D));
/*     */             }
/*     */           
/*     */           case "movetowardstarget":
/* 243 */             if (e instanceof EntityCreature) {
/* 244 */               goals.a(j, (PathfinderGoal)new PathfinderGoalMoveTowardsTarget((EntityCreature)e, 0.9D, 32.0F));
/*     */             }
/*     */           case "opendoor":
/*     */           case "opendoors":
/* 248 */             goals.a(j, (PathfinderGoal)new PathfinderGoalOpenDoor(e, true));
/*     */           case "randomlookaround":
/*     */           case "lookaround":
/* 251 */             goals.a(j, (PathfinderGoal)new PathfinderGoalRandomLookaround(e));
/*     */           
/*     */           case "randomstroll":
/* 254 */             if (e instanceof EntityCreature) {
/* 255 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)e, 1.0D));
/*     */             }
/*     */           case "restrictopendoor":
/*     */           case "closedoors":
/* 259 */             if (e instanceof EntityCreature) {
/* 260 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictOpenDoor((EntityCreature)e));
/*     */             }
/*     */           
/*     */           case "restrictsun":
/* 264 */             if (e instanceof EntityCreature) {
/* 265 */               goals.a(j, (PathfinderGoal)new PathfinderGoalRestrictSun((EntityCreature)e));
/*     */             } }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 272 */     } catch (Exception ex) {
/* 273 */       MythicMobs.error("An error occurred while adding an AIGoalSelector, enable debugging for a stack trace.");
/* 274 */       if (ConfigManager.debugLevel > 0) {
/* 275 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetGoals(LivingEntity entity, List<String> aiMods) {
/* 283 */     MythicMobs.debug(2, "aiTargetSelectorHandler activated...");
/*     */     try {
/* 285 */       EntityInsentient e = (EntityInsentient)((CraftLivingEntity)entity).getHandle();
/*     */       
/* 287 */       Field goalsField = EntityInsentient.class.getDeclaredField("targetSelector");
/* 288 */       goalsField.setAccessible(true);
/* 289 */       PathfinderGoalSelector goals = (PathfinderGoalSelector)goalsField.get(e);
/*     */       
/* 291 */       int i = 0, j = 0;
/*     */ 
/*     */ 
/*     */       
/* 295 */       for (String str : aiMods) {
/* 296 */         String goal, data; Field listField; List list; i++;
/* 297 */         String[] split = str.split(" ");
/*     */         
/* 299 */         if (split[0].matches("[0-9]*")) {
/*     */           
/* 301 */           j = Integer.parseInt(split[0]);
/* 302 */           goal = split[1];
/*     */           
/* 304 */           if (split.length > 2) {
/* 305 */             data = split[2];
/*     */           } else {
/* 307 */             data = "";
/*     */           } 
/*     */         } else {
/* 310 */           j = i;
/* 311 */           goal = split[0];
/*     */           
/* 313 */           if (split.length > 1) {
/* 314 */             data = split[1];
/*     */           } else {
/* 316 */             data = "";
/*     */           } 
/*     */         } 
/*     */         
/* 320 */         MythicMobs.debug(2, "#" + j + ". Adding TargetSelector " + goal + " with data " + data);
/*     */         
/* 322 */         switch (goal.toLowerCase()) { case "reset":
/*     */           case "clear":
/* 324 */             listField = PathfinderGoalSelector.class.getDeclaredField("b");
/* 325 */             listField.setAccessible(true);
/* 326 */             list = (List)listField.get(goals);
/* 327 */             list.clear();
/* 328 */             listField = PathfinderGoalSelector.class.getDeclaredField("c");
/* 329 */             listField.setAccessible(true);
/* 330 */             list = (List)listField.get(goals);
/* 331 */             list.clear();
/*     */           case "hurtbytarget":
/*     */           case "damager":
/*     */           case "attacker":
/* 335 */             if (e instanceof EntityCreature) {
/* 336 */               goals.a(j, (PathfinderGoal)new PathfinderGoalHurtByTarget((EntityCreature)e, false, new Class[0]));
/*     */             }
/*     */           case "ownerhurttarget":
/*     */           case "ownertarget":
/* 340 */             if (e instanceof net.minecraft.server.v1_8_R3.EntityTameableAnimal) {
/* 341 */               goals.a(j, (PathfinderGoal)new PathfinderGoalHurtByTarget((EntityCreature)e, false, new Class[0]));
/*     */             }
/*     */           
/*     */           case "monsters":
/* 345 */             if (e instanceof EntityCreature) {
/* 346 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*     */             }
/*     */           
/*     */           case "players":
/* 350 */             if (e instanceof EntityCreature) {
/* 351 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityHuman.class, true));
/*     */             }
/*     */           
/*     */           case "villagers":
/* 355 */             if (e instanceof EntityCreature) {
/* 356 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityVillager.class, false));
/*     */             }
/*     */           case "iron_golems":
/*     */           case "golems":
/* 360 */             if (e instanceof EntityCreature) {
/* 361 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityCreature)e, EntityIronGolem.class, false));
/*     */             }
/*     */           
/*     */           case "otherfaction":
/* 365 */             if (e instanceof EntityCreature) {
/* 366 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true));
/* 367 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityHuman.class, 0, true));
/*     */             } 
/*     */           
/*     */           case "otherfactionmonsters":
/* 371 */             if (e instanceof EntityCreature) {
/* 372 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityInsentient.class, 0, true, false, IMonster.d));
/*     */             }
/*     */           
/*     */           case "otherfactionvillagers":
/* 376 */             if (e instanceof EntityCreature) {
/* 377 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableOtherFactionTarget(this, (EntityCreature)e, EntityVillager.class, 0, false));
/*     */             }
/*     */           
/*     */           case "specificfaction":
/* 381 */             if (e instanceof EntityCreature) {
/* 382 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, false));
/*     */             }
/*     */           
/*     */           case "specificfactionmonsters":
/* 386 */             if (e instanceof EntityCreature) {
/* 387 */               goals.a(j, (PathfinderGoal)new PathfinderGoalNearestAttackableSpecificFactionTarget(this, (EntityCreature)e, EntityInsentient.class, data, 0, true, false, IMonster.d));
/*     */             } }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 394 */     } catch (Exception ex) {
/* 395 */       MythicMobs.error("An error has occurred, enable debugging for a stack trace.");
/* 396 */       if (ConfigManager.debugLevel > 0)
/* 397 */         ex.printStackTrace(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileAIHandler_v1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */