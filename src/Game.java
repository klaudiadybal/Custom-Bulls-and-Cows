import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private int turns = 1;

    public int getTurns() {
        return turns;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }

    public void playGame() {
        System.out.println("Choose game mode: 1 - standard game, 2 - custom game");
        int gameMode = intInputValidation();
        boolean failure = true;
        while (failure) {
            if (gameMode != 1 && gameMode != 2) {
                System.out.println("Enter valid game mode.");
                gameMode = intInputValidation();
            } else {
                failure = false;
            }
        }

        switch (gameMode) {
            case 1 -> {
                StandardGame standardGame = new StandardGame();
                standardGame.playStandardGame();
            }
            case 2 -> {
                CustomGame customGame = new CustomGame();
                customGame.playCustomGame();
            }
        }

    }

    public int intInputValidation() {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean failure = true;
        while (failure) {
            try {
                number = scanner.nextInt();
                failure = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input");
                scanner.nextLine();
            }
        }
        return number;
    }

    public void compareNumbers(String randomNumber, String guessedNumber) {
        int bulls = 0;
        int cows = 0;
        char [] random = String.valueOf(randomNumber).toCharArray();
        char [] guessed = String.valueOf(guessedNumber).toCharArray();
        try {
            for (int i = 0; i < random.length; i++) {
                if (random[i] == guessed[i]) {
                    bulls++;
                }
            }

            for (int i = 0; i < random.length; i++) {
                if (Arrays.toString(random).indexOf(guessed[i]) != -1) {
                    cows++;

                    if (random[i] == guessed[i]) {
                        cows--;
                    }
                }
            }
            turns++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
            return;
        }


        String message = "";
        if (bulls > 0 || cows > 0) {
            message = String.format("%d bulls(s) and %d cow(s).", bulls, cows);
        } else {
            message = "None.";
        }
        System.out.printf("Grade: %s%n", message);

        if(bulls == random.length) {
            System.out.println("Congratulations! You guessed the secret code.");
            exit(0);
        }
    }
}
