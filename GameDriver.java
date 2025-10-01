import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    public static void main(String args[]) {
        ArrayList<Player> board = new ArrayList<>();
        Scanner stdin = new Scanner(System.in);
        String input = "";
        //clear the screen
        for(int z = 0; z<50;z++) {
        	System.out.println();
        }
        //get input from the user for names of the players
        do {
            System.out.println("Please input the name of the player\nEnter done if complete\n");
            input = stdin.nextLine();
            if(!input.equals("done"))
                board.add(new Player(40,input));
        }while(!(input.equalsIgnoreCase("Done")) || board.size()<=1);
        //set size for commanderDamage arrays
        for(int i = 0; i<board.size();i++){
            board.get(i).numPlayers(board.size()-1);
        }
        input = "";
        String playerName = "";
        int num = 0;
        int i = -1;
        int otherP = -1;
        boolean flag;
        //clear the screen
        for(int z = 0; z<50;z++) {
        	System.out.println();
        }
       //print the boar state
        for(int f = 0; f < board.size(); f++) {
            board.get(f).printPlayerState();
        }
        do {
        	//get the player who has action towards them
            do {
                System.out.println("Enter name of player for action");
                playerName = stdin.nextLine();
                for(int f = 0; f<board.size();f++) {
                    if(board.get(f).getName().equalsIgnoreCase(playerName)) {
                        i = f;
                    }
                }
            }while(i<0);
            //get input from the user on what action to take
            do {
                System.out.println("Please input one of the commands:\nHeal\nDamage\nComDamage\nremoveComDamage\nPoison\nremovePoison");
                input = stdin.nextLine();
            }while(!input.equalsIgnoreCase("heal")&&!input.equalsIgnoreCase("damage")&&!input.equalsIgnoreCase("comdamage")&&!input.equalsIgnoreCase("removecomdamage")&&!input.equalsIgnoreCase("poison")&&!input.equalsIgnoreCase("removepoison"));
            //input the amount of the action to do
            do {
                System.out.println("Please input the amount to do");
                num = stdin.nextInt();
            }while(num<=0);
            if(input.equalsIgnoreCase("heal")) {
                board.get(i).gainLife(num);
            }else if(input.equalsIgnoreCase("damage")) {
                board.get(i).takeDamage(num);
            }else if(input.equalsIgnoreCase("comdamage")) {
            	//get the player dealing the commander damage
                do{
                    System.out.println("Please enter the name of the player dealing the comander damage");
                    String na = stdin.nextLine();
                    for(int f = 0; f<board.size(); f++){
                        if(board.get(f).getName().equalsIgnoreCase(na) && !board.get(f).getisDead() && f!=i){
                            otherP = f;
                        }
                    }
                }while(otherP>board.size() || otherP<0);
                if(otherP>i){
                    otherP--;
                }
                board.get(i).takeCommanderDamage(num,otherP);
                otherP = -1;
            }else if(input.equalsIgnoreCase("removeComDamage")) {
            	//get the player to remove the commander damage from
                do{
                    System.out.println("Please enter the name of the player dealing the comander damage");
                    String na = stdin.nextLine();
                    for(int f = 0; f<board.size(); f++){
                        if(board.get(f).getName().equalsIgnoreCase(na) && !board.get(f).getisDead() && f!=i){
                            otherP = f;
                        }
                    }
                }while(otherP>board.size() || otherP<0);
                if(otherP>i){
                    otherP--;
                }
                board.get(i).removeCommanderDamage(num,otherP);
                otherP = -1;
            }else if(input.equalsIgnoreCase("addPoison")) {
                board.get(i).takePoison(num);
            }else {
                board.get(i).removePoison(num);
            }
            //clear the screen
            for(int z = 0; z<50;z++) {
            	System.out.println();
            }
            //print the board state
            for(int f = 0; f < board.size(); f++) {
                 board.get(f).printPlayerState();
            }
            //check if a player has won the game
            String winningPlayer = "";
            int stillAlive = 0;
            for(int f = 0; f<board.size();f++) {
                if(!board.get(f).getisDead()) {
                    stillAlive++;
                    winningPlayer = board.get(f).getName();
                }
            }
            if(stillAlive==1) {
                System.out.println(winningPlayer + " has won the game");
                flag = true;
            }else{
            	//reset the instance variables
                flag = false; 
                playerName = "";
                num = -1;
                i = -1;
            }
        }while(!flag);  
        //close the scanner
        stdin.close();
    }

}

