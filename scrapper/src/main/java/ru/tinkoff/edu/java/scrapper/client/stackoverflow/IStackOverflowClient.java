package ru.tinkoff.edu.java.scrapper.client.stackoverflow;

import ru.tinkoff.edu.java.scrapper.client.stackoverflow.data.QuestionResponse;

public sealed interface IStackOverflowClient
        permits StackOverflowClient {

    QuestionResponse fetchQuestion(Long id);
}
