package solver.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // necessario pro deepcopy jackson
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class State implements Serializable, Cloneable {
	private static final long serialVersionUID = 8984359143335636337L;
	@Setter
	@Getter
	private double score;
	@JsonIgnore // necessario pro deepCopy nao copia-lo
	// @JsonIgnore por causa de loop do @ToString pois state tem lista dele mesmo
	@Getter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<State> childs = new HashSet<>();
//	@JsonIgnore
//	@Setter
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	private State parent;
//	@Getter
//	@Setter
//	private int produceGold = 0;
	@Setter
	private Map<Resource, Integer> res = new LinkedHashMap<>();

	// @Setter
	private int gold;
	private int wood = 20;
	private int ore = 0;
	private int iron = 0;
	private int oil = 0;
	@Setter
	@Getter
	private int time = 0;// -Game.produceTime;
	private int food;// = 19;
	private int energy;// = 30;
	private int workers;// = 5;
	@Setter
	@Getter
	private int colonists;// = 10;
	private int store;
	// TODO como map e descontar os upkeeps das buildings and units
	private Map<Integer, List<Entity>> entities = new HashMap<>();
//	private Map<Integer, List<Building>> buildings = new HashMap<>();
//	private Map<Integer, List<Unit>> units = new HashMap<>();
//	private Map<Integer, List<Research>> researches = new HashMap<>();

	public State(boolean initial) {
		if (initial) {
			this.entities.put(-1,
					List.of(Building.COMMAND_CENTER, Unit.RANGER, Unit.RANGER, Unit.RANGER, Unit.RANGER, Unit.SOLDIER));
		}
	}

//	public void addTime() {
//		this.time += 40;
//	}

	// protected abstract double heuristica(State node);

	public State addBuilding(Entity b) {
		State novo = this.clone();
		if (b != null) {
			// TODO novo.colonists += b.getCostColonists();
			abateCustos(b, novo);
			List<Entity> bs = novo.entities.get(novo.time + b.getBuildTime());
			if (bs == null) {
				bs = new ArrayList<>();
			}
			bs.add(b);
			novo.entities.put(novo.time + b.getBuildTime(), bs);
		} else {
			novo.time += Game.produceTime;
			atualiza(novo);
		}
		if (!novo.isValid()) {
			return null;
		}
		novo.score = TABillions.heuristica(novo);
//		if (b.getProduceGold() != null)
//			produceGold += b.getProduceGold();
		return novo;
	}

	private void addRes(Resource r, int x, boolean subtract) {
		Integer v = 0;
		// if (!this.res.isEmpty())
		v = this.res.get(r);
		if (v == null) {
			v = 0;
		}
		if (subtract) {
			x = x * -1;
		}
		this.res.put(r, v + x);
	}

	public void atualiza(State novo) {
		for (Integer bTime : novo.getEntities().keySet()) {
			for (Entity e : novo.getEntities().get(bTime)) {
				if (bTime <= novo.getTime()) {
					// a cada incremento produz build prontas
					produzRes(novo, e);
					if (e instanceof Building) {
						novo.gold -= ((Building) e).getUpkeepGold();
						addRes(Resource.GOLD, ((Building) e).getUpkeepGold(), true);
					}
					if (e instanceof Unit) {
						novo.gold -= ((Unit) e).getUpkeepGold();
						addRes(Resource.GOLD, ((Unit) e).getUpkeepGold(), true);
					}
					if ((bTime + Game.produceTime) >= novo.getTime()) {
						// apenas a primeira vez
						if (e instanceof Building) {
							Building b = (Building) e;
							novo.energy += b.getBonusEnergy();
							novo.food += b.getBonusFood();
							novo.store += b.getBonusStore();
							novo.colonists += b.getBonusColonists();
							novo.workers += b.getBonusWorkers();
						} else if (e instanceof Unit) {
							Unit u = (Unit) e;
							novo.food -= u.getCostFood();
							novo.workers -= u.getCostWorker();
						}
					}
				}
			}
		}
//		for (Integer uTime : novo.getUnits().keySet()) {
//			for (Unit u : novo.getUnits().get(uTime)) {
//				if (uTime <= novo.getTime()) {
//					// a cada incremento faz unit upkeeps
//					novo.gold -= u.getUpkeepGold();
//					if ((uTime + Game.produceTime) >= novo.getTime()) {
//						// apenas a primeira vez
//						novo.food -= u.getCostFood();
//						novo.workers -= u.getCostWorker();
//					}
//				}
//			}
//		}
	}

	private void produzRes(State novo, Entity e) {
		if (e instanceof Building) {
			Building b1 = (Building) e;
			novo.gold += b1.getProduceGold();
			addRes(Resource.GOLD, b1.getProduceGold(), false);
			addRes(Resource.WOOD, b1.getProduceWood(), false);
			addRes(Resource.ORE, b1.getProduceOre(), false);
			addRes(Resource.IRON, b1.getProduceIron(), false);
			addRes(Resource.OIL, b1.getProduceOil(), false);

			novo.wood += b1.getProduceWood();
			novo.iron += b1.getProduceIron();
			novo.ore += b1.getProduceOre();
			novo.oil += b1.getProduceOil();
		}
	}

	private void abateCustos(Entity e, State novo) {
		novo.gold -= e.getCostGold();
		addRes(Resource.GOLD, e.getCostGold(), true);
		if (e instanceof Building) {
			Building b = (Building) e;
			addRes(Resource.WOOD, b.getCostWood(), true);
			addRes(Resource.WORKER, b.getCostWorkers(), true);
			addRes(Resource.ENERGY, b.getCostEnergy(), true);
			addRes(Resource.FOOD, b.getCostFood(), true);
			addRes(Resource.ORE, b.getCostOre(), true);
			addRes(Resource.IRON, b.getCostIron(), true);
			addRes(Resource.OIL, b.getCostWood(), true);

			novo.wood -= b.getCostWood();
			novo.workers -= b.getCostWorkers();
			novo.energy -= b.getCostEnergy();
			novo.food -= b.getCostFood();
			novo.iron -= b.getCostIron();
			novo.oil -= b.getCostOil();
			novo.ore -= b.getCostOre();
		}
	}

//	public void calcRes1() {
//		List<Building> bs = new ArrayList<>();
//		for (Integer t : this.getEntities().keySet()) {
//			if (t <= this.getTime()) {
//				bs.addAll(this.getEntities().get(t));
//			}
//		}
//		for (Field f : State.class.getDeclaredFields()) {
//			if (f.getType().isAssignableFrom(Integer.class)) {
//				try {
//					int value = bs.stream().mapToInt(b -> {
//						try {
//							return (int) (Building.class.getDeclaredField("").get(b));
//						} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
//								| SecurityException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}).sum();
//					f.setAccessible(true);
//					f.set(this, value);
//				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
//					throw new RuntimeException(e);
//				} finally {
//					f.setAccessible(false);
//				}
//			}
//		}
//		// for (Field f: Building.class.getDeclaredFields()) {}
//	}

	/**
	 * @return True se o estado é valido, ex. res não negativos.
	 */
	@JsonIgnore
	public boolean isValid() {
		Map<Integer, List<Entity>> prontas = this.entities.entrySet().stream()
				.filter(map -> map.getKey().intValue() <= this.time)
				.collect(Collectors.toMap(Entry<Integer, List<Entity>>::getKey, map -> map.getValue()));
		List<Entity> l = prontas.values().stream().flatMap(List::stream).collect(Collectors.toList());
		List<Entity> todas = entities.values().stream().flatMap(List::stream).collect(Collectors.toList());

		for (Entity e : todas) {
			if (e.getResearch() != null && !l.contains(e.getResearch())) {
				return false;
			}
		}
		return !(energy < 0 || food < 0 || res.get(Resource.GOLD) < 0 || iron < 0 || ore < 0 || wood < 0 || workers < 0
				|| colonists < 0);
	}

//	public void calcRes2() {
//		// recalcula a producao de res
//		this.setProduceGold(0);
//		this.setColonists(0);
//		for (Integer t : this.getEntities().keySet()) {
//			if (t <= this.getTime()) {
//				int pgold = this.getEntities().get(t).stream().mapToInt(b -> b.getProduceGold()).sum();
//				this.addProduceGold(pgold);
//				this.addColonists(this.getEntities().get(t).stream().mapToInt(b -> b.getBonusColonists()).sum());
//			}
//		}
//		if (this.getParent().getTime() < this.getTime()) {
//			this.addGold(this.getProduceGold());
//		}
//	}

//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("@" + Integer.toHexString(hashCode()) + "\t");
//		sb.append("SCORE = " + score + "\t");
//		sb.append("TIME = " + time + "\t");
//		sb.append("GOLD = " + gold + "\t");
//		for (List<Building> bs : buildings.values()) {
//			for (Building b : bs) {
//				sb.append(b.name() + "\t");
//			}
//		}
//		return sb.toString();
//	}

	@Override
	public State clone() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			return objectMapper.readValue(objectMapper.writeValueAsString(this), State.class);
//		} catch (JsonProcessingException e) {
//			throw new RuntimeException(e);
//		}
		State s = null;
		try {
			s = (State) super.clone();
			s.childs = new HashSet<>();
			s.entities = new HashMap<>();// this.entities);
			for (Integer i : this.entities.keySet()) {
				s.entities.put(i.intValue(), new ArrayList<>(this.entities.get(i)));
			}
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		return s;
	}

//	public State setResource(Resource res, int v) {
//		State s = null;
//		String name = res.name().toLowerCase();
//		Field f = null;
//		try {
//			f = State.class.getDeclaredField(name);
//			f.setAccessible(true);
//			s = this.deepCopy();
//			f.set(s, v);
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			throw new RuntimeException(e);
//		} finally {
//			f.setAccessible(false);
//		}
//		return s;
//	}

//	public void addProduceGold(int sum) {
//		this.produceGold += sum;
//	}

//	public void addColonists(int sum) {
//		this.colonists += sum;
//	}

//	public void addGold(int gold) {
//		this.gold = gold;
//	}
}