package ru.tinkoff.edu.java.linkparser.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StackOverflowLinkParserComponentImplTest {

    @Autowired
    private StackOverflowLinkParserComponentImpl parser;

    @Test
    void parseLink() {
        LinkRequest valid = new LinkRequest("https://stackoverflow.com/questions/3295496");
        LinkRequest notExists = new LinkRequest("https://stackoverflow.com/questions/1");
        LinkRequest withoutId = new LinkRequest("https://stackoverflow.com/questions");
        LinkRequest incorrectSuffix = new LinkRequest("https://stackoverflow.com/dumb/23131");
        LinkRequest notValidEmpty = new LinkRequest("");
        LinkRequest notValidOther = new LinkRequest("https://flowoverstack.com/questions/228");
        LinkRequest nullValue = null;

        assertEquals(parser.parseLink(valid), new StackOverflowParsedLinkedResponseImpl("3295496"));
        assertEquals(parser.parseLink(notExists), new StackOverflowParsedLinkedResponseImpl("1"));
        assertNull(parser.parseLink(withoutId));
        assertNull(parser.parseLink(incorrectSuffix));
        assertNull(parser.parseLink(notValidEmpty));
        assertNull(parser.parseLink(notValidOther));
        assertThrows(NullPointerException.class, () -> parser.parseLink(nullValue));
    }
}