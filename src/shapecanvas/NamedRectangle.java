package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class NamedRectangle extends Rectangle implements Serializable {
	final static Color selectedColor = Color.BLUE;
	static int count = 0;
	private Rectangle rect;
	private String name;
	private Color strokeColor;
	private Color fillColor;
	private float strokeWidth;
	private boolean selected;
	
	public NamedRectangle(Color sColor, Color fColor, float strokeWidth, Rectangle r) {
		this.name = "Rectangle " + count++;
		this.strokeColor = sColor;
		this.fillColor = fColor;
		this.strokeWidth = strokeWidth;
		this.rect = r;
		this.selected = false;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(fillColor);
		g2d.fill(rect);
		g2d.setColor(getStrokeColor());
		g2d.draw(rect);
	}
	
	public void setStrokeColor(Color c) {
		this.strokeColor = c;
	}
	
	public Color getStrokeColor() {
		if(this.selected) return selectedColor;
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
	
	public Rectangle getRectangle() {
		return this.rect;
	}
	
	public void setSelected(boolean s) {
		this.selected = s;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public boolean equals(NamedRectangle r) {
		if(r == null) return false;
		return this.name == r.name;
	}
}