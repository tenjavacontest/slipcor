package com.tenjava.slipcor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onBowFire(ProjectileLaunchEvent event) {
		if (event.getEntity().getShooter() instanceof Player) {
			final Vector v = event.getEntity().getVelocity();
			Location l = event.getEntity().getLocation();
			
			event.getEntity().remove();
			
			final Creeper c = l.getWorld().spawn(l, Creeper.class);
			
			class RunLater implements Runnable {
				public void run() {
					c.setVelocity(v);
				}
			}
			Bukkit.getScheduler().runTaskLater(this, new RunLater(), 1L);
			
		}
	}
}
