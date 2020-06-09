package nl.ordina.poolautoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@Profile("test")
public class TestPropertiesConfigurer {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource(System.getProperty("user.home")
                + "/ApplicationProperties/poolauto-api/application-test.properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
