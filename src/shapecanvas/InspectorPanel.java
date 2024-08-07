package shapecanvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class InspectorPanel extends JPanel {
	private NamedShape selected;
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField strokeWidthField;
	private ColorComboBox strokeColor;
	private ColorComboBox fillColor;
	private ArrayList<Color> colors;
	
	public InspectorPanel(ArrayList<Color> colors) {
		this.colors = colors;
		createAndShowGui();
	}
	
	public void addListeners(InspectorActionListener iListen, ColorComboActionListener cListen) {
		xField.addActionListener(iListen);
		xField.addFocusListener(iListen);
		yField.addActionListener(iListen);
		yField.addFocusListener(iListen);
		widthField.addActionListener(iListen);
		widthField.addFocusListener(iListen);
		heightField.addActionListener(iListen);
		heightField.addFocusListener(iListen);
		strokeWidthField.addActionListener(iListen);
		strokeWidthField.addFocusListener(iListen);
		strokeColor.addListener(cListen);
		fillColor.addListener(cListen);
	}
	
	public void createAndShowGui() {
		Border blackline = BorderFactory.createTitledBorder("Selected shape");
		setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  blackline));
		
		this.setLayout(new GridLayout(7, 2));
		
		JLabel xLabel = new JLabel("x:");
		xField = new JTextField(5);
		xField.setActionCommand("x");
		xField.setName("x");

		JLabel yLabel = new JLabel("y:");
		yField = new JTextField(5);
		yField.setActionCommand("y");
		yField.setName("y");
		
		JLabel widthLabel = new JLabel("width:");
		widthField = new JTextField(5);
		widthField.setActionCommand("width");
		widthField.setName("width");
		
		JLabel heightLabel = new JLabel("height:");
		heightField = new JTextField(5);
		heightField.setActionCommand("height");
		heightField.setName("height");
		
		JLabel strokeWidthLabel = new JLabel("stroke weight:");
		strokeWidthField = new JTextField(5);
		strokeWidthField.setActionCommand("strokeWidth");
		strokeWidthField.setName("strokeWidth");
		
		JLabel strokeColorLabel = new JLabel("stroke color:");
		strokeColor = new ColorComboBox(colors, "strokeColor");
		
		JLabel fillColorLabel = new JLabel("fill color:");
		fillColor = new ColorComboBox(colors, "fillColor");

		add(xLabel);
		add(xField);
		add(yLabel);
		add(yField);
		add(widthLabel);
		add(widthField);
		add(heightLabel);
		add(heightField);
		add(strokeWidthLabel);
		add(strokeWidthField);
		add(strokeColorLabel);
		add(strokeColor);
		add(fillColorLabel);
		add(fillColor);
	}
	
	public void setSelected(NamedShape r) {		
		selected = r;
		if(selected == null) {
			xField.setText("");
			yField.setText("");
			widthField.setText("");
			heightField.setText("");
			strokeWidthField.setText("");
			strokeColor.setSelectedIndex(0);
			fillColor.setSelectedIndex(0);
		} else {
			// TODO; make work for different shape types
			Rectangle rect = (Rectangle)selected.getShape();
			xField.setText(String.valueOf(rect.getX()));
			yField.setText(String.valueOf(rect.getY()));
			Dimension size = rect.getSize();
			
			widthField.setText(String.valueOf(size.width));
			heightField.setText(String.valueOf(size.height));
			strokeWidthField.setText(String.valueOf(selected.getStrokeWidth()));
			strokeColor.setSelectedColor(selected.getStrokeColorValue());
			fillColor.setSelectedColor(selected.getFillColor());
		}
	}
}