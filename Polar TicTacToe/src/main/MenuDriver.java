package main;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
class MenuDriver extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameBrain game;
	
	private int psize = 700;
	private boolean isSingleplayer;
	private boolean winMode = false;
	private boolean gameoverMode = false;
	private int totalrounds;
	private String ultiwinner;
	private CardLayout card;
	private JPanel cont;
	private Canvas canvas;

	private JButton single;
	private JButton multi;
	private JButton back;
	private JButton play;
	private JButton next;
	private JButton quit;
	private JButton again;
	private JButton bigquit;
	private JSlider slider;
	private JLabel pl2;
	private JLabel bottom;
	private JLabel sc1;
	private JLabel sc2;
	private JLabel victorytext;
	private JLabel remaininggames;
	private JLabel top;
	
	private Color player1color = Color.pink;
	private Color player2color = Color.blue;
	
	//the main menu panel
	private JPanel Menu;
	//the sub menu panel
	private JPanel SubMenu;
	//the panel in which the entire game runs
	private JPanel Game;
	//the panel where all the info is displayed
	private JPanel hud;
	//the game over panel	
	private JPanel GameOver;
	
	public MenuDriver(GameBrain newgame)
	{
	game = newgame;
	
	//getClass().getResource("/icons/icon.png");
	Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/icon.png"));
    setIconImage(icon);
	
	setTitle("POLAR TIC-TAC-TOE - by sam and alex!");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	
	card = new CardLayout();
	
	cont = new JPanel();
	cont.setLayout(card);
	cont.add("menu", initMenu());
	cont.add("submenu", initSubMenu());
	cont.add("game", initGame());
	
	add(cont);
	setVisible(true);
	pack();
}
	
	
	public JPanel initMenu()
	{
		JLabel background=new JLabel(new ImageIcon(getClass().getResource("/icons/test.png")));
		ImageIcon button = new ImageIcon(getClass().getResource("/icons/button.png"));
		ImageIcon pressed = new ImageIcon(getClass().getResource("/icons/button_press.png"));
		ImageIcon clicked = new ImageIcon(getClass().getResource("/icons/light_button_press.png"));
		
		Menu = new JPanel();
			Menu.setPreferredSize(new Dimension(psize+200, psize));
			Menu.setLayout(new BorderLayout());
			Menu.add(background);
		
		background.setLayout(new GridBagLayout());
		JPanel buttons = new JPanel(); 
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
			buttons.setOpaque(true);
			buttons.setBackground(new Color(0,0,0,0));
				single = new JButton();
					Border emptyBorder = BorderFactory.createEmptyBorder();
					single.setBorder(emptyBorder);
					single.setText("SINGLEPLAYER");
						single.setHorizontalTextPosition(JLabel.CENTER);
						single.setForeground(Color.WHITE);
						single.setFont(new Font("Rockwell", Font.PLAIN, 37));
						single.setIcon(button);
						single.setRolloverIcon(pressed);
						single.setPressedIcon(clicked);
						single.addActionListener(this);	
				multi  = new JButton("Multiplayer");
					multi.setBorder(emptyBorder);
					multi.setText("MULTIPLAYER");
						multi.setHorizontalTextPosition(JLabel.CENTER);
						multi.setForeground(Color.WHITE);
						multi.setFont(new Font("Rockwell", Font.PLAIN, 37));
						multi.setIcon(button);
						multi.setRolloverIcon(pressed);
						multi.setPressedIcon(clicked);
						multi.addActionListener(this);
			buttons.add(Box.createRigidArea(new Dimension(0,400)));
			buttons.add(single);
			buttons.add(Box.createRigidArea(new Dimension(0,40)));
			buttons.add(multi);
		background.add(buttons);
		
		Menu.setVisible(true);
		return Menu;
	}
	
	public JPanel initSubMenu()
	{
		JLabel background=new JLabel(new ImageIcon(getClass().getResource("/icons/test2.png")));
		ImageIcon panel = new ImageIcon(getClass().getResource("/icons/panel.png"));
		ImageIcon button = new ImageIcon(getClass().getResource("/icons/small_button.png"));
		ImageIcon pressed = new ImageIcon(getClass().getResource("/icons/small_button_press.png"));
		ImageIcon clicked = new ImageIcon(getClass().getResource("/icons/small_light_button_press.png"));
		SubMenu = new JPanel();
		SubMenu.setPreferredSize(new Dimension(psize, psize));
		SubMenu.setLayout(new BorderLayout());
		SubMenu.add(background);
		background.setLayout(new GridBagLayout());

		//Create a transparent panel that will be attached to the "background" object. All our content
		//will be contained in here.
		JPanel content = new JPanel();
			content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
			content.setOpaque(true);
			content.setBackground(new Color(0,0,0,0));
			
			Box b1 = Box.createHorizontalBox();
				JLabel pl1 = new JLabel("Player 1");
					pl1.setHorizontalTextPosition(JLabel.CENTER);
					pl1.setForeground(Color.WHITE);
					pl1.setFont(new Font("Rockwell", Font.PLAIN, 27));
					pl1.setIcon(panel);
				pl2 = new JLabel("COMPUTER");
					pl2.setHorizontalTextPosition(JLabel.CENTER);
					pl2.setForeground(Color.WHITE);
					pl2.setFont(new Font("Rockwell", Font.PLAIN, 27));
					pl2.setIcon(panel);
				JLabel top = new JLabel("VS");
					top.setHorizontalTextPosition(JLabel.CENTER);
					top.setForeground(Color.WHITE);
					top.setFont(new Font("Rockwell", Font.BOLD, 20));
				b1.add(pl1);
				b1.add(Box.createRigidArea(new Dimension(30,0)));
				b1.add(top);
				b1.add(Box.createRigidArea(new Dimension(30,0)));
				b1.add(pl2);
			content.add(b1);
			content.add(Box.createRigidArea(new Dimension(0,30)));
			
			Box b2 = Box.createHorizontalBox();
				JLabel colour = new JLabel("Color");
				colour.setHorizontalTextPosition(JLabel.CENTER);
				colour.setForeground(Color.WHITE);
				colour.setFont(new Font("Rockwell", Font.ITALIC, 20));
			
			Box leftbutton = Box.createHorizontalBox();
			    //Create the radio buttons.
			    JRadioButton pinkbut1 = new JRadioButton("Pink");
			    	pinkbut1.setActionCommand("pb1");
			    pinkbut1.setSelected(true);
			    JRadioButton orangebut1 = new JRadioButton("Orange");
			    	orangebut1.setActionCommand("ob1");
			    JRadioButton bluebut1 = new JRadioButton("Blue");
			    	bluebut1.setActionCommand("bb1");
			    //Group the radio buttons.
			    ButtonGroup group = new ButtonGroup();
			    group.add(pinkbut1);
			    group.add(orangebut1);
			    group.add(bluebut1);
			    //Register a listener for the radio buttons.
			    RadioListener myListener = new RadioListener();
			    pinkbut1.addActionListener(myListener);
			    orangebut1.addActionListener(myListener);
			    bluebut1.addActionListener(myListener);
			    leftbutton.add(pinkbut1);
			    leftbutton.add(orangebut1);
			    leftbutton.add(bluebut1);
				
				Box rightbutton = Box.createHorizontalBox();
			    //Create the radio buttons.
			    JRadioButton pinkbut2 = new JRadioButton("Pink");
			    	pinkbut2.setActionCommand("pb2");
			    JRadioButton orangebut2 = new JRadioButton("Orange");
			    	orangebut2.setActionCommand("ob2");
			    JRadioButton bluebut2 = new JRadioButton("Blue");
			    	bluebut2.setSelected(true);
			    	bluebut2.setActionCommand("bb2");
			    //Group the radio buttons.
			    ButtonGroup group1 = new ButtonGroup();
			    group1.add(pinkbut2);
			    group1.add(orangebut2);
			    group1.add(bluebut2);
			    //Register a listener for the radio buttons.
			    pinkbut2.addActionListener(myListener);
			    orangebut2.addActionListener(myListener);
			    bluebut2.addActionListener(myListener);
			   
			    rightbutton.add(pinkbut2);
			    rightbutton.add(orangebut2);
			    rightbutton.add(bluebut2);		
		    b2.add(leftbutton);
		    b2.add(Box.createRigidArea(new Dimension(30,0)));
		    b2.add(colour);
		    b2.add(Box.createRigidArea(new Dimension(30,0)));
		    b2.add(rightbutton);
		    content.add(b2);
		    content.add(Box.createRigidArea(new Dimension(0,30)));
		    
		    Box number = Box.createHorizontalBox();
		    	JLabel numberofgames = new JLabel("Number of games");
		    	numberofgames.setForeground(Color.WHITE);
		    	numberofgames.setFont(new Font("Rockwell", Font.ITALIC, 20));
		    	number.add(numberofgames);
		    	
		    content.add(number);
		    
		    slider = new JSlider(1,15);
		    	slider.setValue(5);
		    	game.setRemain(5);
		    	slider.setMajorTickSpacing(2);
		    	slider.setMinorTickSpacing(1);
		    	slider.setPaintTicks(true);
		    	//Create the label table
		    	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		    	labelTable.put( new Integer( 1 ), new JLabel("1") );
		    	labelTable.put( new Integer( 5 ), new JLabel("5") );
		    	labelTable.put( new Integer( 10 ), new JLabel("10") );
		    	labelTable.put( new Integer( 15 ), new JLabel("15") );
		    	slider.setLabelTable( labelTable );
		    	slider.setPaintLabels(true);
		    	//change state listener that listens if the slider is changed
		    	slider.addChangeListener(new ChangeListener() {
		            public void stateChanged(ChangeEvent e) {
		                System.out.println("Value of the slider is: " + ((JSlider)e.getSource()).getValue());
		                game.setRemain(((JSlider)e.getSource()).getValue());
		            }
		        });
		    content.add(slider);
		    background.add(content);
		    content.add(Box.createRigidArea(new Dimension(0,30)));
		    
		    Box b3 = Box.createHorizontalBox();
			    back = new JButton();
					Border emptyBorder = BorderFactory.createEmptyBorder();
					back.setBorder(emptyBorder);
					back.setText("Main Menu");
					back.setHorizontalTextPosition(JLabel.CENTER);
					back.setForeground(Color.WHITE);
					back.setFont(new Font("Rockwell", Font.PLAIN, 27));
					back.setIcon(button);
					back.setRolloverIcon(pressed);
					back.setPressedIcon(clicked);
					back.addActionListener(this);
				play  = new JButton();
					play.setBorder(emptyBorder);
					play.setText("Let's Play!");
					play.setHorizontalTextPosition(JLabel.CENTER);
					play.setForeground(Color.WHITE);
					play.setFont(new Font("Rockwell", Font.PLAIN, 27));
					play.setIcon(button);
					play.setRolloverIcon(pressed);
					play.setPressedIcon(clicked);
					play.addActionListener(this);
				b3.add(back);
				b3.add(Box.createRigidArea(new Dimension(85,0)));
				b3.add(play);
				
				content.add(b3);
		    return SubMenu;
	}

	public JPanel initGame()
	{	
		ImageIcon panel = new ImageIcon(getClass().getResource("/icons/panel.png"));
		ImageIcon button = new ImageIcon(getClass().getResource("/icons/small_button.png"));
		ImageIcon pressed = new ImageIcon(getClass().getResource("/icons/small_button_press.png"));
		ImageIcon clicked = new ImageIcon(getClass().getResource("/icons/small_light_button_press.png"));
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		Game = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		Game.setBorder(emptyBorder);
        Game.setBackground(Color.white);
		hud = new JPanel();
        hud.setBackground(Color.pink);
        hud.setPreferredSize(new Dimension(200,psize));
        canvas = new Canvas();
        Game.add(canvas);
        Game.add(hud);
        
        Box hudbuttons = Box.createVerticalBox();
        top = new JLabel("Player 1");
			top.setHorizontalTextPosition(JLabel.CENTER);
			top.setForeground(Color.WHITE);
			top.setFont(new Font("Rockwell", Font.PLAIN, 27));
			top.setIcon(panel);
	    bottom = new JLabel("Player 2");
	    	bottom.setHorizontalTextPosition(JLabel.CENTER);
			bottom.setForeground(Color.WHITE);
			bottom.setFont(new Font("Rockwell", Font.PLAIN, 27));
			bottom.setIcon(panel);
		JLabel score = new JLabel("SCORE");
		    score.setHorizontalTextPosition(JLabel.CENTER);
		    score.setForeground(Color.BLACK);
		    score.setFont(new Font("Calibri", Font.BOLD, 27));
		sc1 = new JLabel("0 Games Won");
			sc1.setHorizontalTextPosition(JLabel.CENTER);
			sc1.setForeground(Color.BLACK);
			sc1.setFont(new Font("Calibri", Font.BOLD, 27));
		sc2 = new JLabel("0 Games Won");
			sc2.setHorizontalTextPosition(JLabel.CENTER);
			sc2.setForeground(Color.BLACK);
			sc2.setFont(new Font("Calibri", Font.BOLD, 27));
		victorytext = new JLabel(" ");
			victorytext.setHorizontalTextPosition(JLabel.CENTER);
			victorytext.setForeground(Color.BLACK);
			victorytext.setFont(new Font("Calibri", Font.BOLD, 27));
		remaininggames = new JLabel();
			remaininggames.setHorizontalTextPosition(JLabel.CENTER);
			remaininggames.setForeground(Color.BLACK);
			remaininggames.setFont(new Font("Calibri", Font.BOLD, 27));
        next = new JButton();
		    next.setBorder(emptyBorder);
		    next.setText("Next Game");
		    next.setHorizontalTextPosition(JLabel.CENTER);
		    next.setForeground(Color.WHITE);
		    next.setFont(new Font("Rockwell", Font.PLAIN, 27));
		    next.setIcon(button);
		    next.setRolloverIcon(pressed);
		    next.setPressedIcon(clicked);
		    next.addActionListener(this);
		    next.setEnabled(false);
		quit  = new JButton();
			quit.setBorder(emptyBorder);
			quit.setText("Quit");
			quit.setHorizontalTextPosition(JLabel.CENTER);
			quit.setForeground(Color.WHITE);
			quit.setFont(new Font("Rockwell", Font.PLAIN, 27));
			quit.setIcon(button);
			quit.setRolloverIcon(pressed);
			quit.setPressedIcon(clicked);
			quit.addActionListener(this);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,10)));
		hudbuttons.add(score);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,20)));
		hudbuttons.add(top);
		hudbuttons.add(sc1);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,40)));
		hudbuttons.add(bottom);
		hudbuttons.add(sc2);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,10)));
		hudbuttons.add(victorytext);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,270)));
		hudbuttons.add(remaininggames);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,10)));
		hudbuttons.add(next);
		hudbuttons.add(Box.createRigidArea(new Dimension(0,10)));
		hudbuttons.add(quit);
		hud.add(hudbuttons);
		
        return Game;
	}
 	
	public JPanel initGameOver()
	{
		JLabel background=new JLabel(new ImageIcon(getClass().getResource("/icons/test2.png")));
		
		if(getPl1Score()==getPl2Score())
    		ultiwinner = "Nobody";
    	if(getPl1Score()<getPl2Score() && isSingleplayer)
    		background.setIcon(new ImageIcon(getClass().getResource("/icons/computer_final.png")));
    	if(getPl1Score()<getPl2Score() && !isSingleplayer)
    		background.setIcon(new ImageIcon(getClass().getResource("/icons/player2_final.png")));
    	if(getPl1Score()>getPl2Score())
    		background.setIcon(new ImageIcon(getClass().getResource("/icons/player1_final.png")));
		
    	ImageIcon button = new ImageIcon(getClass().getResource("/icons/button.png"));
		ImageIcon pressed = new ImageIcon(getClass().getResource("/icons/button_press.png"));
		ImageIcon clicked = new ImageIcon(getClass().getResource("/icons/light_button_press.png"));
		ImageIcon panel = new ImageIcon(getClass().getResource("/icons/panel.png"));
		
		GameOver = new JPanel();
			GameOver.setPreferredSize(new Dimension(psize+200, psize));
			GameOver.setLayout(new BorderLayout());
			GameOver.add(background);
		
		background.setLayout(new GridBagLayout());
		JPanel content = new JPanel(); 
			content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
			content.setOpaque(true);
			content.setBackground(new Color(0,0,0,0));
			
			JLabel winner = new JLabel();
				winner.setText(ultiwinner + " wins the tournament!");
				winner.setHorizontalTextPosition(JLabel.CENTER);
			  	winner.setForeground(Color.WHITE);
			    winner.setFont(new Font("Gotham", Font.BOLD, 49));
			    winner.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel score = new JLabel();
			 score.setText("SCORE");
				score.setHorizontalTextPosition(JLabel.CENTER);
				score.setForeground(Color.BLACK);
				score.setFont(new Font("Arial", Font.BOLD, 25));
				score.setAlignmentX(Component.CENTER_ALIGNMENT); 
				
			Box scorelist = Box.createHorizontalBox();
				JLabel score1 = new JLabel(getPl1Score() + " Wins");
					score1.setHorizontalTextPosition(JLabel.CENTER);
					score1.setForeground(Color.WHITE);
					score1.setFont(new Font("Arial", Font.BOLD, 27));
				JLabel score2 = new JLabel(getPl2Score() + " Wins");
					score2.setHorizontalTextPosition(JLabel.CENTER);
					score2.setForeground(Color.WHITE);
					score2.setFont(new Font("Arial", Font.BOLD, 27));
				JLabel top = new JLabel("vs");
					top.setHorizontalTextPosition(JLabel.CENTER);
					top.setForeground(Color.WHITE);
					top.setFont(new Font("Arial", Font.BOLD, 25));
					top.setAlignmentX(Component.CENTER_ALIGNMENT);
			scorelist.add(score1);
			scorelist.add(Box.createRigidArea(new Dimension(115,0)));
			scorelist.add(top);
			scorelist.add(Box.createRigidArea(new Dimension(115,0)));
			scorelist.add(score2);
			scorelist.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			Box labels = Box.createHorizontalBox();
			  JLabel player1 = new JLabel("Player 1");
			  	player1.setHorizontalTextPosition(JLabel.CENTER);
			  	player1.setForeground(Color.WHITE);
			  	player1.setFont(new Font("Rockwell", Font.PLAIN, 27));
			  	player1.setIcon(panel);
			  JLabel player2 = new JLabel("Player 2");
			  	if(isSingleplayer)
			  		player2.setText("COMPUTER");
			  	player2.setHorizontalTextPosition(JLabel.CENTER);
			  	player2.setForeground(Color.WHITE);
			  	player2.setFont(new Font("Rockwell", Font.PLAIN, 27));
			  	player2.setIcon(panel);
			labels.add(player1);
			labels.add(Box.createRigidArea(new Dimension(160,0)));
			labels.add(player2);
			labels.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			Box buttons = Box.createVerticalBox();
				again = new JButton();
					Border emptyBorder = BorderFactory.createEmptyBorder();
					again.setBorder(emptyBorder);
					again.setText("Play Again!");
						again.setHorizontalTextPosition(JLabel.CENTER);
						again.setForeground(Color.WHITE);
						again.setFont(new Font("Rockwell", Font.PLAIN, 37));
						again.setIcon(button);
						again.setRolloverIcon(pressed);
						again.setPressedIcon(clicked);
						again.addActionListener(this);	
				bigquit = new JButton("Multiplayer");
					bigquit.setBorder(emptyBorder);
					bigquit.setText("Exit Game");
						bigquit.setHorizontalTextPosition(JLabel.CENTER);
						bigquit.setForeground(Color.WHITE);
						bigquit.setFont(new Font("Rockwell", Font.PLAIN, 37));
						bigquit.setIcon(button);
						bigquit.setRolloverIcon(pressed);
						bigquit.setPressedIcon(clicked);
						bigquit.addActionListener(this);
			buttons.add(again);
			buttons.add(Box.createRigidArea(new Dimension(0,40)));
			buttons.add(bigquit);
			buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		if(ultiwinner!=null)
		{
			content.add(winner);
			content.add(Box.createRigidArea(new Dimension(0,110)));
		}
		else
			content.add(Box.createRigidArea(new Dimension(0,210)));
		content.add(score);
		//content.add(Box.createRigidArea(new Dimension(0,10)));
		content.add(labels);
		content.add(scorelist);
		content.add(Box.createRigidArea(new Dimension(0,85)));
		content.add(buttons);

		background.add(content);
		GameOver.setVisible(true);
		return GameOver;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 //the source of the button click
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(single))
        {
        	game.setMode("singleplayer");
        	isSingleplayer = true;
        	pl2.setText("COMPUTER");
        	bottom.setText("COMPUTER");
        	card.show(cont, "submenu");
        	//System.out.println("singleplayer button");
        }
        if(j.equals(multi))
        {
        	game.setMode("multiplayer");
        	isSingleplayer = false;
        	pl2.setText("Player 2");
        	bottom.setText("Player 2");
        	card.show(cont, "submenu");
        	//System.out.println("multiplayer button");
        } 
        if(j.equals(back))
        {
        	card.show(cont, "menu");
        	//System.out.println("back button");
        } 
        if(j.equals(play))
        {
        	game.setUp();
        	card.show(cont, "game");
        	totalrounds = game.getData()[2];
        	int r = totalrounds - (game.getData()[2]-1);
        	remaininggames.setText("Round " + r +" of " + totalrounds);
        	System.out.println("Games Remaining: " + game.getData()[2]);
        	 if(game.whosTurn()==1)
	            {
	             	top.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
	            } 
	             else
	             {
	             	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
	             }
        }
        if(j.equals(quit))
        {
        	card.show(cont, "menu");
        	game.nuke();
        	game.setRemain(5);
        	slider.setValue(5);
        	canvas.resetBoard();
        	canvas.updateScore();
        	top.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
        	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
        }
        if(j.equals(next))
        {
            if(gameoverMode)
            {
            	game.boardReset();
            	card.show(cont, "gameover");
                canvas.resetBoard();       
            }
            else
            {
                game.boardReset();
           	    canvas.resetBoard();
           	    int rounds = totalrounds - (game.getData()[2]-1);
           	    if(rounds >= totalrounds)
           	    	remaininggames.setText("Last round!");
           	    else
           	    	remaininggames.setText("Round " + rounds +" of " + totalrounds);
	           	 
           	    if(game.whosTurn()==1)
	            {
	             	top.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
	            } 
	            else
	            {
	            	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
	            }
            }

        }
        if(j.equals(again))
        {
        	card.show(cont, "menu");
        	game.nuke();
        	game.setRemain(5);
        	slider.setValue(5);
        	next.setText("Next Game");
        	canvas.resetBoard();
        	canvas.updateScore();
        	winMode = false;
        	gameoverMode = false;
        }
        if(j.equals(bigquit))
        {
        	System.exit(0);
        }
	}

    public int getPl1Score()
    {
    	return game.getData()[0];
    }
    public int getPl2Score()
    {
    	return game.getData()[1];
    }
    public int getGamesRemaining()
    {
    	return game.getData()[2];
    }
    
	
	/*This is a custom JPanel that will server as our canvas. All polygons will be drawn on this canvas.
	 * 
	 */
	class Canvas extends JPanel implements MouseListener
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Hexashape[][] cube;
        private boolean s;
        private boolean displayWinMode;
        
        public Canvas()
        {            
        	//set background color
        	this.setBackground(Color.white);
        	
        	//create distinct polygons
            cube = new Hexashape[6][5];
            setCubePlots();

            //Add mouse Listener
            addMouseListener(this);

            //Set size to make sure that the whole triangle is shown
            setPreferredSize(new Dimension(psize, psize));
        }

        /** Draws the triangle as this frame's painting */
        public void paintComponent(Graphics g){
        	super.paintComponent(g);
        	Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(3));
            
            //checks if win mode has been activated
            //this is the special mode that makes the screen flash if you win or lose
            if(displayWinMode == true)
            {
                //draw hexa-shape
                for(int i = 0; i<6; i++)
                {
                	for(int k = 0; k<5; k++)
                	{
                		if(s)
                		{
                			g2d.setColor(Color.MAGENTA);
                			g2d.fillPolygon(cube[i][k]);
                			g2d.setColor(Color.black);
                		}
                		else
                		{
                			g2d.setColor(Color.CYAN);
                			g2d.fillPolygon(cube[i][k]);
                			g2d.setColor(Color.black);
                		}
                			
                		g2d.drawPolygon(cube[i][k]);
                	}
                }
            }
            
            else
            {
	            //draw hexa-shape under normal gameplay
	            for(int i = 0; i<6; i++)
	            {
	            	for(int k = 0; k<5; k++)
	            	{
	            		//check if a specific cube is marked to be filled and fill it
	            		if(cube[i][k].isFilled())
	            		{
	            			g2d.setColor(cube[i][k].getColor());
	            			g2d.fillPolygon(cube[i][k]);
	            			g2d.setColor(Color.black);
	            		}
	            		else
	            		{
	            			g2d.setColor(this.getBackground());
	            			g2d.fillPolygon(cube[i][k]);
	            			g2d.setColor(Color.black);
	            		}
	            			
	            		g2d.drawPolygon(cube[i][k]);
	            	}
	            }
            }
        }
        /**A point p is checked against all 30 possible polygons. If the point
         * is contained within one of the polygons, that polygon is returned.
         * Otherwise null is returned.
         */
        public Hexashape checkIfFramed(Point p)
        {
        	for(int i = 0; i<6; i++)
        	{
        		for(int k = 0; k<5; k++)
        		{
        			if(cube[i][k].contains(p))
        			{
        				return cube[i][k];
        			}
        		}
        	}
        	return null;
        }
        
        public void flipFilled(Hexashape shape)
        {
        	shape.setFilled(!shape.isFilled());
        }
        
        /** Called whenever the mouse is pressed.
         * Could be replaced with setting the value of a JLabel, etc. */
        public void mousePressed(MouseEvent e) 
        {
            if(isSingleplayer)
            {
                int r=0; int c=0;
                String coords;
                Point p = e.getPoint();
                Hexashape focus = checkIfFramed(p);               
                if(focus!=null && winMode == false)
                {
                    //check is filled already
                    if(!focus.isFilled())
                    {
                        focus.setColor(player1color);
                        game.setSpace(focus.getX(),focus.getY());
                        flipFilled(focus);
                        repaint();
                        //displayWin(cube[2][2]);
                        if(game.gameWin2())
                        { 
                            checkWin();
                        }
                        else
                        {    
                            
                            coords=game.compComp();
                            System.out.println(coords);
                            r=Integer.parseInt(coords.substring(0,1));
                            c=Integer.parseInt(coords.substring(2,3));
                            //game.setSpace(r,c);
                            cube[r][c].setColor(player2color);
                            cube[r][c].setFilled(true);
                            repaint();
                            checkWin();
                            
                        }
                    }
                    
                }   
            }
            else
            {
                Point p = e.getPoint();
                Hexashape focus = checkIfFramed(p);               
                if(focus!=null && winMode == false)
                {
                    //check is filled already
                    if(!focus.isFilled())
                    {
                        if(game.whosTurn()==1)
                        {
                        	focus.setColor(player1color);
                        	top.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
                        	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
                        } 
                        else
                        {
                        	focus.setColor(player2color);
                        	top.setIcon(new ImageIcon(getClass().getResource("/icons/active_panel.png")));
                        	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
                        }
                            
                        game.setSpace(focus.getX(),focus.getY());
                        flipFilled(focus);
                    }
                    repaint();
                    checkWin();
                }   
            }
        	        	
        }
        
        public void displayWin()
        {
        	if(getGamesRemaining() <= 0)
        		endGame();
        	
        	int repeatTimes = 10;
        	int delay = 75;
        	
        	top.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
        	bottom.setIcon(new ImageIcon(getClass().getResource("/icons/panel.png")));
        	
        	if(isSingleplayer && game.getWinner().contentEquals("Player 2"))
        		victorytext.setText("COMPUTER wins!");
        	else
        		victorytext.setText(game.getWinner() + " wins!");
        	next.setEnabled(true);
        	winMode = true;
        	displayWinMode = true;
        	updateScore();
        	Timer timercasovac = new Timer(delay, new ActionListener() {
        	    private int counter;

        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        s = !s;
        	        repaint();
        	        counter++;
        	        if (counter == repeatTimes) {
        	            ((Timer)e.getSource()).stop();
        	            displayWinMode = false;
        	            repaint();
        	        }
        	    }
        	});
        	timercasovac.start();
        }
        
        public void endGame()
        {
        	gameoverMode = true; 	
        	next.setText("Finish Game");  
        	String winner = null;
        	if(getPl1Score()==getPl2Score())
        		winner = "Nobody";
        	ultiwinner = winner;
        	cont.add("gameover", initGameOver());
        }
        public void checkWin()
        {
        	if(game.gameWin())
            {
            	System.out.println(game.getWinner());
            	displayWin();
            }         		
        }
        
        public void updateScore()
        {
        	int[] focus = game.getData();
        	String score1 = focus[0] + " Games Won";
        	String score2 = focus[1] + " Games Won";
        	
        	sc1.setText(score1);
        	sc2.setText(score2);
        }
        
        
        public void setDWinMode(boolean b)
        {
        	displayWinMode = b;
        }
        
        public void setCubePlots()
        {
        	//create the 30 individual polygons
       		double fw = psize-1;
            double w = fw/10;
            double r = fw/2;
            double cos = Math.cos(Math.toRadians(30));
       		
       		//deca-slice 0        		
            for(int i = 0; i < 5; i++)
            {
            	cube[0][i] = new Hexashape(0,i);
            	cube[0][i].addPoint((int) (r-(cos*(5-i)*w)), (int) (r-(.5*(5-i)*w)));
            	cube[0][i].addPoint((int)r, (int)(i*w));
            	cube[0][i].addPoint((int)r, (int)((i+1)*w));
            	cube[0][i].addPoint((int) (r-(cos*(5-(i+1))*w)), (int) (r-(.5*(5-(i+1))*w)));
            }
            //deca-slice 1
            for(int i = 0; i < 5; i++)
            {
            	cube[1][i] = new Hexashape(1,i);
            	cube[1][i].addPoint((int) (r+(cos*(5-i)*w)), (int) (r-(.5*(5-i)*w)));
            	cube[1][i].addPoint((int)r, (int)(i*w));
            	cube[1][i].addPoint((int)r, (int)((i+1)*w));
            	cube[1][i].addPoint((int) (r+(cos*(5-(i+1))*w)), (int) (r-(.5*(5-(i+1))*w)));
            }
            //deca-slice 2
            for(int i = 0; i < 5; i++)
            {
            	cube[2][i] = new Hexashape(2,i);
            	cube[2][i].addPoint((int) (r+(cos*(5-i)*w)), (int) (r-(.5*(5-i)*w)));
            	cube[2][i].addPoint((int) (r+(cos*(5-(i))*w)), (int) (r+(.5*(5-(i))*w)));
            	cube[2][i].addPoint((int) (r+(cos*(5-(i+1))*w)), (int) (r+(.5*(5-(i+1))*w)));
            	cube[2][i].addPoint((int) (r+(cos*(5-(i+1))*w)), (int) (r-(.5*(5-(i+1))*w)));
            }
            //deca-slice 3
            for(int i = 0; i < 5; i++)
            {
            	cube[3][i] = new Hexashape(3,i);
            	cube[3][i].addPoint((int) (r+(cos*(5-(i))*w)), (int) (r+(.5*(5-(i))*w)));
            	cube[3][i].addPoint((int)r, (int)((10-i)*w));
            	cube[3][i].addPoint((int)r, (int)((9-i)*w));				
            	cube[3][i].addPoint((int) (r+(cos*(5-(i+1))*w)), (int) (r+(.5*(5-(i+1))*w)));
            }
            //deca-slice 4
            for(int i = 0; i < 5; i++)
            {
            	cube[4][i] = new Hexashape(4,i);
            	cube[4][i].addPoint((int)r, (int)((10-i)*w));
            	cube[4][i].addPoint((int) (r-(cos*(5-i)*w)), (int) (r+(.5*(5-i)*w)));
            	cube[4][i].addPoint((int) (r-(cos*(5-(i+1))*w)), (int) (r+(.5*(5-(i+1))*w)));
            	cube[4][i].addPoint((int)r, (int)((9-i)*w));	
          	}
          	//deca-slice 5
          	for(int i = 0; i < 5; i++)
          	{
          		cube[5][i] = new Hexashape(5,i);
          		cube[5][i].addPoint((int) (r-(cos*(5-i)*w)), (int) (r+(.5*(5-i)*w)));
          		cube[5][i].addPoint((int) (r-(cos*(5-i)*w)), (int) (r-(.5*(5-i)*w)));
          		cube[5][i].addPoint((int) (r-(cos*(5-(i+1))*w)), (int) (r-(.5*(5-(i+1))*w)));
          		cube[5][i].addPoint((int) (r-(cos*(5-(i+1))*w)), (int) (r+(.5*(5-(i+1))*w)));
          	}
        }
            
        //Required methods for MouseListener, though the only one you care about is click
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        
        public void resetBoard()
        {
	    	//reset winner notification
        	victorytext.setText(" ");
        	next.setEnabled(false);
        	//turn win mode off
	    	winMode = false;
	    	//turn display win mode off
	    	setDWinMode(false);
	    	//set all hexashapes to not filled
	    	for(int i = 0; i<6; i++)
	    	{
	    		for(int k = 0; k<5; k++)
	    		{
	    			cube[i][k].setFilled(false);
	    		}
	    	}
	    	System.out.println("Games Remaining: " + game.getData()[2]);
    	repaint();
        }
    }
	//this special class exists just to listen for input coming
	//from the six radio buttons that determine player color.
	class RadioListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent e) 
	    {
	        String r = e.getActionCommand();
	    	if(r.equals("pb1"))
	        {
	    		player1color=Color.pink;
	        }
	    	if(r.equals("ob1"))
	        {
	    		player1color=Color.orange;
	        }
	    	if(r.equals("bb1"))
	        {
	    		player1color=Color.blue;
	        }
	    	if(r.equals("pb2"))
	        {
	    		player2color=Color.pink;
	        }
	    	if(r.equals("ob2"))
	        {
	    		player2color=Color.orange;
	        }
	    	if(r.equals("bb2"))
	        {
	    		player2color=Color.blue;
	        }
	    }
	}
}