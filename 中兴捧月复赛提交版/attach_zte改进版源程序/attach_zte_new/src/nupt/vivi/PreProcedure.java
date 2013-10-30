package nupt.vivi;


/*
 * 此类用于预处理，主要是读取文件，包括给定的测试文件以及随机生成的随机数文件。
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PreProcedure {
	
	//读取male和female中的数据以及随机生成的maleRandom和femaleRandom中的数据
	
	public static ArrayList<Player> readFile(String path, int gender) {
		BufferedReader file = null;
		ArrayList<Player> p = new ArrayList<Player>();
		try {
			file = new BufferedReader(new FileReader(new File(
					path)));
			
			
			Player player = null;
			String line;
			while ((line = file.readLine()) != null) {
				player = new Player(line,gender);
				p.add(player);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file!=null){
				try {
					file.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
}
