package guess.card.game.challenge;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class GuessCardGameChallenge {
    private final String[] SUIT = new String[] { "ハート", "ダイヤ", "スペード", "クローバー" };
    private final String[] NUMBER = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    private int selectedSuitIndex;
    private int selectedNumberIndex;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void playGame() {

        this.selectedSuitIndex = rnd.nextInt(3);
        this.selectedNumberIndex = rnd.nextInt(12);

        System.out.println("selected suit is" + SUIT[this.selectedSuitIndex]);
        System.out.println("selected number is" + NUMBER[this.selectedNumberIndex]);

        System.out.println("トランプを選んだよ");
        System.out.println("トランプの図柄を当ててね。2回まで挑戦できるよ。");
        System.out.printf("0:ハート%n1:ダイヤ%n2:スペード%n3:クローバー%n");

        System.out.print("どれだと思う？: ");

        checkSuit();

    }

    private void checkSuit() {
        boolean isSuitCorrect = false;
        int numOfTrial = 0;
        while (!isSuitCorrect && numOfTrial <= 2) {
            try {
                String inputtedChar = scanner.next();
                int guessedSuitIndex = Integer.parseInt(inputtedChar);

                if (!isValidSuitIndex(guessedSuitIndex)) {
                    System.out.print("0~3の数字を入力してください: ");
                    continue;
                }
                if (guessedSuitIndex != this.selectedSuitIndex) {
                    numOfTrial++;

                    if (numOfTrial == 2)
                        break;
                    System.out.printf("残念！%sじゃないよ: ", SUIT[guessedSuitIndex]);

                    continue;
                }

                System.out.printf("正解！図柄は%sだよ%n", SUIT[this.selectedSuitIndex]);
                isSuitCorrect = true;

                System.out.println("次は数字を当ててね。４回まで挑戦できるよ。");
                System.out.print("どれだと思う？: ");

                checkNumber();
                return;

            } catch (NumberFormatException e) {
                System.out.print("数字で入力してください: ");
            }

        }

        System.out.printf("残念！答えは%sの%sでした", SUIT[this.selectedSuitIndex], NUMBER[this.selectedNumberIndex]);
    }

    private void checkNumber() {
        boolean isNumberCorrect = false;
        int numOfTrial = 0;
        while (!isNumberCorrect && numOfTrial <= 4) {

            String guessedNumber = scanner.next();
            if (!isValidNumberIndex(guessedNumber)) {
                System.out.print("2～10の数字またはA,J,Q,Kのいずれかを入力してください:");
                continue;
            }
            if (!guessedNumber.equals(NUMBER[this.selectedNumberIndex])) {
                numOfTrial++;
                if (numOfTrial == 4)
                    break;
                System.out.printf("残念！%sじゃないよ:", guessedNumber, NUMBER[this.selectedNumberIndex]);

                continue;
            }

            System.out.printf("正解！%sの%sだよ%n", SUIT[this.selectedSuitIndex], NUMBER[this.selectedNumberIndex]);
            isNumberCorrect = true;
            return;

        }
        scanner.close();

        System.out.printf("残念！答えは%sの%sでした", SUIT[this.selectedSuitIndex], NUMBER[this.selectedNumberIndex]);

    }

    public boolean isValidSuitIndex(int i) {
        if (i >= 0 && i <= 3)
            return true;
        return false;
    }

    public boolean isValidNumberIndex(String i) {
        ArrayList<String> number = new ArrayList<String>(Arrays.asList(NUMBER));
        if (number.contains(i))
            return true;
        return false;
    }

}
