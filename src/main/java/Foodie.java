import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

class foodie {
    // Used for all the titles of meals in personal ingredients list
    public static List<String> titles = new ArrayList<>();
    public static String[] tags = { "Gluten Free" };
    public static String[] dislikes = { "" };
    public static final int numberOfServings = 1;
    public static final int numberOfMeals = 7;

    public static void main(String[] args) throws IOException, InterruptedException {
        refreshMeals();
        List<String> ings = getMeal();
        JFrame f = new JFrame();// creating instance of JFrame
        JTextArea b = new JTextArea();
        for (String title : titles) {
            b.append(title);
        }
        b.append("\n");

        for (String ing : ings) {
            b.append(ing);
        }

        JScrollPane scrollPane = new JScrollPane(b);
        f.add(scrollPane);
        f.setVisible(true);
        f.setSize(1000, 500);
    }

    private static void refreshMeals() throws IOException, InterruptedException {
        // Used for updating the masterIngredients file
        updatingMaster();

        // Testing reader
        BufferedWriter write = new BufferedWriter(new FileWriter("ingredients.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("ingredientsMaster.txt"));
        String line = reader.readLine();
        StringBuilder preferences = new StringBuilder();
        for (String tag : tags) {
            preferences.append(tag + " ");
        }
        for (String dis : dislikes) {
            preferences.append(dis + " ");
        }
        String pref = preferences.toString();
        String find = line.toString();
        // If the preferences line is the same it does nothing - no need to update
        // TODO: Reruns through entire code despite trying to ignore it
        if (find.contains(pref)) {
            System.out.println("No changes");
        }
        // Otherwise we itterate through each meal and add all their info to new
        // document
        else {
            write.write("Preferences: ");
            for (String tag : tags) {
                write.write(tag.toString() + " ");
            }
            write.write("\nDislikes: ");
            for (String dis : dislikes) {
                write.write(dis.toString() + " ");
            }
            write.write("\n\n");
            while (line != null) {
                boolean goodToEat = false;
                find = line.toString();
                if (find.contains("Tags:")) {
                    reader.mark(5000);
                    for (String tag : tags) {
                        if (find.contains(tag.toString())) {
                            goodToEat = true;
                        } else {
                            goodToEat = false;
                            break;
                        }
                    }
                }
                // TODO: Allow for dislikes to be removed from file - this code breaks the
                // project
                // if(goodToEat){
                // while(!find.equals(":end")){
                // for(String dis: dislikes){
                // if(find.contains(dis.toString())){
                // goodToEat = true;
                // }else{
                // goodToEat = false;
                // break;
                // }
                // }
                // }
                // reader.reset();
                // }
                // System.out.println("Start");
                if (goodToEat) {
                    while (!find.equals(":end")) {
                        find = line.toString();
                        write.write(find);
                        write.write("\n");
                        line = reader.readLine();
                    }
                    write.write("\n");
                }
                line = reader.readLine();
            }
            write.write("\n");
        }
        reader.close();
        write.close();
        Servings.mealPortions(numberOfServings);
        WeeklyMeals.generateMeals(numberOfMeals);
        // Servings.individualServing(2, "Rice Noodles in Coconut Curry Broth");
    }

    public static void updatingMaster() throws IOException, InterruptedException {
        StringBuilder ingredients = new StringBuilder();
        // Runns through all URLs and places information into string builder
        MealList meal = new MealList();
        Ingredients ings = new Ingredients();
        for (int i = 0; i < meal.listSize(); i++) {
            ingredients.append(ings.browserLaunch(meal.list(i)));
        }
        // Turns Stringbuilder into ArrayList
        List<String> items = new ArrayList<>();
        items.addAll(Arrays.asList((ingredients.toString()).split("\n")));

        BufferedWriter writer = new BufferedWriter(new FileWriter("ingredientsMasterTmp.txt"));
        // Writes each item in the items list to ingredients.txt
        for (String ing : items) {
            writer.write(ing);
            writer.write("\n");
        }
        writer.close();

    }

    public static List<String> getMeal() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ingredients.txt"));
        List<String> mealList = new ArrayList<>();
        StringBuilder ingredients = new StringBuilder();
        String line = reader.readLine();
        String find = line.toString();
        while (line != null) {
            if (find.contains("Tags: ")) {
                // Adds everything from tags to :end omitting end
                while (!find.equals(":end")) {
                    if (find.contains("Title: ")) // if title also adds it to titles arraylist
                        titles.add(find.toString() + "\n");
                    ingredients.append(find + "\n");
                    if (line == null)
                        break;
                    find = line.toString();
                    line = reader.readLine();
                }
                mealList.add(ingredients.toString());
                mealList.add("\n");
                // Once ingredient has been added it is cleared
                ingredients.setLength(0);
            }
            if (line == null)
                break;
            find = line.toString();
            line = reader.readLine();
        }
        reader.close();
        return mealList;
    }
}
