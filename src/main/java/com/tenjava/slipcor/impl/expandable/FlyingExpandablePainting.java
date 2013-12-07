package com.tenjava.slipcor.impl.expandable;

import org.bukkit.Art;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandablePainting extends FlyingExpandable {

	public FlyingExpandablePainting(Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * 
		 * art:ART
		 */
		
		
		for (String value : flyingArgs) {
			if (value.startsWith("art:")) {
				String[] split = value.split("art:");
				try {
					Art a = Art.valueOf(split[1]);
					
					Painting painting = (Painting) entity;
					painting.setArt(a);
				} catch (Exception e) {
					
				}
			}
		}
	}

}
