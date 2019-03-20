import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class WeeklyMeals {
    public static StringBuilder weeklyMeals = new StringBuilder();
    public static ArrayList<String> mealList = new ArrayList<>();
    public static int lines = 0;

    public static void generateMeals(int numOfMeals) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ingredients.txt"));
        // Gets total number of lines in the text
        while (br.readLine() != null) {
            lines++;
        }
        br.close();
        for (int i = 0; i < numOfMeals; i++) {
            getMeal();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("ingredientsWeekly.txt"));
        writer.append(weeklyMeals.toString());
        writer.close();
    }

    public static void getMeal() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ingredients.txt"));
        StringBuilder curMeal = new StringBuilder();
        String line = null;
        int i = 0;
        int choice = (int) (Math.random() * lines);
        try {
            // Gets us to the right line we want
            while (i != choice) {
                line = reader.readLine();
                i++;
            }
            // Finds the next Title
            while (!line.contains("Tags")) {
                line = reader.readLine();
            }
            while (!line.equals(":end")) {
                // Checking for duplicates
                if (line.contains("Title:")) {
                    for (String title : mealList) {
                        if (line.contains(title)) {
                            getMeal();
                            return;
                        }
                    }
                    mealList.add(line.toString());
                }
                curMeal.append(line.toString() + "\n");
                line = reader.readLine();
            }
            curMeal.append(":end\n\n");
            weeklyMeals.append(curMeal);
        } catch (NullPointerException e) {
            //If it reaches the end without finding a Tags: line it will retry
            getMeal();
        }
        reader.close();
    }

}