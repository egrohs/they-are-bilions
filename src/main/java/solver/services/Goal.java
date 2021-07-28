package solver.services;
import lombok.Data;

@Data
public class Goal {
	public Goal(Resource res, int i) {
		this.resource = res;
		this.qnt = i;
	}

	public Goal(Building b, int i) {
		this.building = b;
		this.qnt = i;
	}

	private Building building;
	private Resource resource;
	private int qnt;
}