package com.tenjava.slipcor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BowsPlus extends JavaPlugin implements Listener {
	public String prefix = "["+ChatColor.AQUA+"Bows"+ChatColor.YELLOW+"+"+ChatColor.RESET+"] ";
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);

		Bukkit.getPluginCommand("bowsplusreload").setExecutor(new CmdReload(this));
		Bukkit.getPluginCommand("bowsplus").setExecutor(new CmdSet(this));
		
		saveDefaultConfig();
		
		Utils.init(this);
	}
	
	@EventHandler
	public void onBowFire(final ProjectileLaunchEvent event) {
		if (event.getEntity().getShooter() instanceof Player) {
			final Player player = (Player) event.getEntity().getShooter();
			
			if (player.getItemInHand() == null || player.getItemInHand().getType() != Material.BOW) {
				return;
			}

			EntityType eType = null;
			Material material = null;
			
			String[] type = null;
			
			for (MetadataValue value : player.getMetadata("bowplustype")) {
				if (value.getOwningPlugin().getDescription().getName().equals(this.getDescription().getName())) {
					type = (String[]) value.value();
				}
			}
			
			if (type == null) {
				return;
			}
			
			try {
				eType = EntityType.valueOf(type[0]);
			} catch (IllegalArgumentException iax) {
				material = Material.matchMaterial(type[0]);
			} 
			
			if (player.isOp() || Utils.hasPerms(player, eType, material)) {
				if (eType == null && material == null) {
					return;
				}
				
				if (!Utils.hasMats(player, material, type)) {
					player.sendMessage(prefix + ChatColor.RED + "You don't have enough " + material.name());
					return;
				}
				
				final Vector velocity = event.getEntity().getVelocity();
				final Location location = event.getEntity().getLocation();
				
				event.getEntity().remove();
				
				if (eType == null) {
					final Item flyingItem = location.getWorld().dropItem(location, new ItemStack(Material.ARROW));
					
					final Material flyingMat = material;
					final String[] flyingArgs = type;
					
					class RunLater implements Runnable {
						public void run() {
							flyingItem.getItemStack().setType(flyingMat);

							Utils.parseSubValues(flyingItem, flyingArgs);
							
							flyingItem.setVelocity(velocity);
						}
					}
					Bukkit.getScheduler().runTaskLater(this, new RunLater(), 1L);
				} else {
					
					final Entity flyingEntity = location.getWorld().spawn(location, eType.getEntityClass());
					final String[] flyingArgs = type;
					
					class RunLater implements Runnable {
						public void run() {
							Utils.parseSubValues(flyingEntity, Utils.lowerCase(flyingArgs));
							
							flyingEntity.setVelocity(velocity);
						}
					}
					Bukkit.getScheduler().runTaskLater(this, new RunLater(), 1L);
				}
				if (player.getGameMode() != GameMode.CREATIVE && !getConfig().getBoolean("uses.arrows")) {
					player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
				}
			} else {
				player.sendMessage(prefix + ChatColor.RED + "You don't have the permission to spawn this!");
			}
		}
	}
}
