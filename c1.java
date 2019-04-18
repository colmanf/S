
//Schedule.java


public class Schedule {
	public static void main(String [] args)
	{
		FCFS s1=new FCFS();
		//s1.exectue();
		PriorityScheduling s2=new PriorityScheduling();
		//s2.execute();
		RoundRobin s3=new RoundRobin();
		//s3.execute();
		SJF s4=new SJF();
		//s4.execute();
	}
}


//Process.java

import java.util.Comparator;

public class Process {
	int BT,CT,TAT,WT,AT,remBT,priority;
	String ID;
	public Process() {
		
	}
	public Process(String ID,int AT,int BT)
	{
		this.ID=ID;
		this.AT=AT;
		this.BT=BT;
	}
	public Process(String ID,int AT,int BT,int priority)
	{
		this.ID=ID;
		this.AT=AT;
		this.BT=BT;
		this.priority=priority;
	}
	public void display()
	{
		System.out.println(ID+"\t"+BT+"\t"+AT+"\t"+CT+"\t"+TAT+"\t"+WT+"\t"+priority);
	}
}
class sortByArrival implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2) {
		// TODO Auto-generated method stub
		return p1.AT-p2.AT;
	}
	
}
class sortByPriority implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2) {
		// TODO Auto-generated method stub
		return p2.priority-p1.priority;
	}
	
}


//FCFS.java


import java.util.Arrays;
import java.util.Scanner;

public class FCFS {
	private Scanner scan;
	public void exectue()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		int n=scan.nextInt();
		Process process[]=new Process[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter arrival and burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt);
		}
		
		
		Arrays.sort(process,new sortByArrival());
		int sum=0;
		double avgWT=0,avgTAT=0;
		for(int i=0;i<n;i++)
		{
			sum=process[i].CT=sum+process[i].BT;
			process[i].TAT=process[i].CT-process[i].AT;
			process[i].WT=process[i].TAT-process[i].BT;
			
			avgTAT+=process[i].TAT;
			avgWT+=process[i].WT;
		}
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average Waiting Time:"+avgWT);

		System.out.println("Average Turnaround Time:"+avgTAT);
	}
}


//PriorityScheduling.java


import java.util.Arrays;
import java.util.Scanner;

public class PriorityScheduling {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		int n=scan.nextInt();
		Process process[]=new Process[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter Arrival time,burst time and priority");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			int p=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt,p);
		}
		
		Arrays.sort(process,new sortByPriority());
		int time=0;
		double avgWT=0,avgTAT=0;
		
		for(int i=0;i<n;i++)
		{
			time=process[i].CT=time+process[i].BT;
			process[i].TAT=process[i].CT-process[i].AT;
			process[i].WT=process[i].TAT-process[i].BT;
			avgWT+=process[i].WT;
			avgTAT+=process[i].TAT;
		}
		
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average TurnAroundtime:"+avgTAT);

		System.out.println("Average Waiting:"+avgWT);
		
	}
}



//RoundRobin.java


import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter number of process:");
		int n=scan.nextInt();
		Process[] process=new Process[n];
	
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter Arrival time,burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
		
			process[i]=new Process("P"+(i+1),at,bt);
			process[i].remBT=bt;
		}
		
		Arrays.sort(process,new sortByArrival());
		
		System.out.println("Enter quantum:");
		int quantum=scan.nextInt();
		
		int time=0;
		boolean flag;
		double avgTAT=0,avgWT=0;
		while(true)
		{
			flag=true;
			for(int i=0;i<n;i++)
			{
				if((process[i].remBT>0)&&(process[i].AT<=time))
				{
					flag=false;
					if(process[i].remBT>quantum)
					{
						process[i].remBT-=quantum;
						time+=quantum;
					}
					else
					{
						time+=process[i].remBT;
						process[i].remBT=0;
						process[i].CT=time;
						process[i].TAT=process[i].CT-process[i].AT;
						process[i].WT=process[i].TAT-process[i].BT;
						avgWT+=process[i].WT;
						avgTAT+=process[i].TAT;
						process[i].display();
						
					}
				}
			}
			if(flag)
			{
				break;
			}
		}
		
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average TurnAroundtime:"+avgTAT);

		System.out.println("Average Waiting:"+avgWT);
	}
}

//SJF.java


import java.util.Scanner;

public class SJF {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter number of Processes:");
		int n=scan.nextInt();
		Process[] process=new Process[n];
		for(int i =0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter arrival time and burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt);
			process[i].remBT=bt;
		}
		
		double avgTAT=0,avgWT=0;
		int sum=0,time=0;
		boolean done=false,check;
		int minimum=0;
		int min=Integer.MAX_VALUE;
		int count=0;
		
		while(count<n)
		{
			
			check=false;
			for(int i=0;i<n;i++)
			{
				if((process[i].AT<=time)&&(process[i].remBT<=min && process[i].remBT>0))
				{
					minimum=i;
					System.out.println(i);
					min=process[i].remBT;
					check=true;
				}
			}
			if(!check)
			{
				time++;
				continue;
			}
			process[minimum].remBT--;
			min--;
			if(min==0)
			{
				min=Integer.MAX_VALUE;
				count++;
				sum=time+1;
				process[minimum].CT=sum;
				process[minimum].TAT=process[minimum].CT-process[minimum].AT;
				process[minimum].WT=process[minimum].TAT-process[minimum].BT;
				avgWT=avgWT+process[minimum].WT;
				avgTAT=avgTAT+process[minimum].TAT;
			}
			time++;
		}
		avgTAT=(double)avgTAT/n;
		avgWT=(double)avgWT/n;
		System.out.println("Average Waiting Time"+avgWT);
		System.out.println("Average TAT="+avgTAT);
	}
}





