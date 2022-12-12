import java.text.BreakIterator;
import java.util.*;

class Index {
    int i,j;
    Index(int x, int y){
        i = x;
        j = y;
    }

    // to string
    public String toString(){
        String s = "("+i+","+j+")";
        return s;
    }
}

// A linked list NNode
class NNode {
    Index data;
    NNode next;

    public NNode (int x,int y){
        data.i = x;
        data.j = y;
    }

    public NNode(Index ind){
        data = ind;
        // data.i = ind.i;
        // data.j = ind.j;
    }
}

class Stack {
    private NNode top;

    public Stack() {
        this.top = null;
    }

    // Utility function to add an element x in the stack;
    public void push(Index x) {
        NNode newNNode = new NNode(x);
        newNNode.next = top;
        top = newNNode;
    }

    // Utility function to check if the stack is empty or not
    public boolean isEmpty() {
        if(top == null)
            return true;
        else
            return false;
    }

    // Utility function to return top element in stack
    public Index peek() {
        if(this.isEmpty() == false)
            return top.data;
        else
            return null;
    }

    // Utility function to return 2ndlasttop element in stack
    public Index prevIndex() {
        if(top.next != null)
            return top.next.data;

        return new Index(-1,-1);
    }

    // Utility function to pop top element from the stack and check for Stack underflow
    public Index pop() {
        Index ind = top.data;
        if(this.isEmpty() == false){
            top = top.next;
        }
        return ind;
    }
}

class Main {
    static int life =5;
    boolean isAlive(){
        return (life>0) ? true : false;
    }
    Index CheckAlive(Index ind, int[][] bord, ArrayList<Index> list){
        life--;
        if(isAlive()){
            System.out.println("Lost life,,......" + life + " remaining.");
            initTwoD(bord,list);
            bord[ind.i][ind.j] = 2;
            return ind;
        }
        else
            return new Index(7,7);
    }
    public Index goToUp(int[][] bord,Index ind,Stack s, ArrayList<Index> list){

        if(bord[(ind.i-1+5)%5][ind.j] == 1){
            bord[ind.i][ind.j] = 1;
            ind = new Index((ind.i-1+5)%5,ind.j);
            //System.out.println(s.prevIndex()+" is equal to "+ind+" "+ind.equals(s.prevIndex()));
            Index temp = s.prevIndex();
            //Index temp = list.get(list.size()-1);
            if(ind.i == temp.i && ind.j == temp.j){
                list.remove(s.pop());
                return CheckAlive(ind,bord,list);
            }
            else{
                initTwoD(bord,list);
                bord[ind.i][ind.j] = 2;
                s.push(ind);
                list.add(ind);
            }
        }
        else {
            return  CheckAlive(ind,bord,list);
        }
        return ind;
    }

    // Method to go down
    public Index goToDown(int[][] bord,Index ind,Stack s, ArrayList<Index> list){

        if(bord[(ind.i+1)%5][ind.j] == 1){
            ind = new Index((ind.i+1)%5,ind.j);
            Index temp = s.prevIndex();
            //Index temp = list.get(list.size()-1);

            if(ind.i == temp.i && ind.j == temp.j){
                list.remove(s.pop());
                //s.pop();
                return CheckAlive(ind,bord,list);
            }
            else{
                initTwoD(bord,list);
                bord[ind.i][ind.j] = 2;
                s.push(ind);
                list.add(ind);
            }
            System.out.println("complete");
        }
        else {
            return  CheckAlive(ind,bord,list);
        }
        return ind;
    }

    // Method to go goToRight
    public Index goToRight(int[][] bord,Index ind,Stack s, ArrayList<Index> list){

        if(bord[ind.i][(ind.j+1)%5] == 1){
            ind = new Index(ind.i,(ind.j+1)%5);
            Index temp = s.prevIndex();
            //Index temp = list.get(list.size()-1);

            //System.out.println(ind.i +" " + temp.i +" " + ind.j +" " + temp.j);
            if(ind.i == temp.i && ind.j == temp.j){
                list.remove(s.pop());
                //s.pop();
                return CheckAlive(ind,bord,list);
            }
            else{
                initTwoD(bord,list);
                bord[ind.i][ind.j] = 2;
                s.push(ind);
                list.add(ind);
            }
        }
        else {
            return  CheckAlive(ind,bord,list);
        }
        return ind;
    }

    // Method to go Left
    public Index goToLeft(int[][] bord,Index ind,Stack s, ArrayList<Index> list){

        if(bord[ind.i][(ind.j-1+5)%5] == 1){
            ind = new Index(ind.i,(ind.j-1+5)%5);
            Index temp = s.prevIndex();
            //Index temp = list.get(list.size()-1);

            if(ind.i == temp.i && ind.j == temp.j){
                //Index preInd = s.prevIndex();
                list.remove(s.pop());
                //s.pop();
                return CheckAlive(ind,bord,list);
            }
            else{
                initTwoD(bord,list);
                bord[ind.i][ind.j] = 2;
                s.push(ind);
                list.add(ind);
            }

        }
        else {
            return  CheckAlive(ind,bord,list);
        }
        return ind;
    }

    // initilize array randomly
    public void initTwoD(int [][] twoD, ArrayList<Index> visitedInd){
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++){
                twoD[i][j] = ((int)(10* Math.random())%2);
            }
        }

        for(int i=0; i<visitedInd.size();i++){
            int x = visitedInd.get(i).i;
            int y = visitedInd.get(i).j;
            twoD[x][y] =1;
        }

        twoD[4][4] = 1;
    }

    void print(int twoD[][]){
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++){
                System.out.print(twoD[i][j]+" ");
            }
            System.out.println();
        }
    }
    void print(char twoD[][]){
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++){
                System.out.print(twoD[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Main ob = new Main();
 
        // Curr index represents the indes where user is available
        Index currInd = new Index(0,0);

        // Stack that stores visisted index
        Stack s = new Stack();

        // arrayList that stores the the address of visited Index
        ArrayList<Index> list = new ArrayList<>();
        s.push(currInd);
        list.add(currInd);

        int twoD[][] = new int[5][5];
        ob.initTwoD(twoD,list);
        twoD[currInd.i][currInd.j] = 2;

        do{
            ob.print(twoD);
            System.out.println("Current index is ("+currInd.i+","+currInd.j+")");
            System.out.println("\nEnter\n 1-> to goToUp\n 2-> to goToDown\n 3-> to goToLeft\n 4-> to goToRight\n 0-> to stopGame");
            int temp;
            temp = sc.nextInt();
            switch(temp){
                case 1:
                    currInd = ob.goToUp(twoD,currInd,s, list);
                    break;
                case 2:
                    currInd = ob.goToDown(twoD,currInd,s, list);
                    break;
                case 3:
                    currInd = ob.goToLeft(twoD,currInd,s, list);
                    break;
                case 4:
                    currInd = ob.goToRight(twoD,currInd,s, list);
                    break;
            }
            if(temp==0)
                break;
            if(currInd.i == 7 && currInd.j == 7){
                System.out.println("You lost.. :(");
                break;
            }
        }while(currInd.i != 4 || currInd.j != 4);

        if(currInd.i == 4 && currInd.j == 4){
            System.out.println("CONGRATULATIONS, you did it.");
        }

        System.out.println("index stored in array are ");
        System.out.print(list);

        System.out.println("\nBacktrack path");
        while(s.isEmpty() == false){
            s.pop();
        }

    }
}