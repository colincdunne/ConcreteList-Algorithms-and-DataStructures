import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class dbManagement {

    //--------------------------------------------------
    // Attribute
    //--------------------------------------------------
    private myList<myPlayer> items;
    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public dbManagement(int mode){
        if(mode == 1) {
            this.items = new myListArrayList<myPlayer>();
        }
        
        if(mode == 2) {
            this.items = new myListLinkedList<myPlayer>();
        }
        
        if(mode == 3) {
            this.items = new myListDoubleLinkedList<myPlayer>();
        }
    }
    
    //-------------------------------------------------------------------
    // 1. Problem Specific Operation --> Load a MyList from file: load_file
    //-------------------------------------------------------------------
    public void load_file(String s){                
        try {
            //1. We create the file variable
            File my_file = new File(s);
            Scanner sc = new Scanner(my_file);

            //2. We empty the list
            int size = items.my_get_length();
            for (int i = 0; i < size; i++)
                items.my_remove_element(0);

            //3. We fill MyList with the content of the file
            int count = 0;
            while (sc.hasNext()){
                myPlayer player = new myPlayer(sc.next(), sc.nextInt());
                items.my_add_element(count, player);
                count = count + 1;
            }

            //4. We close the scanner
            sc.close();

            System.out.println("Load Operation Completed");
        } 
        catch (Exception e)  {
            System.out.println("Sorry but there is not such file");
        }   
    }

    //-------------------------------------------------------------------
    // 2. Problem Specific Operation --> Display MyFile content: show_elements
    //-------------------------------------------------------------------
    public void show_elements(){
        for(int k = 0; k < items.my_get_length(); k++) {
            items.my_get_element(k).print_info();
        }
    }
    
    //-------------------------------------------------------------------
    // 3. Problem Specific Operation --> Check if element is in MyList: find_element
    //-------------------------------------------------------------------
    public int find_element(String s){
        int k= 0;
        for(int i = 0;i < items.my_get_length();i++)
        {
            String x = items.my_get_element(i).get_name();
            if(x == s)
            {
                k = 1;
            }
        }
        return k;
    }

    //-------------------------------------------------------------------
    // 4. Problem Specific Operation --> Show info of element in MyList: show_info
    //-------------------------------------------------------------------
    public void show_info(String s){
        for(int k = 0; k < items.my_get_length(); k++) {
            if(items.my_get_element(k).get_name().contains(s)) {
                items.my_get_element(k).print_info();
            }
        }
    }
    
    //-------------------------------------------------------------------
    // 5. Problem Specific Operation --> Add new element to MyList: add_by_keyboard
    //-------------------------------------------------------------------
    public void add(String s, int i){
        myPlayer value = new myPlayer(s, i);
        items.my_add_element((items.my_get_length()), value);
        System.out.println("The Player" + s + "has been added to the databade");
    }

    //-------------------------------------------------------------------
    // 6. Problem Specific Operation --> Update element of MyList: update
    //-------------------------------------------------------------------
    public void update(String s, int g){
        boolean found = false;

        for(int k = 0; k < items.my_get_length(); k++)
        {
            if(items.my_get_element(k).get_name().equals(s))
            {
                items.my_get_element(k).set_goals(g);
                found = true;
                System.out.println("Player information has been updated");
            }

            if(k ==(items.my_get_length()-1) && !found)
            {
                System.out.println("No player with the name: " + s + " exists in the database");
            }
        }
    }
    
    //-------------------------------------------------------------------
    // 7. Problem Specific Operation --> Remove element of MyList: remove_element
    //-------------------------------------------------------------------
    public void remove(String s){
        boolean found = false;

        for(int k = 0; k < items.my_get_length(); k++)
        {
            if(items.my_get_element(k).get_name().equals(s))
            {
                items.my_remove_element(k);
                found = true;
                System.out.println("The Player: " + s + " has been removed from the list");
            }

            if(k ==(items.my_get_length()-1) && !found)
            {
                System.out.println("No player with the name: " + s + " exists in the databse");
            }
        }
    }
    
    //-------------------------------------------------------------------
    // 8. Problem Specific Operation --> sort elements of MyList: bubble_sort
    //-------------------------------------------------------------------
    public void bubble_sort(){
        myPlayer temp;

        for(int x = 0; x < items.my_get_length() - 1; x++) {
            for(int y = 0; y < items.my_get_length() - x - 1; y++) {
                 if(items.my_get_element(y).smaller(items.my_get_element(y+1))) {
                     temp = items.my_get_element(y);
                     items.my_add_element(y, items.my_get_element(y+1));
                     items.my_remove_element(y+1);
                     items.my_add_element(y+1, temp);
                     items.my_remove_element(y+2);
                }
            }
        }
    }
    
    //-------------------------------------------------------------------
    // 9. Problem Specific Operation --> Write a MyList to file: write_file
    //-------------------------------------------------------------------
    public void write_file(String s){
        try {
            PrintWriter dataFile = new PrintWriter(s);
            
            for(int k = 0; k < items.my_get_length(); k++) {
                dataFile.println(items.my_get_element(k).get_name() + " " + items.my_get_element(k).get_goals());
            }
            System.out.println("The data has been saved to the file");
            dataFile.close();
        }
        
        catch(Exception e) {
            System.out.println("File does not exist!");
        }
    }       
 }