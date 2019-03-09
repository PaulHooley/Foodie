import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Servings {

    public static void mealPortions(int servings) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ingredients.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt"));
        StringBuilder number = new StringBuilder();
        StringBuilder oldString = new StringBuilder();
        String line = reader.readLine();
        String find = line.toString();
        writer.write(find + "\n");
        while (line != null) {
            find = line.toString();
            line = reader.readLine();
            if (find.contains("Servings:")) {
                int oldValue = find.charAt(10) - 48;
                int removeTill = 0;
                // Save servings size
                // Change it
                // Skip a line
                writer.write("Servings: " + servings + "\n");
                find = line.toString();
                line = reader.readLine();
                writer.write(find + "\n");
                find = line.toString();
                line = reader.readLine();
                // While its still in ingredients we wanna find the number ahead of the
                // ingredient itself
                while (!find.equals(":end")) {
                    number.append(find); // Adds whole line to
                    oldString.append(find);
                    for (int i = 0; i < number.length(); i++) { // Gets number
                        if (!Character.isDigit(number.charAt(i))) {
                            number.delete(i, number.length());
                            removeTill = i;
                            break;
                        }
                    }
                    if (number.toString() == null || number.length() == 0)
                        break;
                    int newValue = Integer.valueOf(number.toString());
                    newValue *= servings;
                    newValue /= oldValue;
                    // Reset old number to be replaced with newValue
                    number.setLength(0);
                    number.append(newValue);
                    // Remove number ahead of oldString
                    oldString.delete(0, removeTill);
                    // New value written to oldString
                    number.append(oldString.toString());
                    writer.write(number.toString() + "\n");
                    number.setLength(0);
                    oldString.setLength(0);
                    find = line.toString();
                    line = reader.readLine();
                }
            }
            writer.write(find + "\n");
        }
        reader.close();
        writer.close();
        File file = new File("tmp.txt");
        File file1 = new File("ingredients.txt");
        file.renameTo(file1);
    }

    public static void individualServing(int servings, String meal) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ingredients.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt"));
        StringBuilder number = new StringBuilder();
        StringBuilder oldString = new StringBuilder();
        String line = reader.readLine();
        String find = line.toString();

        while (line != null) {
            if (find.contains(meal)) {
                find = line.toString();
                line = reader.readLine();
                System.out.println(line.toString());
                writer.write(find + "\n");
                find = line.toString();
                line = reader.readLine();

                if (find.contains("Servings:")) {
                    int oldValue = find.charAt(10) - 48;
                    int removeTill = 0;
                    // Skips servings and Calories lines
                    writer.write("Servings: " + servings + "\n");
                    find = line.toString();
                    line = reader.readLine();
                    writer.write(find + "\n");
                    find = line.toString();
                    line = reader.readLine();
                    // While its still in ingredients we want to find the number ahead of the
                    // ingredient itself
                    while (!find.equals(":end")) {
                        number.append(find); // Adds whole line to
                        oldString.append(find);
                        for (int i = 0; i < number.length(); i++) { // Gets number
                            if (!Character.isDigit(number.charAt(i))) {
                                number.delete(i, number.length());
                                removeTill = i;
                                break;
                            }
                        }
                        if (number.toString() == null)
                            break;
                        int newValue = Integer.valueOf(number.toString());
                        newValue *= servings;
                        newValue /= oldValue;
                        // Reset old number to be replaced with newValue
                        number.setLength(0);
                        number.append(newValue);
                        // Remove number ahead of oldString
                        oldString.delete(0, removeTill);
                        // New value written to oldString
                        number.append(oldString.toString());
                        writer.write(number.toString() + "\n");
                        number.setLength(0);
                        oldString.setLength(0);
                        find = line.toString();
                        line = reader.readLine();
                    }
                }
            }

            find = line.toString();
            line = reader.readLine();
            writer.write(find + "\n");
        }
        reader.close();
        writer.close();
        File file = new File("tmp.txt");
        File file1 = new File("ingredients.txt");
        file.renameTo(file1);
    }
}