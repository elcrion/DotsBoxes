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

            return  state.aiScore - state.playerScore ;
        }



        if(!state.isHumanTurn){


            return getMiniMaxScore(state,depth,true,maxScore);

        }else{

            return getMiniMaxScore(state,depth,false,minScore);



        }





    }


    /**
     * Main implementation for MinMax recursive call
     * @param state current state
     * @param depth current ply
     * @param isMax Max or Min player
     * @param score max or min score
     * @return current score
     * @throws CloneNotSupportedException
     */
    private  int getMiniMaxScore(State state,int depth,boolean isMax,int score) throws CloneNotSupportedException {

        for(String move : state.possibleMoves){
            state.nextState = state.clone();
            State.Move possibleMove = new State.Move(move);
            state.nextState.placeLine(possibleMove);
            int currentScore = miniMax(state.nextState,depth-1);

            if(isMax && score <= currentScore){
                score = currentScore;
                this.bestMove = possibleMove;
            }

            if(!isMax && score >= currentScore){

                score = currentScore;
                this.bestMove = possibleMove;
            }



        }

        return  score;
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

            return state.aiScore - state.playerScore ;
        }

        if (!state.isHumanTurn) {

           return   getAlphaBetaScore(state,depth,true,alpha,beta);


        } else {

          return getAlphaBetaScore(state,depth,false,alpha,beta);
        }


    }



    /**
     * Main implementation for AlphaBeta recursive call
     * @param state current state
     * @param depth current ply
     * @param isMax Max or Min player
     * @param alpha  score
     * @param beta  score
     * @return current score
     * @throws CloneNotSupportedException
     */
    private  int getAlphaBetaScore(State state,int depth,boolean isMax,int alpha,int beta) throws CloneNotSupportedException {

        for (String move : state.possibleMoves) {
            state.nextState = state.clone();
            State.Move possibleMove = new State.Move(move);
            state.nextState.placeLine(possibleMove);
            int currentScore = alphaBeta(state.nextState, depth - 1, alpha, beta);

            if (isMax && currentScore > alpha) {
                alpha = currentScore;
                this.bestMove = possibleMove;
            }

            if (!isMax && currentScore < beta) {
                beta = currentScore;
                this.bestMove = possibleMove;
            }

            if (alpha >= beta) {
                break;
            }

        }



        return  isMax?alpha:beta ;
    }




}
