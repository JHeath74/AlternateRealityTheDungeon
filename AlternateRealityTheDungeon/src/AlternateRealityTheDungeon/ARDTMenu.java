package AlternateRealityTheDungeon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ARDTMenu extends JPanel
{


	
	public ARDTMenu() {

	JFrame frame=new JFrame("Alternate Reality: The Dungeon");
	JPanel panel = new JPanel(new BorderLayout());  
	JPanel l = new JPanel(new FlowLayout());
    JPanel s = new JPanel(new FlowLayout());

	Dimension windowSize = frame.getMaximumSize();

	 
	//Create the menu bar.
	JMenuBar menuBar = new JMenuBar();
	 
	//Build the menu.
	 frame.addWindowListener(new WindowAdapter() {
       @Override
	public void windowClosing(WindowEvent windowEvent){
          System.exit(0);
       }        
    });
	 
	JMenu gameMenu = new JMenu("Game");
	JMenu charecterMenu = new JMenu("Charecter"); 
    JMenu helpMenu = new JMenu("About");

    /////////////////////////create groups of menu items/////////////////////////////////////
    JMenuItem newMenuItem = new JMenuItem("New Game");
    newMenuItem.setMnemonic(KeyEvent.VK_N);
    newMenuItem.getAccessibleContext().setAccessibleDescription("New Game");
    newMenuItem.addActionListener(new ActionListener()
    		{

				@Override
				public void actionPerformed(ActionEvent e) {
				
				
					int result = JOptionPane.showConfirmDialog(frame,"Are you sure you wish to delete your current game and start a new one?", "Swing Tester",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				            if(result == JOptionPane.YES_OPTION){
				               
				            	try {
									BufferedWriter writer = Files.newBufferedWriter(Paths.get("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt"));
									writer.write("");
									writer.flush();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								File d = new File("src//AlternateRealityTheDungeon//TextFiles//SaveGame//");
								
								for(File file: d.listFiles()) 
								    if (!file.isDirectory()) 
								        file.delete();
										
										
								ARTDMain myMain = new ARTDMain();
								
								try {
									myMain.main(null);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								frame.dispose();
								
								
								
								
				            	
				            	
				            }else if (result == JOptionPane.NO_OPTION){
				               
				            }else {
				               
				            }
					
					
					
					
					
					
					
					
				}});
    
    JMenuItem currentMenuItem = new JMenuItem("Load Saved Game");
    currentMenuItem.setMnemonic(KeyEvent.VK_L);
    currentMenuItem.getAccessibleContext().setAccessibleDescription("Load Saved Game");
    currentMenuItem.addActionListener(new ActionListener()
    		{

				@Override
				public void actionPerformed(ActionEvent e) {
					ARDTLoadSaveGame loadgame = new ARDTLoadSaveGame();
					loadgame.LoadGame();
					
				}});
    
    JMenuItem saveMenuItem = new JMenuItem("Save Current Game");
    saveMenuItem.setMnemonic(KeyEvent.VK_S);
    saveMenuItem.getAccessibleContext().setAccessibleDescription("Save Current Game");
    saveMenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ARDTLoadSaveGame savegame = new ARDTLoadSaveGame();
			try {
				savegame.SaveGame();
			} catch (IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}});
    
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.setMnemonic(KeyEvent.VK_X);
    exitMenuItem.getAccessibleContext().setAccessibleDescription("Exit Game");
    exitMenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}});
    
    /////////////////////////Charecter Menu Group ////////////////////////////
    

    
    JMenuItem statsMenuItem = new JMenuItem("Stats");
    statsMenuItem.setMnemonic(KeyEvent.VK_T);
    statsMenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
    
    JMenuItem invMenuItem = new JMenuItem("Inventory");
    invMenuItem.setMnemonic(KeyEvent.VK_I);
    invMenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
    
    JMenuItem mapMenu = new JMenu("Map");
    mapMenu.setMnemonic(KeyEvent.VK_M);
    
    
    //Help and About Menu Items -- About the Game and any Help Information
    JMenuItem aboutMenuItem = new JMenuItem("About");
    aboutMenuItem.setMnemonic(KeyEvent.VK_A);
    aboutMenuItem.getAccessibleContext().setAccessibleDescription("About Game");
    aboutMenuItem.addActionListener(new ActionListener()
    		{

				@Override
				public void actionPerformed(ActionEvent e) {

					JFrame frame = new JFrame("Help Information");
		    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	     
		    	    JPanel p = new JPanel(new BorderLayout());
		    	    
		    	       
		    	    JButton helpbutton = new JButton("Click to Close");
		    	    helpbutton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							
						}
		    	    	   
		    	       });
		    	       
		    	        JTextArea helptext = new JTextArea(50, 150);
		    	        helptext.setLineWrap(true);
		    	        helptext.setWrapStyleWord(true);
		    	        JScrollPane scrollPane = new JScrollPane(helptext);
		    	        scrollPane.setSize(120, 120);
		    	        
		    	        try {
		    	            // Read some text from the resource file to display in
		    	            // the JTextArea.
		    	            helptext.read(new InputStreamReader(Objects.requireNonNull(
		    	                    getClass().getResourceAsStream("/AlternateRealityTheDungeon/TextFiles/About.txt"))), null);
		    	        } catch (IOException e1) {
		    	            e1.printStackTrace();
		    	        }
		    	        
		    	        frame.add(p);
		    	        frame.getContentPane().add(new JScrollPane(helptext), BorderLayout.NORTH);
		    	        frame.setSize(120, 120);
		    	        
		    	        p.add(helpbutton, BorderLayout.SOUTH);
		    	        helpbutton.setSize(120,120);
		    	        
		    	        frame.pack();
		    	        frame.setVisible(true);



				}});
        
    JMenuItem helpMenuItem = new JMenuItem("Help");
    helpMenuItem.setMnemonic(KeyEvent.VK_H);
    helpMenuItem.getAccessibleContext().setAccessibleDescription("Help");
    helpMenuItem.addActionListener(new ActionListener() {

    	@Override
		public void actionPerformed(ActionEvent e) {
			 
    	       JFrame frame = new JFrame("Help Information");
    	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	     
    	       JPanel p = new JPanel(new BorderLayout());
    	       
    	       JButton helpbutton = new JButton("Click to Close");
    	       helpbutton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					
				}
    	    	   
    	       });
    	       
    	        JTextArea helptext = new JTextArea(50, 150);
    	        helptext.setLineWrap(true);
    	        helptext.setWrapStyleWord(true);
    	        JScrollPane scrollPane = new JScrollPane(helptext);
    	        
    	        
    	        try {
    	            // Read some text from the resource file to display in
    	            // the JTextArea.
    	            helptext.read(new InputStreamReader(Objects.requireNonNull(
    	                    getClass().getResourceAsStream("/AlternateRealityTheDungeon/TextFiles/Help.txt"))), null);
    	        } catch (IOException e1) {
    	            e1.printStackTrace();
    	        }
    	        
    	        frame.add(p);
    	        frame.getContentPane().add(new JScrollPane(helptext), BorderLayout.NORTH);
    	        frame.setSize(120, 120);
    	        
    	        p.add(helpbutton, BorderLayout.SOUTH);
    	        helpbutton.setSize(120,120);
    	        
    	        frame.pack();
    	        frame.setVisible(true);


		}});
    
 

  //add menu items to menus
    gameMenu.add(newMenuItem);
    gameMenu.add(currentMenuItem);
    gameMenu.add(saveMenuItem);
    gameMenu.add(exitMenuItem);
        
    charecterMenu.add(statsMenuItem);
    charecterMenu.add(invMenuItem);
    charecterMenu.add(mapMenu);

    helpMenu.add(aboutMenuItem);
    helpMenu.add(helpMenuItem);
    
    //add menu to menubar
    menuBar.add(gameMenu);
    menuBar.add(charecterMenu);
    menuBar.add(helpMenu);
    

    JMenuItem mapFloor1MenuItem = new JMenuItem("Floor 1");
    mapFloor1MenuItem.setMnemonic(KeyEvent.VK_1);
    mapFloor1MenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
    
    
    JMenuItem mapFloor2MenuItem = new JMenuItem("Floor 2");
    mapFloor2MenuItem.setMnemonic(KeyEvent.VK_2);
    mapFloor2MenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
    
    JMenuItem mapFloor3MenuItem = new JMenuItem("Floor 3");
    mapFloor3MenuItem.setMnemonic(KeyEvent.VK_3);
    mapFloor3MenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
    JMenuItem mapFloor4MenuItem = new JMenuItem("Floor 4");
    mapFloor4MenuItem.setMnemonic(KeyEvent.VK_4);
    mapFloor4MenuItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}});
	mapMenu.add(mapFloor1MenuItem);
	mapMenu.add(mapFloor2MenuItem);
	mapMenu.add(mapFloor3MenuItem);
	mapMenu.add(mapFloor4MenuItem);
	
    
    //add menubar to the frame
   frame.setJMenuBar(menuBar);
   
   
	 
   //Size and Location for Window
   
	//Add Menubar and Display JFrame
	   
	frame.setJMenuBar(menuBar);
	frame.add(panel, BorderLayout.NORTH);
	panel.add(l);
	panel.add(s);
	
	frame.setSize(windowSize);
		
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setLayout(null);  
	frame.setVisible(true); 
	}

	



}
