package ru.tinkoff.edu.java.linkparser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.component.BaseLinkParserComponent;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.IParsedLinkResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public record LinkParserService(
        Map<String, Class<? extends BaseLinkParserComponent>> supportedLinks,
        ApplicationContext applicationContext
) {

    @Autowired
    public LinkParserService {
    }

    public IParsedLinkResponse parseLink(
            LinkRequest linkRequest
    ) {
        String url = linkRequest.url();
        for (Map.Entry<String, Class<? extends BaseLinkParserComponent>> kv : this.supportedLinks.entrySet()) {
            if (url.startsWith(kv.getKey())) {
                return this.applicationContext.getBean(kv.getValue())
                        .parseLink(url, kv.getKey());
            }
        }
        return null;
    }

    public List<IParsedLinkResponse> parseLinks(List<LinkRequest> links) {
        return links.stream()
                .map(this::parseLink)
                .collect(Collectors.toList());
    }
}
