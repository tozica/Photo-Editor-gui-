package javaPart;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DialogSaveAs extends Dialog {

	private GUIMAIN owner;
	private TextField textPath = new TextField("Enter the path where you want to save");
	private String path;
	
	public DialogSaveAs(GUIMAIN owner, String title, boolean modal) {
		super(owner, title, modal);
		this.owner = owner;
		setSize(600,100);
		
		createDialog();
	}

	private void createDialog() {
		Panel up = new Panel(new FlowLayout());
		Panel down =  new Panel(new FlowLayout());
		Label info = new Label("Choose option to save");
		up.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		textPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				path = textPath.getText();
				
			}
		});
		
		Button bmp = new Button("BMP");
		bmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File source = new File("tmpProject\\photo.bmp");
				File dest = new File(path);
				copyToDest(source, dest);
				setVisible(false);
				
			}
		});
		
		Button dotToza = new Button(".toza");
		dotToza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				write(path);
				setVisible(false);
				
			}
		});
		
		up.add(info);
		up.add(textPath);
		down.add(bmp);
		down.add(dotToza);
		
		add(up, BorderLayout.NORTH);
		add(down, BorderLayout.CENTER);
		
		
		
		
	}

	protected void write(String path2) {
		for(int i = 0; i < owner.project.getLayers().getElem().size(); i++) {
			File source = new File("tmpProject\\layer" + i + ".bmp");
			File dest = new File(path + "\\layer" + i + ".bmp");
			copyToDest(source, dest);
			owner.project.getLayers().setPath(i, path2 + "\\layer" + i + ".bmp");
		}
		owner.project.writeOnDemand(path2 + "\\myFormat.toza");
		
	}

	protected void copyToDest(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int lenght;
			while((lenght = is.read(buffer)) > 0) {
				os.write(buffer, 0, lenght);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	

}
