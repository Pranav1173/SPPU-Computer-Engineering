#include <iostream>
#include <fstream>
using namespace std;
int main(){
    char data[100];
    fstream f;
    char ch;
    f.open("demo.txt");
    cout<<"\nWriting into this file- "<<endl;
    cin.getline(data,100);
    f<<data;
    f.close();
    cout<<"\n-----------------------------------------";
    cout<<"\nReading from the file- "<<endl;
    f.open("demo.txt", ios::in);
    ch=f.get();
    while(!f.eof()){
        cout<<ch;
        ch=f.get();
    }
    return 0;
}