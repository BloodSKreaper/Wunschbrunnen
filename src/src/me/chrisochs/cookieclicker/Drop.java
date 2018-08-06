package me.chrisochs.wunschbrunnen;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class Drop {
	private Player player;
	private Item item;
	
	public Drop(Player p, Item i) {
		player = p;
		item = i;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Item getItem() {
		return item;
	}

}
