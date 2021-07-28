package solver.services;

import lombok.Getter;

@Getter
public enum Research implements Entity {
	// TODO nao eh 66 os valores
	RESEARCH_COTTAGE(350, 75, 1), RESEARCH_STONE_HOUSE(600, 66, 1), RESEARCH_FARM(400, 75, 1),
	RESEARCH_ADVANCED_FARM(1500, 66, 1), RESEARCH_ADVANCED_QUARRY(1500, 66, 1), RESEARCH_OIL_PLATFORM(1200, 66, 1),
	RESEARCH_ADVANCED_MILL(1400, 66, 1), RESEARCH_POWER_PLANT(800, 66, 1), RESEARCH_MARKET(450, 75, 1),
	RESEARCH_BANK(800, 66, 1),
	// RESEARCH_INN( 66, 66, 1),
	RESEARCH_STONE_WORKSHOP(500, 75, 1), RESEARCH_FOUNDRY(1000, 66, 1), RESEARCH_ENGINEERING_CENTER(2000, 66, 1);

	static {
		RESEARCH_COTTAGE.research = Building.WOOD_WORKSHOP;
		RESEARCH_STONE_HOUSE.research = Building.STONE_WORKSHOP;
		RESEARCH_FARM.research = Building.WOOD_WORKSHOP;
		RESEARCH_ADVANCED_FARM.research = Building.FOUNDRY;
		RESEARCH_ADVANCED_QUARRY.research = Building.FOUNDRY;
		RESEARCH_OIL_PLATFORM.research = Building.FOUNDRY;
		RESEARCH_ADVANCED_MILL.research = Building.FOUNDRY;
		RESEARCH_POWER_PLANT.research = Building.STONE_WORKSHOP;
		RESEARCH_MARKET.research = Building.WOOD_WORKSHOP;
		RESEARCH_BANK.research = Building.STONE_WORKSHOP;
		RESEARCH_STONE_WORKSHOP.research = Building.WOOD_WORKSHOP;
		RESEARCH_FOUNDRY.research = Building.STONE_WORKSHOP;
		RESEARCH_ENGINEERING_CENTER.research = Building.FOUNDRY;
	}

	private int maxQnt;
	// private Building building;
	private int costGold, buildTime;
	private Entity research;

	Research(int gold, int time, int maxQnt) {
		// TODO pq chega null em b ???
		// this.research = b;
		this.costGold = gold;
		this.buildTime = time;
		this.maxQnt = maxQnt;
	}
}