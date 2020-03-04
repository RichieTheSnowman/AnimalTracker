import processing.core.PApplet;

public class FindMouse implements PixelFilter{
    RemoveCircle r;
    ThesholdFilter t;
    Point p;


    public FindMouse(){
        r = new RemoveCircle();
        t = new ThesholdFilter();
        p = new Point(10,10);
    }

    @Override
    public DImage processImage(DImage img) {
        DImage circle = new DImage(r.processImage(img));
        DImage thres = new DImage(t.processImage(circle));

        short[][] pixels = thres.getBWPixelGrid();
        p = getCenter(pixels);


        return thres;
    }

    public Point getCenter(short[][] pixels){
        int avgx = 0;
        int avgy = 0;
        int count = 0;

        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[0].length; c++) {
                if(pixels[r][c] == 255){
                    count++;
                    avgx+=c;
                    avgy+=r;

                }
            }
        }

        Point p = new Point(avgy/count, avgx/count);
        return p;

    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0, 255, 0);
            window.ellipse(p.getCol(), p.getRow(), 10, 10);

    }
}
