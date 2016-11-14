package com.bjss.shopping.app;

import com.bjss.shopping.app.menu.MenuOptions;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class has to pars the command line arguments. Verify that the mandatory commands are
 * available.
 */

@Component
public class AppCmdParser extends DefaultParser {

    @Resource
    private MenuOptions options;

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOptions.class);

    private CommandLine commandLine;

    public AppCmdParser parseCmdLine(final String[] arguments) throws Exception {
        Assert.notNull(arguments, "arguments cannot be null");
        try {

            for (String current : arguments){

                if (options.getOptions().stream().filter(s->s.getLongOpt().equals(current)).findFirst() == null
                        && current.startsWith("--") == false){
                    throw new UnrecognizedOptionException("Option not recognised");
                }
            }
           // options.getOptions().stream().filter(s->s.getLongOpt().equals(arguments));


            if (options.getRequiredOptions().isEmpty()) {
                throw new ParseException("No pre configured command to execute.");
            }

            commandLine = super.parse(this.options, arguments, false);
            if (!isCommandLineValid(commandLine)) {
                throw new UnrecognizedOptionException("Not a valid command");
            }

        } catch (UnrecognizedOptionException e) {
            LOGGER.error("This command is not available in the program. "
                    + "Check the command is defined in the porgram , {}", e);
            throw new UnrecognizedOptionException(e.getMessage());
        } catch (ParseException e) {
            LOGGER.error("Failed to parse the command line {} ", e.toString());
            throw  new ParseException("Failed to parse the command line");
        }

        return this;
    }

    /**
     * The method validate the follow condition : 1. The command line needs to specify at least one
     * command to execute. 2. The command line cannot have more the command at the time specified.
     * 
     * @param cmdLine
     *            it s an already parsed {@link CommandLine}
     * @return true if the already parsed {@link CommandLine} respect the above rules.
     */
    private boolean isCommandLineValid(CommandLine cmdLine) {
        if (ArrayUtils.isEmpty(cmdLine.getOptions())) {
            LOGGER.error("You did not specify any command to execute");
            return false;
        }

        if (cmdLine.getOptions().length > 1) {
            LOGGER.error("You can execute just one command at the time");
            return false;
        }

        return true;

    }

    /**
     * The method give access ot teh command and his arguments.
     * 
     * @return
     */
    public Map<String, List<String>> getCommandList() {
        Map<String, List<String>> result = new HashMap<>();

        for (Option opt : commandLine.getOptions()) {
            result.put(opt.getLongOpt(), commandLine.getArgList());
        }

        return result;
    }

}
