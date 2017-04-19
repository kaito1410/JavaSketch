import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.*;

public class View extends JPanel implements Observer{

	private Model model;

	private int mouseX;
        private int mouseY;

	private int startmouseX;
        private int startmouseY;

	private int endmouseX;
        private int endmouseY;

	public View(Model mod) {
		model = mod;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(1280, 1200));
		this.setMinimumSize(new Dimension(300, 600));
		this.setMaximumSize(new Dimension(1280, 1200));
		mouseEventHandler handler = new mouseEventHandler();
		this.addMouseListener(handler); 
		this.addMouseMotionListener(handler);
/*		this.addComponentListener(new ComponentListener() {
    			public void componentResized(ComponentEvent e) {
				if (!model.getfullsize()) {
				//TBD
				}		           
    			}
		});*/
	}

	class mouseEventHandler extends MouseAdapter
	{         
		@Override
		public void mouseClicked(MouseEvent e) {
			if (model.gettooltype() == 0) {
				mouseX = e.getX();
    				mouseY = e.getY();
				model.selectshape(mouseX, mouseY);
			}
			else if (model.gettooltype() == 4) {
				mouseX = e.getX();
    				mouseY = e.getY();
				model.eraseshape(mouseX, mouseY);
			}
			else if (model.gettooltype() == 5) {
				mouseX = e.getX();
    				mouseY = e.getY();
				model.fillshape(mouseX, mouseY);
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (model.gettooltype() == 1 || model.gettooltype() == 2 || model.gettooltype() == 3) {
				startmouseX = e.getX();
				startmouseY = e.getY();
				System.out.println("mouse enter :" + startmouseX + " " + startmouseY);
			}
			else if (model.gettooltype() == 0) {
				startmouseX = e.getX();
    				startmouseY = e.getY();
				System.out.println("shape select :" + startmouseX + " " + startmouseY);
				model.selectshape(startmouseX, startmouseY);
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (model.gettooltype() == 1 || model.gettooltype() == 2 || model.gettooltype() == 3) {
				endmouseX = e.getX();
				endmouseY = e.getY();
				System.out.println("mouse dragged :" + endmouseX + " " + endmouseY);
				MyShape newshape = new MyShape(startmouseX, startmouseY, endmouseX, endmouseY, model.gettooltype(), model.getcurrcolor(), model.getcurrthickness());
				model.setcurrshape(newshape);
			}
			else if (model.gettooltype() == 0) {
				int movemouseX = e.getX() - startmouseX;
				startmouseX = e.getX();
    				int movemouseY = e.getY() - startmouseY;
				startmouseY = e.getY();
				model.moveshape(movemouseX, movemouseY);
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (model.gettooltype() == 1 || model.gettooltype() == 2 || model.gettooltype() == 3) {
				endmouseX = e.getX();
				endmouseY = e.getY();
				System.out.println("mouse release :" + endmouseX + " " + endmouseY);
				MyShape newshape = new MyShape(startmouseX, startmouseY, endmouseX, endmouseY, model.gettooltype(), model.getcurrcolor(), model.getcurrthickness());
				model.setcurrshape(newshape);
				model.addcurrshape();
			}
			else if (model.gettooltype() == 0) {
				int movemouseX = e.getX() - startmouseX;
				startmouseX = e.getX();
    				int movemouseY = e.getY() - startmouseY;
				startmouseY = e.getY();
				MyShape curr = model.getcurrshape();
				if (curr != null) {
					curr.setX1(curr.getX1() + movemouseX);
					curr.setY1(curr.getY1() + movemouseY);
					curr.setX2(curr.getX2() + movemouseX);
					curr.setY2(curr.getY2() + movemouseY);
					System.out.println("shape release :" + movemouseX + " " + movemouseY);
				}
				model.addcurrshape();
			}
		}
	} 
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		System.out.println("Updated + Repaint...");
		super.paintComponent(g);

		ArrayList<MyShape> temp = model.getallshape();
        	if (temp != null){
            		for (int i = 0; i < temp.size(); i++ ){
                		temp.get(i).draw(g);
            		}
        	}

        	if (model.getcurrshape() != null) {
            		model.getcurrshape().draw(g);
        	}
    	}
}
