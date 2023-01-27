import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18111 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		int row = Integer.parseInt(str[0]);
		int col = Integer.parseInt(str[1]);
		int inv = Integer.parseInt(str[2]);
		
		int[][] arr = new int[row][col];
		
		int min = Integer.MAX_VALUE;
		int max = -1;
		
		for (int i = 0; i < row; i++) {
			String[] tmp = bf.readLine().split(" ");
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(tmp[j]);;
				if (arr[i][j] < min)
					min = arr[i][j];
				if (arr[i][j] > max)
					max = arr[i][j];
			}
		}
		
		int resTime = Integer.MAX_VALUE;
		int resHeight = -1;
		
		
		for (int height = min; height <= max; height++) {
			
			int time = 0;
			int remainBlock = inv;
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (height == arr[i][j]) {
						continue;
					}
					
					if (height < arr[i][j]) {
						time += (arr[i][j] - height) * 2;
						remainBlock += (arr[i][j] - height);
						continue;
					}
					
					if (height > arr[i][j]) {
						time += (height - arr[i][j]);
						remainBlock -= (height - arr[i][j]);
						continue;
					}
					
				}
			}
			
			
			if (remainBlock < 0) continue;
			
			if (time <= resTime) {
				resTime = time;
				resHeight = height;
			}
		}
		
		System.out.println(resTime + " " + resHeight);
	}
	
}
