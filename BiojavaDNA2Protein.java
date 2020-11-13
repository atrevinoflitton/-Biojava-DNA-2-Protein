/*
 Program author: Analia Trevino-Flitton
 */


import org.biojava.bio.BioException;
import org.biojavax.bio.seq.RichSequence;
import org.biojava.bio.seq.DNATools;
import org.biojava.bio.symbol.SymbolList;
import org.biojavax.bio.seq.RichSequenceIterator;

import java.io.*;
import javax.swing.JFileChooser;



/*
Develop program to translate DNA into protein sequence from all three forward frames
    -Be able to read in 1<+ DNA seq from FASTA file
    -Must set DNA string to length divisible by 3 depending on reading frame
 */



public class BiojavaDNA2Protein {

    //Open file with GUI
    private static JFileChooser fileChooser = new JFileChooser(".");

    public static BufferedReader openFile() {
        int retval = fileChooser.showOpenDialog(null);
        BufferedReader reader = null;

        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to read file " + file.getName());
                e.printStackTrace();
            }
        }
        return reader;
    }


    public static void main(String[] args) throws BioException {
        BufferedReader br = openFile();


        //Read file
        RichSequenceIterator it = RichSequence.IOTools.readFastaDNA(br, null);


        // Read in file send DNA seq to symList w/ createDNA then toProtein
        while (it.hasNext()) {

            RichSequence s = it.nextRichSequence();

            SymbolList dna1Seq = DNATools.createDNA(s.seqString());
            SymbolList prot1 = DNATools.toProtein(dna1Seq);

            SymbolList dna2Seq = DNATools.createDNA(s.seqString().substring(1, s.length()));
            SymbolList prot2 = DNATools.toProtein(dna2Seq);

            SymbolList dna3Seq = DNATools.createDNA(s.seqString().substring(2, s.length()));
            SymbolList prot3 = DNATools.toProtein(dna3Seq);


            //Output
            System.out.println("\nAccession: " + s.getAccession());

            System.out.println("Reading Frame 1:");
            System.out.println("DNA\n" + dna1Seq.seqString() + "\nRNA:\n" + (DNATools.toRNA(s)).seqString());
            System.out.println("Protein:\n" + prot1.seqString());

            System.out.println("\nReading Frame 2:");
            System.out.println("DNA:\n" + dna2Seq.seqString() + "\nRNA:\n" + (DNATools.toRNA(s)).seqString().substring(1, s.length()));
            System.out.println("Protein:\n" + prot2.seqString());

            System.out.println("\nReading Frame 3:");
            System.out.println("DNA:\n" + dna3Seq.seqString() + "\nRNA:\n" + (DNATools.toRNA(s)).seqString().substring(2, s.length()));
            System.out.println("Protein:\n" + prot3.seqString());
        }
        System.out.println("\n\nAdapted By: Analia Treviño-Flitton");

    }
}




