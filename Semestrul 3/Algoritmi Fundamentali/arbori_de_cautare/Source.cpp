// lab 7 :statistici dinamice de ordine u arbori binari de cautare

#define _CRT_SECURE_NO_WARNINS

#include <stdio.h>
#include <stdlib.h>
#include "Profiler.h"


#define COUNT 3

#define STEP_SIZE 100
#define MAX_SIZE 1000
#define NR_TESTS 5

Profiler p("lab8");

typedef struct treen {
	int key;
	struct treen* left;
	struct treen* right;
	int size;
}treent;

treent* createnode(int key)
{
	treent* nodnou = (treent*)malloc(sizeof(treent));
	
		nodnou->key = key;
		nodnou->left = NULL;
		nodnou->right = NULL;
		nodnou->size = 0;
	
	return nodnou;

}





treent* buildtree(int* arr, int first, int last)
{
	if (first > last)
	{
		return NULL;
	}

	int mid = (first + last) / 2;
	treent* root = createnode(arr[mid]);
	root->right = buildtree(arr, mid + 1, last);
	root->left = buildtree(arr, first, mid - 1);




	if (root->right != NULL)
	{
		root->size += root->right->size;
	}
	

	if (root->left != NULL)
	{
		root->size += root->left->size;
	}
	root->size += 1;

	if (root->left == NULL && root->right == NULL)
	{
		root->size = 1;

	}

	return root;

	
}



void printtree(treent* a, int lvl)
{
	if (a == NULL)
	{
		return;
	}

	lvl +=COUNT;

	printtree(a->left, lvl);
	printf("\n");

	for (int i = COUNT; i < lvl; i++)
	{
		printf("  ");
	}
	printf("%d(%d)\n", a->key,a->size);

	printtree(a->right, lvl);
}






treent* osselect(treent* nod, int i,Operation selc ,Operation sela)
{
	int r;
	selc.count();
	if (nod->left != NULL)
	{
		r = nod->left->size + 1;
	}
	else
	{
		r = 1;
	}

	if (i == r)
	{
		return nod;
	}
	else if(i<r)
	{	 

		return osselect(nod->left, i,selc,sela);
	}
	else
	{
		return osselect(nod->right, i-r,selc,sela);
	}
}

treent* minvaln(treent* nod)//mergem cel mai mult in staga
{
	
	while ( nod != NULL &&  nod->left != NULL)
	{
		nod= nod->left;
	}
	return nod;
}


treent* deletenode(treent* nod, int key,Operation delc,Operation dela)
{
	if (nod == NULL)
	{
		delc.count();
		return nod;
	}
	if (key <nod->key)//daca cheia e mai mica decat root trece pe subtree ul stang
	{	
		delc.count();
		nod->size--;
		nod->left = deletenode(nod->left, key,delc,dela);
		dela.count();
	}
	else if(key> nod->key)//la fel pt dreapta
	{
		delc.count();
		nod->size--;
		nod->right = deletenode(nod->right, key,delc,dela);
		dela.count();
	}
	else //daca cheia e la fel cu a root ului inseamna ca asta e nodul
	{//vedem daca nodul are un copil sau niciunul
		if (nod->left == NULL)
		{
			delc.count();
			treent* nod1 = nod->right;
			free(nod);
			return nod1;
			dela.count();
		}
		else if (nod->right == NULL)
		{
			delc.count();
			treent* nod1 = nod->left;
			free(nod);
			return nod1;
			dela.count();
		}
		else
		{//daca are 2 copii mergem la cel mai mic din partea dreapta
			treent* nod1 = minvaln(nod->right);
			nod->key = nod1->key;
			nod->size--;
			nod->right = deletenode(nod->right, nod1->key,delc,dela);
			dela.count(3);
		}
	}
	return nod;
}




void demo()
{
	

	int n = 10;
	int arr[] = { 0,1,2,3,4,5,6,7,8,9,10,11 };

	Operation selc = p.createOperation("select_comp", n);
	Operation sela = p.createOperation("select_atr", n);
	Operation delc = p.createOperation("delete_comp", n);
	Operation dela = p.createOperation("delete_atr", n);


	treent* rot = buildtree(arr, 1,11);
	printtree(rot, 0);
	printf("\n\n");

	//printf("Cheia este:\n");
	//treent* aux = osselect(rot, 1,selc,sela);
	//printf("%d  \n\n", aux->key);

	//printf("Noul arbore e:\n");
	//treent* aux2 = deletenode(rot, 3,delc,dela);
	//printtree(rot, 0);

	for (int i = 1; i <= 3; i++)
	{
		int nrrand = (rand() % n) + 1;

		printf("Cheia este:\n");
		treent* aux = osselect(rot, nrrand,selc,sela);
		printf("%d  \n\n", aux->key);

				

	}
	printf("\n\n");
	for (int i = 1; i <= 3; i++)
	{
		int nrrand = (rand() % n) + 1;
		printf("%d se va scoate\n", nrrand);

		printf("Noul arbore e:\n");
		treent* aux2 = deletenode(rot, nrrand,delc,dela);
		printtree(rot, 0);
		printf("\n");

	}

}

void perf()
{
	int n;

	for (n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		for (int test = 0; test < NR_TESTS; test++)
		{

			Operation selc = p.createOperation("select_comp", n);
			Operation sela = p.createOperation("select_atr", n);
			Operation delc = p.createOperation("delete_comp", n);
			Operation dela = p.createOperation("delete_atr", n);

			int nre = n;

			int arr[MAX_SIZE];
			FillRandomArray(arr, MAX_SIZE, 10, 1000, true, ASCENDING);

			treent* root = buildtree(arr, 0, n - 1);
			for (int i = 1; i <= n; i++)
			{
				int nrrand = (rand() % nre) + 1;

				treent* aux3 = osselect(root, nrrand, selc, sela);
				aux3 = deletenode(root, nrrand, delc, dela);
			}
		}
	}

	p.divideValues("select_comp", NR_TESTS);
	p.divideValues("select_atr", NR_TESTS);
	p.divideValues("delete_comp", NR_TESTS);
	p.divideValues("delete_atr", NR_TESTS);
	p.createGroup("select", "select_comp", "selet_atr");
	p.createGroup("delete", "delete_comp", "edlete_atr");
	p.showReport();

}

int main()
{
	//demo();
	perf();
	return 0;

}
