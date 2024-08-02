package shapecanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class DrawingListener extends MouseAdapter {
	private InspectorPanel inspect;
	private ListPanel list;
	private DrawingPanel drawP;
	private NamedRectangle selected;
	
	public DrawingListener(InspectorPanel i, ListPanel l, DrawingPanel d) {
		this.inspect = i;
		this.list = l;
		this.drawP = d;
	}
	
	public void mousePressed(MouseEvent e){
		drawP.setNewStartPoint(e.getPoint());
		
		// handle selection
		selected = drawP.getSelectedShape();
		inspect.setSelected(selected);
	}
	
	public void mouseDragged(MouseEvent e){
		drawP.expandShape(e);
	}
	
	public void mouseReleased(MouseEvent e){
		NamedRectangle temp = drawP.completeShape();
		list.updateList(drawP.getShapes(), temp != null ? temp : selected);
	}
}