package com.tenjava.slipcor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CmdSet implements CommandExecutor {
	
	private final BowsPlus plugin;

	public CmdSet(final BowsPlus bowsPlus) {
		plugin = bowsPlus;
	}

	public boolean onCommand(final CommandSender sender,
			final Command cmd, final String label,
			final String[] args) {
		if (sender instanceof Player) {
			
			if (args.length < 1) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Not enough arguments!");
				return false;
			}
			
			final Player player = (Player) sender;
			
			final String type = args[0].toUpperCase();
			
			/*
			 * 
			 * mobs/livingentities [EntityType]
			 * 
			 * fallingblocks, (ignited) tnt [special]
			 * 
			 */
			
			EntityType eType = null;
			Material material = null;
			
			try {
				eType = EntityType.valueOf(type);
			} catch (IllegalArgumentException iax) {
				material = Material.matchMaterial(type);
			} 
			
			if (player.isOp() || Utils.hasPerms(player, eType, material)) {

				if (eType == null && material == null) {
					sender.sendMessage(plugin.prefix + ChatColor.RED + "Unknown item or entity: "+type+"!");
					return true;
				} else if (eType == null) {
					args[0] = material.name();
				} else {
					args[0] = eType.name();
				}
				player.setMetadata("bowplustype", new FixedMetadataValue(plugin, args));
				player.sendMessage(plugin.prefix + ChatColor.DARK_GRAY + "Your bows now fire: " + ChatColor.WHITE + args[0]);
			} else {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "You don't have the permission to spawn this!");
			}
			
		} else {
			sender.sendMessage("Wait.. you don't even have hands. How would you shoot an arrow?");
			
		}

		return true;
	}

}
