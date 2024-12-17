package selenium.automation.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@ComponentScan(basePackages = "selenium.automation")
public class AutomationFrameworkConfiguration {

    public AutomationFrameworkConfiguration() {}

    @Bean
    public static org.springframework.context.support.PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new org.springframework.context.support.PropertySourcesPlaceholderConfigurer();
    }
}
