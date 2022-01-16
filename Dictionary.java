/*
Project name : Dictionary
This project aims to create a dictionary which stores keywords and its meanings, using binary search tree data structure.
 */

package com.company;
import java.util.*;

public class Dictionary {

    public static void main(String[] args) {
	// write your code here
        //creating object of class Tree
        Tree t = new Tree();
        Scanner sc = new Scanner(System.in);
        int choice,c=0;
        String word,meaning;

        //applying do while loop to create the menu driven program
        do {
            System.out.println("\n**********MENU**********");
            System.out.println("1.Create a dictionary\n2.Add new word to dictionary\n3.Display dictionary contents\n4.Search a word in dictionary\n5.Update a word\n6.Delete a word");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    //to create the Tree
                    t.create();
                    break;

                case 2 :
                    //to add nodes to the Tree
                    t.addnode();
                    break;

                case 3 :
                    //to display dictionary contents
                    System.out.println("Dictionary contents : ");
                    t.display(t.root);
                    break;

                case 4 :
                    //to search a word in dictionary
                    t.Search();
                    break;

                case 5 :
                    //to update a word
                    t.update();
                    break;

                case 6 :
                    //to delete a word
                    t.delete();
            }

            System.out.println("Press 1 to continue : ");
            c = sc.nextInt();

        }while(c==1);
    }
}

class Node {
    //to declare the nodes and variables
    Node left,right;
    String word,meaning;

    //constructor to initialize the nodes and variables
    Node(String word,String meaning) {
        left = null;
        right = null;
        this.word = word;
        this.meaning = meaning;
    }
}


class Tree {
    Scanner sc = new Scanner(System.in);
    Node root;

    //to initialize the node
    public Tree() {
        root = null;
    }

    //method to create a tree
    public void create() {
        //read the inputs
        System.out.println("Enter the word : ");
        String word = sc.nextLine();
        System.out.println("Enter the meaning of the word entered : ");
        String meaning = sc.nextLine();

        Node node = new Node(word,meaning);
        root = node;
    }

    //method to add new node to tree
    public void addnode() {
        int ch;

        //do while iteration to continue a loop
        do {
            sc.nextLine();

            System.out.println("Enter the word : ");
            String word = sc.nextLine();
            System.out.println("Enter the meaning of the word entered : ");
            String meaning = sc.nextLine();

            Node node = new Node(word,meaning);

            //if root is null, which means tree is not created
            if(root == null) {
                root = node;
            }
            else {
                Node n = root;

                while(n!=null) {
                    if(n.word.compareTo(node.word)>=0) {
                        if(n.left==null) {
                            n.left = node;
                            break;
                        }

                        else {
                            n = n.left;
                        }
                    }

                    else {
                        if(n.right==null) {
                            n.right = node;
                            break;
                        }

                        else {
                            n = n.right;
                        }
                    }
                }
            }

            System.out.println("\nPress 1 to continue adding node : ");
            ch = sc.nextInt();
        }while(ch==1);

    }


    public void display(Node localRoot) {
        if(localRoot!=null){
            display(localRoot.left);
            System.out.println(localRoot.word+" : "+localRoot.meaning);
            display(localRoot.right);
        }

    }


    public void Search() {
        sc.nextLine();
        System.out.println("Enter the word to be searched : ");
        String word = sc.nextLine();
        Node n = root;
        int flag = 0;
        while(n != null){
            if(n.word.compareTo(word) == 0){
                flag = 1;
                break;
            }

            else if(n.word.compareTo(word)>0){
                n = n.left;
            }

            else if(n.word.compareTo(word)<0){
                n = n.right;
            }
        }

        if(flag == 0){
            System.out.println("Word not found");
        }
        else{
            System.out.println("Word found");
            System.out.println(n.word+" : "+n.meaning);
        }
    }


    public void update(){
        sc.nextLine();
        System.out.println("Enter the word to be updated : ");
        String word = sc.nextLine();
        Node n = root;
        int flag = 0;

        while(n != null){
            if(n.word.compareTo(word) == 0){
                flag = 1;
                break;
            }
            else if(n.word.compareTo(word)>0){
                n = n.left;
            }
            else if(n.word.compareTo(word)<0){
                n = n.right;
            }
        }
        if(flag == 0){
            System.out.println("Word not found");
        }
        else{
            System.out.println("Enter the meaning of the word to be updated : ");
            n.meaning = sc.nextLine();
            System.out.println("Word meaning updated");
            System.out.println(n.word+" : "+n.meaning);
        }
    }

    void delete() {
        String word;
        String p,r;
        System.out.println("Enter word you want to delete:");
        word = sc.next();
        Node ptr = root;
        Node parent = root;

        if(ptr == null) {
            System.out.println("Tree is empty!");
        }

        int flag = 0;
        while(ptr != null) {
            if(ptr.word.compareTo(word)>0) {
                parent=ptr;
                flag=0;
                ptr=ptr.left;
            }
            else if(ptr.word.compareTo(word)<0){
                parent=ptr;
                flag=0;
                ptr=ptr.right;
            }
            else if(ptr.word.compareTo(word)==0) {
                flag=1;
                Node p1=root;
                if(ptr.right==null && ptr.left==null) {
                    if(parent.left==ptr) {
                        parent.left=null;
                        flag=1;
                        break;
                    }
                    else if(parent.right==ptr) {

                        parent.right=null;
                        flag=1;
                        break;
                    }
                }
                else if(ptr.right==null && ptr.left!=null) {
                    if(parent.left==ptr) {
                        parent.left=ptr.left;
                        flag=1;
                        break;
                    }
                    else if(parent.right==ptr) {
                        parent.right=ptr.left;
                        flag=1;
                        break;
                    }
                }
                else if(ptr.right!=null && ptr.left==null) {
                    if(parent.left==ptr) {
                        parent.left=ptr.right;
                        flag=1;
                        break;
                    }
                    else if(parent.right==ptr) {
                        flag=1;
                        parent.right=ptr.right;
                        System.out.println("Word deleted!");
                        break;
                    }
                }
                else if(ptr.right!=null && ptr.left!=null) {
                    p1=p1.left;
                    while(p1.right!=null) {
                        parent = p1;
                        p1 = p1.right;
                    }
                    ptr.word = p1.word;
                    ptr.meaning = p1.meaning;
                    if(p1.left != null) {
                        parent.right = p1.left;
                        break;
                    }
                    else {
                        parent.right = null;
                        break;
                    }
                }
            }

        }
        if(flag==1) {
            System.out.println("Word deleted!");

        }
        else if(flag==0) {
            System.out.println("Word not found");
        }
    }

}

/*
OUTPUT

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
1
Enter the word :
annual
Enter the meaning of the word entered :
yearly
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
2

Enter the word :
huge
Enter the meaning of the word entered :
big

Press 1 to continue adding node :
1
Enter the word :
sweet
Enter the meaning of the word entered :
polite

Press 1 to continue adding node :
1
Enter the word :
beautiful
Enter the meaning of the word entered :
pretty

Press 1 to continue adding node :
1
Enter the word :
emotions
Enter the meaning of the word entered :
feelings

Press 1 to continue adding node :
0
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
3
Dictionary contents :
annual : yearly
beautiful : pretty
emotions : feelings
huge : big
sweet : polite
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
4
Enter the word to be searched :
beautiful
Word found
beautiful : pretty
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
5

Enter the word to be updated :
head
Word not found
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
5

Enter the word to be updated :
huge
Enter the meaning of the word to be updated :
vast
Word meaning updated
huge : vast
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
6
Enter word you want to delete:
sweet
Word deleted!
Press 1 to continue :
1

**********MENU**********
1.Create a dictionary
2.Add new word to dictionary
3.Display dictionary contents
4.Search a word in dictionary
5.Update a word
6.Delete a word
3
Dictionary contents :
annual : yearly
beautiful : pretty
emotions : feelings
huge : vast
Press 1 to continue :
0

Process finished with exit code 0

 */