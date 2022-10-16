package com.bio.biosmart.seqtools;
import java.io.FileNotFoundException;

public class MainBoard {
    public static void main(String[] args) throws FileNotFoundException {
        ImportSeqFromFile seq = new ImportSeqFromFile("data/test.fasta");
        seq.getContent();
    }
}
