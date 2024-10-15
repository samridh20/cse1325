import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Boggle{
    private static List<Board> boards = new ArrayList<>();
    private static List<String> words = new ArrayList<>();
    private static Set<Solution> solutions = new TreeSet<>();
    private static int numberOfBoards = 1; 
    private static int boardSize = 50;     
    private static int numThreads = 1;     
    private static String filename = "words.txt"; 
    private static int verbosity = 0;      
    
    private static AtomicInteger boardCounter = new AtomicInteger(0);
    public static void main(String[] args) {
        try {
            if (args.length > 0 && args[0].equals("-h")) {
                System.err.println(
                    """
                    usage: java Boggle [#boards] [boardSize] [#threads] [wordsFilename] [verboseLevel(0-3)]
                           defaults:       1         50           1       words.txt            0 
                           You may skip any parameters with '-'. To use 4 threads, type: java Boggle - - 4
                           verbosity 0 = # solutions, 1 = threads, 2 = boards & solutions, 3 = details""");
                System.exit(0);
            }
            
            try {
                if (args.length > 0 && !args[0].equals("-")) numberOfBoards = Integer.parseInt(args[0]);
                if (args.length > 1 && !args[1].equals("-")) boardSize = Integer.parseInt(args[1]);
                if (args.length > 2 && !args[2].equals("-")) numThreads = Integer.parseInt(args[2]);
                if (args.length > 3 && !args[3].equals("-")) filename = args[3];
                if (args.length > 4 && !args[4].equals("-")) verbosity = Integer.parseInt(args[4]);
            } 
            catch (Exception e){
                System.err.println("Invalid command line arguments: " + e);
                System.exit(-2);
            }
            try {
                for (int i = 0; i < numberOfBoards; ++i) {
                    boards.add(new Board(boardSize));
                    log("\nBoard " + i + "\n\n" + boards.get(i) + "\n\n", 2);
                }
            } 
            catch (Exception e){
                System.err.println("Unable to generate new Boggle boards:" + e);
                System.exit(-2);
            }

            String s = null;
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                while ((s = br.readLine()) != null) words.add(s);
            } 
            catch (IOException e){
                System.err.println("Unable to read words from file " + filename + ": " + e);
                System.exit(-1);
            }
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numThreads; ++i) {
                final int threadNumber = i;
                Thread t = new Thread(() -> solveBoards(threadNumber));
                threads.add(t);
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }
            for (Solution solution : solutions){
                log(solution.toString(), 2);
            }
           
            System.out.println("\nFound " + solutions.size() + " solutions");
            System.out.printf("Hash is 0x%08X\n", Objects.hash(solutions));

        } catch (Exception e) {
            System.err.println("Unexpected exception (panic): Contact support");
            e.printStackTrace();
            System.exit(-99);
        }
    }

    private static void solveBoards(int threadNumber) {
        while (true) {
            int boardIndex = boardCounter.getAndIncrement(); 
            if (boardIndex >= boards.size()) {
                break; 
            }
            Board board = boards.get(boardIndex);
            Solver solver = new Solver(board, threadNumber, verbosity);
            for (String word : words) {
                Solution solution = solver.solve(word);
                if (solution != null) {
                    synchronized (solutions) {
                        solutions.add(solution); 
                    }
                }
            }
        }
    }

    private static void log(String s, int level) {

        if (verbosity == level) System.out.println(s);
    }
}