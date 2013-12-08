package com.tenjava.slipcor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdReload implements CommandExecutor {
	
	private final BowsPlus plugin;

	public CmdReload(BowsPlus bowsPlus) {
		plugin = bowsPlus;
	}

	public boolean onCommand(CommandSender paramCommandSender,
			Command paramCommand, String paramString,
			String[] paramArrayOfString) {
		plugin.reloadConfig();
		Utils.reload();
		
		return true;
	}

}
