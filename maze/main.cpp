#include <iostream>
#include <vector>
using namespace std;
// https://codeforces.com/contest/676/problem/D
struct node{
    bool u,d,l,r;
    bool hajime,owari;
    void rot(){
        bool tmp = u;
        u = l;
        l = d;
        d = r;
        r = tmp;
    }
};

struct maze{
    // you dum dum this won't work cuz references
    // won't rotate and would be too expensive to do so
    node** mat;
    int n,m;
    maze(int row,int col){
        mat = new node*[row];
        for (int i=0;i<row;i++){
            mat[i] = new node[col];
        }
        n=row;
        m=col;
    }
    node* get_node(int i,int j){
        return &mat[i][j];
    }
    void handle(int i,int j,char c){
        node* a = &mat[i][j];
        if (a == nullptr){
            a = new node;
            mat[i][j] = *a;
        }
        switch(c){
        case '+':
            a->u=a->d=a->l=a->r=true;
            break;
        case '|':
            a->u=a->d=true;
            break;
        case '-':
            a->l=a->r=true;
            break;
        case '^':
            a->u=true;
            break;
        case '<':
            a->l=true;
            break;
        case '>':
            a->r=true;
            break;
        case 'V':
            a->d=true;
            break;
        case 'U':
            a->d=a->l=a->r=true;
            break;
        case 'D':
            a->u=a->l=a->r=true;
            break;
        case 'L':
            a->u=a->d=a->r=true;
            break;
        case 'R':
            a->u=a->d=a->l=true;
            break;
        }
    }
};

int main()
{
    int n,m;
    int thesus[2],minorthor[2];
    node yamete,kudasai;
    string line;
    cin >> n >> m;
    maze massive(n,m);
    for (int i=0;i<n;i++){
        cin >> line;
        for (int j=0;j<m;j++){
            massive.handle(i,j,line[j]);
        }
    }
    cin >> thesus[0] >> thesus[1] >> minorthor[0] >> minorthor[1];
    massive.get_node(thesus[0],thesus[1])->hajime=true;
    massive.get_node(minorthor[0],minorthor[1])->owari=true;
}
