package javaPart;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectDialog extends Dialog{

	private GUIMAIN owner;
	private TextField name = new TextField("Enter the name of Selection");

	public SelectDialog(GUIMAIN owner, String title, boolean modal) {
		super(owner, title, modal);
		this.owner = owner;
		setSize(200,200);
		
		createDialog();
	}

	private void createDialog() {
		Label info = new Label("Choose option on selection");
		Button add = new Button("Add");
		Button delete = new Button("Delete");
		setLayout(new BorderLayout());
		Panel tmp = new Panel(new FlowLayout());
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.project.createSelection(name.getText());
				setVisible(false);
				GUIMAIN.haveSelection = true;
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.project.setSelection(null);
				setVisible(false);
				GUIMAIN.haveSelection = false;
				
			}
		});
		
		add(info, BorderLayout.NORTH);
		add(name, BorderLayout.CENTER);
		tmp.add(add);
		tmp.add(delete);
		add(tmp, BorderLayout.SOUTH);
		
		
		
	}
	
}
