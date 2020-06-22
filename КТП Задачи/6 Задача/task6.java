import java.util.*;

public class task6 {
    /**1. Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые подмножества.
     * Создайте функцию, которая принимает число n и возвращает соответствующее число Белла. */
    public static int bell(int a){
        int[][] arr = new int[a+1][a+1];
        arr[0][0]=1; //начальные значения
        arr[a][a]=1; //матрицы Стирлинга
        for (int n=1; n<=a;n++) {   //заполняем
            for (int k = 1; k < a; k++){
                arr[n][k]=arr[n-1][k-1]+k*arr[n-1][k];
            }
        }
        int sum=0;  //находим сумму последнего ряда
        for (int k=0;k<=a;k++)sum+=arr[a][k];
        return sum;
    }

    /**2. – Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного до конца слова и добавьте «ay» в конец.
     *    – Если слово начинается с гласной, добавьте "yay" в конце слова. */
    public static String translateWord(String word){
        if (word.isEmpty()) return "";
        if(!Character.isLetter(word.charAt(0))) return "";
        if (word.matches("[aeiyouAEIYOU].*"))//если с гласной
            return word+"yay";
        else{
            String str=word.split("[aeiyouAEIYOU]")[0];
            return word.replaceFirst(str,"")+str+"ay";//если с согласной
        }
    }

    public static String translateSentence(String words){
        String text="";
        String buff="";
        for(char c: words.toCharArray()){
            if (Character.isLetter(c))
                buff+=c;
            else{
                text+=translateWord(buff);
                text+=c;
                buff="";
            }
        }
        return text+=translateWord(buff);
    }


    /**3. Учитывая параметры RGB (A) CSS, определите, является ли формат принимаемых значений допустимым или нет.
     * Создайте функцию, которая принимает строку (например, " rgb(0, 0, 0)") и возвращает true,
     * если ее формат правильный, в противном случае возвращает false.*/
    public static boolean validColor(String str){
        if (!str.startsWith("rgb") && !str.startsWith("rgba")) return false;
        str=str.substring(str.indexOf("(")+1,str.indexOf(")"));//только цифры
        for (String s: str.split(",")){
            if (s.matches("\\d")) { //если цифра
                if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 255) return false;
            }
            else return false;
        }
        return true;
    }

    /**4. Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся параметры запроса и параметры,
     * указанные во втором аргументе (который будет необязательным массивом).*/
    public static String stripUrlParams(String url, String[] arrParam){
        if (!url.contains("?")) return url;
        String out=url.split("\\?")[0]+"?"; //разбиваем на адрес и параметры
        String[] param=url.split("\\?")[1].split("&");//разбиваем параметры

        Map<String, String> params = new HashMap<String, String>();
        for(String p: param) {
            boolean flag=true;
            for (String str : arrParam) { //проверяем на наличие в списке не нужных параметров
                if(p.indexOf(str)==0) flag=false;
            }
            if(flag) params.put(p.split("=")[0],p.split("=")[1]);//добавляем параметр
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            out+=entry.getKey()+"="+entry.getValue()+"&"; //добавляем параметры к адресу
        }
        return out.substring(0,out.length()-1);
    }

    public static String stripUrlParams(String url) {
        return stripUrlParams(url,new String[0]);
    }

    /**5. Напишите функцию, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги.
     * Если несколько слов одинаковой длины, найдите слово, которое встречается первым.*/
    public static String[] getHashTags(String str){
        str="#"+str; //добавляем теги к слова
        List<String> arrList=Arrays.asList(str.toLowerCase().replace(" "," #").split(" "));

        Comparator<String> compare = (String  o1, String o2) -> o2.length()-o1.length();
        Collections.sort(arrList, compare); //сортируем по длине

        while (arrList.size()>3) arrList.remove(arrList.size()-1); //оставляем 3 или меньше

        return arrList.toArray(new String[arrList.size()]);
    }

    /**6. Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.*/
    public static int ulam(int n) {
        int max = 500;
        Vector<Integer> mas = new Vector<Integer>();
        mas.add(1); //начальные значения
        mas.add(2);
        for (int i = 3; i < max; i++) {
            int num = 0;
            for (int j = 0; j < mas.size() - 1; j++) {  //ищем наим. положительное число
                for (int k = j + 1; k < mas.size(); k++) {
                    if (mas.get(j) + mas.get(k) == i) {
                        num++;
                    }
                    if (num > 1)
                        break;
                }
                if (num > 1)
                    break;
            }
            if (num == 1) {
                mas.add(i);
            }
        }
        return mas.get(n - 1);
    }

    /**7. Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.*/
    public static String longestNonrepeatingSubstring(String str){
        String out="";  //слово
        String buff=""; //накопитель
        for(char c: str.toCharArray()){
            if (!buff.contains(String.valueOf(c)))
                buff+=c;    //накапливаем
            else{
                if (buff.length()>out.length())
                    out=buff; //если повтор
                buff=""+c;
            }
        }
        if (buff.length()>out.length())
            out=buff;
        return out;
    }



    /**8. Создайте функцию, которая принимает арабское число и преобразует его в римское число.*/
    public static String convertToRoman(int num){



        int iLet=1000;
        String let="IVXLCDM";//алфавит

        String out="";
        int i=(String.valueOf(num).length()-1)*2;

        for(char c:String.valueOf(num).toCharArray()){//перебор цифр
            if(c>'0' && c<'4'){ //знаки 1000,100,10,1
                out+=String.format("%"+c+"s",String.valueOf(let.charAt(i))).replace(" ",String.valueOf(let.charAt(i)));
            }
            if(c>'4' && c<'9'){ //знаки 500,50,5
                out+=let.charAt(i+1);
                if (c!='5')
                out+=String.format("%"+(c-'5')+"s",String.valueOf(let.charAt(i))).replace(" ",String.valueOf(let.charAt(i)));
            }
            if(c=='4'){//400,40,4
                out+=String.valueOf(let.charAt(i))+String.valueOf(let.charAt(i+1));
            }
            if(c=='9'){//900,90,9
                out+=String.valueOf(let.charAt(i))+String.valueOf(let.charAt(i+2));
            }
            i-=2;
        }

        return out;
    }

    /**9. Создайте функцию, которая принимает строку и возвращает true или false в зависимости от того,
     * является ли формула правильной или нет.*/
    public static boolean formula(String str){
        if (!str.matches("^(\\d+\\s[\\*\\+-/]\\s\\d+\\s=\\s\\d+)$"))
            return false;//если не формула
        String[] left=str.split("=")[0].trim().split(" ");//берем левую часть
        String right=str.split("=")[1].trim();//ответ
        int fun=0;

        switch (left[1]){//проверяем ответ
            case "+":fun=Integer.parseInt(left[0])+Integer.parseInt(left[2]); break;
            case "-":fun=Integer.parseInt(left[0])-Integer.parseInt(left[2]); break;
            case "*":fun=Integer.parseInt(left[0])*Integer.parseInt(left[2]); break;
            case "/":fun=Integer.parseInt(left[0])/Integer.parseInt(left[2]); break;
        }
        if (fun==Integer.parseInt(right)) return true;
        else return false;
    }

    /**10. Создайте функцию, которая возвращает значение true, если само число является палиндромом
     * или любой из его потомков вплоть до 2 цифр (однозначное число - тривиально палиндром).
     * Прямой потомок числа создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.*/
    public static boolean palindromedescendant(int num){
        String nums=String.valueOf(num);

        while(num>9){ //пока не 1 знак
            boolean flag=true;
            for (int i=0; i<nums.length(); i++){//проверяем на палиндром
                if (nums.charAt(i)!=nums.charAt(nums.length()-1-i)) flag=false;
            }
            if (flag) return true;

            String temp=""; //суммируем соседние цифры
            for (int i=1;i<nums.length();i+=2){
                temp+=Integer.parseInt(String.valueOf(nums.charAt(i)))+Integer.parseInt(String.valueOf(nums.charAt(i-1)));
            }
            nums=temp;
        }
        return false;
    }
}
