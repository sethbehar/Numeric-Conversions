import java.util.Scanner;


public class NumericConversion {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int operation;
        boolean a = true;
        while(a){
            printMenu();
            operation = sc.nextInt();

            while(operation < 1 || operation > 4){      // make sure user is entering a proper menu input
                System.out.println("Error");
                operation = sc.nextInt();
            }


            if(operation == 1) {
                System.out.println("Please enter the numeric string to convert: ");
                String code = sc.next();
                System.out.println("Result:" + hexStringDecode(code));

            } else if(operation == 2) {
                System.out.println("Please enter the numeric string to convert: ");
                String code = sc.next();
                System.out.println("Result: " + binaryStringDecode(code));

            } else if(operation == 3) {
                System.out.println("Please enter the numeric string to convert: ");
                String code = sc.next();
                System.out.println("Result: " + binaryToHex(code));

            } else if(operation == 4) {
                System.out.println("Goodbye!");
                a = false;
                break;
            }

        }
    }


    public static void printMenu(){
        System.out.println("\nDecoding Menu" +
                            "\n-------------" +
                            "\n1. Decode hexadecimal" +
                            "\n2. Decode binary" +
                            "\n3. Convert binary to hexadecimal" +
                            "\n4. Quit\n" +
                            "\nPlease enter an option: ");
    }

    public static long hexStringDecode(String hex) {


        if ((hex.indexOf("0x") == 0) || (hex.indexOf("0b") == 0))           // check if it begins with 0x or 0b
            hex = hex.substring(2);


        long answer = 0;
        String val = "";
        String reversedHex =  reverseString(hex);


        for (int i = 0; i < reversedHex.length(); i++) {            // check if char is a letter, then raise it to the proper power of 16
            val = String.valueOf(reversedHex.charAt(i)).toLowerCase();
            switch (val) {

                case "a":
                    answer += 10 * Math.pow(16, i);
                    break;

                case "b":
                    answer += 11 * Math.pow(16, i);
                    break;

                case "c":
                    answer += 12 * Math.pow(16, i);
                    break;

                case "d":
                    answer += 13 * Math.pow(16, i);
                    break;

                case "e":
                    answer += 14 * Math.pow(16, i);
                    break;

                case "f":
                    answer += 15 * Math.pow(16, i);
                    break;

                default:
                    answer += Long.parseLong(val) * Math.pow(16, i);
                    break;
            }
        }
        return answer;
    }


    public static short hexCharDecode(char digit){

        String value = String.valueOf(digit).toLowerCase();
        short temp = 0;
        switch (value) {

            case "a":
                temp = 10;
                break;

            case "b":
                temp = 11;
                break;

            case "c":
                temp = 12;
                break;

            case "d":
                temp = 13;
                break;

            case "e":
                temp = 14;
                break;

            case "f":
                temp = 15;
                break;

            default:
                temp = Short.parseShort(value);
                break;
        }
        return temp;
    }

    public static short binaryStringDecode(String binary){

        if(binary.substring(0,2).equals("0b".toLowerCase()))        // check to see if '0b' is used in UI
            binary = binary.substring(2);

        String newBinary = reverseString(binary);       // reverse the string since binary is read the opposite way.

        String[] bin = new String[binary.length()];     // split String binary into String[] bin

        for(int i = 0; i < binary.length(); i++)
            bin[i] = newBinary.substring(i, i + 1);

        short total = 0;   // check each character in binary string for a 1, then add its corresponding value
        double power = 0;

        for(int i = 0; i < bin.length; i++) {
            if (bin[i].equals("1"))
                total += Short.parseShort(Integer.toString((int) Math.pow(2, power)));
            power++;
        }
        return total;
    }

    public static String binaryToHex(String binary) {

        if ((binary.indexOf("0x") == 0) || (binary.indexOf("0b") == 0))         // remove 0b or 0x from string
            binary = binary.substring(2);


        switch(binary.length() % 4) {       // final length of string is the length mod 4 (each chunk)

            case 1:
                binary = "000" + binary;
                break;

            case 2:
                binary = "00" + binary;
                break;

            case 3:
                binary = "0" + binary;
                break;

            default:
                break;
        }

        String finalHex = "";
        String chunk = "";

        for (int i = 0; i < binary.length(); i++) {

            String piece = String.valueOf(binary.charAt(i));
            chunk += piece;

            if (chunk.length() % 4 == 0) {
                switch(chunk) {
                    // check each chunk and change to proper letter/number (15 possible combinations)
                    case "0000":
                        finalHex += "0";
                        break;

                    case "0001":
                        finalHex += "1";
                        break;

                    case "0010":
                        finalHex += "2";
                        break;

                    case "0011":
                        finalHex += "3";
                        break;

                    case "0100":
                        finalHex += "4";
                        break;

                    case "0101":
                        finalHex += "5";
                        break;

                    case "0110":
                        finalHex += "6";
                        break;

                    case "0111":
                        finalHex += "7";
                        break;

                    case "1000":
                        finalHex += "8";
                        break;

                    case "1001":
                        finalHex += "9";
                        break;

                    case "1010":
                        finalHex += "A";
                        break;

                    case "1011":
                        finalHex += "B";
                        break;

                    case "1100":
                        finalHex += "C";
                        break;

                    case "1101":
                        finalHex += "D";
                        break;

                    case "1110":
                        finalHex += "E";
                        break;

                    case "1111":
                        finalHex += "F";
                        break;

                    default:
                        break;
                }
                chunk = "";
            }
        }

        return finalHex;
    }


    public static String reverseString(String s){
        String temp = "";
        for(int i = s.length(); i > 0; i--)
            temp += s.substring(i - 1, i);
        return temp;
    }


}
