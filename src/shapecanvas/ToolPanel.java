package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class ToolPanel extends JPanel {
	private ArrayList<Color> colors;
	private ButtonGroup toolGroup;
	private JToggleButton selectBtn;
	private JToggleButton drawBtn;
	private JTextField strokeWeight;
	private ColorComboBox strokeColor;
	private ColorComboBox fillColor;
	
	public ToolPanel(ArrayList<Color> colors) {
		this.colors = colors;
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new BorderLayout());
		toolGroup = new ButtonGroup();
		selectBtn = new JToggleButton("Select");
		selectBtn.setActionCommand("select");

		
		drawBtn = new JToggleButton("Draw");

		drawBtn.setActionCommand("draw");
		drawBtn.setSelected(true);
		
		toolGroup.add(drawBtn);
		toolGroup.add(selectBtn);
		
		JPanel toolBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolBtns.add(drawBtn);
		toolBtns.add(selectBtn);
		add(toolBtns, BorderLayout.WEST);
		
		JLabel strokeColorLabel = new JLabel("stroke color:");
		strokeColor = new ColorComboBox(colors, "drawStrokeColor");

		strokeColor.setSelectedIndex(3);
		
		JLabel fillColorLabel = new JLabel("fill color:");
		fillColor = new ColorComboBox(colors, "drawFillColor");

		fillColor.setSelectedIndex(9);
		
		JLabel strokeWeightLabel = new JLabel("stroke weight:");
		strokeWeight = new JTextField(3);
		strokeWeight.setActionCommand("stroke weight");

		strokeWeight.setText("1.0");
		
		JPanel drawingColors = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		drawingColors.add(strokeColorLabel);
		drawingColors.add(strokeColor);
		drawingColors.add(fillColorLabel);
		drawingColors.add(fillColor);
		drawingColors.add(strokeWeightLabel);
		drawingColors.add(strokeWeight);
		
		add(drawingColors, BorderLayout.EAST);
	}
	
	public void addListeners(ToolListener tListen, ColorComboActionListener cListen) {
		selectBtn.addActionListener(tListen);
		drawBtn.addActionListener(tListen);
		strokeColor.addListener(cListen);
		fillColor.addListener(cListen);
		strokeWeight.addActionListener(tListen);
	}
	
	public String getToolMode() {
		return toolGroup.getSelection().getActionCommand();
	}
	
	public void setToolMode(String mode) {
		if(mode.equals("draw")) {
			drawBtn.setSelected(true);
		} else {
			selectBtn.setSelected(true);
		}
	}
}