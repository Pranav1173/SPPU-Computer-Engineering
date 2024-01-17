#include <iostream>
#include <map>
#include <utility>
#include <cstring>
using namespace std;

int main(){
    typedef map<string, int> maptype;
    maptype salarycell;
    salarycell.insert(pair<string, int>("Pranav", 368324));
    salarycell.insert(pair<string, int>("Dinesh", 220520));
    salarycell.insert(pair<string, int>("Mayur", 99204));
    salarycell.insert(pair<string, int>("Sahil", 370354));
    
    maptype:: iterator iter = --salarycell.end();
    for(iter = salarycell.begin(); iter!=salarycell.end(); ++iter){
        cout<<iter->first<<": "<<iter->second<<"$\n";
    }
    cout<<"\nThe Number of Salary Entries are- "<<salarycell.size();
    string name;
    cout<<"\nEnter the Name of person: ";
    cin>>name;
    iter = salarycell.find(name);
    if(iter!=salarycell.end()){
        cout<<"\n";
        cout<<name<<": "<<iter->second<<"$";
    }
    else{
        cout<<"\nEntry not found! Try Again.";
    }
    salarycell.clear();
    return 0;
}