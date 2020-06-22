import java.util.*;

public class task4 {
     /**1. Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк довольно плох,
     * она решает напечатать эссе с помощью текстового процессора. Эссе содержит N слов (1≤N≤100),
     * разделенных пробелами. Каждое слово имеет длину от 1 до 15 символов включительно и состоит только
     * из прописных или строчных букв. Согласно инструкции к заданию, эссе должно быть отформатировано
     * очень специфическим образом: каждая строка должна содержать не более K (1≤K≤80) символов, не считая пробелов.
     * К счастью, текстовый процессор Бесси может справиться с этим требованием, используя следующую стратегию:
     * – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите его в эту строку.
     *      В противном случае поместите слово на следующую строку и продолжайте добавлять к этой строке.
     *      Конечно, последовательные слова в одной строке все равно должны быть разделены одним пробелом.
     *      В конце любой строки не должно быть места.
     * – К сожалению, текстовый процессор Бесси только что сломался.
     *      Пожалуйста, помогите ей правильно оформить свое эссе! */
    public static String Text(int n, int k, String str){
        int cout=0;     //последний проверенный пробел
        int space=0;    //кол-во проверенных пробелов
        String text=""; //вывод
        str+=" ";
        while(str.indexOf(" ",cout+1)>-1){
            if (str.indexOf(" ",cout+1)-str.indexOf("\r\n")-space-1>k){
                text+=str.substring(0,cout)+"\r\n"; //добавляем строку, которая убирается
                str= str.substring(cout+1); //убираем проверенную часть
                cout=0; //обнуляем счетчики
                space=0;
            }
            else {
                cout = str.indexOf(" ", cout + 1);
                space+=1;
            }
        }
        return text+str;
    }

    /**2. Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер должен быть сбалансирован.*/
    public static String[] split(String str){
        List<String> list=new ArrayList<String>();
        int start=0;    //начало скобок
        int end=0;      //конец скобок
        int scob=0;     //счетчик

        do{
            if (str.charAt(end)=='(') scob++;   //откр скобка
            else scob--;                        //закр скобка

            if (scob==0){
                list.add(str.substring(start,end+1));   //добавляем в лист
                start=end+1;
            }

            end++;

        }while (end<str.length());

        return list.toArray(new String[list.size()]);
    }

    /**3. Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет одну строку и преобразует ее либо в camelCase, либо в snake_case.*/
    public static String toCamelCase(String str){
        String[] text=str.split("_");   //разбиваем по пробелам
        str=text[0];
        for(String s: text) //у каждого слова первую букву переводим в заглавную
            str+=s.substring(0,1).toUpperCase()+s.substring(1);
        return str;
    }

    public static String toSnakeCase(String str){
        String buff="";
        for(char c: str.toCharArray()){
            if (c>='A' && c<='Z') buff+="_"+String.valueOf(c).toLowerCase(); //переводим в прописные
            else buff+=c;
        }
        return buff;
    }

    /**4. Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную с сверхурочной работой.

     Работа с 9 до 5: обычные часы работы После 5 вечера это сверхурочная работа Ваша функция получает массив с 4 значениями:
     – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
     – Конец рабочего дня. (Тот же формат)
     – Почасовая ставка
     – Множитель сверхурочных работ

     Ваша функция должна возвращать: $ + заработанные в тот день (округлены до ближайшей сотой) */
    public static String overTime(double[] mas){
        double sum=0;
        for (int i=(int)mas[0];i<17;i++){ //до 17
            sum+=mas[2];
        }
        for (int i=17;i<mas[1];i++){ //после 17
            sum+=mas[2]*mas[3];
        }
        return String.format("$%.2f",sum);
    }

    /**5. Индекс массы тела (ИМТ) определяется путем измерения вашего веса в килограммах и деления на квадрат вашего роста в метрах.
     * Категории ИМТ таковы:

     Недостаточный вес: <18,5
     Нормальный вес: 18.5-24.9
     Избыточный вес: 25 и более Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах, метрах или дюймах)
     и возвращать ИМТ и связанную с ним категорию. Округлите ИМТ до ближайшей десятой.*/
    public static String BMI(String m, String h){
        double mas=Double.valueOf(m.split(" ")[0]);
        if (m.contains("pounds")) mas*=0.453592;    //если фунты
        double height=Double.valueOf(h.split(" ")[0]);
        if (m.contains("inches")) height*=0.0254;   //если дюймы
        double score=Math.round((mas/(height*height)) * 10) / 10;
        if (score<18.5) return score+" Underweight";
        if (score<25) return score+" Normal weight";
        return score+" Overweight";
    }

    /**6. Создайте функцию, которая принимает число и возвращает его мультипликативное постоянство,
     * которое представляет собой количество раз, которое вы должны умножать цифры в num, пока не достигнете одной цифры.*/
    public static int bugger(int num){
        int count=0;    //счетчик
        int mul=num;
        while(mul>=10){
            mul=1;
            do{
                mul*=num%10;//отделяем цифры
                num=num/10;
            }while (num>0);
            num=mul;
            count++;
        }
        return count;
    }

    /**7. Напишите функцию, которая преобразует строку в звездную стенографию. Если символ повторяется n раз, преобразуйте его в символ*n. */
    public static String toStarShorthand(String str){
        String out="";
        char target=' ';
        int count=1;

        for(char c: str.toCharArray()){
            if (c!=target){ //если другой символ
                if (count>1)out+="*"+count;//добавить стар
                out+=c; //обнулить счетчики
                target=c;
                count=1;
            }
            else
                count++;
        }
        if (count>1)out+="*"+count; //если в конце
        return out;
    }

    /**8. Создайте функцию, которая возвращает true, если две строки рифмуются, и false в противном случае.
     * Для целей этого упражнения две строки рифмуются, если последнее слово из каждого предложения содержит одни и те же гласные.*/
    public static boolean doesRhyme(String str1, String str2){
        str1 = str1.substring(str1.lastIndexOf(" ")).toLowerCase();
        str2 = str2.substring(str2.lastIndexOf(" ")).toLowerCase();

        Set<Character> set1= new HashSet<Character>();
        Set<Character> set2= new HashSet<Character>();

        for (char c: str1.toCharArray()){ //добавляем буквы в сет
            if (String.valueOf(c).matches("[aeyuio]")) set1.add(c);
        }
        for (char c: str2.toCharArray()){
            if (String.valueOf(c).matches("[aeyuio]")) set2.add(c);
        }

        return set1.equals(set2); //эквивалентны?
    }

    /**9. Создайте функцию, которая принимает два целых числа и возвращает true,
     * если число повторяется три раза подряд в любом месте в num1 и то же самое число повторяется два раза подряд в num2.*/
    public static boolean trouble(long num1, long num2){
        String str1=String.valueOf(num1); //первое число
        String str2=String.valueOf(num2); //второе число
        for(int i=0;i<=9;i++){ //проверяем на совпадение
            if (str1.contains(i+String.valueOf(i)+i) && str2.contains(String.valueOf(i)+i)) return true;
        }
        return false;
    }

    /**10. Предположим, что пара одинаковых символов служит концами книги для всех символов между ними.
     * Напишите функцию, которая возвращает общее количество уникальных символов (книг, так сказать) между всеми парами концов книги.*/
    public static int countUniqueBooks(String str, char c){
        boolean flag=false; //считываем знаки
        Set<Character> set = new HashSet<Character>();
        for (char ch: str.toCharArray()) {  //перебираем все символы
            if (ch==c) flag=!flag;  //начало/конец книги
            else if (flag)
                set.add(ch);    //записываем символ
        }
        return set.size();
    }
}
