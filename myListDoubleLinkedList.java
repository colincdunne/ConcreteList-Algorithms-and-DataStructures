public class myListDoubleLinkedList<T> implements myList<T> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------
    private myNode<T> head;
    private myNode<T> tail;
    private int num_items;
    //-------------------------------------------------------------------
    // Basic Operation --> Create an empty myList: my_create_empty
    //-------------------------------------------------------------------
    //public myList my_create_empty(){}

    public myListDoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.num_items = my_get_length();
    }

    //-------------------------------------------------------------------
    // Basic Operation --> Get number of integers in myList: my_get_length
    //-------------------------------------------------------------------   
    public int my_get_length(){
        myNode<T> current = this.head;
        int count = 0; 

        while (current != null){
            current = current.getRight();
            count = count + 1;
        }

        return count;
    }

    //-------------------------------------------------------------------
    // Basic Operation --> Get integer of myList at a concrete index: my_get_element
    //-------------------------------------------------------------------
    public T my_get_element(int index) throws myException{
        int count = 0;
        if(index <(num_items/2))
        {
            myNode<T> current = this.head;
            while ((current != null) && (count < index)){
                current = current.getRight();
                count = count + 1;
            }


            if (current != null){
                T element = current.getInfo();
                return element;
            }
            else 
            {
                throw new myException("Invalid Index. The ADT does not have such an Index Position");  
            }
        }
        else
        {
            myNode<T> current = this.tail;
            while ((current != null) && (count > index)){
                current = current.getLeft();
                count = count - 1;
            }


            if (current != null){
                T element = current.getInfo();
                return element;
            }


            else
            {
                throw new myException("Invalid Index. The ADT does not have such an Index Position");  
            }
        }

    }
       
    //-------------------------------------------------------------------
    // Basic Operation --> Add integer to myList at a concrete index: my_add_element 
    //-------------------------------------------------------------------
    public void my_add_element(int index, T element) throws myException{
        int count = 0; 
        if(head != null && tail != null)
        {
            myNode<T> new_node = new myNode<T>(element);
            this.head = new_node;
            this.tail = new_node;

        }
        else if(index <(num_items/2))
        {
            myNode<T> current = this.head;
            myNode<T> previous = null;


            while ((current != null) && (count < index)){
                previous = current;
                current = current.getRight();
                count = count + 1;
            }

            if (count == index){

                myNode<T> new_node = new myNode<T>(element);

                if (index == 0)
                {
                    this.head = new_node;
                    current.setLeft(new_node);
                    new_node.setRight(current);
                }
                else
                {
                    this.head = new_node;
                    current = new_node;
                    current.setLeft(null);  
                }
            }       
            else
                throw new myException("Invalid Index. The ADT does not have such an Index Position");   
        }
        else
        {
            myNode<T> current = this.tail;
            myNode<T> next = null;
            index = num_items - index;

            while ((current != null) && (count > index)){
                next = current;
                current = current.getLeft();
                count = count - 1;
            }

            if (count == index){

                myNode<T> new_node = new myNode<T>(element);

                if (index == 0)
                {
                    //this.tail = new_node;
                    //current.setRight(new_node);
                    //new_node.setLeft(current);
                }
                else
                {
                    this.tail = new_node;
                    next = new_node;
                    current.setRight(null);
                }
            }
            //else
                //throw new myException("Invalid Index. The ADT does not have such an Index Position");
        }
    }


    //-------------------------------------------------------------------
    // Basic Operation --> Remove index of myList: my_remove_element 
    //-------------------------------------------------------------------   
    public void my_remove_element(int index) throws myException{
        int count = 0; 
        if(index <(num_items/2))
        {
            myNode<T> current = this.head;
            myNode<T> previous = null; 

            while ((current != null) && (count < index)){
                previous = current;
                current = current.getRight();
                count = count + 1;
            }

            if (count == index){

                if (previous == null)
                    this.head = current.getRight();
                else
                    previous.setRight(current.getRight());          
            }

            else
                throw new myException("Invalid Index. The ADT does not have such an Index Position");                       
        }   
        else
        {
            myNode<T> current = this.tail;
            myNode<T> next = null; 

            while ((current != null) && (count > index)){
                next = current;
                current = current.getLeft();
                count = count - 1;
            }


            if (count == index){

                if (next == null)
                    this.tail = current.getLeft();
                else
                    next.setLeft(current.getLeft());            
            }

            else
                throw new myException("Invalid Index. The ADT does not have such an Index Position");
        }
    }

}
