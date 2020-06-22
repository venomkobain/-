import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends JComponent{
    BufferedImage image;
    public JImageDisplay(int sizew, int sizeh) {
        image = new BufferedImage(sizew, sizeh, BufferedImage.TYPE_INT_RGB); //создаем полотно
        Dimension imageDimension = new Dimension(sizew, sizeh);
        super.setPreferredSize(imageDimension); //отображение
    }
    //отрисовка
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
    //очистка
    public void clearImage(){
        for (int x=0;x<image.getWidth();x++)
            for (int y=0;y<image.getHeight();y++)
                image.setRGB(x,y,0x000000); //черный цвет
    }
    //закрашивание пикселя в определённый цвет
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x,y,rgbColor);
    }
}
