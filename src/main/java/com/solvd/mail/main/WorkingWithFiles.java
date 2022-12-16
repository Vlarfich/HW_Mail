package com.solvd.mail.main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WorkingWithFiles {
    private static final String DELIMITERS = " \n\t\r.,;:!?@{}[]()*&^%$#|/.";

    public static void main(String[] args) {
        try {
            subjectHeading(DELIMITERS, "text.txt", "out.txt");
        } catch (IOException e) {
            LogManager.getLogger(WorkingWithFiles.class).info(e.getMessage());
        }
    }

    public static void subjectHeading(String delimiters, String inputPath, String outputPath) throws IOException {
        String s = StringUtils.lowerCase(StringUtils.join(StringUtils.split((FileUtils.readFileToString(new File(inputPath), UTF_8)), delimiters), "  "));
        for (String i : new LinkedHashSet<>(List.of(StringUtils.split(s)))) {
            FileUtils.write(new File(outputPath), i + "  " + StringUtils.countMatches(" " + s + " ", " " + i + " ") + "\n", UTF_8, true);
        }
    }
}
