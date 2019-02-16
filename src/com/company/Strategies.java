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


    Strategies(TYPE type, State state,int depth) throws CloneNotSupportedException {


        switch (type){

            case MINIMAX:

                score = miniMax(state,depth);

                break;

            case ALPHABETA:

                score = alphaBeta(state,depth, Integer.MIN_VALUE,Integer.MAX_VALUE);

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
    private int miniMax(State state,int depth) throws CloneNotSupportedException {

        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;


        if(depth == 0 || state.isGameOver()){

            return  maxScore ;
        }

        if(!state.isHumanTurn){

            for(String move : state.possibleMoves){
                State currentState = state.clone();
                State.Move possibleMove = new State.Move(move);
                currentState.placeLine(possibleMove,false);
                int currentScore = miniMax(currentState,depth-1);

                if(maxScore <= currentScore){
                    maxScore = currentScore;
                    this.bestMove = possibleMove;
                }

                state.placeLine(possibleMove,true);
            }


        }else{

            for(String move : state.possibleMoves){
                State currentState = state.clone();
                State.Move possibleMove = new State.Move(move);
                currentState.placeLine(possibleMove,false);
                int currentScore = miniMax(currentState,depth-1);

                if(minScore >= currentScore){
                    minScore = currentScore;
                    this.bestMove = possibleMove;
                }

                state.placeLine(possibleMove,true);

            }




        }




        return maxScore ;

    }


    /**
     * alphaBeta logic implementation
     * @param state current board state
     * @param depth curent ply
     * @param alpha alpha value
     * @param beta beta balue
     * @return best score for player
     */
    private int alphaBeta(State state, int depth, int alpha , int beta) throws CloneNotSupportedException {


        if (depth == 0 || state.isGameOver()) {

            return alpha - beta;
        }

        if (!state.isHumanTurn) {

            for (String move : state.possibleMoves) {
                State currentState = state.clone();
                State.Move possibleMove = new State.Move(move);
                currentState.placeLine(possibleMove,false);
                int currentScore = alphaBeta(currentState, depth - 1, alpha, beta);

                if (currentScore > alpha) {
                    alpha = currentScore;
                    this.bestMove = possibleMove;
                }

                if (alpha >= beta) {
                    break;
                }

                currentState.placeLine(possibleMove,true);
            }

            return alpha;

        } else {

            for (String move : state.possibleMoves) {
                State currentState = state.clone();
                State.Move possibleMove = new State.Move(move);
                currentState.placeLine(possibleMove,false);
                int currentScore = alphaBeta(currentState, depth - 1, alpha, beta);

                if (currentScore < beta) {
                    beta = currentScore;
                    this.bestMove = possibleMove;
                }

                if (alpha >= beta) {
                    break;
                }

                currentState.placeLine(possibleMove,true);
            }

            return beta;
        }


    }


}
