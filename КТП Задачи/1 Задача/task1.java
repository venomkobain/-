public class task1 {

    /**1. В Java есть единственный оператор, способный обеспечить остаток от операции деления.
     * Два числа передаются в качестве параметров. Первый параметр, разделенный на второй параметр,
     * будет иметь остаток, возможно, ноль. Верните это значение.*/
    public static int remainder(int a, int b) {
        return a%b;
    }

    /**2. Напишите функцию, которая принимает основание и высоту треугольника и возвращает его площадь.*/
    public static double triArea(double a, int h) {
        return a*h/2;
    }

    /**3. В этой задаче фермер просит вас сказать ему, сколько ног можно сосчитать среди всех его животных
     * Фермер разводит три вида: chickens = 2 legs cows = 4 legs pigs = 4 legs Фермер подсчитал своих животных,
     * и он дает вам промежуточный итог для каждого вида. Вы должны реализовать функцию, которая возвращает общее количество ног всех животных.*/
    public static int animals(int chickens, int cows, int pigs) {
        int sum = chickens*2 + pigs*4 + cows*4;
        return sum;
    }

    /**4. Создайте функцию, которая принимает три аргумента (prob, prize, pay) и возвращает true, если prob * prize > pay; в противном случае возвращает false.*/
    public static boolean profitableGamble(double prob, double prize, double pay){
        if (prob * prize > pay) return true;
        else return false;
    }

    /**5. Напишите функцию, которая принимает 3 числа и возвращает, что нужно сделать с a и b: они должны быть сложены,
     * вычитаны, умножены или разделены, чтобы получить N. Если ни одна из операций не может дать N, верните "none". 3 числа – это N, a и b.*/
    public static String operation(int a, int b, int N){
        if (a+b==N) return "added";
        if (a-b==N) return "subtracted";
        if (a*b==N) return "multiplied";
        if (a/b==N) return "divided";
        return "none";
    }

    /**6. Создайте функцию, которая возвращает значение ASCII переданного символа.*/
    public static int ctoa(char symb){
        return symb;
    }

    /**7. Напишите функцию, которая берет последнее число из последовательного списка чисел и возвращает сумму всех чисел до него и включая его.*/
    public static int addUpTo(int a){
        int sum=0;
        for (int i=a;i>0;i--){
            sum+=i;
        }
        return sum;
    }

    /**8. Создайте функцию, которая находит максимальное значение третьего ребра треугольника, где длины сторон являются целыми числами.*/
    public static int nextEdge(int a, int b){
        return a+b-1;
    }

    /**9. Создайте функцию, которая принимает массив чисел и возвращает сумму его кубов.*/
    public static int sumOfCubes(int[] mas){
        int sum=0;
        for(int i=0;i<mas.length;i++){
            sum+=Math.pow(mas[i],3);
        }
        return sum;
    }

    /**10. Создайте функцию, которая будет для данного a, b, c выполнять следующие действия:
     * – Добавьте A к себе B раз.
     * – Проверьте, делится ли результат на C.*/
    public static boolean abcmath(int a, int b, int c){
        for(int i=0;i<b;i++){
            a*=2;
        }
        return a%c==0;
    }
}
