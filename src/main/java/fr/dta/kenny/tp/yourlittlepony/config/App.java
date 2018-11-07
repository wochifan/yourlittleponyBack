
package fr.dta.kenny.tp.yourlittlepony.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = { "fr.dta.kenny.tp.yourlittlepony" })
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "fr.dta.kenny.tp.yourlittlepony" })
@EntityScan(basePackages = { "fr.dta.kenny.tp.yourlittlepony" })
public class App {

	public static void main(String[] args) {
		
		SpringApplication.run(App.class, args);
		
	}
}
