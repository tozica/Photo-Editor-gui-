package javaPart;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import cplusplusElements.Project_image;

public class LayerInfo extends Dialog{
	private Project_image project;
	private GUIMAIN owner;
	Panel up = new Panel();
	Panel down = new Panel();
	Button checkLayerState = new Button("Layers State");
	
	public LayerInfo(Project_image project, GUIMAIN owner) {
		super(owner, "Layer state", false);
		this.project = project;
		this.owner = owner;
		setLayout(new GridLayout(1,4));
		setSize(400,400);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		create_state();
		
	}

	private void create_state() {
		Panel layerName = new Panel(new GridLayout(5,1));
		layerName.add(new Label("Layer name"));
		
		for(int i = 0; i < project.getLayers().getElem().size(); i++) {
			Label tmp = new Label("Label " + i);
			layerName.add(tmp);
		}
		add(layerName);
		
		Panel layerOpacity = new Panel(new GridLayout(5,1));
		layerOpacity.add(new Label("Opacity"));
		
		for(int i = 0; i < project.getLayers().getElem().size(); i++) {
			Label tmp = new Label("" + project.getLayers().getOpacity(i));
			layerOpacity.add(tmp);
		}
		add(layerOpacity);
		
		Panel layerVisible = new Panel(new GridLayout(5,1));
		layerVisible.add(new Label("Visibility"));
		
		for(int i = 0; i < project.getLayers().getElem().size(); i++) {
			Label tmp;
			if(project.getLayers().getVisibility(i))
				tmp = new Label("yes");
			else
				tmp = new Label("no");
			
			layerVisible.add(tmp);
		}
		add(layerVisible);
	
		Panel layerActive = new Panel(new GridLayout(5,1));
		layerActive.add(new Label("Activity"));
		
		for(int i = 0; i < project.getLayers().getElem().size(); i++) {
			Label tmp;
			if(project.getLayers().getActivity(i))
				tmp = new Label("yes");
			else
				tmp = new Label("no");
			
			layerActive.add(tmp);
		}
		add(layerActive);
	}
	
}
