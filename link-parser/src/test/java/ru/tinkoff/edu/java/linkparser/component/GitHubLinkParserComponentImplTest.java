package ru.tinkoff.edu.java.linkparser.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.GitHubParsedLinkResponseImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {GitHubLinkParserComponentImpl.class})
class GitHubLinkParserComponentImplTest {

    @Autowired
    private GitHubLinkParserComponentImpl parser;

    @Test
    void parseLink() {
        LinkRequest valid = new LinkRequest("https://github.com/StudentCSF/TinkoffEduProject");
        assertEquals(parser.parseLink(valid),
                new GitHubParsedLinkResponseImpl("StudentCSF", "TinkoffEduProject"));
    }

    @Test
    void parseLink2() {
        LinkRequest notExistsRepo = new LinkRequest("https://github.com/StudentCSF/dumb");
        assertEquals(parser.parseLink(notExistsRepo),
                new GitHubParsedLinkResponseImpl("StudentCSF", "dumb"));

    }

    @Test
    void parseLink3() {
        LinkRequest notExistsUser = new LinkRequest("https://github.com/dumb!@#$@#@#/TinkoffEduProject");
        assertEquals(parser.parseLink(notExistsUser),
                new GitHubParsedLinkResponseImpl("dumb!@#$@#@#", "TinkoffEduProject"));

    }

    @Test
    void parseLink4() {
        LinkRequest withoutRepo = new LinkRequest("https://github.com/StudentCSF");
        assertNull(parser.parseLink(withoutRepo));
    }

    @Test
    void parseLink5() {
        LinkRequest notValidEmpty = new LinkRequest("");
        assertNull(parser.parseLink(notValidEmpty));
    }

    @Test
    void parseLink6() {
        LinkRequest notValidOther = new LinkRequest("https://hubgit.com/StudentCSF/TinkoffEduProject");
        assertNull(parser.parseLink(notValidOther));
    }

    @Test
    void parseLink7() {
        LinkRequest nullValue = null;
        assertThrows(NullPointerException.class, () -> parser.parseLink(nullValue));
    }
}