package rock.scissors.paper;

import java.util.Scanner;
import java.util.Random;

public class RockScissorsPaper {
    private final String[] HAND = { "グー", "チョキ", "パー" };
    private int compHand;
    private int playerHand;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);
    private final String[][] JUDGE = { { "あいこ", "負け", "勝ち" },
            { "勝ち", "あいこ", "負け" },
            { "負け", "勝ち", "あいこ" } };
    private String result;

    public void playGame() {

        this.compHand = rnd.nextInt(2);

        System.out.println("selected hand is " + HAND[this.compHand]);

        System.out.println("じゃんけん勝負");
        System.out.println("グーチョキパーを数字で入力してね");
        System.out.printf("0:グー%n1:チョキ%n2:パー%n");

        System.out.print("最初はぐー、じゃんけん: ");

        battle();

        showResult();

    }

    private void battle() {
        boolean isSettled = false;

        while (!isSettled) {
            try {
                String inputtedChar = scanner.next();
                playerHand = Integer.parseInt(inputtedChar);

                this.result = JUDGE[playerHand][compHand];

                if (!result.equals("あいこ"))
                    break;

                System.out.printf("%s(COM)と%s(YOU)であいこだよ。%n", HAND[this.compHand], HAND[this.playerHand]);
                System.out.print("あいこで: ");

            } catch (NumberFormatException e) {
                System.out.print("数字で入力してください: ");
            }
        }
    }

    private void showResult() {
        System.out.printf("%s(COM)と%s(YOU)であなたの%s", HAND[this.compHand], HAND[this.playerHand],
                JUDGE[this.compHand][this.playerHand]);
    }
}
