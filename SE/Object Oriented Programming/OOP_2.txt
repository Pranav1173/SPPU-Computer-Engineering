#include <iostream>
#include <string>
using namespace std;

class info
{
private:
    string nm, drv_lic, cl, dob;
    int roll;
    char* div, *bdgp;
    long int ctnum;

public:
    // Default constructor
    info()
    {
        nm = "";
        dob = "";
        cl = "";
        roll = 0;
        ctnum = 0;
        div = new char[1];
        bdgp = new char[3];
    }

    // Destructor
    ~info()
    {
        delete[] div;
        delete[] bdgp;
    }

    friend class addinfo;

    void inputinfo()
    {
        cin.ignore(); // Ignore the newline character left in the input stream
        cout << "\n\n_______________*STUDENT DATABASE*______________\n\n";
        cout << "Student Name: ";
        getline(cin, nm);
        cout << "\nClass: ";
        getline(cin, cl);
        cout << "\nDivision: ";
        div = new char[10]; // Allocate memory for division
        cin >> div;
        cout << "\nRoll number: ";
        cin >> roll;
        cout << "\nDate of Birth (Format- dd/mm/yyyy): ";
        cin.ignore(); // Ignore the newline character left in the input stream
        getline(cin, dob);
        cout << "\nBlood Group (A+, A-, B+, B-, AB+, AB-, O+, O-): ";
        bdgp = new char[10];
        cin >> bdgp;
        cout << "\nEnter driving licence number: ";
        cin >> drv_lic;
    }
};

class addinfo
{
public:
    long int ctnum;

    void getinfo()
    {
        try
        {
            cout << "\nContact Number: ";
            cin >> ctnum;
            if (ctnum < 1000000000 || ctnum > 9999999999)
                throw ctnum;
        }
        catch (long int ctnum)
        {
            cout << "\nException caught!";
            cout << "\nContact number entered '" << ctnum << "' is too long!\nTry again!";
            cout << "\nContact Number: ";
            cin >> ctnum;
        }
    }

    int getroll(info &obj)
    {
        return obj.roll;
    }

    void show(info &obj);
};

void addinfo::show(info &obj)
{
    cout << "\nStudent Name: " << obj.nm;
    cout << "\nClass: " << obj.cl;
    cout << "\nDivision: " << obj.div;
    cout << "\nRoll number: " << obj.roll;
    cout << "\nDate of Birth: " << obj.dob;
    cout << "\nBlood Group: " << obj.bdgp;
    cout << "\nDriving licence number: " << obj.drv_lic;
    cout << "\n-------------------------------------\n";
}

int main()
{
    info x[30];
    int i, n;
    cout << "Enter the number of students: ";
    cin >> n;
    addinfo s[30];
    for (i = 0; i < n; i++)
    {
        cout << "\n---------------------------------------\n\nEnter Details of student " << i + 1;
        x[i].inputinfo();
        s[i].getinfo();
    }
    cout << "\n_____________________________________________________________" << endl;
    for (i = 0; i < n; i++)
    {
        s[i].show(x[i]);
    }
    while (true)
    {
        int r, ch;
        int flag = 0;
        cout << "\nEnter student roll number to get info: ";
        cin >> r;
        for (i = 0; i < n; i++)
        {
            if (r == s[i].getroll(x[i]))
            {
                s[i].show(x[i]);
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            cout << "\nRecord not found!";
        cout << "\nDo you want to continue? (Enter 1 for Yes, 0 for No): ";
        cin >> ch;
        if (ch != 1)
            break;
    }
    cout<<"\nEnding the Program!";
    return 0;
}
