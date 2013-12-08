package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableSkeleton extends FlyingRidable {

	public FlyingRidableSkeleton(final Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * type:WITHER
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("type:")) {
				final String[] split = value.split("type:");
				try {
					SkeletonType v = SkeletonType.valueOf(split[1].toUpperCase());

					Skeleton skellie = (Skeleton) entity;
					skellie.setSkeletonType(v);
				} catch (Exception e) {
					
				}
			}
			

		}
	}

}
