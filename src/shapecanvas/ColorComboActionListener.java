package shapecanvas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

class ColorComboActionListener implements ActionListener {
	private ArrayList<Color> colors;
	private DrawingPanel d;
	
	public ColorComboActionListener(ArrayList<Color> colors, DrawingPanel d) {
		this.colors = colors;
		this.d = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	JComboBox<Integer> combo = (JComboBox)e.getSource();
    	Integer selected = (Integer)combo.getSelectedItem();
    	Color selectedColor = colors.get(selected);
    	
    	if(e.getActionCommand() == "drawStrokeColor" || e.getActionCommand() == "drawFillColor") {
    		d.updateDefaultColors(e.getActionCommand(), selectedColor);
    	} else {
    		d.modifyShapeColors(e.getActionCommand(), selectedColor);
    	}
	}
	
}