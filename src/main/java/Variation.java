import java.util.ArrayList;

public class Variation {

    public static boolean nextSet(int[] a, int n, int m) {
        int j = m - 1;
        while(j >= 0 && a[j] == n - 1) {
            j--;
        }

        if(j < 0) {
            return false;
        }

        if(a[j] >= n - 1) {
            j--;
        }

        a[j]++;
        if(j == m - 1) {
            return true;
        }

        for(int k = j + 1; k < m; k++) {
            a[k] = 0;
        }
        return true;
    }
}

