package shapecanvas;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

public class ShapeCanvasFrame extends JFrame {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createOuterContainer();
			}
		});
	}
	
	public static void createOuterContainer() {
		JFrame f = new JFrame("Shape Canvas");
		ShapeCanvasPanel s = new ShapeCanvasPanel();
		
		JMenuBar menuBar = new JMenuBar();
		ShapeMenu shapeMenu = new ShapeMenu();
		ShapeMenuListener sListen = new ShapeMenuListener(s, f);
		shapeMenu.addListeners(sListen);
		
		f.setJMenuBar(menuBar);
		menuBar.add(shapeMenu.createFileMenu());
		
		f.getContentPane().add(s);
		f.setSize(1000, 725);
		f.setVisible(true);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}