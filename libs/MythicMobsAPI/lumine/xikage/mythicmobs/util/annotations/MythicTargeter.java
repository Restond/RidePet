package lumine.xikage.mythicmobs.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MythicTargeter {
  String name() default "";
  
  String[] aliases() default {};
  
  String author() default "";
  
  String description() default "";
  
  String version() default "4.0";
  
  boolean premium() default false;
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\annotations\MythicTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */