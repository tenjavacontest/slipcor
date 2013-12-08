package com.tenjava.slipcor.impl.expandable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandablePrimedTNT extends FlyingExpandable {

	public FlyingExpandablePrimedTNT(final Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);

		
		for (String value : flyingArgs) {
			if (value.startsWith("fuse:")) {
				
				final String[] split = value.split("fuse:");
				
				final TNTPrimed orb = (TNTPrimed) entity;
				
				orb.setFuseTicks(Integer.parseInt(split[1]));
			}
		}
	}

}
