/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.*;

/**
 *
 * @author hoang
 */
public class Test {

    /**
     * TRANSFORM ARRAY BASE ON OFFSET
     * @param arr array
     * @param offset if offset > 0, transform right. 
     *               if offset < 0, transform left.
     * @return TRANSFORMED array
     */
    public static int[] transform(int[] arr, int offset) {
        int[] result = new int[arr.length]; // RESULT
        for (int i = 0; i < arr.length; i++) {
            result[i] = 0;  // INITIALIZE all element of array 
        }
        // NO TRANSFORM
        if(offset == 0) {
            return arr;
        }
        if (offset < 0) {
            for (int i = 0; i < arr.length; i++) {
                result[i] = arr[(-offset + i) % arr.length]; // TRANSFORM LEFT
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                result[i] = arr[(offset + i - 1) % arr.length]; // TRANSFORM RIGHT
            }
        }
        return result;
    }

    /**
     * SORT ARRAY USING BUBBLE SORT ALGORITHM
     * 
     * @param arr array
     *  RETURN ARRAY SORTED BY ASCENDING
     */
    static void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
    
    /**
     * GET MAX VALUE LENGTH OF SUBSETS SATISFY ABSOLUTE VALUE OF EACH ELEMENT LESS THAN 1
     * 
     * @param arr array
     * @return MAX VALUE length of subsets
     */
    static int GetMax(int[] arr) {
        ArrayList<Integer> newList = new ArrayList<>();//temporary list contains subsets
        ArrayList<Integer> lengthOfList = new ArrayList<>();//list contains length of subsets
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            newList.add(arr[i]); 
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) <= 1) {
                    newList.add(arr[j]);
                }
            }
            lengthOfList.add(newList.size());
            newList.clear();
        }
        return Max(lengthOfList);
    }

    /**
     * GET MAX VALUE OF A ARRAY
     * @param arr array
     * @return MAXIMUM of array
     */
    static int Max(ArrayList<Integer> arr) {
        int max = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if (max < arr.get(i)) {
                max = arr.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,4,4,2,2,5,5,5,3};
        int[] res = transform(arr, 0);
        for (int re : res) {
            System.out.println(re);
        }
    }

}
