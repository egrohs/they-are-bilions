package solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import solver.services.Goal;
import solver.services.Resource;
import solver.services.TABillions;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TheyAreBillionsApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TheyAreBillionsApplication.class, args);
	}
	
	@Autowired
	private TABillions trb;

	@Override
	public void run(String... args) throws Exception {
		trb.getGoals().push(new Goal(Resource.COLONISTS, 300));
		trb.solve();
	}
}