package net.mobilim.NaviGateWeb;

import net.mobilim.NaviGateWeb.Config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
