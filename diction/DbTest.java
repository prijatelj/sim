package sim.diction;

public class DbTest {
	public static void main(String [] args){
		Database db = new Database();
		System.out.println("db is empty = " + db.isEmpty());
		System.out.println("db size = " + db.size());
		System.out.println("db size(0) = " + db.size(0));
		System.out.println("db size(0,0) = " + db.size(0,0));
		System.out.println("db get(0,0,0) = " + db.get(0,0,0));
		System.out.println("db get(0,0,0) = " + db.get(0,0,1));
		System.out.println("db get(0,0,0) = " + db.get(0,0,2));
		System.out.println("db get(0,0,0) = " + db.get(0,0,3));
		System.out.println("db get(0,0,0) = " + db.get(0,0,4));
		System.out.println("db get(0,0,0) = " + db.get(0,0,5));
		System.out.println("db get(0,0,0) = " + db.get(0,0,6));
	}
}
