import java.util.Comparator;

public class Sorter {

    public void bubbleSort(int[] arr){
        int temp;
        int n = arr.length;
        int newn;
        boolean swapped = true;

        while(n != 0){
            newn = 0;
            for (int j = 0; j < n - 1; j++){
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    newn = j;
                }
            }
            n = newn;
        }
    }
    public void selectionSort(int[] arr){
        int minIndex, temp;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++){
            minIndex = i;
            for (int j = i + 1; j < n; j++)
                if(arr[j] < arr[minIndex])
                    minIndex = j;

            if(minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
         }
    }
    public void insertionSort(int[] arr){
        int temp;

        for(int i = 1; i < arr.length; i++){
            temp = arr[i];
            for (int j = i; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else break;
            }
        }
    }
    public void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(l+r)/2];
        int temp;

        while (l <= r){
            while(arr[l] < pivot && l <= right) l++;
            while (arr[r] > pivot && r >= left) r--;
            if(l <= r){
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                l++; r--;
            }
        }
        if(r > left) quickSort(arr, left, r);
        if(l < right) quickSort(arr, l, right);
    }
    public <K> void quickSortK(K[] S, Comparator<K> comp, int a, int b){
        if(a >= b) return; // subarray is trivially sorted
        int left = a;
        int right = b-1;
        K pivot = S[b];
        K temp;
        while(left <= right){
            while (comp.compare(S[left], pivot) < 0 && left <= right) left++;
            while (comp.compare(S[right], pivot) > 0 && right >= left) right--;
            if(left <= right){
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++; right--;
            }
        }
        temp = S[left];
        S[left] = S[b];
        S[right] = temp;
        quickSortK(S, comp, a, left -1);
        quickSortK(S, comp, left + 1, b);
    }
}
