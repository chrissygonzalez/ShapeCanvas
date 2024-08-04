package shapecanvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class InspectorPanel extends JPanel {
	private InspectorActionListener iListen;
	private NamedRectangle selected;
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField strokeWidthField;
	private ColorComboBox strokeColor;
	private ColorComboBox fillColor;
	private ArrayList<Color> colors;
	private ColorComboActionListener cListen;
	
	public InspectorPanel(InspectorActionListener iListen, ArrayList<Color> colors, ColorComboActionListener cListen) {
		this.colors = colors;
		this.iListen = iListen;
		this.cListen = cListen;
		
		Border blackline = BorderFactory.createTitledBorder("Selected shape");
		setBorder(blackline);
		
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new GridLayout(7, 1));
		
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
		
		JLabel strokeWidthLabel = new JLabel("stroke width: ");
		strokeWidthField = new JTextField(5);
		strokeWidthField.setActionCommand("strokeWidth");
		strokeWidthField.setName("strokeWidth");
		strokeWidthField.addActionListener(iListen);
		strokeWidthField.addFocusListener(iListen);
		
		JLabel strokeColorLabel = new JLabel("stroke color: ");
		strokeColor = new ColorComboBox(colors, cListen, "strokeColor");
		
		JLabel fillColorLabel = new JLabel("fill color: ");
		fillColor = new ColorComboBox(colors, cListen, "fillColor");
		
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
		
		JPanel fifth = new JPanel();
		fifth.add(strokeWidthLabel);
		fifth.add(strokeWidthField);
		
		JPanel sixth = new JPanel();
		sixth.add(strokeColorLabel);
		sixth.add(strokeColor);
		
		JPanel seventh = new JPanel();
		seventh.add(fillColorLabel);
		seventh.add(fillColor);
		
		add(first);
		add(second);
		add(third);
		add(fourth);
		add(fifth);
		add(sixth);
		add(seventh);
	}
	
	public void setSelected(NamedRectangle r) {		
		selected = r;
		if(selected == null) {
			xField.setText("");
			yField.setText("");
			widthField.setText("");
			heightField.setText("");
			strokeWidthField.setText("");
			strokeColor.setSelectedColor(Color.BLACK);
			fillColor.setSelectedColor(Color.BLACK);
		} else {
			xField.setText(String.valueOf(selected.getRectangle().getX()));
			yField.setText(String.valueOf(selected.getRectangle().getY()));
			Dimension size = selected.getRectangle().getSize();
			
			widthField.setText(String.valueOf(size.width));
			heightField.setText(String.valueOf(size.height));
			strokeWidthField.setText(String.valueOf(selected.getStrokeWidth()));
			strokeColor.setSelectedColor(selected.getStrokeColorValue());
			fillColor.setSelectedColor(selected.getFillColor());
		}
	}
}