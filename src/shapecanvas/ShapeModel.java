package shapecanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.LinkedList;

public class ShapeModel {
	private LinkedList<NamedShape> shapes;
	private NamedShape selected;
	
	public ShapeModel() {
		shapes = new LinkedList<NamedShape>();
	}
	
	public void addShape(Color strokeColor, Color fillColor, float strokeWidth, Shape shape) {
		// TODO: add type of shape based on tool selection
		NamedShape s = new NamedRectangle(strokeColor, fillColor, strokeWidth, (Rectangle)shape);
		shapes.add(0, s);
	}
	
	public void addShape(NamedShape s) {
		shapes.add(0, s);
	}
	
	public void updateShape(NamedShape s, int x, int y, int width, int height) {
		// TODO: make work for all types of shapes
		Rectangle r = (Rectangle)s.getShape();
		r.setBounds(x, y, width, height);
	}
	
	public void updateShapeStrokeWeight(float value) {
		selected.setStrokeWidth(value);
	}
	
	public void updateShapeColors(String property, Color value) {
		if(selected == null) return;
		
		if(property.equals("strokeColor")) {
			selected.setStrokeColor(value);
		} else  {
			selected.setFillColor(value);
		}
	}
	
	public void modifyShape(String name, int value) {
		// bounds are a rectangle, no matter the shape that's selected
		Rectangle bounds = selected.getShape().getBounds();
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
		
		// TODO: make work for all types of shapes
		Rectangle r = (Rectangle)selected.getShape();
		r.setBounds(x, y, width, height);
	}
	
	public NamedShape checkIfSelected(Point startPoint) {
		NamedShape currSelected = null;
		
		for(int i = 0; i < shapes.size(); i++) {
			NamedShape r = shapes.get(i);
			if(r.getShape().contains(startPoint) && currSelected == null) {
				r.setSelected(true);
				currSelected = r;
			} else {
				r.setSelected(false);
			}
		}
		
		setSelected(currSelected);
		return currSelected;
	}
	
	public void setSelected(NamedShape r) {
		if(selected != null && !selected.equals(r)) {
			selected.setSelected(false);
		}

		this.selected = r;
		if(r != null) {
			selected.setSelected(true);
		}
	}
	
	public NamedShape getSelected() {
		return this.selected;
	}
	
	public LinkedList<NamedShape> getShapes(){
		return shapes;
	}
	
	public void setShapes(LinkedList<NamedShape> s) {
		shapes = s;
	}
	
	public void drawAll(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i = shapes.size() - 1; i >= 0; i--) {
			shapes.get(i).draw(g2d);
		}
	}
}
