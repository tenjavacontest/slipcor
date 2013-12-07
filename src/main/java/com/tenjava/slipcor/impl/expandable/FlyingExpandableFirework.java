package com.tenjava.slipcor.impl.expandable;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import com.tenjava.slipcor.impl.FlyingExpandable;

public class FlyingExpandableFirework extends FlyingExpandable {

	public FlyingExpandableFirework(Entity flyingEntity) {
		super(flyingEntity);
	}

	public void parseArguments(String[] flyingArgs) {
		super.parseArguments(flyingArgs);
		
		//TODO check if it actually does anything... first set a default thing
		
		Firework fw = (Firework) entity;
		
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.setPower(1);
		
		fwm.addEffect(FireworkEffect.builder().flicker(true).withColor(Color.BLACK).withColor(Color.WHITE).with(Type.BALL).build());
		
		fw.setFireworkMeta(fwm);
	}

}
