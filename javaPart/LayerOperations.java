package javaPart;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LayerOperations extends Dialog{
	private GUIMAIN owner;
	private int numOfLayer;
	private String Value;
	CheckboxGroup operation;
	
	public LayerOperations(GUIMAIN owner, String title, boolean modal) {
		super(owner, title, modal);
		this.owner = owner;
		setSize(400,250);
		setLayout(new GridLayout(2,1));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		createOperations();
	}

	private void createOperations() {
		Panel up = new Panel(new GridLayout(5,1));
		Panel down = new Panel(new FlowLayout());
		
		operation = new CheckboxGroup();
		Checkbox setOpacity = new Checkbox("Set Opacity", false, operation);
		Checkbox setVisible = new Checkbox("Set Visible", false, operation);
		Checkbox setActive = new Checkbox("Set Active", false, operation);
		Checkbox setDelete = new Checkbox("Delete", false, operation);
		
		down.add(setOpacity);
		down.add(setVisible);
		down.add(setActive);
		down.add(setDelete);
		
		Label select = new Label("Select option and insert which layer do you want");
		TextField text = new TextField("Num of Layer");
		
		Label insertvalue = new Label("Insert value (Opacity (0-1), Active or Visible (true/false)");
		TextField value = new TextField();
		
		value.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Value = value.getText();
				
			}
		});
		
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				numOfLayer = Integer.parseInt(text.getText());
			}
		});
		
		Button submit = new Button("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(operation.getSelectedCheckbox() == setOpacity) {
					owner.project.setOpacity(numOfLayer, Double.parseDouble(Value));
				}
				else
					if(operation.getSelectedCheckbox() == setActive)
						owner.project.setActivity(numOfLayer, Boolean.parseBoolean(Value));
					else
						if(operation.getSelectedCheckbox() == setVisible)
							owner.project.setVisibility(numOfLayer, Boolean.parseBoolean(Value));
						else
							if(operation.getSelectedCheckbox() == setDelete)
								owner.project.removeLayer(numOfLayer);
				GUIMAIN.toDo = true;
				setVisible(false);
				
			}
		});
		
		up.add(select);
		up.add(text);
		up.add(insertvalue);
		up.add(value);
		up.add(submit);
		
		add(up);
		add(down);
		
		
	}
	
}
