#include <stdlib.h>
#include <stdio.h>

#include "Profiler.h"


Profiler p("dfs");

typedef struct node {
	int parent;//parintele nodului
    int key;//valoarea in sine a nodului

	int discoverytime;//timpul de descoperire
	int finishtime;//timpul pana acesta s a innegrit

	int adjsize;//nr de vecini
	struct node** adj;//vectorul de vecini
	int color;//culoarea
};

typedef struct graf {
	int nrnodes;//nr de noduri
	node** v;//vector pt nr de noduri

};

enum {
	COLOR_WHITE,
	COLOR_GRAY,
	COLOR_BLACK

};



void dfs_visit(graf* graf, node* s,Operation Op)
{
    static int time = 0;//contor pt masurarea timpului

    Op.count(2);
	s->discoverytime = time;//momentul la care am gasit acest nod
    time++;
	s->color = COLOR_GRAY;//l am colorat cu gri pt ca am ajusn la el

	for (int i = 0; i < s->adjsize; i++)//trecem prin toti vecinii acestui nod
    {
        Op.count(2);
		if (s->adj[i]->color == COLOR_WHITE)//vedem daca am mai terut prin vecinii acestui nod
		{
            Op.count();
			s->adj[i]->parent = s->key;//nodul devine parinte pt vecinii lui la care merge
			dfs_visit(graf, s->adj[i],Op);//facem dfs visit pe acest nod

		}
		
	}
	s->color = COLOR_BLACK;//il facem negru pt ca l am explorat
	time++;
	s->finishtime = time;//setam timpul de explorare
    Op.count(2);

}

void dfs(graf *graf,Operation Op)
{
	for (int i = 0; i < graf->nrnodes; i++)//initializam nodurile cu alb si parintele -1
	{
		graf->v[i]->color = COLOR_WHITE;
		graf->v[i]->parent = -1;
	
	}
	
	
	for (int i = 0; i < graf->nrnodes; i++)//trecem prin fiecare nod si vedem daca este alb pt a i putea face dfs_visit ul
	{
		if (graf->v[i]->color == COLOR_WHITE)
		{
		
			dfs_visit(graf, graf->v[i],Op);
		}
	}
}

void swap(int* a, int* b)
{
    int aux;
    aux = *a;
    *a = *b;
    *b = aux;
}

void muchii(node* nod1, node* nod2)//functia care face lagatura dintre doua varfuri
{
    if (nod1->adjsize == 0) {//vedem daca are vecini
        nod1->adj = (node**)malloc(sizeof(node*));
    }
    else {
        nod1->adj = (node**)realloc(nod1->adj, (nod1->adjsize + 1) * sizeof(node*));//stabilim legatura dintre nodul x si nodul y
    }

    nod1->adj[nod1->adjsize] = nod2;//y va deveni vecinul lui x
    nod1->adjsize++;//crestem nr de vecini
}

int random(int n)//face un nr random
{
    int x = rand() % n;
    return x;

}



void demo()
{
    Operation op = p.createOperation("dfs", 9);
    graf* graph = (graf*)malloc(sizeof(graf));//alocam memorie pt graf

    graph->nrnodes = 9;//numarul de noduri
    graph->v = (node**)malloc(9 * sizeof(node*));//alocam memorie pt vectorul de noduri

    for (int i = 0; i < graph->nrnodes; i++)
    {
        graph->v[i] = (node*)malloc( sizeof(node));//alocam memorie pt nod
        graph->v[i]->adjsize = 0;//facem adjsize=0;
        graph->v[i]->color = COLOR_WHITE;//coloramin alb pt inceput
        graph->v[i]->parent = -1;//i am dat parintele -1
        graph->v[i]->key = i;//ceheie reprexinta i ul


    }

    muchii(graph->v[0], graph->v[1]);//legam nodurile intre ele
    muchii(graph->v[3], graph->v[2]);
    muchii(graph->v[1], graph->v[3]);
    muchii(graph->v[0], graph->v[4]);
    muchii(graph->v[4], graph->v[5]);
    muchii(graph->v[5], graph->v[6]);
    muchii(graph->v[5], graph->v[7]);
    muchii(graph->v[3], graph->v[7]);
    muchii(graph->v[7], graph->v[8]);
    muchii(graph->v[5], graph->v[8]);

    dfs(graph,op);//apelam dfs ul pe graf
    printf("Vectorul de parinti este: \n");

    for (int i = 0; i < graph->nrnodes; i++)
    {
        printf("%d cu parinte %d \n ", graph->v[i]->key,graph->v[i]->parent);//am apelat fiecare nod cu parintelel lui
    }
    printf("\n");
    printf("timpii de descoperire sunt:\n");
  
    for (int i = 0; i < graph->nrnodes; i++)
    {
        printf("%d  ", graph->v[i]->discoverytime);
    }
    printf("\n");
    printf("\n");
    printf("timpii de finalizare sunt: \n");
  
    for (int i = 0; i < graph->nrnodes; i++)
    {
        printf("%d  ", graph->v[i]->finishtime);
    }
    //soratrea topologica
   int ind[9];
   int timp[9];

    for (int i = 0; i < 9; i++)
    {
        ind[i] = i;
        timp[i] = graph->v[i]->finishtime;

        
    }

    for (int i = 0; i <= 9; i++)
    {
        for (int j = 0; j <= 9; j++)
        {
            if (timp[i] > timp[j])
            {
                int aux = timp[i];
                timp[i] = timp[j];
                timp[j] = aux;
                aux = 0;
                aux = ind[i];
                ind[i] = ind[j];
                ind[j] = aux;

            }
        }

    }

  

    printf("\n");
    printf("\n");
    printf("Nodurile sortate topologic:");
    printf("\n");
    printf("\n");
    for (int i = 0; i < 9; i++)
    {
        printf("timp %d cu indice %d \n", timp[i], ind[i]);
    }






}
/// <summary>
/// afisarea topologica se face descrescator dupa timpul de finalizare
/// </summary>

void performance()
{
    int n, i;
  

    //variaza nr de muchii ale grafului


    for (n = 1000; n <= 4500; n += 100) {//muchiile merg de la 1000 la 4500 cu pas de 100
        Operation op = p.createOperation("bfs-edges", n);
        graf graph;
        graph.nrnodes = 100;//numarul de noduri l am pus 100
        graph.v = (node**)malloc(graph.nrnodes * sizeof(node*));
        //am initializat vectorul de noduri
        for (i = 0; i < graph.nrnodes; ++i) {
            graph.v[i] = (node*)malloc(sizeof(node));
            memset(graph.v[i], 0, sizeof(node));
        }
        //mergem prin toate nodurile si le alocam memorie

        int adiacenta[200][200] = { 0 };
        //facem matricea pt a putea verfiica daca e legatura intre acele noduri

        //mergem de la 0 la numarul de muchii
        for (int i = 0; i < n; i++)
        {
            //generam doua noduri diferite
            int x = random(graph.nrnodes);
            int y = random(graph.nrnodes);

            //verficam daca exista legatura de la nodul x la nodul y
            while (adiacenta[x][y] == 1 || x == y )
            {
                x = random(graph.nrnodes);
                y = random(graph.nrnodes);
                //daca exista legatura intre ele geenram altele doua

            }
            adiacenta[x][y] = 1;
            //facem legatura in matrice de la nodul x la nodul y

            muchii(graph.v[x], graph.v[y]);
            //legam muchiile noi intre cele doua noduri
        }


        dfs(&graph,op);//facem dfs ul
      
    }

    // n reprezinta nr de varfuri
    for (n = 100; n <= 200; n += 10) {//nr de varfuri variaza de la 100 la 200 cu pas de 10
        Operation op = p.createOperation("bfs-vertices", n);
        graf graph;
        graph.nrnodes = n;
        //atribuim nr de noduri ale grafului
        graph.v = (node**)malloc(graph.nrnodes * sizeof(node*));
        //am alocat memorie pt vectorul de noduri
        for (i = 0; i < graph.nrnodes; ++i) {
            graph.v[i] = (node*)malloc(sizeof(node));
            memset(graph.v[i], 0, sizeof(node));
        }
        //am alocat memorie pt fiecare nod in parte

      
        int adiacenta[400][400] = { 0 };
        //lista de adiacenta pt a verifica daca exista legatura intre noduri

        //facem 4500 de muchii
        for (int i = 0; i < 4500; i++)
        {
            int x = random(graph.nrnodes);
            int y = random(graph.nrnodes);

            while (adiacenta[x][y] == 1 || x == y )
            {
                x = random(graph.nrnodes);
                y = random(graph.nrnodes);

            }
            adiacenta[x][y] = 1;
           

            muchii(graph.v[x], graph.v[y]);
        }

        dfs(&graph,op);
        
    }

    p.showReport();
}

int main()
{
    demo();
   // performance();
    return 0;
}