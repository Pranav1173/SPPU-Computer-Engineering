#include<iostream>
#include<stdlib.h>
#define SIZE 80

using namespace std;

class mystack
{
	private :
		char ST[SIZE];
		int top;
	public : 
		mystack();
		void push(char X);
		char pop();
		int isEmpty();
		int isFull();
};

mystack :: mystack()
{
 	top = -1;
}

int mystack :: isEmpty()
{
	if(top == -1)
		return 1;
	else
		return 0;
}

int mystack :: isFull()
{
	if(top == SIZE-1)
		return 1;
	else
		return 0;
}

void mystack :: push(char X)
{
	if(!isFull())
	{
		top++;
		ST[top] = X;
	}
	else
		cout<<"\nStack Overflow !! Error!!";
}

char mystack :: pop()
{
	char X = '\0';
	X = ST[top];
	top--;
	return X;		
}


void convert_string(char Str[],char Str1[])
{
   	int i,j = 0;
	for(i=0;Str[i] != '\0';i++)
	{
		if(Str[i] >= 'a' && Str[i] <= 'z')
			Str1[j++] = Str[i];
		if(Str[i] >= 'A' && Str[i] <= 'Z')
			Str1[j++] = Str[i] + 32;
		if(Str[i] >= '0' && Str[i] <= '9')
			Str1[j++] = Str[i];
	}   
	Str1[j] = '\0';
}

int main()
{
	int ch,flag,i;
	char Str[80],Str1[80];
	mystack S;
	system("clear");
	do
	{
		cout<<"\n\t\t\t1 : Check for Palindrome";
		cout<<"\n\t\t\t2 : Find Reverse";
		cout<<"\n\t\t\t3 : Exit";
		cout<<"\n\nEnter ur choice : ";
		cin>>ch;
		switch(ch)
		{
			case 1 : cout<<"\nEnter the string to be checked for palindrome : ";
					 cin.ignore();
					 cin.getline(Str,80);
					 cout<<"\nEntered String is "<<Str;
					 convert_string(Str,Str1);
					 cout<<"\nconverted String is : "<<Str1;
				 	 for(i = 0; Str1[i] != '\0';i++)
						S.push(Str1[i]);
					 i = 0; flag = 1;
					 while(!S.isEmpty())
					 {
						if(Str1[i++] != S.pop())
						{
						   	flag = 0;
						   	break;
					    }
					 }		
					 if(flag == 1)
                        cout<<"\nGiven string is a palindrome\n";
					 else
 						cout<<"\nGiven String is not a palindrome\n";
					 break;
			case 2 : cout<<"\nEnter the string to be reversed : ";
					 cin.ignore();
					 cin.getline(Str,80);
					 cout<<"\nString entered is "<<Str;

					 for(i = 0; Str[i] != '\0';i++)
						S.push(Str[i]);
					 cout<<"\nReverse String = ";
					 while(!S.isEmpty())
					 {
						cout<<S.pop();
					 }		
					 break;
			case 3 : cout<<"\nEnd of Program\n";
					 break;
			default: cout<<"\nInvalid choice !! Try again\n\n";
		}
	}while(ch != 3);
	return 0;
}