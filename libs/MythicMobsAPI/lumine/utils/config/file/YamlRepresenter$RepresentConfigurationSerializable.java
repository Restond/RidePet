/*    */ package lumine.utils.config.file;
/*    */ 
/*    */ import io.lumine.utils.config.file.YamlRepresenter;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*    */ import org.yaml.snakeyaml.nodes.Node;
/*    */ import org.yaml.snakeyaml.representer.SafeRepresenter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class RepresentConfigurationSerializable
/*    */   extends SafeRepresenter.RepresentMap
/*    */ {
/*    */   private RepresentConfigurationSerializable() {
/* 27 */     super((SafeRepresenter)paramYamlRepresenter);
/*    */   }
/*    */   public Node representData(Object data) {
/* 30 */     ConfigurationSerializable serializable = (ConfigurationSerializable)data;
/* 31 */     Map<String, Object> values = new LinkedHashMap<>();
/*    */     
/* 33 */     String className = serializable.getClass().getName();
/*    */     
/* 35 */     if (className.endsWith("CraftItemStack")) {
/* 36 */       className = "org.bukkit.inventory.ItemStack";
/*    */     }
/* 38 */     if (className.endsWith("CraftMetaItem")) {
/* 39 */       className = "ItemMeta";
/*    */     }
/* 41 */     if (className.contains("inventory.CraftMeta")) {
/* 42 */       className = "ItemMeta";
/*    */     }
/*    */     
/* 45 */     values.put("==", className);
/* 46 */     values.putAll(serializable.serialize());
/*    */     
/* 48 */     return super.representData(values);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlRepresenter$RepresentConfigurationSerializable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */