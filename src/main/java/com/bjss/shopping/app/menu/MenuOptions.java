package com.bjss.shopping.app.menu;

import java.util.List;
import java.util.Map;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bjss.shopping.cmd.Cmd;
import com.bjss.shopping.products.Product;

/**
 * This class build whne intisialised a custom {@link Options} component.
 * 
 * @author angelo.trozzo
 *
 */
@Component
public class MenuOptions extends Options implements InitializingBean {

    private static final long serialVersionUID = 1000162674732065840L;

    @Autowired
    private ApplicationContext applicationContext;

    private Map<? extends Cmd, List<? extends Product>> cmdArgsMap;

    /**
     * This mehtod scan alla available class that are defined via {@link MenuWiredOption}
     * 
     */
    private void setUpAvailableOption() {
        String[] commandsBeanName = applicationContext.getBeanNamesForAnnotation(MenuWireOption.class);
        for (String currentBean : commandsBeanName) {
            MenuWireOption wiredOption = applicationContext.getBean(currentBean).getClass()
                    .getAnnotation(MenuWireOption.class);
            if (wiredOption.isAvailable()) { // this is the a way a product or a command can be excluded from the available options. 
                this.addOption(Option.builder(wiredOption.shortValue()).longOpt(wiredOption.value())
                        .required(wiredOption.isMandatory()).desc(wiredOption.description()).build());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setUpAvailableOption();

    }

}
