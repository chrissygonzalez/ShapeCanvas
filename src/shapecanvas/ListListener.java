package shapecanvas;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {
	private DrawingPanel d;
	private InspectorPanel i;
	
	public ListListener(DrawingPanel d, InspectorPanel i) {
		this.d = d;
		this.i = i;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		JList source = (JList)e.getSource();
		NamedRectangle selected = (NamedRectangle)source.getSelectedValue();
		d.setSelectedShape(selected);
		i.setSelected(selected);
	}
	
}