package shapecanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class DrawingListener extends MouseAdapter {
	private InspectorPanel inspect;
	private ListPanel list;
	private DrawingPanel drawP;
	private ToolPanel toolP;
	private NamedShape selected;
	
	public DrawingListener(InspectorPanel i, ListPanel l, DrawingPanel d, ToolPanel t) {
		this.inspect = i;
		this.list = l;
		this.drawP = d;
		this.toolP = t;
	}
	
	public void mousePressed(MouseEvent e){
		String toolMode = toolP.getToolMode();
		if(toolMode == "draw") {
			drawP.setDrawingCircles(false);
		}
		if(toolMode == "drawCircle") {
			drawP.setDrawingCircles(true);
		}
		drawP.setNewStartPoint(e.getPoint());
		if(toolMode == "select") {
			selected = drawP.getSelectedShape();
			inspect.setSelected(selected);
			list.setSelected(selected);
			drawP.setMoveShapePreviousPoint(e);
		}
	}
	
	public void mouseDragged(MouseEvent e){
		String toolMode = toolP.getToolMode();
		if(toolMode == "draw" || toolMode == "drawCircle") {
			drawP.expandShape(e);
		} else {
			drawP.moveShape(e);
		}
	}
	
	public void mouseReleased(MouseEvent e){
		String toolMode = toolP.getToolMode();
		if(toolMode == "draw" || toolMode == "drawCircle") {
			drawP.completeShape();
			list.updateList(drawP.getShapes(), null);
		}
	}
}