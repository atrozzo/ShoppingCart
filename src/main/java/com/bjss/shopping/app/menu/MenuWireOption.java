package com.bjss.shopping.app.menu;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to wire the products with tha avilable command line arguments. This
 *
 *
 * @author Angelo Trozzo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuWireOption {
	/** menu option settings */

    /**
     * @return the option  value that represent the command.
     */
	String value();

    /**
     * @return the short represnetation of the command, eg: if value = "Help", short would be "h"
     */
	String shortValue();

    /**
     * @return the decription of the command/option
     */
	String description();

    /**
     * @return a boolean value that represent if the command/option is expcted when the command is executed.
     */
	boolean isMandatory() default false;

    /**
     *
     * @return a boolean value that represent the availability of the command. The coommand can be defined but excluded
     * in this way from the available commands to execute.
     */
	boolean isAvailable() default true;
}