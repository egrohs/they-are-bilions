package solver.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

//TODO como lidar com as builds em construcao q deveriam estar em cada estado em separado?
public abstract class Game {
//	@Getter
//	protected Stack<State> caminho = new Stack<>();
	@Getter
	State root, melhor;// = root, lastMelhor = root;
	public static final int produceTime = 40;// 8h in game
	// protected int time = 0;
	@Getter
	private Stack<Goal> goals = new Stack<>();
	private List<State> folhas = new ArrayList<>();
	private Map<String, State> nodos = new HashMap<>();

	public Game() {
		root = new State(true);
		root.atualiza(root);
		// root = new State().addBuilding(Building.COMMAND_CENTER);
		// expandAll(root);
		melhor = root;
//		caminho.push(melhor);
		root.setRes(root.getRes().entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Entry<Resource, Integer>::getKey, Entry<Resource, Integer>::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new)));
	}

	/**
	 * Constroi cada construção possivel.
	 * 
	 * @param node
	 */
	private void expandAll(State node) {
//		node.getEntities().entrySet().stream().sorted(Map.Entry.<Integer, Entity>comparingByKey())
//				.forEach(System.out::println);

		// TODO se produz algo util pro goal(s?)
		// TODO priorizar recursos que estao acabando.

		List<Entity> candidates = new ArrayList<>();
		//Arrays.asList(Building.values()).forEach(b -> b.getProduce());
		node.getRes().keySet().forEach(k -> candidates.add(Building.getBuilding(k)));

//		List<Entity> candidates = Stream.of(Arrays.asList(Building.values()), Arrays.asList(Research.values()))
//				.flatMap(x -> x.stream()).collect(Collectors.toList());

		List<Entity> todas = node.getEntities().values().stream().flatMap(List::stream).collect(Collectors.toList());
		Map<Integer, List<Entity>> mprontas = node.getEntities().entrySet().stream()
				.filter(map -> map.getKey().intValue() <= node.getTime())
				.collect(Collectors.toMap(Entry<Integer, List<Entity>>::getKey, map -> map.getValue()));
		List<Entity> prontas = mprontas.values().stream().flatMap(List::stream).collect(Collectors.toList());
		for (Entity e : candidates) {
			if (e.getMaxQnt() <= todas.stream().filter(b -> b == e).count() || (e instanceof Building
					&& List.of(Resource.SPACE, Resource.LIMIT, Resource.VISIBILITY, Resource.DAMAGE, Resource.DEFENSE)
							.contains(((Building) e).getProduce()))) {
				// se ja tem o max daquela entity
				continue;
			}
// if (e instanceof Research) {
//	 prontas.contains(e.getResearch());
//				List<Entity> l = node.getEntities().values().stream().flatMap(List::stream)
//						.collect(Collectors.toList());
//				if (l.contains(e)) {
//					continue;
//				}
//			}
			// TODO ver se tem a building pronta pra fazer o research

			// verifica se tem a building do research ja pronta

			if (e.getResearch() == null || prontas.contains(e.getResearch())) {
				expand(node, e);
			}
		}
		expand(node, null);
	}

	private void expand(State node, Entity b) {
		// int sobra = node.getGold();
//		if (b != null) {
//			sobra = canExpand(node, b);
//		}
		State s1 = node.addBuilding(b);
		if (s1 != null) {
//			State s = node.deepCopy();
//			s.setGold(sobra);
//			if (b != null)
//				;
//		//		s.addBuilding(b);
//			else
//				s.addTime();
			State s = nodos.get(s1.toString());
			if (s == null) {
				s = s1;
				nodos.put(s.toString(), s);
			} else {
				System.out.println("ja existe");
			}

			node.getChilds().add(s);
			// s.setParent(node);
			folhas.remove(node);
			if (s.getChilds().isEmpty())
				folhas.add(s);

			node.setRes(node.getRes().entrySet().stream().sorted(Map.Entry.comparingByValue())
					.collect(Collectors.toMap(Entry<Resource, Integer>::getKey, Entry<Resource, Integer>::getValue,
							(oldValue, newValue) -> oldValue, LinkedHashMap::new)));
		}
	}

	// protected abstract double heuristica(State node);

	/**
	 * Clona o estado avançando o tempo.
	 * 
	 * @param s
	 */
//	private State avanca(State s) {
//		State novo = s.deepCopy();
//		// novo.setGold(s.getGold() + s.getProduceGold());
//		s.getChilds().add(novo);
//		time += produceTime;
//		novo.setTime(time);
//		return novo;
//	}

	/**
	 * Algoritimo principal
	 */
	public void solve() {
		do {
			Goal g = goals.get(0);
			State atual = melhor;// caminho.peek();
			expandAll(atual);
//			score(atual);
			// lastMelhor = atual;
			melhor = best();
			System.out.println(atual);
			System.out.println(atual.getColonists() + " colonists, " + folhas.size() + " folhas.");
			// if (g.getQnt() <= atual.getColonists())
			List<Entity> todas = atual.getEntities().values().stream().flatMap(List::stream)
					.collect(Collectors.toList());
			if (todas.contains(Building.MARKET)) {
				// TODO remover os goals done em algum ponto.
				break;
			}
		} while (!goals.isEmpty());
	}

	/**
	 * Recursivo, calcula scores e avalia a melhor folha
	 * 
	 * @param s
	 */
//	private void score(State s) {
//		if (s.getChilds().isEmpty()) {
////			s.calcRes();
//			// se folha?ou todos calc heuristica
//			s.setScore(heuristica(s));
//		} else {
//			// recursao
//			for (State s1 : s.getChilds()) {
//				score(s1);
//			}
//		}
//	}

	private State best() {
		melhor = folhas.get(0);
		for (State s : folhas) {
			if (melhor.getScore() <= s.getScore()) {
				melhor = s;
			}
		}
		return melhor;
	}

	/**
	 * Se o estado pode contruir a building b.
	 * 
	 * @param s
	 * @param b
	 * @return O valor que restaria apos a construcao.
	 */
//	private int canExpand(State s, Building b) {
//		// TODO checar todos res necessrios
//		int r = s.getGold() - b.getCostGold();
//		return (r);
//	}

//	public int getResource(State s, Resource res) {
//		String name = res.name().toLowerCase();
//		try {
//			return (Integer) State.class.getDeclaredField(name).get(s);
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			throw new RuntimeException(e);
//		}
//	}
}