/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ 
/*     */ public class SummonMechanic extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*     */   protected MythicMob mm;
/*     */   protected MythicEntity me;
/*     */   protected String strType;
/*     */   protected PlaceholderInt amount;
/*     */   protected int noise;
/*     */   protected int yNoise;
/*     */   protected boolean summonerIsOwner;
/*     */   protected boolean summonerIsParent;
/*     */   protected boolean yUpOnly;
/*     */   protected boolean onSurface;
/*     */   protected boolean inheritFaction;
/*     */   protected boolean inheritThreatTable;
/*     */   protected boolean copyThreatTable;
/*     */   
/*     */   public SummonMechanic(String skill, MythicLineConfig mlc) {
/*  33 */     super(skill, mlc);
/*  34 */     this.ASYNC_SAFE = false;
/*     */     
/*  36 */     this.strType = mlc.getString(new String[] { "type", "t", "mob", "m" }, "SKELETON", new String[0]);
/*  37 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amount", "a" }, 1, new String[0]);
/*  38 */     this.noise = mlc.getInteger(new String[] { "noise", "n", "radius", "r" }, 0);
/*  39 */     this.yNoise = mlc.getInteger(new String[] { "ynoise", "yn", "yradius", "yr" }, this.noise);
/*  40 */     this.yUpOnly = mlc.getBoolean(new String[] { "yradiusuponly", "yradiusonlyup", "yruo", "yu" }, false);
/*  41 */     this.onSurface = mlc.getBoolean(new String[] { "onsurface", "os", "s" }, false);
/*  42 */     this.copyThreatTable = mlc.getBoolean(new String[] { "copythreattable", "ctt" }, false);
/*  43 */     this.inheritThreatTable = mlc.getBoolean(new String[] { "inheritfaction", "if" }, true);
/*  44 */     this.inheritThreatTable = mlc.getBoolean(new String[] { "inheritthreattable", "itt" }, false);
/*  45 */     this.summonerIsOwner = mlc.getBoolean(new String[] { "summonerisowner", "sio" }, true);
/*  46 */     this.summonerIsParent = mlc.getBoolean(new String[] { "summonerisparent", "sip" }, true);
/*     */     
/*  48 */     Schedulers.sync().runLater(() -> { this.mm = MythicMobs.inst().getMobManager().getMythicMob(this.strType); if (this.mm == null) { this.me = MythicEntity.getMythicEntity(this.strType); if (this.me == null) MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "The 'type' attribute must be a valid MythicMob or MythicEntity type.");  }  }1L);
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
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  63 */     int amount = this.amount.get((PlaceholderMeta)data);
/*  64 */     if (this.mm != null) {
/*  65 */       if (this.noise > 0) {
/*     */         
/*  67 */         for (int i = 1; i <= amount; i++) {
/*  68 */           getPlugin().getMobManager(); AbstractLocation l = MobManager.findSafeSpawnLocation(target, this.noise, this.yNoise, this.mm.getMythicEntity().getHeight(), this.yUpOnly, this.onSurface);
/*  69 */           ActiveMob ams = this.mm.spawn(l, data.getCaster().getLevel());
/*  70 */           if (ams != null) {
/*  71 */             getPlugin().getEntityManager().registerMob(ams.getEntity().getWorld(), ams.getEntity());
/*     */             
/*  73 */             if (data.getCaster() instanceof ActiveMob) {
/*     */               
/*  75 */               ActiveMob am = (ActiveMob)data.getCaster();
/*     */               
/*  77 */               if (this.summonerIsOwner) {
/*  78 */                 ams.setParent((SkillCaster)am);
/*  79 */                 ams.setFaction(am.getFaction());
/*     */               } 
/*     */               
/*  82 */               if (this.copyThreatTable == true) {
/*     */                 try {
/*  84 */                   ams.importThreatTable(am.getThreatTable().clone());
/*  85 */                   ams.getThreatTable().targetHighestThreat();
/*  86 */                 } catch (CloneNotSupportedException e1) {
/*  87 */                   e1.printStackTrace();
/*     */                 } 
/*  89 */               } else if (this.inheritThreatTable == true) {
/*  90 */                 ams.importThreatTable(am.getThreatTable());
/*  91 */                 ams.getThreatTable().targetHighestThreat();
/*     */               }
/*     */             
/*  94 */             } else if (this.summonerIsOwner) {
/*  95 */               ams.setOwner(data.getCaster().getEntity().getUniqueId());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 101 */         for (int i = 1; i <= amount; i++) {
/* 102 */           ActiveMob ams = this.mm.spawn(target, data.getCaster().getLevel());
/*     */           
/* 104 */           if (ams != null) {
/* 105 */             getPlugin().getEntityManager().registerMob(ams.getEntity().getWorld(), ams.getEntity());
/*     */             
/* 107 */             if (data.getCaster() instanceof ActiveMob) {
/* 108 */               ActiveMob am = (ActiveMob)data.getCaster();
/*     */               
/* 110 */               if (this.summonerIsParent) {
/* 111 */                 ams.setParent((SkillCaster)am);
/* 112 */                 ams.setFaction(am.getFaction());
/*     */               } 
/*     */               
/* 115 */               if (this.copyThreatTable == true) {
/*     */                 try {
/* 117 */                   ams.importThreatTable(am.getThreatTable().clone());
/* 118 */                   ams.getThreatTable().targetHighestThreat();
/* 119 */                 } catch (CloneNotSupportedException e1) {
/* 120 */                   e1.printStackTrace();
/*     */                 } 
/* 122 */               } else if (this.inheritThreatTable == true) {
/* 123 */                 ams.importThreatTable(am.getThreatTable());
/* 124 */                 ams.getThreatTable().targetHighestThreat();
/*     */               }
/*     */             
/* 127 */             } else if (this.summonerIsOwner) {
/* 128 */               ams.setOwner(data.getCaster().getEntity().getUniqueId());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       return true;
/* 135 */     }  if (this.me != null) {
/* 136 */       if (this.noise > 0) {
/*     */         
/* 138 */         for (int i = 1; i <= amount; i++) {
/* 139 */           MythicMobs.inst().getMobManager(); AbstractLocation l = MobManager.findSafeSpawnLocation(target, this.noise, this.yNoise, this.me.getHeight(), this.yUpOnly, this.onSurface);
/* 140 */           this.me.spawn(BukkitAdapter.adapt(l));
/*     */         } 
/*     */       } else {
/* 143 */         for (int i = 1; i <= amount; i++) {
/* 144 */           this.me.spawn(BukkitAdapter.adapt(target));
/*     */         }
/*     */       } 
/* 147 */       return true;
/*     */     } 
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 154 */     castAtLocation(data, target.getLocation());
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SummonMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */