import processing.core.PApplet;

public class RemoveCircle implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();

        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[0].length; c++) {
                if((r-230)*(r-230) + (c-310)*(c-310) > 205*205){
                    pixels[r][c] = 255;
                }
            }
        }


        img.setPixels(pixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
