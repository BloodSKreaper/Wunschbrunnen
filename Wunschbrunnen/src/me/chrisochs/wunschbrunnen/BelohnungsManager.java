package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BelohnungsManager {
	private List<Belohnung> belohnungen = new ArrayList<Belohnung>();

	public BelohnungsManager() {
		Belohnung b1 = new Belohnung();
		b1.addBelohnung(new ItemStack(Material.DIAMOND, 1));
		belohnungen.add(b1);

		Belohnung b2 = new Belohnung();
		b2.addBelohnung(new ItemStack(Material.EMERALD, 1));
		b2.addBelohnung(new ItemStack(Material.LEVER, 1));
		belohnungen.add(b2);
		Belohnung b3 = new Belohnung();
		b2.addBelohnung(new ItemStack(Material.AIR, 1));
		b2.addBelohnung(new ItemStack(Material.TORCH, 1));
		belohnungen.add(b3);
	}

	public Belohnung getRandomBelohnung() {
		Belohnung result = null;
		double random, divider;
		random = Math.random() * 100;
		divider = 100 / (double) belohnungen.size();
		int index = 0;
		for (double i = divider; i <= 100; i = i + divider) {
			if (random >= i - divider && random < i) {
				result = belohnungen.get(index);
				break;
			} else {
				index++;
			}
		}
		return result;
	}

	public void givePlayerBelohnung(Player p) {
		Belohnung b = getRandomBelohnung();
		if (b.getBelohnungen().size() > 0) {
			p.sendMessage("§6Der Brunnen hat deine Wünsche erhört!");
			for (ItemStack is : b.getBelohnungen()) {
				HashMap<Integer, ItemStack> lost = p.getInventory().addItem(is);
				if (lost.size() > 0) {
					p.getWorld().dropItem(p.getLocation(), lost.get(0));
				}

			}
		} else {
			p.sendMessage("§cHier gibts keine Eier!");

		}
	}

}
