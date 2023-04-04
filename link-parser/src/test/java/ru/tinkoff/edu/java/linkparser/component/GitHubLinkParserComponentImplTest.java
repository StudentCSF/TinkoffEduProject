package ru.tinkoff.edu.java.linkparser.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.GitHubParsedLinkResponseImpl;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitHubLinkParserComponentImplTest {

    @Autowired
    private GitHubLinkParserComponentImpl parser;

    @Test
    void parseLink() {
        LinkRequest valid = new LinkRequest("https://github.com/StudentCSF/TinkoffEduProject");
        LinkRequest notExistsRepo = new LinkRequest("https://github.com/StudentCSF/dumb");
        LinkRequest notExistsUser = new LinkRequest("https://github.com/dumb!@#$@#@#/TinkoffEduProject");
        LinkRequest withoutRepo = new LinkRequest("https://github.com/StudentCSF");
        LinkRequest notValidEmpty = new LinkRequest("");
        LinkRequest notValidOther = new LinkRequest("https://hubgit.com/StudentCSF/TinkoffEduProject");
        LinkRequest nullValue = null;

        assertEquals(parser.parseLink(valid),
                new GitHubParsedLinkResponseImpl("StudentCSF", "TinkoffEduProject"));
        assertEquals(parser.parseLink(notExistsRepo),
                new GitHubParsedLinkResponseImpl("StudentCSF", "dumb"));
        assertEquals(parser.parseLink(notExistsUser),
                new GitHubParsedLinkResponseImpl("dumb!@#$@#@#", "TinkoffEduProject"));
        assertNull(parser.parseLink(withoutRepo));
        assertNull(parser.parseLink(notValidEmpty));
        assertNull(parser.parseLink(notValidOther));
        assertThrows(NullPointerException.class, () -> parser.parseLink(nullValue));
    }
}