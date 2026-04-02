/*     */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class MythicMobDeathEvent
/*     */   extends Event {
/*     */   private final ActiveMob mob;
/*     */   private final LivingEntity killer;
/*     */   private List<ItemStack> drops;
/*     */   
/*     */   @Deprecated
/*     */   public void setMoney(double money) {
/*  21 */     this.money = money; } @Deprecated public void setExperienceSkillAPI(int experienceSkillAPI) { this.experienceSkillAPI = experienceSkillAPI; } @Deprecated public void setExperienceHeroes(int experienceHeroes) { this.experienceHeroes = experienceHeroes; } @Deprecated public void setExperienceMcMMO(int experienceMcMMO) { this.experienceMcMMO = experienceMcMMO; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent)) return false;  io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent other = (io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent)o; if (!other.canEqual(this)) return false;  Object this$mob = getMob(), other$mob = other.getMob(); if ((this$mob == null) ? (other$mob != null) : !this$mob.equals(other$mob)) return false;  Object this$killer = getKiller(), other$killer = other.getKiller(); if ((this$killer == null) ? (other$killer != null) : !this$killer.equals(other$killer)) return false;  Object<ItemStack> this$drops = (Object<ItemStack>)getDrops(), other$drops = (Object<ItemStack>)other.getDrops(); return ((this$drops == null) ? (other$drops != null) : !this$drops.equals(other$drops)) ? false : ((getExp() != other.getExp()) ? false : ((Double.compare(getMoney(), other.getMoney()) != 0) ? false : ((getExperienceSkillAPI() != other.getExperienceSkillAPI()) ? false : ((getExperienceHeroes() != other.getExperienceHeroes()) ? false : (!(getExperienceMcMMO() != other.getExperienceMcMMO())))))); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent; } public int hashCode() { int PRIME = 59; result = 1; Object $mob = getMob(); result = result * 59 + (($mob == null) ? 43 : $mob.hashCode()); Object $killer = getKiller(); result = result * 59 + (($killer == null) ? 43 : $killer.hashCode()); Object<ItemStack> $drops = (Object<ItemStack>)getDrops(); result = result * 59 + (($drops == null) ? 43 : $drops.hashCode()); result = result * 59 + getExp(); long $money = Double.doubleToLongBits(getMoney()); result = result * 59 + (int)($money >>> 32L ^ $money); result = result * 59 + getExperienceSkillAPI(); result = result * 59 + getExperienceHeroes(); return result * 59 + getExperienceMcMMO(); } public String toString() { return "MythicMobDeathEvent(mob=" + getMob() + ", killer=" + getKiller() + ", drops=" + getDrops() + ", exp=" + getExp() + ", money=" + getMoney() + ", experienceSkillAPI=" + getExperienceSkillAPI() + ", experienceHeroes=" + getExperienceHeroes() + ", experienceMcMMO=" + getExperienceMcMMO() + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  28 */   private int exp = 0; @Deprecated
/*  29 */   private double money = 0.0D; @Deprecated public double getMoney() { return this.money; }
/*     */    @Deprecated
/*  31 */   private int experienceSkillAPI = 0; @Deprecated public int getExperienceSkillAPI() { return this.experienceSkillAPI; } @Deprecated
/*  32 */   private int experienceHeroes = 0; @Deprecated public int getExperienceHeroes() { return this.experienceHeroes; } @Deprecated
/*  33 */   private int experienceMcMMO = 0; @Deprecated public int getExperienceMcMMO() { return this.experienceMcMMO; }
/*     */   
/*  35 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   public MythicMobDeathEvent(ActiveMob am, LivingEntity killer, List<ItemStack> drops) {
/*  38 */     this.mob = am;
/*  39 */     this.killer = killer;
/*  40 */     this.drops = drops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActiveMob getMob() {
/*  48 */     return this.mob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntity() {
/*  56 */     return BukkitAdapter.adapt(this.mob.getEntity());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MythicMob getMobType() {
/*  64 */     return this.mob.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobLevel() {
/*  72 */     return this.mob.getLevel();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getNumberOfPlayerKills() {
/*  77 */     return this.mob.getPlayerKills();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getKiller() {
/*  85 */     return this.killer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getDrops() {
/*  93 */     return this.drops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDrops(List<ItemStack> list) {
/* 101 */     this.drops = list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExp() {
/* 109 */     return this.exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExp(int amount) {
/* 117 */     this.exp = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCurrency() {
/* 125 */     return this.money;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(double amount) {
/* 133 */     this.money = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public HandlerList getHandlers() {
/* 138 */     return handlers;
/*     */   }
/*     */   
/*     */   public static HandlerList getHandlerList() {
/* 142 */     return handlers;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicMobDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */