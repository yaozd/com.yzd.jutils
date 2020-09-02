package com.yzd.jutils.algorithmExt.TP95;

/**
 * @author Charies Gavin
 * https:github.com/guobinhit
 * @date 10/29/19,6:57 PM
 * @description water line calculator test class
 * - [https://github.com/guobinhit/awesome-tools](https://github.com/guobinhit/awesome-tools)
 */
public class MonitorWaterLineCalculatorTest {
    public static void main(String[] args) {
        MonitorWaterLineCalculator calculator = new MonitorWaterLineCalculator(95);

        for (int i = 0; i < 1000000000; i++) {
            double temp = randGen(10, 1000);
            //System.out.println(temp);
            calculator.calculate(temp);
        }

        System.out.println(calculator.getResult());
    }

    private static double randGen(int upper, int lower) {
        return Math.floor(Math.random() * (upper - lower)) + lower;
    }
}
