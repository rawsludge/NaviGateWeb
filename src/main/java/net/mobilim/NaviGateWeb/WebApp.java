package net.mobilim.NaviGateWeb;

import net.mobilim.NaviGateWeb.Config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class WebApp
{
    public static void main( String[] args )
    {
    		SpringApplication.run(ApplicationConfig.class, args);
    }
}
