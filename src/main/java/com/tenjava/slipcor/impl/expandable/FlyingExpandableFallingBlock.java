package com.tenjava.slipcor.impl.expandable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandableFallingBlock extends FlyingExpandable {

	public FlyingExpandableFallingBlock(final Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * - amount:1
		 * - data:100
		 */
		Material mat = null;
		byte data = 0;
		
		for (String value : flyingArgs) {
			if (value.startsWith("material:")) {
				final String[] split = value.split("material:");
				final Material matt = Material.matchMaterial(split[1]);
				if (mat != null && matt.isBlock()) {
					mat = matt;
				}
			} else if (value.startsWith("data:")) {
				final String[] split = value.split("data:");
				data = Byte.parseByte(split[1]);
			}
		}
		
		if (mat == null) {
			return;
		}
		
		final Vector v = entity.getVelocity();
		final Location loc = entity.getLocation();
		
		entity.remove();
		FallingBlock block = loc.getWorld().spawnFallingBlock(loc, mat, data);
		block.setVelocity(v);
		
	}

}
