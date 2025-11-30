package ProducerConsumer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Producer implements Runnable{
    private EventStorage storage;
    private String inputFile;
    public Producer(EventStorage storage, String inputFile) {
        this.storage = storage;
        this.inputFile = inputFile;
    }


    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {

                storage.putInput(line);


                System.out.println("المنتج: أضاف سطر - " +
                        line.substring(0, Math.min(20, line.length())) + "...");
            }
            storage.setProducerFinished(true);
            System.out.println("المنتج انتهى من قراءة الملف بالكامل");

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
        }

    }
}
