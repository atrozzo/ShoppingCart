package com.bjss.shopping.app;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bjss.shopping")
public class App {

    public static void main(String[] args) {
         BasicConfigurator.configure();
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);

    }
}
