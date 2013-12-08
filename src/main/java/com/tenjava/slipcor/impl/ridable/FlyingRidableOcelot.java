package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Ocelot;
import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableOcelot extends FlyingRidable {

	public FlyingRidableOcelot(Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * type:RED_CAT
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("type:")) {
				String[] split = value.split("type:");
				try {
					Ocelot.Type v = Ocelot.Type.valueOf(split[1].toUpperCase());

					Ocelot ocelot = (Ocelot) entity;
					ocelot.setCatType(v);
				} catch (Exception e) {
					
				}
			} else if (value.equals("tamed")) {
				try {
					Ocelot ocelot = (Ocelot) entity;
					ocelot.setTamed(true);
				} catch (Exception e) {
					
				}
			}
			

		}
	}

}
