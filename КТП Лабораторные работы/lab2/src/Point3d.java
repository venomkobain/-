public class Point3d extends Point2d{
    /** координата Z **/
    private double zCoord;
    /** Конструктор инициализации **/
    public Point3d(double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    /** Конструктор по умолчанию. **/
    public Point3d(){
        this(0.0,0.0,0.0);
    }
    /** Возвращение координаты Z **/
    public double getZ(){
        return zCoord;
    }
    /** Установка значения координаты Z. **/
    public void setZ(double zCoord) {
        this.zCoord = zCoord;
    }
    /** Сравнение объектов **/
    public boolean equil(Point3d point){
        return (xCoord==point.getX() && yCoord==point.getY() && zCoord==point.getZ());
    }
    /** Дистанция между точками **/
    public double distanceTo(Point3d point){
        double d = Math.sqrt(Math.pow((point.getX()-xCoord),2)+Math.pow((point.getY()-yCoord),2)+Math.pow((point.getZ()-zCoord),2));
        d = (double)Math.round(d*100)/100;
        return d;
    }
}