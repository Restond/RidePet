/*    */ package saukiya.sxattribute.data.attribute.sub.defence;
/*    */ 
/*    */ import com.sucy.skill.SkillAPI;
/*    */ import com.sucy.skill.api.player.PlayerClass;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.UpdateEventData;
/*    */ import github.saukiya.sxattribute.listener.OnHealthChangeDisplayListener;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HealthAttribute
/*    */   extends SubAttribute
/*    */ {
/*    */   private static boolean skillAPI = false;
/*    */   
/*    */   public HealthAttribute() {
/* 32 */     super("Health", 1, new SXAttributeType[] { SXAttributeType.UPDATE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void eventMethod(EventData eventData) {
/* 37 */     if (eventData instanceof UpdateEventData && ((UpdateEventData)eventData).getEntity() instanceof Player) {
/* 38 */       Player player = (Player)((UpdateEventData)eventData).getEntity();
/* 39 */       if (skillAPI) {
/* 40 */         SkillAPI.getPlayerData((OfflinePlayer)player).getAttribute("health");
/*    */       }
/* 42 */       double maxHealth = getAttributes()[0] + getSkillAPIHealth(player);
/* 43 */       if (player.getHealth() > maxHealth) player.setHealth(maxHealth); 
/* 44 */       player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
/* 45 */       int healthScale = Config.getConfig().getInt("HealthScaled.Value");
/* 46 */       if (Config.isHealthScaled() && healthScale < player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
/* 47 */         player.setHealthScaled(true);
/* 48 */         player.setHealthScale(Config.getConfig().getInt("HealthScaled.Value"));
/*    */       } else {
/* 50 */         player.setHealthScaled(false);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private int getSkillAPIHealth(Player player) {
/* 56 */     return skillAPI ? SkillAPI.getPlayerData((OfflinePlayer)player).getClasses().stream().mapToInt(aClass -> (int)aClass.getHealth()).sum() : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 61 */     if (Bukkit.getPluginManager().getPlugin("SkillAPI") != null) {
/* 62 */       skillAPI = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlaceholder(Player player, String string) {
/* 68 */     return string.equalsIgnoreCase("MaxHealth") ? getDf().format(OnHealthChangeDisplayListener.getMaxHealth((LivingEntity)player)) : (
/* 69 */       string.equalsIgnoreCase("Health") ? getDf().format(player.getHealth()) : null);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getPlaceholders() {
/* 74 */     return Arrays.asList(new String[] { "MaxHealth", "Health" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean loadAttribute(String lore) {
/* 79 */     if (lore.contains(Config.getConfig().getString("Attribute.Health.Name"))) {
/* 80 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(lore)).doubleValue();
/* 81 */       return true;
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void correct() {
/* 88 */     if (getAttributes()[0] < 0.0D) getAttributes()[0] = 1.0D;
/*    */   
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 93 */     return getAttributes()[0] * Config.getConfig().getInt("Attribute.Health.Value");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\HealthAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */