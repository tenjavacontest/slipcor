package com.tenjava.slipcor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import com.tenjava.slipcor.api.IFlyingRidable;
import com.tenjava.slipcor.impl.FlyingRidable;

public final class Utils {
	
	private static BowsPlus plugin = null;
	
	private static List<String> blacklist = new ArrayList<String>();
	private static List<String> whitelist = new ArrayList<String>();

	private Utils() {
	}
	
	public static String loreString = "" + ChatColor.AQUA + ChatColor.BLUE + ChatColor.BLACK;

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

	public static void parseSubValues(Item flyingItem, String[] flyingArgs) {
		/**
		 * - amount:1
		 * - durabilty:1
		 * - data:100
		 */
		
		for (String value : flyingArgs) {
			if (value.startsWith("amount:")) {
				String[] split = value.split("amount:");
				int amount = Integer.parseInt(split[1]);
				
				flyingItem.getItemStack().setAmount(amount);
			} else if (value.startsWith("durability:")) {
				String[] split = value.split("durability:");
				short durability = Short.parseShort(split[1]);
				
				flyingItem.getItemStack().setDurability(durability);
			} else if (value.startsWith("data:")) {
				String[] split = value.split("data:");
				byte data = Byte.parseByte(split[1]);
				flyingItem.getItemStack().getData().setData(data);
			}
		}
		
	}

	public static void parseSubValues(Entity flyingEntity, String[] flyingArgs) {
		switch (flyingEntity.getType()) {
			
			
		// rideables
		case CREEPER:
		case SKELETON:
		case SPIDER:
		case GIANT:
		case ZOMBIE:
		case SLIME:
		case GHAST:
		case PIG_ZOMBIE:
		case ENDERMAN:
		case CAVE_SPIDER:
		case SILVERFISH:
		case BLAZE:
		case MAGMA_CUBE:
		case BAT:
		case WITCH:
		case PIG:
		case SHEEP:
		case COW:
		case CHICKEN:
		case SQUID:
		case WOLF:
		case MUSHROOM_COW:
		case OCELOT:
		case IRON_GOLEM:
		case HORSE:
		case VILLAGER:
			IFlyingRidable flying = FlyingRidable.parseToFlyingRidable(flyingEntity);
			if (flying != null) {
				flying.parseArguments(flyingArgs);
			}
			
		// plain
		case LEASH_HITCH:
		case ARROW:
		case SNOWBALL:
		case FIREBALL:
		case SMALL_FIREBALL:
		case ENDER_PEARL:
		case ENDER_SIGNAL: //<<< what?
		case ITEM_FRAME:
		case WITHER_SKULL:
		case PRIMED_TNT:
		case BOAT:
		case MINECART:
		case MINECART_TNT:
		case MINECART_HOPPER:
		case MINECART_CHEST:
		case MINECART_FURNACE:
		case ENDER_DRAGON:
		case WITHER:
		case SNOWMAN:
		case ENDER_CRYSTAL:
		case FISHING_HOOK:
		case LIGHTNING:
		
			
		// expandable
		case EXPERIENCE_ORB:
		case PAINTING:
		case THROWN_EXP_BOTTLE:
		case FALLING_BLOCK:
		case FIREWORK:
		case MINECART_MOB_SPAWNER:
		case SPLASH_POTION:
		case EGG:
		
			// invalid
		default:
			return;
		
		}
	}
}
