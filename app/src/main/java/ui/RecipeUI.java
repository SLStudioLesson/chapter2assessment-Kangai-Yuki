package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    displayRecipes();// 設問1: 一覧表示機能
                        break;
                    case "2":
                    addNewRecipe(); // 設問2: 新規登録機能
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    
    private void displayRecipes() {
        ArrayList<String> recipes = fileHandler.readRecipes();
        // レシピが空であればメッセージを表示し、処理を終了
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }
    
        System.out.println("Recipes:");
        System.out.println("-----------------------------------");
        // 各レシピについてループ処理
        for (String recipe : recipes) {
            // カンマでレシピ名と材料を分割
            String[] parts = recipe.split(",");
            // 材料を結合して1つの文字列にし、余分な空白を削除
            int commaIndex = recipe.indexOf(",");
            String recipeName = (commaIndex != -1) ? recipe.substring(0, commaIndex) : recipe;
            // レシピ名を取得し、余分な空白を削除
            
             String ingredients = String.join(", ", Arrays.copyOfRange(parts, 1, parts.length)).trim(); // 材料を結合
            System.out.println("Recipe Name: " + recipeName );
            System.out.println("Main Ingredients: " + ingredients);
            System.out.println("-----------------------------------");
        }
    }

    
    private void addNewRecipe() throws IOException {
        System.out.print("Enter recipe name: ");
    String recipeName = reader.readLine(); // ユーザーからレシピ名を入力させる

    System.out.print("Enter main ingredients (comma separated): ");
    String ingredients = reader.readLine(); // ユーザーから材料を入力させる

    fileHandler.addRecipe(recipeName, ingredients); // RecipeFileHandlerを使ってレシピを追加

    System.out.println("Recipe added successfully."); // 成功メッセージを表示
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

