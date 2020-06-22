import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends JComponent{
    public BufferedImage image;    //изображение

    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension); //отображение
    }

    //отрисовка
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    //очистка
    public void clearImage(){
        for (int i=0;i<image.getWidth();i++)
            for (int j=0;j<image.getHeight();j++)
                image.setRGB(i,j,0);
    }

    //закрашивание 1 пикселя
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x,y,rgbColor);
    }
}
