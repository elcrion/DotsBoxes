/*
 * Developed by Gleb Iakovlev  on 2/14/19 4:29 PM.
 * Last modified  2/14/19 4:24 PM
 *
 * Copyright (c) 2019. All rights reserved.
 */

package com.company;

import java.util.Scanner;

/**
 * Main class for user input
 *
 * boardSize : current board size, input from user
 * playerName : name of the player displayed on the Box
 * aiName  : name of AI player, could be potentially changed
 *
 */
public class Main {


    public static int  boardSize;
    public static String playerName = "Me";
    public static  String aiName ="Ai";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Please enter the size of the board : ");
        boardSize = scanner.nextInt();
        System.out.println("Please enter the name for Player :  ");
        playerName = scanner.next();


        State rootState = new State();
        rootState.init();

        rootState.isHumanTurn = true;


        while (rootState.isGameOver()){


            if(rootState.isHumanTurn){


                System.out.println("Please enter x coordinate : ");
                int x = scanner.nextInt();
                System.out.println("Please enter y coordinate : ");
                int y = scanner.nextInt();
                System.out.println("Please enter line position : ");

                System.out.println("Available line positions  : ");

                String enteredPosition = scanner.next();
                State.Move playerMove = new State.Move(x,y,Box.Position.valueOf(enteredPosition));
                rootState.placeLine(playerMove,false);


            }else{


                Strategies strategy = new Strategies(Strategies.TYPE.MINIMAX, rootState,1);
                rootState.placeLine(strategy.bestMove,false);


            }


            rootState.printState();

        }


    }









}
