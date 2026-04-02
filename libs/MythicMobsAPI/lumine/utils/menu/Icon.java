/*     */ package lumine.utils.menu;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ public class Icon<T>
/*     */ {
/*     */   private final Function<T, ItemStack> iconFunc;
/*     */   private final Function<T, Material> materialFunc;
/*     */   private final Function<T, Byte> byteFunction;
/*     */   private final Function<T, Integer> amountFunction;
/*     */   private final Function<T, String> nameFunc;
/*     */   private final Function<T, List<String>> loreFunc;
/*  22 */   private byte data = 0;
/*     */   private final Optional<BiConsumer<T, Player>> clickFunc;
/*     */   private final Optional<BiConsumer<T, Player>> rightClickFunc;
/*     */   private final Optional<Function<T, ItemStack>> blinkFunc;
/*  26 */   private ItemStack itemStack = null;
/*     */   private boolean hideFlags = false;
/*     */   private boolean blink = false;
/*     */   
/*     */   protected Icon(Function<T, Material> materialFunc, Function<T, String> nameFunc, Function<T, List<String>> loreFunc, BiConsumer<T, Player> clickFunc, BiConsumer<T, Player> rclickFunc, Function<T, Byte> byteFunction, Function<T, Integer> amountFunction, Function<T, ItemStack> blinkFunc, boolean hideFlags) {
/*  31 */     this.materialFunc = materialFunc;
/*  32 */     this.iconFunc = null;
/*  33 */     this.nameFunc = nameFunc;
/*  34 */     this.loreFunc = loreFunc;
/*  35 */     this.clickFunc = Optional.ofNullable(clickFunc);
/*  36 */     this.rightClickFunc = Optional.ofNullable(rclickFunc);
/*  37 */     this.byteFunction = byteFunction;
/*  38 */     this.amountFunction = amountFunction;
/*  39 */     this.blinkFunc = Optional.ofNullable(blinkFunc);
/*  40 */     this.hideFlags = hideFlags;
/*     */   }
/*     */   
/*     */   protected Icon(ItemStack itemStack, Function<T, String> nameFunc, Function<T, List<String>> loreFunc, BiConsumer<T, Player> clickFunc, BiConsumer<T, Player> rclickFunc, Function<T, Byte> byteFunction, Function<T, Integer> amountFunction, Function<T, ItemStack> blinkFunc, boolean hideFlags) {
/*  44 */     this.itemStack = itemStack;
/*  45 */     this.iconFunc = null;
/*  46 */     this.materialFunc = null;
/*  47 */     this.nameFunc = nameFunc;
/*  48 */     this.loreFunc = loreFunc;
/*  49 */     this.clickFunc = Optional.ofNullable(clickFunc);
/*  50 */     this.rightClickFunc = Optional.ofNullable(rclickFunc);
/*  51 */     this.byteFunction = byteFunction;
/*  52 */     this.amountFunction = amountFunction;
/*  53 */     this.blinkFunc = Optional.ofNullable(blinkFunc);
/*  54 */     this.hideFlags = hideFlags;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon(Function<T, String> nameFunc, Function<T, List<String>> loreFunc, BiConsumer<T, Player> clickFunc, BiConsumer<T, Player> rclickFunc, Function<T, Byte> byteFunction, Function<T, Integer> amountFunction, Function<T, ItemStack> iconFunc, Function<T, ItemStack> blinkFunc, boolean hideFlags) {
/*  59 */     this.iconFunc = iconFunc;
/*  60 */     this.itemStack = null;
/*  61 */     this.materialFunc = null;
/*  62 */     this.nameFunc = nameFunc;
/*  63 */     this.loreFunc = loreFunc;
/*  64 */     this.clickFunc = Optional.ofNullable(clickFunc);
/*  65 */     this.rightClickFunc = Optional.ofNullable(rclickFunc);
/*  66 */     this.byteFunction = byteFunction;
/*  67 */     this.amountFunction = amountFunction;
/*  68 */     this.blinkFunc = Optional.ofNullable(blinkFunc);
/*  69 */     this.hideFlags = hideFlags;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.Icon<T> data(byte date) {
/*  73 */     this.data = date;
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public ItemStack build(T state) {
/*  78 */     ItemStack stack = null;
/*     */     
/*  80 */     if (this.blinkFunc.isPresent()) {
/*  81 */       this.blink = !this.blink;
/*     */       
/*  83 */       if (this.blink) {
/*  84 */         stack = ((Function<T, ItemStack>)this.blinkFunc.get()).apply(state);
/*     */       }
/*     */     } 
/*  87 */     if (stack == null) {
/*  88 */       if (this.itemStack != null) {
/*  89 */         stack = this.itemStack;
/*  90 */       } else if (this.iconFunc != null) {
/*  91 */         stack = this.iconFunc.apply(state);
/*     */       } else {
/*  93 */         stack = new ItemStack(this.materialFunc.apply(state), getAmount(state), (this.byteFunction == null) ? (short)this.data : (short)((Byte)this.byteFunction.apply(state)).byteValue());
/*     */       } 
/*     */     }
/*     */     
/*  97 */     if (stack.getType() == Material.AIR) {
/*  98 */       return stack;
/*     */     }
/*     */     
/* 101 */     if (this.nameFunc == null && this.loreFunc == null) {
/* 102 */       return stack;
/*     */     }
/*     */     
/* 105 */     ItemMeta meta = stack.getItemMeta();
/* 106 */     if (this.nameFunc != null) {
/* 107 */       meta.setDisplayName(this.nameFunc.apply(state));
/*     */     }
/* 109 */     if (this.loreFunc != null) {
/* 110 */       meta.setLore(this.loreFunc.apply(state));
/*     */     }
/* 112 */     if (this.hideFlags) {
/* 113 */       meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS });
/*     */     }
/* 115 */     stack.setItemMeta(meta);
/*     */     
/* 117 */     return stack;
/*     */   }
/*     */   
/*     */   public int getAmount(T state) {
/* 121 */     return (this.amountFunction == null) ? 1 : ((Integer)this.amountFunction.apply(state)).intValue();
/*     */   }
/*     */   
/*     */   public boolean isBlinkingIcon() {
/* 125 */     return this.blinkFunc.isPresent();
/*     */   }
/*     */   
/*     */   public Optional<BiConsumer<T, Player>> getClickFunc() {
/* 129 */     return this.clickFunc;
/*     */   }
/*     */   
/*     */   public Optional<BiConsumer<T, Player>> getRightClickFunc() {
/* 133 */     return this.rightClickFunc;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\Icon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */