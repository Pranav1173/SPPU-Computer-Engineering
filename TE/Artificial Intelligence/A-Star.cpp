#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
#include <string>
#include <functional>

using namespace std;

struct Node {
    string city;
    int g;  // Total path cost from the start node
    int h;  // Heuristic cost to the goal
    string path;  // Path taken to reach this node

    // Calculate f() for priority queue
    int f() const { return g + h; }

    // Compare nodes for priority queue ordering
    bool operator>(const Node& other) const {
        return this->f() > other.f();
    }
};

// Heuristic function: straight-line distance to Bucharest
unordered_map<string, int> heuristic = {
    {"Arad", 336}, {"Sibiu", 253}, {"Timisoara", 329},
    {"Oradea", 380}, {"Fagaras", 176}, {"Bucharest", 0}
};

// Adjacency list for the graph
unordered_map<string, vector<pair<string, int>>> graph = {
    {"Arad", {{"Sibiu", 140}, {"Timisoara", 118}}},
    {"Sibiu", {{"Oradea", 151}, {"Fagaras", 99}, {"Arad", 140}}},
    {"Timisoara", {{"Arad", 118}}},
    {"Oradea", {{"Sibiu", 151}}},
    {"Fagaras", {{"Sibiu", 99}, {"Bucharest", 211}}},
    {"Bucharest", {}}
};

// A* search algorithm implementation
void a_star_search(const string& start, const string& goal) {
    priority_queue<Node, vector<Node>, greater<Node>> open_set;
    open_set.push({start, 0, heuristic[start], start});

    while (!open_set.empty()) {
        Node current = open_set.top();
        open_set.pop();

        // Check if reached goal
        if (current.city == goal) {
            cout << "Path: " << current.path << " with total cost: " << current.f() << endl;
            return;
        }

        // Expand the current node
        for (auto& neighbor : graph[current.city]) {
            int total_g = current.g + neighbor.second;
            string next_city = neighbor.first;
            int total_h = heuristic[next_city];
            string next_path = current.path + " -> " + next_city;
            open_set.push({next_city, total_g, total_h, next_path});
        }
    }

    cout << "No path found!" << endl;
}

int main() {
    string start = "Arad";
    string goal = "Bucharest";
    a_star_search(start, goal);
    return 0;
}
