package ProducerConsumer;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== نظام معالجة النصوص المتوازي ===");


        System.out.print("أدخل مسار ملف الإدخال: ");
        String inputFile = scanner.nextLine().trim();

        if (inputFile.isEmpty()) {
            inputFile = "input.txt";
        }

        System.out.print("أدخل مسار ملف الإخراج: ");
        String outputFile = scanner.nextLine().trim();

        if (outputFile.isEmpty()) {
            outputFile = "output.txt";
        }

        System.out.print("أدخل عدد المستهلكين المعالجين (افتراضي 4): ");
        int numConsumers = 4;
        try {
            numConsumers = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("استخدام القيمة الافتراضية: 4");
        }

        System.out.print("أدخل سعة قوائم الانتظار (افتراضي 100): ");
        int queueCapacity = 100; // القيمة الافتراضية
        try {
            queueCapacity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("استخدام القيمة الافتراضية: 100");
        }

        scanner.close();

        System.out.println("\nإعدادات النظام:");
        System.out.println("ملف الإدخال: " + inputFile);
        System.out.println("ملف الإخراج: " + outputFile);
        System.out.println("عدد المستهلكين المعالجين: " + numConsumers);
        System.out.println("سعة قوائم الانتظار: " + queueCapacity);
        System.out.println("\nبدء المعالجة...\n");


        ProducerConsumer system = new ProducerConsumer(queueCapacity, numConsumers);
        system.processFile(inputFile, outputFile);

        System.out.println("=== انتهت المعالجة ===");
    }
}