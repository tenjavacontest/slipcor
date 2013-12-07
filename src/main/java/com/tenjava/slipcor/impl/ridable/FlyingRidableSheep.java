package com.tenjava.slipcor.impl.ridable;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableSheep extends FlyingRidable {

	public FlyingRidableSheep(Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * color:BLACK
		 * sheared
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("color:")) {
				String[] split = value.split("color:");
				try {
					DyeColor v = DyeColor.valueOf(split[1].toUpperCase());

					Sheep sheep = (Sheep) entity;
					sheep.setColor(v);
				} catch (Exception e) {
					
				}
			} else if (value.equals("sheared")) {
				Sheep sheep = (Sheep) entity;
				sheep.setSheared(true);
			}
		}
	}
}
