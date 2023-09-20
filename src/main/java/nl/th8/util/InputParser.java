package nl.th8.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Util to parse .txt files for use in the word searcher.
 * Code borrowed and adapted from <a href="https://github.com/th8/AdventOfCode2022/blob/main/src/main/java/nl/th8/adventofcode2022/utils/PuzzleInputParser.java">my AdventOfCode2022 repo</a>
 */
public class InputParser {

    private final Path inputPath;

    public InputParser(Path path) {
        inputPath = path;
    }

    /**
     * Factory method to create a parser from path.
     *
     * @param path of the file to parse
     * @return created InputParser
     */
    public static InputParser withPath(Path path) {
        return new InputParser(path);
    }

    /**
     * @return a list of Strings for each line of the file.
     */
    public List<String> getInputAsStringList() {
        try(var fileStream = Files.lines(inputPath)) {
            return fileStream.toList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("Unable to read input file. Please provide a valid .txt file.");
        }
    }
}
