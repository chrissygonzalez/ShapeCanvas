package shapecanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class InspectorActionListener extends FocusAdapter implements ActionListener {
	private DrawingPanel d;
	
	public InspectorActionListener(DrawingPanel d) {
		this.d = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fieldText = ((JTextField) e.getSource()).getText();
		
		if(!fieldText.isEmpty()) {
			Double value = Double.parseDouble(fieldText);
			d.modifyShape(e.getActionCommand(), (int)Math.round(value));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		String fieldText = ((JTextField) e.getSource()).getText();
		
		if(!fieldText.isEmpty()) {
			Double value = Double.parseDouble(fieldText);
			d.modifyShape(e.getComponent().getName(), (int)Math.round(value));
		}
	}
	
}