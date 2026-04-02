/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "blockMask", aliases = {"effect:blockMask", "e:blockMask"}, description = "Temporarily masks a block as a different block")
/*     */ public class BlockMaskEffect
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*  30 */   private Material mat = Material.GRAVEL;
/*     */   
/*     */   private byte data;
/*     */   
/*     */   private int radius;
/*     */   
/*     */   private int duration;
/*     */   
/*     */   private double randomness;
/*     */   
/*     */   private boolean sphere;
/*     */   
/*     */   private MaskType maskType;
/*     */   
/*     */   private int radiusSq;
/*     */   
/*     */   public BlockMaskEffect(String skill, MythicLineConfig mlc) {
/*  47 */     super(skill, mlc);
/*  48 */     this.ASYNC_SAFE = false;
/*     */     
/*  50 */     String strMaterial = mlc.getString(new String[] { "material", "mat", "m" }, "GRAVEL", new String[0]).toUpperCase();
/*     */     
/*     */     try {
/*  53 */       this.mat = (this.data == 0) ? Material.valueOf(strMaterial) : LegacyItemConverter.getMaterial(strMaterial, this.data);
/*  54 */     } catch (Exception ex) {
/*  55 */       this.mat = LegacyItemConverter.getMaterial(strMaterial, this.data);
/*     */     } finally {
/*  57 */       if (this.mat == null) {
/*  58 */         MythicLogger.errorMechanicConfig(this, mlc, "Material '" + strMaterial + "' not found or invalid.");
/*     */       }
/*     */     } 
/*     */     
/*  62 */     this.data = (byte)mlc.getInteger(new String[] { "materialdata", "md", "data", "dv" }, 0);
/*  63 */     this.radius = mlc.getInteger(new String[] { "radius", "r" }, 0);
/*  64 */     this.randomness = mlc.getDouble(new String[] { "noise", "n" }, 0.0D);
/*  65 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 0);
/*     */     
/*  67 */     String shape = mlc.getString(new String[] { "shape", "s" }, "SPHERE", new String[0]).toUpperCase();
/*     */     
/*  69 */     if (shape.equals("SPHERE")) {
/*  70 */       this.sphere = true;
/*     */     } else {
/*  72 */       this.sphere = false;
/*     */     } 
/*     */     
/*  75 */     boolean noAir = mlc.getBoolean(new String[] { "noair", "na" }, true);
/*  76 */     boolean onlyAir = mlc.getBoolean(new String[] { "onlyair", "oa" }, false);
/*     */     
/*  78 */     if (noAir == true && !onlyAir) {
/*  79 */       this.maskType = MaskType.IGNORE_AIR;
/*  80 */     } else if (onlyAir == true) {
/*  81 */       this.maskType = MaskType.ONLY_AIR;
/*     */     } else {
/*  83 */       this.maskType = MaskType.ALL;
/*     */     } 
/*     */     
/*  86 */     if (this.radius < 0) this.radius = 0; 
/*  87 */     this.radiusSq = this.radius * this.radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  92 */     playEffect(target.getLocation());
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  98 */     playEffect(target);
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playEffect(AbstractLocation location) {
/* 104 */     Location l = BukkitAdapter.adapt(location);
/*     */     
/* 106 */     if (this.radius == 0) {
/* 107 */       for (Player p : l.getWorld().getPlayers()) {
/* 108 */         if (p.getLocation().distanceSquared(l) < 65536.0D) {
/* 109 */           p.sendBlockChange(l, this.mat, this.data);
/*     */         }
/*     */       } 
/*     */     } else {
/* 113 */       for (Location ll : getBlocksInRadius(l, true)) {
/* 114 */         for (Player p : l.getWorld().getPlayers()) {
/* 115 */           if (p.getLocation().distanceSquared(l) < 65536.0D) {
/* 116 */             p.sendBlockChange(ll, this.mat, this.data);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     if (this.duration > 0) {
/* 123 */       Schedulers.sync().runLater(() -> { Location lll = BukkitAdapter.adapt(paramAbstractLocation); if (this.radius == 0) { for (Player p : lll.getWorld().getPlayers()) { if (p.getLocation().distanceSquared(lll) < 65536.0D) p.sendBlockChange(lll, lll.getBlock().getType(), lll.getBlock().getData());  }  } else { for (Location ll : getBlocksInRadius(lll, false)) { for (Player p : lll.getWorld().getPlayers()) { if (p.getLocation().distanceSquared(paramLocation) < 65536.0D) p.sendBlockChange(ll, ll.getBlock().getType(), ll.getBlock().getData());  }  }  }  }this.duration);
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
/*     */   private List<Location> getBlocksInRadius(Location l, boolean randomize) {
/* 147 */     List<Location> blocks = new ArrayList<>();
/* 148 */     for (int x = -this.radius; x <= this.radius; x++) {
/* 149 */       for (int y = -this.radius; y <= this.radius; y++) {
/* 150 */         for (int z = -this.radius; z <= this.radius; z++) {
/* 151 */           Location newloc = new Location(l.getWorld(), l.getX() + x, l.getY() + y, l.getZ() + z);
/*     */           
/* 153 */           if (this.sphere != true || 
/* 154 */             l.distanceSquared(newloc) <= this.radiusSq)
/*     */           {
/* 156 */             if (!randomize || this.randomness <= 0.0D || 
/* 157 */               this.randomness >= MythicMobs.r.nextDouble())
/*     */             {
/*     */               
/* 160 */               if (this.maskType == MaskType.ALL) {
/* 161 */                 blocks.add(newloc);
/* 162 */               } else if (this.maskType == MaskType.IGNORE_AIR && newloc.getBlock().getType().isOccluding()) {
/* 163 */                 blocks.add(newloc);
/* 164 */               } else if (this.maskType == MaskType.ONLY_AIR && !newloc.getBlock().getType().isOccluding()) {
/* 165 */                 blocks.add(newloc);
/*     */               }  }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 170 */     return blocks;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BlockMaskEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */