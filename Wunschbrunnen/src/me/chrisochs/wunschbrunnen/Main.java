package me.chrisochs.wunschbrunnen;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin{
	public static BrunnenManager bm = new BrunnenManager();
	public static DropsManager dm = new DropsManager();
	
	public static void main(String args[]) {
		Wunschbrunnen w = new Wunschbrunnen(20,64,-20,1);
		for(Vector v : w.getBlocksOfWell()) {
			System.out.println("x:"+v.getBlockX()+" y:"+v.getBlockY()+" z:"+v.getBlockZ());	
		}
		if(w.containsBlock(20, 64, -20))System.out.println("CONTAINS IT!");
		
	}

}
