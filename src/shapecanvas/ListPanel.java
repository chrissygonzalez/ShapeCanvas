package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
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
	private final Color HIGHLIGHT = Color.WHITE;
	private final Color SHADOW = new Color(135, 187, 249);
	private JList<NamedShape> list;
	private JScrollPane scrollPane;
	private DefaultListModel<NamedShape> listModel;
	private JButton deleteBtn;
	private JButton copyBtn;
	
	public ListPanel() {
		list = new JList<NamedShape>();
		listModel = new DefaultListModel<NamedShape>();
		deleteBtn = new JButton("Delete");
		deleteBtn.setEnabled(false);
		deleteBtn.setActionCommand("delete");
		copyBtn = new JButton("Copy");
		copyBtn.setEnabled(false);
		copyBtn.setActionCommand("copy");
		
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(155, 215));
		JPanel box = new JPanel(new BorderLayout());
		box.setOpaque(false);
		
		JPanel btns = new JPanel(new BorderLayout());
		btns.setOpaque(false);
		btns.add(deleteBtn, BorderLayout.WEST);
		btns.add(copyBtn, BorderLayout.EAST);
		box.add(btns, BorderLayout.CENTER);
		box.add(scrollPane, BorderLayout.SOUTH);
		add(box, BorderLayout.CENTER);
		
		Border etched = BorderFactory.createEtchedBorder(HIGHLIGHT, SHADOW);
		Border title = BorderFactory.createTitledBorder(etched, "All shapes");
		setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  title));
	}
	
	public void addListeners(ListSelectionListener lListen, ListMouseAdapter lm, ListActionListener lAction) {
		list.addListSelectionListener(lListen);
		list.addMouseListener(lm);
		list.addMouseMotionListener(lm);
		deleteBtn.addActionListener(lAction);
		copyBtn.addActionListener(lAction);
	}
	
	public void updateList(LinkedList<NamedShape> shapes, NamedShape selected) {
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
	
	public void setListButtonState(NamedShape r) {
		if(r == null) {
			deleteBtn.setEnabled(false);
			copyBtn.setEnabled(false);
		} else {
			deleteBtn.setEnabled(true);
			copyBtn.setEnabled(true);
		}
	}
	
	public void setSelected(NamedShape r) {
		list.clearSelection();
		deleteBtn.setEnabled(false);
		copyBtn.setEnabled(false);

		if(r != null) {
			deleteBtn.setEnabled(true);
			copyBtn.setEnabled(true);
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
	
	public NamedShape getListModelIndex(int i) {
		return listModel.get(i);
	}
	
	public void removeFromListModel(int i) {
		listModel.remove(i);
	}
	
	public DefaultListModel<NamedShape> deleteAndUpdateShapes(){
		int selectedIndex = list.getSelectedIndex();
		removeFromListModel(selectedIndex);
		return getListModel();
	}
	
	public DefaultListModel<NamedShape> copyAndUpdateShapes(){
		int selectedIndex = list.getSelectedIndex();
		NamedShape selected = listModel.get(selectedIndex);
		
		NamedShape newShape = null;
		if(selected instanceof NamedRectangle) {
			newShape = new NamedRectangle((NamedRectangle) selected);
		} else if(selected instanceof NamedCircle) {
			newShape = new NamedCircle((NamedCircle) selected);
		} else {
			newShape = new NamedTriangle((NamedTriangle) selected);
		}
		
		addToListModel(0, newShape);
		setSelected(newShape);
		return getListModel();
	}
	
	public void addToListModel(int i, NamedShape r) {
		listModel.add(i, r);
	}
	
	public DefaultListModel<NamedShape> getListModel(){
		return listModel;
	}
}