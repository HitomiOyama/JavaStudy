package guess.number.game.challenge.logic;

import java.util.Scanner;
import java.util.Random;

public class GuessNumberGameChallengeLogic {
    private int numOfTry = 1;
    private int answer;
    private boolean isCorrect = false;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void startGame() {

        this.answer = rnd.nextInt(1000);

        System.out.println("数字を当ててみてね。");
        System.out.println("答えられるのは10回までだよ");

        while (numOfTry <= 10) {
            System.out.printf("%d回目:", numOfTry);
            int i = scanner.nextInt();

            if (i == this.answer) {
                System.out.printf("すごい！%d回目で正解しちゃった!", this.numOfTry);
                this.isCorrect = true;
                break;
            }

            if ((this.answer - i) > 100) {
                System.out.println("もっとずっと大きい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

            if ((this.answer - i) > 0) {
                System.out.println("もっと大きい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

            if ((this.answer - i) < -100) {
                System.out.println("もっとずっと小さい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

            if ((this.answer - i) < 0) {
                System.out.println("もっと小さい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

        }

        if (isCorrect)
            return;

        System.out.printf("残念！正解は%dだよ", this.answer);
        scanner.close();
    }

}
