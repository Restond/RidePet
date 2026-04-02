/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.herocraftonline.heroes.Heroes;
/*    */ import com.herocraftonline.heroes.characters.Hero;
/*    */ import com.herocraftonline.heroes.characters.classes.HeroClass;
/*    */ import com.herocraftonline.heroes.characters.party.HeroParty;
/*    */ import com.herocraftonline.heroes.util.Properties;
/*    */ import com.herocraftonline.heroes.util.Util;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeroesSupport
/*    */ {
/* 28 */   private Heroes heroes = (Heroes)Bukkit.getPluginManager().getPlugin("Heroes");
/*    */ 
/*    */   
/*    */   public Heroes getHeroes() {
/* 32 */     return this.heroes;
/*    */   }
/*    */   
/*    */   public void giveHeroesExp(SkillCaster am, Player player, int amount) {
/* 36 */     if (!ConfigManager.compatHeroesXPEnable)
/* 37 */       return;  if (player == null)
/*    */       return; 
/* 39 */     Hero h = this.heroes.getCharacterManager().getHero(player);
/*    */     
/* 41 */     if (h.hasParty()) {
/* 42 */       HeroParty hp = h.getParty();
/*    */       
/* 44 */       Set<Hero> inRangeMembers = new HashSet<>();
/* 45 */       for (Hero partyMember : hp.getMembers()) {
/* 46 */         if (!player.getLocation().getWorld().equals(partyMember.getPlayer().getLocation().getWorld())) {
/*    */           continue;
/*    */         }
/*    */         
/* 50 */         if (player.getLocation().distanceSquared(partyMember.getPlayer().getLocation()) > 2500.0D) {
/*    */           continue;
/*    */         }
/*    */         
/* 54 */         if (partyMember.canGain(HeroClass.ExperienceType.KILLING)) {
/* 55 */           inRangeMembers.add(partyMember);
/*    */         }
/*    */       } 
/*    */       
/* 59 */       int partySize = inRangeMembers.size();
/* 60 */       double sharedExp = (amount / partySize);
/* 61 */       double bonusExp = (partySize > 1) ? sharedExp : 0.0D;
/*    */       
/* 63 */       if (partySize > 1) {
/* 64 */         bonusExp *= Properties.partyMults[partySize - 1];
/*    */       }
/*    */       
/* 67 */       bonusExp *= Heroes.properties.partyBonus;
/* 68 */       bonusExp = Util.formatDouble(bonusExp);
/*    */       
/* 70 */       for (Hero partyMember : inRangeMembers) {
/* 71 */         partyMember.gainExp(sharedExp + bonusExp, HeroClass.ExperienceType.KILLING, BukkitAdapter.adapt(am.getLocation()));
/* 72 */         sendXPMessage(partyMember.getPlayer(), am, sharedExp + bonusExp);
/*    */       } 
/*    */     } else {
/*    */       
/* 76 */       h.gainExp(amount, HeroClass.ExperienceType.KILLING, BukkitAdapter.adapt(am.getLocation()));
/* 77 */       sendXPMessage(h.getPlayer(), am, amount);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendXPMessage(Player p, SkillCaster am, double amount) {
/* 82 */     if (ConfigManager.compatHeroesShowXPMessage == true) {
/* 83 */       String message = ConfigManager.compatHeroesXPMessageFormat;
/*    */       
/* 85 */       message = SkillString.parseMobVariables(message, am, null, (AbstractEntity)BukkitAdapter.adapt(p));
/* 86 */       message = message.replace("<drops.xp>", String.valueOf(amount));
/* 87 */       p.sendMessage(message);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setMobDamage(LivingEntity l, double damage) {
/* 92 */     this.heroes.getCharacterManager().getMonster(l).setDamage(damage);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HeroesSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */