package ru.tinkoff.edu.java.linkparser.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.component.ILinkParserComponent;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record LinkParserService(
        List<ILinkParserComponent> parsers
) {

    public IParsedLinkResponse parseLink(
            LinkRequest linkRequest
    ) {
        if (linkRequest == null) {
            return null;
        }

        IParsedLinkResponse result;
        for (ILinkParserComponent parser : this.parsers) {
            result = parser.parseLink(linkRequest);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public List<IParsedLinkResponse> parseLinks(List<LinkRequest> links) {
        if (links == null) {
            return null;
        }

        return links.stream()
                .map(this::parseLink)
                .collect(Collectors.toList());
    }
}
