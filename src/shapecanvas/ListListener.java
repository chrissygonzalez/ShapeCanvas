package shapecanvas;

import java.awt.Cursor;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {
	private DrawingPanel d;
	private InspectorPanel i;
	private ToolPanel t;
	
	public ListListener(DrawingPanel d, InspectorPanel i, ToolPanel t) {
		this.d = d;
		this.i = i;
		this.t = t;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<NamedShape> source = (JList<NamedShape>)e.getSource();
		NamedShape selected = (NamedShape)source.getSelectedValue();
		if(selected == null) {
			d.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else {
			d.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		d.setSelectedShape(selected);
		i.setSelected(selected);
		if(selected != null) {
			t.setToolMode("select");
		}
	}

}