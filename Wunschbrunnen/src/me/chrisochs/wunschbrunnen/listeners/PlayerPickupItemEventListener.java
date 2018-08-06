package me.chrisochs.wunschbrunnen.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import me.chrisochs.wunschbrunnen.Main;

public class PlayerPickupItemEventListener implements Listener {

	@EventHandler
	public void onPickUp(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			if (Main.dm.containsItem(e.getItem())) {
				Main.dm.removeItem(e.getItem());
			}
		}
	}
}
