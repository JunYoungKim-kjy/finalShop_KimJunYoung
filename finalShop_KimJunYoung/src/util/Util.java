package util;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Util {
	public static Scanner sc = new Scanner(System.in);
	public static Random rd = new Random();
	private Util(){}
	public static Util instance = new Util();
	
	public static String getValue(String msg) {
		System.out.printf("▶ %s\n",msg);
		return sc.next();
	}
	public static int getValue(String msg,int start,int end) {
		while(true) {
			try {
				System.out.printf("▶ %s[%d-%d] 입력:",msg,start,end);
				int input = sc.nextInt();
				if(input < start || input > end) {
					System.out.println("입력 범위 오류");
					continue;
				}
				return input;
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
			}
		}
	}

	
	
	
	
	
	
	
	
}
