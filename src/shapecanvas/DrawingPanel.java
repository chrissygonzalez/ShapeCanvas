package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private RectangleModel rModel;
	private Rectangle shape;
	private Point startPoint;
	private Color defaultStrokeColor;
	private Color defaultFillColor;
	private float defaultStrokeWidth;
	private int prevX;
	private int prevY;
	
	public DrawingPanel(Color fill, Color stroke, float strokeWeight) {
		this.rModel = new RectangleModel();
		this.setBackground(Color.WHITE);
		defaultStrokeColor = stroke;
		defaultFillColor = fill;
		defaultStrokeWidth = strokeWeight;
		prevX = 0;
		prevY = 0;
	}
	
	public void addListeners(DrawingListener dListen ) {
		this.addMouseListener(dListen); 
		this.addMouseMotionListener(dListen);
	}
	
	public void setNewStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		this.shape = new Rectangle();
	}
	
	public void setStrokeWeight(float value) {
		defaultStrokeWidth = value;
	}
	
	public LinkedList<NamedRectangle> getShapes(){
		return rModel.getRectangles();
	}
	
	public void setShapes(LinkedList<NamedRectangle> rects) {
		rModel.setRectangles(rects);
		repaint();
	}
	
	public void setUpdatedShapes(DefaultListModel<NamedRectangle> listModel, NamedRectangle r) {
		LinkedList<NamedRectangle> updated = new LinkedList<>();
		for (Enumeration<NamedRectangle> e = listModel.elements(); e.hasMoreElements();)
		       updated.add(e.nextElement());
		
		rModel.setRectangles(updated);
		rModel.setSelected(r);
		repaint();
	}
	
	public NamedRectangle getSelectedShape() {		
		return rModel.checkIfSelected(startPoint);
	}
	
	public void setSelectedShape(NamedRectangle r) {
		rModel.setSelected(r);
		repaint();
	}
	
	public void setMoveShapePreviousPoint(MouseEvent e) {
		if(rModel.getSelected() != null) {
			Rectangle r = rModel.getSelected().getRectangle();
			prevX = r.x - e.getX();
			prevY = r.y - e.getY();
		}
	}
	
	public void moveShape(MouseEvent e) {
		if(rModel.getSelected() != null) {
			Rectangle r = rModel.getSelected().getRectangle();
			r.setLocation(e.getX() + prevX, e.getY() + prevY);
			repaint();
		}
	}
	
	public void expandShape(MouseEvent e) {
		int x = Math.min(startPoint.x, e.getX());
		int y = Math.min(startPoint.y, e.getY());
		int width = Math.abs(startPoint.x - e.getX());
		int height = Math.abs(startPoint.y - e.getY());

		shape.setBounds(x, y, width, height);
		
		repaint();
	}
	
	public NamedRectangle completeShape() {
		NamedRectangle temp = null;
		if (shape.width != 0 || shape.height != 0){
			temp = new NamedRectangle(defaultStrokeColor, defaultFillColor, defaultStrokeWidth, shape);
			rModel.addRectangle(temp);
		}
		shape = null;
		repaint();
		return temp;
	}
	
	public void modifyShape(String name, int value) {
		rModel.modifyShape(name, value);
		repaint();
	}
	
	public void updateStrokeWeight(float value) {
		rModel.updateRectangleStrokeWeight(value);
		repaint();
	}
	
	public void updateDefaultColors(String property, Color value) {
		if(property.equals("drawStrokeColor")) {
			defaultStrokeColor = value;
		} else if (property.equals("drawFillColor")) {
			defaultFillColor = value;
		}
	}
	
	public void modifyShapeColors(String property, Color value) {
		rModel.updateRectangleColors(property, value);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		rModel.drawAll(g);
		
		if (shape != null){
			g2d.setStroke(new BasicStroke(defaultStrokeWidth));
			g2d.setColor(defaultFillColor);
			g2d.fill(shape);
			g2d.setColor(defaultStrokeColor);
			g2d.draw( shape );
		}
	}
}