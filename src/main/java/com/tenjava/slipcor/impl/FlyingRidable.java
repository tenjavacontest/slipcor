package com.tenjava.slipcor.impl;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.tenjava.slipcor.api.IFlyingRidable;
import com.tenjava.slipcor.impl.flying.FlyingRidableCreeper;
import com.tenjava.slipcor.impl.flying.FlyingRidableHorse;
import com.tenjava.slipcor.impl.flying.FlyingRidableMagmaCube;
import com.tenjava.slipcor.impl.flying.FlyingRidableOcelot;
import com.tenjava.slipcor.impl.flying.FlyingRidableSheep;
import com.tenjava.slipcor.impl.flying.FlyingRidableSkeleton;
import com.tenjava.slipcor.impl.flying.FlyingRidableSlime;
import com.tenjava.slipcor.impl.flying.FlyingRidableVillager;

public class FlyingRidable implements IFlyingRidable {
	protected final Entity entity;
	
	public FlyingRidable(Entity flyingEntity) {
		entity = flyingEntity;
	}

	public static IFlyingRidable parseToFlyingRidable(Entity flyingEntity) {
		
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
			case CREEPER: // powered?
				return new FlyingRidableCreeper(flyingEntity);
			case SKELETON: // wither?
				return new FlyingRidableSkeleton(flyingEntity);
			case SLIME: // size?
				return new FlyingRidableSlime(flyingEntity);
			case MAGMA_CUBE: // size?
				return new FlyingRidableMagmaCube(flyingEntity);
			case SHEEP: // colors
				return new FlyingRidableSheep(flyingEntity);
			case OCELOT: // colors?
				return new FlyingRidableOcelot(flyingEntity);
			case HORSE: // fur ?
				return new FlyingRidableHorse(flyingEntity);
			case VILLAGER: // thingie?
				return new FlyingRidableVillager(flyingEntity);
		}
		return null;
	}

	public void parseArguments(String[] flyingArgs) {
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
					e.printStackTrace(); //TODO 
				}
			}
		}
	}

}
