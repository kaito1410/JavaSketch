import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.util.*;

public class ToolPanel extends JPanel implements Observer{

	private Model model;
	JToggleButton select;
	JToggleButton line;
	JToggleButton rect; 
	JToggleButton circle; 
	JToggleButton erase;
	JToggleButton fill;

	public ToolPanel(Model mod) {
		model = mod;
                Box vertBox = Box.createVerticalBox();
                setLayout(new GridLayout(0,2));
		setMaximumSize(new Dimension(300, 300));
		setPreferredSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(200, 100));
                ImageIcon[] icons = new ImageIcon[6];
                for (int i=0; i<6; i++){
                        icons[i]=new ImageIcon(""+i+".GIF");
                }

		select = new JToggleButton(icons[0]);
		line = new JToggleButton(icons[1]);
		rect = new JToggleButton(icons[2]);
		circle = new JToggleButton(icons[3]);
		erase = new JToggleButton(icons[4]);
		fill = new JToggleButton(icons[5]);
	
		select.setSelected(true);
			
		select.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 0) {
					select.setSelected(true);
				}
				else if (select.isSelected()) {
                			model.settooltype(0);
					line.setSelected(false);
					rect.setSelected(false);
					circle.setSelected(false);
					erase.setSelected(false);
					fill.setSelected(false);
				}
            		}
        	});
		line.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 1) {
					line.setSelected(true);
				}
				else if (line.isSelected()) {
                			model.settooltype(1);
					select.setSelected(false);
					rect.setSelected(false);
					circle.setSelected(false);
					erase.setSelected(false);
					fill.setSelected(false);
				}
            		}
        	});
		rect.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 2) {
					rect.setSelected(true);
				}
				else if (rect.isSelected()) {
                			model.settooltype(2);
					select.setSelected(false);
					line.setSelected(false);
					circle.setSelected(false);
					erase.setSelected(false);
					fill.setSelected(false);
				}
            		}
        	});
		circle.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 3) {
					circle.setSelected(true);
				}
				else if (circle.isSelected()) {
                			model.settooltype(3);
					select.setSelected(false);
					line.setSelected(false);
					rect.setSelected(false);
					erase.setSelected(false);
					fill.setSelected(false);
				}
            		}
        	});
		erase.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 4) {
					erase.setSelected(true);
				}
				else if (erase.isSelected()) {
                			model.settooltype(4);
					select.setSelected(false);
					line.setSelected(false);
					rect.setSelected(false);
					circle.setSelected(false);
					fill.setSelected(false);
				}
            		}
        	});
		fill.addMouseListener(new MouseAdapter() {
            		public void mouseClicked(MouseEvent e) {
				if (model.gettooltype() == 5) {
					fill.setSelected(true);
				}
				else if (fill.isSelected()) {
                			model.settooltype(5);
					select.setSelected(false);
					line.setSelected(false);
					rect.setSelected(false);
					circle.setSelected(false);
					erase.setSelected(false);
				}
            		}
        	});

                add(select);
		add(line);
		add(rect);
		add(circle);
		add(erase);
		add(fill);

                vertBox.add(this);

                JPanel smallPanel = new JPanel();
                smallPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                smallPanel.setSize(50,50);
                smallPanel.setBackground(Color.GRAY);

                vertBox.add(smallPanel);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updated Tool Selection: " + model.gettooltype());
		if (model.getcurrshape() != null) {
			if ((model.getcurrshape()).getshapetype() == 1) {
				fill.setEnabled(false);
			}
			else if ((model.getcurrshape()).getshapetype() != 1) {
				fill.setEnabled(true);
			}
		}
	}
}
