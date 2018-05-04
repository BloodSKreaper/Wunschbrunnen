package me.chrisochs.wunschbrunnen.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;

import me.chrisochs.wunschbrunnen.Main;

public class ItemMergeEventListener implements Listener {

	public ItemMergeEventListener() {

	}

	@EventHandler
	public void onMerge(ItemMergeEvent e) {
		if (e.getEntity().getItemStack().getType() == Material.GOLD_NUGGET && Main.dm.containsItem(e.getEntity())
				|| e.getEntity().getItemStack().getType() == Material.GOLD_NUGGET
						&& Main.dm.containsItem(e.getTarget())) {
			e.setCancelled(true);
		}
	}

}
