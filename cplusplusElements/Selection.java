package cplusplusElements;

import java.util.ArrayList;

class Rectangle{
	int x0, y0, width, height;

	public Rectangle(int x0, int y0, int width, int height) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "\t\t\t<dimensions>" + x0 + "," + y0 + "," + width + "," + height + "</dimensions>\n";
	}

	public int getX0() {
		return x0;
	}

	public int getY0() {
		return y0;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}

class Elem1{
	String name;
	boolean active;
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	public Elem1(String name, boolean active) {
		this.name = name;
		this.active = active;
	}
	
	void addRectangle(int x0, int y0, int width, int height) {
		rectangles.add(new Rectangle(x0, y0, width, height));
	}
	
	@Override
	public String toString() {
		int tmp1 = 0;
		if(active) 
			tmp1 = 1;
		else
			tmp1 = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("\t<selection>\n");
		sb.append("\t\t<name>"); sb.append(name); sb.append("</name>\n");
		sb.append("\t\t<active>");sb.append(tmp1);sb.append("</active>\n");
			for(Rectangle tmp: rectangles) {
				sb.append("\t\t<rectangle>\n");
				sb.append(tmp);
				sb.append("\t\t</rectangle>\n");
			}
		sb.append("\t</selection>\n");
		return sb.toString();
	}
	
}

public class Selection {
	private Elem1 selection;
	
	public int getRectagnles() {
		return selection.rectangles.size();
	}
	
	public Selection(String name, boolean active) {
		selection = new Elem1(name, active);
	}
	
	public void setActive(boolean active) {
		selection.active = active;
	}
	
	public void addrectInSelection(int x0, int y0, int width, int height) {
		selection.addRectangle(x0, y0, width, height);
	}
	
	public void removerectInSelection(int index) {
		selection.rectangles.remove(index);
	}
	
	public void deleteSelection() {
		selection = null;
	}
	
	@Override
	public String toString() {
		return selection.toString();
	}
	
	public static void main(String [] argv) {
		Selection select = new Selection("tozica", true);
		select.addrectInSelection(0, 0, 500, 600);
		select.addrectInSelection(100, 200, 300, 350);
		System.out.println(select);
		
		
	}

	public int[] getRectangle(int i) {
		int x0 = selection.rectangles.get(i).x0;
		int y0 = selection.rectangles.get(i).y0;
		int width = selection.rectangles.get(i).width;
		int height = selection.rectangles.get(i).height;
		return new int[] {x0, y0, width, height};
	}
	
}
