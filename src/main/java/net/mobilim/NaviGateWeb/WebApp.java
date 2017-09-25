package net.mobilim.NaviGateWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.naviosa.*"})
public class WebApp
{
    public static void main( String[] args )
    {
    		SpringApplication.run(WebApp.class, args);
    }
}
