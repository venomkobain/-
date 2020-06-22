import java.util.*;

public class task3 {

    public static void main(String[] args) {
        System.out.println(nextPrime(25));
    }

    /**1. Квадратное уравнение ax2 + bx + c = 0 имеет либо 0, либо 1, либо 2 различных решения для действительных значений x.
     * учитывая a, b и c, вы должны вернуть число решений в уравнение.*/
    public static int solutions(int a, int b, int c){
        int D = b*b-4*a*c; // находим Дискриминант
        if (D>0) return 2; // D>0 2 корня
        if (D==0) return 1;// D=0 1 корень
        return 0;          // D<0 0 корней
    }

    /**2. Напишите функцию, которая возвращает позицию второго вхождения " zip " в строку, или -1,
     *  если оно не происходит по крайней мере дважды. Ваш код должен быть достаточно общим,
     *  чтобы передать все возможные случаи, когда "zip" может произойти в строке. */
    public static int findZip(String str){
        return str.replaceFirst("zip","   ").indexOf("zip"); //заменяем первый zip на 3 пробела и ищем следующее вхождение
    }

    /**3. Создайте функцию, которая проверяет, является ли целое число совершенным числом или нет.
     * Совершенное число - это число, которое можно записать как сумму его множителей, исключая само число. */
    public static boolean checkPerfect(int a){
        int sum=0;              //сумма множителей
        for(int i=1;i<=a;i++){
            if (a%i==0)sum+=i;  //если число множитель
        }
        return a==sum;
    }

    /**4. Создайте функцию, которая принимает строку и возвращает новую строку
     * с заменой ее первого и последнего символов, за исключением трех условий:
     * – Если длина строки меньше двух, верните "несовместимо".".
     * – Если первый и последний символы совпадают, верните "два-это пара.". */
    public static String flipEndChars(String str){
        String s=str.substring(0,1);    //первый символ
        String e=str.substring(str.length()-1,str.length());    // последний символ
        if (str.length()<2) return "Incompatible."; //если длина меньше 2
        if (s==e) return "Two's a pair.";   //первый и последний символ одинаковый
        return e+str.substring(1,str.length()-1)+s; //меняем символы
    }

    /**5. Создайте функцию, которая определяет, является ли строка допустимым шестнадцатеричным кодом.*/
    public static boolean isValidHexCode(String str){
        return str.matches("#[a-fA-F0-9]{6}"); //регулярное выражение
    }

    /**6. Напишите функцию, которая возвращает true, если два массива имеют одинаковое количество уникальных элементов,
     * и false в противном случае.*/
    public static boolean same(Integer[] mas1, Integer[] mas2){
        Set<Integer> m1 = new HashSet<Integer>(); //создаем set, так как он содержит только уникальные значения
        Collections.addAll(m1, mas1);   // переносим все значения из массива в сет
        Set<Integer> m2 = new HashSet<Integer>();
        Collections.addAll(m2, mas2);
        return m1.size()==m2.size(); //сверяем размер (кол-во уникальных элементов)
    }

    /**7. Число Капрекара-это положительное целое число, которое после возведения в квадрат и разбиения
     * на две лексикографические части равно сумме двух полученных новых чисел:
     * – Если количество цифр квадратного числа четное, то левая и правая части будут иметь одинаковую длину.
     * – Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной половиной,
     *      а левая-самой маленькой или равной нулю, если количество цифр равно 1.
     * – Учитывая положительное целое число n, реализуйте функцию, которая возвращает true, если это число Капрекара, и false, если это не так.*/
    public static boolean isKaprekar(int num){
        int a=num*num;                      //квадрат
        String str=String.valueOf(a);       //перевод в строку
        if (str.length()==1) return false;  //если 1 цифра
        int s,e;
        s=Integer.parseInt(str.substring(0,str.length()/2));    //1 часть
        e=Integer.parseInt(str.substring(str.length()/2,str.length())); //2 часть
        return s+e==num;
    }

    /**8. Напишите функцию, которая возвращает самую длинную последовательность последовательных нулей в двоичной строке.*/
    public static String longestZero(String str){
        int maxCout=0;  //наибольшая последовательность
        int cout=0;     //количество подряд идущих 0
        String buff=""; //накопление 0
        String res="";  //результат
        for (char c: str.toCharArray()){
            if (c=='0') {
                cout++;     //увеличиваем счетчик
                buff += '0';//сохраняем 0
            }
            else {
                if (cout>maxCout) { //проверка на длинну
                    maxCout=cout;   //сохраняем новый результат
                    res=buff;
                }
                cout=0; //очищаем счетчики
                buff="";
            }
        }

        if (cout>maxCout)  //если последовательность не оканчивается 1
            res=buff;
        return res;
    }

    /**9. Если задано целое число, создайте функцию, которая возвращает следующее простое число.
     * Если число простое, верните само число.*/
    public static int nextPrime(int num){
        while(true){                    //пока не найдем простое число
            boolean f=true;             //если нашли
            for(int i=2;i<=num/2;i++){  //проверка на простое число
                if (num % i == 0) {
                    num++;              //следующее число
                    f=false;
                }
            }
            if (f)return num;
        }
    }

    /**10. Учитывая три числа, x, y и z, определите, являются ли они ребрами прямоугольного треугольника.*/
    public static boolean rightTriangle(int x,int y, int z) {
        if (z>y && z>x) return Math.sqrt(x*x+y*y)==z;   //по теореме Пифагора
        if (y>z && y>x) return Math.sqrt(x*x+z*z)==y;
        if (x>y && x>z) return Math.sqrt(z*z+y*y)==x;
        return false;
    }
}
