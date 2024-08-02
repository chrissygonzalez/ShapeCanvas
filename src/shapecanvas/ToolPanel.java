package shapecanvas;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ToolPanel extends JPanel {
	private ButtonGroup toolGroup;
	private JToggleButton selectBtn;
	private JToggleButton drawBtn;
	
	public ToolPanel(ToolModeListener tModeListen) {
		toolGroup = new ButtonGroup();
		selectBtn = new JToggleButton("Select");
		selectBtn.setActionCommand("select");
		selectBtn.addActionListener(tModeListen);
		
		drawBtn = new JToggleButton("Draw");
		drawBtn.addActionListener(tModeListen);
		drawBtn.setActionCommand("draw");
		drawBtn.setSelected(true);
		
		toolGroup.add(drawBtn);
		toolGroup.add(selectBtn);
//		System.out.println(toolGroup.getSelection().getActionCommand());
		
		add(selectBtn);
		add(drawBtn);
	}
	
	public String getToolMode() {
		return toolGroup.getSelection().getActionCommand();
	}
}