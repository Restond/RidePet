/*    */ package lumine.xikage.mythicmobs.adapters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractSkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitSkillAdapter;
/*    */ 
/*    */ public class SkillAdapter
/*    */ {
/*    */   protected static AbstractSkillAdapter adapter;
/*    */   
/*    */   public static void initializeBukkit() {
/* 11 */     adapter = (AbstractSkillAdapter)new BukkitSkillAdapter();
/*    */   }
/*    */   
/*    */   public static AbstractSkillAdapter get() {
/* 15 */     return adapter;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\SkillAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */