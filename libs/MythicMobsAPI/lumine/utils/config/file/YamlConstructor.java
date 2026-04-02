/*    */ package lumine.utils.config.file;
/*    */ 
/*    */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*    */ import org.yaml.snakeyaml.nodes.Tag;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class YamlConstructor
/*    */   extends SafeConstructor
/*    */ {
/*    */   public YamlConstructor() {
/* 15 */     this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject(this, null));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlConstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */