package jp.freestyle.day.twentyfive;

public class Amount {
    public double price;
    public final double TAX_RATE = 1.08;

    public Amount(double price) {
        this.price = price;
    }

    public Amount add(double price) {
        this.price += price;
        return new Amount(this.price);
    }

    public Amount addTax() {
        return new Amount(price * TAX_RATE);
    }

    public void showValue() {
        System.out.printf("金額は %.0f 円 です。%n", this.price);
    }

    public double multiplyPriceAndQuantity(int quantity) {
        return this.price * quantity;
    }
}