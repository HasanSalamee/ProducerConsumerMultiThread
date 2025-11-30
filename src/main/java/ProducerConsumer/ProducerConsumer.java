package ProducerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    private EventStorage storage;
    private int numConsumers;


    public ProducerConsumer(int queueCapacity, int numConsumers) {
        this.storage = new EventStorage(queueCapacity);
        this.numConsumers = numConsumers;
    }

    public void processFile(String inputFile, String outputFile) {
        System.out.println("بدء معالجة الملف: " + inputFile);
        System.out.println("عدد المستهلكين المعالجين: " + numConsumers);


        ExecutorService executor = Executors.newFixedThreadPool(numConsumers + 2);

        executor.execute(new Producer(storage, inputFile));


        for (int i = 0; i < numConsumers; i++) {
            executor.execute(new Consumer(storage, i));
        }

        executor.execute(new Consumer(storage, outputFile));

        executor.shutdown();

        try {

            if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
                System.out.println("انتهى الوقت قبل إكمال جميع المهام، إيقاف فوري...");
                executor.shutdownNow();
            } else {
                System.out.println("تم الانتهاء من معالجة الملف بنجاح!");
            }
        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}