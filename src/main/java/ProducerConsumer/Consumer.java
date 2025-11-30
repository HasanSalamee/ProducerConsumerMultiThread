package ProducerConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable{
    private EventStorage storage;
    private int consumerId;
    private boolean isFinalConsumer;
    private String outputFile;

    private static int activeConsumers = 0;

    public Consumer (EventStorage storage, int consumerId) {
        this.storage = storage;
        this.consumerId = consumerId;
        this.isFinalConsumer = false;

        synchronized (Consumer.class) {
            activeConsumers++;
        }
    }
    public Consumer(EventStorage storage, String outputFile) {
        this.storage = storage;
        this.isFinalConsumer = true;
        this.outputFile = outputFile;
        this.consumerId = -1;
    }


    @Override
    public void run() {
        if (isFinalConsumer) {
            runFinalConsumer();   
        } else {
            runProcessingConsumer(); 
        }


    }

    private void runProcessingConsumer() {
        System.out.println("Consumer start processing " + consumerId);

        try {
            while (true) {
                String line = storage.pollInput(1, java.util.concurrent.TimeUnit.SECONDS);

                if (line != null) {

                    String processedLine = processLine(line);
                    storage.putOutput(processedLine);

                    System.out.println("المستهلك " + consumerId + ": معالجة - " +
                            processedLine.substring(0, Math.min(20, processedLine.length())) + "...");

                } else if (storage.isProducerFinished() && storage.isInputEmpty()) {
                    break;
                }
            }


            synchronized (Consumer.class) {
                activeConsumers--;
            }
            System.out.println("المستهلك المعالج " + consumerId + " انتهى");

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    private String processLine(String line) {
        return line.trim()
                .toLowerCase()
                .replaceAll("\\s+", " ")
                .replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    private void runFinalConsumer() {
        System.out.println("بدء المستهلك النهائي");

        // استخدام try-with-resources لضمان إغلاق الملف
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            while (true) {
                // محاولة أخذ سطر معالج مع وقت انتظار
                String processedLine = storage.pollOutput(1, java.util.concurrent.TimeUnit.SECONDS);

                if (processedLine != null) {
                    // كتابة السطر المعالج إلى ملف الإخراج
                    writer.write(processedLine);
                    writer.newLine(); // إضافة سطر جديد

                    System.out.println("المستهلك النهائي: كتب سطر - " +
                            processedLine.substring(0, Math.min(20, processedLine.length())) + "...");

                } else if (storage.isProducerFinished() &&   // إذا انتهى المنتج
                        storage.isInputEmpty() &&          // وكانت القائمة الأولى فارغة
                        activeConsumers == 0 &&           // ولا يوجد مستهلكين نشطين
                        storage.isOutputEmpty()) {        // وكانت القائمة الثانية فارغة
                    break; // الخروج من الحلقة
                }
            }
            System.out.println("المستهلك النهائي انتهى من الكتابة");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static int getActiveConsumers() {
        return activeConsumers;
    }
}




