/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.MissileMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ProjectileMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
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
/*     */ @MythicMechanic(author = "Ashijin", name = "modifyprojectile", description = "Modifies an attribute of the projectile that executed the mechanic")
/*     */ public class ModifyProjectileMechanic
/*     */   extends SkillMechanic
/*     */   implements INoTargetSkill
/*     */ {
/*     */   private ProjectileTrait trait;
/*     */   private ModifyAction action;
/*     */   private double value;
/*     */   
/*     */   public ModifyProjectileMechanic(String skill, MythicLineConfig mlc) {
/*  31 */     super(skill, mlc);
/*     */     
/*  33 */     String t = mlc.getString(new String[] { "trait", "t" }, "VELOCITY", new String[0]);
/*     */     try {
/*  35 */       this.trait = ProjectileTrait.valueOf(t.toUpperCase());
/*  36 */     } catch (Exception ex) {
/*  37 */       ex.printStackTrace();
/*     */     } 
/*  39 */     String a = mlc.getString(new String[] { "action", "a" }, "MULTIPLY", new String[0]);
/*     */     try {
/*  41 */       this.action = ModifyAction.valueOf(a.toUpperCase());
/*  42 */     } catch (Exception ex) {
/*  43 */       ex.printStackTrace();
/*     */     } 
/*  45 */     this.value = mlc.getDouble(new String[] { "value", "v" }, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cast(SkillMetadata data) {
/*  51 */     if (data.getCallingEvent() instanceof ProjectileMechanic.ProjectileMechanicTracker) {
/*  52 */       ProjectileMechanic.ProjectileMechanicTracker projectile = (ProjectileMechanic.ProjectileMechanicTracker)data.getCallingEvent();
/*     */       
/*  54 */       if (this.action == ModifyAction.SET) {
/*  55 */         switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$mechanics$ModifyProjectileMechanic$ProjectileTrait[this.trait.ordinal()]) {
/*     */           case 1:
/*  57 */             projectile.setVelocity(this.value);
/*     */             break;
/*     */           case 2:
/*  60 */             projectile.setPower((float)this.value);
/*     */             break;
/*     */           case 3:
/*  63 */             projectile.setGravity((float)this.value);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } else {
/*  69 */         switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$mechanics$ModifyProjectileMechanic$ProjectileTrait[this.trait.ordinal()]) {
/*     */           case 1:
/*  71 */             projectile.modifyVelocity(this.value);
/*     */             break;
/*     */           case 2:
/*  74 */             projectile.modifyPower((float)this.value);
/*     */             break;
/*     */           case 3:
/*  77 */             projectile.modifyGravity((float)this.value);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*  83 */     } else if (data.getCallingEvent() instanceof MissileMechanic.MissileTracker) {
/*  84 */       MissileMechanic.MissileTracker projectile = (MissileMechanic.MissileTracker)data.getCallingEvent();
/*     */       
/*  86 */       if (this.action == ModifyAction.SET) {
/*  87 */         switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$mechanics$ModifyProjectileMechanic$ProjectileTrait[this.trait.ordinal()]) {
/*     */           case 1:
/*  89 */             projectile.setVelocity(this.value);
/*     */             break;
/*     */           case 2:
/*  92 */             projectile.setPower((float)this.value);
/*     */             break;
/*     */           case 4:
/*  95 */             projectile.setInertia((float)this.value);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } else {
/* 101 */         switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$mechanics$ModifyProjectileMechanic$ProjectileTrait[this.trait.ordinal()]) {
/*     */           case 1:
/* 103 */             projectile.modifyVelocity(this.value);
/*     */             break;
/*     */           case 2:
/* 106 */             projectile.modifyPower((float)this.value);
/*     */             break;
/*     */           case 4:
/* 109 */             projectile.modifyInertia((float)this.value);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ModifyProjectileMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */