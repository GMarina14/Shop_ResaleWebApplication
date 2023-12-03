package ru.skypro.homework.exception;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException() {
        super("Check that the login is entered correctly");
    }
}
