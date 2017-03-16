
/*	Name: Darian Lio
 * 	NetID: 14dl50
 * 	Student ID: 10181505
 * 	Professor: Hesham Farahat
 * 	Course:CMPE212
 * 	Date: Monday, February 13, 2017
 * 	Title: Assignment 2 - Matrix Calculator
 * 
 * 	Instructions: Create Matrix calculator that is capable of performing required operations
 * 				  depending on constructor.
 * 
 * *NOTE: FILE WILL NOT WORK UNLESS IT IS IN THE RIGHT FOLDER AS THE JAVA FILE
 */

import java.io.*;
import java.util.Scanner;

public class Matrix {

	//Initialize private variable attributes
	private double[][] matrix;							
	private int m;														//declare variable for row
	private int n;														//declare variable for column
	private boolean rand;											    //declare boolean random 
	
	//**********************CONSTRUCTORS************************
	//1)Constructor to create a matrix given m and n
	public Matrix(int m, int n) {
		this.m = m;														//set the value of integer into row
		this.n = n;														//set the value of integer into column
		matrix = new double[m][n];										//generate matrix size from row and column dimension
	}

	//2) Constructor to read a matrix from a user using terminal
	public Matrix(String name) {
		System.out.println("Hello " + name);							//greet user
		Scanner scan = new Scanner(System.in);							//initialize scanner
		System.out.println("Enter the amount of rows: ");				//ask user for number of rows
		this.m = scan.nextInt();
		System.out.println("Enter the amount of columns: ");			//ask user for number of columns
		this.n = scan.nextInt();

		matrix = new double[m][n];										//generate matrix index
		
		//Request user to enter values for the matrix
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++) {
				System.out.println("Please enter the value at matrix[" + i + "][" + j + "]");
				matrix[i][j] = scan.nextDouble();						//save user entered value into matrix
			}
		}
		
		//Display the matrix
		System.out.println("User-generated matrix:");
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++){
				System.out.printf("%9.1f", matrix[i][j]);				//print double with one decimal value
			}
			System.out.println();										//line space for next row of matrix
		}
	}

	//3) Constructor to read a matrix from a CSV file.
	public Matrix(File file) {
		System.out.println("Reading the matrix file...");
		try {
			//Initialize scanner to read the file
			Scanner scan = new Scanner(new FileReader(file));			
			
			//Initialize variables
			String line;
			String delimiter = ",";										//delimiter used to split numbers from the comma
			String[] matrixDim = new String[2];							//string array to record matrix dimension of rows and columns
			int index = 0;												//set an index to record each line of matrix in file
			
			//Scan the first line of the file to receive the dimensions
			line = scan.nextLine();
			matrixDim = line.split(delimiter);							//have matrix dimension separate value from comma
			
			//Convert the received dimensions into m and n respectively
			this.m = Integer.parseInt(matrixDim[0]);					//parse string into an integer
			this.n = Integer.parseInt(matrixDim[1]);
			
			String[][] stringMatrix = new String[getM()][];				//string array to record matrix values of each row
			matrix = new double[getM()][getN()];						//initialize matrix

			//read the next line of the file
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				stringMatrix[index++] = line.split(delimiter);			//record values to each index of the matrix using a delimiter

			}
			
			//generate the matrix
			for (int i = 0; i < stringMatrix.length; i++) {
				for (int j = 0; j < stringMatrix[i].length; j++) {
					matrix[i][j] = Double.parseDouble(stringMatrix[i][j]);	//parse string into a double
				}
			}

		} catch (FileNotFoundException e) {								//check for errors and print them
			e.printStackTrace();
		}

	}
	
	//Created fourth constructor to make a new matrix with random values
	public Matrix(int m, int n, boolean rand) {
		this.m = m;														//set the value of integer into row
		this.n = n;														//set the value of integer into column
		this.rand = rand;
		matrix = new double[m][n];										//generate matrix size from row and column dimension
		
		if(rand == true){
			for(int i = 0; i < getM(); i++){
				for(int j = 0; j < getN(); j++){
					double a = Math.random()*10;						//generate random value from 0-9
					matrix[i][j] = (double)Math.round(a*10)/10;			//round to nearest tenth
				}
			}
		}
		
		//Display the matrix
		System.out.println("Generated matrix:");
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++){
				System.out.printf("%9.1f", matrix[i][j]);				//print double with one decimal value
			}
			System.out.println();										//line space for next row of matrix
		}
	}

	//**********************GETTERS*************************
	public int getN() {
		return n;														//return the value of n
	}

	public int getM() {											
		return m;														//return the value of m
	}

	public double get(int i, int j) {
		return matrix[i][j];											//return value of matrix at index
	}

	//********************OPERATIONS************************
	
	//ADD FUNCTION
	public Matrix add(Matrix m) {									
		Matrix a = this;
		Matrix r = new Matrix(getM(), getN());							//matrix result
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++) {
				r.matrix[i][j] =(double)Math.round((a.matrix[i][j] + m.matrix[i][j])*10)/10;		//(a + m = result)
			}
		}
		return r;
	}

	//SUBTRACT FUNCTINO
	public Matrix subtract(Matrix m) {									
		Matrix a = this;
		Matrix r = new Matrix(getM(), getN());
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++) {
				r.matrix[i][j] =(double)Math.round((a.matrix[i][j] - m.matrix[i][j])*10)/10;		//(a - m = result)
			}
		}
		return r;
	}

	//MULTIPLY FUNCTION
	public Matrix multiply(Matrix m) {									  
		Matrix a = this;
		Matrix r = new Matrix(getM(), getN());
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++) {
				r.matrix[i][j] =(double)Math.round((a.matrix[i][j] * m.matrix[i][j])*10)/10;		//(a * m = result)
			}
		}
		return r;
	}

	//MULTIPLY SCALAR FUNCTION
	public Matrix multiply(double x) {									 
		Matrix a = this;
		Matrix r = new Matrix(getM(), getN());
		for (int i = 0; i < getM(); i++) {
			for (int j = 0; j < getN(); j++) {
				r.matrix[i][j] =(double)Math.round((a.matrix[i][j] + x)*10)/10;					//(a * x (scalar) = result)
			}
		}
		return r;

	}
	
	//DIVISION FUNCTION
	public Matrix divide(Matrix m) {									//(inverse of m * a = r) 
		Matrix a = this;
		Matrix r = new Matrix(getM(), getN());

		r = a.multiply(m.inverse());									//only works if matrix is square

		return r;

	}

	//DETERMINANT FUNCTION
	public double determinant() {																
		//Initialize empty integer determinant
		double determinant = 0;
		if (isSquare() == true) {																//if square matrix, find determinant
			if (getM() == 1) { 																	// for 1X1 MATRIX
				determinant = matrix[0][0];
			} else if (getM() == 2) { 															// for 2X2 MATRIX
				determinant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
			} else if (getM() == 3) { 															// for 3X3 MATRIX
				determinant = ((matrix[0][0] * matrix[1][1] * matrix[2][2])
						+ (matrix[0][1] * matrix[1][2] * matrix[2][0]) + (matrix[0][2] * matrix[1][0] * matrix[2][1])
						- (matrix[0][2] * matrix[1][1] * matrix[2][0]) - (matrix[0][1] * matrix[1][0] * matrix[2][2])
						- (matrix[0][0] * matrix[1][2] * matrix[2][1]));
			} else {																			//return message for any matrix that is greater than a 3X3 matrix	 
				System.out.println("The matrix is greater tan a 3x3 matrix. Unable to find determinant.");
			}
			
		} else {																				//return message for any matrix that is not a square matrix		 
			System.out.println("The matrix is not a square. Unable to find determinant.");
		}
		return (double)Math.round(determinant*10)/10;
	}

	//INVERSE FUNCTION
	public Matrix inverse() {																	
		
		Matrix inverse = new Matrix(getM(), getN());											//create an inverse matrix
		double inv[][] = new double[m][n];														//create a double inverse array to perform function with matrix array
		
		if (isSquare() == true){																//if square matrix, than find inverse
			System.out.println("Matrix is square");
			if (determinant() == 0){
				System.out.println("Inverse does not exist");
			}
			
			else if(getM() == 1){																//for 1X1 MATRIX
				inv[0][0] = (double)Math.round((1/matrix[0][0])*10)/10;														//inverse = 1/matrix
				
				inverse.matrix[0][0] = inv[0][0];												//insert into inverse matrix	
			}
			
			else if (getM() == 2){																//for 2X2 Matrix
				inv[0][0] = (double)Math.round((matrix[1][1] * 1/determinant())*10)/10;										//inverse = 1/(ad-bc) * | d  -b |
				inv[1][1] = (double)Math.round((matrix[0][0] * 1/determinant())*10)/10;										//						|-c   a |
				inv[0][1] = (double)Math.round(((matrix[0][1] * (-1)) * 1/determinant())*10)/10;
				inv[1][0] = (double)Math.round(((matrix[1][0] * (-1)) * 1/determinant())*10)/10;

				for (int i = 0; i < getM(); i++) {
					for (int j = 0; j < getN(); j++) {
						inverse.matrix[i][j] = inv[i][j];										//insert into matrix
					}
				}
			}
	 		
			else if (getM() == 3) {																//for 3X3 Matrix
				for (int i = 0; i < inv.length; i++){
					for (int j = 0; j < inv[i].length; j++){									//calculate matrix minors
						inv[i][j] = ((matrix[(i+1)% 3][(j+1)% 3]*matrix[(i+2)% 3][(j+2)%3]) - (matrix[(i+1)%3][(j+2)%3]*matrix[(i+2)%3][(j+1)%3]));
					}
				}
				
				for (int i = 0; i < getM(); i++) {
					for (int j = 0; j < getN(); j++) {
						inverse.matrix[i][j] = inv[i][j];										//insert into inverse matrix
					}
				}
				
				inverse.transpose();															//transpose the matrix
				 for (int i = 0; i < inv.length; i++) {
	                    for (int j = 0; j < inv[i].length; j++) {
	                        inverse.matrix[i][j] = (double)Math.round((inverse.matrix[i][j] / determinant())*10)/10;		//divide transposed matrix by determinant
	                    }
	             }
			}
			else {																				//return message for any matrix that is greater than a 3X3 matrix	 
				System.out.println("The matrix is greater tan a 3x3 matrix. Unable to find inverse.");
			}																
			
		} else {
			System.out.println("The matrix is not a square. Unable to find inverse.");			//return message for any matrix that is not a square matrix
		}
		return inverse;
	}

	//CHECK IF SQUARE FUNCTION
	public boolean isSquare() {																	
		for (int i = 0; i < matrix.length; i++) {												//for row length										
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix.length != matrix[i].length) {									    //if matrix lengths are not equal return false
					return false;
				}
			}
		}
		return true;																			//else true
	}

	//TRANSPOSE FUNCTION
	public Matrix transpose() {																					
		Matrix a = new Matrix(matrix[0].length, matrix.length);	
		for (int i = 0; i < matrix[0].length; i++)
			for (int j = 0; j < matrix.length; j++)
				a.matrix[i][j] = matrix[j][i];
		return a;
	}

	// OTHER
	public String toString() {					//toString FUNCTION
		String line = "";						//Generate an empty string
		for (int i = 0; i < getM(); i++) {		
			for (int j = 0; j < getN(); j++) {
				line += matrix[i][j] + " ";		//add matrix to string for each index
			}
			line += "\n";						//line space to indicate next row of matrix in string
		}

		return line;							
	}

	public void print(String filename) {		//print-to-file FUNCTION
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(getM() + "," + getN() + "");				//write first row with the dimensions
			bw.write(System.getProperty("line.separator"));		//write line separator to seperate dimension from matrix values
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (j < matrix[i].length - 1)				//write matrix value and comma to indicate next value if less than length - 1
						bw.write(matrix[i][j] + ",");
					else 
						bw.write(matrix[i][j] + "");			//write matrix value without comma at end of the line
				}
				bw.write(System.getProperty("line.separator"));	//write line separator after each row
			}

			bw.close();
		} catch (IOException e) {								//catch for error
			e.printStackTrace();
		}
	}

	public static Matrix identity(int size) {	//IDENTITY FUNCTION
		Matrix identity = new Matrix(size, size);
		for (int i = 0; i < size; i++)
			identity.matrix[i][i] = 1;
		return identity;
	}
	
	//DISPLAY ALL OPERATORS OF A MATRIX
	public static void displayOperations(Matrix a){
		System.out.println("Displaying all operations...");
		
		//To String
		System.out.println("To String");
		System.out.println(a.toString());
		
		//Transpose the matrix
		System.out.println("Transpose Function:");
		System.out.println(a.transpose());
		
		//Find the determinant of the matrix in 1x1, 2x2, or 3x3
		System.out.println("Determinant of matrix:");
		System.out.println(a.determinant());
		
		//Find the inverse of the matrix in 1x1, 2x2, or 3x3
		System.out.println("Inverse of matrix:");
		System.out.println(a.inverse());
		
		//Create new matrix b a transpose of matrix a to operate
		System.out.println("Generating new matrix to perform operations:");
		Matrix b = new Matrix(a.getM(),a.getN(), true);
		
		//Add Matrix A and Matrix B
		System.out.println("Add Function:");
		System.out.println(a.add(b));
		
		//Subtract Matrix A and Matrix B
		System.out.println("Subtract Function:");
		System.out.println(a.subtract(b));
		
		//Multiply Matrix A and Matrix B
		System.out.println("Mutliply Function:");
		System.out.println(a.multiply(b));
		
		//Multiply Matrix A and a scalar value (can be changed)
		System.out.println("Multiply Scalar Value of 2 Function (value can change in code):");
		System.out.println(a.multiply(2));
		
		//Divide Matrix A and Matrix B
		System.out.println("Divide Function:");
		System.out.println(a.divide(b));
		
		//Identity of a 1x1 Matrix
		System.out.println("Identity Function of a 1x1 Matrix:");
		System.out.println(Matrix.identity(1));
				
		//Identity of a 2x2 Matrix
		System.out.println("Identity Function of a 2x2 Matrix:");
		System.out.println(Matrix.identity(2));
		
		//Identity of a 3x3 Matrix
		System.out.println("Identity Function of a 3x3 Matrix:");
		System.out.println(Matrix.identity(3));
		
		//Print matrix in a textfile
		System.out.println("Print:");
		a.print("Matrix.txt");
		System.out.println("File named: 'Matrix.txt' has been generated");
	}
}
