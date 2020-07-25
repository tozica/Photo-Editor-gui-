package javaPart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StateCheck extends Thread{
	
	private GUIMAIN guiman;
	
	public StateCheck(GUIMAIN guiman) {
		this.guiman = guiman;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			if(GUIMAIN.toDo) {
				guiman.callProject();
				guiman.callLayerInfo();
				guiman.callProcessAllLayers();
			}
		}
		
	}
}
