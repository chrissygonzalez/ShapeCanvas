package shapecanvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class NamedTriangle extends NamedShape {
	static int count = 0;
	private String name;
	
	public NamedTriangle(Color sColor, Color fColor, float strokeWidth, Rectangle r) {
		super(sColor, fColor, strokeWidth, r);
		this.name = "Triangle " + count++;
	}
	
	public void draw(Graphics g) {
		Rectangle shape = super.getShape();
		Graphics2D g2d = (Graphics2D)g;
		
		int x1 = shape.x;
		int x3 = shape.x + shape.width;
		int x2 = ((x3 - x1)/ 2) + x1;
		int[] xs = {x1, x2, x3};

		int y1 = shape.y;
		int y2 = y1 + shape.height;
		int[] ys = {y2, y1, y2};
		
		g2d.setStroke(new BasicStroke(super.getStrokeWidth()));
		g2d.setColor(super.getFillColor());
		g2d.fillPolygon(xs, ys, 3);
		g2d.setColor(super.getStrokeColor());
		g2d.drawPolygon(xs, ys, 3);
	}
	
	public static void drawTriangle(Graphics2D g2d, Rectangle rect, Color sColor, Color fColor, float strokeWidth) {
		int x1 = rect.x;
		int x3 = rect.x + rect.width;
		int x2 = ((x3 - x1)/ 2) + x1;
		int[] xs = {x1, x2, x3};

		int y1 = rect.y;
		int y2 = y1 + rect.height;
		int[] ys = {y2, y1, y2};
		
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(fColor);
		g2d.fillPolygon(xs, ys, 3);
		g2d.setColor(sColor);
		g2d.drawPolygon(xs, ys, 3);
	}
		
	@Override
	public String toString() {
		return this.name;
	}
	
	public boolean equals(NamedTriangle r) {
		if(r == null) return false;
		return this.name == r.name;
	}
}