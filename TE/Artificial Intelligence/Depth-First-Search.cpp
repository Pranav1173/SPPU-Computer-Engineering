#include <iostream>
#include <vector>
#include <stack>
#include <unordered_set>

using namespace std;

void dfs(int node, vector<vector<int>>& graph, unordered_set<int>& visited) {
    cout << node << " ";
    visited.insert(node);

    for (int neighbor : graph[node]) {
        if (visited.find(neighbor) == visited.end()) {
            dfs(neighbor, graph, visited);
        }
    }
}

int main() {
    int vertices, edges;
    cin >> vertices >> edges;

    vector<vector<int>> graph(vertices);
    unordered_set<int> visited;

    // Assuming the graph is represented as an adjacency list
    for (int i = 0; i < edges; ++i) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    int startNode;
    cin >> startNode;

    cout << "DFS starting from node " << startNode << ": ";
    dfs(startNode, graph, visited);

    return 0;
}
