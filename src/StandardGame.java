import java.util.Random;

public class StandardGame extends Game {

    public int getGuessedNumber() {
        int guessedNumber = intInputValidation();
        return guessedNumber;
    }

    public int getRandomNumber() {
        System.out.println("Please, enter the secret code's length:");
        int secretCodeLength = intInputValidation();
        boolean failure = true;
        while(failure) {
            if (secretCodeLength > 10) {
                System.out.println("Error: can't generate a secret number with a length of " + secretCodeLength +
                        "because there aren't enough unique digits.");
                secretCodeLength = intInputValidation();
            } else if (secretCodeLength < 1) {
                System.out.println("Invalid input.");
                secretCodeLength = intInputValidation();
            } else {
                failure = false;
            }
        }

        Random random = new Random();
        StringBuilder randomCode = new StringBuilder();
        int i = 0;
        while (i < secretCodeLength) {
            int randomInt = random.nextInt(10);
            if(randomCode.indexOf(String.valueOf(randomInt)) == -1) {
                randomCode.append(randomInt);
                i++;
            }
        }
        return Integer.parseInt(randomCode.toString());
    }

    public void playStandardGame() {
        int randomNumber = getRandomNumber();
        int gueesedNumber = 0;
        System.out.println("Let's start! Enter guessed number:");
        while(true) {
            System.out.println("Turn: " + getTurns());
            gueesedNumber = getGuessedNumber();
            compareNumbers(String.valueOf(randomNumber), String.valueOf(gueesedNumber));

        }
    }


}
