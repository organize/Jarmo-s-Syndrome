package syndrome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import syndrome.entity.objective.Objective;

/**
 * An annotation to implicate the objectives assigned
 * to a NPC
 * 
 * All NPC implementations must be annotated.
 * 
 * @author Axel Wallin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ObjectiveInfo {

    /**
     * The objectives allocated for a NPC.
     */
    Objective[] objectives() default {Objective.NONE};
}
