package payment.organizer;

public class PaymentCaliculator {
    private int totalPrice;
    private int numberOfMale;
    private int numberOfFemale;

    public PaymentCaliculator(int totalPrice, int numberOfMale, int numberOfFemale) {
        this.totalPrice = totalPrice;
        this.numberOfMale = numberOfMale;
        this.numberOfFemale = numberOfFemale;
    }

    public void calcEachPaymentEqually() {
        int numberOfParticipant = numberOfMale + numberOfFemale;
        int eachPayment = totalPrice / numberOfParticipant;

        System.out.printf("男性: %d円%n女性: %d円%n", eachPayment, eachPayment);
    }

    public void calcEachPaymentWidhGenderDifference() {
        int differenceBetweenMaleAndFemalePayment = 1000;

        int differenceBetweenMaleAndFemaleTotalPayment = differenceBetweenMaleAndFemalePayment * numberOfMale;

        int numberOfParticipant = numberOfMale + numberOfFemale;

        int femalePaymentEach = (totalPrice - differenceBetweenMaleAndFemaleTotalPayment) / numberOfParticipant;

        int malePaymentEach = femalePaymentEach + differenceBetweenMaleAndFemalePayment;

        System.out.printf("男性: %d円%n女性: %d円%n", malePaymentEach, femalePaymentEach);
    }
}
