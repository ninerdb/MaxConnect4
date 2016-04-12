/*
 * ConnectFour.java
 *
 * Created on December 5, 2003, 5:56 PM
 */

/**
 *
 * @author  Chen Zhang
 */
import java.io.*;
public class ConnectFour {
   private static Player  p1;
   private static Player  p2;
    
    /** Creates a new instance of ConnectFour */
    public ConnectFour() {
        p1 = new HumanPlayer();
        p2 = new HumanPlayer();
    }
    
    /**
     * @param args the command line arguments
     */
    static PrintStream fileout=null;
    public static void main(String[] args) {
    	final PrintStream origout = System.out;
    	
		try {
			fileout = new PrintStream("result.txt");
			System.setOut(new PrintStream(new OutputStream() {
	    	    @Override
	    	    public void write(int b) throws IOException {
	    	        origout.write(b);
	    	        fileout.write(b);
	    	    }
	    	}));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	System.out.println("\t\t Welcome to connect 4");
		System.out.println("Select the player you want to choose to play against computer");
		System.out.println("1. Player 1");
		System.out.println("2. Player 2");
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader br = new BufferedReader( isr );
        String s = null;
        try {
           s = br.readLine();
        }
        catch ( IOException ioe ) {
        }
        int n = Integer.parseInt(s);
        
        if (n == 1) {
            p1 = new HumanPlayer();
            p2 =new MinMaxPlayer();
        }
        else
        {
        	 p1 = new MinMaxPlayer();
             p2 =new HumanPlayer();
        }
        
       
                SimpleBoard BoardA = new SimpleBoard();
        
        p1.setMove(-2);
        p2.setMove(-2);
        
        System.out.println();
        while (!BoardA.over("main") ){
           
           System.out.println(BoardA);
           System.out.println("Score");
			System.out.println("Player 1: " + SimpleBoard.player1_score);
			System.out.println("Player 2  " + SimpleBoard.player2_score);
           System.out.print("Player "+BoardA.next());
           System.out.print(" next move:");
           
           if (BoardA.next() == 1) p1.go(BoardA);
           else p2.go(BoardA);
                       
        }
        System.out.println(BoardA);
        System.out.println("Score");
		System.out.println("Player 1: " + SimpleBoard.player1_score);
		System.out.println("Player 2  " + SimpleBoard.player2_score);
		
		if(SimpleBoard.player1_score > SimpleBoard.player2_score)
		{
			System.out.println("Player 1 wins");
		}
		else if(SimpleBoard.player2_score > SimpleBoard.player1_score)
		{
			System.out.println("Player 2 wins");
		}
		else
		{
			System.out.println("Match Draw");
		}
		fileout.close();
    }
    
}

