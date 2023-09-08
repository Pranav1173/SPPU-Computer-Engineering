#include <iostream>
#include <string.h> 
using namespace std;

class info
{
    // Declaring Data Members used to store data in database in private section of class
    string nm, drv_lic, cl, dob, bdgp;
    int roll;  
    char *div;
    long int ctnum;

public:
/*Declaring a friend class which will be able to access all private data members of this class
  and the class holds function which will display those*/
    friend class addinfo;


//default constructor
    info() 
    {                                                         
        // Setting default values for introduced data members
        nm = "";
        dob = "";
        cl = ""; 
        bdgp = "";
        roll = 0; 
        ctnum = 0;
        div = new char[1];
        bdgp = new char[3];
    }
    
    //Destructor to deallocate the memory allocated dynamically, with the help of delete
    ~info()
    {
        delete div;
    }
    
    
    static int count;
    inline static int getCount(){
        return count;
    }
    
    // Creating function inputinfo in order to get input data from user to store in Data Base.
    void inputinfo()
    {
        cout<<"\n\n_______________*STUDENT DATABASE*______________\n\n";
        cout<<"Student Name: ";
        cin.get();
        getline(cin,nm);
        cout<<"\nClass: ";
        cin.get();
        getline(cin,cl);
        cout<<"\nDivision: ";
        cin>>div;
        cout<<"\nRoll number: ";      
        cin>>roll;
        cout<<"\nDate of Birth (Format- dd/mm/yyyy): ";
        cin.get();
        getline(cin,dob);
        cout<<"\nBlood Group (A+, A-, B+, B-, AB+, AB-, O+, O-): ";
        cin.get();
        getline(cin,bdgp);
        cout<<"\nEnter driving licence number: ";
        cin>>drv_lic;
    }
    
};

int info::count=0;

class addinfo
{
    
public:
    long int ctnum;
    //Exception Handling
    void getinfo()
    {
        try{
        cout<<"\nContact Number: ";
        cin>>ctnum;
        if(ctnum<1000000000 && ctnum>9999999999)
            throw(ctnum);
            
        }
        catch(long int ctnum)
        {
            cout<<"\nException caught!";
            cout<<"\nContact number entered "<<ctnum<<"' is too long!\nTry again!";
            cout<<"\nContact Number: ";
            cin>>ctnum;
        }
    }
    int getroll(info &obj)
    {
        return obj.roll;
    }

    void show(info &obj);
};
inline void addinfo::show(info &obj)
    {
        cout<<"\nStudent Name: "<<obj.nm;
        cout<<"\nClass: "<<obj.cl;
        cout<<"\nDivision: "<<obj.div;
        cout<<"\nRoll number: "<<obj.roll;
        cout<<"\nDate of Birth: "<<obj.dob;
        cout<<"\nBlood Group: "<<obj.bdgp;
        cout<<"\nDriving licence number: "<<obj.drv_lic;
        cout<<"-------------------------------------";

    }

int main()
{
    info x[30];
    int i,n;    
    cout<<"Enter the number of students: ";
    cin>>n;
    addinfo s[30];
    for(i=0; i<n; i++)
    {
        cout<<"\n---------------------------------------\n\nEnter Details of student "<<i+1;
        x[i].inputinfo();
        s[i].getinfo();
    }
    cout<<"\n_____________________________________________________________"<<endl;
    for(i=0;i<n;i++)
    {
        s[i].show(x[i]);
    }
    while(true)
    {
        int r,ch;
        int flag=0;
        cout<<"\nEnter student roll number to get info: ";
        cin>>r;
        for(i=0;i<n;i++)
        {
            if(r==s[i].getroll(x[i]))
            {
                s[i].show(x[i]);
                flag=1;
                break;
                }
                
        }
        if(flag==0)
            cout<<"\nRecord not found!";
        cout<<"\nDo you want to continue?(Enter 1 or 0): ";
        cin>>ch;
        if(ch!=1)
            break;
    }
    return 0;
}
