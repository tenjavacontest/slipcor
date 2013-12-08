package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Ocelot;
import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableOcelot extends FlyingRidable {

	public FlyingRidableOcelot(final Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * type:RED_CAT
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("type:")) {
				final String[] split = value.split("type:");
				try {
					final Ocelot.Type v = Ocelot.Type.valueOf(split[1].toUpperCase());

					final Ocelot ocelot = (Ocelot) entity;
					ocelot.setCatType(v);
				} catch (Exception e) {
					
				}
			} else if (value.equals("tamed")) {
				try {
					final Ocelot ocelot = (Ocelot) entity;
					ocelot.setTamed(true);
				} catch (Exception e) {
					
				}
			}
			

		}
	}

}
