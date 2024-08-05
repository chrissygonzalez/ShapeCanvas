package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ShapeCanvasPanel extends JPanel {
	private ArrayList<Color> colors = new ArrayList<>();
	private Color defaultFillColor = Color.LIGHT_GRAY;
	private Color defaultStrokeColor = Color.BLACK;
	private float defaultStrokeWeight = 1.0f;
	private ToolPanel t;
	private DrawingPanel d;
	private InspectorPanel i;
	private ListPanel l;
	
	public ShapeCanvasPanel() {
		initColors();
		
		d = new DrawingPanel(defaultFillColor, defaultStrokeColor, defaultStrokeWeight);
		i = new InspectorPanel(colors);
		t = new ToolPanel(colors);
		l = new ListPanel();
		
		ColorComboActionListener cListen = new ColorComboActionListener(colors, d);
		InspectorActionListener iListen = new InspectorActionListener(d);
		ToolListener tListen = new ToolListener(d, i, l);
		ListListener lListen = new ListListener(d, i, t);
		DrawingListener dListen = new DrawingListener(i, l, d, t);
		
		i.addListeners(iListen, cListen);
		t.addListeners(tListen, cListen);
		l.addListeners(lListen);
		d.addListeners(dListen);
		
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new BorderLayout());
		this.add(t, BorderLayout.NORTH);
		this.add(d, BorderLayout.CENTER);
		
		JPanel sidePanel = new JPanel(new GridLayout(2, 1));
		sidePanel.add(i);
		sidePanel.add(l);
		
		this.add(sidePanel, BorderLayout.EAST);	
	}
	
	public void initColors() {
		colors.add(new Color(0, 0, 0, 0));
		colors.add(new Color(253, 246, 96));
		colors.add(new Color(151, 222, 237));
		colors.add(Color.BLACK);
		colors.add(Color.WHITE);
		colors.add(Color.RED);
		colors.add(Color.YELLOW);
		colors.add(Color.CYAN);
		colors.add(Color.GREEN);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.BLUE);
	}
	
	
}