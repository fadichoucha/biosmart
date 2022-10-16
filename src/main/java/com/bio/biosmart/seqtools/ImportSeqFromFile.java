package com.bio.biosmart.seqtools;

import java.io.*;
import java.util.stream.Stream;

public class ImportSeqFromFile {
    private final String filePath;

    // file name/path must be provided into the constructor
    public ImportSeqFromFile(String path){
        this.filePath = path;
    }

    public void getContent() throws FileNotFoundException {
        InputStream ist = null;
        // get the file from resource
        try{
            // Load the file content
            try {
                File seqFile = new File(this.filePath);
                BufferedReader br = new BufferedReader(new FileReader(seqFile));
                // Read the file
                Stream<String> content = br.lines();
                System.out.println(content);
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




    }
}
