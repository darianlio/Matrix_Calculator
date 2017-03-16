/*	Name: Darian Lio
 * 	Title: Assignment 2 - Matrix Calculator
 * 
 * 	Instructions: Create Matrix calculator that is capable of performing required operations
 * 				  depending on constructor.
 * 
 * *NOTE: FILE WILL NOT WORK UNLESS IT IS IN THE RIGHT FOLDER AS THE JAVA FILE
 */


import java.io.File;
import java.util.Scanner;

public class MatrixTest {
	
	public static void main(String[] args) {
		// Initialize Variables
		Matrix d = null;													//default matrix
		Matrix u = null;													//user matrix
		Matrix f = null;													//file matrix
		Scanner sc = new Scanner(System.in);								//scanner to interact with user
		int select = 0;
		boolean status = true;												
				
		//Create user interface
		while (status) { // while true
			System.out.println("Running Matrix Calculator...");
			System.out.println(												//Greet user and ask to choose option
						"Welcome to the Matrix Calculator \n"
						+ "Please choose a following option: \n"
						+ "1)Create a default matrix \n"
						+ "2)Create a user matrix \n"
						+ "3)Create a matrix from file \n"
						+ "4)Terminate matrix calculator");
			select = sc.nextInt();											//read user request
					
		// Initialize Matrices using switch case statement
			switch (select){
			case 1:		//Option 1: Default Matrix
				System.out.println("Initializing default matrix...");
				System.out.println("Please enter 1, 2, or 3 to choose a \n"			//default matrix options
											+ "(1 x 1), (2 x 2), or (3 x 3) matrix");
				int num = sc.nextInt();												//scan integer value
				System.out.println("Creating default matrix...");
				d = new Matrix(num, num, true); 									//create default matrix
				System.out.println("Randomizing default matrix values...");
				Matrix.displayOperations(d);												//display operations
				break;			

			case 2:		//Option 2: User Matrix
				System.out.println("Initializing user matrix...");
				System.out.println("Please enter your name: ");
				String name = sc.nextLine();										//scan user's name
				u = new Matrix(name); 												//create user matrix
				Matrix.displayOperations(u);												//display operations
				break;

			case 3:		//Option 3: File Matrix
				System.out.println("Initializing matrix from file...");
				f = new Matrix(new File("example.mat")); 							//create file matrix
				Matrix.displayOperations(f);												//display operations
				break;

			case 4:		//Option 4: Exit Program
				System.out.println("You have terminated the matrix calculator.");
				System.exit(0);														//EXIT
						
			default:	//Default: Exit Program
				System.out.println("You have terminated the matrix calculator.");
				System.exit(0);														//EXIT
					
			}
			status = false;
			//end loop
		}
	}
}
