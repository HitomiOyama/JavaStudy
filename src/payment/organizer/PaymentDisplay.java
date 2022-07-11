package payment.organizer;

public class PaymentDisplay {
    public static void main(String[] args) {
        PaymentCaliculator caliculator = new PaymentCaliculator(12000, 3, 3);

        caliculator.calcEachPaymentEqually();
        caliculator.calcEachPaymentWidhGenderDifference();
    }
}
