package com.example.APIdemo;

import com.example.APIdemo.dto.RequestDTO;
import com.example.APIdemo.dto.ResponseDTO;
import com.example.APIdemo.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApIdemoApplicationTests {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testExampleA() {
        RequestDTO req = new RequestDTO();
        req.setData(List.of("a", "1", "334", "4", "R", "$"));

        ResponseDTO res = service.process(req);

        assertTrue(res.getSuccess());
        assertEquals(List.of("1"), res.getOddNumbers());
        assertEquals(List.of("334", "4"), res.getEvenNumbers());
        assertEquals(List.of("A", "R"), res.getAlphabets());
        assertEquals(List.of("$"), res.getSpecialCharacters());
        assertEquals("339", res.getSum());
        assertEquals("Ra", res.getConcatString());
    }

    @Test
    void testExampleB() {
        RequestDTO req = new RequestDTO();
        req.setData(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        ResponseDTO res = service.process(req);

        assertTrue(res.getSuccess());
        assertEquals(List.of("5"), res.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), res.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), res.getAlphabets());
        assertEquals(List.of("&", "-", "*"), res.getSpecialCharacters());
        assertEquals("103", res.getSum());
        assertEquals("ByA", res.getConcatString());
    }

    @Test
    void testExampleC() {
        RequestDTO req = new RequestDTO();
        req.setData(List.of("A", "ABCD", "DOE"));

        ResponseDTO res = service.process(req);

        assertTrue(res.getSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), res.getAlphabets());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("EoDdCbAa", res.getConcatString());
    }

    @Test
    void testEmptyData() {
        RequestDTO req = new RequestDTO();
        req.setData(List.of());

        ResponseDTO res = service.process(req);

        assertTrue(res.getSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("", res.getConcatString());
    }

    @Test
    void testNullDataThrows() {
        RequestDTO req = new RequestDTO();
        req.setData(null);
        assertThrows(IllegalArgumentException.class, () -> service.process(req));
    }

    @Test
    void testConcatStringLogic() {
        // "ayb" -> concat "AYB" -> reverse "BYA" -> alternating "ByA"
        assertEquals("ByA", service.buildConcatString("AYB"));
        // "AABCDDOE" -> reverse "EODDCBAA" -> alternating "EoDdCbAa"
        assertEquals("EoDdCbAa", service.buildConcatString("AABCDDOE"));
    }
}
