package com.upfault.enhancednodes.utils;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class TriMap<K, V1, V2> {
	private final Map<K, Pair<V1, V2>> map = new HashMap<>();

	public void put(K key, V1 value1, V2 value2) {
		map.put(key, new Pair<>(value1, value2));
	}

	public Pair<V1, V2> get(K key) {
		return map.get(key);
	}

	public void remove(K key) {
		map.remove(key);
	}

	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public V1 getFirst(K key) {
		Pair<V1, V2> pair = map.get(key);
		return pair != null ? pair.getKey() : null;
	}

	public V2 getSecond(K key) {
		Pair<V1, V2> pair = map.get(key);
		return pair != null ? pair.getValue() : null;
	}
}

