package nl.th8;

import nl.th8.util.InputParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * WordSearch CLI
 * <p>
 * Exercise made for SmartDocuments by Thomas van Oss
 */
public class WordSearcherCLI {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Required arguments are <keyword> and <file>.");
        }

        //Parse command line arguments
        boolean matchParts = optionIsSet(args, "part");
        boolean caseInsensitive = optionIsSet(args, "ci");
        String keyword = args[args.length-2];
        String fileName = args[args.length-1];

        //Transform the input file to a list of strings.
        List<String> inputAsStringList = InputParser.withPath(Path.of(Paths.get("").toAbsolutePath().toString(), fileName))
                            .getInputAsStringList();

        //Perform matching and print to standard out.
        List<String> foundMatches = WordSearcher.with(matchParts, !caseInsensitive, keyword).getMatches(inputAsStringList);

        System.out.printf("Found %s occurrences of the keyword %s in '%s'%n", foundMatches.size(), keyword, fileName);
        foundMatches.forEach(System.out::println);

    }

    /**
     * Check whether a commandline option was set by the user.
     *
     * @param args commandline arguments
     * @param option to find in args
     * @return whether option occurs in args
     */
    private static boolean optionIsSet(String[] args, String option) {
        return Arrays.stream(args).anyMatch(arg -> arg.matches("--" + option));
    }
}
