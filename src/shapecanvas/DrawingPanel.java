package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private RectangleModel rModel;
	private Rectangle shape;
	private Point startPoint;
	// move to another model later?
	// could be more global state, have colors and stroke width and current selection
	private Color defaultStrokeColor;
	private Color defaultFillColor;
	private float defaultStrokeWidth;
	private int prevX;
	private int prevY;
	
	public DrawingPanel() {
		this.rModel = new RectangleModel();
		this.setBackground(Color.WHITE);
		defaultStrokeColor = Color.BLACK;
		defaultFillColor = Color.LIGHT_GRAY;
		defaultStrokeWidth = 3.0f;
		prevX = 0;
		prevY = 0;
	}
	
	public void setNewStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		this.shape = new Rectangle();
	}
	
	public void setSelectedShape(NamedRectangle r) {
		rModel.setSelected(r);
		repaint();
	}
	
	public NamedRectangle getSelectedShape() {		
		ArrayList<NamedRectangle> rects = rModel.getRectangles();
		NamedRectangle currSelected = null;

		// move into rModel?
		for(int i = rects.size() - 1; i >= 0; i--) {
			NamedRectangle r = rects.get(i);
			if(r.getRectangle().contains(startPoint) && currSelected == null) {
				r.setSelected(true);
				currSelected = r;
			} else {
				r.setSelected(false);
			}
		}
		repaint();
		rModel.setSelected(currSelected);
		return rModel.getSelected();
	}
	
	public void expandShape(MouseEvent e) {
		// this updates the primitive rectangle while it's
		// first being drawn
		int x = Math.min(startPoint.x, e.getX());
		int y = Math.min(startPoint.y, e.getY());
		int width = Math.abs(startPoint.x - e.getX());
		int height = Math.abs(startPoint.y - e.getY());

		shape.setBounds(x, y, width, height);
		
		repaint();
	}
	
	public void setMoveShapePreviousPoint(MouseEvent e) {
//		System.out.println("move shape prev point");
		if(rModel.getSelected() != null) {
			Rectangle r = rModel.getSelected().getRectangle();
			prevX = r.x - e.getX();
			prevY = r.y - e.getY();
		}
	}
	
	public void moveShape(MouseEvent e) {
//		System.out.println("move shape");
		if(rModel.getSelected() != null) {
			Rectangle r = rModel.getSelected().getRectangle();
			r.setLocation(e.getX() + prevX, e.getY() + prevY);
			repaint();
		}
	}
	
	// move into rModel
	public void modifyShape(String name, int value) {
		NamedRectangle selected = rModel.getSelected();
		Rectangle bounds = selected.getRectangle().getBounds();
		int x = bounds.x;
		int y = bounds.y;
		int width = bounds.width;
		int height = bounds.height;
		
		switch(name) {
			case "x":
				x = value; break;
			case "y":
				y = value; break;
			case "width":
				width = value; break;
			case "height":
				height = value; break;
		}
		
		selected.getRectangle().setBounds(x, y, width, height);

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
	
	public ArrayList<NamedRectangle> getShapes(){
		return rModel.getRectangles();
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