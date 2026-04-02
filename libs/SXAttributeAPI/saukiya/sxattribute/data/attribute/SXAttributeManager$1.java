/*     */ package saukiya.sxattribute.data.attribute;
/*     */ 
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeManager;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.data.eventdata.sub.UpdateEventData;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
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
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends BukkitRunnable
/*     */ {
/*     */   public void run() {
/* 223 */     if (entity instanceof Player) {
/* 224 */       if (Config.isClearDefaultAttributeAll()) {
/* 225 */         SXAttributeManager.access$000(SXAttributeManager.this).getItemUtil().clearAttribute((Player)entity);
/* 226 */       } else if (Config.isClearDefaultAttributeReset()) {
/* 227 */         SXAttributeManager.access$000(SXAttributeManager.this).getItemUtil().removeAttribute((Player)entity);
/*     */       } 
/*     */     }
/* 230 */     UpdateEventData updateEventData = new UpdateEventData(entity);
/* 231 */     SXAttributeData attributeData = SXAttributeManager.this.getEntityData(entity, new SXAttributeData[0]);
/* 232 */     for (SubAttribute subAttribute : attributeData.getAttributeMap().values()) {
/* 233 */       if (subAttribute.containsType(SXAttributeType.UPDATE))
/* 234 */         subAttribute.eventMethod((EventData)updateEventData); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SXAttributeManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */