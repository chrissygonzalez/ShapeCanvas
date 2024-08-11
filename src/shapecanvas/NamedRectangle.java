package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class NamedRectangle extends NamedShape implements Serializable {
	static int count = 0;
	private String name;
	
	public NamedRectangle(Color sColor, Color fColor, float strokeWidth, Rectangle r) {
		super(sColor, fColor, strokeWidth, r);
		this.name = "Rectangle " + count++;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(super.getStrokeWidth()));
		g2d.setColor(super.getFillColor());
		g2d.fill(super.getShape());
		g2d.setColor(super.getStrokeColor());
		g2d.draw(super.getShape());
	}
	
	public static void drawRectangle(Graphics2D g2d, Rectangle rect, Color sColor, Color fColor, float strokeWidth) {
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(fColor);
		g2d.fill(rect);
		g2d.setColor(sColor);
		g2d.draw(rect);
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