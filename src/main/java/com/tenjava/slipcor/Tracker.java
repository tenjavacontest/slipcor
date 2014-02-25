package com.tenjava.slipcor;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <pre>Tracker class</pre>
 * 
 * phones home to www.slipcor.net, saving server IP and plugin version
 * 
 * @author slipcor
 */

public class Tracker implements Runnable {
	private static int taskID = -1;
	private final JavaPlugin plugin;
	
	public Tracker(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * call home to save the server/plugin state
	 */
	private void callHome() {
		if (!plugin.getConfig().getBoolean("tracker", true)) {
			stop();
			return;
		}
		plugin.getLogger().info("calling home...");

		String url = null;
		try {
			url = String
					.format("http://www.slipcor.net/stats/call.php?port=%s&name=%s&version=%s",
							plugin.getServer().getPort(),
							URLEncoder.encode(plugin.getDescription().getName(), "UTF-8"),
							URLEncoder.encode(plugin.getDescription().getVersion(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			new URL(url).openConnection().getInputStream();
		} catch (Exception e) {
			plugin.getLogger().warning("Error while connecting to www.slipcor.net");
			return;
		}
		plugin.getLogger().info("successfully called home!");
	}

	@Override
	public void run() {
		callHome();
	}

	/**
	 * start tracking
	 */
	public void start() {
		plugin.getLogger().info("Plugin tracking enabled. Set tracker: false inside the main config to disable.");
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this,
				0L, 72000L);
	}

	/**
	 * stop tracking
	 */
	public void stop() {
		plugin.getLogger().info("Plugin tracking disabled. See you soon?");
		Bukkit.getScheduler().cancelTask(taskID);
	}
}
