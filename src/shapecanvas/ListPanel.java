package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public class ListPanel extends JPanel {
	private JList<NamedRectangle> list;
	private JScrollPane scrollPane;
	private DefaultListModel<NamedRectangle> listModel;
	
	public ListPanel() {
		list = new JList<NamedRectangle>();
		listModel = new DefaultListModel<NamedRectangle>();
		
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(155, 215));
		add(scrollPane, BorderLayout.CENTER);
		
		Border blackline = BorderFactory.createTitledBorder("All shapes");
		setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  blackline));
	}
	
	public void addListeners(ListSelectionListener lListen) {
		list.addListSelectionListener(lListen);
	}
	
	public void updateList(ArrayList<NamedRectangle> shapes, NamedRectangle selected) {
		listModel.clear();
		Integer selectedIndex = null;
		for (int i = shapes.size() - 1; i >= 0; i--){
		    listModel.addElement(shapes.get(i));
		    if(shapes.get(i).equals(selected)) selectedIndex = shapes.size() - i - 1;
		}
		list.setModel(listModel);
		if(selectedIndex != null) {
			list.setSelectedIndex(selectedIndex);
		} else {
			list.clearSelection();
		}
	}
	
	public void setSelected(NamedRectangle r) {
		list.clearSelection();
		if(r != null) {
			int size = listModel.getSize();
			int index = 0;
			while(index < size) {
				if(listModel.getElementAt(index) == r) list.setSelectedIndex(index);
				index++;
			}
		}
	}
	
}