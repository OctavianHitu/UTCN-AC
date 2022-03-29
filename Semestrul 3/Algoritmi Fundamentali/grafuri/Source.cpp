#include <iostream>
#include <stdlib.h>
#define _CRT_SECURE_NO_WARNINGS
int n;
#include "Profiler.h"

#define MAX_SIZE 1000
#define STEP_SIZE 100

Profiler p("graf");

typedef struct node {
	int key;
	int rank;
	struct node* parent;
} nodet;

typedef struct edge {
	nodet* head;
	nodet* tail;
	int lung;
}edget;

typedef struct graf {
	int nrvarf;
	int nredge;
	edget* edges;
}graft;

int counter;
edget* minsptree;



node* makeset(int key)
{	//creaza doar unn nod si i se atribuie valoare sa: key
	p.countOperation("makeset", n, 3);

	nodet* nod = (nodet*)malloc(sizeof(nodet));

	
	nod->parent = nod;
	nod->key = key;
	nod->rank = 0;

	return nod;

}

nodet* findset(nodet* nod)
{
	p.countOperation("findset", n, 1);
	if (nod != nod->parent)
	{
		nod->parent = findset(nod->parent);
		p.countOperation("findset", n, 1);
	}

	return nod->parent;
}

void link(nodet* nod1, nodet* nod2)
{
	//linkset ul leaga cele doua noduri 

	p.countOperation("unionset", n, 1);
	if (nod1->rank = nod2->rank)
	{
		nod2->parent = nod1;
		p.countOperation("unionset", n, 1);
	}
	else
	{
		p.countOperation("unionset", n, 1);
		nod1->parent = nod2;
		p.countOperation("unionset", n, 1);
		if (nod1->rank == nod2->rank)
		{
			p.countOperation("unionset", n, 1);
			nod2->rank = nod2->rank + 1;
		}
	}
}





void unionset(nodet* nod1, nodet* nod2)
{
	link(findset(nod1), findset(nod2));


}


graft* crearegraf(int nrvf, int nredge)
{
	graft* graf = (graft*)malloc(sizeof(graft)); 
	graf->edges = (edget*)malloc(nredge * sizeof(edget));
	graf->nrvarf = nrvf;
	graf->nredge = nredge;

	//facem un graf nou si atribuim nr de varfuri si nr de edges printr un 
	//vector de edges

	return graf;

}

void swapelem(edget nod1, edget nod2)
{
	edget aux = nod1;
	nod1 = nod2;
	nod2 = aux;

}

int partitionquicksort(edget edges[], int lf, int ri)
{
	int piv = edges[ri].lung;
	int i = lf - 1;
	for (int j = lf; j <= ri - 1; j++)
	{
		if (piv > edges[j].lung)
		{
			i++;
			swapelem(edges[i],edges[j]);
		}
	}

	swapelem(edges[i + 1], edges[ri]);
	return i + 1;

}


void quicksort(edget arr[], int lf, int ri)
{
	if (lf < ri)
	{
		int piv = partitionquicksort(arr, lf, ri);
		quicksort(arr, lf, piv - 1);
		quicksort(arr, piv + 1, ri);
	}
}



void kruskal(graft* graf)
{
	counter= 0;
	int nrvarfuri = graf->nrvarf;
	int nredges = graf->nredge;

	for (int v = 1; v <= nrvarfuri; v++)
	{
		makeset(nrvarfuri);
	}

	minsptree = (edget*)malloc(nredges * sizeof(edge));

	quicksort(graf->edges, 0, graf->nredge -1);
	//parcurgem vectorul de muchii si cand avem deja toate muchiile se opreste si nu va mai parcurge
	for (int i = 0; i < nredges&& counter<nrvarfuri; i++)
	{
		
		edget grafaux = graf->edges[i];


		if (findset(grafaux.head) != findset(grafaux.tail))
		{

			minsptree[counter++] = grafaux;
			unionset(findset(grafaux.head), findset(grafaux.tail));
		}



	}


}


void demo()
{

	//Operation oper = p.createOperation("Operatie_multimi", n);
	nodet** arrx = (nodet**)malloc(5 * sizeof(nodet));

	int arr[5] = { 1,2,3,4,5 };

	for (int i = 0; i < 5; i++)
	{
		arrx[i] = makeset(arr[i]);
		printf("%d  ", arrx[i]->parent->key);
	}
	printf("\n");


	unionset(arrx[0], arrx[4]);
	unionset(arrx[3], arrx[1]);
	



	for (int i = 0; i < 5; i++)
	{
		printf("%d->%d  ",arrx[i]->key, findset(arrx[i])->parent->key);
	}

	int nrvfuri = 5;
	int nre = 10;

	nodet** varfuri = (nodet**)malloc((nrvfuri + 1) * sizeof(nodet*));
	graf* grafaux = crearegraf(nrvfuri, nre);


	for (int i = 1; i <= nrvfuri; i++)
	{
		varfuri[i] = makeset(i);

	}


	grafaux->edges[0].head = varfuri[1];
	grafaux->edges[0].tail = varfuri[2];
	grafaux->edges[0].lung = 7;

	grafaux->edges[1].head = varfuri[2];
	grafaux->edges[1].tail = varfuri[3];
	grafaux->edges[1].lung = 8;


	grafaux->edges[2].head = varfuri[3];
	grafaux->edges[2].tail = varfuri[4];
	grafaux->edges[2].lung = 7;

	grafaux->edges[3].head = varfuri[4];
	grafaux->edges[3].tail = varfuri[5];
	grafaux->edges[3].lung = 9;

	grafaux->edges[4].head = varfuri[1];
	grafaux->edges[4].tail = varfuri[5];
	grafaux->edges[4].lung = 10;

	grafaux->edges[5].head = varfuri[1];
	grafaux->edges[5].tail = varfuri[4];
	grafaux->edges[5].lung = 13;

	grafaux->edges[6].head = varfuri[1];
	grafaux->edges[6].tail = varfuri[3];
	grafaux->edges[6].lung = 14;

	grafaux->edges[7].head = varfuri[2];
	grafaux->edges[7].tail = varfuri[5];
	grafaux->edges[7].lung = 12;

	grafaux->edges[8].head = varfuri[3];
	grafaux->edges[8].tail = varfuri[5];
	grafaux->edges[8].lung = 11;

	grafaux->edges[9].head = varfuri[2];
	grafaux->edges[9].tail = varfuri[4];
	grafaux->edges[9].lung = 10;


	kruskal(grafaux);
	printf("\n");

	for (int i = 0; i < counter; i++)
	{
		printf("%d-%d lungime egala cu:%d \n", minsptree[i].head->key, minsptree[i].tail->key,minsptree[i].lung);
	}

	
}


void perf()
{
	for ( n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		//Operation opermake = p.createOperation("Operatie_multimi1", n);
		//Operation operunion = p.createOperation("Operatie_multimi2", n);
		//Operation operfind = p.createOperation("Operatie_multimi3", n);

		graft* graf = crearegraf(n, 4 * n);
		nodet** varfuri = (nodet**)malloc((n + 1) * sizeof(nodet*));
		int lungimi[40001];
		FillRandomArray(lungimi, 4 * n, 1, 40002, false, 0);

		for (int i = 1; i < n; i++)
		{
			varfuri[i] = makeset(i);
		}


		for (int i = 0; i < 4 * n; i++)
		{
			graf->edges[i].head = varfuri[(rand() % n) + 1];
			graf->edges[i].tail = varfuri[(rand() % n) + 1];
			graf->edges[i].lung = lungimi[i];


		}
		kruskal(graf);
	}
	p.createGroup("toate","makeset", "findset", "unionset");
	p.showReport();
}

int main()
{

	demo();
	//perf();
	return 0;
}
