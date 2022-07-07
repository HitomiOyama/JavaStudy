package blackjack;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class BlackJack {
    private final int[] SCORES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
    final int MAX_HAND = 21;
    final int SPECIAL_CARD = 1;
    final int REPLACABLE_SCORE = 11;
    final int NUM_OF_INITIAL_CARDS = 2;
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public void playGame() {

        ArrayList<Integer> dealerCards = new ArrayList<>();
        ArrayList<Integer> playerCards = new ArrayList<>();

        pickCards(NUM_OF_INITIAL_CARDS, dealerCards, playerCards);

        int dealerHand = cariculateHand(dealerCards);
        int playerHand = cariculateHand(playerCards);

        showEachHands(dealerHand, playerHand);

        if (isDealerBurst(dealerHand, MAX_HAND)) {
            showResultMessage("Dealer burst! you win!");
            return;
        }

        if (isPlayerBurst(playerHand, MAX_HAND)) {
            showResultMessage("Burst! you lose!");
            return;
        }

        playerHand = getPlayerFinalHand(playerHand, MAX_HAND);

        if (isPlayerBurst(playerHand, MAX_HAND)) {
            showResultMessage("Burst! you lose!");
            return;
        }

        dealerHand = getDealerFinalHand(dealerHand);

        if (isDealerBurst(dealerHand, MAX_HAND)) {
            showResultMessage("Dealer burst! you win!");
            return;
        }

        if (isPlayerWin(playerHand, dealerHand)) {
            showResultMessage("you win!");
            return;
        }

        showResultMessage("You lose!");
        return;
    }

    private void pickCards(int numOfCards, ArrayList<Integer> dealerCards, ArrayList<Integer> playerCards) {
        for (int i = 0; i < numOfCards; i++) {
            pickCard("ディーラー", dealerCards);
            pickCard("あなた", playerCards);
        }
    }

    private void pickCard(String who, ArrayList<Integer> cardList) {
        int card = rand.nextInt(12) + 1;
        System.out.printf("%sに%dが配られました。%n", who, card);
        cardList.add(card);
    }

    private int cariculateHand(ArrayList<Integer> cards) {
        int hand = 0;
        int numberOfSpecialCard = 0;

        for (int card : cards) {
            if (isCardSpecial(SPECIAL_CARD, card))
                numberOfSpecialCard++;
            hand = hand + SCORES[card - 1];
        }

        int numberOfCardReplacable = MAX_HAND / REPLACABLE_SCORE;

        for (int i = 1; i <= numberOfSpecialCard; i++) {
            if (isCardReplacable(numberOfCardReplacable, i)) {
                replaceCard(hand, SPECIAL_CARD, REPLACABLE_SCORE);
            }
        }

        return hand;
    }

    private boolean isCardReplacable(int numberOfCardReplacable, int i) {
        return i <= numberOfCardReplacable;
    }

    private int replaceCard(int hand, int SPECIAL_CARD, int REPLACABLE_SCORE) {
        hand = hand - SPECIAL_CARD + REPLACABLE_SCORE;
        return hand;
    }

    private boolean isCardSpecial(int SPECIAL_CARD, int card) {
        return card == SPECIAL_CARD;
    }

    private void showEachHands(int dealerHand, int playerHand) {
        System.out.println("ディーラーの合計は" + dealerHand + "です。");
        System.out.println("現在の合計は" + playerHand + "です。");
    }

    private boolean isDealerBurst(int dealerHand, int maxHand) {
        return dealerHand > maxHand;
    }

    private boolean isPlayerBurst(int playerHand, int maxHand) {
        return playerHand > maxHand;
    }

    private void showResultMessage(String message) {
        System.out.println(message);
    }

    private int getPlayerFinalHand(int playerHand, int maxHand) {
        while (!isPlayerBurst(playerHand, maxHand)) {
            System.out.println("もう一枚カードを引きますか？(Y/N):");
            String playerDecision = scanner.next();

            if (playerDecision.equals("Y")) {
                int newCardIndex = rand.nextInt(13);
                int newCardScore;

                newCardScore = SCORES[newCardIndex];
                System.out.println("あなたに" + newCardScore + "が配られました");

                if (isNewCardSpecial(newCardScore) && isCardReplacable(playerHand)) {
                    playerHand = playerHand + REPLACABLE_SCORE;
                } else {
                    playerHand = playerHand + newCardScore;
                }
                ;

                System.out.println("現在の合計は" + playerHand + "です");

                continue;
            }

            if (playerDecision.equals("N"))
                break;

            continue;
        }
        return playerHand;
    }

    private boolean isCardReplacable(int playerHand) {
        return playerHand < (MAX_HAND - REPLACABLE_SCORE);
    }

    private boolean isNewCardSpecial(int newCardScore) {
        return newCardScore == SPECIAL_CARD;
    }

    private int getDealerFinalHand(int dealerHand) {
        int dealerMaxHandToPickAnother = 17;
        while (isDealerAbleToPickAnotherCard(dealerHand, dealerMaxHandToPickAnother)) {
            int newCardIndex = rand.nextInt(13);
            int newCardScore;

            newCardScore = SCORES[newCardIndex];
            System.out.println("ディーラーに" + newCardScore + "が配られました");

            if (isNewCardSpecial(newCardScore) && isCardReplacable(dealerHand)) {
                dealerHand = dealerHand + REPLACABLE_SCORE;
            } else {
                dealerHand = dealerHand + newCardScore;
            }
            dealerHand = dealerHand + newCardScore;
            System.out.println("ディーラーの合計は" + dealerHand + "です");

        }

        return dealerHand;
    }

    private boolean isDealerAbleToPickAnotherCard(int dealerHand, int dealerMaxHandToPickAnother) {
        return dealerHand < dealerMaxHandToPickAnother;
    }

    private boolean isPlayerWin(int playerHand, int dealerHand) {
        return playerHand > dealerHand;
    }
}
