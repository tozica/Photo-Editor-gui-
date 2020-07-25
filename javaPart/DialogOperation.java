package javaPart;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

class DialogforOper extends Dialog{
	DialogOperation owner;
	TextField valid = new TextField("Enter argument for operation");

	public DialogforOper(DialogOperation owner, String title, boolean modal) {
		super(owner, title, false);
		this.owner = owner;
		setSize(40,100);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		valid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setArg(Integer.parseInt(valid.getText()));
				setVisible(false);
			}
		});
		add(valid);
	}
	
}

public class DialogOperation extends Dialog{

	private GUIMAIN owner;
	private Choice operations = new Choice();
	private int arg = 0;
	private DialogforOper argument = new DialogforOper(this, "Arg", false);
	private Map<String, Integer> map = new HashMap();
	public DialogOperation(GUIMAIN owner, String title, boolean modal) {
		super(owner, "Operations", false);
		this.owner = owner;
		setSize(200,200);
		inicmap();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		createDialog();
		
	}
	public void setArg(int arg) {
		this.arg = arg;
	}
	private void inicmap() {
		map.put("Addition", 1);
		map.put("Substract", 2);
		map.put("InverseSub", 3);
		map.put("Multiply", 4);
		map.put("Division", 5);
		map.put("InverseDiv", 6);
		map.put("Power", 7);   
		map.put("Min", 8);
		map.put("Max", 9);
		map.put("Log", 10);
		map.put("Inverse", 11);
		map.put("Abs", 12);
		map.put("Convert_to_grey", 13);
		map.put("Convert_to_black_and_white", 14);
		map.put("Mediana", 15);
		
	}
	private void createDialog() {
		setLayout(new GridLayout(2,1));
		Panel up = new Panel(new FlowLayout());
		Panel down = new Panel(new FlowLayout());
		Panel upLeft = new Panel(new BorderLayout());
		Panel upRight = new Panel(new FlowLayout());
		
		Label info = new Label("Choose operations");
		
		operations.add("Choose");
		operations.add("Addition");
		operations.add("Substract");
		operations.add("InverseSub");
		operations.add("Multiply");
		operations.add("Division");
		operations.add("InverseDiv");
		operations.add("Power");
		operations.add("Min");
		operations.add("Max");
		operations.add("Log");
		operations.add("Inverse");
		operations.add("Abs");
		operations.add("Convert_to_grey");
		operations.add("Convert_to_black_and_white");
		operations.add("Mediana");
		
		upLeft.add(operations, BorderLayout.CENTER);
		upLeft.add(info, BorderLayout.NORTH);
		
		Button addOp = new Button("Add");
		
		addOp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getInfo(); //
				
			}
		});
		
		operations.addItemListener(new ItemListener() {
			//10,11,12,13,14,15
			@Override
			public void itemStateChanged(ItemEvent e) {
				int key = map.get(operations.getSelectedItem());
				if(!(key == 10 || key == 11 || key == 12 || key == 13 || key == 14 || key == 15)) {
					argument.setVisible(true); 
				}
				
			}
		});
		
		Button submit = new Button("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.project.getComposite().writeInCompositeFile();
				setVisible(false);
				GUIMAIN.toDo = true;
				
			}
		});
		up.add(upLeft);
		up.add(upRight);
		down.add(addOp);
		down.add(submit);
		add(up);
		add(down);
		

	}
	protected void getInfo() {
		String tmp = operations.getSelectedItem();
		
		switch (map.get(tmp)) {
		case 1:
			owner.project.addOperation("Add", arg);
			break;
		case 2:
			owner.project.addOperation("Sub", arg);
			break;
		case 3:
			owner.project.addOperation("InvSub", arg);
			break;
		case 4:
			owner.project.addOperation("Mul", arg);
			break;
		case 5:
			owner.project.addOperation("Div", arg);
			break;
		case 6:
			owner.project.addOperation("InvDiv", arg);
			break;
		case 7:
			owner.project.addOperation("Pow", arg);
			break;
		case 8:
			owner.project.addOperation("Min", arg);
			break;
		case 9:
			owner.project.addOperation("Max", arg);
			break;
		case 10:
			owner.project.addOperation("Log", arg);
			break;
		case 11:
			owner.project.addOperation("Inv", arg);
			break;
		case 12:
			owner.project.addOperation("Abs", arg);
			break;
		case 13:
			owner.project.addOperation("ConvGrey", arg);
			break;
		case 14:
			owner.project.addOperation("ConvBW", arg);
			break;
		case 15:
			owner.project.addOperation("Med", arg);
			break;
		default:
			break;
		}
		arg = 0;
		
	}
	
}
