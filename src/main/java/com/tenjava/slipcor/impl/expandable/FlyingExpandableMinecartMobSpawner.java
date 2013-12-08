package com.tenjava.slipcor.impl.expandable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.SpawnerMinecart;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandableMinecartMobSpawner extends FlyingExpandable {

	public FlyingExpandableMinecartMobSpawner(final Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		SpawnerMinecart sm = (SpawnerMinecart) entity;
		
		//TODO later
	}

}
