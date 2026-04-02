/*    */ package lumine.xikage.mythicmobs.skills.damage;
/*    */ public class DamageMetadata { private final SkillCaster damager;
/*    */   private final double amount;
/*    */   private final String element;
/*    */   
/*  6 */   public DamageMetadata(SkillCaster damager, double amount, String element, Boolean ignoresArmor, Boolean preventsImmunity, Boolean preventsKnockback) { this.damager = damager; this.amount = amount; this.element = element; this.ignoresArmor = ignoresArmor; this.preventsImmunity = preventsImmunity; this.preventsKnockback = preventsKnockback; } protected final Boolean ignoresArmor; protected final Boolean preventsImmunity; protected final Boolean preventsKnockback; public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata)) return false;  io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata other = (io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata)o; if (!other.canEqual(this)) return false;  Object this$damager = getDamager(), other$damager = other.getDamager(); if ((this$damager == null) ? (other$damager != null) : !this$damager.equals(other$damager)) return false;  if (Double.compare(getAmount(), other.getAmount()) != 0) return false;  Object this$element = getElement(), other$element = other.getElement(); if ((this$element == null) ? (other$element != null) : !this$element.equals(other$element)) return false;  Object this$ignoresArmor = getIgnoresArmor(), other$ignoresArmor = other.getIgnoresArmor(); if ((this$ignoresArmor == null) ? (other$ignoresArmor != null) : !this$ignoresArmor.equals(other$ignoresArmor)) return false;  Object this$preventsImmunity = getPreventsImmunity(), other$preventsImmunity = other.getPreventsImmunity(); if ((this$preventsImmunity == null) ? (other$preventsImmunity != null) : !this$preventsImmunity.equals(other$preventsImmunity)) return false;  Object this$preventsKnockback = getPreventsKnockback(), other$preventsKnockback = other.getPreventsKnockback(); return !((this$preventsKnockback == null) ? (other$preventsKnockback != null) : !this$preventsKnockback.equals(other$preventsKnockback)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata; } public int hashCode() { int PRIME = 59; result = 1; Object $damager = getDamager(); result = result * 59 + (($damager == null) ? 43 : $damager.hashCode()); long $amount = Double.doubleToLongBits(getAmount()); result = result * 59 + (int)($amount >>> 32L ^ $amount); Object $element = getElement(); result = result * 59 + (($element == null) ? 43 : $element.hashCode()); Object $ignoresArmor = getIgnoresArmor(); result = result * 59 + (($ignoresArmor == null) ? 43 : $ignoresArmor.hashCode()); Object $preventsImmunity = getPreventsImmunity(); result = result * 59 + (($preventsImmunity == null) ? 43 : $preventsImmunity.hashCode()); Object $preventsKnockback = getPreventsKnockback(); return result * 59 + (($preventsKnockback == null) ? 43 : $preventsKnockback.hashCode()); } public String toString() { return "DamageMetadata(damager=" + getDamager() + ", amount=" + getAmount() + ", element=" + getElement() + ", ignoresArmor=" + getIgnoresArmor() + ", preventsImmunity=" + getPreventsImmunity() + ", preventsKnockback=" + getPreventsKnockback() + ")"; }
/*    */ 
/*    */   
/*  9 */   public SkillCaster getDamager() { return this.damager; }
/* 10 */   public double getAmount() { return this.amount; } public String getElement() {
/* 11 */     return this.element;
/*    */   }
/* 13 */   public Boolean getIgnoresArmor() { return this.ignoresArmor; }
/* 14 */   public Boolean getPreventsImmunity() { return this.preventsImmunity; } public Boolean getPreventsKnockback() {
/* 15 */     return this.preventsKnockback;
/*    */   } }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\damage\DamageMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */