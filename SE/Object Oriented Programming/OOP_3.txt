#include <iostream>
#include <cstring>
#include <exception>

using namespace std;
class publication{
  protected:
    float price;
    string title;
  
  public:
    publication( string t = "No title", float p=0.0){}
};

class book:public publication{
  int pages;
  public: 
    book(string t = "No title", float p=0.0, int pg=0){}
	void getb();
	void displayb();
};

class tape:public publication{
  float mins;
  public: 
    tape(string t = "No title", float p=0.0, float m=0.0){}
	void gett();
	void displayt();
};

void book:: getb(){
  cout<<"Enter the details of book-"<<endl;
  cout<<"Name of the book: ";
  cin.ignore(1, '\n');
  getline(cin, title);
  cout<<"\n Price of Book: ";
  cin>>price;
  cout<<"\n Number of Pages: ";
  cin>>pages;
  try{
    if(pages>=120 && pages<=500)
	{
	  if(price>= 135 && price<=300)
	    displayb();
	}
	else 
	  throw(pages);
  }
  catch(int i)
  {
    cout<<"Exception caught in no of Pages!";
	title = "No title";
	price = 0.0;
	pages = 0;
	displayb();
	throw;
  }
}

void book:: displayb(){
  cout<<"\n____________________________";
  cout<<"\nDetails of Book-";
  cout<<"\nName: "<<title;
  cout<<"\nPrice: "<<price;
  cout<<"\nNo of pages: "<<pages<<endl;
}
  
  void tape:: gett(){
  cout<<"Enter the details of tape-"<<endl;
  cout<<"Name of the tape: ";
  cin.ignore(1, '\n');
  getline(cin, title);
  cout<<"\nPrice of Tape: ";
  cin>>price;
  cout<<"\nTime: ";
  cin>>mins;
  try{
    if(mins>=20 && mins<=300)
	{
	  if(price>= 135 && price<=300)
	    displayt();
	}
	else 
	  throw(mins);
  }
  catch(float f)
  {
    cout<<"Exception caught !";
	title = "No title";
	price = 0.0;
	mins = 0.0;
	displayt();
	throw;
  }
}

void tape:: displayt(){
  cout<<"\n____________________________";
  cout<<"\nDetails of Tape-";
  cout<<"\nName: "<<title;
  cout<<"\nPrice: "<<price;
  cout<<"\nTime: "<<mins<<endl;
}

int main()
{
    book b;
    tape t;
    int choice;
    cout<<"Do you want to buy a book(press 1) or a tape (press 2):"<<flush;
    cin>>choice;
    
    switch(choice)
    {
	    case 1: 
		      b.getb();
		      break;

        case 2:
		       t.gett();
               break;
        default:
        cout<<"entered bad choice! Try again"<<endl;
    }
    
return 0;

}