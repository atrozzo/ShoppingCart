package com.bjss.shopping.cmd;

import java.util.List;

/**
 * 
 * 
 * @author angelo.trozzo
 *
 * @param <T>
 *            represent the kind of arguments the command can use in his execution.
 */
public interface Cmd<T> {

    /**
     * Each command will execute this common mehtod.
     * 
     * @param args
     *            command argumenents.
     */
    void execute(final List<T> args);

    /**
     * retrieve the command name.
     * 
     * @return
     */
    String getName();

}
