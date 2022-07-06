package hit.and.blow;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class HitAndBlow {
    public void startGame() {

        Scanner sca = new Scanner(System.in);

        int correctLength = 4;
        ArrayList<Integer> correct = generateCorrect(correctLength);
        System.out.println("correct is " + correct);

        ArrayList<Integer> playerAnswer = new ArrayList<>();

        int trial = 0;
        int hit = 0;
        while (hit != correctLength) {
            playerAnswer.clear();
            hit = 0;
            int blow = 0;

            ArrayList<Integer> playerAnswerReverse = new ArrayList<>();

            try {
                System.out.println("4桁の数字を入力してください:");
                int inputtedNumber = sca.nextInt();

                if (inputtedNumber > 9999)
                    continue;

                while (inputtedNumber > 0) {
                    playerAnswerReverse.add(inputtedNumber % 10);
                    inputtedNumber /= 10;
                }
                for (int i = 3; i >= 0; i--) {
                    playerAnswer.add(playerAnswerReverse.get(i));
                }

                System.out.println("your answer is" + playerAnswer);

                for (int i = 0; i < correctLength; i++) {
                    if (correct.indexOf(playerAnswer.get(i)) >= 0)
                        blow++;

                    if (playerAnswer.get(i) == correct.get(i)) {
                        blow--;
                        hit++;
                    }
                }

                System.out.printf("hit: %d, blow: %d%n", hit, blow);

            } catch (InputMismatchException e) {
                System.out.println("数字で入力してください");
                sca.next();
            }

            trial++;
        }
        sca.close();
        System.out.printf("Congratulation! You win at %d times.", trial);
    }

    private ArrayList<Integer> generateCorrect(int correctLength) {
        Random rand = new Random();

        ArrayList<Integer> correct = new ArrayList<>();

        int initialNumber = rand.nextInt(8) + 1;
        correct.add(initialNumber);
        System.out.println(correct);

        while (correct.size() < correctLength) {
            int generatedNumber = rand.nextInt(9);
            if (correct.indexOf(generatedNumber) < 0)
                correct.add(generatedNumber);
        }

        return correct;
    }
}
