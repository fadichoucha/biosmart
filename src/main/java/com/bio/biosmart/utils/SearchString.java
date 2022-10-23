package com.bio.biosmart.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchString {
    String input;
    String pattern;
    ArrayList<Integer[]> matchedIndex = new ArrayList<>();

    public SearchString(String in, String pattern){
        this.input = in;
        this.pattern = pattern;
    }

    public ArrayList<Integer[]> getMatchedIndex(){
        Pattern ptr = Pattern.compile(this.pattern);
        Matcher matcher = ptr.matcher(this.input);
        while (matcher.find()){
            matchedIndex.add(
                    new Integer[]{matcher.start(), matcher.end()}
            );
        }

        return matchedIndex;
    }
}
