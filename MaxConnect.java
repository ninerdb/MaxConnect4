/* 
    This program implements the 'Connect four' game (exercise 7.20 in the book, 8th edition)
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class MaxConnect {

	private static char color;
	private static int scoreR = 0, scoreY = 0;
	private static char[][] checkedFields = new char[6][7];
	private static int counter = 0;
	private static int[] checkColumn = new int[7];
	private static int[] modifiedColumn = new int[7];
	private static int[] checkRow = new int[7];
	private static int[] modifiedRow = new int[7];
	private static int[] checkDiagonalLower = new int[7];
	private static int[] modifiedDiagonalLower = new int[7];
	private static int[] checkDiagonalUpper = new int[7];
	private static int[] modifiedDiagonalUpper = new int[7];
	private static int[] checkSubDiagonalLeft = new int[7];
	private static int[] modifiedSubDiagonalLeft = new int[7];
	private static int[] checkSubDiagonalRight = new int[7];
	private static int[] modifiedSubDiagonalRight = new int[7];
	private static int[][] positionRow = new int[7][7];
	private static int[][] positionColumn = new int[7][7];
	private static int[][] positionDiagonalUpper = new int[7][7];
	private static int[][] positionDiagonalLower = new int[7][7];
	private static int[][] positionSubDiagonalLeft = new int[7][7];
	private static int[][] positionSubDiagonalRight = new int[7][7];
	private static PrintWriter printWriter;
	private static ArrayList<String> outputText = new ArrayList<String>();
	private static char[] c = new char[7];
	

	// This method attempts to put the disk of the given color in the given
	// column.
	// It returns true if successful and false if the column is filled and we
	// cannot
	// put a disk.
	public static boolean putDisk(char[][] field, int column, char color) {
		// If the first disk is there, the column is filled, returning false.
		if (field[0][column] != ' ')
			return false;

		// Check all elements in the column.
		for (int row = 0; row < 6; row++) {
			// If we found something, which means if the character is not
			// zero character
			if (field[row][column] != ' ') {
				// Put the disk on top of the current one.
				field[row - 1][column] = color;
				modifiedColumn[column] = 9;
				modifiedRow[row - 1] = 9;
				modifiedDiagonalUpper[row - 1] = 9;
				modifiedDiagonalLower[row - 1] = 9;
				modifiedSubDiagonalLeft[row - 1] = 9;
				modifiedSubDiagonalRight[row - 1] = 9;
				c[column]++;
				return true;
			}
		}

		// If no other disks found, we place this disk at the bottom.
		field[5][column] = color;
		modifiedColumn[column] = 9;
		modifiedRow[5] = 9;
		modifiedDiagonalUpper[5] = 9;
		modifiedDiagonalLower[5] = 9;
		modifiedSubDiagonalLeft[5] = 9;
		modifiedSubDiagonalRight[5] = 9;
		c[column]++;
		return true;

	}

	public static boolean isConsecutiveFour(char[][] field, char color) {
		int noOfRows = field.length;
		int noOfColumns = field[0].length;

		// Check rows
		for (int i = 0; i < 6; i++) {

			// System.out.println(field[i]);
			if (isConsecutiveFour(field[i], color, "row", i)) {

				// if((!(checkRow[i]==i)) || !(modifiedRow[i]== checkRow[i]))
				// {
				if (color == '1') {

					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Row R");
					checkedFields[i] = field[i];
					checkRow[i] = i;
					// System.out.print("Value"+checkRow[i]);
					modifiedRow[i] = checkRow[i];
					// return false;
				} else if (color == '2') {
					// System.out.println("counter"+counter);
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Row Y");
					checkedFields[i] = field[i];
					checkRow[i] = i;
					// System.out.print("Value"+checkRow[i]);
					modifiedRow[i] = checkRow[i];
					// return false;
				}

				// }
				// return false;
			}
		}

		// Check columns
		for (int j = 0; j < noOfColumns; j++) {
			char[] column = new char[noOfRows];
			// Get a column into an array
			for (int k = 0; k < noOfRows; k++)
				column[k] = field[k][j];

			if (isConsecutiveFour(column, color, "column", j)) {

				// if((!(checkColumn[j]==j)) || !(modifiedColumn[j]==
				// checkColumn[j]))
				// {
				if (color == '1') {
					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Column R");

					checkColumn[j] = j;
					// System.out.print("Value"+checkColumn[j]);
					modifiedColumn[j] = checkColumn[j];
					// return false;
				} else if (color == '2') {
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Column Y");
					checkColumn[j] = j;
					// System.out.print("Value"+checkColumn[j]);
					modifiedColumn[j] = checkColumn[j];
					// return false;
				}

				// }

			}
			// return false;
		}
		// System.out.println("Color-->" +color);

		// Check major diagonal (lower)
		for (int k = 0; k < noOfRows - 3; k++) {
			int noOfElementsInDiagonal = Math.min(noOfRows - k, noOfColumns);
			char[] diagonal = new char[noOfElementsInDiagonal];
			for (int j = 0; j < noOfElementsInDiagonal; j++)
				diagonal[j] = field[j + k][j];

			if (isConsecutiveFour(diagonal, color, "diagonalLower", k))
			// return true;
			{
				// if((!(checkDiagonalLower[k]==k)) ||
				// !(modifiedDiagonalLower[k]== checkDiagonalLower[k]))
				// {
				if (color == '1') {
					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Diagonal lower R");

					checkDiagonalLower[k] = k;
					// System.out.print("Value"+checkDiagonalLower[k]);
					modifiedDiagonalLower[k] = checkDiagonalLower[k];
					// return false;
				} else if (color == '2') {
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Diagonal lower Y");
					checkDiagonalLower[k] = k;
					// System.out.print("Value"+checkDiagonalLower[k]);
					modifiedDiagonalLower[k] = checkDiagonalLower[k];
					// return false;
				}

				// }
			}

			// System.out.println("Color-->" +color);

		}

		// Check major diagonal (upper)
		for (int j = 1; j < noOfColumns - 3; j++) {
			int noOfElementsInDiagonal = Math.min(noOfColumns - j, noOfRows);
			char[] diagonal = new char[noOfElementsInDiagonal];
			for (int k = 0; k < noOfElementsInDiagonal; k++)
				diagonal[k] = field[k][k + j];

			if (isConsecutiveFour(diagonal, color, "diagonalUpper", j))
			// return true;
			{
				// if((!(checkDiagonalUpper[j]==j)) ||
				// !(modifiedDiagonalUpper[j]== checkDiagonalUpper[j]))
				// {
				if (color == '1') {
					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Diagonal upper R");

					checkDiagonalUpper[j] = j;
					// System.out.print("Value"+checkDiagonalUpper[j]);
					modifiedDiagonalUpper[j] = checkDiagonalUpper[j];
					// return false;
				} else if (color == '2') {
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Diagonal upper Y");
					checkDiagonalUpper[j] = j;
					// System.out.print("Value"+checkDiagonalUpper[j]);
					modifiedDiagonalUpper[j] = checkDiagonalUpper[j];
					// return false;
				}

				// }
			}

			// System.out.println("Color-->" +color);

		}

		// Check sub-diagonal (left part)
		for (int j = 3; j < noOfColumns; j++) {
			int noOfElementsInDiagonal = Math.min(j + 1, noOfRows);
			char[] diagonal = new char[noOfElementsInDiagonal];
			// System.out.println("Sub Diagonal left");
			for (int k = 0; k < noOfElementsInDiagonal; k++)
				diagonal[k] = field[k][j - k];

			if (isConsecutiveFour(diagonal, color, "subDiagonalLeft", j))
			// return true;
			{

				/*
				 * if((!(checkSubDiagonalLeft[j]==j)) ||
				 * !(modifiedSubDiagonalLeft[j]== checkSubDiagonalLeft[j])) {
				 */
				if (color == '1') {
					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Sub Diagonal left R");

					checkSubDiagonalLeft[j] = j;
					// System.out.print("Value"+checkSubDiagonalLeft[j]);
					modifiedSubDiagonalLeft[j] = checkSubDiagonalLeft[j];
					// return false;
				} else if (color == '2') {
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Sub Diagonal left Y");
					checkSubDiagonalLeft[j] = j;
					// System.out.print("Value"+checkSubDiagonalLeft[j]);
					modifiedSubDiagonalLeft[j] = checkSubDiagonalLeft[j];
					// return false;
				}

				// }
			}

			// System.out.println("Color-->" +color);

		}

		// Check sub-diagonal (right part)
		for (int k = 1; k < noOfRows - 3; k++) {
			int noOfElementsInDiagonal = Math.min(noOfRows - k, noOfColumns);
			char[] diagonal = new char[noOfElementsInDiagonal];

			for (int j = 0; j < noOfElementsInDiagonal; j++)
				diagonal[j] = field[j + k][noOfColumns - j - 1];

			if (isConsecutiveFour(diagonal, color, "subDiagonalRight", k))
			// return true;
			{
				// if((!(checkSubDiagonalRight[k]==k)) ||
				// !(modifiedSubDiagonalRight[k]== checkSubDiagonalRight[k]))
				// {
				if (color == '1') {
					for (int itr = 0; itr < counter; itr++) {
						scoreR++;
					}
					counter = 0;
					// System.out.println("Sub Diagonal right R");

					checkSubDiagonalRight[k] = k;
					// System.out.print("Value"+checkSubDiagonalRight[k]);
					modifiedSubDiagonalRight[k] = checkSubDiagonalRight[k];
					// return false;
				} else if (color == '2') {
					for (int itr = 0; itr < counter; itr++) {
						scoreY++;
					}
					counter = 0;
					// System.out.println("Sub Diagonal right Y");
					checkSubDiagonalRight[k] = k;
					// System.out.print("Value"+checkSubDiagonalRight[k]);
					modifiedSubDiagonalRight[k] = checkSubDiagonalRight[k];
					// return false;
				}

				// }
			}

			// System.out.println("Color-->" +color);
		}

		return false;
	}

	public static void computerMove(char[][] bo,char me,char oppo) {
		int noOfRows = bo.length;
		int noOfColumns = bo[0].length;
		
		

        int[] h= new int[7];
        for (int i = 0; i < 7; i++) h[i]=0;
        

        for (int i=0; i<7; i++)
        {
          if (c[i]==6) h[i]=0; else{
            int col = i;
            int row = 5 - c[i];
           
          //  System.out.print("row "+row+" column" +col);
            
            if ((col>=3) 
                && (bo[row][col-1] == me)
                && (bo[row][col-2] == me)
                && (bo[row][col-3] == me))
                h[i]=h[i]+16;
            //right
            if ((col<=3) 
                && (bo[row][col+1] == me)
                && (bo[row][col+2] == me)
                && (bo[row][col+3] == me))
                h[i]=h[i]+16;
            //check y direction
            if ((row<=2) 
                && (bo[row+1][col] == me)
                && (bo[row+2][col] == me)
                && (bo[row+3][col] == me))
                h[i]=h[i]+16;
            //check left diagonal
            if ((col>=3) && (row<=2)
                && (bo[row+1][col-1] == me)
                && (bo[row+2][col-2] == me)
                && (bo[row+3][col-3] == me))
                h[i]=h[i]+16;
            
            if ((col<=3) && (row<=2)
                && (bo[row+1][col+1] == me)
                && (bo[row+2][col+2] == me)
                && (bo[row+3][col+3] == me))
                h[i]=h[i]+16;
            
            if ((col>=3) && (row>=3)
                && (bo[row-1][col-1] == me)
                && (bo[row-2][col-2] == me)
                && (bo[row-3][col-3] == me))
                h[i]=h[i]+16;
            
            if ((col<=3) && (row>=3)
                && (bo[row-1][col+1] == me)
                && (bo[row-2][col+2] == me)
                && (bo[row-3][col+3] == me))
                h[i]=h[i]+16;
            
            if ((col>=2) 
                && (bo[row][col-1] == me)
                && (bo[row][col-2] == me))
                h[i]=h[i]+4;
            //right
            if ((col<=4) 
                && (bo[row][col+1] == me)
                && (bo[row][col+2] == me))
                h[i]=h[i]+4;
            //check y direction
            if ((row<=3) 
                && (bo[row+1][col] == me)
                && (bo[row+2][col] == me))
                h[i]=h[i]+4;
            //check left diagonal
            if ((col>=2) && (row<=3)
                && (bo[row+1][col-1] == me)
                && (bo[row+2][col-2] == me))
                h[i]=h[i]+4;
            
            if ((col<=4) && (row<=3)
                && (bo[row+1][col+1] == me)
                && (bo[row+2][col+2] == me))
                h[i]=h[i]+4;
            
            if ((col>=2) && (row>=2)
                && (bo[row-1][col-1] == me)
                && (bo[row-2][col-2] == me))
                h[i]=h[i]+4;
            
            if ((col<=4) && (row>=2)
                && (bo[row-1][col+1] == me)
                && (bo[row-2][col+2] == me))
                h[i]=h[i]+4;
            
            if ((col>=1) 
                && (bo[row][col-1] == me))
                h[i]=h[i]+2;
            //right
            
            if ((col<=5) 
                && (bo[row][col+1] == me))
                h[i]=h[i]+2;
            //check y direction
            if ((row<=4) 
                && (bo[row+1][col] == me))
                h[i]=h[i]+2;
            //check left diagonal
            if ((col>=1) && (row<=4)
                && (bo[row+1][col-1] == me))
                h[i]=h[i]+2;
            
            if ((col<=5) && (row<=4)
                && (bo[row+1][col+1] == me))
                h[i]=h[i]+2;
            
            if ((col>=1) && (row>=1)
                && (bo[row-1][col-1] == me))
                h[i]=h[i]+2;
            
            if ((col<=5) && (row>=1)
                && (bo[row-1][col+1] == me))
                h[i]=h[i]+2;
            
            //check x direction.
            //left
            if ((col>=3) 
                && (bo[row][col-1] == oppo)
                && (bo[row][col-2] == oppo)
                && (bo[row][col-3] == oppo))
                h[i]=h[i]+8;
            //right
            if ((col<=3) 
                && (bo[row][col+1] == oppo)
                && (bo[row][col+2] == oppo)
                && (bo[row][col+3] == oppo))
                h[i]=h[i]+8;
            //check y direction
            if ((row<=2) 
                && (bo[row+1][col] == oppo)
                && (bo[row+2][col] == oppo)
                && (bo[row+3][col] == oppo))
                h[i]=h[i]+8;
            //check left diagonal
            if ((col>=3) && (row<=2)
                && (bo[row+1][col-1] == oppo)
                && (bo[row+2][col-2] == oppo)
                && (bo[row+3][col-3] == oppo))
                h[i]=h[i]+8;
            
            if ((col<=3) && (row<=2)
                && (bo[row+1][col+1] == oppo)
                && (bo[row+2][col+2] == oppo)
                && (bo[row+3][col+3] == oppo))
                h[i]=h[i]+8;
            
            if ((col>=3) && (row>=3)
                && (bo[row-1][col-1] == oppo)
                && (bo[row-2][col-2] == oppo)
                && (bo[row-3][col-3] == oppo))
                h[i]=h[i]+8;
            
            if ((col<=3) && (row>=3)
                && (bo[row-1][col+1] == oppo)
                && (bo[row-2][col+2] == oppo)
                && (bo[row-3][col+3] == oppo))
                h[i]=h[i]+8;
            
            if ((col>=2) 
                && (bo[row][col-1] == oppo)
                && (bo[row][col-2] == oppo))
                h[i]=h[i]+4;
            //right
            if ((col<=4) 
                && (bo[row][col+1] == oppo)
                && (bo[row][col+2] == oppo))
                h[i]=h[i]+4;
            //check y direction
            if ((row<=3) 
                && (bo[row+1][col] == oppo)
                && (bo[row+2][col] == oppo))
                h[i]=h[i]+4;
            //check left diagonal
            if ((col>=2) && (row<=3)
                && (bo[row+1][col-1] == oppo)
                && (bo[row+2][col-2] == oppo))
                h[i]=h[i]+4;
            
            if ((col<=4) && (row<=3)
                && (bo[row+1][col+1] == oppo)
                && (bo[row+2][col+2] == oppo))
                h[i]=h[i]+4;
            
            if ((col>=2) && (row>=2)
                && (bo[row-1][col-1] == oppo)
                && (bo[row-2][col-2] == oppo))
                h[i]=h[i]+4;
            
            if ((col<=4) && (row>=2)
                && (bo[row-1][col+1] == oppo)
                && (bo[row-2][col+2] == oppo))
                h[i]=h[i]+4;
            
            if ((col>=1) 
                && (bo[row][col-1] == oppo))
                h[i]=h[i]+2;
            //right
            
            if ((col<=5) 
                && (bo[row][col+1] == oppo))
                h[i]=h[i]+2;
            //check y direction
            if ((row<=4) 
                && (bo[row+1][col] == oppo))
                h[i]=h[i]+2;
            //check left diagonal
            if ((col>=1) && (row<=4)
                && (bo[row+1][col-1] == oppo))
                h[i]=h[i]+2;
            
            if ((col<=5) && (row<=4)
                && (bo[row+1][col+1] == oppo))
                h[i]=h[i]+2;
            
            if ((col>=1) && (row>=1)
                && (bo[row-1][col-1] == oppo))
                h[i]=h[i]+2;
            
            if ((col<=5) && (row>=1)
                && (bo[row-1][col+1] == oppo))
                h[i]=h[i]+2;            
          }              
        }
        int max = 0;
        int mm = 3;
        int sum = 0;
        for (int i=0; i<7; i++) {
            if (h[i]>max) {max=h[i]; mm=i;}
            sum= sum+h[i];
        }
        while(true)
        {
        	System.out.println("sum "+sum);
        if (sum==0) mm = (int) (Math.random()*7);
        if(putDisk(bo,mm,me))
        {
        	
        	return;
        }
        else
        	continue;
        }
//        while(true)
//        {
//        if (sum==0) mm = (int) (Math.random()*7);
//
//        if ( (mm<0) || (mm >6))
//            System.out.println("invalid input\n\n");
//        else{
//            
//           int  row= 5-c[mm];
//            c[mm]++; 
//            if(bo[row][mm]=='2')
//            	{
//            		continue;
//            	}
//            bo[row][mm] = '2';
//            return;
//            
//        }
//        }
        
	}

	public static boolean isConsecutiveFour(char[] field, char color,
			String type, int fieldNo) {
		// System.out.println(field.length-7);
		for (int i = 0; i < field.length - 3; i++) {
			// System.out.println(field.length-3);
			boolean isEqual = true;
			for (int j = i; j < i + 3; j++) {
				// System.out.println(field[j]);
				if (field[j] == ' ' || field[j] != field[j + 1]) {
					// field[j] = color;
					// System.out.println(color);

					isEqual = false;

					break;
				} else if (field[j] != ' ') {
					if (field[j] == color) {
						isEqual = true;

					} else {
						isEqual = false;
					}
				}
				if (j == i + 2) {
					if (type.equals("row")) {
						// System.out.println(type);

						// System.out.println(type+" "+j+" "+positionRow[fieldNo][j]);
						if (positionRow[fieldNo][i] == j) {
							isEqual = false;
							break;
						} else {
							positionRow[fieldNo][i] = j;
							counter++;
						}
					} else if (type.equals("column")) {
						// System.out.println(type);
						if (positionColumn[fieldNo][j] == j) {
							isEqual = false;
							break;
						} else {
							positionColumn[fieldNo][j] = j;
							counter++;
						}
					} else if (type.equals("diagonalUpper")) {
						// System.out.println(type);
						if (positionDiagonalUpper[fieldNo][j] == j) {
							isEqual = false;
							break;
						} else {
							positionDiagonalUpper[fieldNo][j] = j;
							counter++;
						}
					}

					else if (type.equals("diagonalLower")) {
						// System.out.println(type);
						if (positionDiagonalLower[fieldNo][j] == j) {
							isEqual = false;
							break;
						} else {
							positionDiagonalLower[fieldNo][j] = j;
							counter++;
						}
					}

					else if (type.equals("subDiagonalLeft")) {
						// System.out.println(type + " " + j + " "
						// + positionSubDiagonalLeft[fieldNo][j]);
						if (positionSubDiagonalLeft[fieldNo][j] == j) {
							isEqual = false;
							break;
						} else {
							positionSubDiagonalLeft[fieldNo][j] = j;
							counter++;
						}
					}

					else if (type.equals("subDiagonalRight")) {
						// System.out.println(type);
						if (positionSubDiagonalRight[fieldNo][j] == j) {
							isEqual = false;
							break;
						} else {
							positionSubDiagonalRight[fieldNo][j] = j;
							counter++;
						}
					}

				}

			}
			if (isEqual) {
				// return true;
			}
		}
		return true;
	}

	public static boolean isWon(char[][] field, char color) {
		return isConsecutiveFour(field, color);
	}

	public static boolean isDraw(char[][] field) {
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[i].length; j++)
				if (field[i][j] == ' ')
					return false;
		return true; // Cells are all now occupied
	}

	public static void printField(char[][] field) {

		System.out.print("  ");
		for (int col = 0; col < 7; ++col) {
			System.out.print(col+1);
			System.out.print("  ");
		}
		System.out.print("\n");
		for (int row = 0; row < 6; ++row) {
			//System.out.print("Row "+row);
			System.out.print("| ");
			outputText.add("| ");
			for (int col = 0; col < 7; ++col) {
				System.out.print(field[row][col] + "| ");
				outputText.add(field[row][col] + "| ");

			}
			System.out.println();
			outputText.add("\n");
		}

		// Print bottom line
		for (int col = 0; col < 7; ++col) {
			System.out.print("---");
			outputText.add("---");
		}
		System.out.println();
		outputText.add("\n");
	}

	public static void printFile() {

		try {
			/*
			 * Get each number one by one from sorted array list. Format the
			 * numbers and print it in the output file using PrintWriter class
			 */
			printWriter = new PrintWriter(new FileOutputStream("Report.txt"));

			printWriter.println("\t\t MaxConnect Game Report ");
			for (String output : outputText) {
				printWriter.print(output);
			}

		} catch (FileNotFoundException ex) {
			/*
			 * When error occurs during the creation of output file, return the
			 * below message to the user.
			 */
			System.out
					.println("The system can't able to create the output file ");
		} finally {
			printWriter.close();
		}
	}
	 static PrintStream fileout=null;

	public static void main(String[] args) {
		final PrintStream origout = System.out;
		Scanner input = new Scanner(System.in);
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
		String player1=null,player2=null,currentplayer=null;
		boolean isRed = true;
		try
		{
			int player = input.nextInt();
			if(player==1)
			{
				player1="you";
				player2="computer";
			}
			else if(player==2)
			{
				player1="computer";
				player2="you";
			}
			else
			{
				System.out.print("Enter 1 or 2");
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.print("Enter 1 or 2");
			System.exit(0);
		}
		
		// Declare field 2D array.
		char[][] field = new char[6][7];

		// Initialize with spaces
		for (int i = 0; i < 6; ++i)
			for (int j = 0; j < 7; ++j)
				field[i][j] = ' ';

		printField(field);
		// System.out.println(field);
		for (int j = 0; j < 7; j++) {
			
			c[j]=0;
		}
		// This variable will alternate and mean whose turn is it. It is Red's
		// turn now.
		currentplayer=player1;
		while (true) {

			System.out.println("Score");
			System.out.println("Player 1 ("+player1+"): " + scoreR);
			System.out.println("Player 2 ("+player2+"): " + scoreY);
			outputText.add("Score \n");
			outputText.add("Player 1 : " + scoreR + "\n");
			outputText.add("Player 2 : " + scoreY + "\n");
			
			if (currentplayer.equals("you")) {
				System.out.println("your turn!");
				outputText.add("your turn!");

				System.out.print("Choose column (1-7) for a disk:");
				outputText.add("Choose column (1-7) for a disk:");
				// Read the position of turn and check if value is correct.
				int column = input.nextInt();
				if (column < 1 || column > 7) {
					System.out.println("Column should be from 1 to 7");
					outputText.add("Column should be from 1 to 7");
					continue;
				}
				// Try to put disk in a column, method returns false if the columns
				// is filled and you cannot put a disk there.
				char playerno='1';
				if(currentplayer.equals(player1))
				{
					playerno='1';
				}
				else
				{
					playerno='2';
					
				}
				if (!putDisk(field, column - 1,playerno )) {
					System.out
							.println("This column is filled! Choose another one.");
					outputText.add("This column is filled! Choose another one.");
					continue;
				}
				currentplayer="computer";

			} else {
				System.out.println("Computer turn");
				outputText.add("Computer turn");
				if(currentplayer.equals(player1))
				{
					computerMove(field,'1','2');
				}
				else
				{
					computerMove(field,'2','1');
					
				}
				
				currentplayer="you";
			}
			

			printField(field);

			// Get winner, this method returns R if Red win, Y if Yellow wins, D
			// if it is a draw and space character if we need to continue the
			// game.
			// boolean result = getWinner(field);
			// char color;
			if (isWon(field, isRed ? '1' : '2')) {
				// if(isWon(field, (char) color)){
				System.out.println("Player 1 wins");
				System.exit(1);
			}
			if (isDraw(field)) {
				String winner=null;
				if (scoreR > scoreY) {
					if(player1.equals("you"))
					{
						winner="you";
					}
					else
					{
						winner="computer";
					}
					System.out.println("Player 1  ("+winner+") wins " + scoreR + " over "
							+ scoreY);
					outputText.add("Player 1  wins " + scoreR + " over "
							+ scoreY);
					printFile();
					fileout.close();
					System.exit(1);
				} else if (scoreY > scoreR) {
					if(player2.equals("you"))
					{
						winner="you";
					}
					else
					{
						winner="computer";
					}
					System.out.println("Player 2  ("+winner+") wins " + scoreY + " over "
							+ scoreR);
					outputText.add("Player 2  ("+winner+") wins " + scoreY + " over "
							+ scoreR);
					printFile();
					fileout.close();
					System.exit(1);
				} else {
					System.out.println("Match Draw" + scoreY + " over "
							+ scoreR);
					outputText.add("Match Draw" + scoreY + " over " + scoreR);
					printFile();
					fileout.close();
					System.exit(1);
				}
				// printWriter.close();

			}
			/*
			 * if (result == 'D') { System.out.println("It is a draw!"); break;
			 * } else if (result == 'R') { System.out.println("Red win!");
			 * break; } else if (result == 'Y') {
			 * System.out.println("Yellow win!"); break; }
			 */
			// Otherwise we just proceed to the next turn, we need to invert
			// isRed
			// to alternate turns.
			isRed = !isRed;
		}

	}

}