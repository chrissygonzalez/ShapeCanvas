package shapecanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class RectangleModel {
	private ArrayList<NamedRectangle> rectangles;
	private NamedRectangle selected;
	
	public RectangleModel() {
		rectangles = new ArrayList<NamedRectangle>();
	}
	
	public void addRectangle(Color strokeColor, Color fillColor, float strokeWidth, Rectangle rect) {
		NamedRectangle r = new NamedRectangle(strokeColor, fillColor, strokeWidth, rect);
		rectangles.add(r);
	}
	
	public void addRectangle(NamedRectangle r) {
		rectangles.add(r);
	}
	
	public void updateRectangle(NamedRectangle r, int x, int y, int width, int height) {
		r.getRectangle().setBounds(x, y, width, height);
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
	
	public ArrayList<NamedRectangle> getRectangles(){
		return rectangles;
	}
	
	public void drawAll(Graphics g) {
		for(NamedRectangle r : rectangles) {
			r.draw(g);
		}
	}
}
