package sushil;

public class ReverseString {
	
	public static void main(String [] args)
	{
		
		System.out.println("SS");
		String s="Sushil Ghugul";
		String rev="";
		
		
		for(int i=s.length()-1;i>=0;i--)
		{
			rev=rev+s.charAt(i);
			
		}
		System.out.println(rev);
		String ss= rev.toLowerCase();
		System.out.println(ss);
		String sss = ss.replace(" ", "");
		System.out.println(sss);
	}

}
