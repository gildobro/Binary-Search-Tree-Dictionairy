import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void printMenu(){
        System.out.println("\n1: Add New Word");
        System.out.println("2: Delete Word");
        System.out.println("3: Get Meaning");
        System.out.println("4: Dictionary list");
        System.out.println("5: Spell check a text file");
        System.out.println("6: Exit\n");
    }
    public static void main(String[] args) throws IOException {

        //Initialize list of words as object of dictionary class
        Dictionary d1 = new Dictionary();


        //store info from wordsList.txt into an array
        List<String> listOfWords = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader("wordList.txt"));
        String line = bf.readLine();

        while(line != null){
            listOfWords.add(line);
            line = bf.readLine();
        }
        listOfWords.remove(0);
        bf.close();
        String[] wordArray = listOfWords.toArray(new String[0]);

        for(String str : wordArray){
            d1.add(str, "Undefined Word");
        }

        
        String choice;
        do {
            //print menu
            printMenu();
            //Create user choice
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter A Choice: ");
            
            choice = myObj.nextLine();
            switch(choice){
                case "1":
                    Scanner word = new Scanner(System.in);
                    System.out.println("Enter a word: ");
                    String userWord = word.nextLine();
                    Scanner def = new Scanner(System.in);
                    System.out.println("Enter the word's definition: ");
                    String userDefinition = def.nextLine();
                    d1.add(userWord, userDefinition);
                    break;
                case "2":
                    Scanner delete = new Scanner(System.in);
                    System.out.println("Enter a word you wish to delete: ");
                    String deleteWord = delete.nextLine();
                    d1.delete(deleteWord);
                    break;
                case "3":
                    Scanner meaning = new Scanner(System.in);
                    System.out.println("Enter a word of which you want to know the meaning: ");
                    String definitionWord = meaning.nextLine();
                    d1.getMeaning(definitionWord);
                    break;
                case "4":
                    d1.printWordList();
                    break;
                case "5":
                    Boolean wordsNotInDict = false;
                    Scanner in = new Scanner(System.in);
                    System.out.println("What is the name of the file you would like to check?: ");
                    String input = in.nextLine();

                    File file = new File(input);

                    //check if file does not exist
                    if(!file.exists()){
                        System.out.println("\nFile: " + input + " does not exist.\n");
                        break;
                    }
                    BufferedReader newFile = new BufferedReader(new FileReader(input));
                    String fileContent = newFile.readLine();
                    String[] fileWords = fileContent.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                    while(fileContent != null){
                        fileContent = newFile.readLine();
                        
                    }
                    for(String s : fileWords){
                        if(!d1.exists(s)){
                            System.out.println(s);
                            wordsNotInDict = true;
                        }
                    }
                    if(Boolean.FALSE.equals(wordsNotInDict)){
                        System.out.println("All words in the file exist in the dictionary.");
                    }
                    break;
                default:
                    System.out.println("Goodbye");
                    myObj.close();
                    break;
            }
        } while(!choice.equals("6"));



    }
}
