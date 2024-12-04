import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String SpyCsv = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream SpyCsvStream = null;
        Scanner SpyCsvScanner = null;

        // Open the input file
        SpyCsvStream = new FileInputStream(SpyCsv);
        SpyCsvScanner = new Scanner(SpyCsvStream);

        // ignore first line
        SpyCsvScanner.nextLine();

        // FINISH ME

        ArrayList<SPY> Array = new ArrayList<SPY>();

        String line;
        int count = 0;
        while (SpyCsvScanner.hasNext() && count < numLines) {

            line = SpyCsvScanner.nextLine();
            line = line.trim();

            if (line.isEmpty()) continue;

            //separates by the correct commas (avoids separating by commas that are within quotes.
            String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (p.length != 16 || oneElementIsEmpty(p))
                continue;

            SPY s = new SPY(p[0],
                    p[1],
                    p[2],
                    p[3],
                    p[4],
                    p[5],
                    Double.parseDouble(p[6]),
                    Double.parseDouble(p[7]),
                    Double.parseDouble(p[8]),
                    Double.parseDouble(p[9]),
                    p[10],
                    p[11],
                    p[12],
                    p[13],
                    p[14],
                    Double.parseDouble(p[15]));
            //check this
            Array.add(s);

            count++;

        }

        // FINISH ME

        ArrayList<SPY> NewsArray = new ArrayList<SPY>(Array);
        Collections.shuffle(NewsArray);


        //Insert Time For Sorted Array
        double start = 0;
        double Elapsed = 0;
        for(int i = 0; i < 5; i++){
            ArrayList<SPY> NewArray = new ArrayList<SPY>(Array);
            Collections.sort(NewArray);
            start = System.nanoTime();
            SeparateChainingHashTable<SPY> hash = new SeparateChainingHashTable<SPY>(numLines);

            for(SPY spy: NewArray)
                hash.insert(spy);

            Elapsed += (System.nanoTime() - start);
        }
        Elapsed = Elapsed/5;

        writeToFile("Average Time Elapsed For Insert of a Sorted List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Search Time For Sorted Hash Table
        ArrayList<SPY> NewArray1 = new ArrayList<SPY>(Array);
        Collections.sort(NewArray1);
        SeparateChainingHashTable<SPY> hash1 = new SeparateChainingHashTable<SPY>(numLines);

        for(SPY spy: NewArray1)
            hash1.insert(spy);

        start = System.nanoTime();
        for(int i = 0; i < 5; i++){
            for(SPY spy: NewArray1)
                hash1.contains(spy);
        }
        Elapsed = (System.nanoTime() - start)/5;

        writeToFile("Average Time Elapsed For Search of a Sorted List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Deletion Runtime For Sorted Array
        start = System.nanoTime();
        hash1.makeEmpty();
        Elapsed = System.nanoTime() - start;
        writeToFile("Average Time Elapsed For Deletion of a Sorted List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Insert Time For Shuffled Array

        for(int i = 0; i < 5; i++){
            ArrayList<SPY> NewArray = new ArrayList<SPY>(Array);
            Collections.shuffle(NewArray);
            start = System.nanoTime();
            SeparateChainingHashTable<SPY> hash = new SeparateChainingHashTable<SPY>(numLines);

            for(SPY spy: NewArray)
                hash.insert(spy);

            Elapsed += (System.nanoTime() - start);
        }
        Elapsed = Elapsed/5;

        writeToFile("Average Time Elapsed For Insert of a Shuffled List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Search Time For Shuffled Array
        ArrayList<SPY> NewArray2 = new ArrayList<SPY>(Array);
        Collections.shuffle(NewArray2);
        SeparateChainingHashTable<SPY> hash2 = new SeparateChainingHashTable<SPY>(numLines);

        for(SPY spy: NewArray2)
            hash2.insert(spy);

        start = System.nanoTime();
        for(int i = 0; i < 5; i++){
            for(SPY spy: NewArray2)
                hash2.contains(spy);
        }
        Elapsed = (System.nanoTime() - start)/5;

        writeToFile("Average Time Elapsed For Search of a Shuffled List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Deletion Runtime For Shuffled Array
        start = System.nanoTime();
        hash2.makeEmpty();
        Elapsed = System.nanoTime() - start;
        writeToFile("Average Time Elapsed For Deletion of a Shuffled List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");


        //Insert Time For Reversed Array

        for(int i = 0; i < 5; i++){
            ArrayList<SPY> NewArray = new ArrayList<SPY>(Array);
            Collections.sort(NewArray, Collections.reverseOrder());
            start = System.nanoTime();
            SeparateChainingHashTable<SPY> hash = new SeparateChainingHashTable<SPY>(numLines);

            for(SPY spy: NewArray)
                hash.insert(spy);

            Elapsed += (System.nanoTime() - start);
        }
        Elapsed = Elapsed/5;

        writeToFile("Average Time Elapsed For Insert of a Reversed List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Search Time For Reversed Array
        ArrayList<SPY> NewArray3 = new ArrayList<SPY>(Array);
        Collections.sort(NewArray3, Collections.reverseOrder());
        SeparateChainingHashTable<SPY> hash3 = new SeparateChainingHashTable<SPY>(numLines);

        for(SPY spy: NewArray2)
            hash3.insert(spy);

        start = System.nanoTime();
        for(int i = 0; i < 5; i++){
            for(SPY spy: NewArray3)
                hash3.contains(spy);
        }
        Elapsed = (System.nanoTime() - start)/5;

        writeToFile("Average Time Elapsed For Search of a Reversed List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");

        //Deletion Runtime For Reversed Array
        start = System.nanoTime();
        hash3.makeEmpty();
        Elapsed = System.nanoTime() - start;
        writeToFile("Average Time Elapsed For Deletion of a Reversed List ", "/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");
        writeToFile("Runtime: " + Elapsed + ", number of lines:" + numLines,"/Users/jackperry/IdeaProjects/project-4-JackPerry123/src/analysis.txt");




    }

    public static boolean oneElementIsEmpty(String[] s){
        for (String e : s) {
            if (e.isEmpty())
                return true;
        }
        return false;
    }

    public static void writeToFile(String content, String filePath) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch(IOException e){
            System.out.println("error " + e);
        }

    }
}
