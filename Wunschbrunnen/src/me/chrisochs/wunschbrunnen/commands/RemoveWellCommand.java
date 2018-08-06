package me.chrisochs.wunschbrunnen.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.chrisochs.wunschbrunnen.Main;

public class RemoveWellCommand implements CommandExecutor {
	public RemoveWellCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			String name = args[0];
			if (Main.bm.containsBrunnen(name)) {
				Main.bm.removeBrunnen(name);
				sender.sendMessage("§aDer Brunnen §6" + name + " §awurde erfolgreich entfernt!");
			} else {
				sender.sendMessage("§cDer Brunnen §6" + name + " §cexistiert nicht!");
				Main.bm.printAllWells(sender);
			}
		} else {
			sender.sendMessage("§cWrong usage: §6/removeWell <NAME>");
			Main.bm.printAllWells(sender);
		}

		return true;
	}

}
