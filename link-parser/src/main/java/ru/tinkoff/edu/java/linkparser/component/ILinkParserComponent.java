package ru.tinkoff.edu.java.linkparser.component;

import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

public sealed interface ILinkParserComponent
        permits GitHubLinkParserComponentImpl,
        StackOverflowLinkParserComponentImpl {

    IParsedLinkResponse parseLink(LinkRequest link);
}
