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
	private final Color BKGD = new Color(150, 150, 150);
	private ArrayList<Color> colors;
	private ButtonGroup toolGroup;
	private JToggleButton selectBtn;
	private JToggleButton drawBtn;
	private JToggleButton drawCircleBtn;
	private JTextField strokeWeight;
	private ColorComboBox strokeColor;
	private ColorComboBox fillColor;
	
	public ToolPanel(ArrayList<Color> colors) {
		this.colors = colors;
		this.setBackground(BKGD);
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new BorderLayout());
		toolGroup = new ButtonGroup();
		selectBtn = new JToggleButton("Select");
		selectBtn.setActionCommand("select");

		drawBtn = new JToggleButton("Draw Rectangle");
		drawBtn.setActionCommand("draw");
		drawBtn.setSelected(true);
		
		drawCircleBtn = new JToggleButton("Draw Circle");
		drawCircleBtn.setActionCommand("drawCircle");
		drawCircleBtn.setSelected(true);
		
		toolGroup.add(drawBtn);
		toolGroup.add(selectBtn);
		toolGroup.add(drawCircleBtn);;
		
		JPanel toolBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolBtns.setOpaque(false);
		toolBtns.add(selectBtn);
		toolBtns.add(drawBtn);
		toolBtns.add(drawCircleBtn);
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
		
		JPanel drawingDefaults = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		drawingDefaults.setOpaque(false);
		drawingDefaults.add(strokeWeightLabel);
		drawingDefaults.add(strokeWeight);
		drawingDefaults.add(strokeColorLabel);
		drawingDefaults.add(strokeColor);
		drawingDefaults.add(fillColorLabel);
		drawingDefaults.add(fillColor);
		
		add(drawingDefaults, BorderLayout.EAST);
	}
	
	public void addListeners(ToolListener tListen, ColorComboActionListener cListen) {
		selectBtn.addActionListener(tListen);
		drawBtn.addActionListener(tListen);
		drawCircleBtn.addActionListener(tListen);
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
		} else if(mode.equals("drawCircle")) {
			drawCircleBtn.setSelected(true);
		} else {
			selectBtn.setSelected(true);
		}
	}
}