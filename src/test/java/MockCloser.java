import ru.alphant.ProgramCloser;

public class MockCloser implements ProgramCloser {
    @Override
    public void exit(int status) {
        System.out.println("Exit with status: " + status);
    }
}
