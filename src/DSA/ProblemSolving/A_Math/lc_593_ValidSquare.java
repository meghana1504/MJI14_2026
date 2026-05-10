package DSA.ProblemSolving.Math;

import java.lang.reflect.Array;
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

        Arrays.sort(dists);
        System.out.println(Arrays.toString(dists));
        if ( dists[0]>0 && dists[0] == dists[1] && dists[1] == dists[2] && dists[2] == dists[3]) {
            return ( (dists[4] == dists[5]) && ( dists[0] + dists[2] ) == dists[5]  );
        }
        return false;
    }
//        double slopep1p2 = ((double) (p1[0] - p2[0]) /(p1[1]-p2[1]) );
//        double slopep3p4 = ((double) (p3[0] - p4[0]) /(p3[1]-p4[1]) );
//        double slopep1p3 = ((double) (p1[0] - p3[0]) /(p1[1]-p3[1]) );
//        double slopep2p4 = ((double) (p2[0] - p4[0]) /(p2[1]-p4[1]) );
//        double slopep1p4 = ((double) (p1[0] - p4[0]) /(p1[1]-p4[1]) );
//        double slopep2p3 = ((double) (p2[0] - p3[0]) /(p2[1]-p3[1]) );
//        boolean flag=false;
//        boolean part1=false;
//        if(slopep3p4 == slopep1p2 && slopep1p3*slopep2p4==-1 ){
//            if((distp3p4 == distp1p2) && () && ()){
//
//            }
//        }
//        else if(slopep3p4 == slopep1p2 && slopep1p4*slopep2p3==-1){
//
//        }
//
//
//
////        double distp1p2 = Math.sqrt( ( (p1[0]-p2[0]) * (p1[0]-p2[0]) ) + ( (p1[1]-p2[1]) * (p1[1]-p2[1]) ) );
////
//
//
//
//
//        if(dia1p1p3 == dia2p2p4){
//            if((distp1p2 == distp2p3) && (distp2p3 == distp3p4) && (distp3p4 == distp1p2) )
//                return true;
//            else
//                return false;
//        }
//        else
//            return false;
//
//    }
}
