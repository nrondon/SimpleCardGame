+import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Card Game
 * 
 * @author Naylene Rondon
 * @version V1
 */
public class Game implements ActionListener
{
    JFrame screen;
    JPanel cardPanel;
    JPanel labelPanel;
    JPanel buttonPanel;
    JMenu Menu; 
    JMenuItem Help;
    JMenuBar bar;
    JButton card1;
    JButton card2;
    JButton card3;
    JButton card4;
    JButton newGame;
    JButton endGame;
    JLabel labelScore;
    JLabel labelPar;
    JLabel replaced;
    int score;
    int par;
    int ncard1;
    int ncard2;
    int ncard3;
    int ncard4;
    int counter = 0;
    boolean end = false;
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        newGame();
        
    }
    
    public void newGame()
    {
        end = false;
        counter = 0;
        //SCREEN AND PANEL
        screen = new JFrame();
        screen.setSize(400,400); 
        screen.addWindowListener(new WindowDestroyer()); 
        cardPanel =  new JPanel();
        buttonPanel = new JPanel();
        labelPanel = new JPanel();
        
        //HELP
        Menu = new JMenu("File");
        Help = new JMenuItem("Help");
        Help.addActionListener(this);
        Menu.add(Help);
        bar = new JMenuBar();
        bar.add(Menu);
        screen.setJMenuBar(bar);
        
        //CARDS
        ncard1 = cards();
        ncard2 = cards();
        ncard3 = cards();
        ncard4 = cards();
        
        card1 = new JButton(ncard1+"-");
        card1.setPreferredSize(new Dimension(60, 120));
        card2 = new JButton(ncard2+"--");
        card2.setPreferredSize(new Dimension(60, 120));
        card3 = new JButton(ncard3+"---");
        card3.setPreferredSize(new Dimension(60, 120));
        card4 = new JButton(ncard4+"----");
        card4.setPreferredSize(new Dimension(60, 120));
        
        par = ncard1+ncard2+ncard3+ncard4;
        score = ncard1+ncard2+ncard3+ncard4;
        
        card1.addActionListener(this);
        card2.addActionListener(this);
        card3.addActionListener(this);
        card4.addActionListener(this);
        
        cardPanel.add(card1);
        cardPanel.add(card2);
        cardPanel.add(card3);
        cardPanel.add(card4);
        
        //LABELS
        labelScore = new JLabel("Score:  "+score+"");
        labelPar = new JLabel("Par:   " +par+"");
        labelScore.setPreferredSize(new Dimension(80, 40));
        labelPar.setPreferredSize(new Dimension(80, 40));
        replaced = new JLabel("No card replaced");
        
        labelPanel.add(labelScore);
        labelPanel.add(labelPar);
        labelPanel.add(replaced);
        
        //NEW GAME
        newGame = new JButton("New Game");
        newGame.addActionListener(this);
        buttonPanel.add(newGame);
        
        //END Game
        endGame = new JButton("End Game");
        endGame.addActionListener(this);
        buttonPanel.add(endGame);
        
        //ADD
        screen.add(cardPanel, BorderLayout.NORTH);
        screen.add(labelPanel,BorderLayout.CENTER);
        screen.add(buttonPanel, BorderLayout.SOUTH);
        screen.setVisible(true);  
    }
    
    public int cards()
    {
        Random rand = new Random();
        int val = rand.nextInt(10)+1;
        return val;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String button = e.getActionCommand();
        if(button.equals("New Game"))
        {
            newGame();
        }
        if(button.equals("End Game")&&(end!=true))
        {
            score = score+par;
            labelScore.setText("Final Score");
            labelPar.setText(":  "+score);
            end = true;
        }
        if(button.equals(ncard1+"-")&&(counter<4))
        {
            replaced.setText(ncard1 + " has been replaced.");
            ncard1 = cards();
            card1.setText(ncard1+"-");
            score = ncard1+ncard2+ncard3+ncard4;
            score -= 1;
            labelScore.setText("Score:  "+score);
            counter+=1;
        }
        if(button.equals(ncard2+"--")&&(counter<4))
        {
            replaced.setText(ncard2 + " has been replaced.");
            ncard2 = cards();
            card2.setText(ncard2+"--");
            score = ncard1+ncard2+ncard3+ncard4;
            score -= 1;
            labelScore.setText("Score:  "+score);
            counter+=1;
        }
        if(button.equals(ncard3+"---")&&(counter<4))
        {
            replaced.setText(ncard3 + " has been replaced.");
            ncard3 = cards();
            card3.setText(ncard3+"---");
            score = ncard1+ncard2+ncard3+ncard4;
            score -= 1;
            labelScore.setText("Score:  "+score);
            counter+=1;
        }
        if(button.equals(ncard4+"----")&&(counter<4))
        {
            replaced.setText(ncard4 + " has been replaced.");
            ncard4 = cards();
            card4.setText(ncard4+"----");
            score = ncard1+ncard2+ncard3+ncard4;
            score -= 1;
            labelScore.setText("Score:  "+score);
            counter+=1;
        }
        if(button.equals("Help"))
        {
            String help = "This is a 1-player card game. \nYou can switch 4 of you cards in attempt to get the highest score.\n"+
                "Click on the card you which to replace and once you either \nEnd game or run out of card replacements, \n"+
                "the game ends. Keep playing to beat your score and beat the par.";
                
            JFrame helpWindow = new JFrame();
            JTextArea text = new JTextArea(help);
            helpWindow.add(text);
            helpWindow.setSize(600,200); 
            helpWindow.setVisible(true);
        }
    }
}
