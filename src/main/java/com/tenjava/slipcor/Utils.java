package com.tenjava.slipcor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public final class Utils {
	
	private static BowsPlus plugin = null;
	
	private static List<String> blacklist = new ArrayList<String>();
	private static List<String> whitelist = new ArrayList<String>();

	private Utils() {
	}
	
	public static String loreString = "" + ChatColor.AQUA + ChatColor.BLUE + ChatColor.BLACK;
/*
	public static EntityType parseToEntity(String type) {
		type = type.toUpperCase();
		for (EntityType result : EntityType.values()) {
			if (result.name().equals(type)) {
				return result;
			}
		}
		return null;
	}
	*/

	public static boolean hasPerms(Player p, EntityType e, Material m) {
		
		if (e == null && m == null) {
			return false;
		}
		
		if (e == null) {
			if (plugin.getConfig().getBoolean("uses.blacklist")) {
				if (blacklist.contains(m.name())) {
					return false;
				}
			}
			if (plugin.getConfig().getBoolean("uses.whitelist")) {
				if (!whitelist.contains(m.name())) {
					return false;
				}
			}
			return p.hasPermission("bowsplus."+m.name().toLowerCase());
		}
		if (plugin.getConfig().getBoolean("uses.blacklist")) {
			if (blacklist.contains(e.name())) {
				return false;
			}
		}
		if (plugin.getConfig().getBoolean("uses.whitelist")) {
			if (!whitelist.contains(e.name())) {
				return false;
			}
		}
		
		return p.hasPermission("bowsplus."+e.name().toLowerCase());
	}

	public static void init(BowsPlus bowsPlus) {
		plugin = bowsPlus;
		reload();
	}

	public static void reload() {
		blacklist = plugin.getConfig().getStringList("blacklist");
		whitelist = plugin.getConfig().getStringList("whitelist");
	}
}
