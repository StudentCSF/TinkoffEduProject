package ru.tinkoff.edu.java.scrapper.exception;

public final class UserIdNotFoundException
        extends RuntimeException {

    @Override
    public String getMessage() {
        return "User id not found";
    }
}
