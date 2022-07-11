package guess.card.game;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class GuessCardGame {
    private final String[] SUITS = new String[] { "ハート", "ダイヤ", "スペード", "クローバー" };
    private final String[] NUMBERS = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    private int selectedSuitIndex;
    private int selectedNumberIndex;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void play() {

        this.selectedSuitIndex = compSelectSuit();
        this.selectedNumberIndex = compSelectNumber();

        showCheatMessage(); // 動作確認用

        showGuidanceForGuessSuit();

        checkSuit();

        showGuidanceForGuessNumber();

        checkNumber();

    }

    private int compSelectSuit() {
        int maxSuitIndex = SUITS.length - 1;
        return rnd.nextInt(maxSuitIndex);
    }

    private int compSelectNumber() {
        int maxNumberIndex = NUMBERS.length - 1;
        return rnd.nextInt(maxNumberIndex);
    }

    private void showCheatMessage() {
        System.out.println("selected suit is" + SUITS[selectedSuitIndex]);
        System.out.println("selected number is" + NUMBERS[selectedNumberIndex]);
    }

    private void showGuidanceForGuessSuit() {
        System.out.println("トランプを選んだよ");
        System.out.println("トランプの図柄を当ててね");
        System.out.printf("0:ハート%n1:ダイヤ%n2:スペード%n3:クローバー%n");
        System.out.print("どれだと思う？: ");
    }

    private void showGuidanceForGuessNumber() {
        System.out.println("次は数字を当ててね");
        System.out.print("どれだと思う？: ");
    }

    private void checkSuit() {
        boolean isSuitCorrect = false;
        while (!isSuitCorrect) {
            try {
                String inputtedChar = scanner.next();
                int guessedSuitIndex = Integer.parseInt(inputtedChar);

                if (!isValidSuitIndex(guessedSuitIndex)) {
                    showErrorMessageInvalidNumber();
                    continue;
                }
                if (guessedSuitIndex != this.selectedSuitIndex) {
                    showFailMessage(SUITS[guessedSuitIndex]);
                    continue;
                }

                showCorrectSuitMessage();
                isSuitCorrect = true;

            } catch (NumberFormatException e) {
                showErrorMessageNotNumber();
                scanner.next();
            }
        }
    }

    private void showCorrectSuitMessage() {
        System.out.printf("正解！図柄は%sだよ%n", SUITS[this.selectedSuitIndex]);
    }

    private void showErrorMessageNotNumber() {
        System.out.print("数字で入力してください: ");
    }

    private void showErrorMessageInvalidNumber() {
        System.out.print("0~3の数字を入力してください: ");
    }

    private void checkNumber() {
        boolean isNumberCorrect = false;
        while (!isNumberCorrect) {

            String guessedNumber = scanner.next();
            if (!isValidNumberIndex(guessedNumber)) {
                showErrorMessageInvalidString();
                continue;
            }
            if (!guessedNumber.equals(NUMBERS[this.selectedNumberIndex])) {
                showFailMessage(guessedNumber);
                continue;
            }

            showCorrectMessage();
            isNumberCorrect = true;

        }

        scanner.close();
    }

    private void showCorrectMessage() {
        System.out.printf("正解！%sの%sだよ%n", SUITS[this.selectedSuitIndex], NUMBERS[this.selectedNumberIndex]);
    }

    private void showFailMessage(String playerInput) {
        System.out.printf("残念！%sじゃないよ:", playerInput);
    }

    private void showErrorMessageInvalidString() {
        System.out.print("2～10の数字またはA,J,Q,Kのいずれかを入力してください:");
    }

    public boolean isValidSuitIndex(int i) {
        int maxSuitIndex = SUITS.length - 1;
        boolean isWithinSuitIndexRange = i >= 0 && i <= maxSuitIndex;
        if (isWithinSuitIndexRange)
            return true;
        return false;
    }

    public boolean isValidNumberIndex(String i) {
        ArrayList<String> numberList = new ArrayList<String>(Arrays.asList(NUMBERS));
        if (numberList.contains(i))
            return true;
        return false;
    }

}
