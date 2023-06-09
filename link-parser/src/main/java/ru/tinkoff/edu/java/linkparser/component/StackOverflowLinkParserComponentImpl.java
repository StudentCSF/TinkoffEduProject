package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

@Component
public record StackOverflowLinkParserComponentImpl()
        implements ILinkParserComponent {

    private static final String PREFIX = "https://stackoverflow.com/questions/";

    @Override
    public IParsedLinkResponse parseLink(LinkRequest link) {
        String url = link.url();

        if (!url.startsWith(PREFIX)) {
            return null;
        }

        url = url.replaceFirst(PREFIX, "");
        String[] splitLink = url.split("/");
        try {
            return new StackOverflowParsedLinkedResponseImpl(
                    splitLink[0]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
