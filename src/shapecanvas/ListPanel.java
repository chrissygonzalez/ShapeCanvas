package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

public class ListPanel extends JPanel {
	private JList<NamedRectangle> list;
	private JScrollPane scrollPane;
	private DefaultListModel<NamedRectangle> listModel;
	private ListSelectionListener lListen;
	
	public ListPanel(ListSelectionListener lListen) {
		this.lListen = lListen;
		list = new JList<NamedRectangle>();
		list.addListSelectionListener(lListen);
		listModel = new DefaultListModel<NamedRectangle>();
		
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(110, 175));;

		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void updateList(ArrayList<NamedRectangle> shapes, NamedRectangle selected) {
		listModel.clear();
		Integer selectedIndex = null;
		for (int i = shapes.size() - 1; i >= 0; i--)
		{
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
	
}