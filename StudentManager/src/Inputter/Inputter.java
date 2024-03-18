package Inputter;

import java.util.Scanner;

public class Inputter {

    static Scanner sc = new Scanner(System.in);

    public static int GetInt(String content) {
        System.out.println(content);
        int i;
        while (true) {
            try {
                i = Integer.parseInt(sc.nextLine().trim());
                return i;
            } catch (NumberFormatException ex) {
                System.out.println("Please Enter A Integer: ");
            }
        }
    }

    public static double GetDouble(String content) {
        System.out.println(content);
        double d;
        while (true) {
            try {
                d = Double.parseDouble(sc.nextLine().trim());
                return d;
            } catch (NumberFormatException ex) {
                System.out.println("Please Enter A Double: ");
            }
        }
    }

    public static int getBit(String content) {
        System.out.println(content);
        int i;
        while (true) {
            try {

                i = Integer.parseInt(sc.nextLine().trim());
                if (i == 0 || i == 1) {
                    return i;
                } else {
                    System.out.println("Enter the bit : ");
                    System.out.println("1-Yes 0-No");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please Enter A Bit: ");
            }
        }
    }

    public static String GetString(String content) {
        System.out.println(content);
        return sc.nextLine();
    }

    public static String getPhoneNumber(String content) {
        while (true) {
            System.out.println(content);
            String phoneNumber = sc.nextLine().trim();
            if (phoneNumber.length() == 10) {
                int flag = 1;
                for (int i = 0; i < phoneNumber.length(); i++) {
                    if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57) {
                        flag = 0;
                    }
                }
                if (flag == 1) {
                    return phoneNumber;
                }
                else{
                    System.out.println("Số điện thoại không hợp lệ !");
                }
            } else {
                System.out.println("Số điện thoại không hợp lệ !");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(sc.nextLine());
    }
}
