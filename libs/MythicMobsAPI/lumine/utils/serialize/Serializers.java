/*    */ package lumine.utils.serialize;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import io.lumine.utils.gson.JsonBuilder;
/*    */ import io.lumine.utils.serialize.InventorySerialization;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Serializers
/*    */ {
/*    */   public static JsonPrimitive serializeItemstack(ItemStack item) {
/* 18 */     return JsonBuilder.primitiveNonNull(InventorySerialization.encodeItemStackToString(item));
/*    */   }
/*    */   
/*    */   public static ItemStack deserializeItemstack(JsonElement data) {
/* 22 */     Preconditions.checkArgument(data.isJsonPrimitive());
/* 23 */     return InventorySerialization.decodeItemStack(data.getAsString());
/*    */   }
/*    */   
/*    */   public static JsonPrimitive serializeItemstacks(ItemStack[] items) {
/* 27 */     return JsonBuilder.primitiveNonNull(InventorySerialization.encodeItemStacksToString(items));
/*    */   }
/*    */   
/*    */   public static JsonPrimitive serializeInventory(Inventory inventory) {
/* 31 */     return JsonBuilder.primitiveNonNull(InventorySerialization.encodeInventoryToString(inventory));
/*    */   }
/*    */   
/*    */   public static ItemStack[] deserializeItemstacks(JsonElement data) {
/* 35 */     Preconditions.checkArgument(data.isJsonPrimitive());
/* 36 */     return InventorySerialization.decodeItemStacks(data.getAsString());
/*    */   }
/*    */   
/*    */   public static Inventory deserializeInventory(JsonElement data, String title) {
/* 40 */     Preconditions.checkArgument(data.isJsonPrimitive());
/* 41 */     return InventorySerialization.decodeInventory(data.getAsString(), title);
/*    */   }
/*    */   
/*    */   private Serializers() {
/* 45 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\Serializers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */