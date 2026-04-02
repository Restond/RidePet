/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.disabled.VolatileAIHandlerDisabled;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.disabled.VolatileBlockHandlerDisabled;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.disabled.VolatileWorldHandlerDisabled;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Chicken;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ public class VolatileCodeDisabled
/*     */   implements VolatileCodeHandler
/*     */ {
/*  34 */   private final VolatileAIHandler AIHandler = (VolatileAIHandler)new VolatileAIHandlerDisabled(); public VolatileAIHandler getAIHandler() { return this.AIHandler; }
/*  35 */    private final VolatileBlockHandler blockHandler = (VolatileBlockHandler)new VolatileBlockHandlerDisabled(); public VolatileBlockHandler getBlockHandler() { return this.blockHandler; }
/*  36 */    private final VolatileEntityHandler entityHandler = null; public VolatileEntityHandler getEntityHandler() { return this.entityHandler; }
/*  37 */    private final VolatileItemHandler itemHandler = null; public VolatileItemHandler getItemHandler() { return this.itemHandler; }
/*  38 */    private final VolatileWorldHandler worldHandler = (VolatileWorldHandler)new VolatileWorldHandlerDisabled(); public VolatileWorldHandler getWorldHandler() { return this.worldHandler; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void PlaySoundAtLocation(AbstractLocation loc, String sound, float volume, float pitch) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreateFireworksExplosion(Location location, boolean flicker, boolean trail, int type, int[] colors, int[] fadeColors, int flightDuration) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doParticleEffect(Location location, String name, float spreadHoriz, float spreadVert, int count, float speed, float yOffset, int radius) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxHealth(Entity e, double health) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFollowRange(Entity e, double range) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKnockBackResistance(Entity e, double knock) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMobSpeed(Entity e, double speed) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttackDamage(Entity e, double damage) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttribute(String itemName, String attr, double amount, ItemStack item) {
/*  91 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void listItemAttributes(Player player) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperFuseTicks(Creeper c, int t) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperExplosionRadius(Creeper c, int r) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChickenHostile(Chicken c) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZombieSpawnReinforcements(Zombie z, double d) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemUnbreakable(ItemStack i) {
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendActionBarMessageToPlayer(Player player, String message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemHideFlags(ItemStack i) {
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntitySilent(Entity e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<AbstractEntity> getEntitiesBySelector(SkillCaster am, String targetSelector) {
/* 153 */     return Collections.emptySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendWorldEnvironment(AbstractPlayer player, String env) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoAI(ActiveMob am) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playLocalizedLightningEffect(AbstractLocation target, double radius) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttributes(ItemStack item, String itemName, Map<String, Object> options, Map<String, Map<String, Object>> attributes) {
/* 177 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doDamage(ActiveMob mob, AbstractEntity target, float amount) {
/* 182 */     target.damage(amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAbsorptionHearts(LivingEntity entity) {
/* 188 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSkinData(Player player, String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClientVelocity(Player player, Vector velocity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItemRecharge(Player player) {
/* 205 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void doEffectArmSwing(AbstractEntity entity) {}
/*     */   
/*     */   public void setEnderDragonAI(Entity entity) {}
/*     */   
/*     */   public void setEnderDragonPathPoint(AbstractLocation location) {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */