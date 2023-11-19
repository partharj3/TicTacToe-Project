package com.gamer.tictactoe;

import java.util.Scanner;

public class Gamer {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome Gamerzzz!!:\n-------------------\n"
						+ "####################``TIC-TAC-TOE``##################\n");
		System.out.println("RULES:\n******"
				+ "\n1.The player1 have to choose the mark (X or O)."
				+ "\n2.The choosen mark will be held by the player 1, rest one will goes to another player2."
				+ "\n3.If the mark got matched row-wise or column-wise or diagonally that mark holder will be the 'Winner'."
				+ "\n\nthen... Enjoy the fun !!");
		
		System.out.println("\n...............................\n");
		
		while(true) {
			System.out.println("1.Single Player\n2.Multi Player");
			try {
				int choice=scan.nextInt();
				if(choice==1)
				{
					GamePlay.singlePlayer(scan);
					break;
				}else {
					GamePlayDualPlayer.multiplayer(scan);
					break;
				}
			}catch (Exception e) {
				System.out.println("Invalid choice. choose the available option to proceed >>\n");
				scan.next();
			}
		}
		scan.close();
	}

}
