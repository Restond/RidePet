/*    */ package lumine.xikage.mythicmobs.players;
/*    */ 
/*    */ import com.google.gson.GsonBuilder;
/*    */ import io.lumine.utils.serialization.SerializingModule;
/*    */ import io.lumine.utils.serialization.WrappedJsonFile;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*    */ import io.lumine.xikage.mythicmobs.players.PlayerData;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableSerializer;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTCompoundSerializer;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTSerializer;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*    */ import java.io.File;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class PlayerManager
/*    */   extends SerializingModule
/*    */   implements Terminable
/*    */ {
/*    */   protected final MythicMobs core;
/* 29 */   protected Map<UUID, WrappedJsonFile<PlayerData>> playerData = new ConcurrentHashMap<>();
/*    */   
/*    */   public PlayerManager(MythicMobs core) {
/* 32 */     super((JavaPlugin)core, "SavedData");
/*    */     
/* 34 */     this.core = core;
/*    */     
/* 36 */     this
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       .GSON = (new GsonBuilder()).enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(AbstractWorld.class, this.GSON.getAdapter(BukkitWorld.class)).registerTypeAdapter(Tag.class, new NBTSerializer()).registerTypeAdapter(CompoundTag.class, new NBTCompoundSerializer()).registerTypeAdapter(Variable.class, new VariableSerializer()).create();
/*    */     
/* 46 */     for (File file : getModuleFiles("players")) {
/* 47 */       if (file.getName().endsWith(".json")) {
/*    */         try {
/* 49 */           UUID uuid = UUID.fromString(file.getName().split("\\.")[0]);
/* 50 */           WrappedJsonFile<PlayerData> loaded = loadFile(file, PlayerData.class);
/*    */           
/* 52 */           if (loaded != null) {
/* 53 */             this.playerData.put(uuid, loaded);
/*    */           }
/* 55 */         } catch (Exception ex) {
/* 56 */           ex.printStackTrace();
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 63 */     saveAll();
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public PlayerData getPlayerData(AbstractPlayer player) {
/* 68 */     if (!this.playerData.containsKey(player.getUniqueId())) {
/* 69 */       WrappedJsonFile<PlayerData> f = new WrappedJsonFile(this, new File(getBasedir(), "players/" + player.getUniqueId() + ".json"), new PlayerData(player));
/* 70 */       this.playerData.put(player.getUniqueId(), f);
/*    */     } 
/* 72 */     return (PlayerData)((WrappedJsonFile)this.playerData.get(player.getUniqueId())).get();
/*    */   }
/*    */   
/*    */   public void saveAll() {
/* 76 */     this.playerData.forEach((uuid, file) -> {
/*    */           if (file == null)
/*    */             return; 
/*    */           ((PlayerData)file.get()).getVariables().unload();
/*    */           file.save();
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\players\PlayerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */