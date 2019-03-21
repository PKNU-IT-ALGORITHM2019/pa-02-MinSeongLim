import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	ArrayList<Node> node;
	int N;	
	int[] col;
	double min;
	int[] arr;
	long start;
	
	public void exe()
	{
		for(int num = 0; num <7; num++)
		{
			start = System.currentTimeMillis();
			try {
				Scanner sc = new Scanner(new File("input"+ num +".txt"));
				N = sc.nextInt();
				node = new ArrayList<Node>();
				col = new int[N];
				min = 0;
				read(sc,N);				
				method(0,0);
				print();				
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("No File Exist.");
			}
		}
	}
	private void read(Scanner sc, int N)
	{
		for(int i = 0 ; i<N; i++)
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			node.add(new Node(x,y));
		}
	}
	private void print()
	{
		System.out.println(min);
		for(int i = 0; i<N; i++)
			System.out.print((i<N-1)?(arr[i] + " -> "):(arr[i] + " -> " +arr[0]));
		System.out.println("");
	}
	
	private void method(int k, double dis)
	{		
		if(((System.currentTimeMillis()-start)/1000.0) >= 1800 )
			return;
		
		for(int i = 0; i<k-1; i++)
			if(col[i] == col[k-1])				
				return;
		
		if(min!=0 && dis>=min)
			return;
		
		if(k==N)
		{
			dis += get_distance(0, k-1);
			if(min==0 || min>dis)
			{
				min = dis;
				arr = col.clone();
			}
			return;
		}
		
		for(int i = 0 ; i<N; i++)
		{
			col[k] = i;	
			if(k!=0)
				method(k+1,dis + get_distance(k, k-1));
			else
				method(k+1,dis);
		}
		
	}
	
	private double get_distance(int i, int j) {		
		double x = node.get(col[i]).x - node.get(col[j]).x;
		double y = node.get(col[i]).y - node.get(col[j]).y;		
		return Math.sqrt(x*x + y*y);
	}
}
