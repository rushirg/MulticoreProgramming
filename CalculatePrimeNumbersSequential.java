/*
Author: Rushikesh Gaidhani
*/
import java.io.FileWriter;

public class CalculatePrimeNumbersSequential{

	static int totalNumbersToSearch = 100000000;
	static int primeNumbersCount = 0;
	static long primeNumbersSum = 0;
	static int []totalNumbersArray = new int[totalNumbersToSearch+1];

	public static void calculatePrime(){
		totalNumbersArray[0] = 1;
		totalNumbersArray [1] = 1;
		totalNumbersArray[2] = 0;

		for(int i=2; i<=Math.sqrt(totalNumbersToSearch); i+=1){
			for(int j=i*i; j<=totalNumbersToSearch; j+=i){
				if(totalNumbersArray[j] == 0){
					totalNumbersArray[j] = 1;
				}
			}
		} 
	}

	public static void main(String []args){

		long startTime = System.currentTimeMillis();
		CalculatePrimeNumbersSequential obj1 = new CalculatePrimeNumbersSequential();
		obj1.calculatePrime();
		
		for(int i=2; i<=totalNumbersToSearch; i++){
			if(totalNumbersArray[i] == 0){
				primeNumbersCount+=1;
				primeNumbersSum+=i;
			}
		}
		int count=0; String maxTenPrimes="";
		for(int i=totalNumbersToSearch; count<=10 ; i-=1){
			if(totalNumbersArray[i] == 0){
				maxTenPrimes = i + " " + maxTenPrimes;
				count+=1;
			}
		}
		long endTime   = System.currentTimeMillis();
		float totalTime = (endTime - startTime)/1000F;
		String resultString = totalTime + " " + primeNumbersCount + " " + primeNumbersSum + "\n" + maxTenPrimes;  
		try{
			FileWriter fw = new FileWriter("primesSequential.txt");
			fw.write(resultString);
			fw.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
