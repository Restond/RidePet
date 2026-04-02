/*    */ package lumine.utils.config.file;
/*    */ 
/*    */ import io.lumine.utils.config.ConfigurationSection;
/*    */ import io.lumine.utils.config.file.YamlRepresenter;
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
/*    */ class RepresentConfigurationSection
/*    */   extends SafeRepresenter.RepresentMap
/*    */ {
/*    */   private RepresentConfigurationSection() {
/* 20 */     super((SafeRepresenter)paramYamlRepresenter);
/*    */   }
/*    */   public Node representData(Object data) {
/* 23 */     return super.representData(((ConfigurationSection)data).getValues(false));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlRepresenter$RepresentConfigurationSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */