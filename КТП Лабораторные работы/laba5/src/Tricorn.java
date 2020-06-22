import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        int i = 0;//итерации
        double real = 0;//действительная
        double imaginary = 0;//мнимая

        while (i < MAX_ITERATIONS && real*real+imaginary*imaginary<4)
        {
            double real2 = real*real - imaginary*imaginary + x;
            double imaginary2 = -2 * real * imaginary + y;//модуль
            real = real2;
            imaginary = imaginary2;
            i+=1;
        }
        if (i == MAX_ITERATIONS)
            return -1;

        return i;
    }

    public String toString() {
        return "Tricorn";
    }
}
