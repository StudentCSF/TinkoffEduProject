package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.GitHubParsedLinkResponseImpl;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

@Component
public record GitHubLinkParserComponentImpl()
        implements ILinkParserComponent {

    @Override
    public IParsedLinkResponse parseLink(LinkRequest link) {
        String url = link.url();

        final String prefix = "https://github.com/";

        if (!url.startsWith(prefix)) {
            return null;
        }

        url = url.replaceFirst(prefix, "");
        String[] splitLink = url.split("/");
        try {
            return new GitHubParsedLinkResponseImpl(
                    splitLink[0],
                    splitLink[1]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
