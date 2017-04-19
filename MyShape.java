import java.awt.*;
import java.lang.Math.*;
import javax.swing.*;
import java.io.*;

public class MyShape implements Serializable{
	private int shapetype;
	private int x1, y1, x2, y2;
	private Color color;
	private Color fillcolor;
	private int thickness;
	private boolean fill;
	private boolean curr;

	public MyShape() {
	}

	public MyShape(int x1, int y1, int x2, int y2, int shapetype, Color currcolor, int currthickness) {
        	this.x1=x1;
        	this.y1=y1;
        	this.x2=x2;
        	this.y2=y2;
		this.shapetype = shapetype;
        	this.color = currcolor;
		this.thickness = currthickness;
		this.fill = false;
		this.fillcolor = Color.WHITE;
		this.curr = true;
		System.out.println("Creating shape..." + shapetype);
    	}
    
	public void setX1(int x1){
		this.x1=x1;
	}   
    
   	public void setY1(int y1){
		this.y1=y1;
   	}   
    
	public void setX2(int x2){
        	this.x2=x2;
    	}      
	public void setY2(int y2){
		this.y2=y2;
	}   

	public void setcolor(Color newcolor){
		this.color=newcolor;
	}   

	public void setthickness(int newthickness){
		this.thickness = newthickness;
	}   

	public void setcurr(boolean bool) {
		curr = bool;
	}
    
	public void setfill(boolean filled) {
		fill = filled;
	}
	
	public void setfillcolor(Color col) {
		fillcolor = col;
	}

	public int getX1(){
		return x1;
	}
    
	public int getY1(){
        	return y1;
	}
    
	public int getX2(){
        	return x2;
    	}
    
	public int getY2(){
        	return y2;
    	}

	public int getshapetype() {
		return shapetype;
	}
    
	public Color getColor(){
        	return color;
    	}

	public int getthickness(){
        	return thickness;
    	}

	public boolean getcurr() {
		return curr;
	}

	public boolean getfill() {
		return fill;
	}
	
	public Color getfillcolor() {
		return fillcolor;
	}
    
   	public void draw( Graphics g ) {
       		Graphics2D g2 = (Graphics2D) g;
		switch(shapetype)
		{
			case 1:
				if (curr) {
					g2.setColor(Color.YELLOW);
					g2.setStroke(new BasicStroke(3));
					g2.drawRect(Math.min(x1-10, x2-10), Math.min(y1-10, y2-10), Math.max(x2-x1+20, x1-x2+20), Math.max(y2-y1+20, y1-y2+20));
				}
				g2.setStroke(new BasicStroke(thickness));
				g2.setColor(color);	
				g2.drawLine(x1, y1, x2, y2);
				break;
			case 2:
				if (curr) {
					g2.setColor(Color.YELLOW);
					g2.setStroke(new BasicStroke(3));
					g2.drawRect(Math.min(x1-10, x2-10), Math.min(y1-10, y2-10), Math.max(x2-x1+20, x1-x2+20), Math.max(y2-y1+20, y1-y2+20));
				}
				g2.setStroke(new BasicStroke(thickness));
				g2.setColor(color);	
				g2.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.max(x2-x1, x1-x2), Math.max(y2-y1, y1-y2));
				if (fill) {
					g2.setColor(fillcolor);
					g2.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.max(x2-x1, x1-x2), Math.max(y2-y1, y1-y2));
				}
				break;
			case 3:
				int minwidth = Math.max(Math.max(Math.min(x2-x1, y1-y2), Math.min(x2-x1, y2-y1)), Math.max(Math.min(x1-x2, y1-y2), Math.min(x1-x2, y2-y1)));	
				if (curr) {
					g2.setColor(Color.YELLOW);
					g2.setStroke(new BasicStroke(3));
					g2.drawRect(Math.min(x1-10, x2-10), Math.min(y1-10, y2-10), minwidth+20, minwidth+20);
				}
				g2.setStroke(new BasicStroke(thickness));
				g2.setColor(color);
				g2.drawOval(Math.min(x1, x2), Math.min(y1, y2), minwidth, minwidth);
				if (fill) {
					g2.setColor(fillcolor);
					g2.fillOval(Math.min(x1, x2), Math.min(y1, y2), minwidth, minwidth);
				}
				break;
	    	} 
	}
}
