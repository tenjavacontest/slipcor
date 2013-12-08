package com.tenjava.slipcor.impl;

import org.bukkit.entity.Entity;

import com.tenjava.slipcor.impl.expandable.FlyingExpandableExperienceOrb;
import com.tenjava.slipcor.impl.expandable.FlyingExpandableFallingBlock;
import com.tenjava.slipcor.impl.expandable.FlyingExpandableFirework;
import com.tenjava.slipcor.impl.expandable.FlyingExpandableMinecartMobSpawner;
import com.tenjava.slipcor.impl.expandable.FlyingExpandablePrimedTNT;

public class FlyingExpandable {
	protected final Entity entity;

	public FlyingExpandable(final Entity flyingEntity) {
		entity = flyingEntity;
	}

	public static FlyingExpandable parseToFlyingRidable(final Entity flyingEntity) {
		switch (flyingEntity.getType()) {
		case EXPERIENCE_ORB:
			return new FlyingExpandableExperienceOrb(flyingEntity);
		case FALLING_BLOCK:
			return new FlyingExpandableFallingBlock(flyingEntity);
		case FIREWORK:
			return new FlyingExpandableFirework(flyingEntity);
		case MINECART_MOB_SPAWNER:
			return new FlyingExpandableMinecartMobSpawner(flyingEntity);
		case PRIMED_TNT:
			return new FlyingExpandablePrimedTNT(flyingEntity);
		}
		return null;
	}

	public void parseArguments(final String[] flyingArgs) {
	}

}
