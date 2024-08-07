package shapecanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

class ShapeMenuListener implements ActionListener {
	private ShapeCanvasPanel s;
	private JFrame f;
	private JFileChooser jFileChooser;
	private LinkedList<NamedShape> rects;
	
	public ShapeMenuListener(ShapeCanvasPanel s, JFrame f) {
		this.s = s;	
		this.f = f;
		jFileChooser = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ShapeCanvas files", "sc", "shape");
		jFileChooser.setFileFilter(filter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "exit") {
			System.exit(0);
		}
		if(e.getActionCommand() == "open") {
			int returnVal = jFileChooser.showOpenDialog(f);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser.getSelectedFile();
				try {
					readFile(file);
					s.setShapeState(rects);
				} catch (IOException err) {
					err.printStackTrace();
				} catch (ClassNotFoundException err) {
					
				}
			}
		}
		if(e.getActionCommand() == "save") {
			int returnVal = jFileChooser.showSaveDialog(f);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser.getSelectedFile();
				try {
					String filename = file.getCanonicalPath();
					
					if(!filename.endsWith(".sc") ) {
						file = new File(filename + ".sc");
					}
					writeFile(file);
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	}
	
	public LinkedList<NamedShape> readFile(File file) throws IOException, ClassNotFoundException {
		rects = new LinkedList<>();
	    FileInputStream fileInputStream = new FileInputStream(file);
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    
	    try {
	    	while(true){
	    		NamedShape r = (NamedShape)objectInputStream.readObject();
	    		rects.add(r);
	    	}
	    } catch(ClassNotFoundException e) {
	    	
	    } catch(EOFException e) {
	    	
	    }

		objectInputStream.close();
		return rects;
	}
	
	public void writeFile(File file) throws IOException {
		LinkedList<NamedShape> shapes = s.getShapeState();
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    
	    for(int i = 0; i < shapes.size(); i++) {
	    	objectOutputStream.writeObject(shapes.get(i));
	    }
	    objectOutputStream.flush();
	    objectOutputStream.close();
	}
}