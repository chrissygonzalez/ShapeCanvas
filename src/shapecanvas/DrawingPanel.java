package shapecanvas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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
	private String drawingShape;
	private int startWidth = -1;
	private int startHeight = -1;
	private int startX = -1;
	private int startY = -1;
	
	public DrawingPanel(Color fill, Color stroke, float strokeWeight) {
		setPanelCursor("draw");
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
	
	public void setPanelCursor(String mode) {
		if(mode.equals("draw")) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void setDrawingShape(String s) {
		drawingShape = s;
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
	
	public int getSelectedHandleIndex() {
		return rModel.getSelectedHandleIndex();
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
		if(rModel.getSelected() != null && rModel.getSelectedHandleIndex() < 0) {
			Rectangle r = (Rectangle)rModel.getSelected().getShape();
			r.setLocation(e.getX() + prevX, e.getY() + prevY);
			rModel.getSelected().setHandles();
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
	
	public void resetDimensions() {
		startWidth = -1;
	}
	
	public void expandSelectedShape(MouseEvent e) {
		NamedShape selectedShape = rModel.getSelected();
		int selectedHandleIndex = rModel.getSelectedHandleIndex();
		Rectangle bounds = selectedShape.getShape();
		if(startWidth < 0) {
			startWidth = bounds.width;
			startX = bounds.x;
			startHeight = bounds.height;
			startY = bounds.y;
		}
		
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		
		if(selectedHandleIndex == 0 || selectedHandleIndex == 2) {
			x = e.getX();
			width = startWidth - (x - startX);
		}
		if(selectedHandleIndex == 1 || selectedHandleIndex == 3) {
			int newX = e.getX();
			x = Math.min(startX, newX);
			int rightEdge = Math.max(startX, newX);

			width = rightEdge - x;
		}
		
		if(selectedHandleIndex == 0 || selectedHandleIndex == 1) {
			y = e.getY();
			height = startHeight - (y - startY);
		}
		if(selectedHandleIndex == 2 || selectedHandleIndex == 3) {
			int newY = e.getY();
			y = Math.min(startY, newY);
			int bottomEdge = Math.max(startY, newY);

			height = bottomEdge - y;
		}
		
		bounds.setBounds(x, y, width, height);
		
		repaint();
	}
	
	public NamedShape completeShape() {
		NamedShape temp = null;
		if (shape.width != 0 || shape.height != 0){
			if(drawingShape.equals("circle")) {
				temp = new NamedCircle(defaultStrokeColor, defaultFillColor, defaultStrokeWidth, shape);
			} else if(drawingShape.equals("triangle")){
				temp = new NamedTriangle(defaultStrokeColor, defaultFillColor, defaultStrokeWidth, shape);
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
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rModel.drawAll(g);
		if (shape != null){
			if(drawingShape.equals("circle")) {
				NamedCircle.drawCircle(g2d, shape, defaultStrokeColor, defaultFillColor, defaultStrokeWidth);
			} else if(drawingShape.equals("triangle")) {
				NamedTriangle.drawTriangle(g2d, shape, defaultStrokeColor, defaultFillColor, defaultStrokeWidth);
			} else {
				NamedRectangle.drawRectangle(g2d, shape, defaultStrokeColor, defaultFillColor, defaultStrokeWidth);
			}
		}
		g2d.dispose();
	}
}