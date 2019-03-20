import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Cart {
    public static void createCart() throws IOException, NullPointerException {
        BufferedReader reader = new BufferedReader(new FileReader("ingredientsWeekly.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("shoppingCart.txt"));
        StringBuilder number = new StringBuilder();
        StringBuilder oldString = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();

        String line = reader.readLine();
        while (line != null) {
            if (line.contains("Calories:")) {
                int removeTill = 0;
                line = reader.readLine(); // Skips it
                while (!line.contains(":end")) {
                    number.append(line); // Adds whole line to
                    oldString.append(line);
                    for (int i = 0; i < number.length(); i++) { // Gets number
                        if (!Character.isDigit(number.charAt(i))) {
                            number.delete(i, number.length());
                            removeTill = i;
                            break;
                        }
                    }
                    if (number.toString() == null || number.length() == 0)
                        break;
                    oldString.delete(0, removeTill);
                    try{
                        //Attempts to add new value to old one
                        Integer itemCount = new Integer(map.get(oldString.toString()));
                        itemCount += Integer.parseInt(number.toString());
                        map.put(oldString.toString(), itemCount);
                    }catch(NullPointerException e){
                        //If no curent value exists creates a new mapping
                        map.put(oldString.toString(), Integer.parseInt(number.toString()));
                    }
                    oldString.setLength(0);
                    removeTill = 0;
                    number.setLength(0);
                    line = reader.readLine();
                }
            }

            line = reader.readLine();
        }
        //Write to shoppingCart.txt
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            writer.write(entry.getValue() + entry.getKey() + "\n");
        }
        reader.close();
        writer.close();
    }
}