package com.sber.lesson1;

public class ArrayMethod {

    //сортировка пузырьком
    public static void sort(int[] arr){
        for (int j = 0; j < arr.length ; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if(arr[i] > arr[i + 1]){
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }

    //бинарный поиск ( вернет индекс элемента или -1 )
    public static int binSearch(int[] arr, int el){
        int left = -1;
        int right = arr.length;
        int result = -1;

        while ((right - left) > 1){
            int mid = (left + right) / 2;

            if(el == arr[mid]){
                result = mid;
                break;
            }

            //System.out.println("*** " + arr[mid]);

            if(arr[mid] > el){
                right = mid;
            } else {
                left = mid;
            }
        }

        return result;
    }

}
