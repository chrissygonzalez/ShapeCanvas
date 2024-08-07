package shapecanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.io.Serializable;

abstract class NamedShape implements Serializable {
	final static Color selectedColor = Color.BLUE;
	static int count = 0;
	private Shape shape;
	private Color strokeColor;
	private Color fillColor;
	private float strokeWidth;
	private boolean selected;
	
	public NamedShape(Color sColor, Color fColor, float strokeWidth, Shape s) {
		this.strokeColor = sColor;
		this.fillColor = fColor;
		this.strokeWidth = strokeWidth;
		this.shape = s;
		this.selected = false;
	}
	
	abstract public void draw(Graphics g);
	
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
	
	public Shape getShape() {
		return this.shape;
	}
	
	public void setSelected(boolean s) {
		this.selected = s;
	}
}