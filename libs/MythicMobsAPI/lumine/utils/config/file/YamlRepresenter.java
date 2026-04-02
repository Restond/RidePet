/*    */ package lumine.utils.config.file;
/*    */ 
/*    */ import io.lumine.utils.config.ConfigurationSection;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*    */ import org.yaml.snakeyaml.representer.Representer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class YamlRepresenter
/*    */   extends Representer
/*    */ {
/*    */   public YamlRepresenter() {
/* 16 */     this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection(this, null));
/* 17 */     this.multiRepresenters.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable(this, null));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\file\YamlRepresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */