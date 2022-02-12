package com.ua;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String result = makePhoneNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        System.out.println(result);
    }

    /**
     * Метод делает из массива чисел нормер телефона по указаному шаблону
     *
     * @param numbers массив чисел номера телефона
     * @return строку, номер телефона согласно заданому формату
     */
    public static String makePhoneNumber(int[] numbers) {
        String number = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(""));
        return number.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");
    }

    /**
     * Метод разбивает массив на составные части
     *
     * @param ls входной массив для обработки
     * @return массив со значениями суммы всех
     * элементов для каждой из частей
     */
    public static int[] sumParts(int[] ls) {
        if (ls.length < 1)
            return new int[]{0};
        int[] sum_parts = new int[ls.length + 1];
        for (int i = 0; i < ls.length; i++) {
            sum_parts[i] = Arrays.stream(ls, i, ls.length).parallel().sum();
        }

        sum_parts[sum_parts.length - 1] = 0;
        return sum_parts;
    }

    /**
     * Метод который возвращает кол-во минут требуемое на обслуживание очереди
     *
     * @param customers массив покупателей, который хранит значение времени
     *                  требуемого на осущестление покупки
     * @param n         кол-во касс которые могут обслужить покупателей
     * @return значение времени требуемого для обслуживания всей очереди
     */
    public static int solveSuperMarketQueue(int[] customers, int n) {
        int[] results = new int[n];
        for (int customer : customers) {
            results[0] += customer;
            Arrays.sort(results);
        }
        return results[n - 1];
    }

    /**
     * Метод который осуществляет перенос первых букв каждого слова
     * в конец соответствующего слова и добавление приставки 'ay'
     *
     * @param str входная строка для преображения
     * @return измененнуб строку
     */
    public static String pigIt(String str) {
        String[] split = str.split("\\s");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (split[i].toLowerCase().charAt(0) >= 'a' && split[i].toLowerCase().charAt(0) <= 'z')
                split[i] = split[i].substring(1) + split[i].charAt(0) + "ay";
            result.append(split[i]).append(" ");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Метод для нахождения уникального, непосторяющегося значения
     *
     * @param arr массив значений
     * @return уникальное значение
     */
    public static double findUniq(double[] arr) {
        double result = arr[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (result == arr[i])
                result = arr[i];
            else if (arr[i] == arr[i + 1])
                return result;
            else if (result == arr[i + 1])
                return arr[i];
            else
                return arr[i + 1];
        }
        return result;
    }

    /**
     * Оптимизированый метод для определения простого числса
     *
     * @param num число для проверки
     * @return истину в случае, если число простое и ложь - если составное
     */
    public static boolean isPrime(int num) {
        if (num % 2 == 0)
            return num == 2;

        if (num % 3 == 0)
            return num == 3;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        }
        return true;
    }

    /**
     * Перевод строки из верблюжего регистра
     *
     * @param input строка для преображения
     * @return измененную строку
     */
    public static String camelCase(String input) {
        String[] strings = input.split("");
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].equals(input.substring(i, i + 1).toUpperCase()))
                strings[0] += " " + strings[i];
            else
                strings[0] += strings[i];
        }
        return strings[0];
    }

    /**
     * Перевод строки в верблюжий регистр
     *
     * @param s входная строка для преображения
     * @return измененную строку
     */
    public static String toCamelCase(String s) {
        String[] strings = s.split("(\\s|-|_|\\.)");
        for (int i = 1; i < strings.length; i++) {
            strings[0] += strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1);
        }
        return strings[0];
    }

    /**
     * Метод кодирующий строку символами ')' и '(', где символы
     * повторяющиеся в строке заменяем на '(', а в случае уникальности на ')'
     *
     * @param word взодящая строка для преображения
     * @return прображенную строку
     */
    public static String encode(String word) {
        return word.toLowerCase()
                .chars()
                .mapToObj(i -> String.valueOf((char) i))
                .map(i -> word.toLowerCase().indexOf(i) == word.toLowerCase().lastIndexOf(i) ? "(" : ")")
                .collect(Collectors.joining());

    }

    /**
     * Метод который разбивает число на расшириную форму, сумму этого числа
     * например 12 = 10 + 2
     *
     * @param num число
     * @return строку расширеной формы числа
     */
    public static String expendedForm(int num) {
        StringBuilder result = new StringBuilder();
        int length = String.valueOf(num).length();
        for (int i = 0; i < length; i++) {
            if (num % 10 != 0)
                result.insert(0, (num % 10) * (int) Math.pow(10, i) + " + ");
            num /= 10;
        }
        return result.delete(result.length() - 3, result.length()).toString();
    }

    /**
     * Метод ищет пропущенную букву в массиве символов согласно алфавиту
     *
     * @param arr массив символов для поиска пропущенной буквы
     * @return пропущенную букву
     */
    public static char findMissingLetter(char[] arr) {
        for (char c = arr[0]; c <= arr[arr.length - 1]; c++) {
            if (Arrays.binarySearch(arr, c) < 0)
                return c;
        }
        return '?';
    }

    /**
     * Метод который не любит принцип переноса чисел.
     * Проще говоря лиенивое сложение. Например 16 + 18 = 214
     *
     * @param num1 первое входное число
     * @param num2 второе входное число
     * @return полученое значение в ходе ленивого сложения
     */
    public static int add(int num1, int num2) {
        if (num1 / 10.0 > 1 && num2 / 10.0 > 1) {
            StringBuilder result = new StringBuilder();
            int maxNum = Math.max(num1, num2);
            for (int i = 0; i < String.valueOf(maxNum).length(); i++) {
                if (maxNum > 10) {
                    result.insert(0, (num1 % 10) + (num2 % 10));
                } else
                    result.insert(0, num1 + num2);
                num1 /= 10;
                num2 /= 10;
            }
            return Integer.parseInt(result.toString());
        }
        return num1 + num2;
    }

    /**
     * Метод для проверки правильности ввода массива example@example.com
     *
     * @param email электронная почта для проверки
     * @return истину, если почта указана верно, или ложь - неверно
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9+_.-]+@[a-z](.+)$");
        return pattern.matcher(email).matches();
    }
}



