package ProducerConsumer;

import java.util.concurrent.LinkedBlockingQueue;

public class EventStorage {
    private LinkedBlockingQueue<String> inputQueue;
    private LinkedBlockingQueue<String> outputQueue;
    private volatile boolean producerFinished = false;

    public EventStorage (int capacity){
        inputQueue =new LinkedBlockingQueue<>(capacity);
        outputQueue=new LinkedBlockingQueue<>(capacity);
    }

    public String takeInput() throws InterruptedException {
        return inputQueue.take();
    }
    public String pollInput(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException {
        return inputQueue.poll(timeout, unit);
    }
    public void putOutput(String processedLine) throws InterruptedException {
        outputQueue.put(processedLine);
    }
    public String takeOutput() throws InterruptedException {
        return outputQueue.take();
    }
    public String pollOutput(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException {
        return outputQueue.poll(timeout, unit);
    }
    public boolean isInputEmpty() {
        return inputQueue.isEmpty();
    }
    public boolean isOutputEmpty() {
        return outputQueue.isEmpty();
    }

    public void setProducerFinished(boolean finished) {
        this.producerFinished = finished;
    }
    public boolean isProducerFinished() {
        return producerFinished;
    }


    public void putInput(String line) throws InterruptedException {
        inputQueue.put(line);
    }
}
