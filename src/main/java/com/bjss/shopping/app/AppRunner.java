package com.bjss.shopping.app;

import com.bjss.shopping.cmd.CmdExecutor;
import com.bjss.shopping.exception.ExitException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AppRunner implements ApplicationRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppCmdParser.class);

    @Resource
    private AppCmdParser cmdParser;

    @Resource
    private CmdExecutor cmdExecutor;

    @Override
    public void run(final ApplicationArguments applicationArguments) throws Exception {

        validateApplicationArguments(applicationArguments);
        // Commands get retrieved , parsed and executed. 
        cmdExecutor.addExecutableCommands(cmdParser.parseCmdLine(applicationArguments.getSourceArgs())
                .getCommandList()).executeCommands();
    }

    /**
     * Validate teh arguments passed trough
     *
     * @param applicationArguments
     * @throws ExitException
     */
    private void validateApplicationArguments(final ApplicationArguments applicationArguments) throws ExitException{
        if (CollectionUtils.isEmpty(applicationArguments.getOptionNames())) {
            LOGGER.warn("No commands to execute, the progrma will shut down.");
            throw new ExitException("No commands to execute");
        }else if (CollectionUtils.isEmpty(applicationArguments.getNonOptionArgs()))
        {
            LOGGER.warn("No products specified, the progrma will shut down.");
            throw new ExitException("No products specified");
        }
    }

}