import java.util.Observable;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Model extends Observable {

	private ArrayList<MyShape> allshapes;

	private MyShape currshape;
	private int tooltype;
	private Color currcolor;
	private int currthickness;
	private boolean isfullsize;

	public Model() {
		allshapes = new ArrayList<MyShape>();
		this.currcolor = Color.BLACK;
		this.currthickness = 3;
		this.tooltype = 0;
		this.isfullsize = true;
		setChanged();
	}

	public MyShape getcurrshape() {return currshape;}

	public void setcurrshape(MyShape shape) {
		currshape = shape;
		setChanged();
		notifyObservers();
	}

	public void addcurrshape() {
		if (currshape != null) {
			currshape.setcurr(false);
			allshapes.add(currshape);
		}
		currshape = null;
		setChanged();
		notifyObservers();
	}

	public ArrayList<MyShape> getallshape() {return allshapes;}
	public void setallshape(ArrayList<MyShape> all) {
		allshapes = all;
		setChanged();
		notifyObservers();
	}

	public Color getcurrcolor() {return currcolor;}
	public void setcurrcolor(Color newcolor) {
		if (currshape != null) {
			currshape.setcolor(newcolor);
		}
		currcolor = newcolor;
		setChanged();
		notifyObservers();
	}

	public int gettooltype() {return tooltype;}
	public void settooltype(int newtool) {
		addcurrshape();
		tooltype = newtool;
		setChanged();
		notifyObservers();
	}

	public int getcurrthickness() {return currthickness;}
	public void setcurrthickness(int newthickness) {
		if (currshape != null) {
			currshape.setthickness(newthickness);
		}
		currthickness = newthickness;
		setChanged();
		notifyObservers();
	}

	public boolean getfullsize() {return isfullsize;}
	public void setfullsize(boolean size) {
		isfullsize = size;
		setChanged();
		notifyObservers();
	}

	public void selectshape(int x, int y) {
		addcurrshape();
		for (int i = allshapes.size()-1; i >= 0; i--) {
			MyShape temp = allshapes.get(i);
			if ((x >= temp.getX1() && x <= temp.getX2() && y >= temp.getY1() && y <= temp.getY2()) || 
			    (x >= temp.getX1() && x <= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y >= temp.getY1() && y <= temp.getY2())) {
				currshape = temp;
				allshapes.remove(i);
				currshape.setcurr(true);
				currcolor = currshape.getColor();
				currthickness = currshape.getthickness();
				break;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void moveshape(int x, int y) {
		if (currshape != null) {
			currshape.setX1(currshape.getX1() + x);
			currshape.setY1(currshape.getY1() + y);
			currshape.setX2(currshape.getX2() + x);
			currshape.setY2(currshape.getY2() + y);
			System.out.println("shape dragged :" + x + " " + y);
			setChanged();
			notifyObservers();
		}
	}
	
	public void eraseshape(int x, int y) {
		addcurrshape();
		for (int i = allshapes.size()-1; i >= 0; i--) {
			MyShape temp = allshapes.get(i);
			if ((x >= temp.getX1() && x <= temp.getX2() && y >= temp.getY1() && y <= temp.getY2()) || 
			    (x >= temp.getX1() && x <= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y >= temp.getY1() && y <= temp.getY2())) {
				allshapes.remove(i);
				break;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void fillshape(int x, int y) {
		addcurrshape();
		for (int i = allshapes.size()-1; i >= 0; i--) {
			MyShape temp = allshapes.get(i);
			if ((x >= temp.getX1() && x <= temp.getX2() && y >= temp.getY1() && y <= temp.getY2()) || 
			    (x >= temp.getX1() && x <= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y <= temp.getY1() && y >= temp.getY2()) ||
			    (x <= temp.getX1() && x >= temp.getX2() && y >= temp.getY1() && y <= temp.getY2())) {
				temp.setfill(true);
				temp.setfillcolor(currcolor);
				break;
			}
		}
		setChanged();
		notifyObservers();
	}
}
	
