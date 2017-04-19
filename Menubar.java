import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Menubar extends JMenuBar{

	private Model model;
	private JFrame frame;
	boolean isfullsize;
	
	public Menubar(Model mod, JFrame f) {
		model = mod;
		frame = f;
		isfullsize = true;

                JMenu file = new JMenu("File");
		JMenuItem filenew = new JMenuItem("New File");
		JMenuItem fileopen = new JMenuItem("Open File");
		JMenuItem filesave = new JMenuItem("Save File");
		file.add(filenew);
		file.add(fileopen);
		file.add(filesave);

                JMenu view = new JMenu("View");
		JMenuItem fullsize = new JMenuItem("View-Fill Size");
		JMenuItem fitsize = new JMenuItem("View-Fit to Window");
		view.add(fullsize);
		view.add(fitsize);

		filenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MyShape> emptyshape = new ArrayList<MyShape>();
				model.setallshape(emptyshape);
			}
		});

		fileopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename != null) {
					loadfile(filename);
				}
			}
		});

		filesave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.SAVE);
				fd.setDirectory("C:\\");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename != null) {
					savefile(filename);
				}
			}
		});

		fullsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isfullsize = true;
				model.setfullsize(isfullsize);
			}
		});

		fitsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isfullsize = false;
				model.setfullsize(isfullsize);
			}
		});

                add(file);
                add(view); 
	}
	
	public boolean isfullsize() {return isfullsize;}
	
	
	@SuppressWarnings("unchecked")
	public void loadfile(String filename){
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<MyShape> temp = new ArrayList<MyShape>();
			temp = (ArrayList<MyShape>) ois.readObject();
			model.setallshape(temp);
			ois.close();
		}
		catch(Exception ex) {
   			ex.printStackTrace();
		}
	}
	
	public void savefile(String filename) {
		try {
			model.addcurrshape();
    			FileOutputStream fos = new FileOutputStream(filename);
    			ObjectOutputStream oos = new ObjectOutputStream(fos);   
   			oos.writeObject(model.getallshape()); 
    			oos.close(); 
		}
		catch(Exception ex) {
   			ex.printStackTrace();
		}
	}
}
