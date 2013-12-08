package com.tenjava.slipcor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdReload implements CommandExecutor {
	
	private final BowsPlus plugin;

	public CmdReload(final BowsPlus bowsPlus) {
		plugin = bowsPlus;
	}

	public boolean onCommand(final CommandSender sender,
			final Command cmd, final String label,
			final String[] args) {
		plugin.reloadConfig();
		Utils.reload();
		sender.sendMessage("Config reloaded!");
		return true;
	}

}
