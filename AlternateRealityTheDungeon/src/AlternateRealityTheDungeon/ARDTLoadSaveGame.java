package AlternateRealityTheDungeon;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ARDTLoadSaveGame
{

	ARTDCharecter myChar = new ARTDCharecter();
	
	public void SaveGame() throws IOException, ParseException
	{
		
		 
		//LocalDateTime CurrentDateTime = LocalDateTime.now();
		
		//"src//AlternateRealityTheDungeon//TextFiles//SaveGame//Save.txt"
		
		String pattern = "HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("22:00:03");
		
		//the line here is broken    java.io.FileNotFoundException: Invalid file path
		
		String GameSaveDateTime = "Save " + date + ".txt";
		FileWriter writer = new FileWriter("src//AlternateRealityTheDungeon//TextFiles//SaveGame//SaveGame.txt"); 
				
		for(String Charinfo: myChar.CharInfo()) {
		  writer.write(Charinfo + System.lineSeparator());
		}
		writer.close();
		
		/*
		 * JFrame saveGame = new JFrame("Save Current Game"); JPanel sG = new JPanel(new
		 * BorderLayout()); JButton save = new JButton("Save Game");
		 */
		
		JOptionPane.showMessageDialog(null, "Game Save: " + GameSaveDateTime);
		
		/*
		 * saveGame.add(sG, BorderLayout.CENTER); saveGame.add(save,
		 * BorderLayout.SOUTH);
		 */
		
		/*
		 * saveGame.setLocationRelativeTo(null); saveGame.setSize(640, 480);
		 * saveGame.setVisible(true);
		 */
	}
	
	public void LoadGame()
	{
		JFrame loadGame = new JFrame("Load Game");
		JPanel lg = new JPanel(new BorderLayout());
		JButton load = new JButton("Load Game");
		JComboBox loadGameSelection = new JComboBox();
		loadGameSelection.list();
		
		
		lg.add(loadGameSelection);
		loadGame.add(lg, BorderLayout.CENTER);
		loadGame.add(load, BorderLayout.SOUTH);
			
		loadGame.setLocationRelativeTo(null);
		loadGame.setSize(640, 480);
		
		loadGame.setVisible(true);
		
	}
	
	public void StartGameLoadCharecter()
	{
		
	}
	
	
}
