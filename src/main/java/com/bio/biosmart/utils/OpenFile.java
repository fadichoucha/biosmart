package com.bio.biosmart.utils;

import java.io.*;
import java.util.stream.Stream;

public class OpenFile {
    /**
     * Open text file and return its content. The file should contain only
     * raw sequence.
     * Return data as `Stream` String type.
     */
    public static Stream<String> getEntireContent(String filePath){
        Stream<String> content = null;
        try{
            // Load the file content
            File seqFile = new File(filePath);
            try {
                BufferedReader br = new BufferedReader(new FileReader(seqFile));
                // Read the file
                content = br.lines();
                content.forEach(System.out::println);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Finished");
            }
        }
        catch (NullPointerException nul){
            System.out.println("ERROR: File is not found: " + nul.getMessage());
        }

        return content;
    }
}
