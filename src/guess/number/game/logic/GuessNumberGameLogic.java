package guess.number.game.logic;

import java.util.Scanner;
import java.util.Random;

public class GuessNumberGameLogic {

    private int numOfTry = 1;
    private int answer;
    private boolean isCorrect = false;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void startGame() {

        this.answer = rnd.nextInt(100);

        System.out.println("数字を当ててみてね。");
        System.out.println("答えられるのは５回までだよ");

        while (numOfTry <= 5) {

            System.out.printf("%d回目:", numOfTry);
            int i = scanner.nextInt();

            if (i == this.answer) {
                System.out.printf("すごい！%d回目で正解しちゃった!", this.numOfTry);
                this.isCorrect = true;
                break;
            }

            if (i < this.answer) {
                System.out.println("もっと大きい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

            if (i > this.answer) {
                System.out.println("もっと小さい数字だよ");
                this.numOfTry = this.numOfTry + 1;
                continue;
            }

            scanner.close();
        }

        if (isCorrect)
            return;

        System.out.printf("残念！正解は%dだよ", this.answer);
    }

}
