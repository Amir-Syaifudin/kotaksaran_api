package id.ac.stis.kotaksaran; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "id.ac.stis.kotaksaran") 
@EntityScan(basePackages = "id.ac.stis.kotaksaran")
public class KotaksaranApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotaksaranApiApplication.class, args);
    }
}