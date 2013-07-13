/*
Brief description: This program allows two players to play the game of Othello, 
the players will select the size of the game board and a name for the auto saved 
game that will save the game each time both players have gone. The players can access 
a menu when they are playing the game that performs various actions like saving and loading
games. When the game is finished the winner will be declared. 
*/

//Imports two java packages to be used in the java program
   import java.io.*;
   import java.util.*;
//Declaration of the class Othello
   public class Othello{
   //Declaration of all the global variables used in the program
      public static  String [][] board;
      public static int [] numsize = new int [2];
      public static int move = 0;
      public static int bpoints = 0;
      public static int wpoints = 0;
      public static int letternum, intnum = -1;
      public static int bflankpoints = 0;
      public static int wflankpoints = 0;
      public static String filename;
      public static int newchoice = 0;
      public static String autoname;
      public static int gamecount = 4;
      public static int valid = 0;
      public static String [] normalsavednames;
      public static int first;
      public static int autosaveexit;
      public static int load = 0;
      public static String loadautoname;
      public static int totalcount;
      public static int loadcounter = 0;
   //Declaration of the main class
      public static void main (String[]args){
         int choice = 0;
         int run = 0;
      //This infinite loop makes it so the user can only exit the program with the Exit() method
         while (run == 0){
         //Gets the user input for the start menu
            choice = startMenu(); 
         //The if and else if statements runs the part of the program that the user wants to run
            if (choice == 1) {
               newchoice = 1;
            //This loop allows the newGame() method to be re-run from user input from the game menu
               while (newchoice == 1){
                  newchoice = 0;
               //Runs the newGame() method which creats and runs a new game of Othello
                  newGame();
               }
            }
            if (loadcounter == 1){
            //Runs the loadGame() method which loads a pre-existing saved game from user instructions
               loadGame();
               newchoice = 1;
               while (newchoice == 1){
                  newchoice = 0;
               //Runs the contGame() method which allows the users to play Othello after the saved game is loaded to the program
                  contGame();
               }
            }
            else if (choice == 2){
            //Runs the loadGame() method which loads a pre-existing saved game from user instructions
               loadGame();
            //Runs the contGame() method which allows the users to play Othello after the saved game is loaded to the program
               newchoice = 1;
               while (newchoice == 1){
                  newchoice = 0;
               //Runs the contGame() method which allows the users to play Othello after the saved game is loaded to the program
                  contGame();
               }
            }
            if (loadcounter == 1){
            //Runs the loadGame() method which loads a pre-exisiting saved game from user instructions
               loadGame();
               newchoice = 1;
               while (newchoice == 1){
                  newchoice = 0;
               //Runs the contGame() method which allows the users to play Othello after the saved game is loaded to the program
                  contGame();
               }
            }
            else if (choice == 3){
            //Runs the Exit() method which exits the java program of Othello
               Exit();
            }
            else if (choice == 4){
            //Runs the deletsaveGame() method which allows the user to delete pre-exisiting saved games 
               deletsaveGame();
            }
         
            loadcounter = 0;
         }
      }
   
   
   //Declaration of the startMenu() method which gives the user a choice between several different actions
      public static int startMenu()
      {
         String choice;
         int numchoice=0, exit=0;
         Scanner sc = new Scanner (System.in);
         System.out.print("\nWelcome to Othello\n1. New Game\n2. Load Game\n3. Exit Program\n4. Delete Saved Game(s)\n Please enter the integer that represents your choice (1-4): ");
         while (exit == 0){
         //Get the user input of what the user wants to do
            choice = sc.nextLine();
         // tries to turn the user input into a integer, and if it can't be then the exception is caught
            try {
               numchoice = Integer.parseInt(choice);
            }
               catch (NumberFormatException e ){
               }
         //Gives the variable exit the value to exit out of this loop if the user input is correct
            if (numchoice == 1 || numchoice == 2 || numchoice == 3 || numchoice == 4){
               exit = 1;
            }
            //Tells the user they have entered a incorrect choice and to re-enter their choice
            else {
               System.out.print("You did not enter a integer that is between 1-4\n Please enter the integer that represents your choice (1-4): ");
               System.out.print("Welcome to the Start Menu\n1. New Game\n2. Load Game\n3. Quite Game\n4. Delete Saved Game(s)\nPlease enter the integer that represents your choice (1-4): ");
            }
         }
         return numchoice;
      }
   
   //Declares the gameMenu() method, this method get user decisions made from the game menu
      public static int gameMenu(){
         String choice;
         int numchoice=0, exit=0;
         Scanner sc = new Scanner (System.in);
         System.out.print("Welcome to the game menu\n1. Save Game\n2. Load Game\n3. Quit Game\n4. Resume Game\n5. New Game\nPlease enter the integer that represents your choice (1-4): ");
         while (exit == 0){
         //Get the user input of what the user wants to do
            choice = sc.nextLine();
         // tries to turn the user input into a integer, and if it can't be then the exception is caught
            try {
               numchoice = Integer.parseInt(choice);
            }
               catch (NumberFormatException e ){
               }
         //Gives the variable exit the value to exit out of this loop if the user input is correct
            if (numchoice >= 1 && numchoice <=5){
               exit = 1;
            }
            //Tells the user they have entered a incorrect choice and to re-enter their choice
            else {
               System.out.print("You did not enter an integer that is between 1-5\n Please enter the integer that repersents your choice (1-5): ");
               System.out.print("Welcome to the game menu\n1. Save Game\n2. Load Game\n3. Quit Game\n4. Resume Game\n5. New Game\nPlease enter the integer that represents your choice (1-5): ");
            }
         }
         return numchoice;
      }
   
   //Declaration of the newGame() method which creats and runs a new game for the users to play
      public static void newGame(){
      //Declaration of local variables
         int menu, choice;
         bpoints = 2;
         wpoints = 2;
         Scanner sc = new Scanner (System.in);
         System.out.println("Please enter the name of the game you wish to create (this will be the name the auto saved game will be called): ");
      //Gets the name of the auto saved game that the user wants
         autoname = sc.nextLine();
      //Calls the method initBoardsize() which asks the user for the size of the board
         initBoardsize();
         autosaveexit = 0;
      //Runs a loop for the autosave to start running
         while (autosaveexit == 0){
            autosaveexit = 1;
            first = 0;
         //Runs the method autosaveGame() which auto saves the game after both players have gone
            autosaveGame();
         //Asks the user to re-enter the name, because the name is taken
            if (autosaveexit == 0){
               System.out.println("The name you have entered is alreadly taken please enter another one (you can delete auto saved games in the start menu): ");
               autoname = sc.nextLine();
               initBoardsize();
            }
         }
         first = 1;
         board = new String [numsize[1]+1][numsize[0]+1];
      //Calls the method initBoard() which creats the game board
         initBoard();
      //The counted loop that is the number of turns the users can go before the game ends
         for (int i = 1; i <= numsize [1] * numsize [0] - gamecount; i++) {
         //Runs the method autosaveGame() which auto saves the game after both players have gone
            autosaveGame();
         //Runs the displayBoard() method which displays the game board for the users
            displayBoard();
            bflankpoints = 0;
            wflankpoints = 0;
         //Gets the result from the method validMove()
            menu = validMove();
         //Checks if the user wants to enter the game menu 
            if (menu == 1){
               choice = gameMenu();
            //Checks if the user wants to save the game 
               if (choice == 1){
                  saveGame ();
               }
               else if (choice == 2){
                  loadcounter = 1;
                  return;
               }
               //This exits the method and goes back to the start menu
               else if (choice == 3){
                  return;
               }
               else if (choice == 5){
                  newchoice = 1;
                  return;
               }
            }
            //Runs the outflanking methods
            else {
               outFlankVert();
               outFlankHori();
               outFlankDiag();
            }
         //counters to check who's turn it is
            if (move == 0 && valid == 1){
               move = 1;
            }
            else if (move == 1 && valid == 1){
               move = 0;
            }
            if (valid == 0){
               i -= 1;
            }
            valid = 0;
         }
         displayBoard();
      //Runs the method determinWinner() to check for the winner
         determinWinner();
      }
   //Declaration of the Exit() method which exits the program
      public static void Exit (){
         System.out.println("Thank you for playing Othello\nGoodbye and have a nice day!");
      //This exits the java program
         System.exit(0);
      }
   //Declaration of the initBoardsize() which asks the user for the size of the game board
      public static void initBoardsize(){
         String [] size = new String [2];
         int check = 0;
         Scanner sc = new Scanner (System.in);
         while (check == 0){
            System.out.print("Please enter the length of sides of the board (An integer below 27 and above 2): ");
         //The user enters the size of the game board
            size[0]=sc.nextLine();
         //Checks to see if the size the user entered is valid
            try {
               if (Integer.parseInt(size[0])>2 && Integer.parseInt(size[0])<=26)
               {
                  numsize[0] = Integer.parseInt(size[0]);
                  numsize[1] = numsize[0];
                  check = 1; 
               }
               else {
                  System.out.println("Error you have not entered an integer below 27 and above 2");
               }
            }
               catch (NumberFormatException e){ 
                  System.out.println("Error you have not entered an integer below 27 and above 2");
               }
         }
         System.out.println();
      } 
   
   //Declaration of the initBoard() method which creats the game board for a new game
      public static void initBoard (){
         int charcount = 64;
         int lowcharcount = 96;
         String [] sboard = {" "," ."};
         char character;
      //Gives the array board the different string values to make it the game board
         for (int i = 0; i < numsize [1]+1; i++){
            for (int j = 0; j < numsize [0]+1; j++){
               if (i == 0 && j != 0 && j != 1 || i == 0 && j == 1){
                  board [i][j] = " " + (char)(lowcharcount);
               }
               else if (j==0 && i != 0){
                  character = (char)charcount;
                  board [i][j] = Character.toString(character);
               }
               else if (i == 0 && j == 0){
                  board [i][j] = " ";
               }
               else if ((i == numsize [1]/2 && j == numsize [0]/2 )|| (i == numsize [1]/2+1 && j == numsize [0]/2+1)){
                  board [i][j] = " W";
               }  
               else if ((i == numsize [1]/2+1 && j == numsize [0]/2 )||( i== numsize [1]/2 && j == numsize [0]/2+1)){
                  board [i][j] = " B";
               } 
               else {
                  board [i][j] = " .";
               }
               lowcharcount += 1;
            }
            charcount += 1;
         }
      }
   
   
   //Declaration of the displayBoard () method which displays the board
      public static void displayBoard (){
      //loop used to display the board
         for (int i = 0; i < numsize [1]+1; i++)
         {
            for (int j = 0; j < numsize [0]+1; j++)
            {
               System.out.print(board [i][j]);
               if (i == 0 && j == numsize[0]){
               //Prints whos turn it is
                  if (move == 0){
                     System.out.print ("  It is blacks move");
                  }
                  else if (move == 1){
                     System.out.print ("  It is whites move");
                  }
               }
               if (i == 1 && j == numsize[0]){
               //Prints the points gained/lost 
                  System.out.print("  Black total points: "+bpoints+"\tBlack points Gained from move: "+bflankpoints);
               }
               else if (i == 2 && j == numsize[0]){
                  System.out.print("  White total points: "+wpoints+"\tWhite points Gained from move: "+wflankpoints);
               }
            }
            System.out.println();
         }
      }
   
   
   //Declaration of the validMove() method which checks for valid moves
      public static int validMove(){
         String input;
         int already_disk = 0;
         int menu_exit = 0;
         char vertical = ' ', horizontal = ' ' ;
         int exit = 0;
         int menu = 0;
         Scanner sc = new Scanner (System.in);
         System.out.println("Please enter your move(capital letter then lower case letter) or enter M for the menu: ");
         while (exit == 0){
            already_disk = 0;
         //Gets input from the user
            input = sc.nextLine();
            menu = 0;
         //Checks to see if the user input is valid
            try {
               if (input.length() == 2){
                  horizontal = input.charAt(0);
                  vertical = input.charAt(1);
               }
            }
               catch (StringIndexOutOfBoundsException e){
               }
            intnum = vertical;
            intnum = intnum - 96;
            letternum = horizontal;
            try{
               if (input.equals("M")){
                  menu = 1;
                  exit = 1;
                  menu_exit = 1;
               } 
               //checks to see if the location is valid
               else if ((board [letternum - 65+1] [intnum].equals(" W"))  ||( board [letternum - 65+1] [intnum].equals(" B"))){
                  System.out.println("You have not entered a valid move you can not place disks on occupied spaces1");
                  already_disk = 1;
               }
            }
               catch (ArrayIndexOutOfBoundsException e){
               }
            if (intnum == numsize [0]){
            //checks to see if the location is valid
               try {
                  if ((board [letternum - 65+1+1] [intnum].equals(" W" ))|| (board [letternum - 65+1+1] [intnum-1].equals(" W" ))|| (board [letternum - 65+1] [intnum-1].equals(" W" ))|| (board [letternum - 65] [intnum-1].equals(" W" ))|| (board [letternum - 65] [intnum].equals(" W" ))|| (board [letternum - 65+1+1] [intnum].equals(" B" ))||( board [letternum - 65+1+1] [intnum-1].equals(" B" ))||( board [letternum - 65+1] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum].equals(" B" ))&& already_disk == 0){
                     if (move == 0){
                        board [letternum - 65 + 1] [intnum] = " B"; 
                        bpoints +=1;
                        bflankpoints += 1;
                     }
                     else if (move == 1){
                        board [letternum - 65 + 1] [intnum] = " W"; 
                        wpoints += 1;
                        wflankpoints += 1;
                     }
                     exit = 1;
                  }
               }
                  catch (ArrayIndexOutOfBoundsException e) {
                  //checks to see if the location is valid
                     if ((board [letternum - 65][intnum].equals(" W" ))|| (board [letternum - 65 + 1][intnum - 1].equals(" W" ))||( board [letternum - 65][intnum - 1].equals(" W")) ||( board [letternum - 65][intnum].equals(" B" ))||( board [letternum - 65 + 1][intnum - 1].equals(" B")) ||( board [letternum - 65][intnum - 1] .equals(" B")) && already_disk == 0){
                        if (move == 0){
                           board [letternum - 65 + 1] [intnum] = " B"; 
                           bpoints +=1;
                           bflankpoints += 1;
                        }
                        else if (move == 1){
                           board [letternum - 65 + 1] [intnum] = " W"; 
                           wpoints += 1;
                           wflankpoints += 1;
                        }
                        exit = 1;
                     }
                  }
            }
            else if (letternum -64 == numsize [1]){
               try {
               //checks to see if the location is valid
                  if ((board [letternum - 65+1] [intnum-1].equals(" W" ))||( board [letternum - 65] [intnum-1].equals(" W" ))||( board [letternum - 65] [intnum].equals(" W" ))|| (board [letternum - 65] [intnum+1].equals(" W" ))||( board [letternum - 65+1] [intnum+1].equals(" W" ))||( board [letternum - 65+1] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum].equals(" B" ))||( board [letternum - 65] [intnum+1] .equals(" B" ))||( board [letternum - 65+1] [intnum+1].equals(" B" ))&& already_disk == 0){
                     if (move == 0){
                        board [letternum - 65 + 1] [intnum] = " B"; 
                        bpoints +=1;
                        bflankpoints += 1;
                     }
                     else if (move == 1){
                        board [letternum - 65 + 1] [intnum] = " W"; 
                        wpoints += 1;
                        wflankpoints += 1;
                     }
                     exit = 1;
                  }
               }
                  catch (ArrayIndexOutOfBoundsException e){
                  }
            }
            else {
            //checks to see if the location is valid
               try{
                  if ((board [letternum - 65+1+1] [intnum].equals(" W" )||( board [letternum - 65+1+1] [intnum+1].equals(" W" ))||( board [letternum - 65+1+1] [intnum-1].equals(" W" ))||( board [letternum - 65+1] [intnum-1].equals(" W" ))||( board [letternum - 65] [intnum-1].equals(" W" ))||( board [letternum - 65] [intnum].equals(" W" ))||( board [letternum - 65] [intnum+1].equals(" W" ))||( board [letternum - 65+1] [intnum+1].equals(" W"))||( board [letternum - 65+1+1] [intnum].equals(" B" ))||( board [letternum - 65+1+1] [intnum+1].equals(" B" ))||( board [letternum - 65+1+1] [intnum-1].equals(" B")) ||( board [letternum - 65+1] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum-1].equals(" B" ))||( board [letternum - 65] [intnum].equals(" B" ))||( board [letternum - 65] [intnum+1].equals(" B" ))||( board [letternum - 65+1] [intnum+1].equals(" B" ))&& already_disk == 0)){
                     if (move == 0 && already_disk == 0){
                        board [letternum - 65 + 1] [intnum] = " B"; 
                        bpoints +=1;
                        bflankpoints += 1;
                     }
                     else if (move == 1 && already_disk == 0){
                        board [letternum - 65 + 1] [intnum] = " W"; 
                        wpoints += 1;
                        wflankpoints += 1;
                     }
                     if (already_disk == 0){
                        exit = 1;
                     }
                  }
               }
                  catch (ArrayIndexOutOfBoundsException e){
                  }
            }
            if (exit == 1 && menu_exit == 0){
               valid = 1;
            }
            else if ((exit != 1 && menu_exit != 1) && already_disk == 0){
               System.out.println("You have not entered a valid move first enter the capital letter then the lower case letter: 2");
               valid = 0;
               menu_exit = 0;
            }
         }
         return menu;
      }
   
   //Declaration of the outFlankHori() method which makes the outflank action horizontally 
      public static void outFlankHori(){
         for (int j = intnum + 1; j <= numsize[0]; j++){
            if (move == 0){
               if (board [letternum - 64][j] == " ."){
                  break;
               }
               //Checks for horizontal outflanks
               else if (board [letternum - 64][j].equals(" B")){
                  for (int i = intnum + 1; i < j; i++){
                     board[letternum - 64][i] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [letternum - 64][j].equals(" .")){
                  break;
               }
               //Checks for horizontal outflanks
               else if (board [letternum - 64][j].equals( " W")){
                  for (int i = intnum + 1; i < j; i++){
                     board[letternum - 64][i] = " W";
                     wflankpoints += 1;
                     bflankpoints -= 1;
                     bpoints -= 1;
                     wpoints += 1;
                  }
                  break;
               }
            }
         }
         if (intnum != 1){
            for (int j = intnum - 1; j >= 1; j--){
               if (move == 0){
                  if (board [letternum - 64][j].equals(" .")){
                     break;
                  }
                  //Checks for horizontal outflanks
                  else if (board [letternum - 64][j].equals(" B")){
                     for (int i = intnum - 1; i > j; i--){
                        board[letternum - 64][i] = " B";
                        bflankpoints += 1;
                        wflankpoints -= 1;
                        bpoints += 1;
                        wpoints -= 1;
                     }
                     break;
                  }
               }
               if (move == 1){
                  if (board [letternum - 64][j].equals(" .")){
                     break;
                  }
                  //Checks for horizontal outflanks
                  else if (board [letternum - 64][j].equals(" W")){
                     for (int i = intnum - 1; i > j; i--){
                        board[letternum - 64][i] = " W";
                        wflankpoints += 1;
                        bflankpoints -= 1;
                        bpoints -= 1;
                        wpoints += 1;
                     }
                     break;
                  }
               }
            }
         }
      }
   
   
   
   //Declaration of the outFlankVert() method which makes the outflank action vertically 
      public static void outFlankVert(){
         for (int j = letternum - 64 + 1; j <= numsize[1]; j++){
            if (move == 0){
               if (board [j][intnum].equals(" .")){
                  break;
               }
               //Checks for vertical outflanks
               else if (board [j][intnum].equals(" B")){
                  for (int i = letternum - 64 + 1; i < j; i++){
                     board[i][intnum] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [j][intnum].equals(" .")){
                  break;
               }
               //Checks for vertical outflanks
               else if (board [j][intnum].equals(" W")){
                  for (int i = letternum - 64 + 1; i < j; i++){
                     board[i][intnum] = " W";
                     bflankpoints -= 1;
                     wflankpoints += 1;
                     bpoints -= 1;
                     wpoints += 1;
                  }
                  break;
               }
            }
         }
         if (letternum - 64 != 1){
            for (int j = letternum - 64 - 1; j >= 1; j--){
               if (move == 0){
                  if (board [j][intnum].equals(" .")){
                     break;
                  }
                  //Checks for vertical outflanks
                  else if (board [j][intnum].equals(" B")){
                     for (int i = letternum - 64 - 1; i > j; i--){
                        board[i][intnum] = " B";
                        bflankpoints += 1;
                        wflankpoints -= 1;
                        bpoints += 1;
                        wpoints -= 1;
                     }
                     break;
                  }
               }
               if (move == 1){
                  if (board [j][intnum].equals(" .")){
                     break;
                  }
                  //Checks for vertical outflanks
                  else if (board [j][intnum].equals(" W")){
                     for (int i = letternum - 64 - 1; i > j; i--){
                        board[i][intnum] = " W";
                        bflankpoints -= 1;
                        wflankpoints += 1;
                        bpoints -= 1;
                        wpoints += 1;
                     }
                     break;
                  }
               }
            }
         }
      }
   
   
   //Declaration of the outFlankVert() method which makes the outflank action diagonally
      public static void outFlankDiag(){
         int j = letternum - 64 + 1; 
         int a = intnum + 1;
         while ( j <= numsize[1] && a <= numsize[0]){
            if (move == 0){
               if (board [j][a].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j][a].equals(" B")){
                  int i = letternum - 64 + 1; 
                  int b = intnum + 1;
                  while ( i < j && b < a){
                     board[i][b] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                     i++;
                     b++;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [j][a].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j][a].equals(" W")){
                  int i2 = letternum - 64 + 1;
                  int b2 = intnum + 1;
                  while (i2 < j && b2 < a){
                     board[i2][b2] = " W";
                     bflankpoints -= 1;
                     wflankpoints += 1;
                     bpoints -= 1;
                     wpoints += 1;
                     i2++; 
                     b2++;
                  }
                  break;
               }
            }
            a++; 
            j++;
         }
         int j2 = letternum - 64 - 1; 
         int a2 = intnum - 1;
         while ( j2 >= 1 && a2 >= 1){
            if (move == 0){
               if (board [j2][a2].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j2][a2].equals(" B")){
                  int i3 = letternum - 64 - 1;
                  int b3 = intnum - 1;
                  while ( i3 > j2 && b3 > a2){
                     board[i3][b3] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                     i3--;
                     b3--;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [j2][a2].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j2][a2].equals(" W")){
                  int i4 = letternum - 64 - 1;
                  int b4 = intnum - 1;
                  while( i4 > j2 && b4 > a2){
                     board[i4][b4] = " W";
                     bflankpoints -= 1;
                     wflankpoints += 1;
                     bpoints -= 1;
                     wpoints += 1;
                     i4--;
                     b4--;
                  }
                  break;
               }
            }
            j2--;
            a2--;
         }
         int j3 = letternum - 64 + 1;
         int a3 = intnum - 1;
         while ( j3 <= numsize[1] && a3 >= 1){
            if (move == 0){
               if (board [j3][a3].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j3][a3].equals(" B")){
                  int i5 = letternum - 64 + 1;
                  int b5 = intnum - 1;
                  while (i5 < j3 && b5 > a3){
                     board[i5][b5] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                     i5++;
                     b5--;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [j3][a3].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j3][a3].equals(" W")){
                  int i6 = letternum - 64 + 1;
                  int b6 = intnum - 1;
                  while ( i6 < j3 && b6 > a3){
                     board[i6][b6] = " W";
                     bflankpoints -= 1;
                     wflankpoints += 1;
                     bpoints -= 1;
                     wpoints += 1;
                     i6++;
                     b6--;
                  }
                  break;
               }
            }
            a3--; 
            j3++;
         }
         int j4 = letternum - 64 - 1;
         int a4 = intnum + 1;
         while (a4 <= numsize[0] && j4 >= 1){
            if (move == 0){
               if (board [j4][a4].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j4][a4].equals(" B")){
                  int i7 = letternum - 64 - 1;
                  int b7 = intnum + 1;
                  while ( i7 > j4 && b7 < a4){
                     board[i7][b7] = " B";
                     bflankpoints += 1;
                     wflankpoints -= 1;
                     bpoints += 1;
                     wpoints -= 1;
                     i7--;
                     b7++;
                  }
                  break;
               }
            }
            if (move == 1){
               if (board [j4][a4].equals(" .")){
                  break;
               } 
               //Checks for diagonally outflanks
               else if (board [j4][a4].equals(" W")){
                  int i8 = letternum - 64 - 1;
                  int b8 = intnum + 1;
                  while (i8 > j4 && b8 < a4){
                     board[i8][b8] = " W";
                     bflankpoints -= 1;
                     wflankpoints += 1;
                     bpoints -= 1;
                     wpoints += 1;
                     i8--;
                     b8++;
                  }
                  break;
               }
            }
            j4--;
            a4++;
         }
      }
   
   
   //Declaration of the determinWinner() method which determines who is the winner of the game
      public static void determinWinner (){
      //Checks to see who won
         if (bpoints > wpoints){
            System.out.println("Congratulations Black you are the Winner of the game with a score of "+bpoints+" to "+wpoints+" \n\n");
         }
         else if (wpoints > bpoints){
            System.out.println("Congratulations White you are the Winner of the game with a score of "+wpoints+" to "+bpoints+" \n\n");
         }
         else if (bpoints == wpoints){
            System.out.println("Congratulations Both players you Both are the Winners of the game with a score of "+bpoints+" to "+wpoints+" \n\n");
         }
      
      }
   //Declaration of the autosaveGame() method for auto saving the game every time both player have made a move 
      public static void autosaveGame(){
         try{
         //Sets values for files
            File file = new File ("autosavedgames.txt");
            File autofile = new File (autoname+"-auto.txt");
            if (!file.exists()){
               file.createNewFile();
            }
         //Calls a BufferedReader for reading text documents
            BufferedReader read = new BufferedReader (new FileReader ("autosavedgames.txt"));
            int count = 0;
            String input;
         //Creats array/arraylists 
            ArrayList <String> autosavednamesL = new ArrayList <String> ();
            Object [] autosavednamesO;
            String [] autosavednames;
         
            if(first == 0){
            
               boolean exists = autofile.exists();
            //Gets the names of the auto saved games 
               if (exists == false){
                  autofile.createNewFile();
                  while ((input = read.readLine()) != null){
                     autosavednamesL.add(input);
                     count += 1;
                  }
               
                  autosavednamesO = new Object [autosavednamesL.size()];
                  autosavednamesO = autosavednamesL.toArray();
                  autosavednames = new String [autosavednamesL.size()];
               
                  for (int i = 0; i < count; i++){
                     autosavednames [i] = autosavednamesO[i].toString();
                  }
               //Deletes and recreats the file, because the file is set to Read Only.
                  file.delete();
                  file.createNewFile();
               //Write changes to the list of auto saved names
                  BufferedWriter write = new BufferedWriter (new FileWriter("autosavedgames.txt"));
                  for (int i = 0; i < count; i++){
                     write.write(autosavednames [i]);
                     write.newLine();
                  }
                  write.write(autoname+"-auto");
                  write.close();
                  autosaveexit = 1;
               }
               else {
                  autosaveexit = 0;
               }
            }
         
         
            if (move == 0 && first != 0 && autosaveexit == 1){
               autofile.delete();
               autofile.createNewFile();
            //Writes to the newly created text file the game board
               BufferedWriter write2 = new BufferedWriter (new FileWriter(autoname+"-auto.txt"));
               for (int j = 0; j < board.length; j++){
                  for (int i = 0; i < board[0].length; i++){
                     write2.write(board[j][i]);
                  }
                  write2.newLine();
               }
               write2.close();
            } 
         //Sets the files Read Only so they cannot be change
            file.setReadOnly();
            autofile.setReadOnly();
         }
            catch (IOException e){
            
            }
      
      
      }
   //Declaration of the saveGame () method which allows the user to save the game
      public static void saveGame (){
         boolean exists;
         int exit = 0;
         int count = 0;
         String input;
         ArrayList <String> savednamesL = new ArrayList <String> ();
         Object [] savednamesO;
         String [] savednames;
      
         Scanner sc = new Scanner (System.in);
      
         File file = new File ("savedgames.txt");
      
      //asks the user for the name of the game the wish to save as
         System.out.println("Please enter the name of the game you wish to save or enter R (capital) to return to the game or enter AUTO (capital) for the name of the saved game to be the same as the name of the game: ");
         filename = sc.nextLine();
         if (filename.equalsIgnoreCase("R")){
            return;
         }
         else if (filename.charAt(0) == 'A' && filename.charAt(1) == 'U' && filename.charAt(2) == 'T' && filename.charAt(3) == 'O' && filename.length() == 4){
            filename = autoname;
         }
      
         File savefile = new File (filename + ".txt");
      //Checks for the existence of the file
         exists = savefile.exists();
         while (exit != 1){
            if(exists == true){
               System.out.println("There is already a saved game with that name or you have entered an invalid name.\n Please enter another name for the game you wish to save or enter R (capital) to return to the game or enter AUTO (capital) for the name of the saved game to be the same as the name of the game: ");
               filename = sc.nextLine();
               if (filename.equalsIgnoreCase("R")){
                  return;
               }
               if (filename.charAt(0) == 'A' && filename.charAt(1) == 'U' && filename.charAt(2) == 'T' && filename.charAt(3) == 'O' && filename.length() == 4){
                  filename = autoname;
               }
            
               File savefileexit = new File (filename + ".txt");
               exists = savefileexit.exists();
            }
            else  {
               exit = 1;
            }
         }
      
         if (filename.equals("M") == false){
         
         
            try {

               if (exists == false){
                  file.createNewFile();
               }
            //Reads the list of saved games
               BufferedReader read = new BufferedReader (new FileReader ("savedgames.txt"));
               while ((input = read.readLine()) != null){
                  savednamesL.add(input);
                  count += 1;
               }
            
               savednamesO = new Object [savednamesL.size()];
               savednamesO = savednamesL.toArray();
               savednames = new String [savednamesL.size()];
            
               for (int i = 0; i < count; i++){
                  savednames [i] = savednamesO[i].toString();
               }
            //Deletes the file and recreats it so it can be written to
               file.delete();
               file.createNewFile();
            //Writes the changes to the file
               BufferedWriter write = new BufferedWriter (new FileWriter("savedgames.txt"));
               for (int i = 0; i < count; i++){
                  write.write(savednames [i]);
                  write.newLine();
               }
               write.write(filename);
               write.close();
            //Writes the game board to a text file
               BufferedWriter write2 = new BufferedWriter (new FileWriter (filename + ".txt"));
            
               for (int i = 0; i < board.length; i++){
                  for (int j = 0; j < board[0].length; j++){
                     write2.write(board[i][j]);
                  }
                  write2.newLine();
               }
               write2.close();
            
               file.setReadOnly();
               savefile.setReadOnly();
            }
            
               catch (IOException e){
                  System.out.println("Error");
               }
         }
      }
   
   
   
   //Declaration of the loadGame() method that allows the user to load a game
      public static void loadGame (){
      //Declaration of variables
         String input = "";
         String userinput = "";
         int exit = 0;
         int auto = 0;
         int error;
         int numchoice = 0;
         totalcount = 0;
         bpoints = 0;
         wpoints = 0;
         load = 1;
      //Declaration of arrays/arraylists
         ArrayList <String> savedgamenamesL = new ArrayList <String> ();
         Object [] savedgamenamesO;
         String [] savedgamenames; 
         ArrayList <String> gameboardL = new ArrayList <String> ();
         Object [] gameboardO;
         String [] gameboard;
         int count = 0;
         int addcount = 1;
         int addcount2 = 1;
         Scanner sc = new Scanner (System.in);
      //Asks the user for the selection of saved games to load
         System.out.println("Please choose which group of saved games is the game you wish to load located\n1. Most Recent Auto Saved Game\n2. Most Recent Normally Saved Game\n3. A Normally Saved Game\n4. A Auto Saved Game\nPlease enter the integer that represents your choice (1-4): ");
         input = sc.nextLine();
      //Checks the input so see if its valid
         try{
            numchoice = Integer.parseInt(input);
         }
            catch (NumberFormatException e){
            }
         if (numchoice > 0 && numchoice < 5){
            exit = 1;
         }
         while (exit == 0){
            System.out.println("You have entered an invalid choice!\nPlease choose in which group of saved games is the game you wish to load located\n1. Most Recent Auto Saved Game\n2. Most Recent Normally Saved Game\n3. A Normally Saved Game\n4. A Auto Saved Game\nPlease enter the integer that represents your choice (1-4): ");
            input = sc.nextLine();
            try{
               numchoice = Integer.parseInt(input);
            }
               catch (NumberFormatException e){
               }
            if (numchoice > 0 && numchoice < 5){
               exit = 1;
            }
         }
      
         if (numchoice == 1){
            int count1 = 0;
         //Reads the auto saved names
            try{
               BufferedReader rautoread = new BufferedReader (new FileReader ("autosavedgames.txt"));
            
               while ((input = rautoread.readLine()) != null){
                  savedgamenamesL.add(input);
               }
               if (savedgamenamesL.size() == 0){
                  System.out.println("No auto-saved games exisits!");
                  return;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               for (int i = 0; i < savedgamenames.length - 1; i++){
                  File rautofile = new File (savedgamenames [auto]+".txt");
                  File rautofile2 = new File (savedgamenames [i+1]+".txt");
                  if (rautofile.lastModified() < rautofile2.lastModified()){
                     auto = i+1;
                  }
               }
               loadautoname = savedgamenames[auto];
               BufferedReader rautogameread = new BufferedReader (new FileReader (savedgamenames[auto]+".txt"));
            //Reads in the game board and gives the array Board [][] its values.
               while ((input = rautogameread.readLine()) != null){
                  gameboardL.add(input); 
               }
               gameboardO = new Object [gameboardL.size()];
               gameboard = new String [gameboardO.length];
               gameboardO = gameboardL.toArray();
            //Reads in the game board and gives the array Board [][] its values.
               for (int i = 0; i < gameboardO.length; i++){
                  gameboard [i] = gameboardO [i].toString();
               }
               board = new String [gameboard.length] [gameboard.length];
               for (int i = 0; i < gameboardO.length - 1; i++){
                  if (gameboard [i].length() != gameboard [i+1].length()){
                     error = 1;
                  }
               }
               for (int j = 0; j < gameboard.length; j++){
                  addcount = 1;
                  for( int i = 0; i < gameboard.length; i++){
                     if (i != 0){
                        addcount2 = addcount + 2;
                     }
                     if (i == 0 && j != 0){
                        board [j][i] = gameboard [j].substring(0,1);
                     }
                     else if (j == 0&& i ==0){
                        board [j][i] = " ";
                     }
                     //Reads in the game board and gives the array Board [][] its values.
                     else  if (i < gameboard[j].length()){
                        board [j][i] = gameboard [j].substring(addcount,addcount2);
                     //Gives the points and tells the program whos turn it is
                        if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'W'){
                           wpoints ++;
                           totalcount ++;
                        }
                        else if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'B'){
                           bpoints ++;
                           totalcount++;
                        }
                     } 
                     if (i != 0){
                        addcount = addcount2;
                     }
                  }
               }
               numsize [0] = board.length - 1;
               numsize [1] = board.length - 1;
               if (totalcount % 2 == 0){
                  move = 0;
               }
               else {
                  move = 1;
               }
            }
               catch (IOException e){
               }
         }
         
         else if (numchoice == 2){
            int count1 = 0;
         //Reads the auto saved names
            try{
               BufferedReader rautoread = new BufferedReader (new FileReader ("savedgames.txt"));
            
               while ((input = rautoread.readLine()) != null){
                  savedgamenamesL.add(input);
               }
               if (savedgamenamesL.size() == 0){
                  System.out.println("No saved games exisits!");
                  return;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               for (int i = 0; i < savedgamenames.length - 1; i++){
                  File rautofile = new File (savedgamenames [auto]+".txt");
                  File rautofile2 = new File (savedgamenames [i+1]+".txt");
                  if (rautofile.lastModified() > rautofile2.lastModified()){
                     auto = i+1;
                  }
               }
            //Reads in the game board and gives the array Board [][] its values.
               loadautoname = savedgamenames[auto];
               BufferedReader autogameread = new BufferedReader (new FileReader (loadautoname+".txt"));
               while ((input = autogameread.readLine()) != null){
                  gameboardL.add(input); 
               }
               gameboardO = new Object [gameboardL.size()];
               gameboard = new String [gameboardO.length];
               gameboardO = gameboardL.toArray();
            
               for (int i = 0; i < gameboardO.length; i++){
                  gameboard [i] = gameboardO [i].toString();
               }
            //Reads in the game board and gives the array Board [][] its values.
               board = new String [gameboard.length] [gameboard.length];
               for (int i = 0; i < gameboardO.length - 1; i++){
                  if (gameboard [i].length() != gameboard [i+1].length()){
                     error = 1;
                  }
               }
               for (int j = 0; j < gameboard.length; j++){
                  addcount = 1;
                  for( int i = 0; i < gameboard.length; i++){
                     if (i != 0){
                        addcount2 = addcount + 2;
                     }
                  //Reads in the game board and gives the array Board [][] its values.
                     if (i == 0 && j != 0){
                        board [j][i] = gameboard [j].substring(0,1);
                     }
                     else if (j == 0&& i ==0){
                        board [j][i] = " ";
                     }
                     else  if (i < gameboard[j].length()){
                        board [j][i] = gameboard [j].substring(addcount,addcount2);
                        if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'W'){
                           wpoints ++;
                           totalcount ++;
                        }
                        else if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'B'){
                           bpoints ++;
                           totalcount++;
                        }
                     } 
                     if (i != 0){
                        addcount = addcount2;
                     }
                  }
               }
               numsize [0] = board.length - 1;
               numsize [1] = board.length - 1;
               if (totalcount % 2 == 0){
                  move = 0;
               }
               else {
                  move = 1;
               }
            }
               catch (IOException e){
               }
         }
         
         else if (numchoice == 3){
            try{
            //Get the saved game names
               BufferedReader read = new BufferedReader (new FileReader ("savedgames.txt"));
               System.out.println("Please enter the number corresponding to the name of the saved game you wish to enter or the name of the saved game or enter M (capital) for the menue");
            
               while ((input = read.readLine()) != null){
                  System.out.println(count+1+". "+input);
                  savedgamenamesL.add(input);
                  count += 1;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               exit = 0;
               while (exit == 0){
               //Gets user input for which game to load
                  userinput = sc.nextLine();
               //Checks the user input to see if its valid
                  try{
                     numchoice = Integer.parseInt(userinput);
                     if (numchoice <= count){
                        userinput = savedgamenames [numchoice - 1];
                        exit = 1;
                     }
                  }
                     catch (NumberFormatException e){
                     }
                  try {
                     if (userinput.charAt(0) == 'M' && userinput.length() == 1){
                        return;
                     }
                  }
                     catch (StringIndexOutOfBoundsException e){
                     }
                  if (exit != 1){
                     for (int i = 0; i < count; i++){
                        if (userinput.equals(savedgamenames [i])){
                           exit = 1;
                        }
                     }
                  }
                  else if (exit != 1){
                     System.out.println("You have entered an invalid choice, enter the number repersenting the save game or enter the name of the save game or enter M (capital) for the menu");
                  }
               }
            //Reads in the game board and gives the array Board [][] its values.
               BufferedReader read2 = new BufferedReader (new FileReader (userinput+".txt"));
               while ((input = read2.readLine()) != null){
                  gameboardL.add(input); 
               }
               gameboardO = new Object [gameboardL.size()];
               gameboard = new String [gameboardO.length];
               gameboardO = gameboardL.toArray();
            
               for (int i = 0; i < gameboardO.length; i++){
                  gameboard [i] = gameboardO [i].toString();
               }
            
               for (int i = 0; i < gameboardO.length - 1; i++){
                  if (gameboard [i].length() != gameboard [i+1].length()){
                     error = 1;
                  }
               }
            //Reads in the game board and gives the array Board [][] its values.
               board = new String [gameboard.length][gameboard.length];
               for (int j = 0; j < gameboard.length; j++){
                  addcount = 1;
                  for( int i = 0; i < gameboard.length; i++){
                     if (i != 0){
                        addcount2 = addcount + 2;
                     }
                     if (i == 0 && j != 0){
                        board [j][i] = gameboard [j].substring(0,1);
                     }
                     else if (j == 0&& i ==0){
                        board [j][i] = " ";
                     }
                     //Reads in the game board and gives the array Board [][] its values.
                     else  if (i < gameboard[j].length()){
                        board [j][i] = gameboard [j].substring(addcount,addcount2);
                        if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'W'){
                           wpoints ++;
                           totalcount ++;
                        }
                        else if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'B'){
                           bpoints ++;
                           totalcount++;
                        }
                     } 
                     if (i != 0){
                        addcount = addcount2;
                     }
                  }
               }
               numsize [0] = board.length - 1;
               numsize [1] = board.length - 1;
               if (totalcount % 2 == 0){
                  move = 0;
               }
               else {
                  move = 1;
               }
            
            }
               catch (IOException e){
               }
         }
         
         
         else if (numchoice == 4){
            try{
            //Reads in the saved game names
               BufferedReader read = new BufferedReader (new FileReader ("autosavedgames.txt"));
               System.out.println("Please enter the number corresponding to the name of the saved game you wish to enter or the name of the saved game or enter M (capital) for the menue");
            
               while ((input = read.readLine()) != null){
                  System.out.println(count+1+". "+input);
                  savedgamenamesL.add(input);
                  count += 1;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               exit = 0;
               while (exit == 0){
               //Gets the user input
                  userinput = sc.nextLine();
               //Checks the user input to see if its correct
                  try{
                     numchoice = Integer.parseInt(userinput);
                     if (numchoice <= count){
                        userinput = savedgamenames [numchoice - 1];
                        exit = 1;
                     }
                  }
                     catch (NumberFormatException e){
                     }
               
                  if (userinput.charAt(0) == 'M' && userinput.length() == 1){
                     return;
                  }
                  else if (exit != 1){
                     for (int i = 0; i < count; i++){
                        if (userinput.equals(savedgamenames [i])){
                           exit = 1;
                        }
                     }
                  }
                  else if (exit != 1){
                     System.out.println("You have entered an invalid choice, enter the number repersenting the save game or enter the name of the save game or enter M (capital) for the menu");
                  }
               }
            //Reads in the game board and gives the array Board [][] its values.
               BufferedReader read2 = new BufferedReader (new FileReader (userinput+".txt"));
               while ((input = read2.readLine()) != null){
                  gameboardL.add(input); 
               }
               gameboardO = new Object [gameboardL.size()];
               gameboard = new String [gameboardO.length];
               gameboardO = gameboardL.toArray();
            
               for (int i = 0; i < gameboardO.length; i++){
                  gameboard [i] = gameboardO [i].toString();
               }
            //Reads in the game board and gives the array Board [][] its values.
               for (int i = 0; i < gameboardO.length - 1; i++){
                  if (gameboard [i].length() != gameboard [i+1].length()){
                     error = 1;
                  }
               }
               board = new String [gameboard.length][gameboard.length];
               for (int j = 0; j < gameboard.length; j++){
                  addcount = 1;
                  for( int i = 0; i < gameboard.length; i++){
                     if (i != 0){
                        addcount2 = addcount + 2;
                     }
                     if (i == 0 && j != 0){
                        board [j][i] = gameboard [j].substring(0,1);
                     }
                     else if (j == 0&& i ==0){
                        board [j][i] = " ";
                     }
                     //Reads in the game board and gives the array Board [][] its values.
                     else  if (i < gameboard[j].length()){
                        board [j][i] = gameboard [j].substring(addcount,addcount2);
                        if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'W'){
                           wpoints ++;
                           totalcount ++;
                        }
                        else if (board [j][i].charAt(0)== ' ' && board [j][i].charAt(1)== 'B'){
                           bpoints ++;
                           totalcount++;
                        }
                     } 
                     if (i != 0){
                        addcount = addcount2;
                     }
                  }
               }
               numsize [0] = board.length - 1;
               numsize [1] = board.length - 1;
               if (totalcount % 2 == 0){
                  move = 0;
               }
               else {
                  move = 1;
               }
            
            }
               catch (IOException e){
               }
         }
      }
   //Declaration of the contGame () method which continues the game after load game
      public static void contGame(){
         int menu, choice;
         Scanner sc = new Scanner (System.in);
      //Get the name the user wants as the auto saved name
         System.out.println("You may now change the name of the game or enter AUTO to have the name of the game to stay the same (this will be the name the auto saved game will be called): ");
         autoname = sc.nextLine();
      
         while (autoname.length() <= 0){
            System.out.println("You have entered a invalid name! Please re-enter the name of the game you wish to continue or enter AUTO to have the name of the game to stay the same (this will be the name the auto saved game will be called): ");
            autoname = sc.nextLine();
         }
         if (autoname.charAt(0) == 'A' && autoname.charAt(1) == 'U' && autoname.charAt(2) == 'T' && autoname.charAt(3) == 'O' && autoname.length() == 4){
            autoname = loadautoname;
         
            autosaveexit = 2;
         }
         if (autosaveexit != 2){ 
            autosaveexit = 0;
         }
         while (autosaveexit == 0){
            autosaveexit = 1;
            first = 0;
            autosaveGame();
         //Checks the user input on the name 
            if (autosaveexit == 0){
               System.out.println("The name you have entered is alreadly taken please enter another one (you can delete saved games in the start menu): ");
               autoname = sc.nextLine();
               if (autoname.charAt(0) == 'A' && autoname.charAt(1) == 'U' && autoname.charAt(2) == 'T' && autoname.charAt(3) == 'O' && autoname.length() == 4){
                  autoname = loadautoname;
                  autosaveexit = 2;
               }
            }
         }
         first = 1;
      
         for (int i = 1; i <= numsize [1] * numsize [0] - gamecount - totalcount + 4 ; i++) {
            autosaveGame();
            displayBoard();
            bflankpoints = 0;
            wflankpoints = 0;
         //Gets the user choices and runs the corresponding methods
            menu = validMove();
            if (menu == 1){
               choice = gameMenu();
               if (choice == 1){
                  saveGame ();
               }
               else if (choice == 2){
                  loadcounter = 1;
                  return;
               }
               else if (choice == 3){
                  return;
               }
               else if (choice == 5){
                  newchoice = 1;
                  return;
               }
            }
            else {
            //Runs the outflanking methods
               outFlankVert();
               outFlankHori();
               outFlankDiag();
            }
            if (move == 0 && valid == 1){
               move = 1;
            }
            else if (move == 1 && valid == 1){
               move = 0;
            }
            if (valid == 0){
               i -= 1;
            }
            valid = 0;
         }
      //Outputs the board and determines the winner
         displayBoard();
         determinWinner();
      }
   
   
   //Declaration of the deletsaveGame() method which delets saved games
      public static void deletsaveGame(){
         String input = "";
         String userinput = "";
         int exit = 0;
         int auto = 0;
         int error;
         int numchoice = 0;
      
         ArrayList <String> savedgamenamesL = new ArrayList <String> ();
         Object [] savedgamenamesO;
         String [] savedgamenames; 
      
         int count = 0;
         int addcount = 1;
         int addcount2 = 1;
         Scanner sc = new Scanner (System.in);
      //Asks for the section the saved game is located
         System.out.println("Please choice in which group of saved games is the game you wish to delete located\n1. Most Recent Auto Saved Game\n2. Most Recent Normally Saved Game\n3. A Normally Saved Game\n4. A Auto Saved Game\nPlease enter the integer that represents your choice (1-4): ");
         input = sc.nextLine();
      //Validates the user input
         try{
            numchoice = Integer.parseInt(input);
         }
            catch (NumberFormatException e){
            }
         if (numchoice > 0 && numchoice < 5){
            exit = 1;
         }
         while (exit == 0){
            System.out.println("You have entered an invalid choice!\nPlease choice in which group of saved games is the game you wish to load located\n1. Most Recent Auto Saved Game\n2. Most Recent Normally Saved Game\n3. A Normally Saved Game\n4. A Auto Saved Game\nPlease enter the integer that represents your choice (1-4): ");
            input = sc.nextLine();
            try{
               numchoice = Integer.parseInt(input);
            }
               catch (NumberFormatException e){
               }
         }
      
         if (numchoice == 1){
            int count1 = 0;
         //Reads in names of saved games
            try{
               BufferedReader rautoread = new BufferedReader (new FileReader ("autosavedgames.txt"));
            
               while ((input = rautoread.readLine()) != null){
                  savedgamenamesL.add(input);
               }
               if (savedgamenamesL.size() == 0){
                  System.out.println("No auto saved game exisits!");
                  return;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               for (int i = 0; i < savedgamenames.length - 1; i++){
                  File rautofile = new File (savedgamenames [auto]+".txt");
                  File rautofile2 = new File (savedgamenames [i+1]+".txt");
                  if (rautofile.lastModified() < rautofile2.lastModified()){
                     auto = i+1;
                  }
               }
               loadautoname = savedgamenames[auto];
            //deletes the file to rewrite to it
               File file = new File ("autosavedgames.txt");
               File deletefile = new File (loadautoname + ".txt");
            
               file.delete();
               file.createNewFile();
               BufferedWriter write = new BufferedWriter (new FileWriter("autosavedgames.txt"));
               for (int i = 0; i < savedgamenames.length; i++){
                  if (i != auto){
                     write.write(savedgamenames [i]);
                     write.newLine();
                  }
               }
               write.close();
            //Deletes the file
               deletefile.delete();
               file.setReadOnly();
            }
               catch (IOException e){
               }
         
         }
      
      
         if (numchoice == 2){
            int count1 = 0;
         //Reads in the saved game names
            try{
               BufferedReader rautoread = new BufferedReader (new FileReader ("savedgames.txt"));
            
               while ((input = rautoread.readLine()) != null){
                  savedgamenamesL.add(input);
               }
               if (savedgamenamesL.size() == 0){
                  System.out.println("No such saved game exisits!");
                  return;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
            //Reads in the saved game names
               for (int i = 0; i < savedgamenames.length - 1; i++){
                  File rautofile = new File (savedgamenames [auto]+".txt");
                  File rautofile2 = new File (savedgamenames [i+1]+".txt");
                  if (rautofile.lastModified() < rautofile2.lastModified()){
                     auto = i+1;
                  }
               }
               loadautoname = savedgamenames[auto];
               File file = new File ("savedgames.txt");
               File deletefile = new File (loadautoname + ".txt");
            //Deletes the file to change it
               file.delete();
               file.createNewFile();
               BufferedWriter write = new BufferedWriter (new FileWriter("savedgames.txt"));
               for (int i = 0; i < savedgamenames.length; i++){
                  if (i != auto){
                     write.write(savedgamenames [i]);
                     write.newLine();
                  }
               }
               write.close();
            //Deletes the saved game 
               deletefile.delete();
            
            
               file.setReadOnly();
            
            
            }
               catch (IOException e){
               }
         
         }
      
      
         if (numchoice == 3){
            int count1 = 0;
         //Reads in the saved game names
            try{
               BufferedReader read = new BufferedReader (new FileReader ("savedgames.txt"));
               System.out.println("Please enter the number corresponding to the name of the saved game you wish to enter or the name of the saved game or enter M (capital) for the menu");
            
               while ((input = read.readLine()) != null){
                  System.out.println(count+1+". "+input);
                  savedgamenamesL.add(input);
                  count += 1;
               }
            //Reads in the saved game names
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               exit = 0;
            //Gets the user input on which game to delete
               while (exit == 0){
                  userinput = sc.nextLine();
               //Validates the users choice
                  try{
                     numchoice = Integer.parseInt(userinput);
                     if (numchoice <= count){
                        userinput = savedgamenames [numchoice - 1];
                        exit = 1;
                     }
                  }
                     catch (NumberFormatException e){
                     }
               //Validates the users choice
                  if (userinput.charAt(0) == 'M' && userinput.length() == 1){
                     return;
                  }
                  else if (exit != 1){
                     for (int i = 0; i < count; i++){
                        if (userinput.equals(savedgamenames [i])){
                           exit = 1;
                        }
                     }
                  }
                  else if (exit != 1){
                     System.out.println("You have entered an invalid choice, enter the number repersenting the save game or enter the name of the save game or enter M (capital) for the menu");
                  }
               }
               loadautoname = userinput;
            
               File file = new File ("savedgames.txt");
               File deletefile = new File (loadautoname + ".txt");
            
               file.delete();
               file.createNewFile();
               BufferedWriter write = new BufferedWriter (new FileWriter("savedgames.txt"));
               for (int i = 0; i < savedgamenames.length; i++){
                  if (i != auto){
                     write.write(savedgamenames [i]);
                     write.newLine();
                  }
               }
               write.close();
            //Deletes the saved game 
               deletefile.delete();
            
            
               file.setReadOnly();
            
            
            }
               catch (IOException e){
               }
         
         }
      
         if (numchoice == 4){
            int count1 = 0;
         //Reads in the saved game names
            try{
               BufferedReader read = new BufferedReader (new FileReader ("autosavedgames.txt"));
               System.out.println("Please enter the number corresponding to the name of the saved game you wish to enter or the name of the saved game or enter M (capital) for the menue");
            
               while ((input = read.readLine()) != null){
                  System.out.println(count+1+". "+input);
                  savedgamenamesL.add(input);
                  count += 1;
               }
               savedgamenamesO = new Object [savedgamenamesL.size()];
               savedgamenamesO = savedgamenamesL.toArray();
               savedgamenames = new String [savedgamenamesO.length];
               for (int i = 0; i < savedgamenames.length; i++){
                  savedgamenames [i] = savedgamenamesO [i].toString();
               }
               exit = 0;
               while (exit == 0){
               //Gets user input on the saved game to delete
                  userinput = sc.nextLine();
               //Validates the users choice
                  try{
                     numchoice = Integer.parseInt(userinput);
                     if (numchoice <= count){
                        userinput = savedgamenames [numchoice - 1];
                        exit = 1;
                     }
                  }
                     catch (NumberFormatException e){
                     }
               //Validates the users choice
                  if (userinput.charAt(0) == 'M' && userinput.length() == 1){
                     return;
                  }
                  else if (exit != 1){
                     for (int i = 0; i < count; i++){
                        if (userinput.equals(savedgamenames [i])){
                           exit = 1;
                        }
                     }
                  }
                  else if (exit != 1){
                     System.out.println("You have entered an invalid choice, enter the number repersenting the save game or enter the name of the save game or enter M (capital) for the menu");
                  }
               }
               loadautoname = userinput;
            
               File file = new File ("autosavedgames.txt");
               File deletefile = new File (loadautoname + ".txt");
            //Delete file to change it
               file.delete();
               file.createNewFile();
               BufferedWriter write = new BufferedWriter (new FileWriter("autosavedgames.txt"));
               for (int i = 0; i < savedgamenames.length; i++){
                  if (i != auto){
                     write.write(savedgamenames [i]);
                     write.newLine();
                  }
               }
               write.close();
            //Delete saved game
               deletefile.delete();
               file.setReadOnly();
            }
               catch (IOException e){
               }
         }
         System.out.println("The saved game has been deleted");
      }
   }