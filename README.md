# WordSearcher

Wordsearcher is a CLI tool that allows a user to find occurrences of a keyword in a text file. Made as a programming exercise for SmartDocuments by Thomas van Oss.

## Usage

    java -jar WordSearcher.jar [options] <keyword> <file>

### Options

    --part | match the keyword on parts of a word in the file, instead of the exact word only.
    --ci   | case insensitive mode, ignore case in the word to find.

## Questions

### Which considerations did you make before creating the program?

I spent a bit of time considering how to approach the different matching styles, and whether to use extenal libraries to aid me in getting matches. (e.g. Apache Commons Text) This would however not provide me with a way to efficiently get the location of the match in a string and outsourcing all the heavy lifting to a library feels somewhat cheaty in an exercise like this.

At first, I considered making each of the 4 types of matchers (case-sensitive and/or parts) into different implementations of an abstract parent class, with a factory method returning the relevant implementation based on the flags. 

This idea was scrapped after I found the current solution using Java's regex matcher, as I only needed to swap out the pattern to achieve the different matching styles.

### What do you like/dislike in the result?

I like the relative simpleness of the current solution, by relying on different regex patterns to allow all options to be implemented.

I was also glad to have a .txt input parser at hand from my earlier working during the [advent of code 2022](https://adventofcode.com/). (I highly recommend participating next december ;-) )

I was planning on taking a test driven approach, as this type of exercise lends itself well for working test-first, implementation later. But I ended up creating the unittests after completing my implementation of the WordSearcherService, to validate whether it was working at all. 

Furthermore, I would have liked to use a CLI framework instead of rolling my own argument parsing. 

My current solution does not include a --help flag or checks on invalid options, which caused me a bit of confusion when trying the app from the commandline, as I passed `-part` and `-ci` instead of `--part` and `--ci`.