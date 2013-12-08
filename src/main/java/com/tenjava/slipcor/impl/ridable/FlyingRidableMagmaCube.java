package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableMagmaCube extends FlyingRidable {

	public FlyingRidableMagmaCube(final Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * size:4
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("size:")) {
				final String[] split = value.split("size:");
				try {

					MagmaCube mc = (MagmaCube) entity;
					mc.setSize(Integer.parseInt(split[1]));
				} catch (Exception e) {
					
				}
			}
		}
	}

}
