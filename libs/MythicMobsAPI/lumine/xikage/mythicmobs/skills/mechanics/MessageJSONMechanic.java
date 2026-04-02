/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import io.lumine.xikage.mythicmobs.util.NMSReflection;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "messagejson", aliases = {"jsonmessage", "jmsg", "jm"}, description = "Sends a JSON-formatted message to the target entity")
/*     */ public class MessageJSONMechanic
/*     */   extends SkillMechanic implements ITargetedEntitySkill {
/*     */   protected static boolean noloop = false;
/*     */   protected PlaceholderString message;
/*     */   private static Constructor<?> nmsPacketPlayOutChatConstructor;
/*     */   private static Object nmsChatSerializerGsonInstance;
/*     */   private static Method fromJsonMethod;
/*     */   
/*     */   public MessageJSONMechanic(String line, MythicLineConfig mlc) {
/*  34 */     super(line, mlc);
/*  35 */     this.target_creative = true;
/*     */     
/*     */     try {
/*  38 */       this.message = mlc.getPlaceholderString(new String[] { "message", "msg", "m" }, null, new String[0]);
/*  39 */     } catch (Exception ex) {
/*  40 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/*  41 */       this.message = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/*  42 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  45 */     if (nmsPacketPlayOutChatConstructor == null) {
/*     */       try {
/*  47 */         nmsPacketPlayOutChatConstructor = NMSReflection.getNMSClass("PacketPlayOutChat").getDeclaredConstructor(new Class[] { NMSReflection.getNMSClass("IChatBaseComponent") });
/*  48 */         nmsPacketPlayOutChatConstructor.setAccessible(true);
/*  49 */       } catch (NoSuchMethodException e) {
/*  50 */         Bukkit.getLogger().log(Level.SEVERE, "Could not find Minecraft method or constructor.", e);
/*  51 */       } catch (SecurityException e) {
/*  52 */         Bukkit.getLogger().log(Level.WARNING, "Could not access constructor.", e);
/*     */       } 
/*     */     }
/*     */     
/*  56 */     MythicMobs.debug(2, "Loaded message skill with message " + this.message);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  61 */     MythicMobs.debug(2, "Executing message skill with message: " + this.message);
/*  62 */     String m = this.message.get((PlaceholderMeta)data, target);
/*     */     
/*  64 */     if (target.isPlayer()) {
/*  65 */       send((CommandSender)target.getBukkitEntity(), m);
/*  66 */       return true;
/*     */     } 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void send(CommandSender sender, String message) {
/*  75 */     if (!(sender instanceof Player)) {
/*     */       return;
/*     */     }
/*  78 */     Player player = (Player)sender;
/*     */     try {
/*  80 */       Object handle = NMSReflection.getHandle(player);
/*  81 */       Object connection = NMSReflection.getField(handle.getClass(), "playerConnection").get(handle);
/*  82 */       NMSReflection.getMethod(connection.getClass(), "sendPacket", new Class[] { NMSReflection.getNMSClass("Packet") }).invoke(connection, new Object[] { createChatPacket(message) });
/*  83 */     } catch (IllegalArgumentException e) {
/*  84 */       Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", e);
/*  85 */     } catch (IllegalAccessException e) {
/*  86 */       Bukkit.getLogger().log(Level.WARNING, "Could not access method.", e);
/*  87 */     } catch (InstantiationException e) {
/*  88 */       Bukkit.getLogger().log(Level.WARNING, "Underlying class is abstract.", e);
/*  89 */     } catch (InvocationTargetException e) {
/*  90 */       Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", e);
/*  91 */     } catch (NoSuchMethodException e) {
/*  92 */       Bukkit.getLogger().log(Level.WARNING, "Could not find method.", e);
/*  93 */     } catch (ClassNotFoundException e) {
/*  94 */       Bukkit.getLogger().log(Level.WARNING, "Could not find class.", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object createChatPacket(String json) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
/* 103 */     if (nmsChatSerializerGsonInstance == null) {
/*     */       Class<?> chatSerializerClazz;
/*     */ 
/*     */       
/* 107 */       if (MythicMobs.inst().getMinecraftVersion() < 9) {
/* 108 */         String version = NMSReflection.getVersion();
/* 109 */         double majorVersion = Double.parseDouble(version.replace('_', '.').substring(1, 4));
/* 110 */         int lesserVersion = Integer.parseInt(version.substring(6, 7));
/*     */         
/* 112 */         if (majorVersion < 1.8D || (majorVersion == 1.8D && lesserVersion == 1)) {
/* 113 */           chatSerializerClazz = NMSReflection.getNMSClass("ChatSerializer");
/*     */         } else {
/* 115 */           chatSerializerClazz = NMSReflection.getNMSClass("IChatBaseComponent$ChatSerializer");
/*     */         } 
/*     */       } else {
/* 118 */         chatSerializerClazz = NMSReflection.getNMSClass("IChatBaseComponent$ChatSerializer");
/*     */       } 
/*     */       
/* 121 */       if (chatSerializerClazz == null) {
/* 122 */         throw new ClassNotFoundException("Can't find the ChatSerializer class");
/*     */       }
/*     */       
/* 125 */       for (Field declaredField : chatSerializerClazz.getDeclaredFields()) {
/* 126 */         if (Modifier.isFinal(declaredField.getModifiers()) && Modifier.isStatic(declaredField.getModifiers()) && declaredField.getType().getName().endsWith("Gson")) {
/*     */           
/* 128 */           declaredField.setAccessible(true);
/* 129 */           nmsChatSerializerGsonInstance = declaredField.get(null);
/* 130 */           fromJsonMethod = nmsChatSerializerGsonInstance.getClass().getMethod("fromJson", new Class[] { String.class, Class.class });
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     Object serializedChatComponent = fromJsonMethod.invoke(nmsChatSerializerGsonInstance, new Object[] { json, NMSReflection.getNMSClass("IChatBaseComponent") });
/*     */     
/* 140 */     return nmsPacketPlayOutChatConstructor.newInstance(new Object[] { serializedChatComponent });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MessageJSONMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */