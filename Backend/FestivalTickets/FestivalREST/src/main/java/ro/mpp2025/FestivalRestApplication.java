package ro.mpp2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:App.xml")
//@ComponentScan(basePackages = "ro.mpp2025")
public class FestivalRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FestivalRestApplication.class, args);
    }

}