import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_9375 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			int num = Integer.parseInt(br.readLine());
			
			Map<String, Integer> map = new HashMap<>();
			
			for (int i = 0; i < num; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String type = st.nextToken();
				
				if (map.containsKey(type)) {
					map.replace(type, map.get(type) + 1);
				}
				else {
					map.put(type, 1);
				}
			}
			
			Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
			
			int mul = 1;
			
			for (Map.Entry<String, Integer> element : entrySet) {
				mul *= element.getValue() + 1;
			}
			
			mul -= 1;
			
			System.out.println(mul);
		}
	}

}
