package javaPart;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cplusplusElements.Layers;
import cplusplusElements.Selection;

public class OpenDialog extends Dialog{

	private GUIMAIN owner;
	private String path;

	public OpenDialog(GUIMAIN owner, String title, boolean modal) {
		super(owner, title, modal);
		this.owner = owner;
		setLayout(new FlowLayout());
		setSize(300,100);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		creteOpenDialog();
	}

	private void creteOpenDialog() {
		Label info = new Label("Enter a path of file in .toza format");
		TextField enterPath = new TextField("Enter the path of the .toza file");
		
		enterPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				path = enterPath.getText();
				readMyformat(path);
				setVisible(false);
				
			}
		});
		add(info);
		add(enterPath);
	}

	protected void readMyformat(String path2) {
		Pattern patern = Pattern.compile("<image>");
		Pattern patern1 = Pattern.compile("</image>");
		Pattern patern2 = Pattern.compile("\t\t<layer>");
		Pattern patern3 = Pattern.compile("\t\t\t<path>([^<]*)</path>");
		Pattern patern4 = Pattern.compile("\t<selection>");
		Pattern patern5 = Pattern.compile("\t\t<name>([^<]*)</name>");
		Pattern patern6 = Pattern.compile("\t\t<active>([^<]*)</active>");
		Pattern patern7 = Pattern.compile("\t\t<rectangle>");
		Pattern patern8 = Pattern.compile("\t\t\t<dimensions>([0-9]*),([0-9]*),([0-9]*),([0-9]*)</dimensions>");
		Pattern patern9 = Pattern.compile("\t<operation>");
		Pattern patern10 = Pattern.compile("\t\t<composite>([^<]*)</composite>");
		Pattern patern11 = Pattern.compile("\t</selection>");
		Pattern patern12 = Pattern.compile("\t\t</rectangle>");
		Pattern patern13 = Pattern.compile("\t\t\t<opacity>(\\d+.\\d+)</opacity>");
		Pattern patern14 = Pattern.compile("\t\t\t<visible>(\\d+)</visible>");
		Pattern patern15 = Pattern.compile("\t\t\t<active>(\\d+)</active>");
		
		Matcher match;
		String s;
		
		try {
			File ifs = new File(path2);
			Scanner reader = new Scanner(ifs);
			while(reader.hasNextLine()) {
				s = reader.nextLine();
				match = patern2.matcher(s);
				if(match.matches()){
					s = reader.nextLine();
					match = patern3.matcher(s);
					if(match.matches()) {
						String p = match.group(1);
						int active = 0, visible = 0;
						double opacity = 0;
						s = reader.nextLine();
						match = patern13.matcher(s);
						if(match.matches())
						opacity = Double.parseDouble(match.group(1));
						s = reader.nextLine();
						match = patern14.matcher(s);
						if(match.matches())
						visible = Integer.parseInt(match.group(1));
						s = reader.nextLine();
						match = patern15.matcher(s);
						if(match.matches())
						active = Integer.parseInt(match.group(1));
						owner.project.addLayer(p, opacity, visible == 1 ? true: false, active == 1 ? true: false);
						
					}
				}
				match = patern4.matcher(s);
				if(match.matches()) {
					s = reader.nextLine();
					match = patern11.matcher(s);
					if(!match.matches()) {
						match = patern5.matcher(s);
						String name = null;
						if(match.matches())
						name = match.group(1);
						s = reader.nextLine();
						int a = 0;
						match = patern6.matcher(s);
						if(match.matches())
					    a = Integer.parseInt(match.group(1));
						owner.project.setSelection(new Selection(name, a == 1? true : false));
						while(true) {
							s = reader.nextLine();
							match = patern11.matcher(s);
							if(match.matches())
								break;
							match = patern8.matcher(s);
							if(match.matches()) {
								GUIMAIN.haveSelection = true;
								owner.project.addRectangleinSelection(Integer.parseInt(match.group(1)), Integer.parseInt(match.group(2)),
										Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)));
								
							}
						}
					}
				}
				match = patern9.matcher(s);
				if(match.matches()) {
					s = reader.nextLine();
					match = patern10.matcher(s);
					if(match.matches())
						copyComposite(match.group(1));
				}
			}
		}catch (Exception e) {
			System.out.println("ispisi nestp");
		}
		
		owner.project.writeMyFormat();
		//owner.project.writeOnDemand(path2);
		owner.project.proccesInCplusplus();
		owner.readImage();
		if(owner.width < owner.getImg().getWidth())
			owner.width = owner.getImg().getWidth();
		if(owner.height < owner.getImg().getHeight())
			owner.height = owner.getImg().getHeight();
		this.setSize(owner.width, owner.height + 50);
		//this.resize(img.getWidth() + 15, img.getHeight() + 117);
		owner.toDo = false;
		owner.resize(owner.width, owner.height);
		repaint();
		
	}

	private void copyComposite(String group) {
		File dest = new File("tmpProject\\composite.fun");
		File soruce = new File(group);
		
		Scanner reader;
		FileWriter writer;
		try {
			writer = new FileWriter(dest);
			reader = new Scanner(soruce);
			String buffer;
			
			while(reader.hasNextLine()) {
				buffer = reader.nextLine();
				writer.write(buffer);
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		
	}
	
		
	
	
	
}
