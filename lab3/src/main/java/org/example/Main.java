package org.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Программа тестирует производительность операций add,get,remove
 * в LinkedList и ArrayList, и выводит результаты в консоль
 */
public class Main {
    public static void main(String[] args) {
        int[] iterations = {1000, 10000, 100000};

        String format = "%-20s %-20s %-20s %-20s\n";
        DecimalFormat df = new DecimalFormat("###,###");
        System.out.printf(format, "Итерации", "Операция", "Время ArrayList(ns)", "Время LinkedList(ns)");
        for (int iterCount : iterations) {
            long[] arrayList = ListTest.runTest(new ArrayList<Integer>(), iterCount);
            long[] linkedList = ListTest.runTest(new LinkedList<Integer>(), iterCount);

            System.out.printf(format, df.format(iterCount), "add", df.format(arrayList[0]), df.format(linkedList[0]));
            System.out.printf(format, df.format(iterCount), "get", df.format(arrayList[1]), df.format(linkedList[1]));
            System.out.printf(format, df.format(iterCount), "remove", df.format(arrayList[2]), df.format(linkedList[2]));
        }
    }
}