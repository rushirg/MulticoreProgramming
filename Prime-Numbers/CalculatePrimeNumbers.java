/*
Author: Rushikesh Gaidhani
*/
import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicInteger;

class CalculatePrimeNumbers extends Thread{

	static int totalNumbersToSearch = 100000000;
	static int primeNumbersCount = 0;
	static long primeNumbersSum = 0;
	static volatile int[] primeNumbers = new int[totalNumbersToSearch+1];
	static AtomicInteger varI = new AtomicInteger(2);
	static AtomicInteger varJ = new AtomicInteger(2);
  
	public static void incrementI(){
		while(true){
			int updateValI = varI.get() + 1;
			if(varI.compareAndSet(varI.get(), updateValI)){
				return;
			}
		}
	}
	
	public static void incrementJ(){
		while(true){
			int updateValJ = varJ.get() + varI.get();	
			if(varJ.compareAndSet(varJ.get(), updateValJ)){
				return;
			}
		}
	}	

	public static void calculatePrime(){
		primeNumbers[0] = 1;
		primeNumbers[1] = 1;
		primeNumbers[2] = 0;
		
		for(; varI.get()<=Math.sqrt(totalNumbersToSearch); incrementI()){			
			if(primeNumbers[varI.get()] == 0){
				for(varJ.set(varI.get()*varI.get()); varJ.get()<=totalNumbersToSearch; incrementJ())
					primeNumbers[varJ.get()] = 1;	
			}
		}
	}

	public void run(){
		calculatePrime();
	}

	public static void main(String[] args){

		long startTime = System.currentTimeMillis();
		CalculatePrimeNumbers obj1 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj2 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj3 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj4 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj5 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj6 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj7 = new CalculatePrimeNumbers();
		CalculatePrimeNumbers obj8 = new CalculatePrimeNumbers();

		obj1.start();
		obj2.start();
		obj3.start();
		obj4.start();
		obj5.start();
		obj6.start();
		obj7.start();
		obj8.start();

		try{
			obj1.join();
			obj2.join();
			obj3.join();
			obj4.join();
			obj5.join();
			obj6.join();
			obj7.join();
			obj8.join();
		}catch(Exception e){
			e.printStackTrace();
		} 
		for(int i=2; i<=totalNumbersToSearch; i++){
			if(primeNumbers[i] == 0){
				primeNumbersCount +=  1;
				primeNumbersSum += i;
			}
		}
		int count=0; String maxTenPrimes="";
		for(int i=totalNumbersToSearch; count<=10 ; i-=1){
			if(primeNumbers[i] == 0){
				maxTenPrimes = i + " " + maxTenPrimes;
				count+=1;
			}
		}
		long endTime   = System.currentTimeMillis();
		float totalTime = (endTime - startTime)/1000F;
		String resultString = totalTime + " " + primeNumbersCount + " " + primeNumbersSum + "\n" + maxTenPrimes;  
		try{
			FileWriter fw = new FileWriter("primes.txt");
			fw.write(resultString);
			fw.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
