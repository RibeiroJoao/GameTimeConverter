package com.company.gametimeconverter;

/**
 * @Author Joao Peixe Ribeiro
 */
public class GameTimeConverter {

    public static void main(String[] args) {
        if (args.length == 1) {
            giveOutputToUser(new RibeiroGameTimeConverter().convertGameTime(args[0]));
        }
        System.err.println("USAGE: java -jar GameTimeConverter \"your-game-time\"");
    }

    private static void giveOutputToUser(String outputGameTime) {
        System.out.println(outputGameTime);
        System.exit(-1);
    }
}
