package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ToolPanel extends JPanel {
	private final Color BKGD = new Color(210, 226, 243);
	private final Color HIGHLIGHT = Color.WHITE;
	private final Color SHADOW = new Color(135, 187, 249);
	private ArrayList<Color> colors;
	private ButtonGroup toolGroup;
	private ToolButton selectBtn;
	private ToolButton drawBtn;
	private ToolButton drawCircleBtn;
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
		
		JPanel sPanel = new JPanel();
		JLabel sLabel = new JLabel("Select");
	    sPanel.add(sLabel);
		sPanel.setOpaque(false);
		Border etched = BorderFactory.createEtchedBorder(HIGHLIGHT, SHADOW);
		sPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  etched));
		selectBtn = new ToolButton("select", "arrow.png", false);
		sPanel.add(selectBtn);
		
		JPanel dPanel = new JPanel();
		JLabel dLabel = new JLabel("Draw");
	    dPanel.add(dLabel);
		dPanel.setOpaque(false);
		dPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),  etched));
		drawBtn = new ToolButton("draw", "rectangle.png", true);
		drawCircleBtn = new ToolButton("drawCircle", "circle.png", false);
		dPanel.add(drawBtn);
		dPanel.add(drawCircleBtn);
		
		toolGroup.add(drawBtn);
		toolGroup.add(selectBtn);
		toolGroup.add(drawCircleBtn);;
		
		JPanel toolBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolBtns.setOpaque(false);
		toolBtns.add(sPanel);
		toolBtns.add(dPanel);
		add(toolBtns, BorderLayout.WEST);
		
		JLabel strokeColorLabel = new JLabel("Stroke color:");
		strokeColor = new ColorComboBox(colors, "drawStrokeColor");
		strokeColor.setSelectedIndex(3);
		
		JLabel fillColorLabel = new JLabel("Fill color:");
		fillColor = new ColorComboBox(colors, "drawFillColor");
		fillColor.setSelectedIndex(9);
		
		JLabel strokeWeightLabel = new JLabel("Stroke weight:");
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