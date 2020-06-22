public class Primes{
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {    //перебор чисел от 2 до 100
            if (isPrime(i)) {               //вызов метода проверки на простое число
                System.out.print(i+" ");    //вывод
            }
        }
    }

    public static boolean isPrime(int n) {  //метод определения простого числа
        for (int i = 2; i <n; i++) {        //перебор чисел
            if (n % i == 0) return false;
        }
        return true;
    }
}