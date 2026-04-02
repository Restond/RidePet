/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "arrowvolley", description = "Shoots a volley of arrows")
/*    */ public class ArrowVolleyMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected PlaceholderInt amount;
/*    */   protected int spread;
/*    */   
/*    */   public ArrowVolleyMechanic(String line, MythicLineConfig mlc) {
/* 22 */     super(line, mlc);
/* 23 */     this.ASYNC_SAFE = false;
/*    */     
/* 25 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amount", "arrows", "a" }, 20, new String[0]);
/* 26 */     this.spread = mlc.getInteger(new String[] { "spread", "s" }, 45);
/* 27 */     this.fireTicks = mlc.getInteger(new String[] { "fireticks", "ft", "f" }, 0);
/* 28 */     this.velocity = mlc.getFloat(new String[] { "velocity", "v" }, 20.0F);
/* 29 */     this.removeDelay = mlc.getInteger(new String[] { "removedelay", "rd", "r" }, 200);
/*    */     
/* 31 */     this.velocity /= 10.0F;
/*    */   }
/*    */   protected int fireTicks; protected int removeDelay;
/*    */   protected float velocity;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 37 */     SkillAdapter.get().executeVolley(data.getCaster(), target, this.amount.get((PlaceholderMeta)data), this.velocity, this.spread, this.fireTicks, this.removeDelay);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 43 */     SkillAdapter.get().executeVolley(data.getCaster(), target.getLocation(), this.amount.get((PlaceholderMeta)data, target), this.velocity, this.spread, this.fireTicks, this.removeDelay);
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ArrowVolleyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */