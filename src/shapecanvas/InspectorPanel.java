package shapecanvas;

import java.awt.Dimension;
import java.awt.GridLayout;
//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InspectorPanel extends JPanel {
	private InspectorActionListener iListen;
	private NamedRectangle selected;
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;
	
	public InspectorPanel(InspectorActionListener iListen) {
		this.iListen = iListen;
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new GridLayout(5, 1));
		
		JLabel xLabel = new JLabel("x: ");
		xField = new JTextField(5);
		xField.setActionCommand("x");
		xField.setName("x");
		xField.addActionListener(iListen);
		xField.addFocusListener(iListen);
		
		JLabel yLabel = new JLabel("y: ");
		yField = new JTextField(5);
		yField.setActionCommand("y");
		yField.setName("y");
		yField.addActionListener(iListen);
		yField.addFocusListener(iListen);
		
		JLabel widthLabel = new JLabel("width: ");
		widthField = new JTextField(5);
		widthField.setActionCommand("width");
		widthField.setName("width");
		widthField.addActionListener(iListen);
		widthField.addFocusListener(iListen);
		
		JLabel heightLabel = new JLabel("height: ");
		heightField = new JTextField(5);
		heightField.setActionCommand("height");
		heightField.setName("height");
		heightField.addActionListener(iListen);
		heightField.addFocusListener(iListen);
		
		JPanel first = new JPanel();
		first.add(xLabel);
		first.add(xField);
		
		JPanel second = new JPanel();
		second.add(yLabel);
		second.add(yField);
		
		JPanel third = new JPanel();
		third.add(widthLabel);
		third.add(widthField);
		
		JPanel fourth = new JPanel();
		fourth.add(heightLabel);
		fourth.add(heightField);
		
		add(first);
		add(second);
		add(third);
		add(fourth);
	}
	
	public void setSelected(NamedRectangle r) {		
		selected = r;
		if(selected == null ) {
			xField.setText("");
			yField.setText("");
			widthField.setText("");
			heightField.setText("");
		} else {
			xField.setText(String.valueOf(selected.getRectangle().getX()));
			yField.setText(String.valueOf(selected.getRectangle().getY()));
			Dimension size = selected.getRectangle().getSize();
			
			widthField.setText(String.valueOf(size.width));
			heightField.setText(String.valueOf(size.height));
		}
	}
}