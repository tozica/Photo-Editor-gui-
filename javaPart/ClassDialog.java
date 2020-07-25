package javaPart;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import cplusplusElements.Layers;
import cplusplusElements.Project_image;

public class ClassDialog extends Dialog{

	private ClassDialog dialog;
	private String path;
	private double opacity;
	private boolean active, visible;
	private Project_image layers;
	private GUIMAIN guiman;

	public ClassDialog(GUIMAIN guimain, String title, boolean modal, Project_image layers) {
		super(guimain, "InsertLayer", false);
		dialog = this;// TODO Auto-generated constructor stub
		this.layers = layers;
		this.guiman = guimain;
		makeDialog();
	}
	
	private void addDialoglisteners(TextField pathField, TextField opacityField, TextField visible1, TextField active1, Button submit) {
		pathField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			     path = pathField.getText();
			     
			     String tmpPath = "tmpProject\\layer" + guiman.project.getLayers().getElem().size() + ".bmp";
			     File photo = new File(tmpPath);
			     File exef = new File("testiranjeZaSutrasnjiLab\\Debug\\testiranjeZaSutrasnjiLab.exe");
			     File mFormat = new File("preOperation.toza");
			     String absPhoto = photo.getAbsolutePath();
			     String absmFormat = mFormat.getAbsolutePath();
			     String absExe = exef.getAbsolutePath();
				 String file = absExe + " " + absmFormat + " " + absPhoto;
				 
				 Project_image tmp = new Project_image();
				 tmp.addLayer(new File(path).getAbsolutePath());
				 tmp.setComposite(guiman.project.getComposite());
				 tmp.writePreOperate();

			     Runtime runtime = Runtime.getRuntime();
					
					try {
						Process process = runtime.exec(file);
						process.waitFor();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
		    		 path = new File(tmpPath).getAbsolutePath();
			}
		});
		
		opacityField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				opacity = Double.parseDouble(opacityField.getText());
			}
		});
		
		visible1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				visible = Boolean.parseBoolean(visible1.getText());
			}
		});
		
		active1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				active = Boolean.parseBoolean(active1.getText());
				
			}
		});
		
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addLayer();
				
			}
		});
		
	}
	
	protected void addLayer() {
		layers.addLayer(path, opacity, visible, active);
		GUIMAIN.toDo = true;
		setVisible(false);
		//guiman.callProject();
		
	}

	private void makeDialog() {
		//dialog = new Dialog(this, "InsertLayer", false);
		dialog.setSize(500,400);
		dialog.setLayout(new GridLayout(10,1, 10,10));
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dialog.setVisible(false);
			}
		});
		Label textForPath = new Label("      "
				+ "                                 "
				+ "             Insert variables four your image");
		
		
		TextField pathField = new TextField();
		TextField opacityField = new TextField();
		TextField visible = new TextField();
		TextField active = new TextField();
		
		Label pathLabel = new Label("Insert path of the image");
		Label opacLabel = new Label("Insert opacity of the Layer");
		Label visibleLabel = new Label("Visible(true/false)");
		Label activeLabel = new Label("Active(true/false)");
		
		Button submit = new Button("Submit");
		
		
		addDialoglisteners(pathField, opacityField, visible, active, submit);
		
		dialog.add(textForPath);
		dialog.add(pathLabel);
		dialog.add(pathField);
		dialog.add(opacLabel);
		dialog.add(opacityField);
		dialog.add(visibleLabel);
		dialog.add(visible);
		dialog.add(activeLabel);
		dialog.add(active);
		dialog.add(submit);
	}


}
