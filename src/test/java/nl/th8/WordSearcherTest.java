package nl.th8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordSearcherTest {

    private final List<String> inputList = List.of(
            "Should match test in a sentence",
            "Should not match testmachine in a sentence",
            "Should not match TeSt in a sentence",
            "Should not match TestMachine in a sentence",
            "Should match multiple test in a test sentence.",
            "Should not match random things not containing the keyword.");

    @Test
    void getMatchesNoFlags() {
        WordSearcher underTest = WordSearcher.with(
                false,
                true,
                "test");

        assertEquals(3, underTest.getMatches(inputList).size());
    }

    @Test
    void getMatchesCaseInsensitiveNoParts() {
        WordSearcher underTest = WordSearcher.with(
                false,
                false,
                "test");

        assertEquals(4, underTest.getMatches(inputList).size());
    }

    @Test
    void getMatchesCaseInsensitiveWithParts() {
        WordSearcher underTest = WordSearcher.with(
                true,
                false,
                "test");

        assertEquals(6, underTest.getMatches(inputList).size());
    }

    @Test
    void getMatchesWithParts() {
        WordSearcher underTest = WordSearcher.with(
                true,
                true,
                "test");

        assertEquals(4, underTest.getMatches(inputList).size());
    }
}