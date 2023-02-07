import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

	// ㅁ
	// ㅁ
	// ㅁ
	// ㅁ
	static int type0(int[][] paper) {
		int max = -1;
		for (int i = 3; i < paper.length; i++) {
			int tmp1 = 0;
			for (int j = 0; j < paper[i].length; j++) {
				tmp1 = paper[i - 3][j] + paper[i - 2][j] + paper[i - 1][j] + paper[i][j];
				max = Integer.max(max, tmp1);
			}
		}
		return max;
	}
	// ㅁㅁㅁㅁ
	static int type1(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length; i++) {
			int tmp1 = 0;
			for (int j = 3; j < paper[i].length; j++) {
				tmp1 = paper[i][j - 3] + paper[i][j - 1] + paper[i][j - 2] + paper[i][j];
				max = Integer.max(max, tmp1);
			}
		}
		return max;
	}

	// ㅁㅁ
	// ㅁㅁ
	static int type2(int[][] paper) {
		int max = -1;
		for (int i = 1; i < paper.length; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length; j++) {
				tmp = paper[i - 1][j - 1] + paper[i - 1][j] + paper[i][j - 1] + paper[i][j];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	// ㅁ
	// ㅁ
	// ㅇㅁ
	static int type3(int[][] paper) {
		int max = -1;
		for (int i = 2; i < paper.length; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 1; j++) {
				tmp = paper[i - 2][j] + paper[i - 1][j] + paper[i][j] + paper[i][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	// ㅁㅁㅇ
	// ㅁ
	static int type4(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 2; j < paper[i].length; j++) {
				tmp = paper[i][j - 2] + paper[i][j - 1] + paper[i][j] + paper[i + 1][j - 2];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//ㅁㅇ
	//  ㅁ
	//  ㅁ
	static int type5(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 2; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length; j++) {
				tmp = paper[i][j - 1] + paper[i][j] + paper[i + 1][j] + paper[i + 2][j];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//    ㅇ
	// ㅁㅁㅁ
	static int type6(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 2; j < paper[i].length; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j - 1] + paper[i + 1][j - 2];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//   ㅇㅁ
	// ㅁㅁ
	static int type7(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j - 1] + paper[i][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	
	// ㅇ
	// ㅁㅁ
	//  ㅁ
	static int type8(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 2; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j + 1] + paper[i + 2][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	// ㅇㅁㅁ
	//  ㅁ
	static int type9(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 2; j++) {
				tmp = paper[i][j] + paper[i][j + 1] + paper[i][j + 2] + paper[i + 1][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//  ㅁ
	// ㅇㅁ
	//  ㅁ
	static int type10(int[][] paper) {
		int max = -1;
		for (int i = 1; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i][j + 1] + paper[i - 1][j + 1] + paper[i + 1][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	//  ㅇ
	// ㅁㅁㅁ
	static int type11(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i + 1][j - 1] + paper[i + 1][j] + paper[i + 1][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	// ㅇ
	// ㅁㅁ
	// ㅁ
	static int type12(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 2; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j + 1] + paper[i + 2][j];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//   ㅁ
	//   ㅁ
	// ㅇㅁ
	static int type13(int[][] paper) {
		int max = -1;
		for (int i = 2; i < paper.length; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 1; j++) {
				tmp = paper[i][j] + paper[i][j + 1] + paper[i - 1][j + 1] + paper[i - 2][j + 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	// ㅇ
	// ㅁㅁㅁ
	static int type14(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 2; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j + 1] + paper[i + 1][j + 2];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	// ㅁㅇ
	// ㅁ
	// ㅁ
	static int type15(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 2; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length; j++) {
				tmp = paper[i][j] + paper[i][j - 1] + paper[i + 1][j - 1] + paper[i + 2][j - 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	// ㅇㅁㅁ
	//    ㅁ
	static int type16(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 2; j++) {
				tmp = paper[i][j] + paper[i][j + 1] + paper[i][j + 2] + paper[i + 1][j + 2];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	//  ㅇ
	// ㅁㅁ
	// ㅁ
	static int type17(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 2; i++) {
			int tmp = 0;
			for (int j = 1; j < paper[i].length; j++) {
				tmp = paper[i][j] + paper[i + 1][j] + paper[i + 1][j - 1] + paper[i + 2][j - 1];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}

	// ㅇㅁ
	//   ㅁㅁ
	static int type18(int[][] paper) {
		int max = -1;
		for (int i = 0; i < paper.length - 1; i++) {
			int tmp = 0;
			for (int j = 0; j < paper[i].length - 2; j++) {
				tmp = paper[i][j] + paper[i][j + 1] + paper[i + 1][j + 1] + paper[i + 1][j + 2];
				max = Integer.max(max, tmp);
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int rowLen = Integer.parseInt(st.nextToken());
		int colLen = Integer.parseInt(st.nextToken());

		int[][] paper = new int[rowLen][colLen];

		for (int i = 0; i < rowLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colLen; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = -1;
		max = Integer.max(type0(paper), type1(paper));
		max = Integer.max(max, type2(paper));
		max = Integer.max(max, type3(paper));
		max = Integer.max(max, type4(paper));
		max = Integer.max(max, type5(paper));
		max = Integer.max(max, type6(paper));
		max = Integer.max(max, type7(paper));
		max = Integer.max(max, type8(paper));
		max = Integer.max(max, type9(paper));
		max = Integer.max(max, type10(paper));
		max = Integer.max(max, type11(paper));
		max = Integer.max(max, type12(paper));
		max = Integer.max(max, type13(paper));
		max = Integer.max(max, type14(paper));
		max = Integer.max(max, type15(paper));
		max = Integer.max(max, type16(paper));
		max = Integer.max(max, type17(paper));
		max = Integer.max(max, type18(paper));
		
		System.out.println(max);
	}

}
