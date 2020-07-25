package javaPart;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import cplusplusElements.Layers;
import cplusplusElements.Project_image;





public class GUIMAIN extends Frame {
	private DisplayingImage img;
	private MenuBar menuBar = new MenuBar();
	//private Layers layers = new Layers();
	Project_image project = new Project_image();
	private ClassDialog dialog = new ClassDialog(this, "Layer", false, project);
	private StateCheck stateCheck;
	private GUIMAIN me = this;
	private LayerInfo infoLayer = new LayerInfo(project, this);
	private LayerOperations layerOperation = new LayerOperations(this, "Operations", false);
	private DialogOperation dialogOperation = new DialogOperation(this, "Operations", false);
	private ProcessAllLayers allLayers = new ProcessAllLayers(this);
	private DialogSaveAs saveAs = new DialogSaveAs(this, "Save as", false);
	private OpenDialog open;
	public static boolean haveSelection = false;
	private SelectDialog selectDialog;
	public static boolean toDo = false;
	int width = 0, height = 0;
	public static boolean deleteRect = false;
	
	public GUIMAIN() {
		stateCheck = new StateCheck(this);
		setTitle("PhotoShop:D");
		prepareGui();
		addComponents();
		addListeners();
		repaint();
		stateCheck.start();
		
	}
	
	public DisplayingImage getImg() {
		return img;
	}

	public void callProcessAllLayers() {
		allLayers.processAll();
		project.setComposite(null);
		try {
			Files.deleteIfExists(Paths.get("tmpProject\\composite.fun"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//.printStackTrace();
		}
	}
	
	private void addComponents() {
		img = new DisplayingImage(this);
		add(img, BorderLayout.CENTER);
		addmenus();
	}
	
	private void addmenus() {
		Menu fileMenu = new Menu("File");
		fileMenu.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu layerMenu = new Menu("Layer");
		layerMenu.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu operations = new Menu("Operations");
		fileMenu.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		operations.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu selection1 = new Menu("Selection");
		selection1.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		
		MenuItem MenuItemAddLayer= new MenuItem("Add new Layer", new MenuShortcut(KeyEvent.VK_N));
		MenuItemAddLayer.setActionCommand("Add new Layer");
		
		MenuItem MenuItemSaveProject = new MenuItem("Save as", new MenuShortcut(KeyEvent.VK_S));
		MenuItemSaveProject.setActionCommand("Save as");
		
		MenuItem MenuItemOpen = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
		MenuItemSaveProject.setActionCommand("Open");
		
		MenuItem layerStateItem = new MenuItem("Layer State");
		MenuItem layerOperations = new MenuItem("Layer operation");
		MenuItem dialogOperations = new MenuItem("Operate");
		MenuItem selectD = new MenuItem("Select");
		MenuItem deleteRectangle = new MenuItem("Delete rect");
		
		deleteRectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIMAIN.deleteRect = true;
				
			}
		});
		
		selectD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDialog = new SelectDialog(me, "Select", false);
				selectDialog.setVisible(true);
				
			}
		});
		
		MenuItemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open = new OpenDialog(me, "Open", false);
				open.setVisible(true);
				
			}
		});
		
		MenuItemSaveProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAs = new DialogSaveAs(me, "Save as", true);
				saveAs.setVisible(true);
				
			}
		});
		
		dialogOperations.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogOperation = new DialogOperation(me, "Operations", false);
				dialogOperation.setVisible(true);
				
			}
		});
		
		layerOperations.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layerOperation = new LayerOperations(me, "Operations", false);
				layerOperation.setVisible(true);
				
			}
		});
		
		MenuItemAddLayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog = new ClassDialog(me, "Layer", false, project);
				dialog.setVisible(true);
				
			}
		});
		
		layerStateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				infoLayer = new LayerInfo(project, me);
				infoLayer.setVisible(true);				
			}
		});
		
		fileMenu.add(MenuItemAddLayer);
		fileMenu.add(MenuItemSaveProject);
		fileMenu.add(MenuItemOpen);
		layerMenu.add(layerStateItem);
		layerMenu.add(layerOperations);
		operations.add(dialogOperations);
		selection1.add(selectD);
		selection1.add(deleteRectangle);
		
		Menu search = new Menu("Search");
		search.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu project = new Menu("Project");
		project.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu window = new Menu("Window");
		window.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		Menu help = new Menu("Help");
		help.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		
		menuBar.add(fileMenu);
		menuBar.add(layerMenu);
		menuBar.add(operations);
		menuBar.add(selection1);
		menuBar.add(search);
		menuBar.add(project);
		menuBar.add(window);
		menuBar.add(help);
		this.setMenuBar(menuBar);
		
	}

	private void prepareGui() {
		setVisible(true);
		setSize(810,600);
		setLayout(new BorderLayout());
	}
	
	public void readImage() {
		img.readImage();
		repaint();
	}
	
	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stateCheck.interrupt();
				dispose();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void callProject() {
		project.writeMyFormat();
		project.proccesInCplusplus();
		readImage();
		if(this.width < this.getImg().getWidth())
			this.width = this.getImg().getWidth();
		if(this.height < this.getImg().getHeight())
			this.height = this.getImg().getHeight();
		this.setSize(this.width, this.height + 50);
		//this.resize(img.getWidth() + 15, img.getHeight() + 117);
		toDo = false;
		repaint();
		
	}
	
	public void callLayerInfo() {
		
	}

	public static void main(String[] args) {
		new GUIMAIN();
		
	}


}
