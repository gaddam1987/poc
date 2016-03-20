package gaddam1987.github.poc.axon.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AxonConfiguration.class)
@ComponentScan(basePackages = {"gaddam1987.github.poc.axon"})
public class WebConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(WebConfiguration.class);
    }
}
