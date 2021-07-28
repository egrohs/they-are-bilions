package solver.services;

import lombok.Getter;

@Getter
public enum Unit implements Entity {
	RANGER(1, 0, 1, Integer.MAX_VALUE), SOLDIER(3, 1, 1, Integer.MAX_VALUE);
	private int maxQnt;
	private int buildTime;
	private int upkeepGold, upkeepOil;
	private int costGold, costFood, costWorker;
	private Entity research;
	Unit(int uGold, int cFood, int cWorker, int maxQnt) {
		this.upkeepGold = uGold;
		this.costFood = cFood;
		this.costWorker = cWorker;
		this.maxQnt = maxQnt;
	}
}