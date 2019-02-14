package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class Box  {


    public enum Position {TOP,BOTTOM,LEFT,RIGHT}
    HashMap<Position,Boolean> lines = new HashMap<Position, Boolean>();
    boolean isHuman;
    public String player;

    Box(HashMap<Position,Boolean> lines ){

        this.lines = lines;

    }




    public boolean isBoxNotEnclosed(){


        return this.lines.containsKey(Boolean.FALSE);

    }


}
