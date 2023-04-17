import java.util.Random;
import java.util.Scanner;

public class CustomGame extends Game {

    public String playCustomGame() {
        System.out.println("Please, enter the secret code's length:");
        int secretCodeLength = intInputValidation();
        int numberOfSymbols = numberOfSymbolsValidation(secretCodeLength);
        String secretCode = getSecretCode(secretCodeLength, numberOfSymbols);
        String guessedCode = "";
        System.out.println("Let's start! Enter guessed code:");
        while(true) {
            System.out.println("Turn: " + getTurns());
            guessedCode = getGuessedCode();
            compareNumbers(secretCode, guessedCode);
        }

    }

    public String getGuessedCode() {
        Scanner scanner = new Scanner(System.in);
        String guessedCode = scanner.nextLine();
        return guessedCode;
    }

    public String getSecretCode(int secretCodeLength, int numberOfSymbols) {
        Random random = new Random();
        StringBuilder randomCode = new StringBuilder();
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz".substring(0, numberOfSymbols);
        String message = numberOfSymbols < 10 ? String.format("0-%d%n", numberOfSymbols) :
                String.format("0-9, a-%c", (char) 86 + numberOfSymbols);
        System.out.println("Characters that can appear in secret code are: " + message);
        int i = 0;
        while (i < secretCodeLength) {
            char nextRandom = chars.charAt(random.nextInt(chars.length()));
            if (randomCode.indexOf(Character.toString(nextRandom)) == -1) {
                randomCode.append(nextRandom);
                i++;
            }
        }
        return randomCode.toString();
    }

    public int numberOfSymbolsValidation(int secretCodeLength) {
        System.out.println("Input the number of possible symbols in the code:");
        boolean failure = true;
        int numberOfSymbols = 0;
        while (failure) {
            numberOfSymbols = intInputValidation();
            if (numberOfSymbols < secretCodeLength) {
                System.out.printf("Error: it's not possible to generate a code with a length " +
                        "of %d with %d unique symbols%n", secretCodeLength, numberOfSymbols);
            } else if (numberOfSymbols > 36) {
                System.out.println("Error: the unique code can be up to 36 characters long");
            } else {
                failure = false;
            }
        }

        return numberOfSymbols;
    }
}

