/*      */ package lumine.xikage.mythicmobs.mobs;
/*      */ 
/*      */ import com.google.common.collect.ImmutableMap;
/*      */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*      */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*      */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ThreatTable
/*      */   implements Cloneable
/*      */ {
/*      */   private WeakReference<ActiveMob> mob;
/*  679 */   private double totalThreat = 0.0D;
/*  680 */   private double targetThreat = 0.0D;
/*  681 */   private int targetOutOfRangeTicks = 0;
/*  682 */   private AbstractEntity target = null;
/*      */   
/*      */   private ConcurrentHashMap<AbstractEntity, Double> threatTable;
/*      */ 
/*      */   
/*      */   public ThreatTable() {
/*  688 */     this.threatTable = new ConcurrentHashMap<>();
/*      */   }
/*      */   
/*      */   public void setParent(ActiveMob am) {
/*  692 */     this.mob = new WeakReference<>(am);
/*      */   }
/*      */   
/*      */   public void tickThreat() {
/*  696 */     if (this.target == null) {
/*  697 */       clearTarget();
/*  698 */       targetHighestThreat();
/*      */       return;
/*      */     } 
/*  701 */     if (this.target.isDead()) {
/*  702 */       targetDeath();
/*      */       return;
/*      */     } 
/*  705 */     if (this.threatTable.size() == 0) {
/*  706 */       dropCombat();
/*      */       
/*      */       return;
/*      */     } 
/*  710 */     if (ActiveMob.this.getType().getThreatTableDecaysUnreachable() && !this.target.hasLineOfSight(ActiveMob.this.getEntity())) {
/*  711 */       decayTargetThreat();
/*      */     }
/*      */     
/*  714 */     if (this.target.isPlayer() && 
/*  715 */       !this.target.asPlayer().isOnline()) {
/*  716 */       clearTarget();
/*  717 */       targetHighestThreat();
/*      */     } 
/*      */ 
/*      */     
/*  721 */     if (!this.target.getLocation().getWorld().equals(ActiveMob.this.getEntity().getLocation().getWorld()) || (this.target
/*  722 */       .isPlayer() && (this.target.asPlayer().isInCreativeMode() || this.target.asPlayer().isInSpectatorMode()))) {
/*      */       
/*  724 */       clearTarget();
/*  725 */       targetHighestThreat();
/*  726 */       this.targetOutOfRangeTicks = 0;
/*  727 */     } else if (ActiveMob.this.getType().getThreatTableDecaysUnreachable() && this.target.getLocation().distance(ActiveMob.this.getEntity().getLocation()) > ActiveMob.this.getType().getMaxThreatDistance()) {
/*  728 */       this.targetOutOfRangeTicks++;
/*      */       
/*  730 */       if (this.targetOutOfRangeTicks >= 10) {
/*  731 */         clearTarget();
/*  732 */         targetHighestThreat();
/*  733 */         this.targetOutOfRangeTicks = 0;
/*      */       } 
/*      */     } else {
/*  736 */       this.targetOutOfRangeTicks = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean inCombat() {
/*  744 */     return (this.threatTable.size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean targetEvent(AbstractEntity entity) {
/*  753 */     if (entity == null) return false;
/*      */     
/*  755 */     if (this.target == null) {
/*  756 */       this.target = entity;
/*  757 */       ActiveMob.this.setTarget(entity);
/*      */       
/*  759 */       if (this.threatTable.containsKey(entity)) {
/*  760 */         this.targetThreat = ((Double)this.threatTable.get(entity)).doubleValue();
/*      */       } else {
/*  762 */         if (!inCombat()) {
/*  763 */           ActiveMob.access$100().getMobManager().setInCombat(ActiveMob.this, true);
/*  764 */           new TriggeredSkill(SkillTrigger.ENTERCOMBAT, ActiveMob.this, entity, new org.apache.commons.lang3.tuple.Pair[0]);
/*      */         } 
/*  766 */         this.targetThreat = 1.0D;
/*  767 */         this.threatTable.put(entity, Double.valueOf(1.0D));
/*      */       } 
/*  769 */       return true;
/*      */     } 
/*  771 */     if (this.target.equals(entity)) {
/*  772 */       return true;
/*      */     }
/*  774 */     if (this.target.isDead()) {
/*  775 */       targetDeath();
/*  776 */       return (this.target != null && this.target.equals(entity));
/*      */     } 
/*  778 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean Taunt(AbstractEntity target) {
/*  789 */     if (target.equals(this.target)) return false;
/*      */     
/*  791 */     this.threatTable.put(target, Double.valueOf(this.targetThreat * 1.1D));
/*  792 */     targetThreateningEntity(target);
/*      */     
/*  794 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropCombat() {
/*  801 */     this.target = null;
/*  802 */     this.targetThreat = 0.0D;
/*  803 */     this.totalThreat = 0.0D;
/*  804 */     this.threatTable.clear();
/*  805 */     ActiveMob.access$100().getMobManager().setInCombat(ActiveMob.this, false);
/*  806 */     ActiveMob.access$100().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)ActiveMob.access$200(ActiveMob.this).getBukkitEntity(), null);
/*      */     
/*  808 */     new TriggeredSkill(SkillTrigger.DROPCOMBAT, ActiveMob.this, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void observeDeath(AbstractEntity entity) {
/*  816 */     if (this.target == null)
/*  817 */       return;  if (this.target.equals(entity)) {
/*  818 */       targetDeath();
/*      */     }
/*  820 */     this.threatTable.remove(entity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void observeChangeWorld(AbstractEntity entity) {
/*  828 */     if (this.target == null)
/*  829 */       return;  if (this.target.equals(entity)) {
/*  830 */       clearTarget();
/*  831 */       if (inCombat()) {
/*  832 */         targetHighestThreat();
/*      */       }
/*      */     } 
/*  835 */     this.threatTable.remove(entity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean targetThreateningEntity(AbstractEntity entity) {
/*  844 */     this.target = entity;
/*  845 */     this.targetThreat = ((Double)this.threatTable.get(entity)).doubleValue();
/*  846 */     ActiveMob.this.setTarget(this.target);
/*  847 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void targetHighestThreat() {
/*  855 */     if (this.threatTable.size() == 0) {
/*  856 */       dropCombat();
/*      */       
/*      */       return;
/*      */     } 
/*  860 */     Iterator<Map.Entry<AbstractEntity, Double>> it = this.threatTable.entrySet().iterator();
/*  861 */     this.targetThreat = 0.0D;
/*      */     
/*  863 */     while (it.hasNext()) {
/*  864 */       Map.Entry<AbstractEntity, Double> pairs = it.next();
/*      */       
/*  866 */       if (((Double)pairs.getValue()).doubleValue() > this.targetThreat) {
/*  867 */         this.targetThreat = ((Double)pairs.getValue()).doubleValue();
/*  868 */         this.target = pairs.getKey();
/*      */       } 
/*      */     } 
/*  871 */     ActiveMob.this.setTarget(this.target);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearTarget() {
/*  878 */     if (this.target != null) {
/*  879 */       this.threatTable.remove(this.target);
/*      */     }
/*  881 */     if (this.threatTable.size() == 0) {
/*  882 */       dropCombat();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void targetDeath() {
/*  890 */     MythicMobs.debug(3, "Target may have died!");
/*  891 */     if (this.target.isDead()) {
/*  892 */       clearTarget();
/*  893 */       targetHighestThreat();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void threatSet(AbstractEntity entity, double amount) {
/*  903 */     if (entity == null) {
/*      */       return;
/*      */     }
/*      */     
/*  907 */     MythicMobs.debug(3, "An attacker had threat set to " + amount + "");
/*  908 */     if (entity.getUniqueId().equals(ActiveMob.this.getUniqueId()))
/*      */       return; 
/*  910 */     if (ActiveMob.access$300(ActiveMob.this).isPresent() && ((UUID)ActiveMob.access$300(ActiveMob.this).get()).equals(entity.getUniqueId()))
/*      */       return; 
/*  912 */     if (entity.isPlayer() && (entity.asPlayer().isInCreativeMode() || entity.asPlayer().isInSpectatorMode()))
/*      */       return; 
/*  914 */     if (this.target == null) {
/*  915 */       MythicMobs.debug(3, "== Entity previously had no target, assigning target...");
/*  916 */       targetEvent(entity);
/*  917 */       this.targetThreat = amount;
/*  918 */     } else if (this.target.equals(entity)) {
/*  919 */       MythicMobs.debug(3, "== Entity was current target!");
/*  920 */       this.targetThreat += amount;
/*  921 */       amount = this.targetThreat;
/*      */     } else {
/*  923 */       MythicMobs.debug(3, "== Entity was different hostile!");
/*      */     } 
/*  925 */     MythicMobs.debug(3, "==== Attacker threat set to " + amount + " threat!");
/*  926 */     this.threatTable.put(entity, Double.valueOf(amount));
/*      */     
/*  928 */     if (amount > this.targetThreat * 1.1D) {
/*  929 */       targetThreateningEntity(entity);
/*      */     }
/*  931 */     this.totalThreat += amount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void threatGain(AbstractEntity entity, double amount) {
/*  940 */     if (entity == null)
/*      */       return; 
/*  942 */     MythicMobs.debug(3, "An attacker attacked for " + amount + " threat!");
/*  943 */     if (entity.getUniqueId().equals(ActiveMob.this.getUniqueId()))
/*      */       return; 
/*  945 */     if (ActiveMob.access$300(ActiveMob.this).isPresent() && ((UUID)ActiveMob.access$300(ActiveMob.this).get()).equals(entity.getUniqueId()))
/*      */       return; 
/*  947 */     if (entity.isPlayer() && (entity.asPlayer().isInCreativeMode() || entity.asPlayer().isInSpectatorMode()))
/*      */       return; 
/*  949 */     if (this.target == null) {
/*  950 */       MythicMobs.debug(3, "== Entity previously had no target, assigning target...");
/*  951 */       targetEvent(entity);
/*  952 */       this.targetThreat = amount;
/*  953 */     } else if (this.target.equals(entity)) {
/*  954 */       MythicMobs.debug(3, "== Entity was current target!");
/*  955 */       this.targetThreat += amount;
/*  956 */       amount = this.targetThreat;
/*      */     } else {
/*  958 */       MythicMobs.debug(3, "== Entity was different hostile!");
/*  959 */       if (this.threatTable.containsKey(entity)) {
/*  960 */         amount += ((Double)this.threatTable.get(entity)).doubleValue();
/*      */       }
/*      */     } 
/*  963 */     MythicMobs.debug(3, "==== Attacker threat set to " + amount + " threat!");
/*  964 */     this.threatTable.put(entity, Double.valueOf(amount));
/*      */     
/*  966 */     if (amount > this.targetThreat * 1.1D) {
/*  967 */       targetThreateningEntity(entity);
/*      */     }
/*  969 */     this.totalThreat += amount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void threatLoss(AbstractEntity entity, double amount) {
/*      */     double nT;
/*  978 */     MythicMobs.debug(3, "A target lost " + amount + " threat!");
/*      */ 
/*      */ 
/*      */     
/*  982 */     if (this.threatTable.containsKey(entity)) {
/*  983 */       nT = ((Double)this.threatTable.get(entity)).doubleValue() - amount;
/*      */       
/*  985 */       if (nT < 0.0D) {
/*  986 */         amount -= 0.0D - nT;
/*  987 */         nT = 0.0D;
/*      */       } 
/*      */     } else {
/*      */       return;
/*      */     } 
/*  992 */     MythicMobs.debug(3, "==== Attacker threat set to " + amount + " threat!");
/*  993 */     this.threatTable.put(entity, Double.valueOf(nT));
/*  994 */     this.totalThreat -= amount;
/*      */     
/*  996 */     if (this.target.equals(entity)) {
/*  997 */       targetHighestThreat();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decayTargetThreat() {
/* 1005 */     double dt = this.targetThreat * 0.96D;
/*      */     
/* 1007 */     this.totalThreat -= this.targetThreat - dt;
/* 1008 */     this.targetThreat = dt;
/* 1009 */     this.threatTable.put(this.target, Double.valueOf(dt));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractEntity getTopThreatHolder() {
/* 1016 */     return this.target;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getTopTargetThreat() {
/* 1023 */     return this.targetThreat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getThreat(AbstractEntity entity) {
/* 1032 */     if (this.threatTable.containsKey(entity)) {
/* 1033 */       return ((Double)this.threatTable.get(entity)).doubleValue();
/*      */     }
/* 1035 */     return 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getTotalThreat() {
/* 1043 */     return this.totalThreat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<AbstractEntity> getAllThreatTargets() {
/* 1050 */     return this.threatTable.keySet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/* 1057 */     return this.threatTable.size();
/*      */   }
/*      */   
/*      */   public ImmutableMap<AbstractEntity, Double> asMap() {
/* 1061 */     return ImmutableMap.copyOf(this.threatTable);
/*      */   }
/*      */ 
/*      */   
/*      */   public ThreatTable clone() throws CloneNotSupportedException {
/* 1066 */     return (ThreatTable)super.clone();
/*      */   }
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ActiveMob$ThreatTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */