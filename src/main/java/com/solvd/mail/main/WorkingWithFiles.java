package com.solvd.mail.main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WorkingWithFiles {
    private static final String DELIMS = " \n\t\r.,;:!?@{}[]()*&^%$#|/.";

    public static void main(String[] args) throws IOException {
        try {
            subjectHeading(DELIMS, "text.txt", "out.txt");
        } catch (IOException e) {
            LogManager.getLogger(WorkingWithFiles.class).info(e.getMessage());
        }
    }

    public static void subjectHeading(String delimeters, String inputPath, String outputPath) throws IOException {
        String s = StringUtils.join(StringUtils.split((FileUtils.readFileToString(new File(inputPath), StandardCharsets.UTF_8)), delimeters), "  ");
        for (String i : new LinkedHashSet<>(List.of(StringUtils.split(StringUtils.lowerCase(s))))) {
            FileUtils.write(new File(outputPath), i + "  " + StringUtils.countMatches(" " + StringUtils.lowerCase(s) + " ", " " + StringUtils.lowerCase(i) + " ") + "\n", StandardCharsets.UTF_8, true);
        }
    }
}
