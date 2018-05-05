package me.chrisochs.wunschbrunnen.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.chrisochs.wunschbrunnen.Main;
import me.chrisochs.wunschbrunnen.Wunschbrunnen;

public class SetWellCommand implements CommandExecutor {

	/*
	 * Handelt den Plugin-Befehl "/setwell" ab. Dieser Befehl wird wie folgt
	 * aufgebaut: "/setwell <Name> [Radius]" Gibt der Spieler nur einen Namen und
	 * keinen Radius ein, so wird der Radius standardmäßig auf 1 gesetzt.
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			// Wenn es ein Argument nach dem Befehl gibt (Name)
			int x, y, z, radius;
			String name;
			if (args.length >= 1 && args.length < 3) {
				World world = p.getLocation().getWorld();
				x = p.getLocation().getBlockX();
				y = p.getLocation().getBlockY();
				z = p.getLocation().getBlockZ();
				name = args[0];
				if (args.length == 2) {
					if (!isInteger(args[1])) {
						p.sendMessage("§cFehlerhafte Radius-Eingabe! Es sind nur Ganzzahlen erlaubt!");
						return false;
					} else {
						radius = Integer.parseInt(args[1]);
					}
				} else {
					radius = 1;
				}
				if(Main.bm.containsBrunnen(name)) {
					p.sendMessage("§cEin Brunnen mit dem Namen "+name+" existiert bereits! Suche dir einen andere Namen aus oder entferne den Brunnen mit /removeWell <NAME>");
					p.sendMessage("§aAlle Brunnen:");
					Main.bm.printAllWells(p);
				}else {
					p.sendMessage("§aDer Brunnen §6"+name+" §awurde erfolgreich erstellt. (X="+x+";Y="+y+";Z="+z+";r="+radius+")");
				Main.bm.addBrunnen(new Wunschbrunnen(name, world, x, y, z, radius));
				}
			} else {

				p.sendMessage("§cFalsches Format! Bitte benutze §b/setwell <name> [radius]");
			}

		}
		return true;
	}

	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer

			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}

}
