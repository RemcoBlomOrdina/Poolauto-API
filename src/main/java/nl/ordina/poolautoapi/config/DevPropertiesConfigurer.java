package nl.ordina.poolautoapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

@Configuration()
@Profile("dev")
public class DevPropertiesConfigurer {

//    @Autowired
//    private Environment environment;

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        for (String e : environment.getActiveProfiles()) {
//            System.out.println(e);
//        }
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
//        properties.setLocations(
//                new FileSystemResource(System.getProperty("user.home") + "/ApplicationProperties/poolauto-api/application-dev.properties"),
//                new FileSystemResource(System.getProperty("user.home") + "/ApplicationProperties/poolauto-api/application-test.properties")
//        );
        properties.setLocation(
                new FileSystemResource(System.getProperty("user.home") + "/ApplicationProperties/poolauto-api/application-dev.properties")
        );
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
