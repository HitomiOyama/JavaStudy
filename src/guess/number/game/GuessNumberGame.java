package guess.number.game;

import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class GuessNumberGame {
    private final int INITIAL_TRIAL = 1;
    private final int MAX_TRIALS = 5;
    private Random rnd = new Random();

    public void startGame() {

        int numOfTrials = INITIAL_TRIAL;

        int correct = generateCorrect();

        showGameDescription();

        int finalNumOfTrials = loopUntilPlayersAnswerIsCorrectOrReachMaxTrials(correct, numOfTrials);

        if (!isChallengesUnderMaxTrials(MAX_TRIALS, finalNumOfTrials))
            showMessageForFail(correct);

    }

    private int loopUntilPlayersAnswerIsCorrectOrReachMaxTrials(int correct, int numOfTrials) {
        Scanner scanner = new Scanner(System.in);
        boolean isChallengesUnderMaxTrials = isChallengesUnderMaxTrials(MAX_TRIALS,
                numOfTrials);

        while (isChallengesUnderMaxTrials) {
            showNumOfTrials(numOfTrials);

            int playersAnswer = scanner.nextInt();
            try {

                if (isPlayersAnswerEqualsToCorrect(correct, playersAnswer)) {
                    showMessageForCorrect(numOfTrials);
                    return numOfTrials;
                }

                if (isPlayersAnswerUnderCorrect(correct, playersAnswer)) {
                    numOfTrials = increaseNumOfTrials(numOfTrials);
                    isChallengesUnderMaxTrials = isChallengesUnderMaxTrials(MAX_TRIALS, numOfTrials);
                    showMessageForSmaller();
                    continue;
                }

                numOfTrials = increaseNumOfTrials(numOfTrials);
                isChallengesUnderMaxTrials = isChallengesUnderMaxTrials(MAX_TRIALS, numOfTrials);
                showMessageForLarger();

                continue;

            } catch (InputMismatchException e) {

                showErrorMessage();
            }

        }

        scanner.close();
        return numOfTrials;

    }

    private void showErrorMessage() {
        System.out.println("?????????????????????????????????");
    }

    private int generateCorrect() {
        return rnd.nextInt(100);
    }

    private void showGameDescription() {
        System.out.println("??????????????????????????????");
        System.out.printf("?????????????????????%d???????????????%n", MAX_TRIALS);
    }

    private boolean isPlayersAnswerUnderCorrect(int correct, int playersAnswer) {
        return correct > playersAnswer;
    }

    private boolean isPlayersAnswerEqualsToCorrect(int correct, int playersAnswer) {
        return playersAnswer == correct;
    }

    private boolean isChallengesUnderMaxTrials(int MAX_TRIALS, int numOfTrials) {
        return numOfTrials <= MAX_TRIALS;
    }

    private void showNumOfTrials(int numOfTrials) {
        System.out.printf("%d??????:", numOfTrials);
    }

    private void showMessageForLarger() {
        System.out.println("??????????????????????????????");
    }

    private void showMessageForSmaller() {
        System.out.println("??????????????????????????????");
    }

    private int increaseNumOfTrials(int numOfTrials) {
        numOfTrials++;
        return numOfTrials;
    }

    private void showMessageForCorrect(int numOfTrials) {
        System.out.printf("????????????%d??????????????????????????????!", numOfTrials);
    }

    private void showMessageForFail(int correct) {
        System.out.printf("??????????????????%d??????", correct);
    }

}
