package shapecanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ListActionListener implements ActionListener {
	private DrawingPanel d;
	private ListPanel l;
	
	public ListActionListener(ListPanel l, DrawingPanel d) {
		this.d = d;
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		l.deleteAndUpdateShapes();
		d.setUpdatedShapes(l.getListModel(), null);
		l.setDeleteButtonState(null);
	}
	
}