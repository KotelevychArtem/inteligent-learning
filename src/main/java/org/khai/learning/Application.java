package org.khai.learning;

import org.khai.learning.tools.DataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@SpringBootApplication(excludeName = {"DataLoader"})
@ComponentScan(
        basePackages = "org.khai.learning",
        excludeFilters = {
                    @ComponentScan.Filter(
                            type = ASSIGNABLE_TYPE,
                            value = {DataLoader.class}
                    )
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
