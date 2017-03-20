import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyUtils {
	
	public static String getMonthName(String s){
		String mnstr = s.split(" ")[1];
		return mnstr;
	}
	
	//小玩意儿：一年中1一对应的week：何谓1一对应？即周一对应的日期尾号为1。
	public static void show1Mon(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE,1);
		
		Calendar enD = (Calendar) cal.clone();
		enD.add(Calendar.MONTH, 11);
		enD.add(Calendar.DATE, 30);		

		System.out.println(year + "年1一对应表:");
		System.out.println("Month" + "\t" + "Mon" + "\t" + "Tue" + "\t" + "Wed" + "\t" + "Thu" + "\t" + "Fri");
		
		while(!enD.before(cal)){
			if(cal.get(Calendar.DAY_OF_MONTH)==1){
				if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
					System.out.println(getMonthName(cal.getTime().toString()) + "\t" + "0"+cal.get(Calendar.DAY_OF_MONTH) + "\t" + "0"+(cal.get(Calendar.DAY_OF_MONTH)+1) + "\t" + "0"+(cal.get(Calendar.DAY_OF_MONTH)+2) + "\t" + "0"+(cal.get(Calendar.DAY_OF_MONTH)+3) + "\t" + "0"+(cal.get(Calendar.DAY_OF_MONTH)+4));
				}
			}
			if(cal.get(Calendar.DAY_OF_MONTH)==11 || cal.get(Calendar.DAY_OF_MONTH)==21){
				if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
					System.out.println(getMonthName(cal.getTime().toString()) + "\t" + cal.get(Calendar.DAY_OF_MONTH) + "\t" + (cal.get(Calendar.DAY_OF_MONTH)+1) + "\t" + (cal.get(Calendar.DAY_OF_MONTH)+2) + "\t" + (cal.get(Calendar.DAY_OF_MONTH)+3) + "\t" + (cal.get(Calendar.DAY_OF_MONTH)+4));
				}
			}
			cal.add(Calendar.DATE, 1);
		}		
	
	}
	
	//遍历数组元素，一一打印；
	public static void showList(ArrayList<Integer> xlst){
		int ncount = 1;
		for(int item:xlst){
//			System.out.print(item);
			if(ncount%10!=0){
				System.out.print(item + "\t");
			}else{
				System.out.print(item + "\n");
			}
			ncount++;
		}
		System.out.println();
	}
	
	public static void showListStr(ArrayList<String> xlst){
		int ncount = 1;
		for(String item:xlst){
//			System.out.print(item);
			if(ncount%10!=0){
				System.out.print(item + "\t");
			}else{
				System.out.print(item + "\n");
			}
			ncount++;
		}
		System.out.println();
	}
	
	//重载showList方法
	public static void showList(ArrayList<Integer> xlst, int sx){
		int ncount = 1;
		if(sx%2 == 1) {
			Collections.reverse(xlst); //倒序
		}
		for(int item:xlst){
//			System.out.print(item);
			if(ncount%6!=0){
				System.out.print(item + "\t");
			}else{
				System.out.print(item + "\n");
			}
			ncount++;
		}
		System.out.println();
	}
	
	//Fibonacci 给出数列项数，输出到数组；
	public static ArrayList<Integer> getFibonacciList(int f) {
		ArrayList<Integer> fibo = new ArrayList<Integer>();
		if (f == 1){
			fibo.add(1);
		} else if (f == 2){
			fibo.add(1);
			fibo.add(1);
		} else {
			fibo.add(1);
			fibo.add(1);
			for(int i=2; i<f; i++) {
				fibo.add(fibo.get(i-2) + fibo.get(i-1));
			}
		}
		return fibo;
	}
	
	public static int getFiboItemN(int n){
		double j = Math.sqrt(5);
		double d = (double)n;
		int a = (int) (1/j*(Math.pow(((1+j)/2), d) - Math.pow(((1-j)/2), d)));
		// int a = (int) (1/Math.sqrt(5)*(Math.pow(((1+Math.sqrt(5))/2), (double)n) - Math.pow(((1-Math.sqrt(5))/2), (double)n))) // all in one line;
		return a;
	}
	
	//查找小于某个数以内的质数，返回一个列表ArrayList<Integer>；
	public static ArrayList<Integer> getPrime(int lessn){
		if(lessn <= 1){
			System.out.println("Please input a larger number!");
		}
		ArrayList<Integer> pList = new ArrayList<Integer>();
		while(lessn>1){
			boolean isPrime = true;	
			int s = (int)Math.sqrt(lessn);
			for (int j=s; j>1; j--){
				if(lessn%j==0){
					isPrime = false;
					break;
				}
			}
			if(isPrime==true){
				pList.add(lessn);
			}
			lessn = lessn -1;
		}
		return pList;
	}
	
	//按项数获取质数数列；
	public static long getPrimeL(long lessn){
		long nthPrime = 2;
		if(lessn <= 1){
			System.out.println("Please input a larger number!");
		}
		ArrayList<Long> pList = new ArrayList<Long>();
		while(lessn>1){
			boolean isPrime = true;	
			int s = (int)Math.sqrt(lessn);
			for (int j=s; j>1; j--){
				if(lessn%j==0){
					isPrime = false;
					break;
				}
			}
			if(isPrime==true){
				pList.add(lessn);
			}
			lessn = lessn -1;
		}
		return nthPrime;
	}
	
	//判断某个数是否为质数
	public static boolean isPrime(Long pnum){
		boolean isPrime = true;
		int s = (int)Math.sqrt(pnum);
		for(int j=s; j>1; j--){
			if(pnum%j == 0){
				isPrime = false;
//				System.out.println("the number " + pnum + " is NOT a prime!");
				break;
			}
		}
		if(isPrime == true){
//			System.out.println("the number " + pnum + " is a prime!");			
		}
		return isPrime;
	}
	
	/**
	 * 获取某包下所有类
	 * @param packageName 包名
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	public static Set<String> getClassName(String packageName, boolean isRecursion) {
		Set<String> classNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		System.out.println(packagePath);

		URL url = loader.getResource(packagePath);
		System.out.println(url.toString());
		if (url != null) {
			String protocol = url.getProtocol();
			System.out.println(protocol);
			if (protocol.equals("file")) {
				classNames = getClassNameFromDir(url.getPath(), packageName,
						isRecursion);
			} else if (protocol.equals("jar")) {
				JarFile jarFile = null;
				try {
					jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
					System.out.println(jarFile.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (jarFile != null) {
					getClassNameFromJar(jarFile.entries(), packageName,	isRecursion);
					System.out.println("here");
				}
			}
		} else {
			/* 从所有的jar包中查找包名 */
			classNames = getClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
			System.out.println("here" + classNames.toString());
		}
		
		return classNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * @param filePath 文件路径
	 * @param className 类名集合
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
		Set<String> className = new HashSet<String>();
		File file = new File(filePath);
		File[] files = file.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				if (isRecursion) {
					className.addAll(getClassNameFromDir(childFile.getPath(), packageName+"."+childFile.getName(), isRecursion));
				}
			} else {
				String fileName = childFile.getName();
				if (fileName.endsWith(".class") && !fileName.contains("$")) {
					className.add(packageName+ "." + fileName.replace(".class", ""));
				}
			}
		}

		return className;
	}

	
	/**
	 * @param jarEntries
	 * @param packageName
	 * @param isRecursion
	 * @return
	 */
	private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursion){
		Set<String> classNames = new HashSet<String>();
		
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			if(!jarEntry.isDirectory()){
				/*
	             * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug
	             * (FIXME: 先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug)
	             */
				String entryName = jarEntry.getName().replace("/", ".");
				if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
					entryName = entryName.replace(".class", "");
					if(isRecursion){
						classNames.add(entryName);
					} else if(!entryName.replace(packageName+".", "").contains(".")){
						classNames.add(entryName);
					}
				}
			}
		}
		
		return classNames;
	}
	
	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * @param urls URL集合
	 * @param packageName 包路径
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
		Set<String> classNames = new HashSet<String>();
		
		for (int i = 0; i < urls.length; i++) {
			String classPath = urls[i].getPath();
			
			//不必搜索classes文件夹
			if (classPath.endsWith("classes/")) {continue;}

			JarFile jarFile = null;
			try {
				jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jarFile != null) {
				classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
			}
		}
		
		return classNames;
	}
	
	//Fibonacci数列中的质数，以fibo数列的项数为入参
	private static ArrayList<Integer> getPrimeInFiboList(int fbno) {
		ArrayList<Integer> arrlstF = getFibonacciList(fbno);
		ArrayList<Integer> arrlstP = getPrime(arrlstF.get(arrlstF.size()-1));
		ArrayList<Integer> arrlstR = new ArrayList<Integer>();
		for(int item:arrlstF){
			if(arrlstP.contains(item) == true ) {
				arrlstR.add(item);
			}
		}
		return arrlstR;		
	}
	
	//获取质因数的指数
	public static int getPrimePows(int a, int prm){
		int pownum = 0;
		while(a%prm==0){
			pownum += 1;
			a = a/prm;
			if(a%prm!=0){
				break;
			}
		}
		return pownum;
	}
	
	//分解质因数
	//type 1
	public static String getPrimeFactorMultiply(int anum){
		String apf = "";
		String apf1 = "";
		ArrayList<Integer> pflist = new ArrayList<Integer>();
		ArrayList<Integer> factorPlist = getPrime(anum);
		Collections.reverse(factorPlist);
		for(int ipm : factorPlist){
			int tmpfactorpow = getPrimePows(anum, ipm);
			for(int j=tmpfactorpow; j>0; j--){
				apf1 += ipm+",";
				pflist.add(ipm);
			}
			if (tmpfactorpow>1 ) {
				apf += ipm +"^" + tmpfactorpow + "*";
			}else if(tmpfactorpow!=0){
				apf += ipm + "*";
			}
		}
		apf = apf.substring(0, apf.length()-1);
		apf1 = apf1.substring(0, apf1.length()-1);
//		showList(pflist);
		return apf1;
	}
	
	//type 2
	public static ArrayList<Integer> getPrimeFactorList(int anum){
		ArrayList<Integer> factorPlist = getPrime(anum);
		Collections.reverse(factorPlist);
		ArrayList<Integer> pflist = new ArrayList<Integer>();
		for(int ipm : factorPlist){
			int tmpfactorpow = getPrimePows(anum, ipm);
			for(int j=tmpfactorpow; j>0; j--){
				pflist.add(ipm);
			}
		}
		return pflist;
	}
	
	//ArrayList substract
	public static ArrayList<Integer> getSubArrList(ArrayList<Integer> smlist, ArrayList<Integer> bglist){
		ArrayList<Integer> tmpAL = (ArrayList<Integer>) bglist.clone();
		for(int x : smlist){
			if(bglist.contains(x)){
				tmpAL.remove((Object)x);
			}
		}
		return tmpAL;
	}
	
	//n!的最小公倍数
	public static long getSmlstMulitple(int n){
		int tmprs = n;
		int tmprs2 = n;
//		long pretmprs = n;
		while(n>2){
			tmprs2 *= (n-1);
			for(int k=2; k<n; k++){
				if(n%k==0){
					tmprs2 = tmprs2/k;
				}
			}
			n--;
		}
//		pretmprs = tmprs/pretmprs;
		System.out.println("the number tobe repair: " + tmprs2);
		int tmprs1 = 1;
		for(int j=2; j<tmprs; j++){
			tmprs1 *= j;
		}
		return tmprs1;
	}
	
	//1~n，求平方和；
	public static long getSquareSum(int n){
		long tmpsqsum = 0;
		for(int i=1; i<=n; i++){
			tmpsqsum += i*i;
		}
		return tmpsqsum;
	}
	//1~n，求和的平方；
	public static long getSumSquare(int n){
		long tmpsmsqare = 0;
		for(int i=1; i<=n; i++){
			tmpsmsqare += i;
		}
		tmpsmsqare *= tmpsmsqare;
		return tmpsmsqare;
	}
	
	//1~n，平方和与和平方的差；
	public static long getSSDiff(int n){
		long rsltn = getSumSquare(n) - getSquareSum(n);
		return rsltn;
	}
	
	//主程序入口
	public static void main(String args[]) {	
		System.out.println("=====Starting=====");
//		Quiz 008
		String intstr = "731671765313306249192251196744265747423553491949349698352031277450632623957831801698480186947885184385861560789112949495459501737958331952853208805511"
				+ "125406987471585238630507156932909632952274430435576689664895044524452316173185640309871112172238311362229893423380308135336276614282806444486645238749"
				+ "303589072962904915604407723907138105158593079608667017242712188399879790879227492190169972088809377665727333001053367881220235421809751254540594752243"
				+ "525849077116705560136048395864467063244157221553975369781797784617406495514929086256932197846862248283972241375657056057490261407972968652414535100474"
				+ "821663704844031998900088952434506585412275886668811642717147992444292823086346567481391912316282458617866458359124566529476545682848912883142607690042"
				+ "242190226710556263211111093705442175069416589604080719840385096245544436298123098787992724428490918884580156166097919133875499200524063689912560717606"
				+ "0588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
		System.out.println(intstr.length());
		System.out.println(intstr.charAt(0));
		int x1 = Character.getNumericValue(intstr.charAt(0));
		System.out.println(x1);
		

		System.out.println("------------");
		
//		Quiz 010
//		int xxx= 2000000;
//		long sump = 0;
//		for(int j=2; j<=xxx; j++){
//			if(isPrime((long) j)){
//				sump += j;
//			}
//		}
//		System.out.println("the sum of the prime list is: " + sump);		
		
//		Quiz 007
//		By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
//		What is the 10 001st prime number?
//		System.out.println(isPrime(4L));
//		int j=0;
//		int p=0;
//		for(int i=2; i<110000; i++){
//			if(isPrime((long)i)){
//				p = i;
//				j++;
//			}
//			if(j==10001){
//				break;
//			}
//		}
//		System.out.println("100以内，最大的质数等于 " + p + "，这是第 " + j + "个质数");
//		showList(getPrime(11),1);
		
		
//		Quiz 006
//		The sum of the squares of the first ten natural numbers is,
//		12 + 22 + ... + 102 = 385
//		The square of the sum of the first ten natural numbers is,
//		(1 + 2 + ... + 10)2 = 552 = 3025
//		Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
//		Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

		System.out.println(getSquareSum(100));
		System.out.println(getSumSquare(100));
		System.out.println(getSSDiff(100));	

		System.out.println("------------");

//		Quiz 003 
//		The prime factors of 13195 are 5, 7, 13 and 29.
//		What is the largest prime factor of the number 600851475143 ?

//		long ax = 600851475143L;
//		int axp = (int) Math.sqrt(ax);
//		ArrayList<Integer> prmlist = getPrime(axp);
//		ArrayList<Integer> factorsList = new ArrayList<Integer>();
//		if(isPrime(ax)){
//			
//		}
//		for(int pn : prmlist){
//			if(ax%pn == 0){
//				factorsList.add(pn);
//			}
//		}
//		factorsList.add(1);
////		showList(factorsList);
//		System.out.println(factorsList.get(0));
		
//		Quiz 004
		//A palindromic number reads the same both ways. The largest palindrome made
		// from the product of two 2-digit numbers is 9009 = 91 × 99.
		//Find the largest palindrome made from the product of two 3-digit numbers.
		
		// Thinking ----
		//		give a number P; 
		//		get half of the number P(int by up): n; 
		//		from n to 1, not mod calc, use n to divide n; 
		//		if full divided, record n and divede result; 
		//		loop it until n=1; 
		//		judge the two number both are 3-digit number or not; 
		//		if it's true, P is what we want!

		//确定为6位数，要求最大的，则从9开始，9abba9; 分组搜索，一组没有再搜索下一组。如果是最小，则从1开始，1abba1,做个通用的；
		//a也从9往0循环，b也一样，先把9abba9这种形式的回文数枚举展示出来；
		
//		ArrayList<Integer> pldrnumList = new ArrayList<Integer>();
//		int tmpldr = 0;
//		for(int c=9; c>=0; c--){
//			for(int b=9; b>=0; b--){
//				tmpldr = 9*100001 + c*10010 + b*1100;
//				pldrnumList.add(tmpldr);
//			}
//		}
//		showList(pldrnumList);	
//		
//		ArrayList<String> factorStrList = new ArrayList<String>();
//		
//		for(int P : pldrnumList){
//			int tmpi = P/2;
//			while(tmpi>1){
//				if(P%tmpi == 0){
//					int tmpr = P/tmpi;
//					if(tmpr>=100 && tmpr<=999 && tmpi>=100 && tmpi<=999){
//						factorStrList.add(tmpi+"*"+tmpr);						
//					}else if(Math.abs(tmpr-tmpi)<20){
//						factorStrList.add(tmpi+"*"+tmpr);
//					}
//				}
//				tmpi -= 1;
//			}
//		}
//		
//		System.out.println("Sample:");
//		showListStr(factorStrList);	
		
		//Quiz 005 --OK
//		2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
//		What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
		//Thinking:
		//try to use recursion get two numbers smallest multiple. and need another method to get the smallest multiple, to do so, then need to 
		//apart number into prime factors multiply.
		
		long forten = 2520;
		for(int m=11; m<20; m++){
			int ma = (int) (forten%m);
			if(ma != 0){
				if(m%ma==0){
					forten *= m/ma;
				}else{
					forten *= m;					
				}
			}
		}
		System.out.println(forten);
		System.out.println("------------");

		
//		int a = 2520;
//		String apf = "";
//		ArrayList<Integer> factorPlist = getPrime(a);
//		Collections.reverse(factorPlist);
//		for(int ipm : factorPlist){
//			int tmpfactorpow = getPrimePows(a, ipm);
//			if (tmpfactorpow>1 ) {
//				apf += ipm +"^" + tmpfactorpow + "*";
//			}else if(tmpfactorpow!=0){
//				apf += ipm + "*";
//			}
//		}	
//		System.out.println(getPrimePows(72, 8));

//		System.out.println("2520 = " + getPrimeFactorMultiply(2520));
//		System.out.println("16 = " + getPrimeFactorMultiply(16));

//		ArrayList<Integer> apflist = getPrimeFactorList(2520);
//		ArrayList<Integer> bpflist = getPrimeFactorList(16);
//		
//		System.out.println("----------------------------------");
//		ArrayList<Integer> rslist = new ArrayList<Integer>();
//		ArrayList<Integer> apflist1 = (ArrayList<Integer>) apflist.clone();
//		ArrayList<Integer> bpflist1 = (ArrayList<Integer>) bpflist.clone();
//		for(int x : bpflist){
//			if(apflist.contains(x)){
//				rslist.add(x);
//				apflist1.remove((Object)x);
//				bpflist1.remove((Object)x);
//			}
//		}
		
//		showList(apflist);
//		showList(apflist1);
//		showList(bpflist);
//		showList(bpflist1);
//		showList(rslist);
		
//		System.out.println(getSmlstMulitple(10));
		
//		show1Mon(2017);
//		showList(getFibonacciList(20));
//		System.out.println(getFiboItemN(20));
//		System.out.println(getFibonacciList(20).get(19));
//		showList(getPrime(getFibonacciList(12).get(getFibonacciList(12).size()-1)), 1);			
//		showList(getPrimeInFiboList(20),0);		
//		String packageName = "com.jcloud.cap.client.builder";
//		String packageName = "org.objectweb.asm";
//		Set<String> classNames = getClassName(packageName, true);
//		if (classNames != null) {
//			for (String className : classNames) {
//				System.out.println(className);
//			}
//		}else{
//			System.out.println("What?");
//		}		
		
		System.out.println("=====The end!=====");	
	}
}