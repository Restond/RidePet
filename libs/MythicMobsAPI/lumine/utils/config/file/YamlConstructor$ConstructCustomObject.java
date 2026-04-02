/*    */ package lumine.utils.config.file;
/*    */ 
/*    */ import io.lumine.utils.config.file.YamlConstructor;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*    */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*    */ import org.yaml.snakeyaml.error.YAMLException;
/*    */ import org.yaml.snakeyaml.nodes.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ConstructCustomObject
/*    */   extends SafeConstructor.ConstructYamlMap
/*    */ {
/*    */   private ConstructCustomObject() {
/* 18 */     super((SafeConstructor)paramYamlConstructor);
/*    */   }
/*    */   public Object construct(Node node) {
/* 21 */     if (node.isTwoStepsConstruction()) {
/* 22 */       throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
/*    */     }
/*    */     
/* 25 */     Map<?, ?> raw = (Map<?, ?>)super.construct(node);
/*    */     
/* 27 */     if (raw.containsKey("==")) {
/* 28 */       Map<String, Object> typed = new LinkedHashMap<>(raw.size());
/* 29 */       for (Map.Entry<?, ?> entry : raw.entrySet()) {
/* 30 */         typed.put(entry.getKey().toString(), entry.getValue());
/*    */       }
/*    */       
/*    */       try {
/* 34 */         return ConfigurationSerialization.deserializeObject(typed);
/* 35 */       } catch (IllegalArgumentException ex) {
/* 36 */         throw new YAMLException("Could not deserialize object", ex);
/*    */       } 
/*    */     } 
/*    */     
/* 40 */     return raw;
/*    */   }
/*    */ 
/*    */   
/*    */   public void construct2ndStep(Node node, Object object) {
/* 45 */     throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlConstructor$ConstructCustomObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */