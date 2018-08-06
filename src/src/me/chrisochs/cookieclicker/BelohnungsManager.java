package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BelohnungsManager {
	private List<Belohnung> belohnungen = new ArrayList<Belohnung>();

	public BelohnungsManager() {
		Material[] bel = Material.values();
		for (int i = 0; i < bel.length; i++) {
			if (bel[i].isItem() == false || (bel[i] == Material.ENCHANTED_BOOK || bel[i] == Material.DRAGON_EGG
					|| bel[i] == Material.MOB_SPAWNER || bel[i] == Material.MONSTER_EGG
					|| bel[i] == Material.MONSTER_EGGS || bel[i] == Material.POTION
					|| bel[i] == Material.LINGERING_POTION || bel[i] == Material.SPLASH_POTION
					|| bel[i] == Material.TIPPED_ARROW)

			) {

			} else {
				addBelohnung(new ItemStack(bel[i], 1));

			}
		}
		for (Enchantment e : Enchantment.values()) {
			for (int i = e.getStartLevel(); i <= e.getMaxLevel(); i++) {
				ItemStack is = new ItemStack(Material.ENCHANTED_BOOK, 1);
				ItemMeta im = is.getItemMeta();
				im.addEnchant(e, i, true);
				is.setItemMeta(im);
				addBelohnung(is);
			}
		}
	}

	public void addBelohnung(ItemStack istack) {
		Belohnung b = new Belohnung();
		b.addBelohnung(istack);
		belohnungen.add(b);
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
		if (Math.random() < 0.1) {
			Belohnung b = getRandomBelohnung();
			if (b != null && b.getBelohnungen().size() > 0) {
				p.sendMessage("§6Der Brunnen hat deine Wünsche erhört!");
				for (ItemStack is : b.getBelohnungen()) {
					p.getWorld().dropItem(p.getLocation().add(0, 1, 0), is);

				}
			}
		}
	}

}
