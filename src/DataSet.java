import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {
    private double pixelsPerCm;
    private int FPS;
    ArrayList<Point> centers = new ArrayList<>();
    ROI region;


    public ArrayList<Point> getCenters() {
        return centers;
    }

    public void setPixelsPerCm(double pixelsPerCm) {
        this.pixelsPerCm = pixelsPerCm;
    }

    public DataSet(ArrayList<Point> centers, double pixelsPerCm, final int FPS){
        this.centers = centers;
        this.pixelsPerCm = pixelsPerCm;
        this.FPS = FPS;
    }
    public DataSet(String filename){
        readFileAsPoints(filename);
    }
    public double distBtwPoints(Point p1, Point p2){
        double dx = p1.getRow()-p2.getRow();
        double dy = p1.getCol()-p2.getCol();
        return Math.sqrt(dx*dx + dy*dy)/(double)pixelsPerCm;
    }
    public void setFPS(int x){FPS = x; }
    public void add(Point c){centers.add(c);}
    public void clearData(){centers.clear();}

    public void setRegion(ROI region) {
        this.region = region;
    }

    public double getTotalDist(){
        double totalDist = 0;
        for (int i = 0; i < centers.size()-1; i++) {
            totalDist += distBtwPoints(centers.get(i), centers.get(i+1));
        }

        return totalDist;
    }

    public double getTotalDistSoFar(int endFrame){
        if(endFrame < 1){
            return -1;
        }
        double totalDist = 0;
        for (int i = 0; i < endFrame-1; i++) {
            totalDist += distBtwPoints(centers.get(i), centers.get(i+1));
        }

        return totalDist;
    }


    public Point getPosAt(double time){
        if(time < 0){
            System.exit(-1);
        }
        int v = (int)(time*FPS);
        return centers.get(v);
    }
    public double getDist(double time){
        if(time < 1/(double)FPS){
            System.exit(-1);
        }
        int frame = (int)(FPS*time);
        double totalDist = 0;
        for (int i = 0; i < frame-1; i++) {
            totalDist += distBtwPoints(centers.get(i), centers.get(i+1));
        }
        return totalDist;
    }
    public double getDist(double start, double end){
        if(start < 1/(double)FPS && end < 1/(double)FPS){
            System.exit(-1);
        }

        int f1 = (int)(start*FPS);
        int f2 = (int)(end*FPS);

        double totalDist = 0;
        for (int i = f1; i <= f2-1; i++) {
            totalDist += distBtwPoints(centers.get(i), centers.get(i+1));

        }

        return totalDist;
    }
    public double getSpeed(double time){
        if(time < 0){
            System.exit(-1);
        }

        double totalDist = getDist(time, time-1);
        return totalDist / (1);
    }
    public double getSpeed(double start, double end){
        if(start < 1/(double)FPS && end < 1/(double)FPS){
            System.exit(-1);
        }
        double totalDist = getDist(start, end);
        return totalDist / (end-start);
    }

    public double getTimeinROI(){
        double time = 0;

        for (int i = 0; i < centers.size(); i++) {
            Point p = centers.get(i);
            if(region.contains(p)){
                time += 1/(double) FPS;

            }
        }

       return time;
    }

    public String readFileAsString(String filepath){
        StringBuilder output = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filepath))){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                output.append(line + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public void readFileAsPoints(String filepath){
        try (Scanner scanner = new Scanner(new File(filepath))){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[]loc = line.split(",");
                centers.add(new Point(Integer.parseInt(loc[0]), Integer.parseInt(loc[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
