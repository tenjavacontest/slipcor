package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableCreeper extends FlyingRidable {

	public FlyingRidableCreeper(final Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);

		/**
		 * powered
		 */
		
		for (String value : flyingArgs) {
			if (value.equals("powered")) {
				Creeper c = (Creeper) entity;
				c.setPowered(true);
			}
		}
	}

}
