import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.util.*;

public class ColorPanel extends JPanel implements Observer{

	private Model model;
	private Color currcolor;
	JPanel colorpreview;

	public ColorPanel(Model mod) {
		model = mod;
		currcolor = Color.BLACK;

		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 100));
		setMaximumSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(300, 50));
		setBackground(Color.GRAY);

		JButton colorchooser = new JButton("Open Color Palette");
		colorpreview = new JPanel();
		colorpreview.setOpaque(true);
		colorpreview.setMaximumSize(new Dimension(300, 30));
		colorpreview.setPreferredSize(new Dimension(300, 50));
		colorpreview.setMaximumSize(new Dimension(300, 70));
		colorchooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				JColorChooser pick = new JColorChooser();
				currcolor = pick.showDialog(null, "Please select a color", Color.BLACK);
				colorpreview.setBackground(currcolor);
				model.setcurrcolor(currcolor);
			}
               	});

		this.add(colorchooser, BorderLayout.NORTH);
		this.add(colorpreview, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {
		currcolor = model.getcurrcolor();
		colorpreview.setBackground(currcolor);
	}
}
