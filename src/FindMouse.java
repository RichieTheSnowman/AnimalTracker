import processing.core.PApplet;

public class FindMouse implements PixelFilter{
    RemoveCircle r;
    ThesholdFilter t;

    public FindMouse(){
        r = new RemoveCircle();
        t = new ThesholdFilter();
    }

    @Override
    public DImage processImage(DImage img) {
        DImage circle = new DImage(r.processImage(img));
        DImage thres = new DImage(t.processImage(circle));

        return thres;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
