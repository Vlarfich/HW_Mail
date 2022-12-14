package com.solvd.mail.main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WorkingWithFiles {

    public static void main(String[] args) throws IOException {
        File file = new File("text.txt");
        String s = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println(s);
        List<String> res = new ArrayList<>();
        int choice = 2;
        switch (choice) {
            case 1 -> {
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
                map.forEach((k, v) -> {
                    res.add(k + " " + v);
                });
            }
            case 2 -> {
                Set<String> set = new LinkedHashSet<>();
                StringTokenizer st = new StringTokenizer(s, " .,;:?!\n");
                StringBuilder sb = new StringBuilder();
                while (st.hasMoreElements()) {
                    String str = st.nextToken().toLowerCase();
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                    set.add(str);
                    str = " " + str + " ";
                    sb.append(str);
                }
                s = StringUtils.lowerCase(s);
                for (String i : set) {
                    int ind = StringUtils.countMatches(sb.toString(), " " + i + " ");
                    res.add(i + " " + ind);
                }
            }
            case 3 -> {
                Set<String> set = new LinkedHashSet<>();
                StringTokenizer st = new StringTokenizer(s, " .,;:?!\n");
            }
        }

        File out = new File("out.txt");
        FileUtils.writeLines(out, res);

    }
}
