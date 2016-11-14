package com.bjss.shopping.cmd;

import com.bjss.shopping.app.menu.MenuOptions;
import com.bjss.shopping.app.menu.MenuWireOption;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@MenuWireOption(value = "Help", description = ("print the command help"), shortValue = ("pb"), isMandatory = true)
@Component("help")
public class CmdHelp implements Cmd<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CmdHelp.class);

    @Resource
    MenuOptions options;

    @Override
    public void execute(List<String> availableCommands) {

        LOGGER.info("List of all available commands : ");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Help";
    }

}
