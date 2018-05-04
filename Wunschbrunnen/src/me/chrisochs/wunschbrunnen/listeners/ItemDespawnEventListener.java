package me.chrisochs.wunschbrunnen.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

import me.chrisochs.wunschbrunnen.Main;

public class ItemDespawnEventListener implements Listener {

	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e) {
		if (Main.dm.containsItem(e.getEntity())) {
			e.setCancelled(true);
		}
	}

}
