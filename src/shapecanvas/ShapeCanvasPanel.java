package shapecanvas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ShapeCanvasPanel extends JPanel {
	public ShapeCanvasPanel() {
		DrawingPanel d = new DrawingPanel();
		
		InspectorActionListener iListen = new InspectorActionListener(d);
		InspectorPanel i = new InspectorPanel(iListen);
		
		ListListener lListen = new ListListener(d, i);
		ListPanel l = new ListPanel(lListen);
		
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