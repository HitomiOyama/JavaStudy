package rock.scissors.paper;

import java.util.Scanner;
import java.util.Random;

public class RockScissorsPaper {
    private final String[] HAND = { "グー", "チョキ", "パー" };
    private int computerHandIndex;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void playGame() {

        this.computerHandIndex = rnd.nextInt(2);

        System.out.println("selected hand is " + HAND[this.computerHandIndex]);

        System.out.println("じゃんけん勝負")
        System.out.println("グーチョキパーを数字で入力してね");
        System.out.printf("0:グー%n1:チョキ%n2:パー");

        System.out.print("最初はぐー、じゃんけん: ");


    }

    public void battle() {
        boolean isSettled = false;

        while (!isSettled) {
            try {
                String inputtedChar = scanner.next();
                int selectedHandIndex = Integer.parseInt(inputtedChar);

            } catch (NumberFormatException e) {
                System.out.print("数字で入力してください: ");
            }
        }
    }
}
