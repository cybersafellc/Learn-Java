package org.variables.explores;

import java.util.ArrayList;

public class InitialAllVariable {
    private String textString = "my name is robot";
    private int textInt = 2;
    private double textDouble = 2.5;
    private boolean textBoolean = false;
    private char textChar = 'A';

    public static void main(){
        InitialAllVariable obj = new InitialAllVariable();
        ArrayList data = new ArrayList();
        data.add(obj.getValueString());
        data.add(obj.getValueInt());
        data.add(obj.getValueDouble());
        data.add(obj.getValueBool());
        data.add(obj.getTextChar());
        for(Object d : data){
            System.out.println(d);
        }
    }
    public String getValueString(){
        return textString;
    }
    public int getValueInt(){
        return textInt;
    }
    public double getValueDouble(){
        return textDouble;
    }
    public boolean getValueBool(){
        return textBoolean;
    }
    public char getTextChar(){
        return textChar;
    }
}
