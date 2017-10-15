package net.mobilim.NaviGateWeb.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;

@ComponentScan( basePackages = {"net.mobilim.NaviGateWeb"})
@EntityScan("net.mobilim.NaviGateData.Entities")
@EnableJpaRepositories("net.mobilim.NaviGateData.Repositories")
public class ApplicationConfig {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(simpleDateFormat);
        objectMapper.registerModule(
            new Hibernate5Module()
                .configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true)
        );
        return  objectMapper;
    }
}
