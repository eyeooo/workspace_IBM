

public class JniTest {

	static
	{
		System.loadLibrary("HelloWorldDll");
	}

	public native static int MyMethod();

	public static void main(String[] args){
		System.out.println(System.getProperties().get("java.library.path"));
		JniTest hw = new JniTest();
		hw.MyMethod();

	}

		
}
