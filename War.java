import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;



public class War {
	
	private WarLL p1 = new WarLL();    //player1 deck
	private WarLL p2 = new WarLL();	  //player2 deck
	private WarLL deck = new WarLL();	//the main deck
	
	private JPanel contentPane;
	
	Scanner scan = new Scanner(System.in);		//to take user input in drawing or quitting
	private String input = "";
	boolean win1 = false;		//to know if player one wins
	boolean win2 = false; 		//to know if player two wins

	Random rand = new Random();
	
	//creates the main deck
	public void Set() {
		for(int i = 0; i<4; i++) {
			for(int j = 1; j<=10; j++) {
				if(i == 0) 
					deck.add(j, "diamond");
				if(i == 1)
					deck.add(j, "spades");
				if(i == 2)
					deck.add(j, "hearts");
				if(i == 3)
					deck.add(j, "clubs");
			}
		}
		
		//adds the Kings, Queens, Jack and Ace
		for(int k = 11; k<=13; k++) {
			for(int h = 0; h<4; h++) {
				if(h==0)
					deck.add(k, "diamond");
				if(h==1)
					deck.add(k, "hearts");
				if(h==2)
					deck.add(k, "spades");
				if(h==3)
					deck.add(k, "clubs");
			}
		}
	}
	
	//Shuffles in which it removes the first card and inserts it randomly back into the deck
	public void Shuffle() {		
		for(int i = 0; i<52; i++) {
		WarLL.Node z = deck.remove();
		 int value = rand.nextInt(52);
		 deck.add(value, z.num, z.shape);
		}
		 
	}
	
	//splits the main deck into player one's deck and player two's deck
	public void Divide() {
		for(int i = 0; i<26; i++) {
			WarLL.Node x = deck.remove();
			p1.add(x.num, x.shape);
		}
		for(int i = 0; i<26; i++) {
			WarLL.Node y = deck.remove();
			p2.add(y.num, y.shape);
		}
	}
	
	
	//prints the deck of player one (used only to check that the game is running smoothly)
	public ArrayList print1() {
		ArrayList<WarLL.Node> lists = new ArrayList<WarLL.Node>();
		for(int i = 0; i<27; i++) {
			lists.add(p1.getting(i));
		}
		return lists;
	}

	//prints the deck of player two (used only to check that the game is running smoothly)
	public ArrayList print2() {
		ArrayList<WarLL.Node> listing = new ArrayList<WarLL.Node>();
		for(int i = 0; i<27; i++) {
			listing.add(p2.getting(i));
		}
		return listing;
	}
	
	public void Draw() {
		//while loop ensures that the game continues until one player is declared as a winner
				while(win1 == false && win2 == false) {
				System.out.println("Press D to draw");
				input = scan.nextLine();
				if(input.equals("d")) {
					//draws a card from either deck
					WarLL.Node temp1 = p1.remove();
					WarLL.Node temp2 = p2.remove();
					System.out.println("Player One: " + temp1);
					System.out.println("Player Two: " + temp2);
					//compares the cards, the person with the higher card takes both cards and adds them to the back of the deck
					if(temp1.num > temp2.num) {
						p1.add(p1.size()-1, temp1.num, temp1.shape);
						p1.add(p1.size()-1, temp2.num, temp2.shape);
						System.out.println("Player One has " +p1.size());
						System.out.println("Player Two has " +p2.size());
					}
					else if(temp1.num < temp2.num) {
						p2.add(p2.size()-1, temp1.num, temp1.shape);
						p2.add(p2.size()-1, temp2.num, temp2.shape);
						System.out.println("Player One has " +p1.size());
						System.out.println("Player Two has " +p2.size());
					}
					//in the case of a draw, each player takes back their own card and they're added at the back of the deck
					else {
						p1.add(p1.size()-1, temp1.num, temp1.shape);
						p2.add(p2.size()-1, temp2.num, temp2.shape);
						System.out.println("Player One has " +p1.size());
						System.out.println("Player Two has " +p2.size());
					}	
				}
				//if the player doesnt choose to draw, gives a quit/restart option
				else {
					System.out.print("Do you want to quit? (y/n)");
					input = scan.nextLine();
					if(input.equals("y"))
						quit();
					else {
						System.out.println("Draw again or Reset? (d/r)");
						input = scan.nextLine();
						if(input.equals("D")) {
							System.out.println("Player One: " + p1.remove());
							System.out.println("Player Two: " + p2.remove());
							if(input.equals("d")) {
								WarLL.Node temp1 = p1.remove();
								WarLL.Node temp2 = p2.remove();
								System.out.println("Player One: " + temp1);
								System.out.println("Player Two: " + temp2);
								if(temp1.num > temp2.num) {
									p1.add(p1.size()-1, temp1.num, temp1.shape);
									p1.add(p1.size()-1, temp2.num, temp2.shape);
									System.out.println("Player One has " +p1.size());
									System.out.println("Player Two has " +p2.size());
								}
								else if(temp1.num < temp2.num) {
									p2.add(p2.size()-1, temp1.num, temp1.shape);
									p2.add(p2.size()-1, temp2.num, temp2.shape);
									System.out.println("Player One has " +p1.size());
									System.out.println("Player Two has " +p2.size());
								}
								else {
									p1.add(p1.size()-1, temp1.num, temp1.shape);
									p2.add(p2.size()-1, temp2.num, temp2.shape);
									System.out.println("Player One has " +p1.size());
									System.out.println("Player Two has " +p2.size());
								}	
							}
						}
					}
				}
				}
				}
				
	
	//the actual events of the game
	public void Gameplay() {
		Set();
		Shuffle();
		Divide();
		Draw();
		
		
		//checks who won
		if(p1.size()==52) {
			win1 = true;
			System.out.println("Player One Wins!");
		}
		else if(p2.size() == 52) {
			win2 = true;
			System.out.println("Player Two Wins!");
		}
		}
	
	
	public void quit() {
		
	}
	


	public static void main(String[] args) {
		War test = new War();
		//test.Set();
		//System.out.println(test);
		//test.Shuffle();
		//System.out.println(test);
		//test.Divide();
		test.Gameplay(); 
	}
	
	

}


	
	




