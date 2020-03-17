import javax.xml.crypto.Data;

public class CompareDataset {
    public static void main(String[] args) {
        DataSet R = new DataSet("C:\\Users\\Richi\\IdeaProjects\\AnimalTracker\\Output\\CHEN_RICHARD.txt");
        DataSet dobervich = new DataSet("C:\\Users\\Richi\\IdeaProjects\\AnimalTracker\\Output\\DOBERVICH_DAVID.csv");

        R.setFPS(25);
        dobervich.setFPS(25);

        R.setRegion(new Circle(308, 233, 100));
        dobervich.setRegion(new Circle(308, 233, 100));

        R.setPixelsPerCm(5.316);
        dobervich.setPixelsPerCm(5.316);


        System.out.println("My totalDist: " + R.getTotalDistSoFar(4500) + "cm vs Mr. D's totalDist: " + dobervich.getTotalDistSoFar(4500) + "cm");
        System.out.println("My dist traveled: " + R.getDist(50, 65) + "cm vs Mr. D's dist traveled: " + dobervich.getDist(50, 65) + "cm");
        System.out.println("My speed: " + R.getSpeed(30, 40) + "cm/s vs Mr. D's speed: " + dobervich.getSpeed(30, 40) + "cm/s");
        System.out.println("My time in ROI: " + R.getTimeinROI() + "s vs Mr. D's time in ROI: " + dobervich.getTimeinROI() + "s");




    }
}
