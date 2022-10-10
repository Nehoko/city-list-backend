package ru.idmikhailov.citylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.idmikhailov.citylist.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class CityListApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityListApplication.class, args);
    }

}
