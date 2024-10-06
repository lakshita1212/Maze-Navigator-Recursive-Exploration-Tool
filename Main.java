/*
 Lakshita Madhavan
 Maze Project
 */




//import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




//Main Class
public class Main{




    //Main Method
    public static void main(String[] args) throws FileNotFoundException {




        //Opens file
        File myMazeFile = new File("maze.dat");
        Scanner scan = new Scanner(myMazeFile);




        //Gets number of rows and columns in maze based on dimensions given in first line
        int numRows=scan.nextInt();
        int numCol=scan.nextInt();




        //Starts cursor at the beginning of the maze
        scan.nextLine();




        //creates a new array
        char maze[][]=new char[numRows][numCol];


        //Variables for starting position
        int startX=0;
        int startY=0;




        //copies content from maze file to maze 2D array
        for (int i=0;i<numRows;i++){
            String line = scan.nextLine();
            for (int j=0;j<numCol;j++){
                char elem=line.charAt(j);
                maze[i][j]=elem;


                //finds the position of starting plus character
                if (elem=='+'){
                    startX=i;
                    startY=j;
                }
            }
        }






        //final if else statement that prints the maze or says not able to solve
        if (move(maze,startX,startY)){
            print(maze);
            System.out.println("Maze has been solved!");
        }
        else{
            System.out.print("This maze does not have any way to solve it.");
        }




        //closes the scanner
        scan.close();


    }






    //print function that prints out the maze
    public static void print(char[][] arr){


        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){


                //prints out the color of plus character in green
                if(arr[i][j]=='+'){
                    System.out.print("\u001B[32m" + arr[i][j] + "\u001B[0m");
                }
                else{
                    System.out.print(arr[i][j]);}
            }
            System.out.println();
        }


    }


    //checks if position is in bounds and a valid place to move to
    public static boolean validSpot(char[][] arr, int x, int y){
        if (x>=0 && x<arr.length && y<arr[0].length && y>=0){
            if (arr[x][y]!='X'&&arr[x][y]!='.'&&arr[x][y]!='+'){
                return true;
            }
        }
        return false;
    }




    //actual recursive function that traverses through the maze and backtracks
    public static boolean move(char[][] maze,int row, int col){




        //base case - return true if position is '-'
        if (maze[row][col]=='-'){
            return true;
        }




        //variable declared to track movement
        boolean foundWay=false;




        //up
        if (validSpot(maze,row-1,col)){
            maze[row][col]='+';
            foundWay=move(maze,row-1,col);
            if (foundWay)
                return true;
        }




        //left            
        if ( validSpot(maze,row,col-1)   ){
            maze[row][col]='+';
            foundWay=move(maze,row,col-1);
            if (foundWay)
                return true;
        }


        //down
        if (validSpot(maze,row+1,col) ){
            maze[row][col]='+';
            foundWay=move(maze,row+1,col);
            if (foundWay)
                return true;
        }




        //right
        if (validSpot(maze,row,col+1) ){
            maze[row][col]='+';
            foundWay=move(maze,row,col+1);
            if (foundWay)
                return true;
        }


       
        //if none of the directions worked, mark with '.' and backtrack
        if (!foundWay){
            maze[row][col]='.';
            return false;
        }


    //return false if end has not been found
    return false;
}


}














