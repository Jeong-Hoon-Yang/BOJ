#include <iostream>
#include <vector>
using namespace std;

int main() {
	int size;
	cin >> size;
	vector<vector<int>> paper;
	vector<vector<int>> check;
	int blue = 0, white = 0;
	for (int i = 0; i < size; i++) {
		vector<int> vTmp;
		vector<int> vCTmp;
		for (int j = 0; j < size; j++) {
			int iTmp;
			cin >> iTmp;
			vTmp.push_back(iTmp);
			vCTmp.push_back(0);
		}
		paper.push_back(vTmp);
		check.push_back(vCTmp);
	}

	

	int copySize = size;
	int div[4][2] = { {0, 0}, {0, 1}, {1, 0}, {1, 1} };
	while (copySize >= 1) {
		int iPaperColor;
		bool bPossible;
		for (int si = 0; si < size; si += copySize) {
			for (int sj = 0; sj < size; sj += copySize) {
				if (check[si][sj]) continue;
				iPaperColor = paper[si][sj];
				bPossible = true;
				for (int i = si; i < si + copySize; i++) {
					for (int j = sj; j < sj + copySize; j++) {
						if (iPaperColor != paper[i][j]) {
							bPossible = false;
							break;
						}
					}
					if (!bPossible) break;
				}
				if (bPossible) {
					for (int i = si; i < si + copySize; i++) {
						for (int j = sj; j < sj + copySize; j++) {
							check[i][j] = 1;
						}
					}
					if (iPaperColor == 1) {
						blue++;
					}
					else {
						white++;
					}
				}
			}
		}
		copySize /= 2;
	}

	cout << white << endl << blue << endl;
}
