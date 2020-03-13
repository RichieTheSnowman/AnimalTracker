import processing.core.PApplet;

import java.io.*;
import java.util.Scanner;

public class MouseTracker implements PixelFilter {
    private static final int MAX_FRAMES = 4500;  // TODO:  Change this value to match video
    private DataSet dataset;
    private int frameCount = 0;
    private FindMouse mouse;


    public MouseTracker() {
        mouse = new FindMouse();
        dataset = new DataSet(mouse.points, 5.316, 25);  // TODO:  feel free to change the constructor
        dataset.setRegion(new Circle(300, 300, 50));
    }


    @Override
    public DImage processImage(DImage img) {
        frameCount++;
        DImage m = new DImage(mouse.processImage(img));

        //System.out.println("position:       time spend at ROI:");
        displayInfo(dataset);

        if (frameCount < MAX_FRAMES) {
            outputCSVData("C:\\Users\\Richi\\IdeaProjects\\AnimalTracker\\Output\\CHEN_RICHARD.txt", dataset);
        /*
        1).  Filter the image to isolate mouse
        2).  Extract information about the mouse
        3).  Load information into dataset.
         */
        } else if (frameCount == MAX_FRAMES){
            System.exit(0);
        }

        return m;
    }

    private void displayInfo(DataSet dataset) {
        System.out.println(dataset.getTotalDist() + ", " + dataset.getTimeinROI() + ", " + frameCount);
    }

    private void outputCSVData(String filePath,DataSet dataset) {
        try(FileWriter f = new FileWriter(filePath);
            BufferedWriter b = new BufferedWriter(f);
            PrintWriter p = new PrintWriter(b);){
            p.println("First line of the file");

            for (int i = 0; i < dataset.getCenters().size(); i++) {
                p.println(dataset.getCenters().get(i).getCol() + "," + dataset.getCenters().get(i).getRow());
                // + ", " + dataset.getTotalDistSoFar(i)
            }
        } catch (IOException e) {
            System.err.println("There was a problem writing to the file: " + filePath);
            e.printStackTrace();
        }
    }

    private String readFileAsString(String filepath){
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


    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        mouse.drawOverlay(window, original, filtered);
        window.ellipse(300, 300, 100, 100);

        // TODO:  If you want, draw the trail behind the mouse.
    }

}

