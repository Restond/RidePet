/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.advancement.Advancement;
/*     */ import org.bukkit.advancement.AdvancementProgress;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "sendtoast", version = "2.8", aliases = {"advancementmessage", "advmessage", "toastmessage", "toastmsg"}, description = "Sends a message to the target player as an advancement")
/*     */ public class SendToastMechanic
/*     */   extends SkillMechanic implements ITargetedEntitySkill {
/*  29 */   private static Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
/*     */   
/*     */   protected PlaceholderString message;
/*     */   protected String icon;
/*     */   protected String frame;
/*     */   
/*     */   public SendToastMechanic(String line, MythicLineConfig mlc) {
/*  36 */     super(line, mlc);
/*  37 */     this.ASYNC_SAFE = false;
/*  38 */     this.target_creative = true;
/*     */     
/*     */     try {
/*  41 */       this.message = PlaceholderString.of(mlc.getString(new String[] { "message", "msg", "m" }));
/*  42 */     } catch (Exception ex) {
/*  43 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/*  44 */       this.message = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/*  45 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  48 */     this.icon = "minecraft:" + mlc.getString(new String[] { "icon", "i" }, "diamond_sword", new String[0]).toLowerCase();
/*  49 */     this.frame = mlc.getString(new String[] { "frame", "f" }, "challenge", new String[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  54 */     if (target.isPlayer()) {
/*  55 */       NamespacedKey id = new NamespacedKey((Plugin)MythicMobs.inst(), "temp-" + UUID.randomUUID().toString());
/*  56 */       String advJSON = build(data, target);
/*  57 */       Bukkit.getUnsafe().loadAdvancement(id, advJSON);
/*  58 */       Player player = (Player)target.asPlayer().getBukkitEntity();
/*  59 */       Advancement advancement = Bukkit.getAdvancement(id);
/*  60 */       AdvancementProgress progress = player.getAdvancementProgress(advancement);
/*     */       
/*  62 */       if (!progress.isDone()) {
/*  63 */         for (String criteria : progress.getRemainingCriteria()) {
/*  64 */           progress.awardCriteria(criteria);
/*     */         }
/*     */       }
/*     */       
/*  68 */       Scheduler.runLaterAsync(() -> { if (paramAdvancementProgress.isDone()) for (String criteria : paramAdvancementProgress.getRemainingCriteria()) paramAdvancementProgress.revokeCriteria(criteria);   Bukkit.getUnsafe().removeAdvancement(paramNamespacedKey); }20L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       return true;
/*     */     } 
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private String build(SkillMetadata data, AbstractEntity target) {
/*  83 */     JsonObject json = new JsonObject();
/*  84 */     JsonObject icon = new JsonObject();
/*  85 */     JsonObject display = new JsonObject();
/*  86 */     JsonObject criteria = new JsonObject();
/*  87 */     JsonObject trigger = new JsonObject();
/*     */     
/*  89 */     icon.addProperty("item", this.icon);
/*     */     
/*  91 */     display.add("icon", (JsonElement)icon);
/*  92 */     display.addProperty("title", this.message.get((PlaceholderMeta)data, target));
/*  93 */     display.addProperty("description", "MythicMobs Toast Message");
/*  94 */     display.addProperty("background", "minecraft:textures/gui/advancements/backgrounds/adventure.png");
/*  95 */     display.addProperty("frame", this.frame);
/*  96 */     display.addProperty("announce_to_chat", Boolean.valueOf(false));
/*  97 */     display.addProperty("show_toast", Boolean.valueOf(true));
/*  98 */     display.addProperty("hidden", Boolean.valueOf(true));
/*     */     
/* 100 */     trigger.addProperty("trigger", "minecraft:impossible");
/*     */     
/* 102 */     criteria.add("impossible", (JsonElement)trigger);
/*     */     
/* 104 */     json.add("criteria", (JsonElement)criteria);
/* 105 */     json.add("display", (JsonElement)display);
/*     */     
/* 107 */     return gson.toJson((JsonElement)json);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SendToastMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */