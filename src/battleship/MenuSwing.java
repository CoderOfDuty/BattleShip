package battleship;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MenuSwing {

	JTextField xcoords;
	JTextField ycoords;
	BarMenu barMenu;
	JFrame frame;
	Image[] img;
	//Button button;
	JLabel background;
	ImageIcon icon;

	// Object [][] boardImg = {};

	public MenuSwing() {
		frame = new JFrame("Battleship");
		barMenu = new BarMenu();
		frame.setSize(700, 600);

		frame.addWindowListener(new FrameListener());
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("img/icon.gif"));
		frame.setLocation(200, 100);
		frame.setBackground(Color.BLACK);

		//frame.setLayout(null);
		//frame.setLayout(new BorderLayout());
		//frame.setLayout(new FlowLayout());
//		frame.setLayout(LayoutManager);
		
		frame.setMenuBar(barMenu);

		xcoords = new JTextField();
		xcoords.setName("X-coord");
		ycoords = new JTextField();
		frame.add(xcoords);
		frame.add(ycoords);

		// icon = Image.read();
		background = new JLabel();
		frame.add(background);
		
		ImageIcon imgFire = new ImageIcon("img/img0.png");
		ImageIcon imgEmpty = new ImageIcon("img/img1.png");
		ImageIcon imgHit = new ImageIcon("img/img2.png");
		ImageIcon imgWater = new ImageIcon("img/img3.png");
		
		JButton button;
		
		//BufferedImage buttonIcon = ImageIO.read(new File("myImage.png"));
	    button = new JButton();
	    //new ImageIcon(buttonIcon)
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false);

		//button = new Button("FIRE");
		
		JButton n = new JButton("a", new ImageIcon());
		frame.add(button);
		// button.setLocation(400, 400);
		// button.setSize(500, 250);
		button.setBounds(400, 400, 500, 250);
		button.addActionListener(new ActionPerformed());

		

		// frame.pack();
		frame.setVisible(true);
	}

	class FrameListener extends WindowAdapter {// close window X
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		public void windowOpened(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}

	}

	class ActionPerformed implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			/*if (e.getSource().equals()) {

			}*/
		}
	}

	public class BarMenu extends MenuBar {

		Menu menuFile;
		Menu menuEdit;
		Menu menuHelp;

		MenuItem opcionNew;
		MenuItem opcionOpen;
		MenuItem opcionSave;
		MenuItem opcionClose;

		public BarMenu() {
			menuFile = new Menu("File");
			menuEdit = new Menu("Edit");
			menuHelp = new Menu("Help");

			opcionNew = new MenuItem("New");
			opcionOpen = new MenuItem("Open");
			opcionSave = new MenuItem("Save");
			opcionClose = new MenuItem("Close");

			// opcionSave.setShortcut(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

			opcionNew.addActionListener(new ActionMenu());
			opcionOpen.addActionListener(new ActionMenu());
			opcionClose.addActionListener(new ActionMenu());

			menuFile.add(opcionNew);
			menuFile.add(opcionOpen);
			menuFile.add(opcionSave);
			menuFile.add(opcionClose);

			add(menuFile);
			add(menuEdit);
			add(menuHelp);
		}
	}

	public class ActionMenu implements ActionListener {// Actions BarMenu

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(barMenu.opcionClose)) {
				System.exit(0);
			} else if (e.getSource().equals(barMenu.opcionNew)) {

			} else if (e.getSource().equals(barMenu.opcionOpen)) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(null);
				fc.setAccessory(null);

			} else if (e.getSource().equals(barMenu.opcionSave)) {

			}

		}

	}

}
