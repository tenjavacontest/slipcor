package com.tenjava.slipcor.impl.flying;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableSlime extends FlyingRidable {

	public FlyingRidableSlime(Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * size:4
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("size:")) {
				String[] split = value.split("size:");
				try {

					Slime mc = (Slime) entity;
					mc.setSize(Integer.parseInt(split[1]));
				} catch (Exception e) {
					
				}
			}
		}
	}

}
