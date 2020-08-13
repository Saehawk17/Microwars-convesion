import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class displayBackground {

    Image backDisplay;

    public displayBackground(int w, int h) {
        backDisplay = loadImage();
    }

    public Image loadImage() {
        ImageIcon ii = new ImageIcon("C:/waterloo1_campaign.jpg");
        return ii.getImage();
    }

    public int getImageWidth() {
        return backDisplay.getWidth(null);
    }

    public int getImageHeight() {
        return backDisplay.getHeight(null);
    }

    public void draw(Graphics g) {
        g.drawImage(backDisplay, 0, 0, null);
    }
}
