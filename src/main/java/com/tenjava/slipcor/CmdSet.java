package com.tenjava.slipcor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

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
			
			if (player.getItemInHand() == null || player.getItemInHand().getType() != Material.BOW) {
				sender.sendMessage("You don't hold a bow in your hand. Fix this!");
				
				return true;
			}
			
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
				if (e == null) {
					player.setMetadata("bowplustype", new FixedMetadataValue(plugin, m.name()));
				} else {
					player.setMetadata("bowplustype", new FixedMetadataValue(plugin, e.name()));
				}
			} else {
				sender.sendMessage("You don't have the permission to spawn this!");
			}
			
		} else {
			sender.sendMessage("You don't have a bow in your hand. Wait.. you don't even have hands. What the?");
		}
		
		return false;
	}

}
