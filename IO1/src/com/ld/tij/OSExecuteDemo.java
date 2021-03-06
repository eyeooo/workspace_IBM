package com.ld.tij;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OSExecuteDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OSExecute.command("ipconfig");
	}

}

class OSExecute{
	public static void command(String command) {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while((s=result.readLine())!=null){
				System.out.println(s);
			}
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while((s=errors.readLine())!=null){
				System.out.println(s);
				err=true;
			}
		} catch (IOException e) {
			if(!command.startsWith("CMD /C")){
				command("CMD /C "+command);
			}else{
				throw new RuntimeException(e);
			}
		}
		if(err){
			System.err.println("ERROE CMD: "+command);
		}
	}
}