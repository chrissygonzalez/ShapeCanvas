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
			drawP.setDrawingShape("rectangle");
		}
		if(toolMode == "drawCircle") {
			drawP.setDrawingShape("circle");
		}
		if(toolMode == "drawTriangle") {
			drawP.setDrawingShape("triangle");
		}
		drawP.setNewStartPoint(e.getPoint());
		if(toolMode == "select") {
			drawP.setPanelCursor("select");
			selected = drawP.getSelectedShape();
			inspect.setSelected(selected);
			list.setSelected(selected);
			drawP.setMoveShapePreviousPoint(e);
		}
	}
	
	public void mouseDragged(MouseEvent e){
		String toolMode = toolP.getToolMode();
		int selectedHandleIndex = drawP.getSelectedHandleIndex();
		if(toolMode == "select" && selectedHandleIndex >= 0) {
			drawP.expandSelectedShape(e);
		}
		if(toolMode != "select") {
			drawP.expandShape(e);
		} else {
			drawP.moveShape(e);
		}
	}
	
	public void mouseReleased(MouseEvent e){
		String toolMode = toolP.getToolMode();
		if(toolMode != "select") {
			drawP.completeShape();
			list.updateList(drawP.getShapes(), null);
		} else {
			drawP.resetDimensions();
		}
	}
}