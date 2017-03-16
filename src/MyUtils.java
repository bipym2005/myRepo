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
	
	//判断某个数是否为质数
	public static boolean isPrime(Long pnum){
		boolean isPrime = true;
		int s = (int)Math.sqrt(pnum);
		for(int j=s; j>1; j--){
			if(pnum%j == 0){
				isPrime = false;
				System.out.println("the number " + pnum + " is NOT a prime!");
				break;
			}
		}
		if(isPrime == true){
			System.out.println("the number " + pnum + " is a prime!");			
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
	
	//主程序入口
	public static void main(String args[]) {	
		System.out.println("=====Starting=====");

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
		int a = 72;
		System.out.println("number is: " + a);
		int tms = 1;
		if(a%2 == 0){
			for(int i=0; i<64; i++){
				a = a/2;
				if(a%2 != 0){
					break;
				}
				tms += 1;
			}
			System.out.println("number times tms is: " + (int)Math.pow(2, tms));
			System.out.println("factor times is: " + tms);
		}
		
		int minp = 100*100;
		int maxp = 999*999;
		System.out.println("The min and max palindromic number is: " + minp +" and " + maxp);
		ArrayList<Integer> pldrnumList = new ArrayList<Integer>();
		//确定为6位数，要求最大的，则从9开始，9abba9; 分组搜索，一组没有再搜索下一组。如果是最小，则从1开始，1abba1,做个通用的；
		//a也从9往0循环，b也一样，先把9abba9这种形式的回文数枚举展示出来；
		int tmpldr = 0;
		for(int c=9; c>=8; c--){
			for(int b=9; b>=8; b--){
				tmpldr = 9*100001 + c*10010 + b*1100;
				pldrnumList.add(tmpldr);
			}
		}
		showList(pldrnumList);	
		
		ArrayList<String> factorStrList = new ArrayList<String>();
		
		for(int P : pldrnumList){
			int tmpi = P/2;
			while(tmpi>1){
				if(P%tmpi == 0){
					int tmpr = P/tmpi;
					factorStrList.add(tmpi+"*"+tmpr);
				}
				tmpi -= 1;
			}
			System.out.println("---------------------------------------------");
		}
		
		System.out.println("Sample:");
		showListStr(factorStrList);	
		
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