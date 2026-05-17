package org.variables.practice;

public class ConstantsFinal {
    public static void main(String[] args){
        final int MINUTE_PER_HOUR = 60;
        // MINUTE_PER_HOUR = 20; // error Cannot assign a value to final variable 'MINUTE_PER_HOUR'
        System.out.println(MINUTE_PER_HOUR);
    }
}
