/*
Best case ul-toate sunt ordonate crescator
ceel mai bune sunt bubble si insertion deoarce bubble atr are complexitatea O(1) si comp O(n);
insertion are si atr si comp O(n);
selection sort ul are o complexitate ineficienta O(n^2);

Average-aici cel mai bun algoritm este selection sort deoarece face cele mai putine atribuiri si are complexitatea O(n);

worst- aici toti algoritmii au O(n^2)-comparatii
selectiom sort ul are cele mai putinr atribuiri deci e cel mai eficient




*/



#define _CRT_SECURE_NO_WARNINGS 
#include <stdio.h>
#include "Profiler.h"
#define MAX_SIZE 1000
#define STEP_SIZE 100
#define NR_TESTS 5

Profiler p("Sorting Algorithms"); //am luat o variabila de tip profiler

void bubblesort(int v[], int n)
{
	Operation bubcomp = p.createOperation("bubblecomp", n);
	Operation bubatr = p.createOperation("bubbleatr", n);
	int verif = 0;

	for (int i = 0; i < n - 1; i++)
	{
		for (int j = 0; j < n - i - 1; j++)
		{
			bubcomp.count();
			if (v[j] > v[j + 1])
			{
				int aux = v[j];
				v[j] = v[j + 1];
				v[j + 1] = aux;
				bubatr.count();
				bubatr.count();
				bubatr.count();
				verif = 1;

			}


		}
		if (verif == 0)
		{
			break;

		}



	}


}

void insertiosort(int v[], int n)
{
	Operation insrcomp = p.createOperation("insrcomp", n);
	Operation insratr = p.createOperation("insratr", n);
	int sub, index;
	for (int i = 1; i < n; i++)
	{
		index = i - 1;
		sub = v[i];
		insratr.count();
		while (index >= 0 && sub < v[index])
		{
			insrcomp.count();
			v[index + 1] = v[index];
			insratr.count();
			index--;
			



		}
		if(index>=0)
		{
			insrcomp.count();
		}

		insratr.count();
		v[index + 1] = sub;


	}

}

void selectionsort(int v[], int n)
{
	Operation selcomp = p.createOperation("selcomp", n);
	Operation selatr = p.createOperation("selatr", n);

	int mini;
	for (int i = 0; i < n; i++)
	{
		mini = i;

		for (int j = i + 1; j < n; j++)
		{
			selcomp.count();
			if (v[mini] > v[j])
			{
				mini = j;
			}

		}


		if (mini != i)
		{
			int aux = v[mini];
			v[mini] = v[i];
			v[i] = aux;
			selatr.count();
			selatr.count();
			selatr.count();

		}


	}


}

void bubbledemo()
{
	int v[] = { 2,4,5,9,1 };
	int n = sizeof(v) / sizeof(v[0]);
	bubblesort(v, n);
	for (int i = 0; i < n; i++)
	{
		printf("%d", v[i]);
	}
	printf("\n");
}

void insertiondemo()
{
	int a[] = {3,5,1,7,9 };
	int x = sizeof(a) / sizeof(a[0]);
	bubblesort(a, x);
	for (int i = 0; i < x; i++)
	{
		printf("%d", a[i]);
	}
	printf("\n");
}

void selectiondemo()
{
	int b[] = { 2,6,8,1,4,5 };
	int n = sizeof(b) / sizeof(b[0]);
	bubblesort(b, n);
	for (int i = 0; i < n; i++)
	{
		printf("%d", b[i]);
	}
	printf("\n");
}

void perf(int order)
{
	int v[MAX_SIZE];
	int a[MAX_SIZE];
	int b[MAX_SIZE];

	for (int n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		for (int test = 0; test <= NR_TESTS; test++)
		{
		FillRandomArray(v, n, 10, 50000, false, order);
			for (int i = 0; i < n; i++)
			{
				a[i] = v[i];
				b[i] = v[i];


			}
			bubblesort(v, n);
			insertiosort(a, n);
			selectionsort(b, n);

			/*for (int i = 0; i < n; i++)
			{
				printf("%d", v[i]);
			}
			printf("\n");

			for (int i = 0; i < n; i++)
			{
				printf("%d", a[i]);
			}
			printf("\n");
			for (int i = 0; i < n; i++)
			{
				printf("%d", b[i]);
			}
			printf("\n"); */
			
		}


	}
	p.divideValues("bubbleatr", NR_TESTS);
	p.divideValues("bubblcomp", NR_TESTS);
	p.divideValues("insratr", NR_TESTS);
	p.divideValues("insrcomp", NR_TESTS);
	p.divideValues("selatr", NR_TESTS);
	p.divideValues("selcomp", NR_TESTS);
	

	p.createGroup("atr", "bubbleatr", "insratr", "selatr");
	p.createGroup("comp", "bubblecomp", "insrcomp", "selcomp");


	p.addSeries("bubsuma", "bubbleatr", "bubblecomp");
	p.addSeries("insrsuma", "insratr", "insrcomp");
	p.addSeries("selsuma", "selatr", "selcomp");

	

}
void perfall()
{

	
	
	perf(UNSORTED);//AVERAGE CASE
	p.reset("best");
	perf(ASCENDING);//BEST CASE
	p.reset("worst");
	perf(DESCENDING);//WORST CASE
	p.showReport();
}

int main()
{
	//bubbledemo();
	//insertiondemo();
	//selectiondemo();
	perfall();
	printf("\n");
	return 0;
}