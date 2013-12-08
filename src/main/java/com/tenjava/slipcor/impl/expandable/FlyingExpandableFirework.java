package com.tenjava.slipcor.impl.expandable;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandableFirework extends FlyingExpandable {

	public FlyingExpandableFirework(final Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(final String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		//TODO add more varieties. A LOT of work :P
		
		final Firework fw = (Firework) entity;
		
		final FireworkMeta fwm = fw.getFireworkMeta();
		fwm.setPower(1);
		
		fwm.addEffect(FireworkEffect.builder().flicker(true).withColor(Color.BLACK).withColor(Color.WHITE).with(Type.BALL).build());
		
		fw.setFireworkMeta(fwm);
	}

}
