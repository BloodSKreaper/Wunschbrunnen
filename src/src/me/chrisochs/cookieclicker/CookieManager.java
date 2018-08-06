package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public class BrunnenManager {
	private List<Wunschbrunnen> brunnen = new ArrayList<Wunschbrunnen>();

	public BrunnenManager() {

	}

	public void addBrunnen(Wunschbrunnen w) {
		brunnen.add(w);
	}

	public void removeBrunnen(Wunschbrunnen w) {
		if (brunnen.contains(w))
			brunnen.remove(w);
	}

	public List<Wunschbrunnen> getBrunnen() {
		return brunnen;
	}

	public boolean containsBrunnen(Wunschbrunnen w) {
		if (brunnen.contains(w)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean containsBrunnen(String name) {
		for (Wunschbrunnen b : brunnen) {
			if (b.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void printAllWells(CommandSender p) {
		for (Wunschbrunnen b : brunnen) {
			p.sendMessage("§6" + b.getName() + " §f(§b" + b.getX() + "§f, §b" + b.getY() + "§f, §b" + b.getZ()
					+ "§f, §b" + b.getRadius() + "§f)");
		}
	}

	public void removeBrunnen(String name) {
		for (int i = 0; i < brunnen.size(); i++) {
			if (brunnen.get(i).getName().equalsIgnoreCase(name)) {
				brunnen.remove(brunnen.get(i));
				break;
			}
		}
	}

}
