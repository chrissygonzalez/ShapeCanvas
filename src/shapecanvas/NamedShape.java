package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

abstract class NamedShape implements Serializable {
	final static Color selectedColor = Color.BLUE;
	static int count = 0;
	private Rectangle shape;
	private Color strokeColor;
	private Color fillColor;
	private float strokeWidth;
	private boolean selected;
	private Rectangle handle1;
	private Rectangle handle2;
	private Rectangle handle3;
	private Rectangle handle4;
	
	public NamedShape(Color sColor, Color fColor, float strokeWidth, Rectangle s) {
		this.strokeColor = sColor;
		this.fillColor = fColor;
		this.strokeWidth = strokeWidth;
		this.shape = s;
		this.selected = false;
	}
	
	public NamedShape(NamedShape s) {
		Rectangle copied = new Rectangle(s.getShape());
		Rectangle bounds = copied.getBounds();
		copied.setBounds(bounds.x + 15, bounds.y - 15, bounds.width, bounds.height);
		
		this.strokeColor = s.getStrokeColor();
		this.fillColor = s.getFillColor();
		this.strokeWidth = s.getStrokeWidth();
		this.shape = copied;
		this.selected = false;
	}
	
	abstract public void draw(Graphics g);
	
	public void setStrokeColor(Color c) {
		this.strokeColor = c;
	}
	
	public Color getStrokeColor() {
		return this.strokeColor;
	}
	
	public Color getStrokeColorValue() {
		return this.strokeColor;
	}
	
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public float getStrokeWidth() {
		return this.strokeWidth;
	}
	
	public void setStrokeWidth(float s) {
		this.strokeWidth = s;
	}
	
	public Rectangle getShape() {
		return this.shape;
	}
	
	public void setSelected(boolean s) {
		this.selected = s;
		setHandles();
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	
	public void setHandles() {
		if(!this.selected) {
			handle1 = null;
			handle2 = null;
			handle3 = null;
			handle4 = null;
		} else {
			Rectangle shape = getShape();
			int x1 = shape.x - 4;
			int y1 = shape.y - 4;
				
			int x2 = shape.x + shape.width - 4;
			int y2 = shape.y - 4;
				
			int x3 = shape.x - 4;
			int y3 = shape.y + shape.height - 4;
				
			int x4 = shape.x + shape.width - 4;
			int y4 = shape.y + shape.height - 4;

			handle1 = new Rectangle(x1, y1, 8, 8);
			handle2 = new Rectangle(x2, y2, 8, 8);
			handle3 = new Rectangle(x3, y3, 8, 8);
			handle4 = new Rectangle(x4, y4, 8, 8);
		}
	}
	
	public void drawHandles(Graphics2D g2d) {	
		setHandles();
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.BLUE);
		g2d.draw(getShape());
		g2d.fill(handle1);
		g2d.fill(handle2);
		g2d.fill(handle3);
		g2d.fill(handle4);
	}
	
	public Rectangle[] getHandles() {
		Rectangle[] rects = new Rectangle[]{handle1, handle2, handle3, handle4};
		return rects;
	}
}