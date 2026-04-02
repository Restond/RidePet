/*     */ package lumine.xikage.mythicmobs.skills.variables;
/*     */ 
/*     */ import com.google.gson.GsonBuilder;
/*     */ import io.lumine.utils.serialization.SerializingModule;
/*     */ import io.lumine.utils.serialization.WrappedJsonFile;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableScope;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableSerializer;
/*     */ import java.io.File;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class VariableManager extends SerializingModule implements Terminable {
/*     */   protected final MythicMobs core;
/*     */   private final WrappedJsonFile<VariableRegistry> globalRegistry;
/*     */   
/*     */   public WrappedJsonFile<VariableRegistry> getGlobalRegistry() {
/*  28 */     return this.globalRegistry;
/*     */   }
/*     */   public VariableManager(MythicMobs core) {
/*  31 */     super((JavaPlugin)core, "SavedData");
/*     */     
/*  33 */     this.core = core;
/*     */     
/*  35 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  41 */       .GSON = (new GsonBuilder()).enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(AbstractWorld.class, this.GSON.getAdapter(BukkitWorld.class)).registerTypeAdapter(Variable.class, new VariableSerializer()).create();
/*     */     
/*  43 */     File playerDir = getModuleDirectory("players");
/*  44 */     File worldsDir = getModuleDirectory("worlds");
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.globalRegistry = loadFile(getModuleFile("global-data.json"), VariableRegistry.class);
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  52 */     ((VariableRegistry)this.globalRegistry.get()).unload();
/*  53 */     this.globalRegistry.save();
/*  54 */     return true;
/*     */   }
/*     */   public VariableRegistry getRegistry(VariableScope scope, SkillMetadata meta, AbstractEntity entity) {
/*     */     SkillCaster caster;
/*  58 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Getting VariableRegistry in scope {0}", new Object[] { scope });
/*  59 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$variables$VariableScope[scope.ordinal()]) {
/*     */       case 1:
/*  61 */         caster = meta.getCaster();
/*     */         
/*  63 */         if (caster == null) {
/*  64 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "! Can't get registry for null caster", new Object[0]);
/*  65 */           return null;
/*     */         } 
/*  67 */         if (caster instanceof ActiveMob) {
/*  68 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting caster mob registry...", new Object[0]);
/*  69 */           return ((ActiveMob)caster).getVariables();
/*     */         } 
/*  71 */         if (caster.getEntity().isPlayer()) {
/*  72 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting caster player registry...", new Object[0]);
/*  73 */           return this.core.getPlayerManager().getPlayerData(caster.getEntity().asPlayer()).getVariables();
/*     */         } 
/*  75 */         return null;
/*     */       
/*     */       case 2:
/*  78 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting skill registry...", new Object[0]);
/*  79 */         return meta.getVariables();
/*     */       
/*     */       case 3:
/*  82 */         if (this.core.getMobManager().isActiveMob(entity)) {
/*  83 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting target mob registry...", new Object[0]);
/*  84 */           return this.core.getMobManager().getMythicMobInstance(entity).getVariables();
/*     */         } 
/*  86 */         if (entity.isPlayer()) {
/*  87 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting target player registry...", new Object[0]);
/*  88 */           return this.core.getPlayerManager().getPlayerData(entity.asPlayer()).getVariables();
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/*  93 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting world registry...", new Object[0]);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/*  98 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting global registry...", new Object[0]);
/*  99 */         return (VariableRegistry)this.globalRegistry.get();
/*     */     } 
/*     */     
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public VariableRegistry getRegistry(VariableScope scope, AbstractEntity entity) {
/* 106 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Getting VariableRegistry for entity {} in scope {}", new Object[] { entity.getName(), scope });
/* 107 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$variables$VariableScope[scope.ordinal()]) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 115 */         if (this.core.getMobManager().isActiveMob(entity)) {
/* 116 */           return this.core.getMobManager().getMythicMobInstance(entity).getVariables();
/*     */         }
/* 118 */         if (entity.isPlayer()) {
/* 119 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Getting target player registry...", new Object[0]);
/* 120 */           return this.core.getPlayerManager().getPlayerData(entity.asPlayer()).getVariables();
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 129 */         return (VariableRegistry)this.globalRegistry.get();
/*     */     } 
/*     */     
/* 132 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "! Can't get caster registry in this context", new Object[0]);
/* 133 */     return null;
/*     */   }
/*     */   
/*     */   public VariableRegistry getRegistry(ActiveMob am) {
/* 137 */     return am.getVariables();
/*     */   }
/*     */   
/*     */   public VariableRegistry getRegistry(VariableScope scope, AbstractLocation target) {
/* 141 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$variables$VariableScope[scope.ordinal()]) {
/*     */       
/*     */       case 1:
/* 144 */         return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 157 */         return (VariableRegistry)this.globalRegistry.get();
/*     */     } 
/*     */     
/* 160 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "! Can't get caster registry in this context", new Object[0]);
/* 161 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */