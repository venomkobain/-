public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {     //считываем все параметры
            String s = args[i];
            if (isPalindrome(s)){                   //палиндром?
                System.out.print(s+" ");              //вывод
            }
        }
    }

    public static String reverseString(String str){
        String out="";
        for (int i=0;i<str.length();i++){        //перебор букв
            out=str.charAt(i)+out;              //переварачиваем
        }
        return out;
    }

    public static boolean isPalindrome(String s){
        return s.equals(reverseString(s));          //сравнение слова
    }
}
