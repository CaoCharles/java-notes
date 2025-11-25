public class SomeResource implements AutoCloseable {
    public void doThings(boolean error) throws Exception {
        if (error) {
            throw new Exception("Action failed");
        }
    }

    @Override
    public void close() throws Exception {
        throw new Exception("Closure failed");
    }
}
