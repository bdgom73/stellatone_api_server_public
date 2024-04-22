package com.stellive.music.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchUtil {

    public static List<String> generateSearchQueries(String input) {

        // 공백을 기준으로 단어를 나누어 리스트에 추가
        String[] words = input.split("\\s+");
        List<String> queries = new ArrayList<>(Arrays.asList(words));

        // 단어를 조합하여 새로운 검색어를 생성 (공백이 붙어 있는 형태도 추가)
        for (int i = 2; i <= words.length; i++) {
            for (int j = 0; j <= words.length - i; j++) {
                StringBuilder combination = new StringBuilder();
                for (int k = j; k < j + i; k++) {
                    combination.append(words[k]);
                    if (k < j + i - 1) {
                        combination.append(" ");
                    }
                }
                queries.add(combination.toString());
            }
        }

        // 붙어 있는 단어를 추가
        for (int i = 0; i < words.length - 1; i++) {
            StringBuilder combination = new StringBuilder();
            combination.append(words[i]);
            combination.append(words[i + 1]);
            queries.add(combination.toString());
        }

        return queries;
    }

}
