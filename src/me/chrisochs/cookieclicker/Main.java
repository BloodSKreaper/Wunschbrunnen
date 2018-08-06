package me.chrisochs.cookieclicker;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.chrisochs.cookieclicker.listeners.PlayerInteractEventListener;
import me.chrisochs.cookieclicker.listeners.PlayerJoinListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	private playerdata playerdata = new playerdata();
	public static Economy econ = null;

	public void onEnable() {
		if (setupEconomy() == false) {
			this.setEnabled(false);
		}
		registerListeners();
	}

	public void onDisable() {
	}

	public void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new PlayerInteractEventListener(this), this);
		manager.registerEvents(new PlayerJoinListener(this, playerdata), this);

	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public void handleClick(Player player, Location loc) {
		addPoint(player);
		Utils.sendActionBar(player, "§6" + getPoints(player) + " §eCookies");
		if (getPoints(player) % 100 == 0) {
			int money = getPoints(player) / 100;
			econ.depositPlayer(player, money);
			player.sendMessage("§8[§6Cookie§8] §eDu hast §6" + money + " §eCubis erhalten");
		}
		if(getPoints(player) % 250 == 0) {
			dropCookie(player.getLocation());
		}
		if (getPoints(player) % 1000 == 0) {
			getServer().broadcastMessage("§8[§6Cookie§8] §6" + player.getName() + " §ehat §6" + getPoints(player)
					+ "x §eden Cookie geschlagen");

			spawnFirework(loc);

		}

	}

	public void spawnFirework(Location loc) {

		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		fwm.setPower(0);
		fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).withColor(Color.YELLOW).withFade(Color.ORANGE)
				.withFlicker().withTrail().build());

		fw.setFireworkMeta(fwm);
		fw.detonate();

		for (int i = 0; i < 3; i++) {
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

				@Override
				public void run() {
					Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
					fw2.setFireworkMeta(fwm);
				}

			}, 5 * i);
		}
	}
	
	public void dropCookie(Location loc) {
		ItemStack is = new ItemStack(Material.COOKIE, 1);
		loc.getWorld().dropItem(loc.add(0, 1, 0), is);
	}

	public void addPoint(Player player) {
		int points = playerdata.getInt(player.getUniqueId(), "amount") + 1;
		playerdata.set(player.getUniqueId(), "amount", points);
	}

	public int getPoints(Player player) {
		return playerdata.getInt(player.getUniqueId(), "amount");
	}
}
