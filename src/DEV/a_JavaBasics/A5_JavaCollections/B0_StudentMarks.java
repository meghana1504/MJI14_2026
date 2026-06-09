package DEV.a_JavaBasics.A5_JavaCollections;

public class B0_StudentMarks implements Comparable<B0_StudentMarks>{
    // When a custom class implements Comparable interface, it means the class has a strategy to compare its elements and sort its own objects
    private int maths;
    private int physics;

    public B0_StudentMarks(int maths, int physics) {
        this.maths = maths;
        this.physics = physics;
    }

    @Override
    public String toString() {
        return "StudentMarks{" +
                "maths=" + maths +
                ", physics=" + physics +
                '}';
    }

    public int getMaths() {
        return maths;
    }

    public int getPhysics() {
        return physics;
    }

    @Override
    public int compareTo(B0_StudentMarks o) {
        System.out.println("B0_StudentMarks::CompareTo() is called");
        return o.getMaths()-this.getMaths(); // since we want top 3 starting from the top scorer
    }
}
