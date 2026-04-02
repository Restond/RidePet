/*      */ package lumine.xikage.mythicmobs.compatibility;
/*      */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*      */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*      */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*      */ import io.lumine.xikage.mythicmobs.io.GenericConfig;
/*      */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*      */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*      */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*      */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*      */ import java.util.Optional;
/*      */ import me.libraryaddict.disguise.DisguiseAPI;
/*      */ import me.libraryaddict.disguise.disguisetypes.Disguise;
/*      */ import me.libraryaddict.disguise.disguisetypes.DisguiseType;
/*      */ import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
/*      */ import me.libraryaddict.disguise.disguisetypes.MobDisguise;
/*      */ import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.AgeableWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.AreaEffectCloudWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.CatWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.DonkeyWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.DroppedItemWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.HorseWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.LlamaWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.MuleWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.PandaWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.ParrotWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.PolarBearWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.SheepWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.SkeletonHorseWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.TurtleWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.VillagerWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.WolfWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.ZombieHorseWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.ZombieVillagerWatcher;
/*      */ import me.libraryaddict.disguise.disguisetypes.watchers.ZombieWatcher;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.util.EulerAngle;
/*      */ 
/*      */ public class LibsDisguisesSupport {
/*      */   public boolean enabled = false;
/*      */   public LibsDisguises ld;
/*      */   
/*      */   public LibsDisguisesSupport() {
/*   52 */     this.ld = (LibsDisguises)Bukkit.getPluginManager().getPlugin("LibsDisguises");
/*      */   }
/*      */   
/*      */   public void setDisguise(ActiveMob am, String disguise) {
/*   56 */     MythicMob mm = am.getType();
/*   57 */     MythicConfig mc = am.getType().getConfig();
/*      */     
/*   59 */     if (disguise == null) {
/*   60 */       disguise = mc.getString("Options.Disguise");
/*      */     }
/*      */     
/*   63 */     Entity e = am.getEntity().getBukkitEntity();
/*   64 */     Disguise fd = getDisguise(disguise, (GenericConfig)mc);
/*      */     
/*   66 */     if (fd instanceof PlayerDisguise) {
/*   67 */       PlayerWatcher pw = (PlayerWatcher)fd.getWatcher();
/*      */       
/*   69 */       String name = SkillString.parseMobVariables(((PlayerDisguise)fd).getName(), (SkillCaster)am, null, null);
/*      */       
/*   71 */       if (name != null) {
/*   72 */         name = ChatColor.translateAlternateColorCodes('&', name);
/*      */         
/*   74 */         if (name.equals(" ")) {
/*   75 */           am.setShowCustomNameplate(true);
/*      */         }
/*      */       } 
/*      */       
/*      */       try {
/*   80 */         pw.setCustomName(am.getDisplayName());
/*   81 */         pw.setCustomNameVisible(true);
/*   82 */         MythicMobs.debug(1, "Set disguise custom name to " + am.getDisplayName());
/*   83 */       } catch (NoSuchMethodError ex) {
/*   84 */         MythicMobs.inst().handleError(ex);
/*      */       } 
/*   86 */       fd.setWatcher((FlagWatcher)pw);
/*   87 */     } else if (fd instanceof MobDisguise) {
/*   88 */       if (am.getType().getConfig().getBoolean("Disguise.ShowName", true) == true) {
/*   89 */         FlagWatcher watcher = fd.getWatcher();
/*      */         
/*      */         try {
/*   92 */           watcher.setCustomName(am.getDisplayName());
/*   93 */           watcher.setCustomNameVisible(true);
/*   94 */         } catch (NoSuchMethodError ex) {
/*   95 */           MythicMobs.inst().handleError(ex);
/*      */         }
/*      */       
/*      */       } 
/*   99 */     } else if (am.getType().getConfig().getBoolean("Disguise.ShowName", false) == true) {
/*  100 */       FlagWatcher watcher = fd.getWatcher();
/*      */       
/*      */       try {
/*  103 */         watcher.setCustomName(am.getDisplayName());
/*  104 */         watcher.setCustomNameVisible(true);
/*  105 */       } catch (NoSuchMethodError noSuchMethodError) {}
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  110 */       DisguiseAPI.disguiseToAll(e, fd);
/*  111 */     } catch (NullPointerException ex) {
/*  112 */       MythicMobs.error("(LibsDisguises) Error applying Disguise: disguise may be configured incorrectly.");
/*      */       
/*      */       return;
/*      */     } 
/*  116 */     (new Object(this, e, fd))
/*      */ 
/*      */ 
/*      */       
/*  120 */       .runTaskLater((Plugin)MythicMobs.inst(), 10L);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisguise(AbstractEntity entity, MythicLineConfig mc) {
/*  125 */     Entity e = BukkitAdapter.adapt(entity);
/*  126 */     String disguise = mc.getString(new String[] { "type", "disguise", "d" }, "player:Xikage", new String[0]);
/*      */     
/*  128 */     Disguise fd = getDisguise(disguise, (GenericConfig)mc);
/*      */     
/*  130 */     if (mc.getBoolean("showname", true) == true) {
/*  131 */       FlagWatcher watcher = fd.getWatcher();
/*      */       
/*      */       try {
/*  134 */         watcher.setCustomNameVisible(true);
/*  135 */       } catch (NoSuchMethodError ex) {
/*  136 */         MythicMobs.inst().handleError(ex);
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/*  141 */       DisguiseAPI.disguiseToAll(e, fd);
/*  142 */     } catch (NullPointerException ex) {
/*  143 */       MythicLogger.error("(LibsDisguises) Error applying Disguise: disguise may be configured incorrectly.");
/*  144 */       ex.printStackTrace();
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeDisguise(AbstractEntity entity) {
/*  156 */     Scheduler.runSync(() -> DisguiseAPI.undisguiseToAll(paramAbstractEntity.getBukkitEntity()));
/*      */   }
/*      */   
/*      */   public Disguise getDisguise(String disguise, GenericConfig mc) {
/*      */     MythicConfig mythicConfig;
/*      */     PlayerDisguise playerDisguise;
/*  162 */     if (mc instanceof MythicConfig) {
/*  163 */       mythicConfig = new MythicConfig(((MythicConfig)mc).getKey() + ".Disguise", ((MythicConfig)mc).getFile(), ((MythicConfig)mc).getFileConfiguration());
/*      */     }
/*      */     
/*  166 */     String[] split = disguise.split(":");
/*      */     
/*  168 */     String dn = split[0];
/*  169 */     String data = null;
/*      */     
/*  171 */     if (split.length > 1) {
/*  172 */       data = split[1];
/*      */     }
/*      */     
/*  175 */     Disguise d = null; try {
/*      */       MobDisguise mobDisguise12; MiscDisguise miscDisguise12; MobDisguise mobDisguise11; MiscDisguise miscDisguise11; MobDisguise mobDisguise10; MiscDisguise miscDisguise10; MobDisguise mobDisguise9; MiscDisguise miscDisguise9; MobDisguise mobDisguise8; MiscDisguise miscDisguise8; MobDisguise mobDisguise7; MiscDisguise miscDisguise7; MobDisguise mobDisguise6; MiscDisguise miscDisguise6; MobDisguise mobDisguise5; MiscDisguise miscDisguise5; MobDisguise mobDisguise4; MiscDisguise miscDisguise4; MobDisguise mobDisguise3; MiscDisguise miscDisguise3; MobDisguise mobDisguise2; MiscDisguise miscDisguise2; MobDisguise mobDisguise1; MiscDisguise miscDisguise1; boolean has_arms; String block_id; CatWatcher catWatcher; AgeableWatcher cww; CreeperWatcher creeperWatcher; DonkeyWatcher donkeyWatcher; ZombieWatcher zwb; FoxWatcher foxWatcher; HorseWatcher horseWatcher; ZombieWatcher zombieWatcher1; String item_id; LlamaWatcher llamaWatcher; SlimeWatcher mcw; MuleWatcher muleWatcher; PandaWatcher pandaWatcher; ParrotWatcher parrotWatcher; PhantomWatcher phantomWatcher; ZombieWatcher w; String player, player2; PolarBearWatcher polarBearWatcher; SheepWatcher shw; SlimeWatcher slw; SkeletonHorseWatcher hw; TurtleWatcher watcher; VillagerWatcher vw; WolfWatcher ww; boolean small; short block_dv; String catType; boolean bool2; String horseArmor; short item_dv; boolean hasSaddle; int slimesize; boolean bool1; String variant; int size; String skin, skin2, color; int slimessize; boolean horseSaddled; String villagerType; boolean angry, invisible; FallingBlockWatcher fbw; boolean bool3; String horseColor; DroppedItemWatcher diw; boolean carryingChest, horseChest; WolfWatcher bww; ZombieWatcher zombieWatcher2; ZombieHorseWatcher zombieHorseWatcher; ZombieVillagerWatcher zw, zwbv; AreaEffectCloudWatcher areaEffectCloudWatcher; boolean baseplate; Optional<MythicItem> optional1; boolean bool5; Optional<MythicItem> mi; boolean isGrazing, angry2, bool4; ArmorStandWatcher asw; Material m; String horseStyle; ItemStack ditemstack; boolean bool6; String headPose; boolean bool7;
/*      */       String bodyPose, lArmPose, rArmPose, lLegPose, rLegPose;
/*  178 */       switch (dn.toLowerCase()) { case "armor_stand":
/*      */         case "armorstand":
/*  180 */           mobDisguise12 = new MobDisguise(DisguiseType.ARMOR_STAND);
/*      */           
/*  182 */           has_arms = mythicConfig.getBoolean("HasArms", true);
/*  183 */           small = mythicConfig.getBoolean("Small", false);
/*  184 */           invisible = mythicConfig.getBoolean("Invisible", false);
/*  185 */           baseplate = mythicConfig.getBoolean("HideBasePlate", false);
/*      */           
/*  187 */           asw = (ArmorStandWatcher)mobDisguise12.getWatcher();
/*      */           
/*  189 */           asw.setShowArms(has_arms);
/*  190 */           asw.setSmall(small);
/*  191 */           asw.setInvisible(invisible);
/*  192 */           asw.setNoBasePlate(baseplate);
/*      */ 
/*      */           
/*  195 */           headPose = mythicConfig.getString("Pose.Head", null);
/*      */           
/*  197 */           if (headPose != null) {
/*      */             try {
/*  199 */               String[] sp = headPose.split(",");
/*  200 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  201 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  202 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  204 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  205 */               asw.setHead(a);
/*  206 */             } catch (Exception ex) {
/*  207 */               MythicMobs.error("ArmorStand Disguise Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  208 */               if (ConfigManager.debugLevel > 0) {
/*  209 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  214 */           bodyPose = mythicConfig.getString("Pose.Body", null);
/*  215 */           if (bodyPose != null) {
/*      */             try {
/*  217 */               String[] sp = headPose.split(",");
/*  218 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  219 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  220 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  222 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  223 */               asw.setBody(a);
/*  224 */             } catch (Exception ex) {
/*  225 */               MythicMobs.error("ArmorStand Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  226 */               if (ConfigManager.debugLevel > 0) {
/*  227 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  232 */           lArmPose = mythicConfig.getString("Pose.LeftArm", null);
/*  233 */           if (lArmPose != null) {
/*      */             try {
/*  235 */               String[] sp = headPose.split(",");
/*  236 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  237 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  238 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  240 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  241 */               asw.setLeftArm(a);
/*  242 */             } catch (Exception ex) {
/*  243 */               MythicMobs.error("ArmorStand Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  244 */               if (ConfigManager.debugLevel > 0) {
/*  245 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  250 */           rArmPose = mythicConfig.getString("Pose.RightArm", null);
/*  251 */           if (rArmPose != null) {
/*      */             try {
/*  253 */               String[] sp = headPose.split(",");
/*  254 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  255 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  256 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  258 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  259 */               asw.setRightArm(a);
/*  260 */             } catch (Exception ex) {
/*  261 */               MythicMobs.error("ArmorStand Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  262 */               if (ConfigManager.debugLevel > 0) {
/*  263 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  268 */           lLegPose = mythicConfig.getString("Pose.LeftLeg", null);
/*  269 */           if (lLegPose != null) {
/*      */             try {
/*  271 */               String[] sp = headPose.split(",");
/*  272 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  273 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  274 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  276 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  277 */               asw.setLeftLeg(a);
/*  278 */             } catch (Exception ex) {
/*  279 */               MythicMobs.error("ArmorStand Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  280 */               if (ConfigManager.debugLevel > 0) {
/*  281 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  286 */           rLegPose = mythicConfig.getString("Pose.RightLeg", null);
/*  287 */           if (rLegPose != null) {
/*      */             try {
/*  289 */               String[] sp = headPose.split(",");
/*  290 */               double poseP = Double.valueOf(sp[0]).doubleValue();
/*  291 */               double poseY = Double.valueOf(sp[1]).doubleValue();
/*  292 */               double poseA = Double.valueOf(sp[2]).doubleValue();
/*      */               
/*  294 */               EulerAngle a = new EulerAngle((float)poseP, (float)poseY, (float)poseA);
/*  295 */               asw.setRightLeg(a);
/*  296 */             } catch (Exception ex) {
/*  297 */               MythicMobs.error("ArmorStand Pose is configured incorrectly: must be in format: pitch,yaw,angle");
/*  298 */               if (ConfigManager.debugLevel > 0) {
/*  299 */                 ex.printStackTrace();
/*      */               }
/*      */             } 
/*      */           }
/*      */           break;
/*      */         
/*      */         case "arrow":
/*  306 */           miscDisguise12 = new MiscDisguise(DisguiseType.ARROW);
/*      */           break;
/*      */         case "bat":
/*  309 */           mobDisguise11 = new MobDisguise(DisguiseType.BAT, true);
/*      */           break;
/*      */         case "boat":
/*  312 */           miscDisguise11 = new MiscDisguise(DisguiseType.BOAT);
/*      */           break;
/*      */         case "blaze":
/*  315 */           mobDisguise10 = new MobDisguise(DisguiseType.BLAZE, true);
/*      */           break;
/*      */         case "block":
/*      */         case "falling_block":
/*  319 */           block_id = mythicConfig.getString("Block", data);
/*  320 */           block_dv = (short)mythicConfig.getInteger("BlockData", 0);
/*      */           
/*  322 */           miscDisguise10 = new MiscDisguise(DisguiseType.FALLING_BLOCK);
/*      */           
/*  324 */           fbw = (FallingBlockWatcher)miscDisguise10.getWatcher();
/*      */           
/*  326 */           optional1 = MythicMobs.inst().getItemManager().getItem(block_id);
/*      */           
/*  328 */           if (optional1.isPresent()) {
/*  329 */             ItemStack i = BukkitAdapter.adapt(((MythicItem)optional1.get()).generateItemStack(1));
/*  330 */             fbw.setBlock(i); break;
/*      */           } 
/*  332 */           m = Material.getMaterial(block_id.toUpperCase());
/*      */           
/*  334 */           if (m != null) {
/*  335 */             ItemStack i = new ItemStack(m, 1, block_dv);
/*  336 */             i.setDurability(block_dv);
/*      */             
/*  338 */             fbw.setBlock(i);
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case "cat":
/*  344 */           mobDisguise9 = new MobDisguise(DisguiseType.CAT, true);
/*      */           
/*  346 */           catWatcher = (CatWatcher)mobDisguise9.getWatcher();
/*      */           
/*  348 */           catType = mythicConfig.getString("CatType", null);
/*  349 */           if (catType != null) {
/*  350 */             catWatcher.setType(Cat.Type.valueOf(catType.toUpperCase()));
/*      */           }
/*      */           break;
/*      */         case "cat_baby":
/*      */         case "baby_cat":
/*      */         case "kitten":
/*  356 */           mobDisguise9 = new MobDisguise(DisguiseType.CAT, true);
/*      */           
/*  358 */           catWatcher = (CatWatcher)mobDisguise9.getWatcher();
/*      */           
/*  360 */           catWatcher.setBaby();
/*      */           
/*  362 */           catType = mythicConfig.getString("CatType", null);
/*  363 */           if (catType != null) {
/*  364 */             catWatcher.setType(Cat.Type.valueOf(catType.toUpperCase()));
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "cave_spider":
/*  370 */           mobDisguise9 = new MobDisguise(DisguiseType.CAVE_SPIDER, true);
/*      */           break;
/*      */         
/*      */         case "chicken":
/*  374 */           mobDisguise9 = new MobDisguise(DisguiseType.CHICKEN, true);
/*      */           break;
/*      */         
/*      */         case "cod":
/*  378 */           mobDisguise9 = new MobDisguise(DisguiseType.COD, true);
/*      */           break;
/*      */         
/*      */         case "cow":
/*  382 */           mobDisguise9 = new MobDisguise(DisguiseType.COW, true);
/*      */           break;
/*      */         case "cow_baby":
/*      */         case "baby_cow":
/*  386 */           mobDisguise9 = new MobDisguise(DisguiseType.COW, true);
/*  387 */           cww = (AgeableWatcher)mobDisguise9.getWatcher();
/*      */           
/*  389 */           cww.setBaby();
/*      */           break;
/*      */ 
/*      */         
/*      */         case "creeper":
/*  394 */           mobDisguise9 = new MobDisguise(DisguiseType.CREEPER, true);
/*      */           break;
/*      */         case "creeper_powered":
/*      */         case "powered_creeper":
/*  398 */           mobDisguise9 = new MobDisguise(DisguiseType.CREEPER, true);
/*  399 */           creeperWatcher = (CreeperWatcher)mobDisguise9.getWatcher();
/*  400 */           creeperWatcher.setPowered(true);
/*      */           break;
/*      */         
/*      */         case "dolphin":
/*  404 */           mobDisguise9 = new MobDisguise(DisguiseType.DOLPHIN, true);
/*      */           break;
/*      */         case "dolphin_baby":
/*      */         case "baby_dolphin":
/*  408 */           mobDisguise9 = new MobDisguise(DisguiseType.DOLPHIN, true);
/*      */           break;
/*      */         
/*      */         case "donkey":
/*  412 */           mobDisguise9 = new MobDisguise(DisguiseType.DONKEY, true);
/*      */           
/*  414 */           donkeyWatcher = (DonkeyWatcher)mobDisguise9.getWatcher();
/*      */           
/*  416 */           bool2 = mythicConfig.getBoolean("Saddled", false);
/*  417 */           bool3 = mythicConfig.getBoolean("HasChest", false);
/*      */           
/*  419 */           if (bool2 == true) {
/*  420 */             donkeyWatcher.setSaddled(true);
/*      */           }
/*  422 */           if (bool3 == true) {
/*  423 */             donkeyWatcher.setCarryingChest(true);
/*      */           }
/*      */           break;
/*      */         case "dragon_fireball":
/*      */         case "dragonfireball":
/*  428 */           mobDisguise9 = new MobDisguise(DisguiseType.DRAGON_FIREBALL, true);
/*      */           break;
/*      */         
/*      */         case "drowned":
/*  432 */           mobDisguise9 = new MobDisguise(DisguiseType.DROWNED, true); break;
/*      */         case "drowned_baby":
/*      */         case "baby_drowned":
/*      */         case "babydrowned":
/*  436 */           mobDisguise9 = new MobDisguise(DisguiseType.DROWNED, true);
/*      */           
/*  438 */           zwb = (ZombieWatcher)mobDisguise9.getWatcher();
/*  439 */           zwb.setBaby();
/*      */           break;
/*      */         
/*      */         case "egg":
/*  443 */           miscDisguise9 = new MiscDisguise(DisguiseType.EGG);
/*      */           break;
/*      */         case "enderman":
/*      */         case "endermen":
/*  447 */           mobDisguise8 = new MobDisguise(DisguiseType.ENDERMAN, true);
/*      */           
/*  449 */           if (data != null)
/*  450 */           { EndermanWatcher enw = (EndermanWatcher)mobDisguise8.getWatcher();
/*  451 */             ItemStack itemstack = new ItemStack(Material.valueOf(data.toUpperCase()));
/*      */             
/*  453 */             enw.setItemInHand(itemstack); }  break;
/*      */         case "end_crystal":
/*      */         case "endcrystal":
/*      */         case "endercrystal":
/*      */         case "ender_crystal":
/*  458 */           miscDisguise8 = new MiscDisguise(DisguiseType.ENDER_CRYSTAL); break;
/*      */         case "end_dragon": case "ender_dragon":
/*      */         case "enddragon":
/*      */         case "enderdragon":
/*  462 */           mobDisguise7 = new MobDisguise(DisguiseType.ENDER_DRAGON, true);
/*      */           break;
/*      */         
/*      */         case "endermite":
/*  466 */           mobDisguise7 = new MobDisguise(DisguiseType.ENDERMITE, true);
/*      */           break;
/*      */         
/*      */         case "elder_guardian":
/*  470 */           mobDisguise7 = new MobDisguise(DisguiseType.ELDER_GUARDIAN, true);
/*      */           break;
/*      */         
/*      */         case "evoker":
/*  474 */           mobDisguise7 = new MobDisguise(DisguiseType.EVOKER, true);
/*      */           break;
/*      */         case "evokerfangs":
/*      */         case "evoker_fangs":
/*  478 */           mobDisguise7 = new MobDisguise(DisguiseType.EVOKER_FANGS, true);
/*      */           break;
/*      */         
/*      */         case "fireball":
/*  482 */           miscDisguise7 = new MiscDisguise(DisguiseType.FIREBALL);
/*      */           break;
/*      */         
/*      */         case "fox":
/*  486 */           mobDisguise6 = new MobDisguise(DisguiseType.FOX, true);
/*      */           break;
/*      */         case "fox_baby":
/*      */         case "baby_fox":
/*  490 */           mobDisguise6 = new MobDisguise(DisguiseType.DOLPHIN, true);
/*  491 */           foxWatcher = (FoxWatcher)mobDisguise6.getWatcher();
/*  492 */           foxWatcher.setBaby();
/*      */           break;
/*      */         
/*      */         case "ghast":
/*  496 */           mobDisguise6 = new MobDisguise(DisguiseType.GHAST, true);
/*      */           break;
/*      */         
/*      */         case "giant":
/*  500 */           mobDisguise6 = new MobDisguise(DisguiseType.GIANT, true);
/*      */           break;
/*      */         
/*      */         case "guardian":
/*  504 */           mobDisguise6 = new MobDisguise(DisguiseType.GUARDIAN, true);
/*      */           break;
/*      */         
/*      */         case "horse":
/*  508 */           mobDisguise6 = new MobDisguise(DisguiseType.HORSE, true);
/*      */           
/*  510 */           horseWatcher = (HorseWatcher)mobDisguise6.getWatcher();
/*      */           
/*  512 */           horseArmor = mythicConfig.getString("Armor", null);
/*  513 */           horseColor = mythicConfig.getString("Color", null);
/*  514 */           bool5 = mythicConfig.getBoolean("Saddled", false);
/*  515 */           horseStyle = mythicConfig.getString("Style", null);
/*  516 */           bool7 = mythicConfig.getBoolean("HasChest", false);
/*      */           
/*  518 */           if (horseStyle != null) {
/*  519 */             horseWatcher.setStyle(Horse.Style.valueOf(horseStyle));
/*      */           }
/*  521 */           if (horseColor != null) {
/*  522 */             horseWatcher.setColor(Horse.Color.valueOf(horseColor));
/*      */           }
/*  524 */           if (horseArmor != null) {
/*  525 */             switch (horseArmor) {
/*      */               case "diamond":
/*  527 */                 horseWatcher.setArmor(new ItemStack[] { new ItemStack(VolatileMaterial.DIAMOND_HORSE_ARMOR, 1) });
/*      */                 break;
/*      */               case "gold":
/*      */               case "golden":
/*  531 */                 horseWatcher.setArmor(new ItemStack[] { new ItemStack(VolatileMaterial.GOLDEN_HORSE_ARMOR, 1) });
/*      */                 break;
/*      */               
/*      */               default:
/*  535 */                 horseWatcher.setArmor(new ItemStack[] { new ItemStack(VolatileMaterial.IRON_HORSE_ARMOR, 1) });
/*      */                 break;
/*      */             } 
/*      */           
/*      */           }
/*  540 */           if (bool5 == true) {
/*  541 */             horseWatcher.setSaddled(true);
/*      */           }
/*  543 */           if (bool7 == true) {
/*  544 */             horseWatcher.setCarryingChest(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case "husk":
/*  549 */           mobDisguise6 = new MobDisguise(DisguiseType.HUSK, true);
/*      */           break;
/*      */         case "husk_baby":
/*      */         case "baby_husk":
/*  553 */           mobDisguise6 = new MobDisguise(DisguiseType.HUSK, true);
/*      */           
/*  555 */           zombieWatcher1 = (ZombieWatcher)mobDisguise6.getWatcher();
/*  556 */           zombieWatcher1.setBaby();
/*      */           break;
/*      */         
/*      */         case "illusioner":
/*  560 */           mobDisguise6 = new MobDisguise(DisguiseType.ILLUSIONER, true);
/*      */           break;
/*      */         case "item":
/*      */         case "dropped_item":
/*  564 */           miscDisguise6 = new MiscDisguise(DisguiseType.DROPPED_ITEM);
/*      */           
/*  566 */           item_id = mythicConfig.getString("Item", data);
/*  567 */           item_dv = (short)mythicConfig.getInteger("ItemData", 0);
/*      */           
/*  569 */           diw = (DroppedItemWatcher)miscDisguise6.getWatcher();
/*      */           
/*  571 */           mi = MythicMobs.inst().getItemManager().getItem(item_id);
/*      */           
/*  573 */           if (mi.isPresent()) {
/*  574 */             ItemStack i = BukkitAdapter.adapt(((MythicItem)mi.get()).generateItemStack(1));
/*  575 */             diw.setItemStack(i); break;
/*      */           } 
/*  577 */           ditemstack = new ItemStack(Material.valueOf(item_id.toUpperCase()), 1, item_dv);
/*  578 */           diw.setItemStack(ditemstack);
/*      */           break;
/*      */         
/*      */         case "iron_golem":
/*      */         case "irongolem":
/*  583 */           mobDisguise5 = new MobDisguise(DisguiseType.IRON_GOLEM, true); break;
/*      */         case "itemframe":
/*      */         case "item_frame":
/*  586 */           miscDisguise5 = new MiscDisguise(DisguiseType.ITEM_FRAME);
/*      */           break;
/*      */         case "llama":
/*  589 */           mobDisguise4 = new MobDisguise(DisguiseType.LLAMA, true);
/*  590 */           llamaWatcher = (LlamaWatcher)mobDisguise4.getWatcher();
/*      */           
/*  592 */           hasSaddle = mythicConfig.getBoolean("HasSaddle", false);
/*  593 */           carryingChest = mythicConfig.getBoolean("CarryingChest", false);
/*  594 */           isGrazing = mythicConfig.getBoolean("Grazing", false);
/*      */           
/*  596 */           if (hasSaddle == true) {
/*  597 */             llamaWatcher.setSaddled(true);
/*      */           }
/*  599 */           if (carryingChest == true) {
/*  600 */             llamaWatcher.setCarryingChest(true);
/*      */           }
/*  602 */           if (isGrazing)
/*  603 */             llamaWatcher.setGrazing(true); 
/*      */           break;
/*      */         case "magma_cube":
/*      */         case "magmacube":
/*      */         case "lavaslime":
/*  608 */           mobDisguise4 = new MobDisguise(DisguiseType.MAGMA_CUBE, true);
/*      */           
/*  610 */           mcw = (SlimeWatcher)mobDisguise4.getWatcher();
/*      */           
/*  612 */           slimesize = 2;
/*  613 */           if (data != null && data.matches("[0-9]+")) {
/*  614 */             slimesize = Integer.parseInt(data);
/*      */           }
/*  616 */           slimesize = mythicConfig.getInteger("Size", slimesize);
/*  617 */           mcw.setSize(slimesize);
/*      */           break;
/*      */ 
/*      */         
/*      */         case "minecart":
/*  622 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART);
/*      */           break;
/*      */         
/*      */         case "minecart_chest":
/*  626 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART_CHEST);
/*      */           break;
/*      */         
/*      */         case "minecart_furnace":
/*  630 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART_FURNACE);
/*      */           break;
/*      */         
/*      */         case "minecart_hopper":
/*  634 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART_HOPPER);
/*      */           break;
/*      */         
/*      */         case "minecart_mob_spawner":
/*  638 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART_MOB_SPAWNER);
/*      */           break;
/*      */         
/*      */         case "minecart_tnt":
/*  642 */           miscDisguise4 = new MiscDisguise(DisguiseType.MINECART_TNT);
/*      */           break;
/*      */         case "mooshroom":
/*      */         case "mushroom_cow":
/*  646 */           mobDisguise3 = new MobDisguise(DisguiseType.MUSHROOM_COW, true);
/*      */           break;
/*      */         case "mule":
/*  649 */           mobDisguise3 = new MobDisguise(DisguiseType.MULE, true);
/*      */           
/*  651 */           muleWatcher = (MuleWatcher)mobDisguise3.getWatcher();
/*      */           
/*  653 */           bool1 = mythicConfig.getBoolean("Saddled", false);
/*  654 */           horseChest = mythicConfig.getBoolean("HasChest", false);
/*      */           
/*  656 */           if (bool1 == true) {
/*  657 */             muleWatcher.setSaddled(true);
/*      */           }
/*  659 */           if (horseChest == true) {
/*  660 */             muleWatcher.setCarryingChest(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case "ocelot":
/*  665 */           mobDisguise3 = new MobDisguise(DisguiseType.OCELOT, true);
/*      */           break;
/*      */         
/*      */         case "painting":
/*  669 */           if (data != null && data.matches("[0-9]*")) {
/*  670 */             MiscDisguise miscDisguise = new MiscDisguise(DisguiseType.PAINTING, Integer.parseInt(data));
/*      */           }
/*      */           break;
/*      */         case "panda":
/*  674 */           mobDisguise3 = new MobDisguise(DisguiseType.PANDA, true);
/*  675 */           pandaWatcher = (PandaWatcher)mobDisguise3.getWatcher();
/*      */           
/*  677 */           pandaWatcher.setMainGene(Panda.Gene.NORMAL);
/*  678 */           pandaWatcher.setHiddenGene(Panda.Gene.NORMAL);
/*      */           break;
/*      */         case "panda_baby":
/*      */         case "baby_panda":
/*  682 */           mobDisguise3 = new MobDisguise(DisguiseType.PANDA, true);
/*  683 */           pandaWatcher = (PandaWatcher)mobDisguise3.getWatcher();
/*  684 */           pandaWatcher.setBaby();
/*      */           break;
/*      */         
/*      */         case "parrot":
/*  688 */           mobDisguise3 = new MobDisguise(DisguiseType.PARROT, true);
/*      */           
/*  690 */           parrotWatcher = (ParrotWatcher)mobDisguise3.getWatcher();
/*      */           
/*  692 */           variant = mythicConfig.getString("Color", null);
/*  693 */           variant = mythicConfig.getString("Variant", variant);
/*      */           
/*  695 */           if (variant != null) {
/*  696 */             parrotWatcher.setVariant(Parrot.Variant.valueOf(variant));
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "phantom":
/*  702 */           mobDisguise3 = new MobDisguise(DisguiseType.PHANTOM, true);
/*  703 */           phantomWatcher = (PhantomWatcher)mobDisguise3.getWatcher();
/*      */           
/*  705 */           size = mythicConfig.getInteger("Size", 0);
/*      */           
/*  707 */           phantomWatcher.setSize(size);
/*      */           break;
/*      */         
/*      */         case "pig":
/*  711 */           mobDisguise3 = new MobDisguise(DisguiseType.PIG, true);
/*      */           break;
/*      */         case "pig_zombie":
/*      */         case "pigzombie":
/*  715 */           mobDisguise3 = new MobDisguise(DisguiseType.PIG_ZOMBIE, true); break;
/*      */         case "pig_zombie_baby":
/*      */         case "pigzombie_baby":
/*      */         case "babypigzombie":
/*  719 */           mobDisguise3 = new MobDisguise(DisguiseType.PIG_ZOMBIE, true);
/*      */           
/*  721 */           w = (ZombieWatcher)mobDisguise3.getWatcher();
/*  722 */           w.setBaby();
/*      */           break;
/*      */         
/*      */         case "pillager":
/*  726 */           mobDisguise3 = new MobDisguise(DisguiseType.PILLAGER, true);
/*      */           break;
/*      */         
/*      */         case "player":
/*  730 */           player = data;
/*  731 */           skin = player;
/*  732 */           if (split.length > 2) {
/*  733 */             skin = split[2];
/*      */           }
/*      */           
/*  736 */           player = mythicConfig.getString("Player", player);
/*  737 */           skin = mythicConfig.getString("Skin", skin);
/*      */           
/*  739 */           if (player != null) {
/*  740 */             player = ChatColor.translateAlternateColorCodes('&', player);
/*  741 */             playerDisguise = new PlayerDisguise(player);
/*      */             
/*  743 */             PlayerWatcher pw = (PlayerWatcher)playerDisguise.getWatcher();
/*      */             
/*  745 */             if (split.length > 2) {
/*  746 */               pw.setSkin(split[2]); break;
/*      */             } 
/*  748 */             pw.setSkin(skin);
/*      */             break;
/*      */           } 
/*  751 */           if (MythicMobs.inst().getHologramManager().isActive()) {
/*  752 */             playerDisguise = new PlayerDisguise(" ");
/*      */             
/*  754 */             PlayerWatcher pw = (PlayerWatcher)playerDisguise.getWatcher();
/*  755 */             pw.setSkin(skin);
/*      */           } 
/*      */           break;
/*      */         case "player_clone":
/*      */         case "player_doppleganger":
/*      */         case "doppleganger":
/*  761 */           player2 = data;
/*  762 */           skin2 = player2;
/*  763 */           if (split.length > 2) {
/*  764 */             skin2 = split[2];
/*      */           }
/*      */           
/*  767 */           if (player2 != null) {
/*  768 */             player2 = ChatColor.translateAlternateColorCodes('&', player2);
/*  769 */             playerDisguise = new PlayerDisguise(player2);
/*      */             
/*  771 */             PlayerWatcher pw2 = (PlayerWatcher)playerDisguise.getWatcher();
/*      */             
/*  773 */             if (split.length > 2) {
/*  774 */               pw2.setSkin(split[2]); break;
/*      */             } 
/*  776 */             pw2.setSkin(skin2);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case "polar_bear":
/*      */         case "polarbear":
/*  782 */           mobDisguise3 = new MobDisguise(DisguiseType.POLAR_BEAR, true); break;
/*      */         case "polar_bear_baby": case "polarbear_baby":
/*      */         case "babypolarbear":
/*      */         case "baby_polar_bear":
/*  786 */           mobDisguise3 = new MobDisguise(DisguiseType.POLAR_BEAR, true);
/*      */           
/*  788 */           polarBearWatcher = (PolarBearWatcher)mobDisguise3.getWatcher();
/*  789 */           polarBearWatcher.setBaby(true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case "pufferfish":
/*  794 */           mobDisguise3 = new MobDisguise(DisguiseType.PUFFERFISH, true);
/*      */           break;
/*      */         
/*      */         case "rabbit":
/*  798 */           mobDisguise3 = new MobDisguise(DisguiseType.RABBIT, true);
/*      */           break;
/*      */         
/*      */         case "ravager":
/*  802 */           mobDisguise3 = new MobDisguise(DisguiseType.RAVAGER, true);
/*      */           break;
/*      */         
/*      */         case "salmon":
/*  806 */           mobDisguise3 = new MobDisguise(DisguiseType.SALMON, true);
/*      */           break;
/*      */         
/*      */         case "sheep":
/*  810 */           mobDisguise3 = new MobDisguise(DisguiseType.SHEEP);
/*      */           
/*  812 */           shw = (SheepWatcher)mobDisguise3.getWatcher();
/*      */           
/*  814 */           color = mythicConfig.getString("Color", null);
/*  815 */           if (color != null) {
/*  816 */             shw.setColor(AnimalColor.valueOf(color));
/*      */           }
/*      */           break;
/*      */         
/*      */         case "shulker":
/*  821 */           mobDisguise3 = new MobDisguise(DisguiseType.SHULKER, true);
/*      */           break;
/*      */         case "shulker_bullet":
/*      */         case "shulkerbullet":
/*  825 */           mobDisguise3 = new MobDisguise(DisguiseType.SHULKER_BULLET, true);
/*      */           break;
/*      */         
/*      */         case "silverfish":
/*  829 */           mobDisguise3 = new MobDisguise(DisguiseType.SILVERFISH, true);
/*      */           break;
/*      */         
/*      */         case "skeleton":
/*  833 */           mobDisguise3 = new MobDisguise(DisguiseType.SKELETON);
/*      */           break;
/*      */         
/*      */         case "slime":
/*  837 */           mobDisguise3 = new MobDisguise(DisguiseType.SLIME, true);
/*      */           
/*  839 */           slw = (SlimeWatcher)mobDisguise3.getWatcher();
/*  840 */           slimessize = 2;
/*  841 */           if (data != null && data.matches("[0-9]+")) {
/*  842 */             slimessize = Integer.parseInt(data);
/*      */           }
/*  844 */           slimessize = mythicConfig.getInteger("Size", slimessize);
/*  845 */           slw.setSize(slimessize);
/*      */           break;
/*      */         
/*      */         case "snowman":
/*  849 */           mobDisguise3 = new MobDisguise(DisguiseType.SNOWMAN, true);
/*      */           break;
/*      */         
/*      */         case "spider":
/*  853 */           mobDisguise3 = new MobDisguise(DisguiseType.SPIDER, true);
/*      */           break;
/*      */         case "skeleton_horse":
/*      */         case "skeletal_horse":
/*  857 */           mobDisguise3 = new MobDisguise(DisguiseType.SKELETON_HORSE, true);
/*      */           
/*  859 */           hw = (SkeletonHorseWatcher)mobDisguise3.getWatcher();
/*      */           
/*  861 */           horseSaddled = mythicConfig.getBoolean("Saddled", false);
/*  862 */           horseChest = mythicConfig.getBoolean("HasChest", false);
/*      */           
/*  864 */           if (horseSaddled == true) {
/*  865 */             hw.setSaddled(true);
/*      */           }
/*  867 */           if (horseChest == true) {
/*  868 */             hw.setCarryingChest(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case "squid":
/*  873 */           mobDisguise3 = new MobDisguise(DisguiseType.SQUID, true);
/*      */           break;
/*      */         
/*      */         case "stray":
/*  877 */           mobDisguise3 = new MobDisguise(DisguiseType.STRAY, true);
/*      */           break;
/*      */         
/*      */         case "turtle":
/*  881 */           mobDisguise3 = new MobDisguise(DisguiseType.TURTLE, true);
/*      */           break;
/*      */         case "turtle_baby":
/*      */         case "baby_turtle":
/*  885 */           mobDisguise3 = new MobDisguise(DisguiseType.TURTLE, true);
/*  886 */           watcher = (TurtleWatcher)mobDisguise3.getWatcher();
/*  887 */           watcher.setBaby();
/*      */           break;
/*      */         case "thrown_xp_bottle":
/*      */         case "xp_bottle":
/*  891 */           miscDisguise3 = new MiscDisguise(DisguiseType.THROWN_EXP_BOTTLE); break;
/*      */         case "experience_orb": case "exp":
/*      */         case "xp":
/*      */         case "xporb":
/*  895 */           miscDisguise3 = new MiscDisguise(DisguiseType.EXPERIENCE_ORB);
/*      */           break;
/*      */         
/*      */         case "villager":
/*  899 */           mobDisguise2 = new MobDisguise(DisguiseType.VILLAGER, true);
/*      */           
/*  901 */           vw = (VillagerWatcher)mobDisguise2.getWatcher();
/*      */           
/*  903 */           villagerType = mythicConfig.getString("Profession", null);
/*      */           
/*  905 */           if (villagerType != null) {
/*  906 */             vw.setProfession(Villager.Profession.valueOf(villagerType));
/*      */           }
/*      */           break;
/*      */         case "villlager_baby":
/*      */         case "baby_villager":
/*      */         case "babyvillager":
/*  912 */           mobDisguise2 = new MobDisguise(DisguiseType.VILLAGER, true);
/*      */           
/*  914 */           vw = (VillagerWatcher)mobDisguise2.getWatcher();
/*      */           
/*  916 */           vw.setBaby();
/*      */           
/*  918 */           villagerType = mythicConfig.getString("Profession", null);
/*      */           
/*  920 */           if (villagerType != null) {
/*  921 */             vw.setProfession(Villager.Profession.valueOf(villagerType));
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "vindicator":
/*  927 */           mobDisguise2 = new MobDisguise(DisguiseType.VINDICATOR, true);
/*      */           break;
/*      */         
/*      */         case "vex":
/*  931 */           mobDisguise2 = new MobDisguise(DisguiseType.VEX, true);
/*      */           break;
/*      */         
/*      */         case "witch":
/*  935 */           mobDisguise2 = new MobDisguise(DisguiseType.WITCH, true);
/*      */           break;
/*      */         
/*      */         case "wither":
/*  939 */           mobDisguise2 = new MobDisguise(DisguiseType.WITHER, true); break;
/*      */         case "witherskeleton":
/*      */         case "wither_skeleton":
/*  942 */           mobDisguise2 = new MobDisguise(DisguiseType.WITHER_SKELETON); break;
/*      */         case "witherskull":
/*      */         case "wither_skull":
/*  945 */           miscDisguise2 = new MiscDisguise(DisguiseType.WITHER_SKULL);
/*      */           break;
/*      */         case "wolf":
/*  948 */           mobDisguise1 = new MobDisguise(DisguiseType.WOLF, true);
/*  949 */           ww = (WolfWatcher)mobDisguise1.getWatcher();
/*      */           
/*  951 */           angry = mythicConfig.getBoolean("Angry", false);
/*  952 */           ww.setAngry(angry); break;
/*      */         case "wolf_baby":
/*      */         case "baby_wolf":
/*      */         case "babywolf":
/*  956 */           mobDisguise1 = new MobDisguise(DisguiseType.WOLF, true);
/*      */           
/*  958 */           bww = (WolfWatcher)mobDisguise1.getWatcher();
/*  959 */           angry2 = mythicConfig.getBoolean("Angry", false);
/*  960 */           bww.setBaby();
/*  961 */           bww.setAngry(angry2);
/*      */           break;
/*      */         
/*      */         case "zombie":
/*  965 */           mobDisguise1 = new MobDisguise(DisguiseType.ZOMBIE, true); break;
/*      */         case "zombie_baby":
/*      */         case "baby_zombie":
/*      */         case "babyzombie":
/*  969 */           mobDisguise1 = new MobDisguise(DisguiseType.ZOMBIE, true);
/*      */           
/*  971 */           zombieWatcher2 = (ZombieWatcher)mobDisguise1.getWatcher();
/*  972 */           zombieWatcher2.setBaby();
/*      */           break;
/*      */         case "zombie_horse":
/*      */         case "undead_horse":
/*  976 */           mobDisguise1 = new MobDisguise(DisguiseType.ZOMBIE_HORSE, true);
/*      */           
/*  978 */           zombieHorseWatcher = (ZombieHorseWatcher)mobDisguise1.getWatcher();
/*      */           
/*  980 */           bool4 = mythicConfig.getBoolean("Saddled", false);
/*  981 */           bool6 = mythicConfig.getBoolean("HasChest", false);
/*      */           
/*  983 */           if (bool4 == true) {
/*  984 */             zombieHorseWatcher.setSaddled(true);
/*      */           }
/*  986 */           if (bool6 == true)
/*  987 */             zombieHorseWatcher.setCarryingChest(true); 
/*      */           break;
/*      */         case "zombie_villager":
/*      */         case "zombievillager":
/*      */         case "villagezombie":
/*  992 */           mobDisguise1 = new MobDisguise(DisguiseType.ZOMBIE_VILLAGER, true);
/*  993 */           zw = (ZombieVillagerWatcher)mobDisguise1.getWatcher();
/*  994 */           if (MythicMobs.inst().getMinecraftVersion() >= 9)
/*  995 */             zw.setProfession(Villager.Profession.FARMER); 
/*      */           break;
/*      */         case "zombie_villager_baby":
/*      */         case "babyzombievillager":
/*      */         case "babyvillagezombie":
/* 1000 */           mobDisguise1 = new MobDisguise(DisguiseType.ZOMBIE_VILLAGER, true);
/*      */           
/* 1002 */           zwbv = (ZombieVillagerWatcher)mobDisguise1.getWatcher();
/* 1003 */           if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 1004 */             zwbv.setProfession(Villager.Profession.FARMER);
/*      */           }
/* 1006 */           zwbv.setBaby();
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "cloud":
/*      */         case "areaeffectcloud":
/* 1014 */           miscDisguise1 = new MiscDisguise(DisguiseType.AREA_EFFECT_CLOUD);
/* 1015 */           areaEffectCloudWatcher = (AreaEffectCloudWatcher)miscDisguise1.getWatcher();
/*      */           
/*      */           try {
/* 1018 */             String strColor = mythicConfig.getString("Color", "255,0,0");
/*      */             
/* 1020 */             String[] spl = strColor.split(",");
/* 1021 */             int red = Integer.valueOf(spl[0]).intValue();
/* 1022 */             int green = Integer.valueOf(spl[1]).intValue();
/* 1023 */             int blue = Integer.valueOf(spl[2]).intValue();
/*      */             
/* 1025 */             Color colorID = Color.fromRGB(red, green, blue);
/* 1026 */             areaEffectCloudWatcher.setColor(colorID);
/* 1027 */           } catch (Exception exception) {}
/*      */           
/*      */           try {
/* 1030 */             Particle particle = Particle.valueOf(mythicConfig.getString("Particle", "CLOUD"));
/* 1031 */             areaEffectCloudWatcher.setParticle(particle);
/* 1032 */           } catch (Exception exception) {}
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*      */           try {
/* 1041 */             Disguise disguise1 = DisguiseAPI.getCustomDisguise(dn);
/* 1042 */           } catch (Exception ex) {
/* 1043 */             MythicLogger.errorCompatibility("LibsDisguises", "Disguise '" + dn + "' not found");
/* 1044 */             ex.printStackTrace();
/* 1045 */             playerDisguise = new PlayerDisguise("Error");
/*      */           } finally {
/* 1047 */             if (playerDisguise == null) {
/* 1048 */               MythicLogger.errorCompatibility("LibsDisguises", "Disguise '" + dn + "' not found");
/* 1049 */               playerDisguise = new PlayerDisguise("Ashijin");
/*      */             } 
/*      */           } 
/*      */           break; }
/*      */ 
/*      */ 
/*      */       
/* 1056 */       if (playerDisguise instanceof PlayerDisguise) {
/* 1057 */         PlayerWatcher pw = (PlayerWatcher)playerDisguise.getWatcher();
/* 1058 */         pw.setCustomName(data);
/*      */       } 
/* 1060 */       if (playerDisguise == null) {
/* 1061 */         return null;
/*      */       }
/* 1063 */       FlagWatcher fw = playerDisguise.getWatcher();
/*      */       
/* 1065 */       if (mythicConfig.getBoolean("Burning", false) == true) {
/* 1066 */         fw.setBurning(true);
/*      */       }
/* 1068 */       if (mythicConfig.getBoolean("Invisible", false) == true) {
/* 1069 */         fw.setInvisible(true);
/*      */       }
/* 1071 */       if (mythicConfig.getBoolean("Glowing", false) == true) {
/* 1072 */         fw.setGlowing(true);
/*      */       }
/* 1074 */       if (mythicConfig.getBoolean("Gliding", false) == true) {
/* 1075 */         fw.setFlyingWithElytra(true);
/*      */       }
/* 1077 */       if (mythicConfig.getBoolean("Blocking", false) == true) {
/* 1078 */         fw.setRightClicking(true);
/*      */       }
/* 1080 */       if (mythicConfig.getBoolean("Sneaking", false) == true) {
/* 1081 */         fw.setSneaking(true);
/*      */       }
/* 1083 */       if (mythicConfig.getBoolean("Sprinting", false) == true) {
/* 1084 */         fw.setSprinting(true);
/*      */       }
/* 1086 */       if (!mythicConfig.getBoolean("ModifyBoundingBox", true)) {
/* 1087 */         playerDisguise.setModifyBoundingBox(false);
/*      */       }
/*      */       
/* 1090 */       String handMain = mythicConfig.getString("Equipment.MainHand", null);
/* 1091 */       if (handMain != null) {
/* 1092 */         fw.setItemInMainHand(MythicMobs.inst().getItemManager().getItemStack(handMain));
/*      */       }
/*      */       
/* 1095 */       String handOff = mythicConfig.getString("Equipment.OffHand", null);
/* 1096 */       if (handOff != null) {
/* 1097 */         fw.setItemInMainHand(MythicMobs.inst().getItemManager().getItemStack(handOff));
/*      */       }
/*      */       
/* 1100 */       String armorFeet = mythicConfig.getString("Equipment.Feet", null);
/* 1101 */       String armorLegs = mythicConfig.getString("Equipment.Legs", null);
/* 1102 */       String armorChest = mythicConfig.getString("Equipment.Chest", null);
/* 1103 */       String armorHead = mythicConfig.getString("Equipment.Head", null);
/*      */       
/* 1105 */       if (!Stream.<String>of(new String[] { armorFeet, armorLegs, armorChest, armorHead }).allMatch(v -> (v == null)))
/*      */       {
/*      */         
/* 1108 */         ItemStack[] armorItems = new ItemStack[4];
/*      */         
/* 1110 */         armorItems[0] = (armorFeet == null) ? null : MythicMobs.inst().getItemManager().getItemStack(armorFeet);
/* 1111 */         armorItems[1] = (armorLegs == null) ? null : MythicMobs.inst().getItemManager().getItemStack(armorLegs);
/* 1112 */         armorItems[2] = (armorChest == null) ? null : MythicMobs.inst().getItemManager().getItemStack(armorChest);
/* 1113 */         armorItems[3] = (armorHead == null) ? null : MythicMobs.inst().getItemManager().getItemStack(armorHead);
/*      */         
/* 1115 */         fw.setArmor(armorItems);
/*      */       }
/*      */     
/* 1118 */     } catch (Exception ex) {
/* 1119 */       MythicLogger.errorCompatibility("LibsDisguises", "Disguise type '" + disguise + "' not found");
/* 1120 */       if (ConfigManager.debugLevel > 0) {
/* 1121 */         ex.printStackTrace();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1126 */     return (Disguise)playerDisguise;
/*      */   }
/*      */   
/*      */   public void modifyDisguise(AbstractEntity target, GenericConfig mc) {
/* 1130 */     Entity entity = BukkitAdapter.adapt(target);
/*      */     
/* 1132 */     Disguise d = DisguiseAPI.getDisguise(entity);
/*      */     
/* 1134 */     if (d == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1138 */     FlagWatcher fw = d.getWatcher();
/*      */     
/* 1140 */     if (mc.getBoolean("Burning", false) == true) {
/* 1141 */       fw.setBurning(true);
/*      */     } else {
/* 1143 */       fw.setBurning(false);
/*      */     } 
/* 1145 */     if (mc.getBoolean("Invisible", false) == true) {
/* 1146 */       fw.setInvisible(true);
/*      */     } else {
/* 1148 */       fw.setInvisible(false);
/*      */     } 
/* 1150 */     if (mc.getBoolean("Glowing", false) == true) {
/* 1151 */       fw.setGlowing(true);
/*      */     } else {
/* 1153 */       fw.setGlowing(false);
/*      */     } 
/* 1155 */     if (mc.getBoolean("Gliding", false) == true) {
/* 1156 */       fw.setFlyingWithElytra(true);
/*      */     } else {
/* 1158 */       fw.setFlyingWithElytra(false);
/*      */     } 
/* 1160 */     if (mc.getBoolean("Blocking", false) == true) {
/* 1161 */       fw.setRightClicking(true);
/*      */     } else {
/* 1163 */       fw.setRightClicking(false);
/*      */     } 
/* 1165 */     if (mc.getBoolean("Sneaking", false) == true) {
/* 1166 */       fw.setSneaking(true);
/*      */     } else {
/* 1168 */       fw.setSneaking(false);
/*      */     } 
/* 1170 */     if (mc.getBoolean("Sprinting", false) == true) {
/* 1171 */       fw.setSprinting(true);
/*      */     } else {
/* 1173 */       fw.setSprinting(false);
/*      */     } 
/* 1175 */     if (!mc.getBoolean("ModifyBoundingBox", true)) {
/* 1176 */       d.setModifyBoundingBox(false);
/*      */     } else {
/* 1178 */       d.setModifyBoundingBox(true);
/*      */     } 
/*      */     
/* 1181 */     DisguiseAPI.disguiseEntity(entity, d);
/*      */   }
/*      */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\LibsDisguisesSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */