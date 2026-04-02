/*    */ package lumine.xikage.mythicmobs.players;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   private UUID uniqueId;
/* 13 */   private VariableRegistry variables = new VariableRegistry(); public VariableRegistry getVariables() { return this.variables; }
/*    */   
/*    */   public PlayerData(AbstractPlayer player) {
/* 16 */     this.uniqueId = player.getUniqueId();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\players\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */