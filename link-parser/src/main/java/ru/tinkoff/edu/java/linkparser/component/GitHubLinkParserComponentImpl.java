package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.response.GitHubParsedLinkResponseImpl;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

@Component
public final class GitHubLinkParserComponentImpl
        extends BaseLinkParserComponent {

    @Override
    public IParsedLinkResponse parseLink(
            String[] splitLink
    ) {
        return new GitHubParsedLinkResponseImpl(
                splitLink[0], splitLink[1]
        );
    }
}
