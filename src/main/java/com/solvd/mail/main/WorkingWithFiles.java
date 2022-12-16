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
    private static final String DELIMS = "\n\t\r.,;:!?@{}[]()*&^%$#|/.";
    private static final File out = new File("out.txt");

    public static void main(String[] args) throws IOException {
        String s = StringUtils.lowerCase(StringUtils.joinWith("  ", (Object[]) StringUtils.split(StringUtils.replaceChars(FileUtils.readFileToString(new File("text.txt"), StandardCharsets.UTF_8), DELIMS, " "))));
        Set<String> set = new LinkedHashSet<>(List.of(StringUtils.split(s)));
        for (String i : set) {
            FileUtils.write(out, (i + "  " + StringUtils.countMatches(" " + StringUtils.lowerCase(s) + " ", " " + StringUtils.lowerCase(i) + " ") + "\n"), StandardCharsets.UTF_8, true);
        }
    }
}
