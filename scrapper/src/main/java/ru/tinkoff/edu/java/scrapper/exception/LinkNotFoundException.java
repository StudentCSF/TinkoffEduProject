package ru.tinkoff.edu.java.scrapper.exception;

public final class LinkNotFoundException
        extends RuntimeException {

    @Override
    public String getMessage() {
        return "Link not found";
    }
}
