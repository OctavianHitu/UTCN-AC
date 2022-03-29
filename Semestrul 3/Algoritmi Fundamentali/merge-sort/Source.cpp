#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include "Profiler.h"
#define MAX_SIZE 1000
#define STEP_SIZE 100
#define NR_TESTS 5

Profiler p("ksort");

typedef struct node {
	int data;
	struct node* next;
	struct node* prev;
}nodet;

nodet* first, * last;

void swap(nodet* a, nodet* b)
{
	nodet* aux = (nodet*)malloc(sizeof(nodet));
	*aux = *a;
	*a = *b;
	*b = *aux;

}

void minheapify(nodet* arr[], int n, int i,Operation C_BU,Operation A_BU)
{
	//Operation C_BU = p.createOperation("COMP_BU", n);
	//Operation A_BU = p.createOperation("ATR_BU", n);

	int smallest = i;//am initializat radacina
	int left = 2 * i + 1;//copil stanga
	int right = 2 * i + 2;//copil dreapta

	C_BU.count();
	if (left < n && arr[left]->data < arr[smallest]->data)
	{
		smallest = left;
	}

	C_BU.count();

	if (right < n && arr[right]->data < arr[smallest]->data)
	{
		smallest = right;
	}
	if (smallest != i)
	{
		swap(arr[i], arr[smallest]);
		A_BU.count(3);
		minheapify(arr, n, smallest,C_BU,A_BU );

	}
}

void buildheap(nodet* arr[], int n,Operation C_BU,Operation A_BU)
{
	
	for (int i = n / 2 - 1 ; i >= 0; i--)
	{
		minheapify(arr, n, i,C_BU,A_BU);

	}
}

void insertelement(nodet** head, int data)
{
	nodet* aux = new nodet;
	nodet* p;
	aux->data = data;
	aux->next = NULL;

	if (*head == NULL)
	{
		*head = aux;
	}
	else
	{
		p = *head;
		while (p->next != NULL)
		{
			p = p->next;

		}
		p->next = aux;//iterez in lista pana gasesc null pt a pune in loc
	}
}

nodet* arraytolist(int arr[], int n)//face di fiecare vector o lista
{
	nodet* head = NULL;
	for (int i = 0; i < n; i++)
	{
		insertelement(&head, arr[i]);
	}
	return head;
}

void mergelists(nodet* arr[], int mergedarr[], int k, int n,Operation C_BU,Operation A_BU)
{
	int size = 0;
	nodet* heap[MAX_SIZE];
	int heapsize = 0;

	for (int i = 0; i < k; i++)
	{
		if (arr[i]->next)
		{
			heap[heapsize] = arr[i];
			A_BU.count();
			heapsize++;
		}

	}

	buildheap(heap, heapsize,C_BU,A_BU);

	while (heapsize != 0)
	{
		mergedarr[size] = heap[0]->data;//radacina se pune in vector
		A_BU.count();
		size++;
		C_BU.count();
		if (heap[0]->next != NULL)//trece prin array
		{
			heap[0] = heap[0]->next;
			A_BU.count();
		}
		else
		{
			heap[0] = heap[heapsize - 1];
			A_BU.count();
			--heapsize;
		}
		buildheap(heap, heapsize,C_BU,A_BU);
	}
}

void printdata(nodet* head)
{
	while (head != NULL)
	{
		printf("%d ", head->data);
		head = head->next;

	}
	printf("\n");
}


void generatelists(nodet** list, int k, int n)
{
	int* arr = (int*)malloc(sizeof(int) * n);
	for (int i = 0; i < k; i++)
	{
		FillRandomArray(arr, n, 10, 50000, true, ASCENDING);
		list[i] = arraytolist(arr, n);
		printdata(list[i]);
	}
}

void demo()
{
	int n = 40;
	int k = 4;
	Operation C_BU = p.createOperation("comp", n);
	Operation A_BU = p.createOperation("atr", n);


	int size = n / k;
	//int arr[40];
	nodet** a = (nodet**)malloc(sizeof(nodet*) * 4);
	int* mergedarr = (int*)malloc(sizeof(int) * 40);
	for (int i = 0; i < k; i++)
	{
		a[i] = NULL;
	}
	
	generatelists(a, 4, 10);
	mergelists(a, mergedarr, 4, 10,C_BU,A_BU);
	for (int i = 0; i < n; i++)
	{
		printf("%d  ", mergedarr[i]);
	}

}

void perf()
{
	nodet* arr[MAX_SIZE];
	for (int k = 10; k <= 500; k += 10)
	{
		int mergedarr[MAX_SIZE];
		Operation opcomp = p.createOperation("opcomp", k);
		Operation opatr = p.createOperation("opatr", k);

		for (int test = 0; test < NR_TESTS; test++)
		{
			generatelists(arr, k, MAX_SIZE / k);
			mergelists(arr, mergedarr, k, MAX_SIZE / k, opcomp,opatr);

			
		}
	}
	p.divideValues("opcomp", NR_TESTS);
	p.divideValues("opatr", NR_TESTS);
	p.addSeries("operatii", "opcomp", "opatr");
	p.showReport();
 }
 
void perfmultik()
{
	nodet* arr5[5], * arr10[10], * arr100[100];
	int mergedarr5[MAX_SIZE], mergedarr10[MAX_SIZE], mergedarr100[MAX_SIZE];
	for (int n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		Operation opc5 = p.createOperation("comp5", n);
		Operation opa5 = p.createOperation("atr5", n);
		Operation opc10 = p.createOperation("comp10", n);
		Operation opa10 = p.createOperation("atr10", n);
		Operation opc100 = p.createOperation("comp100", n);
		Operation opa100 = p.createOperation("atr100", n);
		for (int t = 0; t < NR_TESTS; t++)
		{
			generatelists(arr5, 5, n / 5);
			generatelists(arr10,10, n / 10);
			generatelists(arr100, 100, n / 100);
			
			mergelists(arr5, mergedarr5, 5, n / 5, opc5, opa5);
			mergelists(arr10, mergedarr10, 10, n / 10, opc10, opa10);
			mergelists(arr100, mergedarr100, 100, n / 100, opc100, opa100);

		}

	}
	p.divideValues("comp5", NR_TESTS);
	p.divideValues("atr5", NR_TESTS);
	p.divideValues("comp10", NR_TESTS);
	p.divideValues("atr10", NR_TESTS);
	p.divideValues("comp100", NR_TESTS);
	p.divideValues("atr100", NR_TESTS);

	p.addSeries("total5", "comp5", "atr5");
	p.addSeries("total10", "comp10", "atr10");
	p.addSeries("total100", "comp100", "atr100");

	p.createGroup("total", "total5", "total10", "total100");

	p.showReport();
}


int main()
{
	//demo();
	perf();
	p.reset("new");
	perfmultik();
	return 0;
}