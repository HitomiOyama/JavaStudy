package guess.card.game;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class GuessCardGame {
    private final String[] SUIT = new String[] { "ハート", "ダイヤ", "スペード", "クローバー" };
    private final String[] NUMBER = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    private int selectedSuitIndex;
    private int selectedNumberIndex;
    private boolean isSuitCorrect = false;
    private boolean isNumberCorrect = false;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void playGame() {

        this.selectedSuitIndex = rnd.nextInt(3);
        this.selectedNumberIndex = rnd.nextInt(12);

        System.out.println("selected suit is" + SUIT[selectedSuitIndex]);
        System.out.println("selected number is" + NUMBER[selectedNumberIndex]);

        System.out.println("トランプを選んだよ");
        System.out.println("トランプの図柄を当ててね");
        System.out.printf("0:ハート%n1:ダイヤ%n2:スペード%n3:クローバー%n");

        System.out.print("どれだと思う？: ");

        checkSuit();

        System.out.println("次は数字を当ててね");
        System.out.print("どれだと思う？: ");

        checkNumber();

    }

    private void checkSuit() {
        while (!this.isSuitCorrect) {
            try {
                String inputtedChar = scanner.next();
                int guessedSuitIndex = Integer.parseInt(inputtedChar);

                if (!isValidSuitIndex(guessedSuitIndex)) {
                    System.out.print("0~3の数字を入力してください: ");
                    continue;
                }
                if (guessedSuitIndex != this.selectedSuitIndex) {
                    System.out.printf("残念！%sじゃないよ: ", SUIT[guessedSuitIndex]);
                    continue;
                }

                System.out.printf("正解！図柄は%sだよ%n", SUIT[this.selectedSuitIndex]);
                this.isSuitCorrect = true;

            } catch (NumberFormatException e) {
                System.out.print("数字で入力してください: ");
                scanner.next();
            }
        }
    }

    private void checkNumber() {
        while (!this.isNumberCorrect) {

            String guessedNumber = scanner.next();
            if (!isValidNumberIndex(guessedNumber)) {
                System.out.print("2～10の数字またはA,J,Q,Kのいずれかを入力してください:");
                continue;
            }
            if (!guessedNumber.equals(NUMBER[this.selectedNumberIndex])) {
                System.out.printf("残念！%sじゃないよ:", guessedNumber, NUMBER[this.selectedNumberIndex]);
                continue;
            }

            System.out.printf("正解！%sの%sだよ%n", SUIT[this.selectedSuitIndex], NUMBER[this.selectedNumberIndex]);
            this.isNumberCorrect = true;

        }

        scanner.close();
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
