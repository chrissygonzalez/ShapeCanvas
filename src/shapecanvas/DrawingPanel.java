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
	private ShapeModel rModel;
	private Rectangle shape;
	private Point startPoint;
	private Color defaultStrokeColor;
	private Color defaultFillColor;
	private float defaultStrokeWidth;
	private int prevX;
	private int prevY;
	// TODO: set this with the UI
	private boolean drawingCircles = false;
	
	public DrawingPanel(Color fill, Color stroke, float strokeWeight) {
		this.rModel = new ShapeModel();
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
	
	public void setDrawingCircles(boolean c) {
		drawingCircles = c;
	}
	
	public void setNewStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		this.shape = new Rectangle();
	}
	
	public void setStrokeWeight(float value) {
		defaultStrokeWidth = value;
	}
	
	public LinkedList<NamedShape> getShapes(){
		return rModel.getShapes();
	}
	
	public void setShapes(LinkedList<NamedShape> rects) {
		rModel.setShapes(rects);
		repaint();
	}
	
	public void setUpdatedShapes(DefaultListModel<NamedShape> listModel, NamedShape r) {
		LinkedList<NamedShape> updated = new LinkedList<>();
		for (Enumeration<NamedShape> e = listModel.elements(); e.hasMoreElements();)
		       updated.add(e.nextElement());
		
		rModel.setShapes(updated);
		rModel.setSelected(r);
		repaint();
	}
	
	public NamedShape getSelectedShape() {		
		return rModel.checkIfSelected(startPoint);
	}
	
	public void setSelectedShape(NamedShape r) {
		rModel.setSelected(r);
		repaint();
	}
	
	public void setMoveShapePreviousPoint(MouseEvent e) {
		if(rModel.getSelected() != null) {
			Rectangle r = (Rectangle)rModel.getSelected().getShape();
			prevX = r.x - e.getX();
			prevY = r.y - e.getY();
		}
	}
	
	public void moveShape(MouseEvent e) {
		if(rModel.getSelected() != null) {
			Rectangle r = (Rectangle)rModel.getSelected().getShape();
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
	
	public NamedShape completeShape() {
		NamedShape temp = null;
		if (shape.width != 0 || shape.height != 0){
			if(drawingCircles) {
				temp = new NamedCircle(defaultStrokeColor, defaultFillColor, defaultStrokeWidth, shape);
			} else {
				temp = new NamedRectangle(defaultStrokeColor, defaultFillColor, defaultStrokeWidth, shape);
			}
			rModel.addShape(temp);
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
		rModel.updateShapeStrokeWeight(value);
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
		rModel.updateShapeColors(property, value);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		rModel.drawAll(g);
		
		if (shape != null){
			if(drawingCircles) {
				g2d.setStroke(new BasicStroke(defaultStrokeWidth));
				g2d.setColor(defaultFillColor);
				g2d.fillOval(shape.x, shape.y, shape.width, shape.height);
				g2d.setColor(defaultStrokeColor);
				g2d.drawOval(shape.x, shape.y, shape.width, shape.height);
			} else {
				g2d.setStroke(new BasicStroke(defaultStrokeWidth));
				g2d.setColor(defaultFillColor);
				g2d.fill(shape);
				g2d.setColor(defaultStrokeColor);
				g2d.draw( shape );
			}
		}
	}
}