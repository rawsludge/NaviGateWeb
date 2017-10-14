package net.mobilim.NaviGateWeb;

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
@ComponentScan( basePackages = {"net.mobilim"})
@EntityScan("net.mobilim.NaviGateData.Entities")
@EnableJpaRepositories("net.mobilim.NaviGateData.Repositories")
public class WebApp
{
    public static void main( String[] args )
    {
    		SpringApplication.run(WebApp.class, args);
    }
}
