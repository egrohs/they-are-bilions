package solver.services;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum Building implements Entity {
//Tier	Rank	;
	COMMAND_CENTER(1, Resource.GOLD, "Command Center", -1, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 10, 20, 30, 200,
			0, 0, 0, 0),
	TENT(Integer.MAX_VALUE, Resource.COLONISTS, "Tent", 21, 4, 4, 1, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 2, 0, 0, 8, 0, 0,
			0, 0),
	COTTAGE(Integer.MAX_VALUE, Resource.COLONISTS, "Cottage", 36, 4, 8, 3, 120, 12, 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0,
			18, 0, 0, 0, 0),
	STONE_HOUSE(Integer.MAX_VALUE, Resource.COLONISTS, "Stone House", 48, 4, 16, 10, 300, 5, 10, 0, 0, 0, 0, 0, 0, 0,
			16, 8, 0, 0, 40, 0, 0, 0, 0),
	HUNTERS_COTTAGE(Integer.MAX_VALUE, Resource.FOOD, "Hunters Cottage", 30, 1, 0, 1, 80, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2,
			0, 12, 0, 0, 0, 0, 0, 0),
	FISHERMAN_COTTAGE(Integer.MAX_VALUE, Resource.FOOD, "Fisherman Cottage", 30, 1, 0, 1, 80, 0, 0, 0, 0, 0, 0, 2, 0, 0,
			2, 0, 12, 0, 0, 0, 0, 0, 0),
	FARM(Integer.MAX_VALUE, Resource.FOOD, "Farm", 45, 25, 0, 4, 300, 30, 0, 0, 0, 0, 0, 20, 0, 0, 12, 0, 33, 0, 0, 0,
			0, 0, 0),
	ADVANCED_FARM(Integer.MAX_VALUE, Resource.FOOD, "Advanced Farm", 26, 25, 0, 30, 1200, 30, 20, 0, 20, 20, 0, 50, 0,
			0, 24, 0, 66, 0, 0, 0, 0, 0, 0),
	QUARRY(Integer.MAX_VALUE, Resource.ORE, "Quarry", 39, 4, 0, 4, 300, 30, 0, 4, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 5,
			5, 0),
	ADVANCED_QUARRY(Integer.MAX_VALUE, Resource.ORE, "Advanced Quarry", 45, 4, 0, 15, 1200, 30, 0, 4, 20, 20, 0, 30, 0,
			0, 0, 0, 0, 0, 0, 0, 10, 5, 0),
	SAWMILL(Integer.MAX_VALUE, Resource.WOOD, "Sawmill", 39, 4, 0, 4, 300, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 12,
			0, 0, 0),
	OIL_PLATFORM(Integer.MAX_VALUE, Resource.OIL, "Oil Platform", 60, 4, 0, 30, 1200, 0, 20, 8, 0, 20, 0, 80, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 10),
	TESLA_TOWER(Integer.MAX_VALUE, Resource.SPACE, "Tesla Tower", 30, 1, 0, 0, 200, 10, 0, 1, 0, 0, 0, 4, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	MILL(Integer.MAX_VALUE, Resource.ENERGY, "Mill", 39, 4, 0, 0, 300, 20, 0, 4, 0, 0, 0, 6, 0, 0, 0, 0, 0, 30, 0, 0, 0,
			0, 0),
	ADVANCED_MILL(Integer.MAX_VALUE, Resource.ENERGY, "Advanced Mill", 0, 4, 0, 0, 800, 20, 0, 4, 0, 10, 0, 30, 0, 0, 0,
			0, 0, 50, 0, 0, 0, 0, 0),
	POWER_PLANT(Integer.MAX_VALUE, Resource.ENERGY, "Power Plant", 0, 9, 0, 0, 800, 50, 30, 4, 0, 0, 0, 40, 10, 0, 0, 0,
			0, 160, 0, 0, 0, 0, 0),
	WAREHOUSE(Integer.MAX_VALUE, Resource.LIMIT, "Warehouse", 0, 16, 0, 8, 400, 40, 40, 8, 0, 0, 0, 30, 0, 50, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	WOOD_WORKSHOP(1, Resource.UPGRADE, "Wood Workshop", 90, 16, 0, 10, 400, 40, 40, 10, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0),
	STONE_WORKSHOP(1, Resource.UPGRADE, "Stone Workshop", 90, 16, 0, 20, 1000, 40, 40, 15, 0, 0, 0, 40, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	FOUNDRY(1, Resource.UPGRADE, "Foundry", 0, 16, 0, 50, 2000, 30, 30, 20, 0, 50, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0),
	MARKET(3, Resource.GOLD, "Market", 60, 9, 0, 8, 400, 30, 40, 8, 0, 0, 200, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	THE_INN(0, Resource.COLONISTS, "The Inn", 0, 16, 100, 65, 2000, 100, 100, 0, 0, 0, 800, 0, 30, 0, 0, 100, 0, 0, 0,
			0, 0, 0, 0),
	BANK(1, Resource.GOLD, "Bank", 90, 9, 0, 20, 1000, 50, 50, 10, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	SOLDIERS_CENTER(Integer.MAX_VALUE, Resource.DAMAGE, "Soldiers Center", 60, 9, 0, 6, 450, 20, 20, 8, 0, 0, 0, 14, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	ENGINEERING_CENTER(1, Resource.UPGRADE, "Engineering Center", 0, 9, 0, 50, 2000, 0, 40, 20, 0, 40, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0),
	LOOKOUT_TOWER(Integer.MAX_VALUE, Resource.VISIBILITY, "Lookout Tower", 0, 1, 0, 0, 300, 10, 0, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0),
	RADAR_TOWER(Integer.MAX_VALUE, Resource.VISIBILITY, "Radar Tower", 0, 1, 0, 10, 800, 10, 10, 2, 0, 10, 0, 30, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0),
	GREAT_BALLISTA(Integer.MAX_VALUE, Resource.DAMAGE, "Great Ballista", 0, 4, 0, 2, 500, 20, 0, 2, 0, 0, 0, 15, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0),
	EXECUTOR(Integer.MAX_VALUE, Resource.DAMAGE, "Executor", 0, 4, 0, 10, 1200, 0, 20, 4, 0, 30, 0, 50, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	SHOCKING_TOWER(Integer.MAX_VALUE, Resource.DAMAGE, "Shocking Tower", 0, 4, 0, 30, 600, 30, 30, 4, 0, 0, 0, 20, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0),
	WOOD_WALL(Integer.MAX_VALUE, Resource.DEFENSE, "Wood Wall", 0, 1, 0, 0, 10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0),
	STONE_WALL(Integer.MAX_VALUE, Resource.DEFENSE, "Stone Wall", 0, 1, 0, 0, 30, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0),
	WOOD_GATE(Integer.MAX_VALUE, Resource.DEFENSE, "Wood Gate", 0, 3, 0, 0, 50, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0),
	STONE_GATE(Integer.MAX_VALUE, Resource.DEFENSE, "Stone Gate", 0, 3, 0, 0, 200, 3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	WOOD_TOWER(Integer.MAX_VALUE, Resource.DAMAGE, "Wood Tower", 0, 1, 0, 0, 120, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	STONE_TOWER(Integer.MAX_VALUE, Resource.DAMAGE, "Stone Tower", 0, 1, 0, 0, 450, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0),
	STAKES_TRAP(Integer.MAX_VALUE, Resource.DAMAGE, "Stakes Trap", 0, 1, 0, 0, 30, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0),
	WIRE_FENCE_TRAP(Integer.MAX_VALUE, Resource.DAMAGE, "Wire Fence Trap", 0, 1, 0, 0, 100, 0, 0, 0, 0, 3, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0),
	THANATOS(Integer.MAX_VALUE, Resource.DAMAGE, "Thanatos", 0, 0, 0, 0, 600, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0),
	SNIPER(Integer.MAX_VALUE, Resource.DAMAGE, "Sniper", 0, 0, 1, 0, 120, 2, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0),
	RANGER(Integer.MAX_VALUE, Resource.DAMAGE, "Ranger", 0, 0, 0, 0, 120, 2, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0);

	static {
		COTTAGE.research = Research.RESEARCH_COTTAGE;
		STONE_HOUSE.research = Research.RESEARCH_STONE_HOUSE;
		FARM.research = Research.RESEARCH_FARM;
		ADVANCED_FARM.research = Research.RESEARCH_ADVANCED_FARM;
		ADVANCED_QUARRY.research = Research.RESEARCH_ADVANCED_QUARRY;
		OIL_PLATFORM.research = Research.RESEARCH_OIL_PLATFORM;
		ADVANCED_MILL.research = Research.RESEARCH_ADVANCED_MILL;
		POWER_PLANT.research = Research.RESEARCH_POWER_PLANT;
		STONE_WORKSHOP.research = Research.RESEARCH_STONE_WORKSHOP;
		FOUNDRY.research = Research.RESEARCH_FOUNDRY;
		MARKET.research = Research.RESEARCH_MARKET;
		BANK.research = Research.RESEARCH_BANK;
		ENGINEERING_CENTER.research = Research.RESEARCH_ENGINEERING_CENTER;
	}

	public static Building getBuilding(Resource r) {
		for (Building b : values()) {
			if (b.getProduce() == r) {
				return b;
			}
		}
		//TODO gambiarra aqui
		if (r == Resource.IRON)
			return Building.QUARRY;
		return null;
	}

	private Building(int maxQnt, Resource res, String name, int btime, int size, int cfood, int cenergy, int cGold,
			int cwood, int cOre, int cworker, int coil, int ciron, int ccol, int ugold, int uwood, int bstore, int bcol,
			int bwork, int bfood, int benergy, int pGold, int pWood, int pOre, int pIron, int pOil/* , Research r */) {
		this.maxQnt = maxQnt;
		this.produce = res;
		this.name = name;
		this.buildTime = btime;
		this.size = size;
		this.costFood = cfood;
		this.costEnergy = cenergy;
		this.costGold = cGold;
		this.costWood = cwood;
		this.costOre = cOre;
		this.costWorkers = cworker;
		this.costOil = coil;
		this.costIron = ciron;
		this.costColonists = ccol;
		this.upkeepGold = ugold;
		this.upkeepWood = uwood;
		this.bonusStore = bstore;
		this.bonusColonists = bcol;
		this.bonusWorkers = bwork;
		this.bonusFood = bfood;
		this.bonusEnergy = benergy;
		this.produceGold = pGold;
		this.produceWood = pWood;
		this.produceOre = pOre;
		this.produceIron = pIron;
		this.produceOil = pOil;
		// this.research = r;
		if ("Command Center".equals(this.name))
			this.bonusWood = 20;
	}

	int bonusWood;

	private int maxQnt;
	private Resource produce;
	private String name;
	private int buildTime;// Utime
	private int size;
	private int costFood;
	private int costEnergy;
	private int costGold;// UGold
	private int costWood;
	private int costOre;
	private int costIron;
	private int costOil;
	private int costWorkers;
	private int costColonists;
	private int upkeepGold;// 8h/40s
	private int upkeepWood;/// 8h
	private int bonusStore;// Gold
	private int bonusColonists;
	private int bonusWorkers;
	private int bonusFood;
	private int bonusEnergy;
	private int produceGold;
	private int produceWood;
	private int produceOre;
	private int produceIron;
	private int produceOil;

	private Research research;

	// private int areaCoverage;
//private int Requires	Stats	Provides
	public static List<Building> candidates(Resource res) {
		List<Building> ret = new ArrayList<>();
		for (Building b : values()) {
			if (b.produce == res) {
				ret.add(b);
			}
		}
		return ret;
	}
}