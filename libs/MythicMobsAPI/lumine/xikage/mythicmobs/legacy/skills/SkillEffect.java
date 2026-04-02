/*     */ package lumine.xikage.mythicmobs.legacy.skills;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectAtom;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectDNA;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectFirework;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectFlames;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectLightning;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectRadiusFireworks;
/*     */ import io.lumine.xikage.mythicmobs.legacy.skills.EffectSmoke;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class SkillEffect {
/*     */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/*  25 */     MythicMobs.debug(2, "Running Effect! SkillString=" + skill);
/*  26 */     String[] base = skill.split(" ");
/*     */     
/*  28 */     String location = base[1];
/*  29 */     String effect = base[2];
/*  30 */     String effectdata = null;
/*     */     
/*  32 */     if (base.length > 3) {
/*  33 */       effectdata = base[3];
/*     */     }
/*     */     
/*  36 */     if (location.equals("boss") || location.equals("self")) {
/*  37 */       EffectHandler(l, l.getLocation(), l, effect, effectdata);
/*  38 */     } else if (location.equals("target")) {
/*     */       
/*  40 */       Location tl = null;
/*     */       
/*  42 */       if (target == null) {
/*  43 */         if (l instanceof Creature) {
/*  44 */           target = ((Creature)l).getTarget();
/*     */           
/*  46 */           if (target != null) {
/*  47 */             tl = target.getLocation();
/*     */           }
/*  49 */         } else if (l instanceof Player) {
/*  50 */           tl = ((Player)l).getTargetBlock((HashSet)null, 64).getLocation();
/*     */         } else {
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*  56 */         tl = target.getLocation();
/*     */       } 
/*     */       
/*  59 */       if (tl == null)
/*     */         return; 
/*  61 */       EffectHandler(l, tl, target, effect, effectdata);
/*  62 */     } else if (location.contains("playersinradius")) {
/*  63 */       int radius = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).getType().getMaxAttackRange();
/*  64 */       if (location.contains(":")) {
/*  65 */         String[] split = location.split(":");
/*  66 */         radius = Integer.parseInt(split[1]);
/*     */       } 
/*     */       
/*  69 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/*  70 */         EffectHandler(l, p.getLocation(), target, effect, effectdata);
/*     */       }
/*  72 */     } else if (location.contains("entitiesinradius")) {
/*  73 */       double radius = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).getType().getMaxAttackRange();
/*  74 */       double radiusY = radius;
/*  75 */       if (location.contains(":")) {
/*  76 */         String[] split = location.split(":");
/*  77 */         radius = Integer.parseInt(split[1]);
/*     */         
/*  79 */         if (split.length > 2) {
/*  80 */           radiusY = Integer.parseInt(split[2]);
/*     */         }
/*     */       } 
/*     */       
/*  84 */       for (Entity e : l.getNearbyEntities(radius, radiusY, radius)) {
/*  85 */         if (e instanceof LivingEntity) {
/*  86 */           EffectHandler(l, e.getLocation(), target, effect, effectdata);
/*     */         }
/*     */       } 
/*  89 */     } else if (location.contains("mythicmobsinradius")) {
/*  90 */       double radius = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).getType().getMaxAttackRange();
/*  91 */       double radiusY = radius;
/*  92 */       if (location.contains(":")) {
/*  93 */         String[] split = location.split(":");
/*  94 */         radius = Integer.parseInt(split[1]);
/*     */         
/*  96 */         if (split.length > 2) {
/*  97 */           radiusY = Integer.parseInt(split[2]);
/*     */         }
/*     */       } 
/*     */       
/* 101 */       for (Entity e : l.getNearbyEntities(radius, radiusY, radius)) {
/* 102 */         if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 103 */           EffectHandler(l, e.getLocation(), target, effect, effectdata);
/*     */         }
/*     */       } 
/* 106 */     } else if (location.contains("threattargets")) {
/* 107 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/* 108 */       if (am == null)
/* 109 */         return;  if (!am.getType().usesThreatTable())
/*     */         return; 
/* 111 */       for (AbstractEntity p : am.getThreatTable().getAllThreatTargets()) {
/* 112 */         EffectHandler(l, BukkitAdapter.adapt(p).getLocation(), target, effect, effectdata);
/*     */       }
/* 114 */     } else if (location.contains("location")) {
/*     */       try {
/* 116 */         String[] split = location.split(":");
/* 117 */         String[] coords = split[1].split(",");
/*     */ 
/*     */ 
/*     */         
/* 121 */         double x = Double.parseDouble(coords[0]);
/* 122 */         double y = Double.parseDouble(coords[1]);
/* 123 */         double z = Double.parseDouble(coords[2]);
/*     */         
/* 125 */         Location tl = new Location(l.getWorld(), x, y, z);
/* 126 */         EffectHandler(l, tl, target, effect, effectdata);
/* 127 */       } catch (Exception ex) {
/* 128 */         MythicMobs.error("You have an incorrectly configured @location effect: Format is location:x,y,z! String=" + skill);
/* 129 */         ex.printStackTrace();
/*     */       } 
/* 131 */     } else if (location.contains("spawner")) {
/*     */       try {
/* 133 */         String[] split = location.split(":");
/*     */ 
/*     */         
/* 136 */         if (split[1].equalsIgnoreCase("g") && split.length > 2) {
/* 137 */           MythicMobs.debug(3, "-- Executing effects on spawner group " + split[2]);
/* 138 */           ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(split[2]);
/*     */           
/* 140 */           for (MythicSpawner ms : msl) {
/* 141 */             Location loc = BukkitAdapter.adapt(ms.getLocation()).clone().add(0.5D, 0.5D, 0.5D);
/* 142 */             EffectHandler(l, loc, target, effect, effectdata);
/*     */           } 
/*     */         } else {
/* 145 */           MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(split[1]);
/* 146 */           Location loc = BukkitAdapter.adapt(ms.getLocation()).clone().add(0.5D, 0.5D, 0.5D);
/* 147 */           EffectHandler(l, loc, target, effect, effectdata);
/*     */         } 
/* 149 */       } catch (Exception ex) {
/* 150 */         MythicMobs.error("You have an incorrectly configured @spawner effect: Format is spawner:[spawnername]! String=" + skill);
/* 151 */         ex.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void EffectHandler(LivingEntity sourceentity, Location targetlocation, LivingEntity targetentity, String effect, String effectdata) {
/* 157 */     MythicMobs.debug(3, "-- Executing effect " + effect + ". EffectData=" + effectdata);
/*     */     
/* 159 */     switch (effect) { case "endersignal":
/*     */       case "ender":
/* 161 */         EffectEnderSignal.DoEffect(targetlocation); break;
/*     */       case "explosion":
/*     */       case "explode":
/* 164 */         EffectExplosion.DoEffect(targetlocation); break;
/*     */       case "firework":
/*     */       case "fireworks":
/* 167 */         EffectFirework.DoEffect(targetlocation, effectdata);
/*     */         break;
/*     */       case "flames":
/* 170 */         EffectFlames.DoEffect(targetlocation);
/*     */         break;
/*     */       case "lightning":
/* 173 */         EffectLightning.DoEffect(targetlocation);
/*     */         break;
/*     */       case "lightningring":
/* 176 */         EffectLightningRing.DoEffect(targetlocation, effectdata); break;
/*     */       case "particles":
/*     */       case "p":
/* 179 */         EffectParticles.DoEffect(targetlocation, effectdata); break;
/*     */       case "particleline":
/*     */       case "pl":
/* 182 */         EffectParticleLine.DoEffect(sourceentity, targetlocation, effectdata); break;
/*     */       case "particlering":
/*     */       case "pr":
/* 185 */         EffectParticleRing.DoEffect(targetlocation, effectdata);
/*     */         break;
/*     */       case "particlesquare":
/* 188 */         EffectParticleSquare.DoEffect(targetlocation, effectdata); break;
/*     */       case "radiusfireworks":
/*     */       case "radiusfirework":
/* 191 */         EffectRadiusFireworks.DoEffect(targetlocation, effectdata);
/*     */         break;
/*     */       case "smoke":
/* 194 */         EffectSmoke.DoEffect(targetlocation, effectdata); break;
/*     */       case "sound":
/*     */       case "s":
/* 197 */         EffectSound.DoEffect(targetlocation, effectdata);
/*     */         break;
/*     */ 
/*     */       
/*     */       case "atom":
/* 202 */         EffectAtom.DoEffect(targetlocation, effectdata);
/*     */         break;
/*     */       case "bleed":
/* 205 */         EffectBleed.DoEffect(targetentity, effectdata);
/*     */         break;
/*     */       case "dna":
/* 208 */         EffectDNA.DoEffect(sourceentity.getLocation(), targetlocation, effectdata);
/*     */         break;
/*     */       case "vortex":
/* 211 */         EffectVortex.DoEffect(targetlocation, effectdata);
/*     */         break; }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */