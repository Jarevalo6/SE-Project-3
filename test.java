package package1;

import java.io.*;
import java.util.*;

public class test {
	static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
	input();
	}
	
	public static void input() throws IOException {
		System.out.println("Would you like to use 'xor' or 'negation'?");
		String request = console.nextLine();		//Takes user input
		if(request.equalsIgnoreCase("xor")) {
			xor();
		}
		else if (request.equalsIgnoreCase("negation")) {
			negate();
		}
		else {
			System.out.println("Sorry, the options are 'xor' and 'negation'");
			input();
		}
	}
	
	public static void xor() throws IOException {
		FileInputStream in = null;			//Our three ByteStreams
		FileOutputStream out = null;
		FileInputStream key = null;
		try {
			in = new FileInputStream("out.txt");		//This is where the input, output, and key are specified
			out = new FileOutputStream("xordec.txt");
			key = new FileInputStream("key.txt");
			
			int c;
			int k;
			int count = 0;			//Keeps a count of how many times the following loop is run
			
			while((c = in.read()) != -1) {
				k = key.read();
				if(k == -1) {			//This part allows us to have keys of various sizes (it seems multiples of 32 bits)
					key.close();
					key = new FileInputStream("key.txt");
					k = key.read();
				}
				System.out.println("C before: " + Integer.toBinaryString(c));
				System.out.println("Value of K: " + Integer.toBinaryString(k));
				c = c ^ k;						//Here is where the XOR actually happens
				System.out.println("C after: " + Integer.toBinaryString(c));
				System.out.println("Count is: " + count);
				out.write(c);
				count++;
			}
		} finally {
			in.close();
			out.close();
			key.close();
		}
	}
	
	public static void negate() throws IOException {
		FileInputStream in = null;			//Our two ByteStreams
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream("negout.txt");
			out = new FileOutputStream("negdec.txt");
			
			int c;
			int count = 0;
			
			while((c = in.read()) != -1) {
				System.out.println("C before: " + Integer.toBinaryString(c));
				c = ~c;						//This is our negation. It flips each bit so we don't need a key.
				System.out.println("C after: " + Integer.toBinaryString(c));
				System.out.println("Count is: " + count);
				out.write(c);
				count++;
			}
		} finally {
			in.close();
			out.close();
		}
	}
}
