/*     */ package lumine.utils.serialize;
/*     */ 
/*     */ import io.lumine.utils.serialize.Base64Util;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.io.BukkitObjectInputStream;
/*     */ import org.bukkit.util.io.BukkitObjectOutputStream;
/*     */ 
/*     */ public final class InventorySerialization
/*     */ {
/*     */   public static byte[] encodeItemStack(ItemStack item) {
/*  16 */     try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
/*  17 */         BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
/*  18 */       dataOutput.writeObject(item);
/*  19 */       return outputStream.toByteArray();
/*     */     }
/*  21 */     catch (IOException e) {
/*  22 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String encodeItemStackToString(ItemStack item) {
/*  27 */     return Base64Util.encode(encodeItemStack(item));
/*     */   }
/*     */   
/*     */   public static ItemStack decodeItemStack(byte[] buf) {
/*  31 */     try(ByteArrayInputStream inputStream = new ByteArrayInputStream(buf); 
/*  32 */         BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
/*  33 */       return (ItemStack)dataInput.readObject();
/*     */     }
/*  35 */     catch (ClassNotFoundException|IOException e) {
/*  36 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ItemStack decodeItemStack(String data) {
/*  41 */     return decodeItemStack(Base64Util.decode(data));
/*     */   }
/*     */   
/*     */   public static byte[] encodeItemStacks(ItemStack[] items) {
/*  45 */     try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
/*  46 */         BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
/*  47 */       dataOutput.writeInt(items.length);
/*  48 */       for (ItemStack item : items) {
/*  49 */         dataOutput.writeObject(item);
/*     */       }
/*  51 */       return outputStream.toByteArray();
/*     */     }
/*  53 */     catch (IOException e) {
/*  54 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String encodeItemStacksToString(ItemStack[] items) {
/*  59 */     return Base64Util.encode(encodeItemStacks(items));
/*     */   }
/*     */   
/*     */   public static ItemStack[] decodeItemStacks(byte[] buf) {
/*  63 */     try(ByteArrayInputStream inputStream = new ByteArrayInputStream(buf); 
/*  64 */         BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
/*  65 */       ItemStack[] items = new ItemStack[dataInput.readInt()];
/*  66 */       for (int i = 0; i < items.length; i++) {
/*  67 */         items[i] = (ItemStack)dataInput.readObject();
/*     */       }
/*  69 */       return items;
/*     */     }
/*  71 */     catch (ClassNotFoundException|IOException e) {
/*  72 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ItemStack[] decodeItemStacks(String data) {
/*  77 */     return decodeItemStacks(Base64Util.decode(data));
/*     */   }
/*     */   
/*     */   public static byte[] encodeInventory(Inventory inventory) {
/*  81 */     try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
/*  82 */         BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
/*  83 */       dataOutput.writeInt(inventory.getSize());
/*  84 */       for (int i = 0; i < inventory.getSize(); i++) {
/*  85 */         dataOutput.writeObject(inventory.getItem(i));
/*     */       }
/*  87 */       return outputStream.toByteArray();
/*     */     }
/*  89 */     catch (IOException e) {
/*  90 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String encodeInventoryToString(Inventory inventory) {
/*  95 */     return Base64Util.encode(encodeInventory(inventory));
/*     */   }
/*     */   
/*     */   public static Inventory decodeInventory(byte[] buf, String title) {
/*  99 */     try(ByteArrayInputStream inputStream = new ByteArrayInputStream(buf); 
/* 100 */         BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
/* 101 */       Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt(), title);
/* 102 */       for (int i = 0; i < inventory.getSize(); i++) {
/* 103 */         inventory.setItem(i, (ItemStack)dataInput.readObject());
/*     */       }
/* 105 */       return inventory;
/*     */     }
/* 107 */     catch (ClassNotFoundException|IOException e) {
/* 108 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Inventory decodeInventory(String data, String title) {
/* 113 */     return decodeInventory(Base64Util.decode(data), title);
/*     */   }
/*     */   
/*     */   private InventorySerialization() {
/* 117 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialize\InventorySerialization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */