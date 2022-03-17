package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p1 {
	
	private static Queue<Integer> enqueue = new Queue<Integer>();
	private static Queue<Integer> enqueue2 = new Queue<Integer>();
	private static Queue<Character> enqueueChar = new Queue<Character>();
	private static Queue<Integer> dequeue = new Queue<Integer>();
	private static Queue<Integer> dequeue2 = new Queue<Integer>();
	private static Queue<Character> dequeueChar = new Queue<Character>();
	private static int rows = 0;
	private static int cols = 0;
	private static int rooms = 0;
	private static boolean cake = false;
	private static boolean full = false;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		/*how to do the thing: 
		 * right click folder and click properties
		 * click the button thing
		 * for zipping folders right click
		 * click send-to
		 * compressed folder
		 * just do src folder?*/
		
		Scanner scanner;
		File f = new File("map1.txt");
		

			//code that might throw a special error
			scanner = new Scanner(f);
			
			//use next methods to grab the first
			//3 numbers from the file for your
			//map info
			
			rows = scanner.nextInt();
			cols = scanner.nextInt();
			rooms = scanner.nextInt();
			
			char[][] coordinate = new char [rows * rooms][cols];
			
			if (f.getName().contains("Coordinate")) { 
				coordinateBased(scanner);
			} else {
				//take in the # of columns and rows
				//scanner.nextLine();
				
				int count = -1;
				while(scanner.hasNextLine() && count <= rows * rooms) {
					String line = scanner.nextLine();
					
					//use charAt to grab the elements of the map
					//for a given row
					
					for (int i = 0; i < line.length(); i++) {
						coordinate[count][i] = line.charAt(i);
						System.out.print(line.charAt(i));
					}
					System.out.println();
					count++;
				}
				System.out.println();
			}
			
		int startX = 0;
		int startY = 0;
		
		for (int i = 0; i < coordinate.length; i++) {
			for (int j = 0; j < coordinate[0].length; j++) {
				if (coordinate[i][j] == 'K') {
					enqueue.add(i);
					enqueue2.add(j);
					enqueueChar.add('K');
					startX = i;
					startY = j;
				}
			}
		}
		
		boolean[][] visit = new boolean [rows * rooms][cols];
		
		//for () {
			while (cake == false && full == false) {
				visit[startX][startY] = true;
				startX = enqueue.remove();
				startY = enqueue2.remove();
				dequeue.add(startX);
				dequeue2.add(startY);
				dequeueChar.add(enqueueChar.remove());
					if (!(startX - 1 < 0 || coordinate[startX - 1][startY] == '@' || visit[startX - 1][startY] == true)) {
						enqueue.add(startX - 1);
						enqueue2.add(startY);
						enqueueChar.add(coordinate[startX - 1][startY]);
						if (coordinate[startX - 1][startY] == 'C') {
							cake = true;
						}
						visit[startX - 1][startY] = true;
						System.out.println(startX - 1 + " " + startY);
					}
					if (!(startX + 1 >= coordinate.length || coordinate[startX + 1][startY] == '@' || visit[startX + 1][startY] == true)) {
						enqueue.add(startX + 1);
						enqueue2.add(startY);
						enqueueChar.add(coordinate[startX + 1][startY]);
						if (coordinate[startX + 1][startY] == 'C') {
							cake = true;
						}
						visit[startX + 1][startY] = true;
						System.out.println(startX + 1 + " " + startY);
					}
					if (!(startY + 1 >= coordinate[0].length || coordinate[startX][startY + 1] == '@' || visit[startX][startY + 1] == true)) {
						enqueue.add(startX);
						enqueue2.add(startY + 1);
						enqueueChar.add(coordinate[startX][startY + 1]);
						if (coordinate[startX][startY + 1] == 'C') {
							cake = true;
						}
						visit[startX][startY + 1] = true;
						int bob = startY + 1;
						System.out.println(startX + " " + bob);
					}
					if (!(startY - 1 < 0 || coordinate[startX][startY - 1] == '@' || visit[startX][startY - 1] == true)) {
						enqueue.add(startX);
						enqueue2.add(startY - 1);
						enqueueChar.add(coordinate[startX][startY - 1]);
						if (coordinate[startX][startY - 1] == 'C') {
							cake = true;
						}
						visit[startX][startY - 1] = true;
						int bob = startY - 1;
						System.out.println(startX + " " + bob);
					}
				}
				
				System.out.println("found it");
			}
	//}
	
	public static void coordinateBased(Scanner scan) {
		
			char[][] coordinate = new char[rows * rooms][cols];
			
			for (int k = 0; k < rows * rooms; k++) {
				for (int j = 0; j < cols; j++) {
					coordinate[k][j] = '.';
				}
			}
			
			char[] item = new char[rows * cols * rooms];
			int[] row = new int[rows * cols * rooms];
			int changeRoom = 0;
			int count = -1;
			int[] col = new int[rows * cols * rooms];
			
			while(scan.hasNextLine()) {
				
				String line = scan.nextLine();
				//use charAt to grab the elements of the map
				//for a given row
				
				count++;
				
				//fix for numbers greater than 1 digit
				for (int j = 0; j < line.length(); j++) {
					if (j == 0) {
						char Character = line.charAt(j);
						item[count] = Character;
					} else if (j == 2) {
						char Character = line.charAt(j);
						row[count] = Character - 48;
						if (row[count] < row[count - 1] && count >= 1) {
							changeRoom++;
						}
					} else if (j == 4) {
						char Character = line.charAt(j);
						col[count] = Character - 48;
					}
				}
				
				if (changeRoom == 0) {
					coordinate[row[count]][col[count]] = item[count];
				} else {
					coordinate[row[count] + rows * changeRoom][col[count]] = item[count];
				}
			}
			
			coordinate[0][0] = '.';
			
			for (int k = 0; k < rows * rooms; k++) {
				for (int j = 0; j < cols; j++) {
					System.out.print(coordinate[k][j]);
				}
				System.out.println();
			}
		
	}

}
