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
    private static Strategies.TYPE strategyType;

    public static void main(String[] args) {

        System.out.println("Please enter the size of the board : ");
        boardSize = scanner.nextInt();
        System.out.println("Please enter the name for Player :  ");
        playerName = scanner.next();
        System.out.print("Please select the strategy : ");

        for(Strategies.TYPE strategy:Strategies.TYPE.values()){

            System.out.print(strategy + " ");
        }
        System.out.println();

        strategyType = Strategies.TYPE.valueOf(scanner.next());

        State rootState = new State();
        rootState.init();

        rootState.isHumanTurn = true;


        while (!rootState.isGameOver()){

            String currentPlayer = rootState.isHumanTurn?Main.playerName:Main.aiName;
            System.out.println("User : "  + currentPlayer );

            if(rootState.isHumanTurn){

                System.out.println("Please enter x coordinate : ");
                int x = scanner.nextInt();
                System.out.println("Please enter y coordinate : ");
                int y = scanner.nextInt();
                System.out.println("Please enter line position : ");

                System.out.println("Available line positions  : ");
                for(Box.Position availableMove: Box.Position.values()){

                    System.out.print(availableMove+" ");
                }
                System.out.println();
                String enteredPosition = scanner.next();
                State.Move playerMove = new State.Move(x,y,Box.Position.valueOf(enteredPosition));
                rootState.placeLine(playerMove);


            }else{

                try {

                    Strategies  strategy = new Strategies(strategyType, rootState,1);
                    rootState.placeLine(strategy.bestMove);
                    System.out.println("Move : " + strategy.bestMove.toString());

                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }

            rootState.printState();
        }


    }









}
