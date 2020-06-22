import java.util.Arrays;

public class task2 {
    /**1. Создайте функцию, которая повторяет каждый символ в строке n раз*/
    public static String repeat(String str, int num){
        String out="";
        for (int i=0;i<str.length();i++){
            out+=String.format("%"+String.valueOf(num)+"s",str.charAt(i)).replace(' ',str.charAt(i));
        }
        return out;
    }

    /**2. Создайте функцию, которая принимает массив и возвращает разницу между самыми большими и самыми маленькими числами.*/
    public static int differenceMaxMin(int[] mas){
        Arrays.sort(mas);
        return mas[mas.length-1]-mas[0];
    }

    /**3. Создайте функцию, которая принимает массив в качестве аргумента и возвращает true или false в зависимости от того,
     * является ли среднее значение всех элементов массива целым числом или нет.*/
    public static boolean isAvgWhole(int[] mas){
        double sum=0;
        for (int i=0;i<mas.length;i++){
            sum+=mas[i];
        }
        double average = sum/mas.length;
        if (average%1==0)return true;
        return false;
    }

    /**4. Создайте метод, который берет массив целых чисел и возвращает массив, в котором каждое целое число
     * является суммой самого себя + всех предыдущих чисел в массиве.*/
    public static int[] cumulativeSum(int[] mas){
        int[] newMas= new int[mas.length];
        int sum=0;
        for (int i=0;i<mas.length;i++){
            sum+=mas[i];
            newMas[i]=sum;
        }
        return newMas;
    }

    /**5. Создайте функцию, которая возвращает число десятичных знаков, которое имеет число (заданное в виде строки).
     * Любые нули после десятичной точки отсчитываются в сторону количества десятичных знаков.*/
    public static int getDecimalPlaces(String str){
        if (str.lastIndexOf(".")==-1) return 0;
        return str.lastIndexOf(".");
    }

    /**6. Создайте функцию, которая при заданном числе возвращает соответствующее число Фибоначчи.*/
    public static int Fibonacci(int a){
        int last=1;
        int next=2;
        for(int i=1;i<a;i++){
            int temp=next;
            next+=last;
            last=temp;
        }
        return last;
    }

    /**7. Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку, напишите функцию,
     * чтобы определить, является ли вход действительным почтовым индексом.
     * Действительный почтовый индекс выглядит следующим образом:
     * – Должно содержать только цифры (не допускается использование нецифровых цифр).
     * – Не должно содержать никаких пробелов.
     * – Длина не должна превышать 5 цифр.*/
    public static boolean isValid(String str){
        return str.matches("\\d{5}");
    }

    /**8. Пара строк образует странную пару, если оба из следующих условий истинны:
     * – Первая буква 1-й строки = последняя буква 2-й строки.
     * – Последняя буква 1-й строки = первая буква 2-й строки.
     * – Создайте функцию, которая возвращает true, если пара строк представляет собой странную пару, и false в противном случае.*/
    public static boolean isStrangePair(String str1, String str2){
        String cs1 =String.valueOf(str1.charAt(0));
        String ce1 =String.valueOf(str1.charAt(str1.length()-1));
        String cs2 =String.valueOf(str2.charAt(0));
        String ce2 =String.valueOf(str2.charAt(str2.length()-1));
        return cs1==ce2 && ce1==cs2;
    }

    /**9. Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
     * – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
     * – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
     * – В противном случае верните false.*/
    public static boolean isPrefix(String str1, String str2){
        return str1.startsWith(str2.replace("-",""));
    }

    public static boolean isSuffix(String str1, String str2){
        return str1.endsWith(str2.replace("-",""));
    }

    /**10. Создайте функцию, которая принимает число (шаг) в качестве аргумента и возвращает количество полей
     * на этом шаге последовательности.*/
    public static int boxSeq(int a){
        boolean flag=true;
        int sum=0;
        for(int i=0;i<a;i++){
            if (flag) sum+=3;
            else sum-=1;
            flag=!flag;
        }
        return sum;
    }
}
