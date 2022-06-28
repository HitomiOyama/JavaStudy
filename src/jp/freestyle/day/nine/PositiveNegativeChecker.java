package jp.freestyle.day.nine;

import java.util.Scanner;

public class PositiveNegativeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("正数または負数を入力してください : ");
        int num = scanner.nextInt();

        if (num > 0) {
            System.out.println("正数です");
        } else if (num < 0) {
            System.out.println("負数です");
        } else {
            System.out.println("入力値は0です");
        }

        scanner.close();
    }
}
