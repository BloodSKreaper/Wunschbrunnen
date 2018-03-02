package me.chrisochs.wunschbrunnen;

import java.util.ArrayList;
import java.util.List;

public class BrunnenManager {
	private List<Wunschbrunnen> brunnen = new ArrayList<Wunschbrunnen>();
	
	public BrunnenManager() {
		
	}
	
	public void addBrunnen(Wunschbrunnen w) {
		brunnen.add(w);
	}
	
	public void removeBrunnen(Wunschbrunnen w) {
		if(brunnen.contains(w))brunnen.remove(w);
	}
	
	public List<Wunschbrunnen> getBrunnen(){
		return brunnen;
	}
	
	public boolean containsBrunnen(Wunschbrunnen w) {
		if (brunnen.contains(w)) {
			return true;
		}else {
			return false;
		}
	}
}
