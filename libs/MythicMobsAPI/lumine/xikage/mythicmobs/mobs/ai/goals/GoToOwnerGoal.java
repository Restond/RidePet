/*     */ package lumine.xikage.mythicmobs.mobs.ai.goals;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.Pathfinder;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfindingGoal;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicAIGoal;
/*     */ import java.lang.ref.WeakReference;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ @MythicAIGoal(name = "goToOwner", aliases = {}, version = "4.8", description = "Path to the mob's owner")
/*     */ public class GoToOwnerGoal
/*     */   extends Pathfinder
/*     */   implements PathfindingGoal
/*     */ {
/*  21 */   private transient WeakReference<Player> owner = null;
/*     */   
/*     */   private double followRangeSq;
/*     */   private double minRangeSq;
/*     */   
/*     */   public GoToOwnerGoal(AbstractEntity entity, String line, MythicLineConfig mlc) {
/*  27 */     super(entity, line, mlc);
/*  28 */     this.goalType = Pathfinder.GoalType.MOVE_LOOK;
/*     */     
/*  30 */     double def = 4.0D;
/*     */     try {
/*  32 */       def = Double.valueOf(this.dataVar1).doubleValue();
/*  33 */     } catch (Exception|Error exception) {}
/*  34 */     double followRange = mlc.getDouble(new String[] { "followrange", "fr", "r", "maxrange" }, def);
/*  35 */     this.followRangeSq = Math.pow(followRange, 2.0D);
/*     */     
/*  37 */     double minRange = mlc.getDouble(new String[] { "minrange", "mr" }, def);
/*  38 */     this.minRangeSq = Math.pow(minRange, 2.0D);
/*     */     
/*  40 */     float defSpeed = 1.0F;
/*     */     try {
/*  42 */       defSpeed = Float.valueOf(this.dataVar2).floatValue();
/*  43 */     } catch (Exception|Error exception) {}
/*  44 */     this.speed = mlc.getFloat(new String[] { "speed", "s" }, defSpeed);
/*     */     
/*  46 */     this.dropTarget = mlc.getBoolean(new String[] { "droptarget", "dt" }, true);
/*     */   }
/*     */   private float speed; private boolean dropTarget;
/*     */   
/*     */   public boolean shouldStart() {
/*  51 */     if (this.owner == null || this.owner.get() == null) {
/*  52 */       if (!this.activeMob.getOwner().isPresent()) {
/*  53 */         return false;
/*     */       }
/*  55 */       Player player = Bukkit.getPlayer(this.activeMob.getOwner().get());
/*     */       
/*  57 */       if (player == null) {
/*  58 */         return false;
/*     */       }
/*  60 */       this.owner = new WeakReference<>(player);
/*     */     } 
/*     */     
/*  63 */     if (this.owner == null) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     AbstractLocation destination = BukkitAdapter.adapt(((Player)this.owner.get()).getLocation());
/*     */     
/*  69 */     if (this.entity.getLocation().distanceSquared(destination) > this.followRangeSq) {
/*  70 */       if (this.dropTarget) {
/*  71 */         ai().setTarget((LivingEntity)BukkitAdapter.adapt(this.entity), null);
/*     */       }
/*  73 */       return true;
/*     */     } 
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {}
/*     */ 
/*     */   
/*     */   public void tick() {
/*  83 */     AbstractLocation destination = BukkitAdapter.adapt(((Player)this.owner.get()).getLocation());
/*  84 */     double dist = this.entity.getLocation().distanceSquared(destination);
/*     */     
/*  86 */     if (dist > 4096.0D) {
/*  87 */       this.entity.teleport(destination);
/*     */     } else {
/*  89 */       ai().navigateToLocation(this.entity, destination, this.speed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldEnd() {
/*  95 */     if (this.owner.get() == null || !((Player)this.owner.get()).isOnline()) {
/*  96 */       return true;
/*     */     }
/*  98 */     AbstractLocation destination = BukkitAdapter.adapt(((Player)this.owner.get()).getLocation());
/*     */     
/* 100 */     if (this.entity.getLocation().distanceSquared(destination) <= this.minRangeSq) {
/* 101 */       return true;
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public void end() {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\goals\GoToOwnerGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */