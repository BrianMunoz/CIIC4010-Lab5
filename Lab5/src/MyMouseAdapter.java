import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}
			JFrame frame = (JFrame) d;
			MyPanel panel = (MyPanel) frame.getContentPane().getComponent(0);
			Insets outsideInSets = frame.getInsets();
			int x1Outside = outsideInSets.left;
			int y1Outside = outsideInSets.top;
			e.translatePoint(-x1Outside, -y1Outside);
			d = e.getComponent();
			int xOutside = e.getX();
			int yOutside = e.getY();
			panel.x = xOutside;
			panel.y = yOutside;
			panel.mouseDownGridX = panel.getGridX(xOutside, yOutside);
			panel.mouseDownGridY = panel.getGridY(xOutside, yOutside);
			panel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
							if(gridX == 0 && gridY !=0 && gridY != 10){
								for(int i = 1; i < 10; i++){
									Color newColor = null;
									do{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.RED;
										break;
									case 2:
										newColor = Color.BLUE;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
									}while(myPanel.colorArray[myPanel.mouseDownGridX + i][myPanel.mouseDownGridY].equals(newColor));
									myPanel.colorArray[myPanel.mouseDownGridX + i][myPanel.mouseDownGridY] = newColor;
									myPanel.repaint();
								}
							}else if(gridY == 0 && gridX != 0){
								for(int i = 1; i < 10; i++){
									Color newColor = null;
									do{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.RED;
										break;
									case 2:
										newColor = Color.BLUE;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
									}while(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + i].equals(newColor));
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + i] = newColor;
									myPanel.repaint();
								}
							}else if (gridX == 0 && gridY == 0){
								for(int i = 1; i < 10; i++){
									Color newColor = null;
									do{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.RED;
										break;
									case 2:
										newColor = Color.BLUE;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
									}while(myPanel.colorArray[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+i].equals(newColor));
									myPanel.colorArray[myPanel.mouseDownGridX + i][myPanel.mouseDownGridY + i] = newColor;
									myPanel.repaint();
								}
							}else if (gridX == 0 && gridY == 10){
								for(int moveOnCol = 4; moveOnCol <=6; moveOnCol++){
									for(int moveOnRow = 4; moveOnRow <= 6; moveOnRow++){
										Color newColor = null;
										do{
										switch (generator.nextInt(5)) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA;
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}
										}while(myPanel.colorArray[gridX + moveOnCol][gridY - moveOnRow].equals(newColor));
										myPanel.colorArray[gridX + moveOnCol][gridY - moveOnRow] = newColor;
										myPanel.repaint();
									}
								}				
							}
							}else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							do{
							switch (generator.nextInt(5)) {
							case 0:
								newColor = Color.YELLOW;
								break;
							case 1:
								newColor = Color.MAGENTA;
								break;
							case 2:
								newColor = Color.BLACK;
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							}
							}while(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor));
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}
			JFrame frame = (JFrame) d;
			MyPanel panel = (MyPanel) frame.getContentPane().getComponent(0);
			Insets outsideInSets = frame.getInsets();
			int x1Outside = outsideInSets.left;
			int y1Outside = outsideInSets.top;
			e.translatePoint(-x1Outside, -y1Outside);
			int xOutside = e.getX();
			int yOutside = e.getY();
			panel.x = xOutside;
			panel.y = yOutside;
			int gridXOutside = panel.getGridX(xOutside, yOutside);
			int gridYOutside = panel.getGridY(xOutside, yOutside);
			
			if(gridXOutside == -1 || gridYOutside == -1){
				
				Color colorOutside = null;
				for(int i = 1; i <= 9; i++){
					for(int j = 1; j <= 9; j++){
						do{
							switch (generator.nextInt(3)){
							
							case 0:
								colorOutside = Color.ORANGE;
								break;
							case 1:
								colorOutside = Color.CYAN;
								break;
							case 2:
								colorOutside = Color.PINK;
								break;
							}
						}while(panel.colorArray[gridXOutside + i + 1][gridYOutside + j + 1].equals(colorOutside));
						panel.colorArray[gridXOutside + i + 1][gridYOutside + j + 1] = colorOutside;
						panel.repaint();
						}
					}
				}
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}