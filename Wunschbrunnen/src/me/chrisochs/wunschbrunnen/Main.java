package me.chrisochs.wunschbrunnen;

import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.chrisochs.wunschbrunnen.commands.RemoveWellCommand;
import me.chrisochs.wunschbrunnen.commands.SetWellCommand;
import me.chrisochs.wunschbrunnen.listeners.ItemDespawnEventListener;
import me.chrisochs.wunschbrunnen.listeners.ItemMergeEventListener;
import me.chrisochs.wunschbrunnen.listeners.PlayerDropItemEventListener;
import me.chrisochs.wunschbrunnen.listeners.PlayerPickupItemEventListener;

public class Main extends JavaPlugin {
	public static BrunnenManager bm = new BrunnenManager();
	public static DropsManager dm = new DropsManager();
	private BelohnungsManager bema = new BelohnungsManager();
	private ConfigManager cm = new ConfigManager(this);

	/*
	 * Diese Methode wird beim Laden des Plugins aufgerufen Es werden die Listener
	 * sowie die CommandExecutor registriert. Auﬂerdem wird ein Aufruf einer Methode
	 * gemacht, die alle gespeicherten Wunschbrunnenobjekte in den Arbeitsspeicher
	 * l‰dt.
	 */
	public void onEnable() {
		registerListeners();
		registerCommands();
		startScheduler();
		cm.loadWellsFromConfig();
	}
	
	public void onDisable() {
		cm.writeWellsToConfig();
	}

	public void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new ItemDespawnEventListener(), this);
		manager.registerEvents(new ItemMergeEventListener(), this);
		manager.registerEvents(new PlayerDropItemEventListener(), this);
		manager.registerEvents(new PlayerPickupItemEventListener(), this);

	}

	public void registerCommands() {
		getCommand("setWell").setExecutor(new SetWellCommand());
		getCommand("removeWell").setExecutor(new RemoveWellCommand());
	}

	public void startScheduler() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int i = 0;
			@Override
			public void run() {
				if(i==5) {
					for (int j = 0; j < bm.getBrunnen().size(); j++) {
						bm.getBrunnen().get(j).showParticles();
					}
					i=0;
				}
				

				List<Drop> drops = dm.getDrops();
				for (int i = 0; i < drops.size(); i++) {

					Drop drop = drops.get(i);
					Item item = drop.getItem();
					for (int j = 0; j < bm.getBrunnen().size(); j++) {

						Wunschbrunnen brunnen = bm.getBrunnen().get(j);
						if (brunnen.containsBlock(item.getLocation())) {
							drop.getItem().remove();
							bema.givePlayerBelohnung(drop.getPlayer());
							dm.removeDrop(drop);
						}
					}
				}
				i++;
			}
		}, 20L, 1L);
	}
}
