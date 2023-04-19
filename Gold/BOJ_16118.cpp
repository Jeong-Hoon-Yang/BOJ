#include <iostream>
#include <vector>
#include <tuple>
#include <utility>
#include <queue>
using namespace std;

int dijkstra(int start, vector<vector<pair<int, int>>> list);

struct cmp {
	bool operator()(pair<int, int>&a, pair<int, int>&b) {
		if (a.second == b.second)
			return a.first > b.first;
		return a.second > b.second;
	}
};

struct cmp2 {
	bool operator()(tuple<int, int, bool>&a, tuple<int, int, bool>&b) {
		if (get<1>(a) == get<1>(b))
			return get<0>(a) > get<0>(b);
		return get<1>(a) > get<1>(b);
	}
};

int main() {
	ios::sync_with_stdio(false);
	int stubNum;
	int roadNum;

	cin >> stubNum;
	cin >> roadNum;

	vector<vector<pair<int, int>>> list(stubNum + 1);

	for (int i = 0; i < roadNum; i++) {
		int stub1, stub2, cost;
		cin >> stub1;
		cin >> stub2;
		cin >> cost;

		list[stub1].push_back(make_pair(stub2, cost * 2));
		list[stub2].push_back(make_pair(stub1, cost * 2));
	}

	cout << dijkstra(1, list);
}

int dijkstra(int start, vector<vector<pair<int, int>>> list) {
	vector<int> foxDist(list.size());
	vector<vector<int>> wolfDist(2);
	wolfDist[0].resize(list.size());
	wolfDist[1].resize(list.size());

	fill(foxDist.begin(), foxDist.end(), 0x7fffffff);
	fill(wolfDist[0].begin(), wolfDist[0].end(), 0x7fffffff);
	fill(wolfDist[1].begin(), wolfDist[1].end(), 0x7fffffff);

	priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> fQueue;
	priority_queue<tuple<int, int, bool>, vector<tuple<int, int, bool>>, cmp2> wQueue;

	fQueue.push(make_pair(start, 0));
	wQueue.push(make_tuple(start, 0, false));

	foxDist[start] = 0;
	wolfDist[0][start] = 0;

	while (!fQueue.empty()) {
		int curPos = fQueue.top().first;
		int curCost = fQueue.top().second;
		fQueue.pop();

		if (foxDist[curPos] < curCost) continue;
		for (int i = 0; i < list[curPos].size(); i++) {
			int nextPos = list[curPos][i].first;
			int nextCost = list[curPos][i].second + curCost;

			if (nextCost < foxDist[nextPos]) {
				foxDist[nextPos] = nextCost;
				fQueue.push(make_pair(nextPos, nextCost));
			}
		}
	}

	while (!wQueue.empty()) {
		int curPos = get<0>(wQueue.top());
		int curCost = get<1>(wQueue.top());
		bool curFlag = get<2>(wQueue.top());
		wQueue.pop();
		bool nextFlag = curFlag != true;

		if (wolfDist[curFlag ? 1 : 0][curPos] < curCost) continue;
		for (int i = 0; i < list[curPos].size(); i++) {
			int nextPos = list[curPos][i].first;
			int nextCost = nextFlag ? list[curPos][i].second / 2 : list[curPos][i].second * 2;
			nextCost += curCost;

			if (nextCost < wolfDist[nextFlag ? 1 : 0][nextPos]) {
				wolfDist[nextFlag ? 1 : 0][nextPos] = nextCost;
				wQueue.push(make_tuple(nextPos, nextCost, nextFlag));
			}
		}
	}

	int cnt = 0;

	for (int i = 1; i < list.size(); i++) {
		if (foxDist[i] < (wolfDist[0][i] < wolfDist[1][i] ? wolfDist[0][i] : wolfDist[1][i])) {
			cnt++;
		}
	}

	return cnt;
}