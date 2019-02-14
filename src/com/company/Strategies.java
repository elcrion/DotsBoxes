/*
 * Developed by Gleb Iakovlev  on 2/14/19 4:29 PM.
 * Last modified  2/14/19 4:21 PM
 *
 * Copyright (c) 2019. All rights reserved.
 */

package com.company;

/**
 * Main Strategies for AI logic
 */
public class Strategies {

    public enum TYPE {MINIMAX,ALPHABETA}
    public int score;
    public State.Move bestMove;

    Strategies(TYPE type, State state,int depth){


        switch (type){

            case MINIMAX:

                score = miniMax(state,depth);

                break;

        }

    }

    /**
     * Minimax representation
     * Going over states recursively to find maximum or minimum score depends on the player
     * @param state current board state
     * @param depth current recursion depth
     * @return
     */
    private int miniMax(State state,int depth){

        int maxScore = state.aiScore;
        int minScore = state.playerScore;
        int currentScore;

        if(depth ==0 || state.isGameOver()){

            return  maxScore;
        }

        if(state.isHumanTurn){

            for(String move : state.possibleMoves){

                State.Move possibleMove = new State.Move(move);
                state.placeLine(possibleMove,false);
                currentScore = miniMax(state,depth-1);
                if(maxScore <= currentScore){
                    maxScore = currentScore;
                    this.bestMove = possibleMove;
                }

                state.placeLine(possibleMove,true);
            }


        }else{

            for(String move : state.possibleMoves){

                State.Move possibleMove = new State.Move(move);
                state.placeLine(possibleMove,false);
                currentScore = miniMax(state,depth-1);
                if(minScore >= currentScore){
                    minScore = currentScore;
                    this.bestMove = possibleMove;
                }

                state.placeLine(possibleMove,true);
            }




        }




        return maxScore;

    }


}
