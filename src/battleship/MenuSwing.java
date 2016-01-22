package battleship;


import java.awt.*;
import java.awt.event.*;
	
public class MenuSwing{
	
	TextField xcoords;
	TextField ycoords;
	BarMenu barMenu;
	Frame frame;
	//Label img;
	//Image background;
	Image[] img;
	Button button;
	Label background;
	Image icon;
	
		
	//Object [][] boardImg = {};
			
	public MenuSwing(){
		frame = new Frame("Battleship");
		barMenu = new BarMenu();
		frame.setSize(700, 600);
		
		frame.addWindowListener(new FrameListener());
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("img/icon.gif"));
		frame.setLocation(200,100);
		frame.setBackground(Color.BLACK);
		
		frame.setLayout(new BorderLayout());
		frame.setLayout(new FlowLayout());
		//marco.setBackground(new Image("img/background.jpg"));
		//background = Toolkit.getDefaultToolkit();
		//frame.setBackground(Toolkit.getDefaultToolkit("background.jpg"));
		frame.setMenuBar(barMenu);
		
		xcoords = new TextField();
		xcoords.setName("X-coord");
		ycoords = new TextField();
		frame.add(xcoords);
		frame.add(ycoords);
		
		//icon = Image.read();
		background = new Label();
		frame.add(background);
		
		
		button = new Button("FIRE");
		frame.add(button);
		button.setLocation(400, 400);
		button.setSize(500, 250);
		button.addActionListener(new ActionPerformed());
		
		
		for(int i = 0;i <3; i++){
			//img[i] = new Image("img/img"+i+".png");
		}
		//frame.pack();
		frame.setVisible(false);
	}
	class FrameListener  extends WindowAdapter{//close window X
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}
	class ActionPerformed implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Battleship.
			
			Button b = (Button)e.getSource();
			if(b == button){
				
			}
		}	
	}

	class BarMenu extends MenuBar{

		Menu menuFile;
		Menu menuEdit;
		Menu menuHelp;
		
		BarMenu(){
			menuFile = new Menu("File");
			menuEdit = new Menu("Edit");
			menuHelp = new Menu("Help");
			
			MenuItem opcionNew = new MenuItem("New");
			MenuItem opcionOpen = new MenuItem("Open");
			MenuItem opcionClose = new MenuItem("Close");
			
			menuFile.add(opcionNew);
			menuFile.add(opcionOpen);
			menuFile.add(opcionClose);
			
			add(menuFile);
			add(menuEdit);
			add(menuHelp);
		}
	}
	
	public class ActionMenu implements ActionListener{//Actions BarMenu

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}
		
	}

}
