package com.bio.biosmart.seqtools;

import com.bio.biosmart.UI.SeqEditShow;
import com.bio.biosmart.utils.OpenFile;

import java.io.FileNotFoundException;

public class MainBoard {
    public static void main(String[] args) throws FileNotFoundException {
        ImportSeqFromFile seq = new ImportSeqFromFile("data/test.fasta");
        //seq.getContent();

        // Get file content
        //OpenFile.getEntireContent("data/rawSeq.txt");
        //CheckSeq.checkRawDNASeq("CAGTTGCAGNTGTYA");
    }
}
