package com.tenjava.slipcor.impl.expandable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandableExperienceOrb extends FlyingExpandable {

	public FlyingExpandableExperienceOrb(final Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		for (String value : flyingArgs) {
			if (value.startsWith("value:")) {
				
				final String[] split = value.split("value:");
				
				final ExperienceOrb orb = (ExperienceOrb) entity;
				
				orb.setExperience(Integer.parseInt(split[1]));
			}
		}
	}

}
