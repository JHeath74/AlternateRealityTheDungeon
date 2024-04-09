package DungeonoftheBrutalKing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import GameEngine.Camera;
import GameEngine.Screen;
import GameEngine.Texture;

/*
 * Games Menu Items
 *
 */
public class MainGameScreen extends JFrame implements Runnable {

	//SavedGameDirectory
	private static final long serialVersionUID = 1L;
	Charecter myChar = Charecter.Singleton();
	GameSettings myGameSettings = new GameSettings();
	LoadSaveGame myGameState = new LoadSaveGame();
	GameMenuItems myGameMenuItems = new GameMenuItems();
	

	public int mapWidth = 15;
	public int mapHeight = 15;
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	public int[] pixels;
	public ArrayList<Texture> textures;
	public Camera camera;
	public Screen screen;
	public static int[][] map = 
		{
			{1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
			{1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
			{1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
			{1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
			{1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
			{1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
			{1,0,3,3,0,3,3,0,2,0,0,0,0,0,2},
			{1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
			{1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
			{1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
			{1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
			{1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
			{1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
		};



	JFrame MainGameScreenFrame = null;
	JPanel p1, p2, p3, p4, GameImagesAndCombatPanel = null;
	JTextArea MessageArea = null;
	JTextField CharNameClassLevel, CharStats, CharStats2, CharXPHPGold = null;
	JLabel DisplayGameAreaLabel = null;
	JMenuBar menuBar = null;
	JMenu gameMenu, charecterMenu, settingsMenu, helpMenu = null;
	JMenuItem newGameMenuItem, LoadSavedGameMenuItem, saveMenuItem,
				exitGameMenuItem,charecterstatsMenuItem,
				charecterinventoryMenuItem, mapMenu, gameSettingsMenuItem,
				aboutMenuItem, helpMenuItem, mapFloor1MenuItem, mapFloor2MenuItem,
				mapFloor3MenuItem, mapFloor4MenuItem = null;

	JSplitPane PicturesAndTextUpdates = null;

	Dimension screenSize = null;
	int width, height = 0;
	Timer timer = null;


	public MainGameScreen() throws IOException {
		

		//Creating Frame
		MainGameScreenFrame = new JFrame("Dungeon of the Brutal King");


		//Adding Frame Preferences and Settings
		MainGameScreenFrame.setLayout(new BorderLayout());
		MainGameScreenFrame.setForeground(myGameSettings.colorBrown);
		MainGameScreenFrame.setUndecorated(true);
		MainGameScreenFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


		// getScreenSize() returns the size
        // of the screen in pixels
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        width = (int)size.getWidth();

        // height will store the height of the screen
        height = (int)size.getHeight();

        //artdmenuframe.pack();
        MainGameScreenFrame.setSize(width, height);


		p1 = new JPanel(new BorderLayout());
		p2 = new JPanel(new BorderLayout());
		p3 = new JPanel(new BorderLayout());
		p4 = new JPanel(new BorderLayout());
		//GameImagesAndCombatPanel = new JPanel(new BorderLayout());

		try {
			myGameState.StartGameLoadCharecter();
		} catch (IOException e2) {

			e2.printStackTrace();
		}

		//*********************************************************
		// -----------  Fields at top of the screen displaying
		//------------ Name and Stats for toon
		//***********************************************************

		CharNameClassLevel = new JTextField();
		CharNameClassLevel.setFont(myGameSettings.fontTimesNewRoman);
		CharNameClassLevel.setBackground(myGameSettings.colorGreen);
		CharNameClassLevel.setForeground(myGameSettings.colorWhite);
		CharNameClassLevel.setColumns(3);
		CharNameClassLevel.setEditable(false);


		CharStats = new JTextField();
		CharStats.setLayout(new FlowLayout());
		CharStats.setFont(myGameSettings.fontTimesNewRoman);
		CharStats.setBackground(myGameSettings.colorBlue);
		CharStats.setForeground(myGameSettings.colorWhite);
		CharStats.setEditable(false);

		CharStats2 = new JTextField();
		CharStats2.setLayout(new FlowLayout());
		CharStats2.setFont(myGameSettings.fontTimesNewRoman);
		CharStats2.setBackground(myGameSettings.colorBlue);
		CharStats2.setForeground(myGameSettings.colorWhite);
		CharStats2.setEditable(false);


		CharXPHPGold = new JTextField();
		CharXPHPGold.setLayout(getLayout());
		CharXPHPGold.setFont(myGameSettings.fontTimesNewRoman);
		CharXPHPGold.setBackground(myGameSettings.colorPurple);
		CharXPHPGold.setForeground(myGameSettings.colorWhite);
		CharXPHPGold.setColumns(3);
		CharXPHPGold.setEditable(false);


		ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				 CharNameClassLevel.setText("Name: " + myChar.CharInfo.get(0) + "\t\t"
						 					+ "Level: " + myChar.CharInfo.get(2) + "\t\t"
						 					+ "Experience: " + myChar.CharInfo.get(3));



				CharStats.setText("Stamina:\t"
								+ "Charisma: \t"
								+ "Strength: \t"
								+ "Intelligence:\t "
								+ "Wisdom: \t"
								+ "Agility: \t");

				CharStats2.setText(myChar.CharInfo.get(5) + "\t" +
								   myChar.CharInfo.get(6) + "\t" +
								   myChar.CharInfo.get(7) + "\t" +
								   myChar.CharInfo.get(8) + "\t" +
								   myChar.CharInfo.get(9) + "\t" +
								   myChar.CharInfo.get(10));


				CharXPHPGold.setText("Hit Points: " + myChar.CharInfo.get(4) + "\t\t"
									+ "Gold: " + myChar.CharInfo.get(11) + "\t\t"
									+ "Gems: " + myChar.CharInfo.get(12) + "\t\t"
									+ "Dungeon Level:");
			}
		};
		timer = new Timer(100, task); // Execute task each 100 miliseconds
		timer.setRepeats(true);
		timer.start();

		// ****************************************************************************************
		// ----------------------------Menu Bar and Menu Items------------------------------------
		// ****************************************************************************************

		// Create the menu bar.
		menuBar = new JMenuBar();

		//Menu Bar Preferences

		menuBar.setPreferredSize(new Dimension(25,35));
		menuBar.setFont(new Font("sans-serif", Font.ROMAN_BASELINE, 22));
		menuBar.setBackground(myGameSettings.colorPlum);

		// Build the menu.
		MainGameScreenFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// *************************************************************
		// ------------------- Adding Menu Headers -----------------------------
		// *************************************************************

		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);

		charecterMenu = new JMenu("Charecter");
		charecterMenu.setMnemonic(KeyEvent.VK_C);

		settingsMenu = new JMenu("Preferences");
		settingsMenu.setMnemonic(KeyEvent.VK_P);

		helpMenu = new JMenu("About");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		// ****************************************************************************************
		// ----------------------------Adding Menu Items-------------------------------------------
		// ****************************************************************************************
		// ****************************************************************************************
		// ----------------------------Load, Save and Exit------------------------------------
		// ****************************************************************************************

		newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newGameMenuItem.getAccessibleContext().setAccessibleDescription("New Game");
		newGameMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(MainGameScreenFrame,
						"Are you sure you wish to delete your current game and start a new one?", "Start New Game?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					MainGameScreenFrame.dispose();
					try {
						BufferedWriter writer = Files.newBufferedWriter(Paths
								.get("src//AlternateRealityTheDungeon//TextFiles//SaveGame//InitialCharecterSave.txt"));
						writer.write("");
						writer.flush();
					} catch (IOException e1) {

						e1.printStackTrace();
					}

					File d = new File(GameSettings.SavedGameDirectory);

					for (File file : d.listFiles()) {
						if (!file.isDirectory()) {
							file.delete();
						}
					}

					try {

						CharacterCreation.CharacterCreation();

					} catch (IOException | InterruptedException e1) {

						e1.printStackTrace();
					}

					MainGameScreenFrame.dispose();

				} else if (result == JOptionPane.NO_OPTION) {

				} else {

				}

			}
		});

		// ***************************************************************************
		// ----------------------------- Loading a Previously Saved Game--------------
		// ***************************************************************************

		LoadSavedGameMenuItem = new JMenuItem("Load Saved Game");
		LoadSavedGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		LoadSavedGameMenuItem.getAccessibleContext().setAccessibleDescription("Load Saved Game");
		LoadSavedGameMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				myGameState.LoadGame();

			}
		});

		// ***************************************************************************
		// ----------------------------- Saving Your Game ----------------------------
		// ***************************************************************************

		saveMenuItem = new JMenuItem("Save Current Game");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveMenuItem.getAccessibleContext().setAccessibleDescription("Save Current Game");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					myGameState.SaveGame();
				} catch (IOException | ParseException e1) {

					e1.printStackTrace();
				}
			}
		});

		// ***************************************************************************
		// ----------------------------- Exiting Your Game ---------------------------
		// ***************************************************************************

		exitGameMenuItem = new JMenuItem("Exit");
		exitGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		exitGameMenuItem.getAccessibleContext().setAccessibleDescription("Exit Game");
		exitGameMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		// ****************************************************************************************
		// ----------------------------Stats, Inventory and Maps ---------------------------------
		// ****************************************************************************************

		charecterstatsMenuItem = new JMenuItem("Stats");
		charecterstatsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		charecterstatsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myGameMenuItems.Stats();

			}
		});

		charecterinventoryMenuItem = new JMenuItem("Inventory");
		charecterinventoryMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		charecterinventoryMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myGameMenuItems.Inventory();

			}
		});

		mapMenu = new JMenu("Map");
		mapMenu.setMnemonic(KeyEvent.VK_M);

		// ****************************************************************************************
		// ----------------------------Settings and Preferences------------------------------------
		// ****************************************************************************************

		gameSettingsMenuItem = new JMenuItem("Settings");
		gameSettingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		gameSettingsMenuItem.getAccessibleContext().setAccessibleDescription("Game Settings");
		gameSettingsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Settings Such as
				// Auto Save Timer
				// Set Brightness
				// Set Resolution
				// Set Colors
				// Set Volume

				System.out.print("Settings and Preferences");

			}
		});

		// ****************************************************************************************
		// ---------------------------- Help and About -------------------------------------------
		// ****************************************************************************************

		// Help and About Menu Items -- About the Game and any Help Information
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription("About Game");
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
/*
				try {
					myCombat.CombatEncouter();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
*/
				JFrame frame = new JFrame("Help Information");
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
					helptext.read(
							new InputStreamReader(Objects.requireNonNull(
									getClass().getResourceAsStream("/DungeonoftheBrutalKing/TextFiles/About.txt"))),
							null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				frame.add(p);
				frame.getContentPane().add(new JScrollPane(helptext), BorderLayout.NORTH);
				frame.setSize(120, 120);

				p.add(helpbutton, BorderLayout.SOUTH);
				helpbutton.setSize(120, 120);

				frame.pack();
				frame.setVisible(true);

			}
		});

		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		helpMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		helpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame frame = new JFrame("Help Information");
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

				try {
					// Read some text from the resource file to display in
					// the JTextArea.
					helptext.read(
							new InputStreamReader(Objects.requireNonNull(
									getClass().getResourceAsStream("/DungeonoftheBrutalKing/TextFiles/Help.txt"))),
							null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				frame.add(p);
				frame.getContentPane().add(new JScrollPane(helptext), BorderLayout.NORTH);
				frame.setSize(120, 120);

				p.add(helpbutton, BorderLayout.SOUTH);
				helpbutton.setSize(120, 120);

				try {
					MainGameScreen game = new MainGameScreen();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.pack();
				frame.setVisible(true);

			}
		});

		// **********************************************************
		// ---------- Adding Menu Items to the Menu Selections-------
		// **********************************************************

		gameMenu.add(newGameMenuItem);
		gameMenu.add(LoadSavedGameMenuItem);
		gameMenu.add(saveMenuItem);
		gameMenu.add(exitGameMenuItem);

		charecterMenu.add(charecterstatsMenuItem);
		charecterMenu.add(charecterinventoryMenuItem);
		charecterMenu.add(mapMenu);

		settingsMenu.add(gameSettingsMenuItem);

		helpMenu.add(aboutMenuItem);
		helpMenu.add(helpMenuItem);

		// **************************************************************
		// ---------- Adding Menu Selection Items to the Menu bar--------
		// **************************************************************
		menuBar.add(gameMenu);
		menuBar.add(charecterMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);

		// ***************************************************************
		// --------------------- Map Sub Menu Items----------------------
		// **************************************************************

		mapFloor1MenuItem = new JMenuItem("Floor 1");
		mapFloor1MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mapFloor1MenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		mapFloor2MenuItem = new JMenuItem("Floor 2");
		mapFloor2MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		mapFloor2MenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		mapFloor3MenuItem = new JMenuItem("Floor 3");
		mapFloor3MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK));
		mapFloor3MenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});
		mapFloor4MenuItem = new JMenuItem("Floor 4");
		mapFloor4MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK));
		mapFloor4MenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});
		mapMenu.add(mapFloor1MenuItem);
		mapMenu.add(mapFloor2MenuItem);
		mapMenu.add(mapFloor3MenuItem);
		mapMenu.add(mapFloor4MenuItem);

		// **************************************************************************************
		// ----------------------------Setting Up JSplitPane ------------------------------------
		// *************************************************************************************

		
		
		
		
		
		
		MessageArea = new JTextArea("JTextArea AMessageArea - Game Text Updates");
		MessageArea.setBackground(myGameSettings.colorLightBrown);
		MessageArea.setForeground(myGameSettings.colorLightYellow);

		PicturesAndTextUpdates = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		PicturesAndTextUpdates.setVisible(true);
		PicturesAndTextUpdates.setResizeWeight(.90d);
		PicturesAndTextUpdates.setLeftComponent(GameImagesAndCombatPanel);
		PicturesAndTextUpdates.setRightComponent(MessageArea);
		PicturesAndTextUpdates.setVisible(true);

		MessageArea.setFont(myGameSettings.fontLomoCopyLTStdMidi);


		// ****************************************************************
		// ----------------------------Adding Menu Bar to the JFrame ------
		// ****************************************************************
		MainGameScreenFrame.setJMenuBar(menuBar);

		// ***************************************************************
		// -------------------Setting Up Menubar and JFrame --------------
		// ****************************************************************



		MainGameScreenFrame.add(PicturesAndTextUpdates, BorderLayout.CENTER);
		MainGameScreenFrame.add(p1, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.NORTH);
		p1.add(p3, BorderLayout.CENTER);
		p1.add(p4, BorderLayout.SOUTH);
		p2.add(CharNameClassLevel);
		p3.add(CharStats, BorderLayout.NORTH);
		p3.add(CharStats2, BorderLayout.SOUTH);
		p4.add(CharXPHPGold);

		GameImagesAndCombatPanel = new JPanel(new BorderLayout();
		GameImagesAndCombatPanel.setPreferredSize(640,480)
		DisplayGameAreaLabel = new JLabel();
		GameImagesAndCombatPanel.add(DisplayGameAreaLabel, BorderLayout.WEST);
		DisplayGameAreaLabel(new ImageIcon("MenuBar.png"));
		
	//      thread = new Thread(this);
		
	//	image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
	//	pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	//	textures = new ArrayList<Texture>();
	//	textures.add(Texture.wood);
	//	textures.add(Texture.brick);
	//	textures.add(Texture.bluestone);
	//	textures.add(Texture.stone);
	//	camera = new Camera(4.5, 4.5, 1, 0, 0, -.66);
	//	screen = new Screen(map, mapWidth, mapHeight, textures, 640, 480);
	//	addKeyListener(camera);
		DisplayGameAreaLabel.setText("Does This Work?");
	//	DisplayGameAreaLabel.setIcon(new ImageIcon(image));
		
	//	setSize(640, 480);
	//	setResizable(false);
	//	setTitle("3D Engine");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setBackground(Color.black);
	//	setLocationRelativeTo(null);
	//	setVisible(true);
	//	start();
		
		
		
		
		
		MainGameScreenFrame.setVisible(true);


	}
	
	private synchronized void start() {
		running = true;
	//	thread.start();
	}
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		bs.show();
	}
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;//60 times per second
		double delta = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta = delta + ((now-lastTime) / ns);
			lastTime = now;
			while (delta >= 1)//Make sure update is only happening 60 times a second
			{
				//handles all of the logic restricted time
				screen.update(camera, pixels);
				camera.update(map);
				delta--;
			}
			render();//displays to the screen unrestricted time
		}
	}

}