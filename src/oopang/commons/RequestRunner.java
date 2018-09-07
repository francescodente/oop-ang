package oopang.commons;

import java.util.function.Consumer;

/**
 * 
 */
public class RequestRunner<T> extends Thread {
    
    private final Consumer<T> action;
    private volatile boolean requestPending;
    private final double waitTime;
    private final T subject;
    
    public RequestRunner(final T subject, final Consumer<T> action, final double waitTime) {
        super();
        this.action = action;
        this.waitTime = waitTime;
        this.requestPending = false;
        this.subject = subject;
    }
    
    @Override
    public void run() {
        while (!this.requestPending) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        boolean waitFinished = false;
        while (!waitFinished) {
            try {
                this.wait((long)(this.waitTime * 1000));
                waitFinished = true;
            } catch (InterruptedException e) {
            }
        }
        this.requestPending = false;
        synchronized (this.subject) {
            this.action.accept(this.subject);
        }
    }
    
    public synchronized void sendRequest() {
        this.requestPending = true;
        this.notifyAll();
    }
}
