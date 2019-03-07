import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import controller.contact.SendEmail;

public class Test {
	public static void main(String[] args) throws IOException {
		File fe = new File("DATA/image/utilisateur/coucoul.jpeg");
		fe.createNewFile();
		
		 OutputStream out = new FileOutputStream(fe);
		 
		 
		File f = new File("DATA/image/utilisateur");
		for(String s: f.list())
			System.out.println(s);
	}
}
