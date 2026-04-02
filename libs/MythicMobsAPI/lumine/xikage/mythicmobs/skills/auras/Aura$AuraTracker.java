/*     */ package lumine.xikage.mythicmobs.skills.auras;
/*     */ 
/*     */ import io.lumine.utils.Events;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AuraTracker
/*     */   implements Terminable, Runnable, IParentSkill
/*     */ {
/* 129 */   protected final TerminableRegistry components = TerminableRegistry.create(); protected SkillMetadata skillMetadata;
/*     */   public SkillMetadata getSkillMetadata() {
/* 131 */     return this.skillMetadata;
/*     */   }
/*     */   protected Optional<AbstractEntity> entity; protected Optional<AbstractLocation> location;
/*     */   protected int chargesRemaining;
/* 135 */   protected int stacks = 1; protected int ticksRemaining; public int getStacks() { return this.stacks; }
/* 136 */   public int getChargesRemaining() { return this.chargesRemaining; } public int getTicksRemaining() {
/* 137 */     return this.ticksRemaining;
/*     */   } protected boolean hasEnded = false; public boolean isHasEnded() {
/* 139 */     return this.hasEnded;
/*     */   }
/*     */   public AuraTracker(SkillMetadata data) {
/* 142 */     this.entity = Optional.of(data.getCaster().getEntity());
/* 143 */     this.location = Optional.empty();
/* 144 */     this.skillMetadata = data.deepClone();
/* 145 */     this.skillMetadata.setCallingEvent(this);
/*     */     
/* 147 */     this.chargesRemaining = Aura.this.charges;
/* 148 */     this.ticksRemaining = Aura.this.duration;
/*     */     
/* 150 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker Activated for Caster {0}: skill = {1}", new Object[] { data.getCaster().getEntity().getName(), Aura.access$000(this$0) });
/*     */   }
/*     */   
/*     */   public AuraTracker(SkillCaster caster, AbstractEntity target, SkillMetadata data) {
/* 154 */     this.entity = Optional.of(caster.getEntity());
/* 155 */     this.location = Optional.empty();
/* 156 */     this.skillMetadata = data.deepClone();
/* 157 */     this.skillMetadata.setCallingEvent(this);
/* 158 */     this.skillMetadata.setEntityTarget(target);
/*     */     
/* 160 */     this.chargesRemaining = Aura.this.charges;
/* 161 */     this.ticksRemaining = Aura.this.duration;
/*     */     
/* 163 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker Activated for Entity {0}: skill = {1}", new Object[] { caster.getEntity().getName(), Aura.access$100(this$0) });
/*     */   }
/*     */   
/*     */   public AuraTracker(AbstractEntity entity, SkillMetadata data) {
/* 167 */     this.entity = Optional.of(entity);
/* 168 */     this.location = Optional.empty();
/* 169 */     this.skillMetadata = data.deepClone();
/* 170 */     this.skillMetadata.setCallingEvent(this);
/* 171 */     this.skillMetadata.setEntityTarget(entity);
/*     */     
/* 173 */     this.chargesRemaining = Aura.this.charges;
/* 174 */     this.ticksRemaining = Aura.this.duration;
/*     */     
/* 176 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker Activated for Entity {0}: skill = {1}", new Object[] { entity.getName(), Aura.access$200(this$0) });
/*     */   }
/*     */   
/*     */   public AuraTracker(SkillCaster caster, AbstractLocation target, SkillMetadata data) {
/* 180 */     this.entity = Optional.of(caster.getEntity());
/* 181 */     this.location = Optional.empty();
/* 182 */     this.skillMetadata = data.deepClone();
/* 183 */     this.skillMetadata.setCallingEvent(this);
/* 184 */     this.skillMetadata.setLocationTarget(target);
/*     */     
/* 186 */     this.chargesRemaining = Aura.this.charges;
/* 187 */     this.ticksRemaining = Aura.this.duration;
/*     */     
/* 189 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker Activated for Location {0}: skill = {1}", new Object[] { this.location.toString(), Aura.access$300(this$0) });
/*     */   }
/*     */   
/*     */   public AuraTracker(AbstractLocation location, SkillMetadata data) {
/* 193 */     this.entity = Optional.empty();
/* 194 */     this.location = Optional.of(location);
/* 195 */     this.skillMetadata = data.deepClone();
/* 196 */     this.skillMetadata.setCallingEvent(this);
/* 197 */     this.skillMetadata.setLocationTarget(location);
/* 198 */     this.chargesRemaining = Aura.this.charges;
/* 199 */     this.ticksRemaining = Aura.this.duration;
/*     */     
/* 201 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker Activated for Location {0}: skill = {1}", new Object[] { location.toString(), Aura.access$400(this$0) });
/*     */   }
/*     */   
/*     */   public void registerAuraComponent(Terminable component) {
/* 205 */     this.components.accept(component);
/*     */   }
/*     */   
/*     */   public UUID getCasterUUID() {
/* 209 */     return this.skillMetadata.getCaster().getEntity().getUniqueId();
/*     */   }
/*     */   
/*     */   public void merge(AuraTracker tracker) {
/* 213 */     this.stacks += tracker.getStacks();
/*     */     
/* 215 */     if (this.stacks > Aura.this.maxStacks) {
/* 216 */       this.stacks = Aura.this.maxStacks;
/*     */     }
/*     */     
/* 219 */     if (Aura.this.refreshDuration) {
/* 220 */       this.chargesRemaining = Aura.this.charges;
/* 221 */       this.ticksRemaining = Aura.this.duration;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean executeAuraSkill(Optional<Skill> skill, SkillMetadata data) {
/* 226 */     return executeAuraSkill(skill, data, false);
/*     */   }
/*     */   
/*     */   public boolean executeAuraSkill(Optional<Skill> skill, SkillMetadata data, boolean atCaster) {
/* 230 */     data = data.deepClone();
/*     */     
/* 232 */     if (atCaster) {
/* 233 */       data.setEntityTarget(data.getCaster().getEntity());
/*     */     }
/*     */     
/* 236 */     VariableRegistry vars = data.getVariables();
/* 237 */     if (Aura.this.auraName.isPresent()) {
/* 238 */       vars.putString("aura-name", Aura.this.auraName.get());
/*     */     }
/* 240 */     vars.putInt("aura-charges", this.chargesRemaining);
/* 241 */     vars.putInt("aura-duration", this.ticksRemaining);
/* 242 */     vars.putInt("aura-stacks", this.stacks);
/*     */     
/* 244 */     if (Aura.this.barTimer != null) {
/* 245 */       double progress = this.ticksRemaining / Aura.this.duration;
/* 246 */       Aura.this.barTimer.setTitle(Aura.this.barTimerDisplay.get((PlaceholderMeta)data, this.entity.get()));
/* 247 */       Aura.this.barTimer.setProgress(progress);
/*     */     } 
/*     */     
/* 250 */     if (skill == null || !skill.isPresent())
/* 251 */       return true; 
/* 252 */     if (((Skill)skill.get()).isUsable(data)) {
/* 253 */       ((Skill)skill.get()).execute(data);
/* 254 */       return true;
/*     */     } 
/* 256 */     return false;
/*     */   }
/*     */   
/*     */   public boolean start() {
/* 260 */     if (this.entity.isPresent() && Aura.this.auraName.isPresent()) {
/* 261 */       if (Aura.this.mergeAll || Aura.this.mergeSameCaster) {
/* 262 */         if (AbstractSkill.getPlugin().getSkillManager().getAuraManager().getAuraRegistry(this.entity.get()).mergeAura(Aura.this.auraName.get(), this, Aura.this.mergeSameCaster)) {
/* 263 */           return false;
/*     */         }
/*     */       } else {
/* 266 */         if (Aura.this.overwriteAll || Aura.this.overwriteCaster) {
/* 267 */           AbstractSkill.getPlugin().getSkillManager().getAuraManager().getAuraRegistry(this.entity.get()).removeAll(Aura.this.auraName.get());
/*     */         }
/* 269 */         AbstractSkill.getPlugin().getSkillManager().getAuraManager().getAuraRegistry(this.entity.get()).registerAura(Aura.this.auraName.get(), this);
/*     */       } 
/*     */     }
/*     */     
/* 273 */     if (this.entity.isPresent()) {
/* 274 */       AbstractEntity entity = this.entity.get();
/* 275 */       UUID uuid = entity.getUniqueId();
/* 276 */       if (Aura.this.cancelOnGiveDamage) {
/* 277 */         this.components.accept(Events.subscribe(EntityDamageByEntityEvent.class)
/* 278 */             .filter(event -> event.getDamager().getUniqueId().equals(paramUUID))
/* 279 */             .handler(event -> terminate()));
/*     */       }
/*     */ 
/*     */       
/* 283 */       if (Aura.this.cancelOnTakeDamage) {
/* 284 */         this.components.accept(Events.subscribe(EntityDamageByEntityEvent.class)
/* 285 */             .filter(event -> event.getEntity().getUniqueId().equals(paramUUID))
/* 286 */             .handler(event -> terminate()));
/*     */       }
/*     */ 
/*     */       
/* 290 */       if (Aura.this.cancelOnQuit && entity.isPlayer()) {
/* 291 */         this.components.accept(Events.subscribe(PlayerQuitEvent.class)
/* 292 */             .filter(event -> event.getPlayer().getUniqueId().equals(paramUUID))
/* 293 */             .handler(event -> terminate()));
/*     */       }
/*     */ 
/*     */       
/* 297 */       if (Aura.this.cancelOnDeath) {
/* 298 */         this.components.accept(Events.subscribe(EntityDeathEvent.class)
/* 299 */             .filter(event -> event.getEntity().getUniqueId().equals(paramUUID))
/* 300 */             .handler(event -> terminate()));
/*     */       }
/*     */ 
/*     */       
/* 304 */       if (Aura.this.showBarTimer && entity.isPlayer()) {
/* 305 */         Aura.this.barTimer = MythicMobs.inst().server().createBossBar(" ", Aura.this.barTimerColor, Aura.this.barTimerStyle);
/* 306 */         Aura.this.barTimer.setProgress(1.0D);
/* 307 */         Aura.this.barTimer.addPlayer(entity.asPlayer());
/* 308 */         this.components.accept(Aura.this.barTimer);
/*     */       } 
/*     */     } 
/* 311 */     if (Aura.access$500(Aura.this)) {
/* 312 */       this.components.accept(Schedulers.async().runRepeating(this, 0L, Aura.this.interval));
/*     */     } else {
/* 314 */       this.components.accept(Schedulers.sync().runRepeating(this, 0L, Aura.this.interval));
/*     */     } 
/* 316 */     auraStart();
/* 317 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 321 */     if (this.components.hasTerminated() || this.hasEnded) {
/* 322 */       return false;
/*     */     }
/* 324 */     if (Aura.this.charges > 0 && this.chargesRemaining <= 0) {
/* 325 */       return false;
/*     */     }
/* 327 */     if (this.ticksRemaining <= 0) {
/* 328 */       return false;
/*     */     }
/* 330 */     if (this.entity.isPresent() && !((AbstractEntity)this.entity.get()).isValid()) {
/* 331 */       return false;
/*     */     }
/* 333 */     return true;
/*     */   }
/*     */   
/*     */   public void run() {
/* 337 */     this.ticksRemaining -= Aura.this.interval;
/*     */     
/* 339 */     if (!this.hasEnded && !isValid()) {
/* 340 */       terminate();
/*     */     } else {
/* 342 */       if (this.entity.isPresent()) {
/* 343 */         this.skillMetadata.setOrigin(((AbstractEntity)this.entity.get()).getLocation());
/*     */       }
/* 345 */       auraTick();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void consumeCharge() {
/* 350 */     if (Aura.this.charges <= 0)
/*     */       return; 
/* 352 */     this.chargesRemaining--;
/*     */     
/* 354 */     if (this.chargesRemaining <= 0) {
/* 355 */       terminate();
/*     */     }
/*     */   }
/*     */   
/*     */   public void auraStart() {
/* 360 */     executeAuraSkill(Aura.this.onStartSkill, this.skillMetadata);
/*     */   }
/*     */   
/*     */   public void auraTick() {
/* 364 */     executeAuraSkill(Aura.this.onTickSkill, this.skillMetadata);
/*     */   }
/*     */   
/*     */   public void auraStop() {
/* 368 */     executeAuraSkill(Aura.this.onEndSkill, this.skillMetadata);
/*     */   }
/*     */   
/*     */   public void terminateFromRegistry() {
/* 372 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker cancelled from Registry for " + this.skillMetadata.getCaster().getEntity().getName() + ": skill = " + Aura.access$600(Aura.this), new Object[0]);
/* 373 */     if (!this.hasEnded) {
/*     */       try {
/* 375 */         auraStop();
/* 376 */       } catch (Exception ex) {
/* 377 */         ex.printStackTrace();
/*     */       } 
/* 379 */       if (Aura.this.barTimer != null) {
/* 380 */         Aura.this.barTimer = null;
/*     */       }
/* 382 */       this.hasEnded = true;
/*     */     } 
/* 384 */     this.components.terminate();
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/* 388 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ AuraTracker cancelled for " + this.skillMetadata.getCaster().getEntity().getName() + ": skill = " + Aura.access$700(Aura.this), new Object[0]);
/* 389 */     if (!this.hasEnded) {
/*     */       try {
/* 391 */         auraStop();
/* 392 */       } catch (Exception ex) {
/* 393 */         ex.printStackTrace();
/*     */       } 
/* 395 */       if (this.entity.isPresent() && Aura.this.auraName.isPresent()) {
/* 396 */         AbstractSkill.getPlugin().getSkillManager().getAuraManager().getAuraRegistry(this.entity.get()).unregisterAura(Aura.this.auraName.get(), this);
/*     */       }
/* 398 */       if (Aura.this.barTimer != null) {
/* 399 */         Aura.this.barTimer = null;
/*     */       }
/* 401 */       this.hasEnded = true;
/*     */     } 
/*     */     
/* 404 */     return this.components.terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled() {
/* 409 */     terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 414 */     return hasTerminated();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\auras\Aura$AuraTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */