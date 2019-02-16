
/*
 * Developed by Gleb Iakovlev  on 2/14/19 4:29 PM.
 * Last modified  2/14/19 4:22 PM
 *
 * Copyright (c) 2019. All rights reserved.
 */

package com.company;

import java.util.HashMap;


/**
 * Box representation
 */
public class Box  {


    public enum Position {TOP,BOTTOM,LEFT,RIGHT}
    HashMap<Position,Boolean> lines = new HashMap<Position, Boolean>();
    public String player = "";

    Box(HashMap<Position,Boolean> lines ){

        this.lines = lines;

    }

    @Override
    public Box clone () {


        Box box = new Box((HashMap<Position, Boolean>) this.lines.clone());


        return box;
    }


    /**
     * Check if Box has some dots not connected yet
     * @return
     */
    public boolean isBoxOpen(){


        return this.lines.containsValue(Boolean.FALSE);

    }


}
