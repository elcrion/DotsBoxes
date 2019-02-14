package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class State {

    int playerScore;
    int aiScore;
    Box[][] board;
    List<Move> possibleMoves = new CopyOnWriteArrayList<Move>();


    public void init(){

        for(int x = 0;x< Main.boardSize; x++){

            for(int y = 0;y< Main.boardSize; y++) {

                HashMap<Box.Position,Boolean> boxMap = new HashMap<Box.Position, Boolean>();

                for(Box.Position boxPosition : Box.Position.values()){

                    boxMap.put(boxPosition,false);
                    possibleMoves.add(new Move(x,y,boxPosition));

                }

                board[x][y] = new Box(boxMap);

            }

        }

    }


    public boolean isGameOver(){


        return true;
    }









    class Move{

        int x;
        int y;
        Box.Position linePosition;

        Move(int x, int y, Box.Position linePosition){

            this.x = x;
            this.y = y;
            this.linePosition = linePosition;

        }


        public  String toString(){


            return this.x+" "+this.y+" "+linePosition.toString();

        }


        public Move fromInput(int x , int y, String position){

            this.x = x;
            this.y =y;
            this.linePosition = Box.Position.valueOf(position);

            return  this;
        }

    }







}
