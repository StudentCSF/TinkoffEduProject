package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

@Component
public final class StackOverflowLinkParserComponentImpl
        extends BaseLinkParserComponent {

    @Override
    public IParsedLinkResponse parseLink(
            String[] splitLink
    ) {
        return new StackOverflowParsedLinkedResponseImpl(
                splitLink[1]
        );
    }
}
