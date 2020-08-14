package apiPjt.soccerAPI;

import apiPjt.soccerAPI.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SoccerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerApiApplication.class, args);
	}

}
