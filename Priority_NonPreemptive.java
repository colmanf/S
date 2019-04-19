import java.util.*;
class ppnode
{
	int pid;
	int bt;
	int at;
	int pr;
	ppnode(){}
}

public class Priority_NonPreemptive {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no of process: ");
        int n = sc.nextInt();
        ppnode p[] = new ppnode[n];
        for(int i=0;i<n;i++)
        {
        	p[i] = new ppnode();
        	System.out.print("Enter process id: ");
        	p[i].pid = sc.nextInt();
        	System.out.print("Enter burst time: ");
        	p[i].bt = sc.nextInt();
        	System.out.print("Enter arrival time: ");
        	p[i].at = sc.nextInt();
        	System.out.print("Enter priority 3: ");
        	p[i].pr = sc.nextInt();
        }



        System.out.println();
        System.out.println("Process Id\tBurst Time\tArrival Time\tPriority");
        for(int i=0;i<n;i++)
        {
        	System.out.println("   "+p[i].pid + "\t\t  " + p[i].bt + "\t\t  " + p[i].at + "\t\t  " + p[i].pr);
        }

        waitingTime(p,n);
        sc.close();
	}

	static void waitingTime(ppnode p[],int n)
	{
	   int[] wtime = new int[n];
	   int time = 0,l = 0,pmin=0;
	   int remainingTime[] = new int[n];
	   int min = Integer.MAX_VALUE;
	   for(int i=0;i<n;i++)
		   remainingTime[i] = p[i].bt;
	   while(l!= n)
	   {
		   min=Integer.MAX_VALUE;
		   for(int i=0;i<n;i++)
		   {
			   if( (p[i].at <= time) && (p[i].pr<min) )
			   {
				   min = p[i].pr;
				   pmin = i;
			   }
		   }
			  	time+=p[pmin].bt;
		   		p[pmin].pr = Integer.MAX_VALUE;
			   	l++;
			   int fin = time;
			   wtime[pmin] = fin - (p[pmin].bt+p[pmin].at);

	   }
	   for(int i = 0;i<n;i++)
	         System.out.println(wtime[i]);
	}

}
