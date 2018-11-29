package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileReader {
	
	public fileReader(String filePath) {
		File file = new File(filePath);
		Scanner scan;
		
		try {
			scan = new Scanner(file);
			String line;
			
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				
				String[] row = line.split(",");
				
			}
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name." + '\n');
		}
	}
}
