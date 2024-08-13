package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class NamedCircle extends NamedShape implements Serializable {
	static int count = 0;
	private String name;
	
	public NamedCircle(Color sColor, Color fColor, float strokeWidth, Rectangle r) {
		super(sColor, fColor, strokeWidth, r);
		this.name = "Circle " + count++;
	}
	
	public NamedCircle(NamedCircle c) {
		super(c);
		this.name = "Circle " + count++;
	}
	
	public void draw(Graphics g) {
		Rectangle shape = super.getShape();
		
		if(shape.height < 0) {
			int newHeight = shape.height * -1;
			int newY = shape.y - newHeight;
			shape.setBounds(shape.x, newY, shape.width, newHeight);
		}
		
		if(shape.width < 0) {
			int newWidth = shape.width * -1;
			int newX = shape.x - newWidth;
			shape.setBounds(newX, shape.y, newWidth, shape.height);
		}
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(super.getStrokeWidth()));
		g2d.setColor(super.getFillColor());
		g2d.fillOval(shape.x, shape.y, shape.width, shape.height);
		g2d.setColor(super.getStrokeColor());
		g2d.drawOval(shape.x, shape.y, shape.width, shape.height);
		
		if(super.getSelected()) {
			super.drawHandles(g2d);
		}
	}
	
	public static void drawCircle(Graphics2D g2d, Rectangle rect, Color sColor, Color fColor, float strokeWidth) {
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(fColor);
		g2d.fillOval(rect.x, rect.y, rect.width, rect.height);
		g2d.setColor(sColor);
		g2d.drawOval(rect.x, rect.y, rect.width, rect.height);
	}
		
	@Override
	public String toString() {
		return this.name;
	}
	
	public boolean equals(NamedCircle r) {
		if(r == null) return false;
		return this.name == r.name;
	}
}