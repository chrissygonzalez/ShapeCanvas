package shapecanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

class ShapeMenu extends JMenu {

	public JMenuItem createFileOpenItem() {
		JMenuItem item = new JMenuItem("Open");
//		item.addActionListener(new OpenFileListener());
		return item;
	}
	
	public JMenuItem createFileSaveItem() {
		JMenuItem item = new JMenuItem("Save");
		return item;
	}
	
	public JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");      
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				System.exit(0);
	         }
		}      
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	public JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createFileOpenItem());
		menu.add(createFileSaveItem());
		menu.add(createFileExitItem());
		return menu;
	}
}

