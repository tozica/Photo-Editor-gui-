package cplusplusElements;

import java.util.ArrayList;

class Elem{
	String path;
	double opacity;
	boolean visible;
	boolean active;
	
	Elem(String p, double o, boolean v, boolean a){
		path = p;
		opacity = o;
		visible = v;
		active = a;
	}
	
	
	public String getPath() {
		return path;
	}


	public double getOpacity() {
		return opacity;
	}


	public boolean isVisible() {
		return visible;
	}


	public boolean isActive() {
		return active;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int visible1 = visible == true ? 1 : 0;
		int active1 = active == true ? 1 : 0;
		sb.append("\t\t<layer>\n");
			sb.append("\t\t\t<path>"); sb.append(path); sb.append("</path>\n");
			sb.append("\t\t\t<opacity>"); sb.append(opacity); sb.append("</opacity>\n");
			sb.append("\t\t\t<visible>"); sb.append(visible1); sb.append("</visible>\n");
			sb.append("\t\t\t<active>"); sb.append(active1); sb.append("</active>\n");
		sb.append("\t\t</layer>\n");
		
		
		return sb.toString();
	}


	
	
}

public class Layers {
	private ArrayList<Elem> elem = new ArrayList<Elem>();
	
	public void addLayer(String p, double o, boolean v, boolean a) {
		elem.add(new Elem(p, o, v, a));
	}
	
	public void removeLayer(int i) {
		elem.remove(i);
	}
	
	public void setActivity(int i, boolean active) {
		elem.get(i).setActive(active);
	}
	
	public void setVisibility(int i, boolean visible) {
		elem.get(i).setVisible(visible);
	}
	
	public void setOpacity(int i, double opacity) {
		elem.get(i).setOpacity(opacity);
	}
	
	public void setPath(int i, String p) {
		elem.get(i).setPath(p);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<layers>\n");
			for(Elem tmp: elem)
				sb.append(tmp.toString());
		sb.append("\t</layers>\n");
		return sb.toString();
		
	}
	
	public boolean getActivity(int i) {
		return elem.get(i).isActive();
	}
	
	public boolean getVisibility(int i) {
		return elem.get(i).isVisible();
	}
	
	public double getOpacity(int i) {
		return elem.get(i).getOpacity();
	}
	
	
	public ArrayList<Elem> getElem() {
		return elem;
	}

	public void setElem(ArrayList<Elem> elem) {
		this.elem = elem;
	}

	public static void main(String argv[]) {
		Layers l = new Layers();
		l.addLayer("lllllll", 0.7, true, true);
		System.out.println(l);
	}
	
}
