package main.edm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.ExternalData;

public class FileManager {
	private Scanner file;
	private Scanner parser;
	private ExternalData data = new ExternalData();

	private boolean player = false;
	private int dataPassCounter = 0, dataIndice = 0;

	/**
	 * 
	 * @param URL URL to the file to be read
	 */
	public void getFile(String URL) {
		try {
			file = new Scanner(new File(URL));
		} catch (FileNotFoundException e) {
			// TODO(Cheol Woo) Error Logging code here
			e.printStackTrace();
		}
		data.setTag(new ArrayList<String>());
		data.setData(new ArrayList<String>());
	}

	/**
	 * 
	 */
	public ExternalData parseFile() {
		boolean levelFlag = false;
		boolean entityFlag = false;
		while (file.hasNextLine()) {
			String line = file.nextLine();
			if (!line.isEmpty()) {
				parser = new Scanner(line);

				parser.useDelimiter("//s+,//s+");
				// In regular expression this is [" "*","" "*]
				String newLine = parser.next();

				if (newLine.equals("[layout]")) {
					levelFlag = true;
					entityFlag = false;
				} else if (newLine.equals("[entity]")) {
					levelFlag = false;
					entityFlag = true;
				} else {
					if (levelFlag) {
						parseLevel(newLine);
					}
					if (entityFlag) {
						parseEntity(newLine);
					}
				}
			}
		}

		return data;
	}

	private void parseLevel(String line) {
		// Level needs to be 16 by 12
		if (data.getTag().isEmpty()) {
			data.getTag().add("level");
		}

		if (data.getData().isEmpty()) {
			data.getData().add(line);
		} else {
			String temp = data.getData().get(0);
			temp = temp + "," + line;

			data.getData().set(0, temp);
		}
	}

	private void parseEntity(String line) {
		Scanner s = new Scanner(line);
		s.useDelimiter(",");
		while (s.hasNext()) {
			String newLine = s.next();
			// TODO fill the rest of the cases

			if (player) {
				parsePlayer(newLine);
			}

			if (newLine.equals("p_")) {
				data.getTag().add("Player");
				player = true;
			}
		}
		s.close();
	}

	private void parsePlayer(String line) {
		if (dataPassCounter == 0) {
			addToData(line);
			dataIndice = data.getTag().lastIndexOf("Player");
			dataPassCounter++;
		} else if (dataPassCounter < 3) {
			if (dataIndice == data.getData().size()) {
				addToData(line);
			} else {
				concatToData(line, dataIndice);
				if (dataPassCounter >= 3) {
					dataPassCounter = 0;
					player = false;
					return;
				}
			}
			dataPassCounter++;
		}
	}
	
	/*
	 * Adds new element to data array list of ExternalData
	 */
	private void addToData(String line) {
		data.getData().add(line);
	}
	
	/*
	 * Concats a string to data array list element of ExternalData
	 */
	private void concatToData(String line, int dataIndice) {
		String temp = data.getData().get(dataIndice);
		temp = temp + "," + line;
		data.getData().set(dataIndice, line);
	}
}
