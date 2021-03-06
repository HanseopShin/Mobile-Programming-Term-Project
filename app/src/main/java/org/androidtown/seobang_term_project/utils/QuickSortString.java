package org.androidtown.seobang_term_project.utils;

/**
 * @When & @functions & @technique:
 * This class is for sorting the unordered database lines
 * for showing each steps of the things in the database, call this class and sort the arrays
 */

public class QuickSortString {
    public static void sort(String[] data, int l, int r) {
        int left = l;
        int right = r;
        //    int pivot = Integer.parseInt(data[(l + r) / 2].substring(data[(l + r) / 2].indexOf("+") + 1, data[(l + r) / 2].indexOf("&")));
        if (data[l].indexOf("+") != -1) {
            int pivot = Integer.parseInt(data[(l + r) / 2].substring(data[(l + r) / 2].indexOf("+") + 1, data[(l + r) / 2].indexOf("&")));
            do {
                while (Integer.parseInt(data[left].substring(data[left].indexOf("+") + 1, data[left].indexOf("&"))) < pivot)
                    left++;
                while (Integer.parseInt(data[right].substring(data[right].indexOf("+") + 1, data[right].indexOf("&"))) > pivot)
                    right--;
                if (left <= right) {
                    String temp = data[left];
                    data[left] = data[right];
                    data[right] = temp;
                    left++;
                    right--;
                }
            } while (left <= right);

            if (l < right) sort(data, l, right);
            if (r > left) sort(data, left, r);
        }
    }
}
