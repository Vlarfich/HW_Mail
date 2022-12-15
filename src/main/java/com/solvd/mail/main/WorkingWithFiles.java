package com.solvd.mail.main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WorkingWithFiles {

    public static void main(String[] args) throws IOException {
        String s = StringUtils.replaceChars(FileUtils.readFileToString(new File("text.txt"), StandardCharsets.UTF_8), "\n.,;:?!\t", " ");
        List<String> res = new ArrayList<>();
        String[] mas = StringUtils.split(StringUtils.lowerCase(s));
        Set<String> set = new LinkedHashSet<>(List.of(mas));
        set.forEach((i) -> res.add(i + "  " + StringUtils.countMatches(" " + StringUtils.lowerCase(s) + " ", " " + StringUtils.lowerCase(i) + " ")));
        FileUtils.writeLines(new File("out.txt"), res);
    }
}
