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
        System.out.print("Please select the strategy : ");

        int index = 0;
        for(Strategies.TYPE strategy:Strategies.TYPE.values()){
            index++;
            System.out.print(index +". " + strategy + "  ");
        }

        System.out.println();

        strategyType = Strategies.TYPE.values()[scanner.nextInt()-1];

        State rootState = new State();
        rootState.init();

        rootState.isHumanTurn = true;


        while (!rootState.isGameOver()){

            String currentPlayer = rootState.isHumanTurn?Main.playerName:Main.aiName;
            System.out.println("User : "  + currentPlayer );

            if(rootState.isHumanTurn){

                System.out.println("Please enter row coordinate : ");
                int x = scanner.nextInt()-1;
                System.out.println("Please enter column coordinate : ");
                int y = scanner.nextInt()-1;
                System.out.println("Please enter line position : ");
                System.out.println("Available line positions : ");
                index = 0;
                for(Box.Position availableMove: Box.Position.values()){
                    index++;
                    System.out.print(index + ". "+availableMove+"  ");
                }
                System.out.println();
                Box.Position enteredPosition = Box.Position.values()[scanner.nextInt()-1];
                State.Move playerMove = new State.Move(x,y,enteredPosition);
                rootState.placeLine(playerMove);


            }else{

                try {

                    Strategies  strategy = new Strategies(strategyType, rootState,1);
                    System.out.println("Move : " + strategy.bestMove.toString());

                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }

            rootState.printState();
        }


    }





}
