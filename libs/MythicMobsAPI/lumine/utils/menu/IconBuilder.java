/*     */ package lumine.utils.menu;
/*     */ 
/*     */ import io.lumine.utils.menu.Icon;
/*     */ import java.util.List;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class IconBuilder<T>
/*     */ {
/*     */   private Function<T, Material> materialFunc;
/*     */   private Function<T, String> nameFunc;
/*     */   private Function<T, List<String>> loreFunc;
/*     */   private Function<T, Byte> dataFunc;
/*     */   private Function<T, Integer> amountFunc;
/*     */   private Function<T, ItemStack> iconFunc;
/*     */   private Function<T, ItemStack> blinkFunc;
/*     */   private BiConsumer<T, Player> clickFunc;
/*     */   private BiConsumer<T, Player> rightClickFunc;
/*     */   private ItemStack itemStack;
/*     */   private boolean hideFlags = false;
/*     */   
/*     */   public static <T> io.lumine.utils.menu.IconBuilder<T> create() {
/*  26 */     return new io.lumine.utils.menu.IconBuilder<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> item(ItemStack itemStack) {
/*  33 */     this.itemStack = itemStack;
/*  34 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> icon(Material material) {
/*  38 */     this.materialFunc = (_state -> paramMaterial);
/*  39 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> icon(Function<T, ItemStack> materialFunc) {
/*  43 */     this.iconFunc = materialFunc;
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> blink(Function<T, ItemStack> materialFunc) {
/*  48 */     this.blinkFunc = materialFunc;
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> material(Material material) {
/*  53 */     this.materialFunc = (_state -> paramMaterial);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> material(Function<T, Material> materialFunc) {
/*  58 */     this.materialFunc = materialFunc;
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> name(String name) {
/*  63 */     this.nameFunc = (_impl -> paramString);
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> name(Function<T, String> nameFunc) {
/*  68 */     this.nameFunc = nameFunc;
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> lore(List<String> lore) {
/*  73 */     this.loreFunc = (_impl -> paramList);
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> lore(Function<T, List<String>> loreFunc) {
/*  78 */     this.loreFunc = loreFunc;
/*  79 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> click(BiConsumer<T, Player> clickFunc) {
/*  83 */     this.clickFunc = clickFunc;
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> rightClick(BiConsumer<T, Player> clickFunc) {
/*  88 */     this.rightClickFunc = clickFunc;
/*  89 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> data(Byte data) {
/*  93 */     this.dataFunc = (_state -> paramByte);
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> data(Function<T, Byte> dataFunc) {
/*  98 */     this.dataFunc = dataFunc;
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> amount(int data) {
/* 103 */     this.amountFunc = (_state -> Integer.valueOf(paramInt));
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> amount(Function<T, Integer> amountFunc) {
/* 108 */     this.amountFunc = amountFunc;
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.IconBuilder<T> hideFlags() {
/* 113 */     this.hideFlags = true;
/* 114 */     return this;
/*     */   }
/*     */   
/*     */   public Icon<T> build() {
/* 118 */     if (this.iconFunc != null)
/* 119 */       return new Icon(this.nameFunc, this.loreFunc, this.clickFunc, this.rightClickFunc, this.dataFunc, this.amountFunc, this.iconFunc, this.blinkFunc, this.hideFlags); 
/* 120 */     if (this.itemStack != null) {
/* 121 */       return new Icon(this.itemStack, this.nameFunc, this.loreFunc, this.clickFunc, this.rightClickFunc, this.dataFunc, this.amountFunc, this.blinkFunc, this.hideFlags);
/*     */     }
/* 123 */     if (this.materialFunc == null) {
/* 124 */       throw new IllegalStateException("Material must be specified!");
/*     */     }
/* 126 */     return new Icon(this.materialFunc, this.nameFunc, this.loreFunc, this.clickFunc, this.rightClickFunc, this.dataFunc, this.amountFunc, this.blinkFunc, this.hideFlags);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\IconBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */