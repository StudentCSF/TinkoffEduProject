package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

@Component
public record StackOverflowLinkParserComponentImpl()
        implements ILinkParserComponent {

    @Override
    public IParsedLinkResponse parseLink(LinkRequest link) {
        String url = link.url();

        final String prefix = "https://stackoverflow.com/";

        if (!url.startsWith(prefix)) {
            return null;
        }

        url = url.replaceFirst(prefix, "");
        String[] splitLink = url.split("/");
        try {
            return new StackOverflowParsedLinkedResponseImpl(
                    splitLink[1]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
