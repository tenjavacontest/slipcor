package com.tenjava.slipcor.impl.flying;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableHorse extends FlyingRidable {

	public FlyingRidableHorse(Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * variant:DONKEY
		 * style:WHITEFIELD
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("variant:")) {
				String[] split = value.split("variant:");
				try {
					Variant v = Variant.valueOf(split[1].toUpperCase());

					Horse horse = (Horse) entity;
					horse.setVariant(v);
				} catch (Exception e) {
					
				}
			} else if (value.startsWith("style:")) {
				String[] split = value.split("style:");
				try {
					Style style = Style.valueOf(split[1].toUpperCase());

					Horse horse = (Horse) entity;
					horse.setStyle(style);
				} catch (Exception e) {
					
				}
			}
			

		}
	}
}
