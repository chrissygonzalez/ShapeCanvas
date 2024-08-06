package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	private JButton deleteBtn;
	
	public ListPanel() {
		list = new JList<NamedRectangle>();
		listModel = new DefaultListModel<NamedRectangle>();
		deleteBtn = new JButton("Delete selected");
		deleteBtn.setEnabled(false);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(155, 215));
		JPanel box = new JPanel(new BorderLayout());
		box.add(deleteBtn, BorderLayout.CENTER);
		box.add(scrollPane, BorderLayout.SOUTH);
		add(box, BorderLayout.CENTER);
		
		Border blackline = BorderFactory.createTitledBorder("All shapes");
		setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  blackline));
	}
	
	public void addListeners(ListSelectionListener lListen, ListMouseAdapter lm, ListActionListener lAction) {
		list.addListSelectionListener(lListen);
		list.addMouseListener(lm);
		list.addMouseMotionListener(lm);
		deleteBtn.addActionListener(lAction);
	}
	
	public void updateList(LinkedList<NamedRectangle> shapes, NamedRectangle selected) {
		listModel.clear();
		Integer selectedIndex = null;
		for (int i = 0; i < shapes.size(); i++){
		    listModel.addElement(shapes.get(i));
		    if(shapes.get(i).equals(selected)) selectedIndex = i;
		}
		list.setModel(listModel);
		if(selectedIndex != null) {
			list.setSelectedIndex(selectedIndex);
		} else {
			list.clearSelection();
		}
	}
	
	public void setDeleteButtonState(NamedRectangle r) {
		if(r == null) {
			deleteBtn.setEnabled(false);
		} else {
			deleteBtn.setEnabled(true);
		}
	}
	
	public void setSelected(NamedRectangle r) {
		list.clearSelection();
		deleteBtn.setEnabled(false);
		
		if(r != null) {
			deleteBtn.setEnabled(true);
			int size = listModel.getSize();
			int index = 0;
			while(index < size) {
				if(listModel.getElementAt(index) == r) list.setSelectedIndex(index);
				index++;
			}
		}
	}
	
	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}
	
	public int getCurrentIndex(Point p) {
		return list.locationToIndex(p);
	}
	
	public NamedRectangle getListModelIndex(int i) {
		return listModel.get(i);
	}
	
	public void removeFromListModel(int i) {
		listModel.remove(i);
	}
	
	public DefaultListModel<NamedRectangle> deleteAndUpdateShapes(){
		int selectedIndex = list.getSelectedIndex();
		removeFromListModel(selectedIndex);
		return getListModel();
	}
	
	public void addToListModel(int i, NamedRectangle r) {
		listModel.add(i, r);
	}
	
	public DefaultListModel<NamedRectangle> getListModel(){
		return listModel;
	}
}