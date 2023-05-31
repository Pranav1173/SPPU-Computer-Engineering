#include<graphics.h>
#include<iostream.h>
#include<conio.h>
void main(){
int gm=DETECT,gm,points1[]={90,106,90,230,205,230,90,106},points2[]={205,230,318,230,318,106,205,230},v;
initgraph(&gd,&gm,"c:\\TURBOC3\\BGI");
cout<<"Enter The Number Of VErtices Of Graph :";
cin>>v;
cout<<"Enter The Vertices :"<<endl;
cout<<"x0: ";
cin>>v;
cout<<"y0: ";
cin>>v;
cout<<"x1: ";
cin>>v;
cout<<"y1: ";
cin>>v;
cout<<"x2: ";
cin>>v;
cout<<"y2: ";
cin>>v;
cout<<"x3: ";
cin>>v;
cout<<"y3: ";
cin>>v;
cout<<"x4: ";
cin>>v;
cout<<"y4: ";
cin>>v;
getch();

bar(90,230,318,337);
fillpoly(4,points1);
fillpoly(4,points2);
getch();
closegraph();
}