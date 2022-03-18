package com.study.springmvc.case02.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	private static List<Set<Integer>> lottos = new ArrayList<>();

	public List<Set<Integer>> getLottos() {
		return lottos;
	}

	public void addLottos() {
		lottos.add(0, generateLotto());
	}

	public void updateLottos(int index) {
		lottos.set(index, generateLotto());
	}

	public void removeLottos(int index) {
		lottos.remove(index);
	}

	private Set<Integer> generateLotto() {
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(new Random().nextInt(39) + 1);
		}
		return lotto;
	}

	public Map<Object, Object> getStatistics() {		
		return getLottos().stream().flatMap(lotto -> lotto.stream())
				.collect(Collectors.toMap(Function.identity(), c -> 1, Integer::sum)).entrySet().stream()
				.sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (m1, m2) -> m2, LinkedHashMap::new));
	}
}