package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableVillager extends FlyingRidable {

	public FlyingRidableVillager(Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * profession:BUTCHER
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("profession:")) {
				String[] split = value.split("profession:");
				try {
					Profession v = Profession.valueOf(split[1].toUpperCase());

					Villager villager = (Villager) entity;
					villager.setProfession(v);
				} catch (Exception e) {
					
				}
			}
			

		}
	}

}
