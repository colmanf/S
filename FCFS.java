import java.util.*;

class Process
{
	int at;
	int bt;
	int ct;
	int tat;
	int wt;
	int prt;
	boolean bl;
}

public class FCFS
{
	Scanner cin = new Scanner(System.in);
	int q;
	Process p[];

	public void input()
	{
		q=0;
		int i;

		System.out.println("Enter Total Processes : ");
		q = cin.nextInt();

		p = new Process[q];

		for(i=0;i<q;i++)
		{
			p[i] = new Process();
			p[i].at = 0;
			p[i].bt = 0;
			p[i].tat = 0;
			p[i].wt = 0;
			p[i].ct = 0;
			p[i].bl = false;
		}

		for(i=0;i<q;i++)
		{
			System.out.println("AT for "+(i+1)+"th process : ");
			p[i].at = cin.nextInt();
			System.out.println("BT for "+(i+1)+"th process : ");
			p[i].bt = cin.nextInt();
			/*System.out.println("PRT for "+(i+1)+"th process : ");
			p[i].prt = cin.nextInt();*/
		}

		//fcfs();
		sjf_p();
		//priority();
	}



	/*SJF*/
	public void sjf_np()
	{
		boolean a = true;
		int st=0,tot=0;

		while(true)
		{
			int c = q;
			int min=999;

			if (tot == q)
				break;

			for (int i=0; i<q; i++)
			{
				if ((p[i].at <= st) && (p[i].bl == false) && (p[i].bt < min))
				{
					min=p[i].bt;
					c=i;
				}
			}

			/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
			if (c==q)
				st++;
			else
			{
				p[c].ct = st + p[c].bt;
				st += p[c].bt;
				p[c].tat = p[c].ct - p[c].at;
				p[c].wt = p[c].tat-p[c].bt;
				p[c].bl=true;
				tot++;
			}
		}

		print();
	}




	/*SJF*/
	public void sjf_p()
	{
		boolean a = true;
		int st=0,tot=0;
		int i;

		int k[] = new int[q];

		for(i=0;i<q;i++)
		{
			k[i] = p[i].bt;
		}

		while(true)
		{
	    	int min=99,c=q;

	    	if (tot==q)
	    		break;

	    	for (i=0;i<q;i++)
	    	{
	    		if ((p[i].at<=st) && (p[i].bl==false) && (p[i].bt<min))
	    		{
	    			min=p[i].bt;
	    			c=i;
	    		}
	    	}

	    	if (c==q)
	    		st++;
	    	else
	    	{
	    		p[c].bt--;
	    		st++;
	    		if (p[c].bt==0)
	    		{
	    			p[c].ct= st;
	    			p[c].bl=true;
	    			tot++;
	    		}
	    	}
	    }

		for(i=0;i<q;i++)
		{
			p[i].tat = p[i].ct - p[i].at;
			p[i].wt = p[i].tat - k[i];
		}

		print();
	}


	public void fcfs()
	{
		int i,j;
		int swap = 0;

		for(i=0;i<q;i++)
		{
			for(j=i+1;j<q;j++)
			{
				if(p[i].at>p[j].at)
				{
					swap = p[i].at;
					p[i].at = p[j].at;
					p[j].at = swap;

					swap = p[i].bt;
					p[i].bt = p[j].bt;
					p[j].bt = swap;
				}
			}
		}

		p[0].ct = p[0].bt+p[0].at;

		for(i=1;i<q;i++)
		{
			if(p[i].at < p[i-1].ct)
				p[i].ct = p[i-1].ct + p[i].bt;
			else
				p[i].ct = p[i].bt + p[i].at;
		}

		for(i=0;i<q;i++)
		{
			p[i].tat = p[i].ct - p[i].at;
		}

		for(i=0;i<q;i++)
		{
			p[i].wt = p[i].tat - p[i].bt;
		}

		print();
	}




	public void print()
	{
		int i;

		System.out.println("\nA.T."+"\t"+"B.T."+"\t"+"C.T."+"\t"+"T.A.T."+"\t"+"W.T.\n");

		for(i=0;i<q;i++)
		{
			System.out.println(p[i].at+"\t"+p[i].bt+"\t"+p[i].ct+"\t"+p[i].tat+"\t"+p[i].wt);
		}
	}


	public static void main(String args[])
	{
		FCFS f = new FCFS();
		f.input();
	}
}
