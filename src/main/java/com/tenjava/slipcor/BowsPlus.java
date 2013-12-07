package com.tenjava.slipcor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BowsPlus extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);

		Bukkit.getPluginCommand("bowsplusreload").setExecutor(new CmdReload(this));
		Bukkit.getPluginCommand("bowsplus").setExecutor(new CmdSet(this));
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onBowFire(ProjectileLaunchEvent event) {
		if (event.getEntity().getShooter() instanceof Player) {
			Player player = (Player) event.getEntity().getShooter();
			
			if (player.getItemInHand() == null || player.getItemInHand().getType() != Material.BOW) {
				return;
			}

			EntityType e = null;
			Material m = null;
			
			String type = null;
			
			for (MetadataValue value : player.getMetadata("bowplustype")) {
				if (value.getOwningPlugin().getDescription().getName().equals(this.getDescription().getName())) {
					type = value.asString();
					System.out.print("value found for " + player.getName() + ": "+type);
				}
			}
			
			if (type == null) {
				return;
			}
			
			try {
				e = EntityType.valueOf(type);
			} catch (IllegalArgumentException iax) {
				m = Material.matchMaterial(type);
			} 
			
			if (player.isOp() || Utils.hasPerms(player, e, m)) {
				if (e == null || m == null) {
					return;
				}
			} else {
				player.sendMessage("You don't have the permission to spawn this!");
			}
			
			final Vector v = event.getEntity().getVelocity();
			Location l = event.getEntity().getLocation();
			
			event.getEntity().remove();
			
			if (e == null) {
				final Item c = l.getWorld().spawn(l, Item.class);
				
				final Material mat = m;
				
				class RunLater implements Runnable {
					public void run() {
						c.getItemStack().setType(mat);
						c.setVelocity(v);
					}
				}
				Bukkit.getScheduler().runTaskLater(this, new RunLater(), 1L);
			} else {
				
				final Entity c = l.getWorld().spawn(l, e.getEntityClass());
				
				class RunLater implements Runnable {
					public void run() {
						c.setVelocity(v);
					}
				}
				Bukkit.getScheduler().runTaskLater(this, new RunLater(), 1L);
			}
			
			
		}
	}
}
