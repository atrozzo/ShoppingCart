package com.bjss.shopping.cmd;

import com.bjss.shopping.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class execute the commands
 * 
 * 
 * @author angelo.trozzo
 *
 */

@Service
public class CmdExecutor {

    @Autowired
    private ApplicationContext context;

    private Map<Cmd<Product>, List<Product>> executbaleCommand;

    /**
     * This method transforms the command line arguments in their related bean and associate them 
     * with the command bean that need to use it. 
     * 
     * @param cmds it s a Map<String, List<String>> where K is the Command and V is the List of arguments 
     * @return an instance of It self 
     */
    @SuppressWarnings("unchecked")
    public CmdExecutor addExecutableCommands(final Map<String, List<String>> cmds) {
        Assert.notNull(cmds, "Commands list is null");
        executbaleCommand = new HashMap<>();
        // Start to transform the Spring 
        for (Entry<String, List<String>> entry : cmds.entrySet()) {
            List<Product> products = (List<Product>) convertList(entry.getValue(), x -> (Product) context.getBean(x));
            executbaleCommand.put(((Cmd<Product>) context.getBean(entry.getKey())), products);
        }
        return this;
    }
    
    
    public Map<Cmd<Product>, List<Product>> getExecutableCommands(){
    	return executbaleCommand;
    }
        
    /**
     * The method execute all commands in the list and pass the arguments values as arguments. 
     */
    public void executeCommands(){
        this.getExecutableCommands().forEach((k,v)->{ 
        	k.execute(v);
        });
    }
    
    
    
    /**
     * This is an utility conversion method, useful to transform the content of a list from a type to another one. 
     * 
     * @param from is the type from what to convert to 
     * @param func is the {@link Function} to apply for the conversion
     * 
     * @return  a converted {@link List}
     */
    private  <T, U> List<U> convertList(final List<T> from,final Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

}
