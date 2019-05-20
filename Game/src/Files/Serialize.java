package Files;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialize {
	public static void dumpGame(Model model) throws Exception {
		FileOutputStream fos = new FileOutputStream("dump/gamedump.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(model);
		oos.close();
	}
}
