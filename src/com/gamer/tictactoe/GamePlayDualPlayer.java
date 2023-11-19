package com.gamer.tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlayDualPlayer {
	
	private static void displayBoard(char[][] board) {
		System.out.println(" "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
		System.out.println("-----------");
		System.out.println(" "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
		System.out.println("-----------");
		System.out.println(" "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
		System.out.println();
	}
	
	private static void clearTheBoard(char[][] board) {
		for(int i=0; i<board.length ; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j]=' ';
			}
		}
	}
	
	private static boolean isValidMove(char[][] board, int position) {
		switch(position) {
			case 1:
				return (board[0][0]==' ');
			case 2:
				return (board[0][1]==' ');
			case 3:
				return (board[0][2]==' ');
			case 4:
				return (board[1][0]==' ');
			case 5:
				return (board[1][1]==' ');
			case 6:
				return (board[1][2]==' ');
			case 7:
				return (board[2][0]==' ');
			case 8:
				return (board[2][1]==' ');
			case 9:
				return (board[2][2]==' ');	
			default:
				return false;
		}
	}
	
	private static void placeMark(char[][] board, int position, char marker) {
		switch(position) {
		case 1:
			board[0][0]=marker;
			break;
		case 2:
			board[0][1]=marker;
			break;
		case 3:
			board[0][2]=marker;
			break;
		case 4:
			board[1][0]=marker;
			break;
		case 5:
			board[1][1]=marker;
			break;
		case 6:
			board[1][2]=marker;
			break;
		case 7:
			board[2][0]=marker;
			break;
		case 8:
			board[2][1]=marker;
			break;
		case 9:
			board[2][2]=marker;
			break;
		default:
			System.out.println("*");
		}
	}
	
	private static String selectMarker(String name,Scanner scan) {
		while(true) {
			System.out.println("\n"+name+", Select your marker (X/O): ");
			String marker=scan.next().toUpperCase();
			if(marker.equals("X")|| marker.equals("O")) {
				return marker;
			}
			else
				System.out.println("********!!OOPS!! Its not applicable. Please try anyone of these (X/O).");
		}
	}
	
	private static void playerMove(String name,char[][] board, Scanner scan, char mark) {
		int userChoice=-1;
		while(true) {
			try {
				System.out.println(" >>>>"+name+", choose the place you wanna hold(1-9): ");
				userChoice = scan.nextInt();
				if(isValidMove(board, userChoice)) {
					break;
				}
				else {
					System.out.println(userChoice+" is Engaged !!. Please try another one");
				}
			}
			catch(InputMismatchException exp) {
				System.out.println("*******!!Invalid input. Please enter a number between 1 and 9.\n");
	            scan.next();
			}
		}	
		placeMark(board, userChoice, mark);
	}
	
	private static boolean isGameFinished(char[][] board, String username, char mark) {
		if(strikerWon(board, mark)) {
			displayBoard(board);
			System.out.println("::::::) Congratz "+username+"!!.You won the game (::::::\n");
			return true;
		}
		for(int i=0; i<board.length ; i++) {
			for(int j=0; j<board[i].length ; j++) {
				if(board[i][j]==' ')
					return false;
			}
		}
		displayBoard(board);
		System.out.println("!--The game is tied--!");
		return true;
	}
	
	private static boolean strikerWon(char[][] board, char mark) {
		if((board[0][0]==mark && board[0][1]==mark && board[0][2]==mark) || 
		   (board[1][0]==mark && board[1][1]==mark && board[1][2]==mark) ||
		   (board[2][0]==mark && board[2][1]==mark && board[2][2]==mark) ||
		   
		   (board[0][0]==mark && board[1][0]==mark && board[2][0]==mark) || 
		   (board[0][1]==mark && board[1][1]==mark && board[2][1]==mark) ||
		   (board[0][2]==mark && board[1][2]==mark && board[2][2]==mark) ||
		   
		   (board[0][0]==mark && board[1][1]==mark && board[2][2]==mark) || 
		   (board[0][2]==mark && board[1][1]==mark && board[2][0]==mark)){
			   return true;
		}
		return false;
	}
	
	static void multiplayer(Scanner scan) {
		System.out.println("::::::::::::::: MULTI PLAYER :::::::::::::");
		System.out.print("enter your name player1: ");
		String user1 = scan.next();
		System.out.print("enter your name player2: ");
		String user2 = scan.next();
		System.out.println("Welcome guys.."+user1+" and "+user2);
		
		char[][] board = {{' ',' ',' '},
						  {' ',' ',' '},
						  {' ',' ',' '}};
		
		boolean playAgain = true;
		char marker= selectMarker(user1,scan).charAt(0);
		while(playAgain) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
					+ "\n### .Lets start the game now.. ###\n");
			displayBoard(board);
			if(marker=='X')
				System.out.println("# "+user1+" chose 'X'\n# "+user2+" got 'O'\n");
			else
				System.out.println("# "+user1+" chose 'O'\n# "+user2+" got 'X'\n");
			while(true) {
				if(marker=='X') {
					playerMove(user1,board, scan, 'X');
					if(isGameFinished(board,user1,'X')) {
						break;
					}
					displayBoard(board);

					playerMove(user2,board, scan, 'O');
					if(isGameFinished(board,user2,'O')) {
						break;
					}
					displayBoard(board);
				}
				else {
					playerMove(user1,board, scan, 'O');
					if(isGameFinished(board,user2,'O')) {
						break;
					}
					displayBoard(board);
					playerMove(user2,board, scan, 'X');
					if(isGameFinished(board,user1,'X')) {
						break;
					}
					displayBoard(board);
				}
			}
			System.out.println("Do you wanna play again ?? (if yes - Y)");
			String again = scan.next().toLowerCase();
			switch(again) {
			case "y":
				clearTheBoard(board);
				playAgain=true;
				break;
			default:
				playAgain=false;
				System.out.println("Bye, Take care !!!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}
}

