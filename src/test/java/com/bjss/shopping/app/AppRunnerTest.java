package com.bjss.shopping.app;

import com.bjss.shopping.exception.ExitException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by angelo on 22/08/2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
public class AppRunnerTest {


    private ApplicationRunner runner;

    @Before
    public void setUp() {
        initMocks(this);
        runner = new AppRunner();
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void sourcesMustNotBeNull() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Sources must not be empty");
        new SpringApplication().run();
    }


    /**
     * Test an empty executions , no  commands.
     *
     * @throws Exception
     */
    @Test
    public void runWithNoCommands() throws Exception {
        ApplicationArguments arguments = mock(ApplicationArguments.class);
        this.thrown.expect(ExitException.class);
        runner.run(arguments);
    }

    /**
     * Run a test passing a non existing command available in the system
     * @throws Exception
     */
    @Test
    public void runWithWrongCommand() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectCause(new CauseMatcher(UnrecognizedOptionException.class, "Unrecognized option: --ExecuteMaxValueCalc"));
        String[]  wrongCmd = {"--ExecuteMaxValueCalc", "Apple"};
        SpringApplication app = new SpringApplication(App.class);
        app.run(wrongCmd);
    }


    /**
     * Test a correct command but no arguments ( porducts )
     *
     * @throws Exception
     */
    @Test
    public void runWithCommandAndNoArgs() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectCause(new CauseMatcher(ExitException.class, "No products specified"));
        String[]  commands = {"--PriceBasket"};
        SpringApplication app = new SpringApplication(App.class);
        app.run(commands);
    }

    /**
     * The test check that two command at the time are not accepted.
     *
     * @throws Exception
     */
    @Test
    public void runWithTwoCommands() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectCause(new CauseMatcher(UnrecognizedOptionException.class, "Not a valid command"));
        String[]  commands = {"--PriceBasket","--Help" ,"Apple"};
        SpringApplication app = new SpringApplication(App.class);
        app.run(commands);
    }


    @Test
    public void runWithWrongProduct() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectCause(new CauseMatcher(UnrecognizedOptionException.class, "Option not recognised"));
        String[]  commands = {"--PriceBasket","RedApple"};
        SpringApplication app = new SpringApplication(App.class);
        app.run(commands);
    }


    @Test
    public void parseCmdLineWithNoArguments() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Args must not be null");
        SpringApplication app = new SpringApplication(App.class);
        app.run(null);
    }



    @Test
    public void runApplePrice() throws Exception {
        String[]  commands = {"-PriceBasket","Apple"};
        SpringApplication app = new SpringApplication(App.class);
        app.run(commands);

    }



    @Test
    public void runWithNoCommands1() throws Exception {
        ApplicationArguments arguments = mock(ApplicationArguments.class);
        doReturn(Arrays.asList("Apple", "Apple")).when(arguments).getNonOptionArgs();

        runner.run(arguments);

    }



    /**
     * Confortable utility to reach the Caused exception that get handled and wrapped in {@link SpringApplication}
     *
     */
    private static class CauseMatcher extends TypeSafeMatcher<Throwable> {

        private final Class<? extends Throwable> type;
        private final String expectedMessage;

        public CauseMatcher(Class<? extends Throwable> type, String expectedMessage) {
            this.type = type;
            this.expectedMessage = expectedMessage;
        }

        @Override
        protected boolean matchesSafely(Throwable item) {
            return item.getClass().isAssignableFrom(type)
                    && item.getMessage().contains(expectedMessage);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("expects type ")
                    .appendValue(type)
                    .appendText(" and a message ")
                    .appendValue(expectedMessage);
        }
    }


}