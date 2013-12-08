package com.tenjava.slipcor;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CmdSet implements CommandExecutor {
	
	private final BowsPlus plugin;

	public CmdSet(BowsPlus bowsPlus) {
		plugin = bowsPlus;
	}

	public boolean onCommand(CommandSender sender,
			Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			
			if (args.length < 1) {
				sender.sendMessage("Not enough arguments!");
				return false;
			}
			
			Player player = (Player) sender;
			
			String type = args[0];
			
			/*
			 * 
			 * mobs/livingentities [EntityType]
			 * 
			 * fallingblocks, (ignited) tnt [special]
			 * 
			 */
			
			EntityType e = null;
			Material m = null;
			
			try {
				e = EntityType.valueOf(type);
			} catch (IllegalArgumentException iax) {
				m = Material.matchMaterial(type);
			} 
			
			if (player.isOp() || Utils.hasPerms(player, e, m)) {

				if (e == null && m == null) {
					sender.sendMessage("Unknown item or entity: "+type+"!");
					return true;
				} else if (e == null) {
					args[0] = m.name();
				} else {
					args[0] = e.name();
				}
				player.setMetadata("bowplustype", new FixedMetadataValue(plugin, args));
				System.out.print("saving " + args[0] + " to " +player.getName());
				player.sendMessage("Your bows now fire: " + args[0]);
			} else {
				sender.sendMessage("You don't have the permission to spawn this!");
			}
			
		} else {
			sender.sendMessage("You don't have a bow in your hand. Wait.. you don't even have hands. What the?");
			
		}

		return true;
	}

}
