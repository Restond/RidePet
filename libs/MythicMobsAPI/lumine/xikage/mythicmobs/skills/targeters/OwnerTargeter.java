/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "owner", aliases = {}, description = "Targets the caster's owner")
/*    */ public class OwnerTargeter
/*    */   extends IEntitySelector
/*    */ {
/*    */   public OwnerTargeter(MythicLineConfig mlc) {
/* 20 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 25 */     HashSet<AbstractEntity> targets = new HashSet<>();
/* 26 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 27 */       return targets;
/*    */     }
/* 29 */     ActiveMob am = (ActiveMob)data.getCaster();
/*    */     
/* 31 */     if (am.getOwner().isPresent()) {
/* 32 */       UUID uuid = am.getOwner().get();
/* 33 */       Player player = Bukkit.getPlayer(uuid);
/*    */       
/* 35 */       if (player != null) {
/* 36 */         targets.add(BukkitAdapter.adapt(player));
/*    */       }
/*    */     } 
/* 39 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\OwnerTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */