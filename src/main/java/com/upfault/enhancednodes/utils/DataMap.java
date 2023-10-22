package com.upfault.enhancednodes.utils;

import org.bukkit.Location;

import java.util.HashMap;

public class DataMap {
	private static final HashMap<Location, String> dataMap = new HashMap<>();

	public static void addData(Location location, String value) {
		dataMap.put(location, value);
	}

	public static String getData(Location location) {
		return dataMap.get(location);
	}

	public static void removeData(Location location) {
		dataMap.remove(location);
	}
}

