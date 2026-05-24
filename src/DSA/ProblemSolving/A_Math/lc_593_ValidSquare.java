package DSA.ProblemSolving.A_Math;

import java.util.Arrays;

public class lc_593_ValidSquare {
    public static void main(String[] args) {
        int[] p1= {0,0};
        int[] p2= {0,0};
        int[] p3= {0,0};
        int[] p4= {0,0};
        System.out.println(validSquare(p1,p2,p3,p4));
    }
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double[] dists = new double[6];
        // We skipped the square root to avoid unnecessary extra calculations
        // We are calculating ALL possible distances in a square : side1, side2, side3, side4, diagoal1, diagonal2
        // But we dont know which is which YET
        double distp1p2 = ((p1[0] - p2[0]) * (p1[0] - p2[0])) + ((p1[1] - p2[1]) * (p1[1] - p2[1]));
        dists[0] = distp1p2;
        double dia1p1p3 = ((p1[0] - p3[0]) * (p1[0] - p3[0])) + ((p1[1] - p3[1]) * (p1[1] - p3[1]));
        dists[1] = dia1p1p3;
        double dia2p2p4 = ((p2[0] - p4[0]) * (p2[0] - p4[0])) + ((p2[1] - p4[1]) * (p2[1] - p4[1]));
        dists[2] = dia2p2p4;
        double distp3p4 = ((p3[0] - p4[0]) * (p3[0] - p4[0])) + ((p3[1] - p4[1]) * (p3[1] - p4[1]));
        dists[3] = distp3p4;
        double distp2p3 = ((p2[0] - p3[0]) * (p2[0] - p3[0])) + ((p2[1] - p3[1]) * (p2[1] - p3[1]));
        dists[4] = distp2p3;
        double distp4p1 = ((p4[0] - p1[0]) * (p4[0] - p1[0])) + ((p4[1] - p1[1]) * (p4[1] - p1[1]));
        dists[5] = distp4p1;

        // We stored all distances in an array and SORTED THEM
        // So FIRST FOUR WOULD BE SIDES AND LAST 2 WOULD BE DIAGONALS (VVIMP)
        Arrays.sort(dists);
        System.out.println(Arrays.toString(dists));

        // 3 CONDITIONS TO CHECK :
        // 1. Side length HAS to be GREATER THAN 0
        // 2. first 4 entries (SIDES of the Square) HAVE to be EQUAL
        // 3. Last 2 entries, (DIAGONALS of the Square) HAVE to be EQUAL
        // 4. SUM of 2 sides of squares = Diagonal (PYTHAGORAS)
        if ( dists[0]>0 && dists[0] == dists[1] && dists[1] == dists[2] && dists[2] == dists[3]) {
            return ( (dists[4] == dists[5]) && ( dists[0] + dists[2] ) == dists[5]  );
        }
        return false;
    }
}