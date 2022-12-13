package com.solvd.mail.main;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WorkingWithFiles {

    public static void main(String[] args) throws IOException {
        File file = new File("text.txt");
        String s = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println(s);
        Map<String, Integer> map = new LinkedHashMap<>();
        StringTokenizer st = new StringTokenizer(s, " .,;:?!\n");
        while (st.hasMoreElements()) {
            String str = st.nextToken().toLowerCase();
            str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
            if (map.get(str) == null) {
                map.put(str, 1);
            } else {
                int k = map.get(str) + 1;
                map.replace(str, k);
            }

        }
        List<String> res = new ArrayList<>();
        File out = new File("out.txt");
        map.forEach((k, v) -> {
            res.add(k + " " + v);
        });
        FileUtils.writeLines(out, res);
    }
}
