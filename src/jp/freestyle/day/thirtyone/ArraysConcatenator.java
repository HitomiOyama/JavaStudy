package jp.freestyle.day.thirtyone;

import java.util.Arrays;

public class ArraysConcatenator {
    private int pos = 0;

    public static void main(String[] args) {
        int[] firstArray = { 1, 2, 3000, 2000, 1000 };
        int[] secondArray = { 103, 102, 101 };
        // firstArray と secondArray を
        // 連結させる処理をここに書く
        int arrayLength = firstArray.length + secondArray.length;
        int[] result = new int[arrayLength];

        ArraysConcatenator arraysConcatenator = new ArraysConcatenator();

        int[] resultWithFirstArray = arraysConcatenator.concatArray(firstArray, result);
        int[] resultWithSecondArray = arraysConcatenator.concatArray(secondArray, resultWithFirstArray);

        System.out.println(Arrays.toString(resultWithSecondArray));
    }

    public int[] concatArray(int[] Array, int[] result) {
        for (int arrayItem : Array) {
            result[this.pos] = arrayItem;
            this.pos++;
        }

        return result;
    }
}