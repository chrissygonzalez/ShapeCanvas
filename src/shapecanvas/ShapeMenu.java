package shapecanvas;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

class ShapeMenu extends JMenu {
	private ShapeMenuListener sListen;
	
	public void addListeners(ShapeMenuListener sListen) {
		this.sListen = sListen;
	}

	public JMenuItem createFileOpenItem() {
		JMenuItem item = new JMenuItem("Open...");
		item.setActionCommand("open");
		item.addActionListener(sListen);
		return item;
	}
	
	public JMenuItem createFileSaveItem() {
		JMenuItem item = new JMenuItem("Save...");
		item.setActionCommand("save");
		item.addActionListener(sListen);
		return item;
	}
	
	public JMenuItem createFileExportItem() {
		JMenuItem item = new JMenuItem("Save as image...");
		item.setActionCommand("image");
		item.addActionListener(sListen);
		return item;
	}
	
	public JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit"); 
		item.setActionCommand("exit");
		item.addActionListener(sListen);
		return item;
	}
	
	public JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createFileOpenItem());
		menu.add(createFileSaveItem());
		menu.add(createFileExportItem());
		menu.add(createFileExitItem());
		return menu;
	}
}

