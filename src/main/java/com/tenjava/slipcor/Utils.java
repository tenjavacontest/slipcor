package com.tenjava.slipcor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tenjava.slipcor.impl.FlyingExpandable;
import com.tenjava.slipcor.impl.FlyingRidable;

public final class Utils {
	
	private static BowsPlus plugin = null;
	
	private static List<String> blacklist = new ArrayList<String>();
	private static List<String> whitelist = new ArrayList<String>();

	private Utils() {
	}

	public static boolean hasMats(Player player, Material material, String[] flyingArgs) {
		if (!plugin.getConfig().getBoolean("uses.cost")) {
			return true;
		}
		
		ItemStack removal = new ItemStack(material);
		
		for (String value : flyingArgs) {
			if (value.startsWith("amount:")) {
				String[] split = value.split("amount:");
				int amount = Integer.parseInt(split[1]);
				
				removal.setAmount(amount);
			} else if (value.startsWith("data:")) {
				String[] split = value.split("data:");
				byte data = Byte.parseByte(split[1]);
				removal.getData().setData(data);
			}
		}
		
		if (player.getInventory().containsAtLeast(removal, 1))) {
			player.getInventory().remove(removal);
			player.updateInventory();
			return true;
		}
		return false;
	}
	
	public static boolean hasPerms(Player player, EntityType eType, Material material) {
		
		if (eType == null && material == null) {
			return false;
		}
		
		if (eType == null) {
			if (blocked(material.name())) {
				return false;
			}
			return player.hasPermission("bowsplus."+material.name().toLowerCase());
		}
		
		switch (eType) {
		
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
		case WITCH:
			if (blocked("MOBS")) {
				return false;
			}
			break;
			
		
		case PIG:
		case SHEEP:
		case COW:
		case CHICKEN:
		case SQUID:
		case WOLF:
		case MUSHROOM_COW:
		case OCELOT:
		case HORSE:
		case VILLAGER:
		case BAT:
			if (blocked("PASSIVE")) {
				return false;
			}
			break;
			
		case ENDER_DRAGON:
		case WITHER:
			if (blocked("BOSS")) {
				return false;
			}
			break;
			
		case SNOWMAN:
		case IRON_GOLEM:
			if (blocked("DEFENSE")) {
				return false;
			}
			break;

		}
		
		if (blocked(eType.name())) {
			return false;
		}
		
		return player.hasPermission("bowsplus."+eType.name().toLowerCase());
	}

	private static boolean blocked(String string) {
		if (plugin.getConfig().getBoolean("uses.blacklist")) {
			if (blacklist.contains(string)) {
				return true;
			}
		}
		if (plugin.getConfig().getBoolean("uses.whitelist")) {
			if (!whitelist.contains(string)) {
				return true;
			}
		}
		return false;
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
			FlyingRidable flying = FlyingRidable.parseToFlyingRidable(flyingEntity);
			if (flying != null) {
				flying.parseArguments(flyingArgs);
			}
			break;

		// expandable
		case EXPERIENCE_ORB:
		case FALLING_BLOCK:
		case FIREWORK:
		case MINECART_MOB_SPAWNER:
		case PRIMED_TNT:
			
			FlyingExpandable expandable = FlyingExpandable.parseToFlyingRidable(flyingEntity);
			if (expandable != null) {
				expandable.parseArguments(flyingArgs);
			}
			break;
		// plain
		case LEASH_HITCH:
		case ARROW:
		case SNOWBALL:
		case SMALL_FIREBALL:
		case FIREBALL:
		case ENDER_PEARL:
		case ENDER_SIGNAL: //<<< what?
		case WITHER_SKULL:
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
		case THROWN_EXP_BOTTLE:
		case EGG:
		
			
			
			
		// invalid
		default:
			return;
		
		}
	}

	public static String[] lowerCase(String[] args) {
		String[] result = new String[args.length];
		result[0] = args[0];
		for (int i=1;i<args.length;i++) {
			result[i] = args[i].toLowerCase();
		}
		return result;
	}
}
