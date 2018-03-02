package me.chrisochs.wunschbrunnen.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.chrisochs.wunschbrunnen.Main;
import me.chrisochs.wunschbrunnen.Wunschbrunnen;

public class SetWellCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length <1 || args.length >1) {
				p.sendMessage("§cFalsches Format! Bitte benutze §b/setwell <radius>");
			}else {
				if(!isInteger(args[0])) {
					p.sendMessage("§cFehlerhafte Radius-Eingabe! Es sind nur Ganzzahlen erlaubt!");
				}else {
					int x,y,z,radius;
					x=p.getLocation().getBlockX();
					y=p.getLocation().getBlockY();
					z=p.getLocation().getBlockZ();
					radius = Integer.parseInt(args[0]);
					Main.bm.addBrunnen(new Wunschbrunnen(x,y,z,radius));
				}
			}
		}
		return true;
	}
	
	   public static boolean isInteger(String s) {
		      boolean isValidInteger = false;
		      try
		      {
		         Integer.parseInt(s);
		 
		         // s is a valid integer
		 
		         isValidInteger = true;
		      }
		      catch (NumberFormatException ex)
		      {
		         // s is not an integer
		      }
		 
		      return isValidInteger;
		   }

}
