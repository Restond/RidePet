/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
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
/*     */ 
/*     */ 
/*     */ public interface VolatileCodeHandler
/*     */ {
/*     */   static MythicMobs getPlugin() {
/*  35 */     return MythicMobs.inst();
/*     */   }
/*     */   
/*     */   default VolatileAIHandler getAIHandler() {
/*  39 */     return null;
/*     */   }
/*     */   
/*     */   default VolatileBlockHandler getBlockHandler() {
/*  43 */     return null;
/*     */   }
/*     */   
/*     */   default VolatileEntityHandler getEntityHandler() {
/*  47 */     return null;
/*     */   }
/*     */   
/*     */   default VolatileItemHandler getItemHandler() {
/*  51 */     return null;
/*     */   }
/*     */   
/*     */   default VolatileWorldHandler getWorldHandler() {
/*  55 */     return null;
/*     */   }
/*     */   
/*     */   default CompoundTag createCompoundTag(Map<String, Tag> value) {
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void doDamage(ActiveMob paramActiveMob, AbstractEntity paramAbstractEntity, float paramFloat);
/*     */ 
/*     */ 
/*     */   
/*     */   void PlaySoundAtLocation(AbstractLocation paramAbstractLocation, String paramString, float paramFloat1, float paramFloat2);
/*     */ 
/*     */ 
/*     */   
/*     */   void CreateFireworksExplosion(Location paramLocation, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt2);
/*     */ 
/*     */ 
/*     */   
/*     */   default void doParticleEffect(Location location, String name, float spreadHoriz, float spreadVert, int count, float speed, float yOffset, int radius) {}
/*     */ 
/*     */ 
/*     */   
/*     */   default void setMaxHealth(Entity e, double health) {}
/*     */ 
/*     */ 
/*     */   
/*     */   default void setFollowRange(Entity e, double range) {}
/*     */ 
/*     */   
/*     */   default void setKnockBackResistance(Entity e, double knock) {}
/*     */ 
/*     */   
/*     */   default void setMobSpeed(Entity e, double speed) {}
/*     */ 
/*     */   
/*     */   default ItemStack setItemDurability(ItemStack i, int durability) {
/*  94 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   default void setAttackDamage(Entity e, double damage) {}
/*     */ 
/*     */   
/*     */   default void setCreeperFuseTicks(Creeper c, int t) {}
/*     */ 
/*     */   
/*     */   default void setCreeperExplosionRadius(Creeper c, int r) {}
/*     */ 
/*     */   
/*     */   default void setZombieSpawnReinforcements(Zombie z, double d) {}
/*     */ 
/*     */   
/*     */   default void setEntitySilent(Entity e) {}
/*     */ 
/*     */   
/*     */   void playLocalizedLightningEffect(AbstractLocation paramAbstractLocation, double paramDouble);
/*     */ 
/*     */   
/*     */   ItemStack setItemUnbreakable(ItemStack paramItemStack);
/*     */ 
/*     */   
/*     */   ItemStack setItemHideFlags(ItemStack paramItemStack);
/*     */ 
/*     */   
/*     */   ItemStack setItemAttribute(String paramString1, String paramString2, double paramDouble, ItemStack paramItemStack);
/*     */ 
/*     */   
/*     */   void listItemAttributes(Player paramPlayer);
/*     */ 
/*     */   
/*     */   void setChickenHostile(Chicken paramChicken);
/*     */ 
/*     */   
/*     */   void sendTitleToPlayer(Player paramPlayer, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   void sendActionBarMessageToPlayer(Player paramPlayer, String paramString);
/*     */   
/*     */   Set<AbstractEntity> getEntitiesBySelector(SkillCaster paramSkillCaster, String paramString);
/*     */   
/*     */   void sendWorldEnvironment(AbstractPlayer paramAbstractPlayer, String paramString);
/*     */   
/*     */   void setNoAI(ActiveMob paramActiveMob);
/*     */   
/*     */   ItemStack setItemAttributes(ItemStack paramItemStack, String paramString, Map<String, Object> paramMap, Map<String, Map<String, Object>> paramMap1);
/*     */   
/*     */   double getAbsorptionHearts(LivingEntity paramLivingEntity);
/*     */   
/*     */   void saveSkinData(Player paramPlayer, String paramString);
/*     */   
/*     */   void setClientVelocity(Player paramPlayer, Vector paramVector);
/*     */   
/*     */   float getItemRecharge(Player paramPlayer);
/*     */   
/*     */   void doEffectArmSwing(AbstractEntity paramAbstractEntity);
/*     */   
/*     */   void setEnderDragonAI(Entity paramEntity);
/*     */   
/*     */   void setEnderDragonPathPoint(AbstractLocation paramAbstractLocation);
/*     */   
/*     */   default void lookAtLocation(AbstractEntity entity, AbstractLocation target, boolean headOnly, boolean immediate) {}
/*     */   
/*     */   default void lookAtEntity(AbstractEntity entity, AbstractEntity target, boolean headOnly, boolean immediate) {}
/*     */   
/*     */   default void lookAt(AbstractEntity entity, float yaw, float pitch) {}
/*     */   
/*     */   default void setHeadYaw(AbstractEntity entity, float yaw) {}
/*     */   
/*     */   default boolean getItemRecharging(Player player) {
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   default void applyPhysics(Block target) {}
/*     */   
/*     */   default void sendResourcePack(AbstractPlayer player, String url, String hash) {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */