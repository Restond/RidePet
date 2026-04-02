package lumine.xikage.mythicmobs.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MythicField {
  String name() default "";
  
  String[] aliases() default {};
  
  String description() default "";
  
  String version() default "";
  
  String defValue() default "";
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\annotations\MythicField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */