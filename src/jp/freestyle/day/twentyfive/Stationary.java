package jp.freestyle.day.twentyfive;

public class Stationary {
    private String name;
    private Amount amount;
    private int quantity;

    public Stationary(String name, Amount amount, int quantity) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;

        System.out.printf("%sが%d本あります。%n", this.name, this.quantity);
    }

    public double calcTotalAmount() {
        return this.amount.multiplyPriceAndQuantity(this.quantity);
    }
}
