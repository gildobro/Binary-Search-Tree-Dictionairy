public class BTNode {
    public WordInfo data;
    public BTNode left, right;


    public BTNode(WordInfo data){
        this.data = data;
        left = right = null;
    }


    public int getSize(){
        int leftSize = 0, rightSize = 0;

        if(this.left != null){
            leftSize = this.left.getSize();
        }
        if(this.right != null){
            rightSize = this.right.getSize();
        }
        return 1 + leftSize + rightSize;
    }
}
