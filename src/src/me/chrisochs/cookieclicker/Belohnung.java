package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Belohnung {
	private List<ItemStack> belohnungen = new ArrayList<ItemStack>();

	public Belohnung() {
	}

	public void addBelohnung(ItemStack itemstack) {
		belohnungen.add(itemstack);
	}

	public void removeBelohnung(ItemStack itemstack) {
		belohnungen.remove(itemstack);
	}

	public List<ItemStack> getBelohnungen() {
		return belohnungen;
	}

}
