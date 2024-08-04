package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ShapeCanvasPanel extends JPanel {
	public ArrayList<Color> colors;
	
	public ShapeCanvasPanel() {
		colors = new ArrayList<>();
		initColors();
		
		DrawingPanel d = new DrawingPanel();
		ToolModeListener tModeListen = new ToolModeListener();
		ToolPanel t = new ToolPanel(tModeListen);
		
		ColorComboActionListener cListen = new ColorComboActionListener(colors, d);
		InspectorActionListener iListen = new InspectorActionListener(d);
		InspectorPanel i = new InspectorPanel(iListen, colors, cListen);
		
		ListListener lListen = new ListListener(d, i);
		ListPanel l = new ListPanel(lListen);
		
		DrawingListener dListen = new DrawingListener(i, l, d, t);
		d.addMouseListener(dListen);
		d.addMouseMotionListener(dListen);
		
		this.setLayout(new BorderLayout());
		this.add(t, BorderLayout.NORTH);
		this.add(d, BorderLayout.CENTER);
		
		JPanel sidePanel = new JPanel(new GridLayout(2, 1));
		sidePanel.add(i);
		sidePanel.add(l);
		
		this.add(sidePanel, BorderLayout.EAST);	
	}
	
	public void initColors() {
		colors.add(new Color(253, 246, 96));
		colors.add(new Color(151, 222, 237));
		colors.add(Color.BLACK);
//		colors.add(Color.RED);
//		colors.add(Color.YELLOW);
//		colors.add(Color.CYAN);
//		colors.add(Color.GREEN);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.BLUE);
	}
}