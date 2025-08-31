package sushil;

public class AssendingOrder {
	
public static void main(String [] args)
{
	int  a[]= {7,9,1,0,-1};
	int ass=0;
	
	
	for(int i=0;i<a.length;i++)
	{
		for(int j=i+1;j<a.length;j++)
		{
			if(a[i]>a[j]) {
			ass=a[i];
			a[i]=a[j];
			a[j]=ass;
			}
		}
//		System.out.println(a[i]);
	}
	System.out.println(a[a.length-1]);
	
}
}
