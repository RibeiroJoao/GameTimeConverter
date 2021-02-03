
/**
 * @Author Joao Peixe Ribeiro
 */
public class GameTimeConverter {

    public static void main(String[] args) {
        if (areArgsAsExpected(args)) {
            giveOutputToUser(new RibeiroGameTimeConverter().convertGameTime(getInput(args[0])));
        }
        System.out.println("USAGE: java -jar GameTimeConverter \"<your-game-time>\"");
    }

    //TODO clarify requirements: How many args should be accepted?
    private static boolean areArgsAsExpected(String[] args) {
        return args.length == 1 && args[0].startsWith("\"") && args[0].endsWith("\"");
    }

    private static String getInput(String arg) {
        return arg.replaceAll("\"", "");
    }

    private static void giveOutputToUser(String outputGameTime) {
        System.out.println(outputGameTime);
        System.exit(-1);
    }
}
