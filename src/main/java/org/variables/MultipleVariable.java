package org.variables;

public class MultipleVariable {
    public static void main(String[] args){
        declareManyofVariable();
        oneValuetoMultipleVariable();
    }
    public static void declareManyofVariable(){
        int x = 50, y = 10, z = 20;
        System.out.println(x + y + z);
    }
    public static void oneValuetoMultipleVariable(){
        int x,y,z;
        x = y = z = 2;
        System.out.println(x + y + z);
    }
}
