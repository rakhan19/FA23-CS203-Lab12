import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Encrypter {

	private int shift;
	private String encrypted;
	static ArrayList<Character> decrypt = new ArrayList<Character>();
	static ArrayList<Character> encrypt = new ArrayList<Character>();

	/**
	 * Default Constructor
	 */
	public Encrypter() {
		this.shift = 1;
		this.encrypted = "";
	}

	/**
	 * Non-default Constructor
	 * 
	 * @param s - custom shift amount
	 */
	public Encrypter(int s) {
		this.shift = s;
		this.encrypted = "";
	}

	/**
	 * Encrypts the content of a file and writes the result to another file.
	 *
	 * @param inputFilePath     the path to the file containing the text to be
	 *                          encrypted
	 * @param encryptedFilePath the path to the file where the encrypted text will
	 *                          be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {

		String text = readFile(inputFilePath);
		String wantedmessage = "";
		int i = 0;
		while (i < text.length()) {
			char tempstr = text.charAt(i);
			if (Character.isLetter(tempstr)) {
				if (Character.isUpperCase(tempstr)) {
					int numervalue = ((int) tempstr) - 64;
					if (numervalue - this.shift <= 0) {
						int updatedvalue = numervalue + 26 - this.shift;
						char adjvalue = (char) (updatedvalue + 64);
						encrypt.add(adjvalue);
						i++;
					} else {
						char adjvalue = (char) (((numervalue - this.shift) % 26) + 64);
						encrypt.add(adjvalue);
						i++;
					}
				} else {
					int numervalue = ((int) tempstr) - 96;
					if (numervalue - this.shift <= 0) {
						int updatedvalue = numervalue + 26 - this.shift;
						char adjvalue = (char) (updatedvalue + 96);
						encrypt.add(adjvalue);
						i++;
					} else {
						char adjvalue = (char) (((numervalue - this.shift) % 26) + 96);
						encrypt.add(adjvalue);
						i++;
					}
				}
			} else {
				encrypt.add(text.charAt(i));
				i++;
			}
		}
		for (int j = 0; j < encrypt.size(); j++) {
			String current = Character.toString(encrypt.get(j));
			wantedmessage += current;
		}
		writeFile(wantedmessage, encryptedFilePath);
	}

	/**
	 * Decrypts the content of an encrypted file and writes the result to another
	 * file.
	 *
	 * @param messageFilePath   the path to the file containing the encrypted text
	 * @param decryptedFilePath the path to the file where the decrypted text will
	 *                          be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
		String text = readFile(messageFilePath);
		String wantedmessage = "";
		int i = 0;
		while (i < text.length()) {
			char tempstr = text.charAt(i);
			if (Character.isLetter(tempstr)) {
				if (Character.isUpperCase(tempstr)) {
					int numervalue = ((int) tempstr) - 64;
					if (numervalue - this.shift <= 0) {
						int updatedvalue = numervalue + 26 - this.shift;
						char adjvalue = (char) (updatedvalue + 64);
						decrypt.add(adjvalue);
						i++;
					} else {
						char adjvalue = (char) (((numervalue - this.shift) % 26) + 64);
						decrypt.add(adjvalue);
						i++;
					}
				} else {
					int numervalue = ((int) tempstr) - 96;
					if (numervalue - this.shift <= 0) {
						int updatedvalue = numervalue + 26 - this.shift;
						char adjvalue = (char) (updatedvalue + 96);
						decrypt.add(adjvalue);
						i++;
					} else {
						char adjvalue = (char) (((numervalue - this.shift) % 26) + 96);
						decrypt.add(adjvalue);
						i++;
					}
				}
			} else {
				decrypt.add(text.charAt(i));
				i++;
			}
		}
		for (int j = 0; j < decrypt.size(); j++) {
			String current = Character.toString(decrypt.get(j));
			wantedmessage += current;
		}
		writeFile(wantedmessage, decryptedFilePath);
	}

	/**
	 * Reads the content of a file and returns it as a string.
	 *
	 * @param filePath the path to the file to be read
	 * @return the content of the file as a string
	 * @throws Exception if an error occurs while reading the file
	 */
	private static String readFile(String filePath) throws Exception {
		String message = "";
		// TODO: Read file from filePath
		try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				message += line + "\n";
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("File not found");
		}
		return message;
	}

	/**
	 * Writes data to a file.
	 *
	 * @param data     the data to be written to the file
	 * @param filePath the path to the file where the data will be written
	 */
	private static void writeFile(String data, String filePath) {
		try (PrintWriter output = new PrintWriter(filePath)) {
			output.println(data);
			output.close();
		} catch (Exception e) {
			System.out.println("Error while attempting to write to file");
		}
	}

	/**
	 * Returns a string representation of the encrypted text.
	 *
	 * @return the encrypted text
	 */
	@Override
	public String toString() {
		return encrypted;
	}
}
