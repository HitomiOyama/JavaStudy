package hit.and.blow;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class HitAndBlow {
    final int CORRECT_LENGTH = 4;
    final ArrayList<Integer> CORRECT = generateCorrect(CORRECT_LENGTH);

    public void startGame() {
        Scanner sca = new Scanner(System.in);

        System.out.println("correct is " + CORRECT);

        int trial = trialUntilReachCorrect(sca);

        sca.close();

        showResultMesage(trial);
    }

    private ArrayList<Integer> generateCorrect(int CORRECT_LENGTH) {
        Random rand = new Random();

        ArrayList<Integer> CORRECT_ARRAY = new ArrayList<>();

        addNumbersToCorrect(CORRECT_LENGTH, rand, CORRECT_ARRAY);

        return CORRECT_ARRAY;
    }

    private void addNumbersToCorrect(int CORRECT_LENGTH, Random rand, ArrayList<Integer> CORRECT_ARRAY) {
        final int MAXIMUM_OF_ONE_DIGIT_NUM = 9;

        int initialNumber = generatePosiitveNumber(rand);
        CORRECT_ARRAY.add(initialNumber);

        while (shorterThanCorrectLength(CORRECT_LENGTH, CORRECT_ARRAY)) {
            int generatedNumber = rand.nextInt(MAXIMUM_OF_ONE_DIGIT_NUM);

            if (!isAlreadyIncludedInCorrect(CORRECT_ARRAY, generatedNumber))
                CORRECT_ARRAY.add(generatedNumber);
        }
    }

    private boolean isAlreadyIncludedInCorrect(ArrayList<Integer> CORRECT, int generatedNumber) {
        return CORRECT.indexOf(generatedNumber) > 0;
    }

    private boolean shorterThanCorrectLength(int CORRECT_LENGTH, ArrayList<Integer> CORRECT) {
        return CORRECT.size() < CORRECT_LENGTH;
    }

    private int generatePosiitveNumber(Random rand) {
        return rand.nextInt(8) + 1;
    }

    private int trialUntilReachCorrect(Scanner sca) {
        int trial = 1;
        boolean isPlayerAnswerCorrect = false;

        while (!isPlayerAnswerCorrect) {
            int hit = initHit();
            int blow = initBlow();

            try {
                showGuidance();
                int inputtedNumber = sca.nextInt();

                if (!IsInputtedNumberFourDigits(inputtedNumber))
                    continue;

                ArrayList<Integer> playerAnswer = formatPlayerAnswer(inputtedNumber);

                for (int i = 0; i < CORRECT_LENGTH; i++) {
                    if (isBlow(playerAnswer, i))
                        blow++;

                    if (isHit(playerAnswer, i)) {
                        blow--;
                        hit++;
                    }
                }

                if (isPlayerAnswerCorrect(hit)) {
                    isPlayerAnswerCorrect = true;
                    break;
                }

                showHitAndBlowNumberEach(hit, blow);

            } catch (InputMismatchException e) {
                showErrorMessage();
                sca.next();
            }

            trial++;
        }
        return trial;
    }

    private int initBlow() {
        int blow = 0;
        return blow;
    }

    private int initHit() {
        int hit = 0;
        return hit;
    }

    private void showGuidance() {
        System.out.println(CORRECT_LENGTH + "桁の数字を入力してください:");
    }

    private boolean IsInputtedNumberFourDigits(int inputtedNumber) {
        return (inputtedNumber <= 9999) || (inputtedNumber >= 1000);
    }

    private ArrayList<Integer> formatPlayerAnswer(int inputtedNumber) {
        ArrayList<Integer> playerAnswerArray = new ArrayList<>();
        ArrayList<Integer> playerAnswerReverseArray = new ArrayList<>();
        while (inputtedNumber > 0) {
            playerAnswerReverseArray.add(inputtedNumber % 10);
            inputtedNumber /= 10;
        }
        for (int i = 3; i >= 0; i--) {
            playerAnswerArray.add(playerAnswerReverseArray.get(i));
        }

        return playerAnswerArray;
    }

    private boolean isHit(ArrayList<Integer> playerAnswer, int i) {
        return playerAnswer.get(i) == CORRECT.get(i);
    }

    private boolean isBlow(ArrayList<Integer> playerAnswer, int i) {
        return CORRECT.indexOf(playerAnswer.get(i)) >= 0;
    }

    private void showHitAndBlowNumberEach(int hit, int blow) {
        System.out.printf("hit: %d, blow: %d%n", hit, blow);
    }

    private void showErrorMessage() {
        System.out.println("数字で入力してください");
    }

    private boolean isPlayerAnswerCorrect(int hit) {
        return hit == CORRECT_LENGTH;
    }

    private void showResultMesage(int trial) {
        System.out.printf("Congratulation! You win at %d times.", trial);
    }

}
