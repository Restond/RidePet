/*     */ package lumine.xikage.mythicmobs.legacy;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicTimerSkill;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillCommandRadius;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillDamage;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillEjectPassenger;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillLightningAll;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillMountPlayer;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillRandomSkill;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillSkillRadiusAll;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class LegacySkillHandler {
/*     */   public static void ExecuteSkills(List<String> skills, Entity e, LivingEntity target, SkillTrigger trigger) {
/*  26 */     if (!ConfigManager.EnableLegacySkills)
/*  27 */       return;  if (SkillDamage.noloop == true)
/*  28 */       return;  if (skills == null)
/*  29 */       return;  for (String skill : skills)
/*  30 */       (new Object(e, trigger, skill, target))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  35 */         .runTask((Plugin)MythicMobs.inst()); 
/*     */   }
/*     */   
/*     */   public static void RunTimerSkills(long timer) {
/*  39 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/*     */       
/*  41 */       if (!am.isDead() && am.getEntity().isValid() && (am.getType()).usingTimers == true)
/*  42 */         ExecuteTimerSkills((am.getType()).legacyTimerSkills, am, timer); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void ExecuteTimerSkills(List<LegacyMythicTimerSkill> skills, ActiveMob am, long timer) {
/*  47 */     if (SkillDamage.noloop == true)
/*     */       return; 
/*  49 */     for (LegacyMythicTimerSkill skill : skills) {
/*  50 */       MythicMobs.inst().getTimingsHandler().markSkillNew("LEGACY:" + am.getType().getInternalName() + ":" + skill);
/*  51 */       if (timer % (skill.interval / ConfigManager.ClockInterval) == 0L) {
/*  52 */         LivingEntity target; if (am.getType().usesThreatTable()) {
/*  53 */           if (am.getThreatTable().inCombat()) {
/*  54 */             target = (LivingEntity)BukkitAdapter.adapt(am.getThreatTable().getTopThreatHolder());
/*     */           } else {
/*  56 */             target = null;
/*     */           }
/*     */         
/*  59 */         } else if (am.getEntity() instanceof Creature) {
/*  60 */           target = ((Creature)am.getEntity()).getTarget();
/*  61 */         } else if (am.getEntity() instanceof Player) {
/*  62 */           target = MythicUtil.getTargetedEntity((Player)am.getEntity());
/*     */         } else {
/*  64 */           target = null;
/*     */         } 
/*     */         
/*  67 */         LivingEntity tar = target;
/*  68 */         (new Object(am, skill, tar))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  73 */           .runTask((Plugin)MythicMobs.inst());
/*     */       } 
/*  75 */       MythicMobs.inst().getTimingsHandler().markSkillComplete("LEGACY:" + am.getType().getInternalName() + ":" + skill);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void ExecuteMetaSkills(List<String> list, Entity l, LivingEntity p, SkillTrigger trigger) {
/*  80 */     List<String> DelayedSkills = new ArrayList<>();
/*  81 */     boolean delayrest = false;
/*  82 */     int delayamount = 0;
/*     */ 
/*     */     
/*  85 */     for (String line : list) {
/*  86 */       if (!delayrest) {
/*  87 */         String[] split = line.split(" ");
/*  88 */         if (split[0].equals("delay")) {
/*  89 */           delayrest = true;
/*  90 */           delayamount = Integer.parseInt(split[1]);
/*     */           continue;
/*     */         } 
/*  93 */         ExecuteMobSkill(l, trigger, line, p, true);
/*     */         continue;
/*     */       } 
/*  96 */       DelayedSkills.add(line);
/*     */     } 
/*     */ 
/*     */     
/* 100 */     if (delayrest == true) {
/* 101 */       if (!l.getLocation().getChunk().isLoaded())
/* 102 */         return;  DelayedSkill ds = new DelayedSkill(DelayedSkills, l, p, trigger);
/* 103 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)ds, delayamount);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ExecuteMobSkill(Entity e, SkillTrigger trigger, String skill, LivingEntity p, boolean meta) {
/*     */     LivingEntity l;
/* 111 */     if (e instanceof LivingEntity) {
/* 112 */       l = (LivingEntity)e;
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/* 117 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)l));
/* 118 */     double bosshp = l.getHealth();
/*     */     
/* 120 */     String[] split = skill.split(" ");
/*     */     
/* 122 */     if (split[0] == null) {
/* 123 */       MythicMobs.debug(3, "-- AbstractSkill string is null?! Returning void.");
/*     */       
/*     */       return;
/*     */     } 
/* 127 */     if (split.length > 4) {
/* 128 */       if (split[split.length - 3].contains("~"))
/* 129 */       { if (!CheckTrigger(split[split.length - 3], trigger, l, skill, meta))
/*     */           return;  }
/* 131 */       else if (split[split.length - 2].contains("~"))
/* 132 */       { if (!CheckTrigger(split[split.length - 2], trigger, l, skill, meta))
/*     */           return;  }
/* 134 */       else if (split[split.length - 1].contains("~"))
/* 135 */       { if (!CheckTrigger(split[split.length - 1], trigger, l, skill, meta)) {
/*     */           return;
/*     */         } }
/* 138 */       else if (!CheckTrigger("~default", trigger, l, skill, meta)) { return; }
/*     */     
/* 140 */     } else if (split.length > 3) {
/* 141 */       if (split[split.length - 2].contains("~"))
/* 142 */       { if (!CheckTrigger(split[split.length - 2], trigger, l, skill, meta))
/*     */           return;  }
/* 144 */       else if (split[split.length - 1].contains("~"))
/* 145 */       { if (!CheckTrigger(split[split.length - 1], trigger, l, skill, meta)) {
/*     */           return;
/*     */         } }
/* 148 */       else if (!CheckTrigger("~default", trigger, l, skill, meta)) { return; }
/*     */     
/* 150 */     } else if (split.length > 2) {
/* 151 */       if (split[split.length - 1].contains("~")) {
/* 152 */         if (!CheckTrigger(split[split.length - 1], trigger, l, skill, meta)) {
/*     */           return;
/*     */         }
/* 155 */       } else if (!CheckTrigger("~default", trigger, l, skill, meta)) {
/*     */         return;
/*     */       } 
/* 158 */     } else if (!CheckTrigger("~default", trigger, l, skill, meta)) {
/*     */       return;
/*     */     } 
/*     */     
/* 162 */     if (split[split.length - 1].matches("[0-9]*[.]?[0-9]+")) {
/* 163 */       float chance = Float.parseFloat(split[split.length - 1]);
/* 164 */       if (MythicMobs.r.nextFloat() > chance) {
/* 165 */         MythicMobs.debug(3, "-- AbstractSkill chance " + chance + " failed! AbstractSkill not executed.");
/*     */         return;
/*     */       } 
/* 168 */       if (!CheckHealth(split[split.length - 2], am.getEntity(), bosshp, skill)) {
/* 169 */         MythicMobs.debug(3, "-- AbstractSkill health range check " + split[split.length - 2] + " false! AbstractSkill not executed.");
/*     */         
/*     */         return;
/*     */       } 
/* 173 */     } else if (!CheckHealth(split[split.length - 1], am.getEntity(), bosshp, skill)) {
/* 174 */       MythicMobs.debug(3, "-- AbstractSkill health range check " + split[split.length - 2] + " false! AbstractSkill not executed.");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     if (ConfigManager.apiUseSkillEvent != true || 
/* 205 */       am != null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     MythicMobs.debug(2, "Executing skill" + split[0] + " from string: " + skill);
/*     */     
/* 216 */     switch (split[0].toLowerCase()) { case "activatespawner":
/*     */       case "activate":
/*     */       case "spawner":
/* 219 */         SkillActivateSpawner.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "airungoalselector":
/*     */       case "aigoalselector":
/* 223 */         SkillAIRunGoalSelector.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "airuntargetselector":
/*     */       case "aitargetselector":
/* 227 */         SkillAIRunTargetSelector.ExecuteSkill(l, skill); return;
/*     */       case "bartimer":
/*     */       case "bartimermsg":
/*     */       case "bartimermessage":
/* 231 */         SkillBarTimerMessage.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "command":
/*     */       case "cmd":
/* 235 */         SkillCommand.ExecuteSkill(l, skill, p); return;
/*     */       case "ringcommand":
/*     */       case "cmdring":
/*     */       case "commandring":
/* 239 */         SkillCommandRing.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "consume":
/* 243 */         SkillConsume.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "damage":
/*     */       case "dmg":
/* 247 */         SkillDamage.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "damageall":
/* 251 */         SkillDamageAll.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "damageself":
/*     */       case "dmgself":
/* 255 */         SkillDamageSelf.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "disguise":
/* 259 */         SkillDisguise.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "dismount":
/* 263 */         SkillDismount.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "effect":
/* 267 */         SkillEffect.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "ejectpassenger":
/*     */       case "eject":
/* 271 */         SkillEjectPassenger.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "explosion":
/* 275 */         SkillExplosion.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "equip":
/* 279 */         SkillEquip.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "gcd":
/* 283 */         SkillGCD.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "forcepull":
/* 287 */         SkillForcePull.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "forcepullnear":
/* 291 */         SkillForcePullNear.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "healself":
/*     */       case "heal":
/* 295 */         SkillHealSelf.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "ignite":
/*     */       case "fire":
/* 299 */         SkillIgnite.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "igniteall":
/*     */       case "fireall":
/* 303 */         SkillIgniteAll.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "jump":
/* 307 */         SkillJump.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "leap":
/* 311 */         SkillLeap.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "lightning":
/* 315 */         SkillLightning.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "lightningall":
/* 319 */         SkillLightningAll.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "message":
/*     */       case "msg":
/* 323 */         SkillMessage.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "mount":
/* 327 */         SkillMount.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "mountplayer":
/* 331 */         SkillMountPlayer.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "newtarget":
/* 335 */         SkillTargetChange.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "potion":
/* 339 */         SkillPotion.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "potionboss":
/*     */       case "potionself":
/* 343 */         SkillPotionSelf.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "potionmobs":
/*     */       case "potionallies":
/* 347 */         SkillPotionMobs.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "pull":
/* 351 */         SkillPull.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "pullall":
/* 355 */         SkillPullAll.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "pushbutton":
/*     */       case "button":
/* 359 */         SkillPushButton.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "radiuscommand":
/*     */       case "radiuscmd":
/* 363 */         SkillCommandRadius.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "rally":
/* 367 */         SkillRally.ExecuteSkill(l, skill, p); return;
/*     */       case "randomskill":
/*     */       case "randompack":
/*     */       case "randommeta":
/* 371 */         SkillRandomSkill.ExecuteSkill(l, skill, p, trigger);
/*     */         return;
/*     */       case "removeself":
/*     */       case "remove":
/* 375 */         SkillRemoveSelf.ExecuteSkill(l);
/*     */         return;
/*     */       
/*     */       case "removemobs":
/* 379 */         SkillRemoveMobs.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "sethealth":
/* 383 */         SkillSetHealth.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "setmaxhealth":
/* 387 */         SkillSetMaxHealth.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "setname":
/* 391 */         SkillSetName.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "shootfireball":
/*     */       case "fireball":
/* 395 */         SkillShootFireball.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "shootpotion":
/* 399 */         SkillShootPotion.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "shootprojectile":
/*     */       case "projectile":
/* 403 */         SkillShootProjectile.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "shootskull":
/*     */       case "witherskull":
/* 407 */         SkillShootSkull.ExecuteSkill(l, skill, p); return;
/*     */       case "sendactionmessage": case "actionmessage":
/*     */       case "actionmsg":
/*     */       case "am":
/* 411 */         SkillSendActionMessage.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "sendtitle":
/*     */       case "title":
/* 415 */         SkillSendTitle.ExecuteSkill(l, skill, p); return;
/*     */       case "skill":
/*     */       case "pack":
/*     */       case "meta":
/* 419 */         SkillSkill.ExecuteSkill(l, skill, p, trigger);
/*     */         return;
/*     */       case "skillradius":
/*     */       case "radiusskill":
/* 423 */         SkillSkillRadius.ExecuteSkill(l, skill, trigger);
/*     */         return;
/*     */       case "skillradiusall":
/*     */       case "radiusskillall":
/* 427 */         SkillSkillRadiusAll.ExecuteSkill(l, skill, trigger);
/*     */         return;
/*     */       case "summonpassenger":
/*     */       case "spawnpassenger":
/* 431 */         SkillSpawnPassenger.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "setstance":
/*     */       case "stance":
/* 435 */         SkillSetStance.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "suicide":
/* 439 */         SkillSuicide.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "summon":
/*     */       case "swarm":
/* 443 */         SkillSummon.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "togglelever":
/* 447 */         SkillToggleLever.ExecuteSkill(l, skill);
/*     */         return;
/*     */       case "teleport":
/*     */       case "tp":
/* 451 */         SkillTeleport.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       case "teleportnear":
/*     */       case "tpnear":
/* 455 */         SkillTeleportNear.ExecuteSkill(l, skill, p); return;
/*     */       case "teleportlocation":
/*     */       case "tplocation":
/*     */       case "tploc":
/* 459 */         SkillTeleportLocation.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "throw":
/* 463 */         SkillThrow.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "throwall":
/* 467 */         SkillThrowAll.ExecuteSkill(l, skill);
/*     */         return;
/*     */       
/*     */       case "volley":
/* 471 */         SkillVolley.ExecuteSkill(l, skill, p);
/*     */         return;
/*     */       
/*     */       case "weather":
/* 475 */         SkillWeather.ExecuteSkill(l, skill);
/*     */         return; }
/*     */ 
/*     */     
/* 479 */     if (am == null || 
/* 480 */       ConfigManager.apiUseCustomSkillEvent == true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean CheckTrigger(String strTrigger, SkillTrigger trigger, LivingEntity l, String full, boolean meta) {
/* 491 */     MythicMobs.debug(3, "-- Performing Trigger Check for skill...");
/*     */     
/* 493 */     String search = strTrigger.substring(1);
/*     */     
/* 495 */     MythicMobs.debug(3, "---- Comparing " + search + " == " + trigger.toString());
/*     */     
/* 497 */     if (search.contains("onTimer")) return true;
/*     */     
/* 499 */     switch (search) {
/*     */       case "onSpawn":
/* 501 */         return (trigger == SkillTrigger.SPAWN);
/*     */       case "onDeath":
/* 503 */         return (trigger == SkillTrigger.DEATH);
/*     */       case "onCombat":
/* 505 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK);
/*     */       case "onDamaged": case "onHurt":
/* 507 */         return (trigger == SkillTrigger.DAMAGED);
/*     */       case "onAttack":
/* 509 */         return (trigger == SkillTrigger.ATTACK);
/*     */       case "onExplode":
/* 511 */         return (trigger == SkillTrigger.EXPLODE);
/*     */       case "onTeleport":
/* 513 */         return (trigger == SkillTrigger.TELEPORT);
/*     */       case "onKillPlayer": case "onPlayerKill":
/* 515 */         return (trigger == SkillTrigger.KILLPLAYER);
/*     */       case "onCombatDrop": case "onDropCombat":
/* 517 */         return (trigger == SkillTrigger.DROPCOMBAT);
/*     */       case "onInteract":
/* 519 */         return (trigger == SkillTrigger.INTERACT);
/*     */       case "default":
/* 521 */         if (meta == true) {
/* 522 */           return true;
/*     */         }
/* 524 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK || trigger == SkillTrigger.SPAWN || trigger == SkillTrigger.DEATH || trigger == SkillTrigger.TIMER);
/*     */     } 
/*     */     
/* 527 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean CheckHealth(String strHealth, AbstractEntity l, double currentbosshp, String full) {
/* 533 */     MythicMobs.debug(3, "-- Performing Health Check for skill...");
/*     */     
/* 535 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt(l));
/* 536 */     if (am == null) return true;
/*     */     
/* 538 */     String[] healthparts = strHealth.split(",");
/*     */     
/* 540 */     for (String health : healthparts) {
/*     */       double d1, bosshp;
/*     */       
/* 543 */       String healthT = health;
/* 544 */       String skillstr = full + "~" + health;
/*     */       
/* 546 */       healthT = healthT.replace("%", "");
/* 547 */       healthT = healthT.replace("<", "");
/* 548 */       healthT = healthT.replace(">", "");
/* 549 */       healthT = healthT.replace("=", "");
/* 550 */       healthT = healthT.replace("-", "");
/*     */       
/* 552 */       if (healthT.matches("[-+]?[0-9]*.?[0-9]+")) {
/* 553 */         d1 = Double.parseDouble(healthT);
/*     */       } else {
/* 555 */         return true;
/*     */       } 
/*     */       
/* 558 */       if (health.endsWith("%")) {
/* 559 */         bosshp = currentbosshp / l.getMaxHealth();
/* 560 */         d1 = Double.parseDouble(healthT) / 100.0D;
/*     */       } else {
/* 562 */         bosshp = currentbosshp / l.getMaxHealth();
/*     */         
/* 564 */         d1 = Double.parseDouble(healthT) / am.getType().getHealth();
/*     */       } 
/*     */       
/* 567 */       MythicMobs.debug(3, "-- orighealth = " + health + ", Boss hp = " + bosshp + "%, skill %hp=" + d1);
/*     */       
/* 569 */       if (health.startsWith(">")) {
/* 570 */         MythicMobs.debug(4, "---- Parsing GREATER THAN for skill...");
/* 571 */         health = health.replace(">", "");
/*     */         
/* 573 */         if (bosshp > d1) {
/* 574 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 578 */       else if (health.startsWith("=")) {
/*     */         
/* 580 */         MythicMobs.debug(4, "---- Parsing EQUAL TO for skill...");
/* 581 */         health = health.replace("=", "");
/*     */         
/* 583 */         if (bosshp <= d1 && l.getHealth() > d1 && !hasUsedSkill(skillstr, l)) {
/* 584 */           if (!am.getType().getRepeatAllSkills().booleanValue()) {
/* 585 */             MythicMobs.inst().getMobManager(); MobManager.setMetaData(l, skillstr, skillstr);
/*     */           } 
/* 587 */           return true;
/*     */         }
/*     */       
/*     */       }
/* 591 */       else if (health.startsWith("<")) {
/* 592 */         MythicMobs.debug(4, "---- Parsing LESS THAN for skill...");
/* 593 */         health = health.replace("<", "");
/*     */         
/* 595 */         if (bosshp < d1) {
/* 596 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 600 */       else if (health.contains("-")) {
/* 601 */         double hp1, hp2; MythicMobs.debug(4, "---- Parsing RANGE for skill...");
/* 602 */         String[] hps = health.split("-");
/*     */ 
/*     */ 
/*     */         
/* 606 */         if (hps[0].endsWith("%")) {
/* 607 */           hp1 = Double.parseDouble(hps[0].substring(0, hps[0].length() - 1)) / 100.0D;
/*     */         } else {
/* 609 */           hp1 = Double.parseDouble(hps[0].substring(0, hps[0].length())) / am.getType().getHealth();
/*     */         } 
/* 611 */         if (hps[1].endsWith("%")) {
/* 612 */           hp2 = Double.parseDouble(hps[1].substring(0, hps[1].length() - 1)) / 100.0D;
/*     */         } else {
/* 614 */           hp2 = Double.parseDouble(hps[1].substring(0, hps[1].length())) / am.getType().getHealth();
/*     */         } 
/*     */         
/* 617 */         MythicMobs.debug(4, "------ RANGE scaled to " + hp1 + "-" + hp2);
/*     */         
/* 619 */         if (hp1 > hp2) {
/* 620 */           if (bosshp > hp2 && bosshp < hp1) {
/* 621 */             return true;
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 626 */         else if (bosshp > hp1 && bosshp < hp2) {
/* 627 */           return true;
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 633 */         MythicMobs.debug(3, "-- AbstractSkill Health Check is invalid or not present? Assuming 100% up-time.");
/* 634 */         return true;
/*     */       } 
/*     */     } 
/* 637 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasUsedSkill(String full, AbstractEntity l) {
/* 641 */     List<MetadataValue> list = l.getBukkitEntity().getMetadata(full);
/*     */     
/* 643 */     for (MetadataValue mv : list) {
/* 644 */       if (mv.asString().equals(full)) {
/* 645 */         return true;
/*     */       }
/*     */     } 
/* 648 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\LegacySkillHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */