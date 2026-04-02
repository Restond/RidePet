/*     */ package lumine.xikage.mythicmobs.api.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitAPIHelper
/*     */ {
/*     */   public MythicMob getMythicMob(String type) {
/*  43 */     return MythicMobs.inst().getMobManager().getMythicMob(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawnMythicMob(MythicMob type, Location location, int level) throws InvalidMobTypeException {
/*  54 */     if (type == null) {
/*  55 */       throw new InvalidMobTypeException("Supplied mob type cannot be null.");
/*     */     }
/*     */     
/*  58 */     return type.spawn(BukkitAdapter.adapt(location), level).getEntity().getBukkitEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawnMythicMob(String type, Location location) throws InvalidMobTypeException {
/*  68 */     return spawnMythicMob(type, location, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawnMythicMob(String type, Location location, int level) throws InvalidMobTypeException {
/*  79 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(type);
/*  80 */     if (mm == null) {
/*  81 */       throw new InvalidMobTypeException(type + " is not a valid Mythic Mob type.");
/*     */     }
/*     */     
/*  84 */     return mm.spawn(BukkitAdapter.adapt(location), level).getEntity().getBukkitEntity();
/*     */   }
/*     */   
/*     */   public boolean isMythicMob(Entity l) {
/*  88 */     return MythicMobs.inst().getMobManager().isActiveMob(BukkitAdapter.adapt(l));
/*     */   }
/*     */   
/*     */   public boolean isMythicMob(UUID u) {
/*  92 */     return MythicMobs.inst().getMobManager().isActiveMob(u);
/*     */   }
/*     */   
/*     */   public ActiveMob getMythicMobInstance(Entity e) {
/*  96 */     return MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt(e));
/*     */   }
/*     */   
/*     */   public boolean castSkill(Entity e, String skillName) {
/* 100 */     return castSkill(e, skillName, e.getLocation(), 1.0F);
/*     */   }
/*     */   
/*     */   public boolean castSkill(Entity e, String skillName, float power) {
/* 104 */     return castSkill(e, skillName, e.getLocation(), power);
/*     */   }
/*     */   
/*     */   public boolean castSkill(Entity e, String skillName, Location origin) {
/* 108 */     return castSkill(e, skillName, origin, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean castSkill(Entity e, String skillName, Location origin, float power) {
/* 112 */     return castSkill(e, skillName, null, origin, null, null, power);
/*     */   }
/*     */   
/*     */   public boolean castSkill(Entity e, String skillName, Location origin, Collection<Entity> eTargets, Collection<Location> lTargets, float power) {
/* 116 */     return castSkill(e, skillName, null, origin, eTargets, lTargets, power);
/*     */   }
/*     */   public boolean castSkill(Entity e, String skillName, Entity trigger, Location origin, Collection<Entity> eTargets, Collection<Location> lTargets, float power) {
/*     */     GenericCaster genericCaster;
/* 120 */     Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(skillName);
/* 121 */     if (!maybeSkill.isPresent()) return false;
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 126 */       ActiveMob activeMob = MythicMobs.inst().getMobManager().getMythicMobInstance(e);
/*     */     } else {
/* 128 */       genericCaster = new GenericCaster(BukkitAdapter.adapt(e));
/*     */     } 
/* 130 */     Skill skill = maybeSkill.get();
/*     */     
/* 132 */     HashSet<AbstractEntity> feTargets = new HashSet<>();
/* 133 */     HashSet<AbstractLocation> flTargets = new HashSet<>();
/*     */     
/* 135 */     if (eTargets != null) {
/* 136 */       for (Entity en : eTargets) {
/* 137 */         feTargets.add(BukkitAdapter.adapt(en));
/*     */       }
/*     */     }
/*     */     
/* 141 */     if (lTargets != null) {
/* 142 */       for (Location l : lTargets) {
/* 143 */         flTargets.add(BukkitAdapter.adapt(l));
/*     */       }
/*     */     }
/*     */     
/* 147 */     SkillMetadata data = new SkillMetadata(SkillTrigger.API, (SkillCaster)genericCaster, BukkitAdapter.adapt(trigger), BukkitAdapter.adapt(origin), feTargets, flTargets, power);
/*     */     
/* 149 */     if (skill.usable(data, SkillTrigger.API)) {
/* 150 */       skill.execute(data);
/*     */     }
/*     */     
/* 153 */     return true;
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
/*     */   public boolean addThreat(Entity mob, LivingEntity target, double amount) {
/* 168 */     if (!MythicMobs.inst().getMobManager().isActiveMob(mob.getUniqueId())) {
/* 169 */       return false;
/*     */     }
/*     */     
/* 172 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(mob);
/*     */     
/* 174 */     if (am.getThreatTable() == null) {
/* 175 */       return false;
/*     */     }
/* 177 */     am.getThreatTable().threatGain(BukkitAdapter.adapt((Entity)target), amount);
/*     */     
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reduceThreat(Entity mob, LivingEntity target, double amount) {
/* 190 */     if (!MythicMobs.inst().getMobManager().isActiveMob(mob.getUniqueId())) {
/* 191 */       return false;
/*     */     }
/*     */     
/* 194 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(mob);
/*     */     
/* 196 */     if (am.getThreatTable() == null) {
/* 197 */       return false;
/*     */     }
/* 199 */     am.getThreatTable().threatLoss(BukkitAdapter.adapt((Entity)target), amount);
/*     */     
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean taunt(Entity mob, LivingEntity target) {
/* 211 */     if (!MythicMobs.inst().getMobManager().isActiveMob(mob.getUniqueId())) {
/* 212 */       return false;
/*     */     }
/*     */     
/* 215 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(mob);
/*     */     
/* 217 */     if (am.getThreatTable() == null) {
/* 218 */       return false;
/*     */     }
/* 220 */     am.getThreatTable().Taunt(BukkitAdapter.adapt((Entity)target));
/* 221 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\BukkitAPIHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */