import java.io.Console;
import java.util.Random;
class pk
{

/****************************/
/****guess number with pc****/
/****written by Lin**********/
/****2015/1/2-2015/1/3*******/
/****************************/

	public static void main(String[] arg)
	{
	/*****nag*****/
	System.out.println("");	//space
	System.out.println("Hello everyone! This is a game.");
	System.out.println("You will game with your computer.");
	System.out.println("The system will give a 4-digits number and you need to guess it.");
	System.out.println("This number is composed by 4 different figures.");
	System.out.println("You must think it is very difficult because the number is a lot!");
	System.out.println("But don't worry, the system will give you a hint.");
	System.out.println("After guessing a number, the system will give you A & B.");
	System.out.println("A represents correct, and B represents number correct, location error.");
	System.out.println("Now, enjoy this game:");
	System.out.println("");	//space
	
	/*****make ans*****/
	String ans ;
	do
		{ ans = String.valueOf( (int)(1022+Math.random()*8855) ); }
	while
		( CheckSame(ans)== true );
	
		System.out.println("ans="+ans);  //cheat
		
	/*****decide start*****/
	Console cs = System.console();
	String con = cs.readLine("Start: Pc or Player or Random \nInput: 1 or 2 or 3\n");
	int cf ;
	cf = 0;	 //initialized**
				 //cf=con-fac
				 //%2=0->Pc guess
				 //%2=1->Player guess
	boolean CF ;
	CF = false ;
	try
	{
	int c = Integer.parseInt( con );
	if( c==1 )
		{ cf=0 ; }
	else if( c==2 )
		{ cf=1 ; }
	else if( c==3 )
		{
		int C = (int)(1+Math.random()*2);
		if( C==1 )
			{ cf=0; }
		else if( C==2 )
			{ cf=1; }
		}
	else
		{
		int CC = (int)(1+Math.random()*2);
		if( CC==1 )
			{ cf=0; }
		else if( CC==2 )
			{ cf=1; }
		}
	}
	catch(Exception e)
	{
	int CCC = (int)(1+Math.random()*2);
	if( CCC==1 )
		{ cf=0; }
	else if( CCC==2 )
		{ cf=1; }
	}		
			
	/*****make ans*****/
	
	//pc guess
	int[] pcgusa = new int[ 4536 ];//original array
	int[] pcgusb = new int[ 4536 ];
	int cnta = 0;
	int cntb = 0;
	int pcgus= 0;	//initialized**
	for(int i=1023; i<=9876; i++)
	{
    if( CheckSame ( String.valueOf(i) ) == false )
		{ pcgusa[ cnta ] = i; cnta++; }		
    }

	//Player guess
	String plg;
	
	//cal-AB
	AB cab = new AB();	//plg&array
	AB cab2 = new AB();	//pcgus&array
	AB cab3 = new AB();	//pcgus&ans
	AB plcab = new AB();//ans&&plg
	
	do
	{
	//pc guess&cal-AB
	if(cf%2==0)
		{
		pcgus = pcgusa[ RandomMToN(0,cnta-1) ];
		System.out.println( "Pc guess : "+pcgus );
		cntb = 0 ;
		Console csa = System.console();
		String cona = csa.readLine("A:");
		cab3.A = Integer.parseInt(cona);
		Console csb = System.console();
		String conb = csb.readLine("B:");
		cab3.B = Integer.parseInt(conb);
		for( int i=0; i<cnta; i++ )
			{
			cab2 = CountAB( String.valueOf(pcgus), String.valueOf(pcgusa[i]) );
			if( (cab3.A==cab2.A)&&(cab3.B==cab2.B) )
				{ pcgusb[ cntb ] = pcgusa[i]; cntb++; }
			}
		cnta = cntb;
		System.out.println( cab3.A+"A, "+cab3.B+"B." );
		//*****Copy pcgusb to pcgusa***** 
		for( int i=0; i<cntb; i++ )
			{ pcgusa[i]=pcgusb[i]; }
		if(cab3.A==4)
			{ CF = true; }
		}
		
		//player guess&cal-AB
	else if(cf%2==1)
		{
		plg = GuessInput("Player guess : ");
		plcab = CountAB(ans,plg);
		System.out.println( plcab.A+"A, "+plcab.B+"B." );
		if(plcab.A==4)
			{ CF = true; }
		//pc cal-AB in player guess
	/*	cntb = 0 ;
		//create new 
		for( int i=0; i<cnta; i++ )
			{
			cab = CountAB( plg, String.valueOf(pcgusa[i]) );
			if( (plcab.A==cab.A)&&(plcab.B==cab.B) )
				{ pcgusb[ cntb ] = pcgusa[i]; cntb++; }
			}
		cnta = cntb;
		//new enter old
		for( int i=0; i<cntb; i++ )
			{ pcgusa[i]=pcgusb[i]; }*/
		}
		cf = cf + 1 ;
	}
	while( CF==false );
	
	//announce the winner
	if( cf%2==1 )
		{System.out.println("The winner is Pc.");}
	else if( cf%2==0 )
		{System.out.println("The winner is Player.");}	
	}
	
	
	
	//functions

	static boolean CheckSame( String s )
    {
       int k = s.length();
	   String z1, z2;
	   for( int i=0; i<=(k-2); i++ )
	   {
	       z1 = s.substring( i,i+1 );
		   z2 = s.substring( i+1,k );
	       if( z2.indexOf(z1)>=0 )
		   { return( true ); }		  
	   }
	   return( false );
    }
	
    static class AB
	{ int A; int B; }	
	
	static AB CountAB( String s1, String s2 )
	{
	   int i,k;  String z;
	   AB p = new AB();
	   p.A = 0;  p.B = 0;
	   
	   for( i=0; i<4; i++ )
	   {
		   z = s2.substring(i,i+1);
		   k = s1.indexOf(z);
	       if( k==i ) {  p.A++;  }
	       else if( k>=0 )  {  p.B++;  } 
	   }
	   return( p );
	}

    static int Random1ToN( int n )
	{ return(  1+(int)(Math.random()*n) ); }
	
	static int RandomMToN( int m, int n )
	{ 
	   if( m>n ) {  int k=m; m=n; n=k;  } 
       return( (m-1)+Random1ToN( n-m+1 ) ); 
	}	
	
	static String InputStr( String msg )
	{
        Console cs = System.console();	
        String z;	
	    z = cs.readLine(msg);
        return( z );		
	}
	
	static String GuessInput(String msg)
	{
		boolean tt = true;
		String Gus; int k;
		do
		{
			do
			{
				Gus = InputStr(msg);
			}while( (Gus.length()!=4)||(CheckSame( Gus )==true) );
			tt = true;
			try
			{  k = Integer.parseInt( Gus ); }
			catch(Exception e )
			{  tt = false; }
		}while( tt==false );		
	    return( Gus );	
	}

}