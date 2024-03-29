package DungeonoftheBrutalKing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;


public class GameStartMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, FontFormatException, BadLocationException {

		GameSettings myGameSettings = new GameSettings();
		
		LoadSaveGame myLoadSaveGame = new LoadSaveGame();

		UIManager UI=new UIManager();

		MusicPlayer soundplayer = new MusicPlayer();
		try {
			soundplayer.midiPlayer("Stones.mid");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//***********************************************
		//**** Creating JFrame, JPanel, and JButtons ****
		//***********************************************

		JFrame StartMenuFrame = null;
		JPanel GameTitlePanel, StartButtonPanel = null;
		JButton ContinueGameButton, StartNewGameButton, LoadExistingGameButton, ExitGameButton = null;
		BufferedImage StartGameImage = null;
		JTextArea GameTitleArea = null;

		//JLabel StartImageLabel = null;
		int width, height = 0;

		//***************************************************
		//**** Initializing JFrame, JPanel, and JButtons ****
		//***************************************************

		StartMenuFrame = new JFrame("Dungeon of the Brutal King");
		GameTitlePanel = new JPanel();
		StartButtonPanel = new JPanel();
		StartNewGameButton = new JButton("Start New Game");
		
		ContinueGameButton = new JButton("Continue Current Game");
		LoadExistingGameButton = new JButton("Load Exisiting Game");
		ExitGameButton = new JButton("Exit Game");
		GameTitleArea = new JTextArea();

		//***************************************************
		//******** Getting Screen Width and Height **********
		//***************************************************

		// getScreenSize() returns the size of the screen in pixels
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        width = (int)size.getWidth();

        // height will store the height of the screen
        height = (int)size.getHeight();

      //***************************************************
      //***************** Setting Up JFrame ***************
      //***************************************************

        StartMenuFrame.setLayout(new BorderLayout());
        StartMenuFrame.setSize(width, height);
        StartMenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StartMenuFrame.setUndecorated(true);
        StartMenuFrame.getContentPane().setBackground(myGameSettings.colorLightBrown);
        StartMenuFrame.getContentPane().setForeground(myGameSettings.colorLightBrown);


      //***************************************************
      //**** Setting Up and adding JPanel to JFrame********
      //***************************************************

        GameTitlePanel.setLayout(new BorderLayout());
        StartButtonPanel.setLayout(new FlowLayout());

        GameTitlePanel.setMaximumSize(new Dimension(450, 200));
        StartButtonPanel.setMaximumSize(new Dimension(250, 350));

        StartMenuFrame.add(GameTitlePanel, BorderLayout.CENTER);
        StartMenuFrame.add(StartButtonPanel, BorderLayout.SOUTH);

        StartButtonPanel.setBackground(myGameSettings.colorLightBrown);
        StartButtonPanel.setForeground(myGameSettings.colorLightBrown);

        GameTitlePanel.setBackground(myGameSettings.colorLightBrown);
        GameTitlePanel.setForeground(myGameSettings.colorLightBrown);


      //***************************************************
      //********** Adding Fields to JPanel ****************
      //***************************************************

        StartButtonPanel.add(ContinueGameButton, FlowLayout.LEFT);
        StartButtonPanel.add(StartNewGameButton, FlowLayout.LEFT);
       
        StartButtonPanel.add(LoadExistingGameButton, FlowLayout.CENTER);
        StartButtonPanel.add(ExitGameButton, FlowLayout.RIGHT);


      //***************************************************
      //********** Setting settings for JPanel ************
      //***************************************************

        //StartImageLabel = new JLabel(new ImageIcon(myGameSettings.myStartMenuPicture));
        GameTitlePanel.add(GameTitleArea);
        
       // Font avatarFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\DungeonoftheBrutalKing\\Fonts\\fontAvatar.ttf"));
       // Font fontAvatar = myGameSettings.fontAvatar;
        Font avatarFont = new Font("src\\DungeonoftheBrutalKing\\Fonts\\fontAvatar.ttf", Font.BOLD, 100);

        
       
        GameTitleArea.setEditable(false);

        
        GameTitleArea.setFont(avatarFont);
        GameTitleArea.setForeground(myGameSettings.colorLightYellow);
        GameTitleArea.setBackground(myGameSettings.colorLightBrown);
        GameTitleArea.setText("\n                   Dungeon \n                      of the \n                 Brutal King");


      //***************************************************
      //************ Setting Up Buttons *******************
      //***************************************************

        ContinueGameButton.setPreferredSize(new Dimension(200,50));
        StartNewGameButton.setPreferredSize(new Dimension(200, 50));
        LoadExistingGameButton.setPreferredSize(new Dimension(200, 50));
        ExitGameButton.setPreferredSize(new Dimension(200, 50));

        ContinueGameButton.setBackground(myGameSettings.colorGrey);
        ContinueGameButton.setForeground(myGameSettings.colorWhite);
        
        StartNewGameButton.setBackground(myGameSettings.colorGrey);
        StartNewGameButton.setForeground(myGameSettings.colorWhite);
        
        LoadExistingGameButton.setBackground(myGameSettings.colorGrey);
        LoadExistingGameButton.setForeground(myGameSettings.colorWhite);

        ExitGameButton.setBackground(myGameSettings.colorGrey);
        ExitGameButton.setForeground(myGameSettings.colorWhite);



        File directory = new File(GameSettings.SavedGameDirectory);
        if (directory.isDirectory()) {
           String[] files = directory.list();
           if (files.length > 0) {
              LoadExistingGameButton.setVisible(true);
              ContinueGameButton.setVisible(true);
           } else {
        	  LoadExistingGameButton.setVisible(false);
        	  ContinueGameButton.setVisible(false);
           }
        }

        StartMenuFrame.setVisible(true);

        StartNewGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				 if (JOptionPane.showConfirmDialog(null, "Do you wish to delete your game to start a new one", "Restart Game",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    
					 
					 UI.put("OptionPane.background", myGameSettings.colorLightBrown);
					// UI.put("Panel.background", myGameSettings.colorLightBrown);

				
					 for(File file: directory.listFiles()) {
						if (!file.isDirectory()) {
							file.delete();
						}
					}

				} else {
					try {
						StartingStory.StartGameMessage();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}





				try {

					Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
					MusicPlayer.stopMidi();


					window.dispose();


					try {
						//StartingStory.StartGameMessage();
						CharacterCreation.CharacterCreation();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}});

        ContinueGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myLoadSaveGame.ContinueCurrentGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
        	
        });

        LoadExistingGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MusicPlayer.stopMidi();


				Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
				myLoadSaveGame.LoadGame();
				window.dispose();
			}

        });


        ExitGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}});



	}



}