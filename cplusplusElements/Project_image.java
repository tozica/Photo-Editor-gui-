package cplusplusElements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Project_image {
	private Composite composite;
	private static final String path = "tmpProject";
	private Layers layers = new Layers();
	private Selection selection = null;
	
	public Project_image(){
		File tmpDir = new File(path);
		if(!tmpDir.exists()) {
			tmpDir.mkdir();
		}
		else {
			tmpDir.delete();
			if(!tmpDir.exists())
				tmpDir.mkdir();
		}
		//composite = new Composite(path + "\\composite.fun");
	}
	
	public void addLayer(String file) {
		layers.addLayer(file, 0.5, true, true);
	}
	
	public void createSelection(String name) {
		selection = new Selection(name, true);
	}
	
	public void addRectangleinSelection(int x0, int y0, int width, int height) {
		selection.addrectInSelection(x0, y0, width, height);
	}
	
	public void addLayer(String p, double o, boolean v, boolean a) {
		layers.addLayer(p, o, v, a);
	}
	
	public void removeLayer(int i) {
		layers.removeLayer(i);
	}
	
	public void setActivity(int i, boolean active) {
		layers.setActivity(i, active);
	}
	
	public void setVisibility(int i, boolean visible) {
		layers.setVisibility(i, visible);
	}
	
	public void setOpacity(int i, double opacity) {
		layers.setOpacity(i, opacity);
	}
	
	public void writeMyFormat() {
		StringBuilder sb = new StringBuilder();
		sb.append("<image>\n");
		if(layers != null)
			sb.append(layers.toString());
		if(selection != null)
			sb.append(selection.toString());
		if(composite != null)
			sb.append(composite.toString());
		sb.append("</image>");
		
		File myFormat = new File(path + "\\myFormat.toza");
		
		if(!myFormat.exists()) {
			try {
				FileWriter writer = new FileWriter(myFormat);
				writer.write(sb.toString());
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed writting in class Project_image in method writeMyFormat");
			}
		}
		else {
			myFormat.delete();
			if(!myFormat.exists()) {
				try {
					FileWriter writer = new FileWriter(myFormat);
					writer.write(sb.toString());
					writer.close();
				} catch (IOException e) {
					System.out.println("Failed writting in class Project_image in method writeMyFormat");
				}
			}
		}
	}
	
	public void writePreOperate() {
		StringBuilder sb = new StringBuilder();
		sb.append("<image>\n");
		if(layers != null)
			sb.append(layers.toString());
		if(selection != null)
			sb.append(selection.toString());
		if(composite != null)
			sb.append(composite.toString());
		sb.append("</image>");
		
		File myFormat = new File("preOperation.toza");
		
		if(!myFormat.exists()) {
			try {
				FileWriter writer = new FileWriter(myFormat);
				writer.write(sb.toString());
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed writting in class Project_image in method writeMyFormat");
			}
		}
		else {
			myFormat.delete();
			if(!myFormat.exists()) {
				try {
					FileWriter writer = new FileWriter(myFormat);
					writer.write(sb.toString());
					writer.close();
				} catch (IOException e) {
					System.out.println("Failed writting in class Project_image in method writeMyFormat");
				}
			}
		}
	}
	
	public void writeOnDemand(String path) {
		StringBuilder sb = new StringBuilder();
		sb.append("<image>\n");
		if(layers != null)
			sb.append(layers.toString());
		if(selection != null)
			sb.append(selection.toString());
		if(composite != null)
			sb.append(composite.toString());
		sb.append("</image>");
		
		File myFormat = new File(path);
		
		if(!myFormat.exists()) {
			try {
				FileWriter writer = new FileWriter(myFormat);
				writer.write(sb.toString());
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed writting in class Project_image in method writeMyFormat");
			}
		}
		else {
			myFormat.delete();
			if(!myFormat.exists()) {
				try {
					FileWriter writer = new FileWriter(myFormat);
					writer.write(sb.toString());
					writer.close();
				} catch (IOException e) {
					System.out.println("Failed writting in class Project_image in method writeMyFormat");
				}
			}
		}
	}
	
	public void proccesInCplusplus() {
		File photo = new File("tmpProject\\photo.bmp");
		File exef = new File("testiranjeZaSutrasnjiLab\\Debug\\testiranjeZaSutrasnjiLab.exe");
		File mFormat = new File("tmpProject\\myFormat.toza");
		String absPhoto = photo.getAbsolutePath();
		String absmFormat = mFormat.getAbsolutePath();
		String absExe = exef.getAbsolutePath();
		
		
		String file = absExe + " " + absmFormat + " " + absPhoto;
		//String file = "C:\\Users\\tozaj\\eclipse\\ProjekatIzJave\\testiranjeZaSutrasnjiLab\\Debug\\testiranjeZaSutrasnjiLab.exe "
		// + "C:\\Users\\tozaj\\eclipse\\ProjekatIzJave\\tmpProject\\myFormat.toza " + "C:\\Users\\tozaj\\eclipse\\ProjekatIzJave\\tmpProject\\photo.bmp";
		
		Runtime runtime = Runtime.getRuntime();
		
		try {
			Process process = runtime.exec(file);
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public Composite getComposite() {
		return composite;
	}

	public static String getPath() {
		return path;
	}

	public Layers getLayers() {
		return layers;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setComposite(Composite composite) {
		this.composite = composite;
	}

	public void setLayers(Layers layers) {
		this.layers = layers;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public static void main(String []argc) {
		Project_image p = new Project_image();
		p.composite.addOperation("Inv", 0);
		p.layers.addLayer("C:\\Users\\tozaj\\Desktop\\toze.bmp", 0.5, true, true);
		p.createSelection("tozicaa");
		p.addRectangleinSelection(0, 0, 200, 200);
		p.composite.writeInCompositeFile();
		p.writeMyFormat();
		p.proccesInCplusplus();
	}
	
	public void addOperation(String op, int arg) {
		if(composite == null)
			composite = new Composite("tmpProject\\composite.fun");
		composite.addOperation(op, arg);
	}
	
	public int[] getRectangle(int i) {
		return selection.getRectangle(i);
	}
}
