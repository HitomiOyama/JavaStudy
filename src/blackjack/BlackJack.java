package blackjack;

import java.util.Scanner;
import java.util.Random;

public class BlackJack {
    private final int[] SCORES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public void playGame() {
        final int MAX_HAND = 21;

        int[] dealerCards = pickCards();
        int[] playerCards = pickCards();

        int dealerHand = cariculateHand(dealerCards);
        int playerHand = cariculateHand(playerCards);

        showEachHands(dealerHand, playerHand);

        if (isDealerBurst(dealerHand, MAX_HAND))
            return;
        if (isPlayerBurst(playerHand, MAX_HAND))
            return;

        playerHand = getPlayerFinalHand(playerHand, MAX_HAND);

        if (isPlayerBurst(playerHand, MAX_HAND))
            return;

        dealerHand = getDealerFinalHand(dealerHand);

        if (isDealerBurst(dealerHand, MAX_HAND))
            return;

        if (isPlayerWin(playerHand, dealerHand))
            return;

        showResultMessage("You lose!");
        return;
    }

    private int[] pickCards() {
        int[] cards = { rand.nextInt(12), rand.nextInt(12) };
        return cards;
    }

    private int cariculateHand(int[] cards) {
        int hand = 0;

        for (int i = 0; i < cards.length; i++) {
            if (i == 0 && cards[i] == 1) {
                if (cards[i + 1] <= 10)
                    hand = hand + 11;
                hand = hand + SCORES[cards[i]];
            }

            if (i > 0 && cards[i] == 1) {
                if (hand <= 10)
                    hand = hand + 11;
                hand = hand + SCORES[cards[i]];
            }

            hand = hand + SCORES[cards[i]];
        }

        return hand;
    }

    private int getPlayerFinalHand(int playerHand, int maxHand) {
        while (!playerBurst(playerHand, maxHand)) {
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

            if (playerDecision.equals("N"))
                break;

            continue;
        }
        return playerHand;
    }

    private int getDealerFinalHand(int dealerHand) {
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
        return dealerHand;
    }

    private boolean playerBurst(int playerHand, int maxHand) {
        return playerHand > maxHand;
    }

    private void showEachHands(int dealerHand, int playerHand) {
        System.out.println("ディーラーの合計は" + dealerHand + "です。");
        System.out.println("現在の合計は" + playerHand + "です。");
    }

    private void showResultMessage(String message) {
        System.out.println(message);
    }

    private boolean isDealerBurst(int dealerHand, int maxHand) {
        if (dealerHand > maxHand) {
            showResultMessage("Dealer burst! you win!");
        }
        return dealerHand > maxHand;
    }

    private boolean isPlayerBurst(int playerHand, int maxHand) {
        if (playerHand > maxHand) {
            showResultMessage("Burst! you lose!");
        }
        return playerHand > maxHand;
    }

    private boolean isPlayerWin(int playerHand, int dealerHand) {
        if (playerHand > dealerHand) {
            showResultMessage("you win!");
        }
        return playerHand > dealerHand;
    }
}
