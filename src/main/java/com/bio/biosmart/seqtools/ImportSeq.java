package com.bio.biosmart.seqtools;

import java.io.*;

public class ImportSeq {


    /**
     * * Open text file and return its content. The file should
     * contain only raw sequence. Only for small files.
     *
     * @param filePath
     * String name of file. Full or short file name.
     * @return
     * Return data as `Stream` String type.
     */
    public static String getEntireContent(String filePath){
        StringBuffer content = new StringBuffer();
        try{
            // Load the file content
            File seqFile = new File(filePath);
            try {
                BufferedReader br = new BufferedReader(new FileReader(seqFile));
                // Read the file
                br.lines().forEach(content::append);
                System.out.println(content);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Finished");
            }
        }
        catch (NullPointerException nul){
            System.out.println("ERROR: File is not found: " + nul.getMessage());
        }

        return content.toString();
    }
}
