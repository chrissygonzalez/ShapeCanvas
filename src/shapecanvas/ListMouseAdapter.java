package shapecanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ListMouseAdapter extends MouseAdapter {
	private boolean mouseDragging = false;
    private int dragSourceIndex;
    private ListPanel l;
    private DrawingPanel d;
    
    public ListMouseAdapter(ListPanel l, DrawingPanel d) {
    	this.l = l;
    	this.d = d;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	dragSourceIndex = l.getSelectedIndex();
    	NamedShape selected = dragSourceIndex > 0 ? l.getListModelIndex(dragSourceIndex) : null;
    	d.setSelectedShape(selected);
    	l.setDeleteButtonState(selected);
    	mouseDragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseDragging) {
            int currentIndex = l.getCurrentIndex(e.getPoint());
            if (currentIndex != dragSourceIndex) {
                int dragTargetIndex = l.getSelectedIndex();
                NamedShape dragElement = l.getListModelIndex(dragSourceIndex);
                l.removeFromListModel(dragSourceIndex);
                l.addToListModel(dragTargetIndex, dragElement);
                dragSourceIndex = currentIndex;
                d.setUpdatedShapes(l.getListModel(), dragElement);
            }
        }
    }
}