package com.tenjava.slipcor.impl;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.tenjava.slipcor.impl.ridable.FlyingRidableCreeper;
import com.tenjava.slipcor.impl.ridable.FlyingRidableHorse;
import com.tenjava.slipcor.impl.ridable.FlyingRidableMagmaCube;
import com.tenjava.slipcor.impl.ridable.FlyingRidableOcelot;
import com.tenjava.slipcor.impl.ridable.FlyingRidableSheep;
import com.tenjava.slipcor.impl.ridable.FlyingRidableSkeleton;
import com.tenjava.slipcor.impl.ridable.FlyingRidableSlime;
import com.tenjava.slipcor.impl.ridable.FlyingRidableVillager;

public class FlyingRidable  {
	protected final Entity entity;
	
	public FlyingRidable(final Entity flyingEntity) {
		entity = flyingEntity;
	}

	public static FlyingRidable parseToFlyingRidable(final Entity flyingEntity) {
		
		switch (flyingEntity.getType()) {
			// standard
			case SPIDER:
			case GIANT:
			case ZOMBIE:
			case GHAST:
			case PIG_ZOMBIE:
			case ENDERMAN:
			case CAVE_SPIDER:
			case SILVERFISH:
			case BLAZE:
			case BAT:
			case WITCH:
			case PIG:
			case COW: 
			case CHICKEN:
			case SQUID:
			case WOLF:
			case MUSHROOM_COW:
			case IRON_GOLEM:
				return new FlyingRidable(flyingEntity);
		
			// special case
			case CREEPER:
				return new FlyingRidableCreeper(flyingEntity);
			case SKELETON:
				return new FlyingRidableSkeleton(flyingEntity);
			case SLIME:
				return new FlyingRidableSlime(flyingEntity);
			case MAGMA_CUBE:
				return new FlyingRidableMagmaCube(flyingEntity);
			case SHEEP:
				return new FlyingRidableSheep(flyingEntity);
			case OCELOT:
				return new FlyingRidableOcelot(flyingEntity);
			case HORSE:
				return new FlyingRidableHorse(flyingEntity);
			case VILLAGER:
				return new FlyingRidableVillager(flyingEntity);
		}
		return null;
	}

	public void parseArguments(final String[] flyingArgs) {
		/**
		 * baby
		 * rider:RIDER
		 */
		
		for (String value : flyingArgs) {
			if (value.equals("baby") && entity instanceof Ageable) {
				Ageable aEntity = (Ageable) entity;
				aEntity.setBaby();
			} else if (value.startsWith("rider:")) {
				String[] split = value.split("rider:");
				
				try {
					EntityType eType = EntityType.fromName(split[1].toUpperCase());
					Entity passenger = entity.getWorld().spawnEntity(entity.getLocation(), eType);
					
					entity.setPassenger(passenger);
				} catch (Exception e) {
					
				}
			}
		}
	}

}
