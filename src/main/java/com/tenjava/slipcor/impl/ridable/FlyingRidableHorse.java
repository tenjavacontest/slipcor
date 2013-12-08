package com.tenjava.slipcor.impl.ridable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

import com.tenjava.slipcor.impl.FlyingRidable;

public class FlyingRidableHorse extends FlyingRidable {

	public FlyingRidableHorse(final Entity flyingEntity) {
		super(flyingEntity);
	}

	@Override
	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		/**
		 * variant:DONKEY
		 * style:WHITEFIELD
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("variant:")) {
				String[] split = value.split("variant:");
				try {
					final Variant v = Variant.valueOf(split[1].toUpperCase());

					final Horse horse = (Horse) entity;
					horse.setVariant(v);
				} catch (Exception e) {
					
				}
			} else if (value.startsWith("style:")) {
				String[] split = value.split("style:");
				try {
					final Style style = Style.valueOf(split[1].toUpperCase());

					final Horse horse = (Horse) entity;
					horse.setStyle(style);
				} catch (Exception e) {
					
				}
			}
			

		}
	}
}
