package ru.tinkoff.edu.java.linkparser.component;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

@Component
public abstract class BaseLinkParserComponent {

    public IParsedLinkResponse parseLink(
            String url,
            String prefix
    ) {
        String cutLink = removePrefix(url, prefix);
        String[] splitLink = splitLink(cutLink);
        try {
            return parseLink(splitLink);
        } catch (Exception e) {
            return null;
        }
    }

    protected String removePrefix(
            String link,
            String prefix
    ) {
        return link.replaceFirst(prefix, "");
    }

    protected String[] splitLink(
            String link
    ) {
        return link.split(splitter());
    }

    protected String splitter() {
        return "/";
    }

    protected abstract IParsedLinkResponse parseLink(
            String[] splitLink
    );

}
