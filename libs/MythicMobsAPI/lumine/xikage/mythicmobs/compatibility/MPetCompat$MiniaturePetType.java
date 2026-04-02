/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.kirelcodes.miniaturepets.MiniaturePets;
/*    */ import com.kirelcodes.miniaturepets.mob.MobContainer;
/*    */ import com.kirelcodes.miniaturepets.utils.APIUtils;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.MPetCompat;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import java.io.File;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
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
/*    */ public class MiniaturePetType
/*    */ {
/*    */   private MobContainer mType;
/*    */   private String display;
/*    */   private boolean showName;
/*    */   private boolean valid = false;
/*    */   
/*    */   public MiniaturePetType(MythicConfig mc) {
/* 37 */     String type = mc.getString("MiniaturePet.Type", null);
/* 38 */     String anchor = mc.getString("MiniaturePet.Anchor", null);
/* 39 */     double speed = mc.getDouble("Options.MovementSpeed", 0.17D);
/*    */     
/* 41 */     this.showName = mc.getBoolean("MiniaturePet.ShowName", false);
/* 42 */     this.showName = mc.getBoolean("MPet.ShowName", this.showName);
/* 43 */     this.display = mc.getColorString("DisplayName", "");
/* 44 */     this.display = mc.getColorString("Display", this.display);
/*    */     
/* 46 */     if (type == null) {
/* 47 */       type = mc.getString("MPet.Type", null);
/*    */     }
/* 49 */     if (anchor == null) {
/* 50 */       anchor = mc.getString("MPet.Anchor", null);
/*    */     }
/*    */     try {
/* 53 */       EntityType anchorType = EntityType.valueOf(anchor.toUpperCase());
/*    */       
/* 55 */       String path = MiniaturePets.getInstance().getDataFolder().getAbsolutePath() + "/pets/" + type + ".mpet";
/* 56 */       File file = new File(path);
/*    */       
/* 58 */       if (!file.exists()) {
/* 59 */         MythicLogger.errorCompatibility("MiniaturePets", "Could not find file for pet type '" + type + "' at '" + path + "'");
/*    */         
/*    */         return;
/*    */       } 
/* 63 */       this.mType = APIUtils.loadContainer(file);
/*    */       
/* 65 */       this.mType.setAnchor(anchorType);
/* 66 */       this.mType.setSpeed(speed);
/* 67 */       this.valid = true;
/* 68 */     } catch (Exception ex) {
/* 69 */       MythicMobs.error("Invalid MiniaturePet Anchor type: " + anchor);
/* 70 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 75 */     return this.valid;
/*    */   }
/*    */   
/*    */   public Entity spawn(AbstractLocation location) {
/* 79 */     MPetCompat.MythicMPet mythicMPet = new MPetCompat.MythicMPet(MPetCompat.this, BukkitAdapter.adapt(location), this.mType);
/*    */     
/* 81 */     if (this.showName) {
/* 82 */       mythicMPet.setCustomName(this.display);
/*    */     }
/*    */     
/* 85 */     return (Entity)mythicMPet.getNavigator();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\MPetCompat$MiniaturePetType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */