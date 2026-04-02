/*     */ package saukiya.sxattribute.data.attribute;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.AttributeMap;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.IntStream;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SubAttribute
/*     */ {
/*     */   private static String firstPerson;
/*     */   private final String name;
/*     */   private final SXAttributeType[] attributeTypes;
/*  28 */   static final AttributeMap attributeMap = new AttributeMap(); private final double[] doubles;
/*     */   public static void setFirstPerson(String firstPerson) {
/*  30 */     github.saukiya.sxattribute.data.attribute.SubAttribute.firstPerson = firstPerson; } public static String getFirstPerson() {
/*  31 */     return firstPerson;
/*     */   }
/*     */   public String getName() {
/*  34 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private JavaPlugin plugin = null;
/*     */   public DecimalFormat getDf() {
/*  43 */     return this.df;
/*  44 */   } private DecimalFormat df = SXAttribute.getDf();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubAttribute(String name, int doublesLength, SXAttributeType... attributeTypes) {
/*  55 */     this.name = name;
/*  56 */     this.attributeTypes = attributeTypes;
/*  57 */     this.doubles = new double[doublesLength];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean probability(double d) {
/*  67 */     return (d > 0.0D && d / 100.0D > SXAttribute.getRandom().nextDouble());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumber(String lore) {
/*  78 */     String str = lore.replaceAll("§+[a-z0-9]", "").replaceAll("[^-0-9.]", "");
/*  79 */     return (str.length() == 0 || str.replaceAll("[^.]", "").length() > 1) ? "0" : str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final SXAttributeType[] getType() {
/*  88 */     return (SXAttributeType[])this.attributeTypes.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean containsType(SXAttributeType attributeType) {
/*  98 */     return Arrays.<SXAttributeType>stream(this.attributeTypes).anyMatch(type -> type.equals(attributeType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void registerAttribute(JavaPlugin plugin) {
/* 108 */     if (plugin == null) {
/* 109 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cAttribute >> §4" + getName() + " §cNull Plugin!");
/* 110 */     } else if (getPriority() < 0) {
/* 111 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §8Attribute >> §4" + getName() + " §8Disable!");
/* 112 */     } else if (this.attributeTypes.length == 0) {
/* 113 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §cAttribute >> §4" + getName() + " §cNo SXAttributeType!");
/* 114 */     } else if (SXAttribute.isPluginEnabled()) {
/* 115 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §cAttribute >> §cSXAttribute is Enabled §4" + getName() + "§r !");
/*     */     } else {
/* 117 */       this.plugin = plugin;
/* 118 */       github.saukiya.sxattribute.data.attribute.SubAttribute attribute = attributeMap.put(Integer.valueOf(getPriority()), this);
/* 119 */       if (attribute == null) {
/* 120 */         Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] Attribute >> Register §c" + getName() + " §rTo Priority §c" + getPriority() + " §r!");
/*     */       } else {
/* 122 */         Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] Attribute >> The §c" + getName() + " §rCover To §c" + attribute.getName() + " §7[§c" + attribute.getPlugin() + "§7]§r !");
/*     */       } 
/* 124 */       attributeMap.put(Integer.valueOf(getPriority()), this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> introduction() {
/* 149 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   github.saukiya.sxattribute.data.attribute.SubAttribute newAttribute() {
/*     */     try {
/* 159 */       return ((github.saukiya.sxattribute.data.attribute.SubAttribute)getClass().newInstance()).setPlugin(this.plugin);
/* 160 */     } catch (InstantiationException|IllegalAccessException e) {
/* 161 */       Bukkit.getConsoleSender().sendMessage("§r[§c" + this.plugin.getName() + "§r] Attribute >> §4" + getName() + " §cConstructors Error §r!");
/* 162 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPriority() {
/* 196 */     return Config.getConfig().getInt("AttributePriority." + getName(), -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double[] getAttributes() {
/* 205 */     return this.doubles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setAttributes(Double... doubles) {
/* 214 */     IntStream.range(0, this.doubles.length).forEach(i -> this.doubles[i] = doubles[i].doubleValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addAttribute(double[] doubles) {
/* 223 */     IntStream.range(0, this.doubles.length).forEach(i -> this.doubles[i] = this.doubles[i] + doubles[i]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void loadFromString(String attributeString) {
/* 243 */     String[] args1 = attributeString.split("#");
/* 244 */     if (args1[0].equals(getName())) {
/* 245 */       List<String> list = args1[1].contains("/") ? Arrays.<String>asList(args1[1].split("/")) : Collections.<String>singletonList(args1[1]);
/* 246 */       setAttributes((Double[])list.stream().filter(s -> (s.length() > 0)).map(Double::valueOf).toArray(x$0 -> new Double[x$0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String saveToString() {
/* 259 */     if (Arrays.stream(this.doubles, 0, this.doubles.length).anyMatch(aDouble -> (aDouble != 0.0D))) {
/* 260 */       return getName() + "#" + (String)IntStream.range(0, this.doubles.length).<CharSequence>mapToObj(i -> String.valueOf((i == this.doubles.length - 1) ? Double.valueOf(this.doubles[i]) : (this.doubles[i] + "/"))).collect(Collectors.joining());
/*     */     }
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void correct() {
/* 269 */     IntStream.range(0, this.doubles.length).filter(i -> (this.doubles[i] < 0.0D)).forEach(i -> this.doubles[i] = 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected github.saukiya.sxattribute.data.attribute.SubAttribute clone() {
/*     */     try {
/* 287 */       return (github.saukiya.sxattribute.data.attribute.SubAttribute)super.clone();
/* 288 */     } catch (CloneNotSupportedException e) {
/* 289 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JavaPlugin getPlugin() {
/* 299 */     return this.plugin;
/*     */   }
/*     */   
/*     */   github.saukiya.sxattribute.data.attribute.SubAttribute setPlugin(JavaPlugin plugin) {
/* 303 */     this.plugin = plugin;
/* 304 */     return this;
/*     */   }
/*     */   
/*     */   public abstract void eventMethod(EventData paramEventData);
/*     */   
/*     */   public abstract String getPlaceholder(Player paramPlayer, String paramString);
/*     */   
/*     */   public abstract List<String> getPlaceholders();
/*     */   
/*     */   public abstract boolean loadAttribute(String paramString);
/*     */   
/*     */   public abstract double getValue();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SubAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */