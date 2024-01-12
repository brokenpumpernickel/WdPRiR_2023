import java.util.concurrent.locks.ReentrantLock;

public class ReadersWritersSubpar {


    public static class Resource {
        private ReentrantLock lock = new ReentrantLock();
        private int counter = 0;

        public int getCounter() {
            try {
                lock.lock();
                Thread.sleep(1000);
                return counter;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }

        public void setCounter(int counter) {
            try {
                lock.lock();
                Thread.sleep(1000);
                this.counter = counter;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public int incrementAndGet() {
            try {
                lock.lock();
                Thread.sleep(1000);
                this.counter = ++counter;
                return this.counter;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Reader extends Thread {
        private Resource resource;

        public Reader(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while(true)
                System.out.println("Reader " + getId() + " " + resource.getCounter());
        }
    }

    public static class Writer extends Thread {
        private Resource resource;

        public Writer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while(true)
                System.out.println("Writer " + getId() + " " + resource.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        Writer writer = new Writer(resource);
        writer.start();

        Reader[] readers = new Reader[5];
        for(int i = 0; i < readers.length; ++i) {
            readers[i] = new Reader(resource);
            readers[i].start();
        }
    }
}
