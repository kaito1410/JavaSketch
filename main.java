import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;


// JComponent is a base class for custom components 
public class main{

    public static void main(String[] args) {
	JFrame f = new JFrame("PaintApp"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	Model model = new Model();

	View view = new View(model);

	Menubar mbar = new Menubar(model, f);

	ToolPanel tpanel = new ToolPanel(model);

	ColorPanel cpanel = new ColorPanel(model);

	WidthPanel wpanel = new WidthPanel(model);

	model.addObserver(wpanel);

	model.addObserver(tpanel);

	model.addObserver(view);

	model.addObserver(cpanel);

	model.notifyObservers();
	
	JPanel controls = new JPanel();
	controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
	controls.setMinimumSize(new Dimension(300, 480));
	controls.setBackground(Color.GRAY);

	tpanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	cpanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	wpanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

	controls.add(tpanel);
	controls.add(Box.createRigidArea(new Dimension(10, 10)));
	controls.add(cpanel);
	controls.add(Box.createRigidArea(new Dimension(10, 10)));
	controls.add(wpanel);

	JScrollPane scroll = new JScrollPane(view);

	f.getContentPane().setLayout(new BorderLayout());
	f.add(scroll, BorderLayout.CENTER);
	f.add(controls, BorderLayout.WEST);
	f.setJMenuBar(mbar);
	f.setMinimumSize(new Dimension(640, 480));
	f.setPreferredSize(new Dimension(1600, 1200));
	f.setMaximumSize(new Dimension(1600, 1200));
 	f.pack();
	f.setResizable(true); 
	f.setLocationRelativeTo(null);
        f.setVisible(true); // show the window
   }
}
