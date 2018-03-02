package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class Wunschbrunnen {
	private int x,y,z,radius;
	
	public Wunschbrunnen(int X, int Y, int Z, int Radius) {
		x=X;
		y=Y;
		z=Z;
		radius=Radius;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public List<Vector> getBlocks(){
		List<Vector> locations = new ArrayList<Vector>();
		for(int i = x-radius; i<=x+radius;i++) {
			for(int g = z-radius; g<=z+radius;g++) {
				Vector ve = new Vector(i,y,g);
				locations.add(ve);				
			}
		}
		return locations;
		
	}
	
	public boolean containsBlock(int x, int y, int z) {
		boolean result = false; 
		for(Vector v:getBlocks()) {
			if(v.getBlockX() == x && v.getBlockY()==y&&v.getBlockZ() ==z) {
				result = true;
				break;
			}
		}
		return result;
	}
	

}
