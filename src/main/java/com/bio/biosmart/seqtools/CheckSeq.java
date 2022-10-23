package com.bio.biosmart.seqtools;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckSeq {
    static private String inputS = "";
    static private ArrayList<Object> inputArray = new ArrayList<>();

    /**
     * Check whether input sequence is a raw sequence. A raw sequence
     * is defined by a sequence of common DNA nucleotides without any
     * annotation or file specific formats.
     */
    static void checkRawDNASeq(String rawSequence){
        char[] rawSequenceArray = rawSequence.toCharArray();
        // abnormal contains 10 abnormal char maximum
        ArrayList<String> abnormal = new ArrayList<>();
        ArrayList<Character> nucleotides = new ArrayList<>();
        nucleotides.add('A');
        nucleotides.add('T');
        nucleotides.add('G');
        nucleotides.add('C');
        for (char nt:
            rawSequenceArray) {
            if (nucleotides.contains(nt)){
                System.out.println(nt);
            }else {
                if (abnormal.size() < 11){
                    abnormal.add(String.valueOf(nt));
                }
            }
        }
        // if there is abnormal
        if (abnormal.size() != 0){
            System.out.println("Summary:: " + abnormal);
        }
    }

    /**
     * Check if the input sequence is a Fasta format.
     FASTA format the line before the nucleotide sequence, called the
     FASTA definition line, must begin with a carat (">"), followed by
     a unique SeqID (sequence identifier).
     */
    static void checkDNAFasta(){

    }
}
