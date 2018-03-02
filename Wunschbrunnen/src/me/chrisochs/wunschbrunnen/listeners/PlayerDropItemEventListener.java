package me.chrisochs.wunschbrunnen.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.chrisochs.wunschbrunnen.Main;

public class PlayerDropItemEventListener implements Listener{
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		if(e.getItemDrop().getItemStack().getType() == Material.GOLD_NUGGET&&e.getItemDrop().getItemStack().getAmount() == 1) {
			Main.dm.addItem(e.getPlayer(), e.getItemDrop());
		}
	}

}
