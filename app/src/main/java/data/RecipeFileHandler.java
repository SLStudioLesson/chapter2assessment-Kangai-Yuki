package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readRecipes() {
        // レシピを格納するためのArrayListを作成
        ArrayList<String> recipes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // ファイルの各行を読み込むループ
            while ((line = br.readLine()) != null) {
                recipes.add(line);
            }
        } catch (IOException e) {
             // IOExceptionが発生した場合、エラーメッセージをコンソールに表示
            System.out.println("Error reading file: " + e.getMessage());
        }
        return recipes;
    }

    
    public void addRecipe(String recipeName, String ingredients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) { // 追記モードでファイルを開く
            bw.write(recipeName + "," + ingredients); // レシピ名と材料をカンマで結合して書き込む
            bw.newLine(); // 新しい行を追加
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage()); // エラーメッセージを表示
        }
    }
}
    
