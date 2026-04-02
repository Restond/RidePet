/*    */ package lumine.xikage.mythicmobs.skills.placeholders;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ 
/*    */ public class GenericPlaceholderMeta implements PlaceholderMeta {
/*    */   private final SkillCaster caster;
/*    */   
/*  8 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta)) return false;  io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta other = (io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta)o; if (!other.canEqual(this)) return false;  Object this$caster = getCaster(), other$caster = other.getCaster(); if ((this$caster == null) ? (other$caster != null) : !this$caster.equals(other$caster)) return false;  Object this$trigger = getTrigger(), other$trigger = other.getTrigger(); return !((this$trigger == null) ? (other$trigger != null) : !this$trigger.equals(other$trigger)); } private final AbstractEntity trigger; protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta; } public int hashCode() { int PRIME = 59; result = 1; Object $caster = getCaster(); result = result * 59 + (($caster == null) ? 43 : $caster.hashCode()); Object $trigger = getTrigger(); return result * 59 + (($trigger == null) ? 43 : $trigger.hashCode()); } public String toString() { return "GenericPlaceholderMeta(caster=" + getCaster() + ", trigger=" + getTrigger() + ")"; }
/*    */ 
/*    */   
/* 11 */   public SkillCaster getCaster() { return this.caster; } public AbstractEntity getTrigger() {
/* 12 */     return this.trigger;
/*    */   }
/*    */   public GenericPlaceholderMeta(SkillCaster caster) {
/* 15 */     this.caster = caster;
/* 16 */     this.trigger = null;
/*    */   }
/*    */   
/*    */   public GenericPlaceholderMeta(AbstractEntity caster) {
/* 20 */     this.caster = (SkillCaster)new GenericCaster(caster);
/* 21 */     this.trigger = null;
/*    */   }
/*    */   
/*    */   public GenericPlaceholderMeta(SkillCaster caster, AbstractEntity trigger) {
/* 25 */     this.caster = caster;
/* 26 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public GenericPlaceholderMeta(AbstractEntity caster, AbstractEntity trigger) {
/* 30 */     this.caster = (SkillCaster)new GenericCaster(caster);
/* 31 */     this.trigger = trigger;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\GenericPlaceholderMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */