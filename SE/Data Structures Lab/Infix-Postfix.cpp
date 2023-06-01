#include<iostream>  
#include<stack> 
#include<math.h> 
using namespace std;
  
bool IsOperator(char c)  
{  
    if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^' )  
        return true;     
    return false;  
}  
  
 
bool IsOperand(char c)  
{  
    if( c >= 'A' && c <= 'Z')  
        return true;  
    if (c >= 'a' && c <= 'z')  
        return true;  
    if(c >= '0' && c <= '9')   
        return true;  
    return false;  
}  


int precedence(char op)  
{  
    if(op == '+' || op == '-')                  
        return 1;  
    if (op == '*' || op == '/')  
        return 2;  
    if(op == '^') 
        return 3;       
    return 0; 
} 

bool eqlOrhigher (char top_stack, char incoming)  
{  
    int p1 = precedence(top_stack);  
    int p2 = precedence(incoming);  
    if (p1 == p2)  
    {  
        if (incoming == '^' )  
            return false;  
        return true;  
    }  
    return  (p1>p2 ? true : false);  
}  
  
string convert(string infix)  
{  
    stack <char> S;  
    string postfix ="";    
    char ch;  
  
    S.push( '(' );  
    infix += ')';  
  
    for(int i = 0; i<infix.length(); i++)  
    {  
        ch = infix[i];  
  
        if(ch == ' ')  
            continue;  
        else if(ch == '(')  
            S.push(ch);  
        else if(IsOperand(ch))  
            postfix += ch;  
        else if(IsOperator(ch))  
        {  
	        while(!S.empty() && eqlOrhigher(S.top(), ch))  
	        {  
	            postfix += S.top();  
	            S.pop();  
	        }  
	        S.push(ch);  
        }  
        else if(ch == ')')  
        {  
	        while(!S.empty() && S.top() != '(')  
	        {  
	            postfix += S.top();  
	            S.pop();  
	        }  
	        S.pop();  
        }  
    }  
    return postfix;  
}  


int calculate_Postfix(string  post_exp)
{
    stack <int> stack1;
    int len = post_exp.length();
    for (int i = 0; i < len; i++)
    {
        if ( post_exp[i] >= '0' &&  post_exp[i] <= '9')
        {
            stack1.push( post_exp[i] - '0');
        }
        else
        {
            int a = stack1.top();
            stack1.pop();
            int b = stack1.top();
            stack1.pop();
            switch (post_exp[i])
            {
                case '+':
                          stack1.push(b + a);
                          break;
                case '-':
                          stack1.push(b - a);
                          break;
                case '*': 
                          stack1.push(b * a);
                          break;
                case '/':
                          stack1.push(b / a);
                          break;
                case '^': 
                          stack1.push(pow(b,a));
                          break;
            }
        }
    }
    return stack1.top();
}
  
int main()  
{  
    string infix_expression, postfix_expression;  
    int ch;  
    do  
    {  
        cout << " Enter an infix expression: ";  
        cin >> infix_expression;  
        postfix_expression = convert(infix_expression);  
        cout << "\n Your Infix expression is: " << infix_expression;  
        cout << "\n Postfix expression is: " << postfix_expression; 
        cout<<"\n The answer after calculating the postfix expression is : ";
        cout<<calculate_Postfix(postfix_expression); 
        cout << "\n \t Do you want to enter infix expression (1/ 0)?";  
        cin >> ch;    
    } while(ch == 1);  
    return 0;  
}  
  
