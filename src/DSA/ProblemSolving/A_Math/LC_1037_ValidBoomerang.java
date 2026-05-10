package DSA.ProblemSolving.A_Math;

public class LC_1037_ValidBoomerang {

    public static void main(String[] args) {
        int[][] points = {{0,1},
                {2,0},
                {2,1}};

        System.out.println(isBoomerang(points));
    }
// Boomerang are the points which are NON - COLLINEAR
    public static boolean isBoomerang(int[][] points) {
        float slAB = 0.0f;
        float slBC = 0.0f;
        float slAC = 0.0f;
        System.out.println("  0 1 \n" + "0 " + points[0][0] + " " + points[0][1] + " \n"
                + "1 " + points[1][0] + " " + points[1][1] + " \n"
                + "2 " + points[2][0] + " " + points[2][1] + " \n");

        if (points[0][0] == points[1][0] && points[0][1] == points[1][1] ||
                points[0][0] == points[2][0] && points[0][1] == points[2][1] ||
                points[2][0] == points[1][0] && points[2][1] == points[1][1]
        )
            return false;
        else if (points[0][0] == points[1][0] && points[0][0] == points[2][0]) //&& points[1][0]==points[2][0]
            return false;
        else if (points[0][1] == points[1][1] && points[0][1] == points[2][1]) //&& points[1][1]==points[2][1]
            return false;
        else { // Checking the slopes, they should not be equal
            if (points[1][0] - points[0][0] != 0) {
                slAB = (float) (points[1][1] - points[0][1]) / (points[1][0] - points[0][0]); //2
                System.out.println(slAB);
            }
            if (points[2][0] - points[1][0] != 0) {
                slBC = (float) (points[2][1] - points[1][1]) / (points[2][0] - points[1][0]); //-1
                System.out.println(slBC);
            }
            if (points[0][0] - points[2][0] != 0) {
                slAC = (float) (points[0][1] - points[2][1]) / (points[0][0] - points[2][0]); // 0.5
                System.out.println(slAC);
                 }
            // if the slopes are equal, they are NOT Boomerang, aka they are Collinear, so return FALSE when slopes are equal
                if (slAB == slBC && slBC == slAC && slAB == slAC)
                    return false;
                else
                    return true;
            }
    }
}
