package shapecanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

public class ShapeCanvasPanel extends JPanel {
	private final Color TRANSPARENT = new Color(0, 0, 0, 0);
	private final Color YELLOW = new Color(250, 241, 37);
	private final Color CYAN = new Color(48, 215, 252);
	private final Color GREEN = new Color(97, 218, 55);
	private final Color ORANGE = new Color(255, 126, 54);
	private final Color PINK = new Color(239, 54, 188);
	private final Color RED = new Color(241, 0, 0);
	private final Color WHITE = new Color(255, 255, 255);
	private final Color GRAY = new Color(217, 217, 217);
	private final Color BLACK = new Color(0, 0, 0);
	private final Color BKGD = new Color(210, 226, 243);
	private ArrayList<Color> colors = new ArrayList<>();
	private Color defaultFillColor = GRAY;
	private Color defaultStrokeColor = BLACK;
	private float defaultStrokeWeight = 1.0f;
	private ToolPanel t;
	private DrawingPanel d;
	private InspectorPanel i;
	private ListPanel l;
	
	public ShapeCanvasPanel() {
		initColors();
		
		d = new DrawingPanel(defaultFillColor, defaultStrokeColor, defaultStrokeWeight);
		i = new InspectorPanel(colors);
		t = new ToolPanel(colors, defaultFillColor, defaultStrokeColor);
		l = new ListPanel();
		
		ColorComboActionListener cListen = new ColorComboActionListener(colors, d);
		InspectorActionListener iListen = new InspectorActionListener(d);
		ToolListener tListen = new ToolListener(d, i, l);
		ListListener lListen = new ListListener(d, i, t);
		DrawingListener dListen = new DrawingListener(i, l, d, t);
		ListMouseAdapter lm = new ListMouseAdapter(l, d);
		ListActionListener lAction = new ListActionListener(l, d);
		
		i.addListeners(iListen, cListen);
		t.addListeners(tListen, cListen);
		l.addListeners(lListen, lm, lAction);
		d.addListeners(dListen);
		
		createAndShowGui();
	}
	
	public void createAndShowGui() {
		this.setLayout(new BorderLayout());
		this.add(t, BorderLayout.NORTH);
		this.add(d, BorderLayout.CENTER);
		
		JPanel sidePanel = new JPanel(new GridLayout(2, 1));
		sidePanel.setBackground(BKGD);
		i.setOpaque(false);
		l.setOpaque(false);
		sidePanel.add(i);
		sidePanel.add(l);
		
		this.add(sidePanel, BorderLayout.EAST);	
	}
	
	public void initColors() {
		colors.add(TRANSPARENT);
		colors.add(YELLOW);
		colors.add(CYAN);
		colors.add(GREEN);
		colors.add(ORANGE);
		colors.add(PINK);
		colors.add(RED);
		colors.add(WHITE);
		colors.add(GRAY);
		colors.add(BLACK);
//		colors.add(new Color(217, 217, 217));
//		colors.add(Color.BLUE);
	}
	
	public LinkedList<NamedShape> getShapeState(){
		return d.getShapes();
	}
	
	public void setShapeState(LinkedList<NamedShape> rects) {
		d.setShapes(rects);
		l.updateList(rects, null);
	}
	
	public DrawingPanel getDrawingPanel() {
		return d;
	}
}