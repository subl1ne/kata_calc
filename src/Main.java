import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = inputScanner.nextLine();
        System.out.println(calculateExpression(expression));
    }

    public static String calculateExpression(String expression) throws Exception {
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда");

        String operation = detectOperation(expression);
        if (operation == null) throw new Exception("Неподдерживаемая математическая операция");

        boolean isRoman = Roman.isRoman(operands[0]) && Roman.isRoman(operands[1]);

        int operand1 = isRoman ? Roman.convertToArabian(operands[0]) : Integer.parseInt(operands[0]);
        int operand2 = isRoman ? Roman.convertToArabian(operands[1]) : Integer.parseInt(operands[1]);

        if (operand1 > 10 || operand2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int result = (operation.equals("+")) ? operand1 + operand2 :
                (operation.equals("-")) ? operand1 - operand2 :
                        (operation.equals("*")) ? operand1 * operand2 :
                                operand1 / operand2;

        if (isRoman) {
            if (result <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            return Roman.convertToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    static String detectOperation(String expression) {
        return expression.contains("+") ? "+" :
                expression.contains("-") ? "-" :
                        expression.contains("*") ? "*" :
                                expression.contains("/") ? "/" :
                                        null;
    }
}


class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
