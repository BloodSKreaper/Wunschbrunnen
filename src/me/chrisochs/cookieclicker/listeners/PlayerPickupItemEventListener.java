package me.chrisochs.wunschbrunnen.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.chrisochs.wunschbrunnen.Main;

public class PlayerPickupItemEventListener implements Listener{

	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		if(Main.dm.containsItem(e.getItem())) {
			Main.dm.removeItem(e.getItem());
		}
	}
}
