import java.util.ArrayList;

public class SQLTester{
	public static void main(String [] args){
		try {
			SQLManager.createDB();

			SQLManager.addUser("Test1", "pass1");
			SQLManager.addUser("Test2", "pass2");

			SQLManager.addLink("Test1", "www.test.com");
			SQLManager.addLink("Test1", "www.test2.com");
			
			SQLManager.shareLink("Test1","Test2", "www.test2.com");
			SQLManager.sharedLink("Test2","www.test2.com",true);

			SQLManager.addLink("Test2", "www.test3.com");


			ArrayList<String> results = SQLManager.findSavedLinks("Test1");
			System.out.println("Count: " + results.size());
			for (int i = 0; i < results.size(); i++){
				System.out.println("Result " + i + ":" + results.get(i));
			}

			//SQLManager.deleteSavedLink("Test1", "www.test2.com");

			results = SQLManager.findSavedLinks("Test2");
			System.out.println("Count: " + results.size());
			for (int i = 0; i < results.size(); i++){
				System.out.println("Result " + i + ":" + results.get(i));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}