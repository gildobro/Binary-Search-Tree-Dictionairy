public class Dictionary {
    private BTNode root;


    public Dictionary(){
        root = null;
    }


    //adds a new word to the dictionary  (must use binary search tree)
    public boolean add(String word, String definition){
        word = word.toLowerCase();
        // create new wordinfo object
        WordInfo data = new WordInfo(word, definition);
        BTNode newNode = new BTNode(data);

        //if node already exists, return false
        if(exists(word)){
            System.out.println("This word already exists in the Dictionary.");
            return false;
        }

        if(root==null){
            root = newNode;
            return true;
        }
        BTNode parent;
        BTNode current;
        current = parent = root;
        while ( current != null){
            parent = current;
            if(word.compareTo(current.data.word) < 0){
                //go left
                current = current.left;
            }
            else{
                //go right
                current = current.right;
            }
        }
        if(word.compareTo(parent.data.word) < 0){
            parent.left = newNode;
            return true;
        }
        else{
            parent.right = newNode;
            return true;
        }
    }


    //delete the WordInfo object with the matching word
    public boolean delete(String word){
        word = word.toLowerCase();
        if(!exists(word)){
            System.out.println("\nThis word does not exist.\n");
            return false;
        }
        System.out.println("\nDeleting word " + word + "\n");
        return root == deleteRecursive(root, word);
    }

    public BTNode deleteRecursive(BTNode root, String word){

        if(root == null) return null;
        // search for the item to be deleted
        if(word.compareTo(root.data.word) < 0){
            root.left = deleteRecursive(root.left, word);
        }
        else{
            if(word.compareTo(root.data.word) > 0){
                root.right = deleteRecursive(root.right, word);
            }
            else{
                // have found the node to be deleted
                if(root.left == null){
                    return root.right;
                }
                if(root.right == null){
                    return root.left;
                }
                BTNode inOrderSucc = root.right;
                while(inOrderSucc.left != null){
                    inOrderSucc = inOrderSucc.left;
                }
                root.data = inOrderSucc.data;
                root.right = deleteRecursive(root.right, inOrderSucc.data.word);

            }
        }
        return root;
    }


    //Return true if word is in the dictionary else false
    public boolean exists(String word){
        return search(word) != null;
    }

    public BTNode search(String word){
        BTNode curr = root;
        if(root == null) return null;
        while(curr != null && !curr.data.word.equals(word)){
            if(word.compareTo(curr.data.word) < 0) curr = curr.left;
            else{
                curr = curr.right;
            }
        }
        if(curr == null) return null;
        return curr;
    }



    //Returns number of words in Dictionairy
    public int getCount(){
        if(root != null){
            return root.getSize();
        }else{
            return 0;
        }
    }


    //Return definition of word
    public String getMeaning(String word){
        word = word.toLowerCase();
        BTNode curr = search(word);
        if(curr != null){
            System.out.println("\nWord: " + word + " Definition: " + curr.data.definition);
            return root.data.definition;
        }

            System.out.println("The word: " + word + " does not exist in the dictionary.");
            return null;
    }



    //Returns list of all words stored in the dictionairy in alphabetical order
    public String printWordList(){
        System.out.println("\n");
        wordsInOrder(root);
        return null;
    }

    public void wordsInOrder(BTNode curr){
        if(curr == null) return;
        wordsInOrder(curr.left);
        System.out.println(curr.data.word + " ");
        wordsInOrder(curr.right);
    }


    //Returns the full word and meaning for each WordInfo object in the dictionairy (asc order)
    public void printDictionary(){
        dictInOrder(root);
        System.out.println("");
    }

    public void dictInOrder(BTNode curr){
        if(curr == null) return;
        dictInOrder(curr.left);
        System.out.println("Word: " + curr.data.word + " Definition: " + curr.data.definition );
        dictInOrder(curr.right);
    }
}
