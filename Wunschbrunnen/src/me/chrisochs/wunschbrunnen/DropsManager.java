package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class DropsManager {
	private List<Drop> drops = new ArrayList<Drop>();

	public DropsManager() {

	}

	public void addItem(Player player, Item item) {
		drops.add(new Drop(player, item));
	}

	// Entfernt ein Item bzw dessen Drop aus der Liste
	public void removeItem(Item item) {
		if (containsItem(item))
			drops.remove(getDropFromItem(item));
	}

	// Entfernt ein Item bzw dessen Drop aus der Liste
	public void removeDrop(Drop drop) {
		drops.remove(drop);
	}

	public boolean containsItem(Item item) {
		boolean result = false;
		if (drops.size() > 0) {
			for (Drop d : drops) {
				if (d.getItem().equals(item)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public Drop getDropFromItem(Item item) {
		Drop result = null;
		for (Drop d : drops) {
			if (d.getItem().equals(item)) {
				result = d;
				break;
			}
		}
		return result;
	}

	public List<Drop> getDrops() {
		return drops;
	}

}
