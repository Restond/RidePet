/*    */ package lumine.xikage.mythicmobs.drops;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DropMetadata
/*    */   implements Cloneable, PlaceholderMeta
/*    */ {
/*    */   private final Optional<SkillCaster> dropper;
/*    */   private final Optional<AbstractEntity> cause;
/*    */   
/*    */   public void setAmount(float amount) {
/* 15 */     this.amount = amount; } public void setGenerations(int generations) { this.generations = generations; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.drops.DropMetadata)) return false;  io.lumine.xikage.mythicmobs.drops.DropMetadata other = (io.lumine.xikage.mythicmobs.drops.DropMetadata)o; if (!other.canEqual(this)) return false;  Object<SkillCaster> this$dropper = (Object<SkillCaster>)getDropper(), other$dropper = (Object<SkillCaster>)other.getDropper(); if ((this$dropper == null) ? (other$dropper != null) : !this$dropper.equals(other$dropper)) return false;  Object<AbstractEntity> this$cause = (Object<AbstractEntity>)getCause(), other$cause = (Object<AbstractEntity>)other.getCause(); return ((this$cause == null) ? (other$cause != null) : !this$cause.equals(other$cause)) ? false : ((Float.compare(getAmount(), other.getAmount()) != 0) ? false : (!(getGenerations() != other.getGenerations()))); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.drops.DropMetadata; } public int hashCode() { int PRIME = 59; result = 1; Object<SkillCaster> $dropper = (Object<SkillCaster>)getDropper(); result = result * 59 + (($dropper == null) ? 43 : $dropper.hashCode()); Object<AbstractEntity> $cause = (Object<AbstractEntity>)getCause(); result = result * 59 + (($cause == null) ? 43 : $cause.hashCode()); result = result * 59 + Float.floatToIntBits(getAmount()); return result * 59 + getGenerations(); } public String toString() { return "DropMetadata(dropper=" + getDropper() + ", cause=" + getCause() + ", amount=" + getAmount() + ", generations=" + getGenerations() + ")"; }
/*    */ 
/*    */   
/* 18 */   public Optional<SkillCaster> getDropper() { return this.dropper; } public Optional<AbstractEntity> getCause() {
/* 19 */     return this.cause;
/*    */   }
/* 21 */   private float amount = 0.0F; public float getAmount() { return this.amount; }
/*    */   
/* 23 */   private int generations = 0; public int getGenerations() { return this.generations; }
/*    */   
/*    */   public DropMetadata(SkillCaster dropper, AbstractEntity killer) {
/* 26 */     this.dropper = Optional.ofNullable(dropper);
/* 27 */     this.cause = Optional.ofNullable(killer);
/*    */   }
/*    */   
/*    */   public int tick() {
/* 31 */     return this.generations++;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillCaster getCaster() {
/* 36 */     return this.dropper.isPresent() ? this.dropper.get() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractEntity getTrigger() {
/* 41 */     return this.cause.isPresent() ? this.cause.get() : null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\DropMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */