package com.tenjava.slipcor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public final class Utils {

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
			return p.hasPermission("bowsplus."+m.name().toLowerCase());
		}
		
		return p.hasPermission("bowsplus."+e.name().toLowerCase());
	}
	
	
}
