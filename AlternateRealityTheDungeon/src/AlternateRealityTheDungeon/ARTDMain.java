package AlternateRealityTheDungeon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ARTDMain {

	
	
	
	public static void main(String[] args) throws IOException {

		ARTDCharecter myChar = new ARTDCharecter();
		
		// checking if file is empty // if file doesn't exits then also length()
		// method will consider // it empty.
		
		Scanner saveFile = new Scanner(new File("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt"));

		if (!saveFile.hasNext())
		{
			JFrame frame = new JFrame("Create New Charecter");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			  int range = 20; int lowerbound = 10;
			  
			  Integer[] stat = new Integer[6];
			  
			  for(int i = 0; i < stat.length; i++) { stat[i] = (int)
			  (Math.random()*range)+lowerbound; 
			  
		 }
			  
			    //Tooncreation,  allows user to make up their name
				JTextField tooncreation = new JTextField("Please Enter a User Name.");
				tooncreation.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						tooncreation.setText("");
						
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					
				});
				
				
				//Toon Stats Randomly Generates Charecter Stats
				
				JTextArea toonstats = new JTextArea("Charecter Stats\nSTAMINA: \t\t"+  stat[0] + "  \nCHARISMA:\t\t"+ stat[1] + "\n"
						+ "STRENGTH:\t\t" + stat[2]+ " \nINTELLIGENCE:\t"+ stat[3] + "\nWISDOM:\t\t" + stat[4] + "\nAGILITY:\t\t" +  stat[5]);
				JScrollPane toonstatsPane = new JScrollPane(toonstats);
				
				
			
			String[] toonclass = {"Paladin", "Cleric", "Rogue","Hunter","Warrior","Bard"}; // Charecter Classes
			
			Arrays.sort(toonclass);
						
			
			JComboBox charectorClass = new JComboBox(toonclass); // Adding the Array for Classes to the JComboBox allowing the player to choose.
			
			JTextArea toonclassDescription = new JTextArea("Choose Your Class from the DropDown Box Above."); // 
			toonclassDescription.setLineWrap(true);
			
			
			charectorClass.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					String toonD = charectorClass.getSelectedItem().toString(); // Get the selected item in the JComboBox
			
				
						// Set the class description for the JTextField
						switch(toonD)
						{
						case "Paladin":
							toonclassDescription.setText("Compared with other classes the paladin class has one of the most restrictive codes"
									+ " of conduct and paladin characters are expected to demonstrate and embody goodness. \n\n"
									+ "Wisdom (WIS) followed by Strength is most important stats for a Paladin");
						break;
						case "Cleric":
							toonclassDescription.setText("Clerics are versatile figures, both capable in combat and skilled in the use of "
									+ "divine magic (thaumaturgy). Clerics are powerful healers due to the large number of healing and"
									+ " curative magics available to them. With divinely-granted abilities over life or death, they are"
									+ " also able to repel or control undead creatures. \n\n"
									+ "Wisdom (WIS) is your most important stat, followed closely by INTELLIGENCE (INT).");
						break;
						case "Rogue":
							toonclassDescription.setText("As adventurers, rogues fall on both sides of the law. Some are hardened criminals "
									+ "who decide to seek their fortune in treasure hoards, while others enter a life of adventure to escape "
									+ "from the law. Others have learned and perfected their skills with the explicit purpose of infiltrating "
									+ "ancient ruins and hidden crypts in search of treasure. \n\n"
									+ "AGILITY (AGI) followed by Intelligence (INT) are important stats for a Rogue");
						break;
						case "Hunter":
							toonclassDescription.setText("A hunter finds its place as a bulwark between civilization and the terrors of the wilderness. "
									+ "Despite its namesake, tracking mere game is only a minor task in the hunter's repertoire of expertise. "
									+ "Its specialized battle techniques can fell rampaging ogres to hordes of orcs.\n\n"
									+ "Stamina (STA) is an important skill for a Hunter");
						break;
						case "Warrior":
							toonclassDescription.setText("Warriors share an unparalleled mastery with weapons and armor, and a thorough knowledge "
									+ "of the skills of combat. They are well acquainted with death, both meting it out and staring it defiantly "
									+ "in the face. Fighters share an unparalleled mastery with weapons and armor, and a thorough knowledge of the "
									+ "skills of combat. They are well acquainted with death, both meting it out and staring it defiantly in the face.\n\n"
									+ "Strength (STR) is an important skill for a Warrior ");
						break;
						case "Bard":
							toonclassDescription.setText("A bard is traditionally defined as \"a poet, especially one who writes impassioned, lyrical,"
									+ " or epic verse.\"  bards are a playable "
									+ "class centered on the idea of accessing magic through some form of artistic expression \n\n"
									+ "Charisma (CHA) is an important skill for a Bard");
						break;
						}
						
					
				}});

			String toonD = charectorClass.getSelectedItem().toString();
			JButton saveToon = new JButton("Save Charecter"); // Save Button for Charecter creation
			saveToon.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						
						
					      FileWriter writer = new FileWriter("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt");

					      ArrayList<String> newChar = new ArrayList<String>();
					      
					      ArrayList<String> newChar2 = new ArrayList<String>();
					      String charName = String.valueOf(tooncreation.getText());
					      
					      if(charName.equals("") || charName.equals("Please Enter a User Name.") || charName == "" || charName == null) // if statment isn't
					    	  																											//preventing toon
					    	  																											// being created without a name
					      {
					    	  JOptionPane.showMessageDialog(frame, "Please Enter a User Name, in the User Name Box Above");
					      }else {
					    	  
					    	//Character Name
					    	  
					    	  newChar.add(charName); 
					      }
					      
					      //Character Class
					      newChar.add(toonD);
					      
					      
					      //Character Level
					      newChar.add("0");
					     
					      
					      // Character Experience
					      newChar.add("0");
					      
					      
					      //Character HitPoints
					      newChar.add("15");
					      
			/*		      
					      //Character Stats
					      //Stamina
					      newChar.add(stat[0]);
					      
					      //Charisma
					      newChar.add(stat[1]);
					      
					      //Strength
					      newChar.add(stat[2]);
					      
					      //Intelligence
					      newChar.add(stat[3]);
					      
					      //Wisdom
					      newChar.add(stat[4]);
					      
					      //Agility
					      newChar.add(stat[5]);
				
					      
*/
					      //Character Gold
					      newChar2.add("100");
					      
					      //Character Food
					      newChar2.add("3");
					     
					      
					      //Character Water
					      newChar2.add("3");
					    
					      
					      //Character Torches
					      newChar2.add("3");
					   
					      
					      //Character Gems
					      newChar2.add("0");
					      
							
					        //newChar[0] = Charecter Name
							//newChar[1] = Class
					      	//newChar[2] =  Level
					      	//newChar[3] = Experience
					      	//newChar[4] = Hit Points
							//newChar[5] = Stat: Stamina
							//newChar[6] = Stat: Charisma
							//newChar[7] = Stat: Strength
							//newChar[8] = Stat: Intelligence
							//newChar[9] = Stat: Wisdom
							//newChar[10] = Stat: Agility
							//newChar[11] = Gold
							//newChar[12] = Food
					      	//newChar[13] = Water
							//newChar[14] = Torches
							//newChar[15] = Gems
					      
					      				
					    
					      for(String str: newChar) {
					        writer.write(str + System.lineSeparator());
					      }
					      
					    
					      for(Integer str2: stat) {
					        writer.write(str2 + System.lineSeparator());
					      }

					      
					      for(String str3: newChar2) {
					    	  writer.write(str3+System.lineSeparator());
					      }
					      writer.close();
					      
						 JOptionPane.showMessageDialog(frame, "Charecter Created");
						 
						 BufferedReader bufReader = new BufferedReader(new FileReader("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt"));
											 
						 String line = bufReader.readLine(); 
						 while (line != null) {
							 myChar.CharInfo().add(line);
							 line = bufReader.readLine(); 
							} 
						 
						 bufReader.close();

						 
						 
						 
						 frame.dispose();
						 new ARDTMenu();
						 
					    } catch (IOException e1  ) {
					      System.out.println("An error occurred.");
					       e1.printStackTrace();
					    }
					
				}});
			
			
			BufferedReader bufReader = new BufferedReader(new FileReader("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt"));
			 
			 String line = bufReader.readLine(); 
			 while (line != null) {
				 myChar.CharInfo().add(line);
				 line = bufReader.readLine(); 
				} 
			 
			 bufReader.close();
			
			
			JPanel panel = new JPanel();
			JPanel panel2 = new JPanel(new FlowLayout());
			JPanel panel3 = new JPanel(new BorderLayout());

		
			panel.add(saveToon);
			
			
			

			panel2.add((charectorClass));	//Select Charector Class
			panel2.add((toonstatsPane)); // Display Randomly Generated Charector Stats
			panel3.add((toonclassDescription), BorderLayout.NORTH);
			panel3.add(saveToon, BorderLayout.SOUTH);		
	
			frame.add((tooncreation), BorderLayout.NORTH);
			frame.add((panel2), BorderLayout.CENTER);
			frame.add((panel3), BorderLayout.SOUTH);
			

			frame.setSize(400,600);

			frame.setVisible(true);

			

		} else {

			new ARDTMenu();
		}

	}

}
