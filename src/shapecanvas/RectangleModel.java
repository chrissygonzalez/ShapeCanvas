package shapecanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class RectangleModel {
	private LinkedList<NamedRectangle> rectangles;
	private NamedRectangle selected;
	
	public RectangleModel() {
		rectangles = new LinkedList<NamedRectangle>();
	}
	
	public void addRectangle(Color strokeColor, Color fillColor, float strokeWidth, Rectangle rect) {
		NamedRectangle r = new NamedRectangle(strokeColor, fillColor, strokeWidth, rect);
		rectangles.add(0, r);
	}
	
	public void addRectangle(NamedRectangle r) {
		rectangles.add(0, r);
	}
	
	public void updateRectangle(NamedRectangle r, int x, int y, int width, int height) {
		r.getRectangle().setBounds(x, y, width, height);
	}
	
	public void updateRectangleStrokeWeight(float value) {
		selected.setStrokeWidth(value);
	}
	
	public void updateRectangleColors(String property, Color value) {
		if(selected == null) return;
		
		if(property.equals("strokeColor")) {
			selected.setStrokeColor(value);
		} else  {
			selected.setFillColor(value);
		}
	}
	
	public void modifyShape(String name, int value) {
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
	}
	
	public NamedRectangle checkIfSelected(Point startPoint) {
		NamedRectangle currSelected = null;
		
		for(int i = 0; i < rectangles.size(); i++) {
			NamedRectangle r = rectangles.get(i);
			if(r.getRectangle().contains(startPoint) && currSelected == null) {
				r.setSelected(true);
				currSelected = r;
			} else {
				r.setSelected(false);
			}
		}
		
		setSelected(currSelected);
		return currSelected;
	}
	
	public void setSelected(NamedRectangle r) {
		if(selected != null && !selected.equals(r)) {
			selected.setSelected(false);
		}

		this.selected = r;
		if(r != null) {
			selected.setSelected(true);
		}
	}
	
	public NamedRectangle getSelected() {
		return this.selected;
	}
	
	public LinkedList<NamedRectangle> getRectangles(){
		return rectangles;
	}
	
	public void setRectangles(LinkedList<NamedRectangle> rects) {
		rectangles = rects;
	}
	
	public void drawAll(Graphics g) {	
		for(int i = rectangles.size() - 1; i >= 0; i--) {
			rectangles.get(i).draw(g);
		}
	}
}
