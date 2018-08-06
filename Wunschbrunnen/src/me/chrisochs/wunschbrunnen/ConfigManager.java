package me.chrisochs.wunschbrunnen;

import org.bukkit.World;

public class ConfigManager {
	private Main main;

	public ConfigManager(Main m) {
		main = m;
	}

	public void writeWellsToConfig() {
		clearWellsFromConfig();
		main.getConfig().set("brunnen", null);
		for (Wunschbrunnen b : Main.bm.getBrunnen()) {
			String base = "brunnen." + b.getName();
			main.getConfig().set(base + ".world", b.getWorld().getName());
			main.getConfig().set(base + ".x", b.getX());
			main.getConfig().set(base + ".y", b.getY());
			main.getConfig().set(base + ".z", b.getZ());
			main.getConfig().set(base + ".r", b.getRadius());
		}
		main.saveConfig();
	}

	public void loadWellsFromConfig() {
		if (main.getConfig().getConfigurationSection("brunnen") == null
				|| main.getConfig().getConfigurationSection("brunnen").getKeys(false).size() == 0)
			return;
		for (String s : (main.getConfig().getConfigurationSection("brunnen").getKeys(false))) {
			String base = "brunnen." + s + ".";
			if (main.getServer().getWorld(main.getConfig().getString(base + "world")) != null) {
				String name = s;
				World world = main.getServer().getWorld(main.getConfig().getString(base + "world"));
				int x = main.getConfig().getInt(base + "x");
				int y = main.getConfig().getInt(base + "y");
				int z = main.getConfig().getInt(base + "z");
				int r = main.getConfig().getInt(base + "r");
				Wunschbrunnen b = new Wunschbrunnen(name, world, x, y, z, r);
				Main.bm.addBrunnen(b);
			}
		}

	}

	public void clearWellsFromConfig() {
		for (String s : main.getConfig().getKeys(false)) {
			main.getConfig().set("brunnen" + s, null);
		}
	}

}
