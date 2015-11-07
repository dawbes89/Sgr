package sgr.commons.frontend;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for injecting beans from context to class fields.
 *
 * @author leonzio
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean
{

   /**
    * Form name from which will be get component.
    *
    * @return form name
    */
   public String form() default "";

   /**
    * Name of component to get.
    *
    * @return component name
    */
   public String name() default "";

}
