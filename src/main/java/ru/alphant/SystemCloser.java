package ru.alphant;

public class SystemCloser implements ProgramCloser{
    @Override
    public void exit(int status) {
        System.exit(status);
    }
}
