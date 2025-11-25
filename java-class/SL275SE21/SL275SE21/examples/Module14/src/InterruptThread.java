public class InterruptThread {
    public static void main(String[] args) {
        Runnable r = () -> {
            Thread ct = Thread.currentThread(); // locate current thread object
            while (!ct.isInterrupted()) { // check interrupt signal when running
                // perform thread actions
                try {
                    Thread.sleep(1000); // enter timed waiting state for 1000 milliseconds
                } catch (InterruptedException ex) {
                    // perform interrupted when waiting actions
                    return;
                }
            }
        }; // getting to the end of the run method terminates the thread
        Thread t = new Thread(r);
        t.start();
        t.interrupt();
        // when thread is in the running state it is not forced to check this signal
    }
}
