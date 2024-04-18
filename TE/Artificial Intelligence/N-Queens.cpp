#include <iostream>
#include <vector>
using namespace std;

class NQueenSolver {
public:
    NQueenSolver(int size) : boardSize(size) {
        board.resize(boardSize, vector<int>(boardSize, 0));
    }

    // Public method to solve the N-Queen problem
    void solve() {
        if (placeQueens(0)) {
            cout << "Solution found:" << endl;
            printBoard();
        } else {
            cout << "No solution exists." << endl;
        }
    }

private:
    vector<vector<int>> board;
    int boardSize;

    // Utility function to print the board
    void printBoard() {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                cout << (board[i][j] ? "Q " : ". ");
            }
            cout << endl;
        }
    }

    // Function to check if a queen can be placed on board[row][col]
    bool isSafe(int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i])
                return false;

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j])
                return false;

        for (int i = row, j = col; j >= 0 && i < boardSize; i++, j--)
            if (board[i][j])
                return false;

        return true;
    }

    // Recursive utility function to solve the problem
    bool placeQueens(int col) {
        if (col >= boardSize)
            return true;

        for (int i = 0; i < boardSize; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;
                if (placeQueens(col + 1))
                    return true;
                board[i][col] = 0;  // Backtrack
            }
        }
        return false;
    }
};

int main() {
    int n;
    cout << "Enter the number of Queens: ";
    cin >> n;
    NQueenSolver solver(n);
    solver.solve();
    return 0;
}
