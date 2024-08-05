package shapecanvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

class ColorComboBox extends JPanel {
	private static final int IMG_WIDTH = 20;
	private int x = 2;
	private int y = x;
	private int width = IMG_WIDTH - 2 * x;
	private int height = IMG_WIDTH - 2 * y;
	private ArrayList<Color> colors;
	private ArrayList<ImageIcon> icons;
	JComboBox<Integer> colorCombos;
	
	public ColorComboBox(ArrayList<Color> colors, String actionCommand) {
		this.colors = colors;
		icons = new ArrayList<>();
		
		for(Color c : colors) {
			BufferedImage squareImg = new BufferedImage(IMG_WIDTH, IMG_WIDTH, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = squareImg.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(c);
			g2.fillRect(x, y, width, height);
			g2.dispose();
			      
			ImageIcon icon = new ImageIcon(squareImg);
			icon.setImage(squareImg);
			icons.add(icon);
		}
		
        Integer[] intArray = new Integer[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            intArray[i] = new Integer(i);
            icons.get(i).setDescription(colors.get(i).toString());
        }
        
        colorCombos = new JComboBox<Integer>(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        
        colorCombos.setSelectedIndex(0);
        colorCombos.setActionCommand(actionCommand);
        colorCombos.setRenderer(renderer);
        add(colorCombos);
	}
	
	public void addListener(ColorComboActionListener cListen) {
		colorCombos.addActionListener(cListen);
	}
	
	public void setSelectedColor(Color c) {
		colorCombos.setSelectedIndex(colors.indexOf(c));
	}
	
	public void setSelectedIndex(int i) {
		colorCombos.setSelectedIndex(i);
	}
	

    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
    	
        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			int selectedIndex = ((Integer)value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			ImageIcon icon = icons.get(selectedIndex);
			setIcon(icon);

			return this;
		}

    }
}