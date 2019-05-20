package Files;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
	public static Model pullGame() throws Exception {
		FileInputStream fis = new FileInputStream("dump/gameDump.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Model model = (Model) ois.readObject();
		ois.close();
		
		return model;
	}
}
