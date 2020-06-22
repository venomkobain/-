import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int sizeDisplay;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    static public void main(String args[]){
        FractalExplorer displayExplorer = new FractalExplorer(650);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }

    public FractalExplorer(int sizeDisplay) {
        this.sizeDisplay = sizeDisplay;
        range = new Rectangle2D.Double();
        fractal = new Mandelbrot();
        fractal.getInitialRange(range);
        display = new JImageDisplay(sizeDisplay,sizeDisplay);
    }

    public void createAndShowGUI(){
        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal");
        frame.add(display, BorderLayout.CENTER);    //дисплей
        EventBtn eventBtn = new EventBtn();         //события
        EventMouse eventMouse = new  EventMouse();
        display.addMouseListener(eventMouse);
        JButton btnReset = new JButton();           //кнопка
        btnReset.setText("Reset");
        frame.add(btnReset, BorderLayout.SOUTH);
        btnReset.addActionListener(eventBtn);
        frame.pack ();              //размещение
        frame.setVisible (true);    //видимость
        frame.setResizable (false); //запрет изменения размера окна
        drawFractal();
        frame.repaint();            //обновить дисплей
    }

    private void drawFractal() {
        for (int x = 0; x < sizeDisplay; x++) {
            for (int y = 0; y < sizeDisplay; y++) {
                double xCoord = fractal.getCoord(range.x, range.x + range.width, sizeDisplay, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, sizeDisplay, y);

                int numIters = fractal.numIterations(xCoord, yCoord);

                if (numIters == -1){
                    display.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    private class EventBtn implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    private class EventMouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,range.x + range.width, sizeDisplay, x);

            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,range.y + range.height, sizeDisplay, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }
}
