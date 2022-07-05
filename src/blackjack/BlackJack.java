package blackjack;

import java.util.Scanner;
import java.util.Random;

public class BlackJack {
    private final int[] SCORES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public void playGame() {
        int[] dealerCards = { rand.nextInt(12), rand.nextInt(12) };
        int[] playerCards = { rand.nextInt(12), rand.nextInt(12) };

        int dealerHand = 0;
        int playerHand = 0;

        for (int i = 0; i < dealerCards.length; i++) {
            if (i == 0 && dealerCards[i] == 1) {
                if (dealerCards[i + 1] <= 10)
                    dealerHand = dealerHand + 11;
                dealerHand = dealerHand + SCORES[dealerCards[i]];
            }

            if (i > 0 && dealerCards[i] == 1) {
                if (dealerHand <= 10)
                    dealerHand = dealerHand + 11;
                dealerHand = dealerHand + SCORES[dealerCards[i]];
            }

            dealerHand = dealerHand + SCORES[dealerCards[i]];
        }

        for (int i = 0; i < playerCards.length; i++) {
            if (i == 0 && playerCards[i] == 1) {
                if (playerCards[i + 1] <= 10)
                    playerHand = playerHand + 11;
                playerHand = playerHand + SCORES[playerCards[i]];
            }

            if (i > 0 && playerCards[i] == 1) {
                if (playerHand <= 10)
                    playerHand = playerHand + 11;
                playerHand = playerHand + SCORES[playerCards[i]];
            }

            playerHand = playerHand + SCORES[playerCards[i]];
        }

        System.out.println("ディーラーの合計は" + dealerHand + "です。");
        System.out.println("現在のの合計は" + playerHand + "です。");

        if (dealerHand > 21) {
            System.out.println("Dealer burst! You win!");
            return;
        }

        while (playerHand < 21) {
            System.out.println("もう一枚カードを引きますか？(Y/N):");
            String playerDecision = scanner.next();
            if (playerDecision.equals("Y")) {
                int newCardIndex = rand.nextInt(13);
                int newCardScore;
                if (newCardIndex == 1)
                    newCardScore = 1;
                newCardScore = SCORES[newCardIndex];
                System.out.println("あなたに" + newCardScore + "が配られました");
                playerHand = playerHand + newCardScore;
                System.out.println("現在の合計は" + playerHand + "です");

                continue;
            }

            break;
        }

        if (playerHand > 21) {
            System.out.println("Burst! You lose!");
            return;
        }

        while (dealerHand < 17) {
            int newCardIndex = rand.nextInt(13);
            int newCardScore;
            if (newCardIndex == 1)
                newCardScore = 1;
            newCardScore = SCORES[newCardIndex];
            System.out.println("ディーラーに" + newCardScore + "が配られました");
            dealerHand = dealerHand + newCardScore;
            System.out.println("ディーラーの合計は" + dealerHand + "です");
        }

        if (dealerHand > 21) {
            System.out.println("Dealer burst! You win!");
            return;
        }

        if (playerHand > dealerHand) {
            System.out.println("You win!");
            return;
        }

        System.out.println("You lose!");
        return;
    }
}
