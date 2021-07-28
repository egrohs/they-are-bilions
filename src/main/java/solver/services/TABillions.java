package solver.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class TABillions extends Game {
	// @Override
	public static double heuristica(State node) {
		Map<Integer, List<Entity>> prontas = node.getEntities().entrySet().stream()
				.filter(map -> map.getKey().intValue() <= node.getTime())
				.collect(Collectors.toMap(Entry<Integer, List<Entity>>::getKey, map -> map.getValue()));

		long cTodas = node.getEntities().values().stream().flatMap(List::stream).count();
		long cProntas = prontas.values().stream().flatMap(List::stream).count();
		int res = node.getColonists() + node.getEnergy() + node.getFood() + node.getFood()
				+ (int) (0.004 * node.getRes().get(Resource.GOLD)) + node.getIron() + node.getOil() + node.getOre() + node.getStore()
				+ node.getWood() + node.getWorkers();
		if (res > 9999) {
			res = 9999;
		}
		return Double.parseDouble("" + cProntas + (cTodas - cProntas) + String.format("%04d", res))
				- (4300 * node.getTime());
	}
}