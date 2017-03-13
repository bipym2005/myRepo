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
	
	//С�������һ����1һ��Ӧ��week����ν1һ��Ӧ������һ��Ӧ������β��Ϊ1��
	public static void show1Mon(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE,1);
		
		Calendar enD = (Calendar) cal.clone();
		enD.add(Calendar.MONTH, 11);
		enD.add(Calendar.DATE, 30);		

		System.out.println(year + "��1һ��Ӧ��:");
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
	
	//��������Ԫ�أ�һһ��ӡ��
	public static void showList(ArrayList<Integer> xlst){
		int ncount = 1;
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
	//����showList����
	public static void showList(ArrayList<Integer> xlst, int sx){
		int ncount = 1;
		if(sx%2 == 1) {
			Collections.reverse(xlst); //����
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
	
	//Fibonacci ����������������������飻
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
	
	//����С��ĳ�������ڵ�����������һ���б�ArrayList<Integer>��
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
	
	/**
	 * ��ȡĳ����������
	 * @param packageName ����
	 * @param isRecursion �Ƿ�����Ӱ�
	 * @return �����������
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
			/* �����е�jar���в��Ұ��� */
			classNames = getClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
			System.out.println("here" + classNames.toString());
		}
		
		return classNames;
	}

	/**
	 * ����Ŀ�ļ���ȡĳ����������
	 * @param filePath �ļ�·��
	 * @param className ��������
	 * @param isRecursion �Ƿ�����Ӱ�
	 * @return �����������
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
	             * ������Ϊ�˷��㣬�Ȱ�"/" ת�� "." ���ж� ".class" ���������ܻ���bug
	             * (FIXME: �Ȱ�"/" ת�� "." ���ж� ".class" ���������ܻ���bug)
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
	 * ������jar�������ð�������ȡ�ð���������
	 * @param urls URL����
	 * @param packageName ��·��
	 * @param isRecursion �Ƿ�����Ӱ�
	 * @return �����������
	 */
	private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
		Set<String> classNames = new HashSet<String>();
		
		for (int i = 0; i < urls.length; i++) {
			String classPath = urls[i].getPath();
			
			//��������classes�ļ���
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
	
	//Fibonacci�����е���������fibo���е�����Ϊ���
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
	
	//���������
	public static void main(String args[]) {	
		System.out.println("=====Starting=====");
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