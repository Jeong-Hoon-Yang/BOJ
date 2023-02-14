import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int siteNum = Integer.parseInt(st.nextToken());
		int findNum = Integer.parseInt(st.nextToken());
		
		Map<String, String> map = new HashMap<>();
		
		for (int i = 0; i < siteNum; i++) {
			st = new StringTokenizer(br.readLine());
			String site = st.nextToken();
			String pwd = st.nextToken();
			
			map.put(site, pwd);
		}
		
		for (int i = 0; i < findNum; i++) {
			String findSite = br.readLine();
			sb.append(map.get(findSite)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}
