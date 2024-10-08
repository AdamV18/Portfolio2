package com.example.portfolio2;

import java.util.Iterator;
import java.util.*;

public class Model {
   /*
   The program here contains code to several exercise
    - main examine complexity of reverse on lists
      for an arraylist it is linear time, for linked list it is quadratic
      because set and get on linked list is linear time and reverse itself is a single loop over list

    -main2 shows how a stack with push and pop is run

    -main1  shows how to iterate over some numbers with a ForEach object
    */

    public static void main(String[] args) {
        int mx=100000000;
        List<Integer> list =new ArrayList<>();
        //List<Integer> list =new LinkedList<>();
        for(int i=1;i<=mx;i++)list.add(i);
        reverse(list);
        //list.forEach(System.out::println);

    }
    static void reverse(List<Integer> list){
        int i1=0,i2=list.size()-1;
        while(i1<i2){
            int temp=list.get(i1);
            list.set(i1,list.get(i2));
            list.set(i2,temp);
            i1++;i2--;
        }
    }



    public static void main2(String[] args) {
        //Stack<String> stack = new Stack<>();
        MyStack stack=new MyStack();
        for(int i=1;i<=10;i++)stack.push(""+i);
        while(!stack.empty())
            System.out.println(stack.pop());

    }
    public static void main1(String[] args) {
        List<Integer> list=List.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println(list);
        //for(Integer i:list) System.out.println(i);
        //list.forEach(System.out::println);
        //Iterator<Integer> itr=nums.iterator();

        for(Integer i:new ForEach(1,10))
            System.out.println(i);

        ForEach nums=new ForEach(1,10);
        Iterator<Integer> itr=nums.iterator();
        while(itr.hasNext()){
            Integer n= itr.next();
            System.out.println(n);
        }
    }
}

class MyStack{
    ArrayList<String> list=new ArrayList<>();
    public void push(String v){list.add(v); }
    public boolean empty(){
        return list.size()==0; }
    public String peek(){
        return list.get(list.size()-1);
    }
    public String pop(){      // something missing here
        return list.remove(list.size()-1);
    }
}


class ForEach  implements Iterable<Integer>{
    int from;int to;
    ForEach(int from,int to){this.from=from;this.to=to;}
    public Iterator<Integer> iterator() {
        return new MyIterator(from,to);
    }
}
class MyIterator implements Iterator<Integer>{
    int from,to;
    MyIterator(int from,int to){this.from=from;this.to=to;}
    public boolean hasNext(){return from<=to;}
    public Integer next(){int h=from;from++; return h;}
}
