
//PageReplacement.java

public class PageReplacement 
{

	public static void main(String[] args)
	{
		
		
		LRU lru=new  LRU();
		//lru.execute();
		
		Optimal optimal=new Optimal();
		optimal.execute();
	}

}




//LRU.java


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LRU 
{
	private Scanner sc;

	public void execute()
	{
		sc = new Scanner(System.in);
		 System.out.println("Enter Number of Pages:");
		 int numPages=sc.nextInt();
		 int pages[]=new int[numPages];
		 System.out.println("Enter Reference String: ");
		 for(int i=0;i<numPages;i++)
		 {
			 pages[i]=sc.nextInt();
		 }
		 
		 System.out.println("Enter Number of Frames");
		 int capacity=sc.nextInt();
		 
		 //To represent set of current pages
		 HashSet<Integer> frames=new HashSet<>(capacity);
		 
		 //To store LRU Indexes of pages //<key=Page,value=index>
		 HashMap<Integer,Integer>  index=new HashMap<>();
		 int pageFaults=0;
		 int hits=0;
		 for(int i=0;i<numPages;i++)
		 {
			 if(frames.size()<capacity) //check if set can hold n=more pages
			 {
				 //IF page not present insert into set and increment pagefault
				 if(!frames.contains(pages[i])) 
				 {
					 frames.add(pages[i]);
					 index.put(pages[i],i); //push current page into queue
					 pageFaults++;
					
					 
				 }
				 else
				 {
					 hits++;
					 index.put(pages[i],i); 
				 }
			 }
			 else //set is full,need replacement
			 {
				 if(!frames.contains(pages[i])) //frame is not present present
				 {
					 int lru=Integer.MAX_VALUE;
					 int val=Integer.MIN_VALUE;
					
					 Iterator<Integer> itr=frames.iterator();
					 while(itr.hasNext())
					 {
						 int temp=itr.next();
						 if(index.get(temp)<lru)
						 {
							 lru=index.get(temp);
							 val=temp;
						 }
					 }
					 
					 frames.remove(val);
					 frames.add(pages[i]);
					 pageFaults++;
					 index.put(pages[i], i);
				 }
				 else //frame is present in set
				 {
					 hits++;
					 index.put(pages[i],i); 
				 }
			 }
			 frames.forEach(System.out::print);
			 System.out.println();
		 }
		 
		 System.out.println("Number of Page Faults : "+pageFaults);
		 System.out.println("Hits:\t"+hits);
		 System.out.println("hit ratio: "+((double)hits/(double)numPages));

	}
}


//Optimal.java


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Optimal {
	private Scanner sc;

	public void execute()
	{
		sc = new Scanner(System.in);
		 System.out.println("Enter Number of Pages:");
		 int numPages=sc.nextInt();
		 int pages[]=new int[numPages];
		 System.out.println("Enter Reference String: ");
		 for(int i=0;i<numPages;i++)
		 {
			 pages[i]=sc.nextInt();
		 }
		 
		 System.out.println("Enter Number of Frames");
		 int capacity=sc.nextInt();
	
		 HashSet<Integer> frames=new HashSet<>();
		 
		 HashMap<Integer, Integer> index=new HashMap<>();
		 int pagefaults=0;
		 int hits=0;
		 for(int i=0;i<numPages;i++)
		 {
			 if(frames.size()<capacity)
			 {
				 if(!frames.contains(pages[i]))
				 {
					 pagefaults++;
					 frames.add(pages[i]);
					 
					 //finding next access of page
					 int farthest=nextIndex(pages, i);
					 
					 index.put(pages[i], farthest);
					 
				 }
				 else
				 {
					 hits++;
					 index.put(pages[i], nextIndex(pages,i));
				 }
			 }
			 else
			 {
				 if(!frames.contains(pages[i]))
				 {
					 int farthest=-1;
					 int val=0;
					 
					 Iterator<Integer> itr=frames.iterator();
					 while(itr.hasNext())
					 {
						 int temp=itr.next();
						 
						 if(index.get(temp)>farthest)
						 {
							 farthest=index.get(temp);
							 val=temp;
						 }
					 }
					 
					 frames.remove(val);
					 index.remove(farthest);
					 frames.add(pages[i]);
					 pagefaults++;
					 int nextUse=nextIndex(pages, i);
					 index.put(pages[i],nextUse);
				 }
				 else
				 {
					 hits++;
					 index.put(pages[i], nextIndex(pages, i));
				 }
				 
			 }
			 frames.forEach(System.out::print);
			 System.out.println();
			
		 }
		 System.out.println("Number of Page Faults : "+pagefaults);
		 System.out.println("Hits:\t"+hits);
		 System.out.println("hit ratio: "+((double)hits/(double)numPages));
	}
	public static int nextIndex(int pages[],int curIndex)
	{
		 int farthest=curIndex;
		 int currentPage=pages[curIndex];
		 int j=farthest;
		 for(j=farthest+1;j<pages.length;j++)
		 {
			 if(pages[j]==currentPage)
			 {
				 farthest=j;
				 return farthest;
			 }

		 }
		 return Integer.MAX_VALUE;
	}
}








