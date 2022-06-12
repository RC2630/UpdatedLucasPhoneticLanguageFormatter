public class CustomExceptions {

    public static class FileIsEmptyException extends Exception {
        // represents a case where a file is empty
    }

    public static class NotAnOptionException extends Exception {
        // represents a case where the option given by the user is not valid
    }

}