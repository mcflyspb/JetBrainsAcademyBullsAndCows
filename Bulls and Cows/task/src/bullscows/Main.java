package bullscows;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static final String USER_INPUT = "Please, enter the secret code's length:";
    private static final String POSSIBLE_SYMBOLS = "Input the number of possible symbols in the code:";

    private static final String START_GAME = "Okay, let's start a game!";
    private static final String END_GAME = "Congratulations! You guessed the secret code.";
    private static final String ERROR_MESSAGE = "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.";

    public static void main(String[] args) {
        String code = "1234";
        //System.out.println(START_MESSAGE);
        //System.out.println();
        boolean gameStop = true;
        int turn = 1;
        int turnResult[] = new int[2];


        Scanner scanner = new Scanner(System.in);
        System.out.println(USER_INPUT);

        String inputCountString = scanner.next();
        String inputCountStringChecked = inputCountString.replaceAll("\\D+", "");
        if (!Objects.equals(inputCountString, inputCountStringChecked) || Objects.equals(inputCountString, "0")) {
            System.out.println("Error: \"" + inputCountString + "\" isn't a valid number.");
            return;
        }


        int inputCount = Integer.parseInt(inputCountString);

        System.out.println(POSSIBLE_SYMBOLS);
        int symbolCount = scanner.nextInt();

        if (symbolCount > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }


        int printSymbolCount = symbolCount - 1;

        if (inputCount > symbolCount) {
            System.out.println("Error: it's not possible to generate a code with a length of " + inputCount + " with "+ symbolCount + " unique symbols.");
            return;
        }

        StringBuilder stars = new StringBuilder();
        for (int x = 0; x < inputCount; x++) {
            stars.append("*");
        }

        if (symbolCount < 10) {
            System.out.println("The secret is prepared: " + stars.toString() + " (0-" + printSymbolCount  + ").");
        } else {
            String l = (char)(86 + symbolCount) + "";
            System.out.println("The secret is prepared: " + stars.toString() + " (0-9, a-" + l + ").");
        }

        if (inputCount > 10) {
            System.out.println(ERROR_MESSAGE);
            return;
        }


        code = newGenerateRandomNumber(inputCount, symbolCount);


        //System.out.println(code);

        System.out.println(START_GAME);

        while (gameStop) {

            System.out.println("Turn: " + turn + ":");
            Scanner scanner2 = new Scanner(System.in);

            String inputDigit = scanner2.nextLine();

            turnResult = checkBullCow(inputDigit, code, inputCount);
            StringBuilder outputLine = new StringBuilder("Grade: ");

            if (turnResult[0] == code.length()) {
                System.out.println("Grade: " + code.length() + " bulls");
                System.out.println(END_GAME);
                return;
            } else if (turnResult[0] > 0 && turnResult[1] > 0) {
                outputLine.append(turnResult[0] + " bull(s) and " + turnResult[1] + " cow(s). ");
            } else if (turnResult[0] > 0) {
                outputLine.append(turnResult[0] + " bull(s). ");
            } else if (turnResult[1] > 0) {
                outputLine.append(turnResult[1] + " cow(s). ");
            } else {
                outputLine.append("None. ");
            }

            System.out.println(outputLine.toString());
            turn++;
        }
    }

    private static String newGenerateRandomNumber(int lenght, int symbolCount){
        String[] array = new String[lenght];
        Set<String> hashSet = new HashSet<>();
        while (hashSet.size() < lenght) {
            String x = (char)getRandom(symbolCount) + "";
            hashSet.add(x);
        }
        StringBuilder output = new StringBuilder();
        hashSet.forEach((c) -> output.append(c));
        return output.reverse().toString();
    }

    private static int getRandom(int symbolCount) {
        Random random = new Random();
        int rnd;
        while (true) {
            rnd = random.nextInt(symbolCount);
            if (rnd < 10) {
                return 48 + rnd;
            } else {
                return rnd + 87;
            }
        }
    }


    private static int[] checkBullCow(String input, String code, int inputCount) {
        int bull = 0;
        int cow = 0;
        String[] inputStr = input.split("");
        String[] codeStr = code.split("");

        for (int x = 0; x < inputCount; x++ ) {
            if (Objects.equals(inputStr[x],codeStr[x])) {
                bull++;
            }
        }

        for (String x : inputStr) {
            for (String y : codeStr) {
                if (Objects.equals(x,y)) {
                    cow++;
                }
            }
        }
        cow = cow - bull;
        return new int[] {bull, cow};
    }
}
