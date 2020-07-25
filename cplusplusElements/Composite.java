package cplusplusElements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Add, Sub, InvSub, Mul, Div, InvDiv, Pow, Min, Max, Log, Inv, Abs, ConvGrey, ConvBW, Composite, Med

class Operation {
	String nameOfOperation;
	int arg;
	Operation(String name, int arg){
		nameOfOperation = name;
		this.arg = arg;
	}
	
}

public class Composite {
	private String pathToComposite;
	//private File tmpDir = null; 
	private ArrayList<Operation> operations = new ArrayList<Operation>();
	public Composite(String fileName) {
		pathToComposite = fileName;
	}
	
	public void addOperation(String op, int arg) {
		operations.add(new Operation(op, arg));
	}
	
	public void writeInCompositeFile() {
		try {
			File newFile = new File(pathToComposite);
			newFile.createNewFile();
			FileWriter writer = new FileWriter(newFile);
			StringBuilder sb = new StringBuilder();
			sb.append("<Comoposite_fun>\n");
			for(Operation tmp: operations) {
				sb.append("\t<operation>\n");
				sb.append("\t\t<name>");sb.append(tmp.nameOfOperation);sb.append("</name>\n");
				sb.append("\t\t<arg>");sb.append(tmp.arg);sb.append("</arg>\n");
				sb.append("\t</operation>\n");
			}
			sb.append("</Comoposite_fun>");
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			System.out.println("Problem with opening a file");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<operation>\n");
			sb.append("\t\t<composite>");sb.append(new File(pathToComposite).getAbsolutePath());sb.append("</composite>\n");
		sb.append("\t</operation>\n");
		return sb.toString();
	}
	
	public static void main(String [] argv) {
		Composite com = new Composite("C:\\Users\\tozaj\\Desktop\\tozica1.fun");
		com.addOperation("Add", 15);
		com.addOperation("Add", 10);
		com.writeInCompositeFile();
	}

}
