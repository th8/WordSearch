package nl.th8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearcher {

    public static final String WORD_BOUNDARY = "\\b";
    private final boolean matchParts;
    private final boolean matchCase;
    private final String keyword;

    public WordSearcher(boolean matchParts, boolean matchCase, String keyword) {
        this.matchParts = matchParts;
        this.matchCase = matchCase;
        this.keyword = keyword;
    }

    /**
     * Factory method to create a WordSearcher.
     *
     * @param matchParts should this searcher match parts of a word.
     * @param matchCase should this searcher be case-sensitive.
     * @param keyword to match on.
     *
     * @return created WordSearcher
     */
    public static WordSearcher with(boolean matchParts, boolean matchCase, String keyword) {
        return new WordSearcher(matchParts, matchCase, keyword);
    }

    /**
     * Performs matching on the input list based on this WordSearchers configuration, and returns its matches for output to standard out.
     *
     * @param linesToSearchIn list of string to perform search on.
     * @return list of strings to print to standard out, showing highlighted matches in the lines.
     */
    public List<String> getMatches(List<String> linesToSearchIn) {
        Pattern keywordPattern;
        if(matchParts && matchCase)
            keywordPattern = Pattern.compile(keyword);
        else if (matchParts && !matchCase)
            keywordPattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        else if (!matchParts && matchCase)
            keywordPattern = Pattern.compile(WORD_BOUNDARY + keyword + WORD_BOUNDARY);
        else
            keywordPattern = Pattern.compile(WORD_BOUNDARY + keyword + WORD_BOUNDARY, Pattern.CASE_INSENSITIVE);

        AtomicInteger lineNr = new AtomicInteger(1);
        return linesToSearchIn.stream()
                .map(line -> {
                    Matcher keywordMatcher = keywordPattern.matcher(line);
                    List<String> matchesInLine = new ArrayList<>();

                    while(keywordMatcher.find())
                        matchesInLine.add(getHighlightStringFromMatcher(keywordMatcher, line, lineNr.get()));

                    lineNr.incrementAndGet();
                    return matchesInLine;
                })
                .flatMap(List::stream)
                .toList();
    }

    /**
     * Turn matches into a CLI readable output with a highlight string pointing towards a match.
     *
     * @param matcher to get matches from
     * @param line to process
     * @param lineNr of the line
     * @return A string containing the lineNr, line and a highlight string below pointing to the found match.
     */
    private String getHighlightStringFromMatcher(Matcher matcher, String line, int lineNr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lineNr).append("  ").append(line).append(System.lineSeparator());
        stringBuilder.append(" ".repeat(2 + String.valueOf(lineNr).length())).append("-".repeat(Math.max(0, matcher.start())));
        stringBuilder.append("^".repeat(Math.max(0, matcher.end() - matcher.start())));
        return stringBuilder.toString();
    }
}
