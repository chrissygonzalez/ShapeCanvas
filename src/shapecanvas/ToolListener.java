package shapecanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ToolListener implements ActionListener {
	private DrawingPanel d;
	private InspectorPanel i;
	private ListPanel l;
	
	public ToolListener(DrawingPanel d, InspectorPanel i, ListPanel l) {
		this.d = d;
		this.i = i;
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "select"){
			d.setPanelCursor("select");
		} else if(e.getActionCommand() == "stroke weight"){
			JTextField field = (JTextField)e.getSource();
			String text = field.getText();
			try {
				Float value = Float.parseFloat(text);
				d.setStrokeWeight(value);
			} catch(NumberFormatException err) {
				field.setText("1.0");
			}
		} else {
			d.setPanelCursor("draw");
			d.setSelectedShape(null);
			i.setSelected(null);
			l.setSelected(null);
		}
	}
	
}