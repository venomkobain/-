import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.*;

public class task5 {
    /**1. Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение.
     * Первая буква строки или первый элемент массива представляет собой символьный код этой буквы.
     * Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y. */
    public static int[] encrypt(String str){
        int[] out=new int[str.length()]; //создаем массив
        out[0]=str.charAt(0);   //добавляем код первого символа
        for (int i=1;i<str.length();i++){
            out[i]=str.charAt(i)-str.charAt(i-1);   //добавляем разницу кодов символов
        }
        return out;
    }

    public static String decrypt(int[] mas){
        String out=String.valueOf((char)mas[0]);
        int score=mas[0];   // предыдущее декодированное значение
        for (int i=1;i<mas.length;i++){
            out+=String.valueOf((char)(mas[i]+score));  //сумируем коды
            score=mas[i]+score;
        }
        return out;
    }

    /**2. Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
     * Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого сделать.
     * Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король".*/
    public static boolean canMove(String piece,String start, String end){
        char wordS = start.charAt(0);
        int numS = Integer.parseInt(String.valueOf(start.charAt(1)));
        char wordE = end.charAt(0);
        int numE = Integer.parseInt(String.valueOf(end.charAt(1)));
        if (wordS==wordE && numS==numE) return false;
        switch (piece){
            case "Pawn": {      //Пешка
                if (wordS==wordE && numS==2 && numE==4) return true;
                if (wordS==wordE && numS==(numE-1)) return true;
                return false;
            }
            case "Knight": {    //Конь
                if ((Math.abs(wordS-wordE)==2 && Math.abs(numS-numE)==1)||(Math.abs(wordS-wordE)==1 && Math.abs(numS-numE)==2)) return true;
                return false;
            }
            case "Bishop": {    //Слон
                if (Math.abs(wordS-wordE)==Math.abs(numS-numE))return true;
                return false;
            }
            case "Rook": {      //Ладья
                if ((wordS==wordE && numS!=numE) || (wordS!=wordE && numS==numE)) return true;
                return false;
            }
            case "Queen": {     //Ферзь
                if ((wordS==wordE && numS!=numE) || (wordS!=wordE && numS==numE)) return true;
                if (Math.abs(wordS-wordE)==Math.abs(numS-numE))return true;
                break;
            }
            case "King": {      //Король
                if (Math.abs(wordS-wordE)<2 && Math.abs(numS-numE)<2) return true;
                return false;
            }
            default: return false;
        }
        return false;
    }

    /**3. Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие буквы не должны быть удалены,
     * чтобы соответствовать слову. Кроме того, порядок букв во входной строке должен быть таким же, как и порядок букв в последнем слове.
     * Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено.*/
    public static boolean canComplete(String str1,String str2){
        boolean flag=false;
        for(char c: str2.toCharArray()){ //перебираем все символы слова
            if (c==str1.charAt(0)){ //проверяем совпадение с первой буквой дополняемого слова
                str1=str1.substring(1); //удаляем первый совпавший символ
                if (str1.length()==0){  //если дополняемое слово 0 длины, то true
                    flag=true;
                    break;
                }
            }
        }
        return flag;
    }

    /**4. Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе
     * и возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру. */
    public static int sumDigProd(int... number){
        int num=0;
        for (int i : number) {
            num+=i;
        }

        int mul=num;
        while(mul>=10){
            mul=1;
            do{
                mul*=num%10;//отделяем цифры
                num=num/10;
            }while (num>0);
            num=mul;
        }
        return num;
    }

    /**5. Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в любом порядке и / или количестве),
     * что и первое слово, включая первое слово. */
    public static String[] sameVowelGroup(String[] str){
        List<String> arrList=new ArrayList<String>(); //результат
        arrList.add(str[0]);

        for(int i=1;i<str.length;i++){
            boolean isTrue=true;
            for(char c: "aeiuoy".toCharArray()){    //проверка на наличие совпадающих гласных
                if (str[0].contains(String.valueOf(c))!=str[i].contains(String.valueOf(c)))
                    isTrue=false;
            }
            if (isTrue) arrList.add(str[i]); //добавление слова
        }
        return arrList.toArray(new String[arrList.size()]);
    }

    /**6. Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если это число является
     * действительным номером кредитной карты, а в противном случае-false. Номера кредитных карт должны быть длиной
     * от 14 до 19 цифр и проходить тест Луна, описанный ниже:
     * – Удалите последнюю цифру (это"контрольная цифра").
     * – Переверните число.
     * – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры,
     *      сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
     * – Добавьте все цифры.
     * – Вычтите последнюю цифру суммы (из шага 4) из 10.
     * Результат должен быть равен контрольной цифре из Шага 1.*/
    public static boolean validateCard (long num){
        String str=String.valueOf(num);
        if (str.length()<14 || str.length()>19) return false; //неправильная длина
        //step 1
        String valid = str.substring(str.length()-1); //сохр. посл. символ
        str=str.substring(0,str.length()-1);    //убираем его
        //step 2
        String temp="";
        for (int i=str.length()-1;i>=0;i--){    //переварачиваем
            temp+=str.charAt(i);
        }
        str=temp;
        //step 3-4
        int sum=0;
        for (int i=0; i<str.length();i++){  //домножаем и складываем
            int n=Integer.parseInt(String.valueOf(str.charAt(i)));
            if (i%2==0) n*=2;
            if (n>9) n=n%10+n/10;
            sum+=n;
        }
        //step 5
        sum=10-sum%10;//число для проверки
        //step 6
        return valid==String.valueOf(sum);
    }

    /**7. Напишите функцию, которая принимает положительное целое число от 0 до 999 включительно
     * и возвращает строковое представление этого целого числа, написанное на английском языке.*/
    public static String numToEng(int num){
        String out="";
        if (num==0) return "zero";
        switch (num/100){   //сотни
            case 1: {out += "one hundred ";break;}
            case 2: {out += "two hundred ";break;}
            case 3: {out += "three hundred ";break;}
            case 4: {out += "four hundred ";break;}
            case 5: {out += "five hundred ";break;}
            case 6: {out += "six hundred ";break;}
            case 7: {out += "seven hundred ";break;}
            case 8: {out += "eight hundred ";break;}
            case 9: {out += "nine hundred ";break;}
        }
        switch (num/10%10){ //10-19
            case 1: {switch (num%10){
                case 1: {out += "eleven";return out;}
                case 2: {out += "twelve";return out;}
                case 3: {out += "thirteen";return out;}
                case 4: {out += "fourteen";return out;}
                case 5: {out += "fifteen";return out;}
                case 6: {out += "sixteen";return out;}
                case 7: {out += "seventeen";return out;}
                case 8: {out += "eighteen";return out;}
                case 9: {out += "nineteen";return out;}}}
            case 2: {out += "twenty ";break;} //20-90
            case 3: {out += "thirty ";break;}
            case 4: {out += "forty ";break;}
            case 5: {out += "fifty ";break;}
            case 6: {out += "sixty ";break;}
            case 7: {out += "seventy ";break;}
            case 8: {out += "eighty ";break;}
            case 9: {out += "ninety ";break;}
        }
        switch (num%10){//единицы
            case 1: {out += "one";break;}
            case 2: {out += "two";break;}
            case 3: {out += "three";break;}
            case 4: {out += "four";break;}
            case 5: {out += "five";break;}
            case 6: {out += "six";break;}
            case 7: {out += "seven";break;}
            case 8: {out += "eight";break;}
            case 9: {out += "nine";break;}
        }
        return out;
    }

    public static String numToRus(int num){
        String out="";
        if (num==0) return "ноль";
        switch (num/100){                   //сотни
            case 1: {out += "сто ";break;}
            case 2: {out += "двести ";break;}
            case 3: {out += "триста ";break;}
            case 4: {out += "четыреста ";break;}
            case 5: {out += "пятьсот ";break;}
            case 6: {out += "шестьсот ";break;}
            case 7: {out += "семьсот ";break;}
            case 8: {out += "восемьсот ";break;}
            case 9: {out += "девятьсот ";break;}
        }
        switch (num/10%10){                 //десятки
            case 1: {out += "десять ";break;}
            case 2: {out += "двадцать ";break;}
            case 3: {out += "тридцать ";break;}
            case 4: {out += "сорок ";break;}
            case 5: {out += "пятьдесят ";break;}
            case 6: {out += "шестьдесят ";break;}
            case 7: {out += "семьдесят ";break;}
            case 8: {out += "восемьдесят ";break;}
            case 9: {out += "девяносто ";break;}
        }
        switch (num%10){                    //единицы
            case 1: {out += "один";break;}
            case 2: {out += "два";break;}
            case 3: {out += "три";break;}
            case 4: {out += "четыре";break;}
            case 5: {out += "пять";break;}
            case 6: {out += "шесть";break;}
            case 7: {out += "семь";break;}
            case 8: {out += "восемь";break;}
            case 9: {out += "девять";break;}
        }
        return out;
    }

    /**8. Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
     * Хеш должен быть отформатирован в виде шестнадцатеричной цифры.*/
    public static String getSha256Hash(String str)throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256"); //задаем кодировку
        byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8)); //тип кодировки
        String res = Base64.getEncoder().encodeToString(encodedhash);
        char[] alf = "0123456789ABCDEFF".toCharArray(); // алфавит
        char[] out = new char[encodedhash.length*2];
        for (int i = 0; i < encodedhash.length;i++)
        {
            int v = encodedhash[i] & 0xFF;
            out[i*2]=alf[v>>>4];
            out[i*2+1]=alf[v & 0x0F];
        }
        return new String(out).toLowerCase();
    }

    /**9. Напишите функцию, которая принимает строку и возвращает строку с правильным регистром.
     * Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый символ
     * в верхнем регистре, а остальные-в Нижнем. */
    public static String correctTitle(String str){
        String[] input=str.toLowerCase().split(" "); //разбиваем по пробелам
        String line="";
        for (String s:input){ //проверяем все слова
            if (s.equals("and") || s.equals("the") || s.equals("of") || s.equals("in"))
                line+=s+" ";
            else line+=String.valueOf(s.charAt(0)).toUpperCase()+s.substring(1)+" ";
        }
        return line.trim();
    }

    /**10. Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если n не является
     * центрированным шестиугольным числом или его иллюстрацией в виде многострочной прямоугольной строки в противном случае.*/
    public static String hexLattice(int num){
        int n=1;
        int i=1;
        while (n<num){ //проверяем, что это центрированный шестиугольник
            n+=6*i;
            i++;
        }
        if (n!=num) return "Invalid";

        int k=1;
        boolean up = true;
        String out="";
        while (k>0){ //цикл строк
            if (i-k!=0)out+= String.format("%" + (i-k) + "s", "");
            for (int j = 0; j < i+k-1; j++) {   //цикл o в строке
                out += String.format("%2s", "o");
            }
            out+="\r\n"; //отступ

            if (up) k++; //переключение уменьшения и увеличения
            else k--;
            if (k>i) {
                up=false;
                k-=2;
            }
        }

        return out;
    }
}
