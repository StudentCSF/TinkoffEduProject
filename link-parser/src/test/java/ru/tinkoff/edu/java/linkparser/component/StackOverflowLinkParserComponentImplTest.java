package ru.tinkoff.edu.java.linkparser.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.data.request.LinkRequest;
import ru.tinkoff.edu.java.linkparser.data.response.StackOverflowParsedLinkedResponseImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StackOverflowLinkParserComponentImpl.class})
class StackOverflowLinkParserComponentImplTest {

    @Autowired
    private StackOverflowLinkParserComponentImpl parser;

    @Test
    void parseLink() {
        LinkRequest valid = new LinkRequest("https://stackoverflow.com/questions/3295496");
        assertEquals(parser.parseLink(valid), new StackOverflowParsedLinkedResponseImpl("3295496"));
    }

    @Test
    void parseLink2() {
        LinkRequest notExists = new LinkRequest("https://stackoverflow.com/questions/1");
        assertEquals(parser.parseLink(notExists), new StackOverflowParsedLinkedResponseImpl("1"));
    }

    @Test
    void parseLink3() {
        LinkRequest withoutId = new LinkRequest("https://stackoverflow.com/questions");
        assertNull(parser.parseLink(withoutId));
    }

    @Test
    void parseLink4() {
        LinkRequest incorrectSuffix = new LinkRequest("https://stackoverflow.com/dumb/23131");
        assertNull(parser.parseLink(incorrectSuffix));
    }

    @Test
    void parseLink5() {
        LinkRequest notValidEmpty = new LinkRequest("");
        assertNull(parser.parseLink(notValidEmpty));
    }

    @Test
    void parseLink6() {
        LinkRequest notValidOther = new LinkRequest("https://flowoverstack.com/questions/228");
        assertNull(parser.parseLink(notValidOther));
    }

    @Test
    void parseLink7() {
        LinkRequest nullValue = null;
        assertThrows(NullPointerException.class, () -> parser.parseLink(nullValue));
    }
}