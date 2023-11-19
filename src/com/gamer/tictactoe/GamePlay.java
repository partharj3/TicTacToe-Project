package com.gamer.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class GamePlay {
	
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
	
	private static void playerMove(char[][] board, Scanner scan) {
		int userChoice;
		while(true) {
			System.out.println("Choose the place you wanna hold(1-9): ");
			userChoice = scan.nextInt();
			if(isValidMove(board, userChoice)) {
				break;
			}
			else {
				System.out.println("Invalid Choice '"+userChoice+"'!!. Please enter valid one.");
			}
		}
		placeMark(board, userChoice, 'X');
	}
	
	private static void computerMove(char[][] board) {
		Random random = new Random();
		int computerChoice;
		while(true){
			computerChoice = random.nextInt(9)+1; // generally (it takes from 0-8) so '+1'
			if(isValidMove(board, computerChoice)) {
				break;
			}
		}
		System.out.println("Computer's move: "+computerChoice);
		placeMark(board, computerChoice, 'O');
	}
	
	private static boolean isGameFinished(char[][] board, String username) {
		if(strikerWon(board, 'X')) {
			displayBoard(board);
			System.out.println("Congratz "+username+"!!.You won the game");
			return true;
		}
		if(strikerWon(board, 'O')) {
			displayBoard(board);
			System.out.println("***** COMPUTER WON *****");
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
	
	static void singlePlayer(Scanner scan) {
		System.out.println(" :::::::::::: SINGLE PLAYER ::::::::::::\n");
		System.out.print("Please enter your name: ");
		String username = scan.next();
		System.out.println("Wow !!. thats a sweet name '"+username);
		char[][] board = {{' ',' ',' '},
						  {' ',' ',' '},
						  {' ',' ',' '}};
		
		boolean playAgain = true;
		
		while(playAgain) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
					+ "\n### .Lets start the game now.. ###\n");
			displayBoard(board);
			while(true) {
				playerMove(board, scan);
				if(isGameFinished(board,username)) {
					break;
				}
				displayBoard(board);
				
				computerMove(board);
				if(isGameFinished(board,null)) {
					break;
				}
				displayBoard(board);
			}
			System.out.println("Do you wanna play again ?? (if yes - Y)");
			String again = scan.next().toLowerCase();
			switch(again) {
			case "y":
				playAgain=true;
				clearTheBoard(board);
				break;
			default:
				playAgain=false;
				System.out.println("Bye "+username+", Take care !!!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}
	
}
