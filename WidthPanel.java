import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.util.*;

public class WidthPanel extends JPanel implements Observer{

	private Model model;
	private int currwidth;
	private JSlider widthchooser;

	public WidthPanel(Model mod) {
		model = mod;
		currwidth = 1;

		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(300, 100));
		setMinimumSize(new Dimension(300, 100));

		JLabel name = new JLabel("Thickness Palette", JLabel.LEFT);

		widthchooser = new JSlider(1, 10, 1);
		widthchooser.setMajorTickSpacing(2);
		widthchooser.setPaintTicks(true);
		widthchooser.setPaintLabels(true);

		widthchooser.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {	
				int temp = ((JSlider)e.getSource()).getValue();
				model.setcurrthickness(temp);
			}
               	});
//		this.add(Box.createRigidArea(new Dimension(10, 30)));
		this.add(name, BorderLayout.NORTH);
		this.add(widthchooser, BorderLayout.SOUTH);
	}

	@Override
	public void update(Observable o, Object arg) {
		currwidth = model.getcurrthickness();
		widthchooser.setValue(currwidth);
		repaint();
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(currwidth));
                g2.setColor(model.getcurrcolor());
                g2.drawLine(0, 50, 300, 50);
	}
}

