package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class Wunschbrunnen {
	private World world;
	private int x, y, z, radius;
	String name;

	public Wunschbrunnen(String name, World world, int X, int Y, int Z, int Radius) {
		this.world = world;
		x = X;
		y = Y;
		z = Z;
		radius = Radius;
		this.name = name;
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

	public World getWorld() {
		return world;
	}

	public double getRadius() {
		return radius;
	}

	public String getName() {
		return name;
	}

	public List<Vector> getBlocks() {
		List<Vector> locations = new ArrayList<Vector>();
		for (int i = x - radius; i <= x + radius; i++) {
			for (int g = z - radius; g <= z + radius; g++) {
				if (getWorld().getBlockAt(i, y, g).getType().isSolid() == false) {
					Vector ve = new Vector(i, y, g);
					locations.add(ve);
				}
			}
		}
		return locations;

	}

	public boolean containsBlock(Location loc) {
		if (!loc.getWorld().equals(world)) {
			return false;
		} else {
			boolean result = false;
			for (Vector v : getBlocks()) {
				if (v.getBlockX() == loc.getBlockX() && v.getBlockY() == loc.getBlockY()
						&& v.getBlockZ() == loc.getBlockZ()) {
					result = true;
					break;
				}
			}
			return result;
		}
	}

	public void showParticles() {
		Vector result = null;
		List<Vector> blocks = getBlocks();
		if (blocks.size() > 0) {
			double random, divider;
			for (int k = 0; k < 3; k++) {
				random = Math.random() * 100;
				divider = 100 / (double) blocks.size();
				int index = 0;
				for (double i = divider; i <= 100; i = i + divider) {
					if (random >= i - divider && random < i) {
						result = blocks.get(index);
						break;
					} else {
						index++;
					}
				}
				if (result != null
						&& world.getChunkAt(new Location(getWorld(), result.getX(), result.getY(), result.getZ()))
								.isLoaded() == true)
					getWorld().spawnParticle(Particle.SPELL_INSTANT, result.getX(), result.getY() + 1, result.getZ(), 1,
							0.5, 0, 0.5, 1);
			}
		}
	}

}
