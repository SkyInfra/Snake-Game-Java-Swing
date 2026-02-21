import java.io.*;

public class History {
    private final String FILE_PATH = "scores.txt";
    private java.util.List<Record> topScores = new java.util.ArrayList<>();

    public History() {
        loadFromFile();
    }

    public void addRecord(int score, int speed, String name, String map, String difficulty) {
        topScores.add(new Record(score, speed, name, map, difficulty));
        topScores.sort((a, b) -> b.score - a.score);
        if (topScores.size() > 5) topScores = topScores.subList(0, 5);
        saveToFile();
    }

    public java.util.List<Record> getTopScores() {
        return topScores;
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            topScores.clear();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int score = Integer.parseInt(parts[0]);
                    int speed = Integer.parseInt(parts[1]);
                    String name = parts[2];
                    String map = parts[3];
                    String difficulty = parts[4];
                    topScores.add(new Record(score, speed, name, map, difficulty));
                }
            }
            topScores.sort((a, b) -> b.score - a.score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Record r : topScores) {
                bw.write(String.format("%d,%d,%s,%s,%s%n", r.score, r.initialSpeed, r.name, r.map, r.difficulty));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Record {
        int score;
        int initialSpeed;
        String name;
        String map;
        String difficulty;

        public Record(int score, int initialSpeed, String name, String map, String difficulty) {
            this.score = score;
            this.initialSpeed = initialSpeed;
            this.name = name;
            this.map = map;
            this.difficulty = difficulty;
        }
    }
}