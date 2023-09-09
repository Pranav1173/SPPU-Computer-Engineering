#include <iostream>
#include <climits>
using namespace std;

#define MAX_VERTICES 8

class CityGraph {
    int noOfVertices;
    int cityGraph[MAX_VERTICES][MAX_VERTICES];

public:
    CityGraph();
    void primsAlgo();
};

CityGraph::CityGraph() {
    int i, j;
    cout << "Enter Total Offices: ";
    cin >> noOfVertices;

    cout << "\nEnter Adjacency Matrix:\n";
    for (i = 0; i < noOfVertices; i++) {
        for (j = 0; j < noOfVertices; j++) {
            if (i == j) {
                cityGraph[i][j] = 0;
            } else {
                cout << "Enter distance between " << i << " and " << j << " (-1 if there is no edge): ";
                cin >> cityGraph[i][j];
                if (cityGraph[i][j] == -1)
                    cityGraph[i][j] = INT_MAX;
                cityGraph[j][i] = cityGraph[i][j];
            }
        }
    }
}

void CityGraph::primsAlgo() {
    int selected[MAX_VERTICES] {}; // Initialize selected array with zeros
    int cost = 0, min = 0;
    int x, y, n = 0;  // Used in while loop
    selected[0] = 1; // Mark the first vertex as selected
    while (noOfVertices - 1 > n) {
        min = INT_MAX;
        for (int i = 0; i < noOfVertices; i++) {
            if (selected[i] == 1) {
                for (int j = 0; j < noOfVertices; j++) {
                    if (selected[j] == 0 && cityGraph[i][j] < min) {
                        min = cityGraph[i][j];
                        x = i; // For graph position
                        y = j; // For graph position
                    }
                }
            }
        }
        selected[y] = 1; // Mark the selected vertex as visited
        cout << "\n" << x << " ---> " << y;
        cost += cityGraph[x][y];
        n++;
    }
    cout << "\n\tTotal Cost: " << cost << endl;
}

int main() {
    CityGraph c1;
    c1.primsAlgo();
    return 0;
}
