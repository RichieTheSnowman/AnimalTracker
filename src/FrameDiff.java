import processing.core.PApplet;

public class FrameDiff implements PixelFilter {
    DImage previousImg = null;
    DImage currentImg = null;
    private int thres;

    public FrameDiff(){
        this.thres = 25;
    }

    @Override
    public DImage processImage(DImage img) {
        currentImg = new DImage(img);

        if (previousImg == null) {
            previousImg = new DImage(img); // save current frame
            return img;
        }

        short[][] current = currentImg.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();

        for (int r = 0; r < current.length; r++) {
            for (int c = 0; c < current[0].length; c++) {
                int diff = Math.abs(current[r][c] - previous[r][c]);

                if (diff > thres) current[r][c] = 255;
                else {current[r][c] = 0; }

            }
        }
        previousImg = new DImage(img);
        img.setPixels(current);
        return img;

    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {



    }
}
