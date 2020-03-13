import processing.core.PApplet;

public class Circle implements ROI,PixelFilter{
    private int row, col, radius;

    public Circle(int row, int col, int radius){
        this.row = row;
        this.col = col;
        this.radius = radius;
    }


    @Override
    public boolean contains(Point p) {
        int rowD = p.getRow()-row;
        int colD = p.getCol()-col;
        if((rowD*rowD + colD*colD < radius*radius)){
            return true;
        }
        return false;
    }

    @Override
    public DImage processImage(DImage img) {
        return null;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0, 255, 0);
        window.ellipse(col, row, radius, radius);
    }
}
