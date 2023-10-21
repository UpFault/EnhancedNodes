package com.upfault.enhancednodes.utils;

import java.util.Collection;
import java.util.List;

public class OreInfo {
	private final MessageChance[] messageChances;

	public OreInfo(MessageChance... messageChances) {
		this.messageChances = messageChances;
	}

	public Collection<? extends MessageChance> getMessageChances() {
		return List.of(messageChances);
	}
}

