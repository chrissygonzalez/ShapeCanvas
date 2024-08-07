package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class NamedRectangle extends NamedShape implements Serializable {
	static int count = 0;
	private Rectangle rect;
	private String name;
	
	public NamedRectangle(Color sColor, Color fColor, float strokeWidth, Rectangle r) {
		super(sColor, fColor, strokeWidth, r);
		this.name = "Rectangle " + count++;
		this.rect = r;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(super.getStrokeWidth()));
		g2d.setColor(super.getFillColor());
		g2d.fill(rect);
		g2d.setColor(super.getStrokeColor());
		g2d.draw(rect);
	}
	
	@Override
	public Rectangle getShape() {
		return this.rect;
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