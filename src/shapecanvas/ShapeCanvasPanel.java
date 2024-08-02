package shapecanvas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ShapeCanvasPanel extends JPanel {
	public ShapeCanvasPanel() {
//		RectangleModel rModel = new RectangleModel();
		DrawingPanel d = new DrawingPanel();
		
		InspectorActionListener iListen = new InspectorActionListener(d);
		InspectorPanel i = new InspectorPanel(iListen);
		ListPanel l = new ListPanel();
		
		DrawingListener dListen = new DrawingListener(i, l, d);
		d.addMouseListener(dListen);
		d.addMouseMotionListener(dListen);
		
		this.setLayout(new BorderLayout());
		this.add(d, BorderLayout.CENTER);
		
		JPanel sidePanel = new JPanel(new GridLayout(2, 1));
		sidePanel.add(i);
		sidePanel.add(l);
		
		this.add(sidePanel, BorderLayout.EAST);	
	}
}