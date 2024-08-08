package shapecanvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

class ToolButton extends JToggleButton {
	private Color backgroundColor = Color.WHITE;
	private Color selectedColor = new Color(135, 187, 249);
	
	public ToolButton(String actionCommand, String iconPath, boolean selected) {
		setPreferredSize(new Dimension(40, 40));
		setBackground(selected ? selectedColor : backgroundColor);
		setOpaque(true);
		
		setIcon(new ImageIcon(iconPath));
		setBorder( new LineBorder(Color.BLACK) );
		setActionCommand(actionCommand);
		setSelected(selected);
		
		addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					JToggleButton btn = (JToggleButton)e.getSource();
					btn.setBackground(selectedColor);
				} else if(e.getStateChange()==ItemEvent.DESELECTED){
			        JToggleButton btn = (JToggleButton)e.getSource();
			        btn.setBackground(backgroundColor);
			      }
			   }
			});
	}
}