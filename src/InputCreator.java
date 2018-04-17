
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InputCreator {
    private final static String fileName = "inputs";
    private final static int wordSize = 32;
    private static int inputNumber;
    private String[] words;
    private boolean created;

    public InputCreator() {
        words = new String[100];
        created = false;
        File input = new File(fileName);
        if (!input.exists() || !input.isDirectory())
            input.mkdir();
        inputNumber = input.list().length;
    }

    private String addressPlus(String string) {
        int i = string.length() - 1;
        StringBuilder stringBuilder = new StringBuilder(string);
        while (stringBuilder.charAt(i) == '1') {
            stringBuilder.setCharAt(i, '0');
            i--;
        }
        stringBuilder.setCharAt(i, '1');
        return stringBuilder.toString();
    }

    public void create() {

        Random random = new Random();
        for (int i = 0; i < 20; i++) {

            StringBuilder wordBuilder = new StringBuilder();

            for (int j = 0; j < wordSize; j++) {
                int x = Math.abs(random.nextInt() % 10);
                if (x > 5)
                    wordBuilder.append('0');
                else
                    wordBuilder.append('1');
            }

            String str = wordBuilder.toString();
            for (int j = i * 5; j < ((i * 5) + 5); j++) {
                words[j] = addressPlus(str);
                str = words[j];
            }


        }

        created = true;

    }


    private boolean writeToFile(int fileNumber) {

        if (fileNumber > 5 || fileNumber < 1)
            return false;
        if (!created)
            return false;
        File file = new File("inputs/input" + fileNumber + ".txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        FileWriter fileWriter;
        BufferedWriter writer;
        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < 100; i++) {
                fileWriter.write(words[i]);
                fileWriter.write('\n');
            }
            Random random = new Random();
            boolean[] wrote = new boolean[10];
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 10; j++)
                    wrote[j] = false;

                for (int j = 0; j < 10; j++) {
                    int k = Math.abs(random.nextInt() % 10);
                    while (wrote[k])
                        k = Math.abs(random.nextInt() % 10);
                    for (int l = 10 * k; l < 10 * (k + 1); l++) {
                        fileWriter.write(words[l]);
                        fileWriter.write('\n');
                    }
                    wrote[k] = true;
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error Writing in File!");
        }


        return true;
    }


    public void createInputFiles() {

        writeToFile(1);
        writeToFile(2);
        writeToFile(3);
        writeToFile(4);
        writeToFile(5);
    }
}