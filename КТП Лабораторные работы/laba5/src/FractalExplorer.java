import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class FractalExplorer {
    private int sizeDisplay;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    static public void main(String args[]){
        FractalExplorer displayExplorer = new FractalExplorer(600);
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
        EventMouse eventMouse = new EventMouse();  
        display.addMouseListener(eventMouse);
        JPanel DOWNpanel = new JPanel();
        JButton btnReset = new JButton("Reset");
        JButton btnSave =new JButton("Save Image");
        DOWNpanel.add(btnSave); //добавляем кнопки
        DOWNpanel.add(btnReset);
        btnReset.addActionListener(eventBtn);
        btnSave.addActionListener(eventBtn);
        frame.add(DOWNpanel, BorderLayout.SOUTH);   
        JPanel UPpanel = new JPanel();              
        JComboBox comboBox = new JComboBox();       
        comboBox.addActionListener(eventBtn);
        JLabel labelComboBox = new JLabel("Fractal:");        
        UPpanel.add(labelComboBox);
        UPpanel.add(comboBox);
        frame.add(UPpanel, BorderLayout.NORTH);   
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
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
            if (e.getSource() instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) e.getSource();
                fractal = (FractalGenerator) comboBox.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand().equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if(e.getActionCommand().equals("Save Image")){
                JFileChooser openDialog = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                openDialog.setFileFilter(filter);
                openDialog.setAcceptAllFileFilterUsed(false);
                int selectedFile = openDialog.showSaveDialog(display);
                if (selectedFile == openDialog.APPROVE_OPTION){
                    File file = openDialog.getSelectedFile();
                    try {
                        BufferedImage image = display.image;
                        ImageIO.write(image, "png", file);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
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
