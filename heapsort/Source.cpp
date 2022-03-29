#include <stdio.h>
#include<math.h>
#define MAX_SIZE 1000
#define STEP_SIZE 100
#define NR_TESTS 5
#include "Profiler.h"

Profiler  p("biuldheap heapsort");

void swap(int* x, int* y)
{
	int aux;
	aux = *x;
	*x = *y;
	*y = aux;

}




void heapify(int v[], int n, int i, Operation BUPcomp, Operation BUPatr)//bottom up
{
	int max = i;
	int left = 2 * i + 1;
	int right = 2 * i + 2;


	BUPcomp.count();
	if (left<n && v[left]>v[max])
	{
		max = left;
	}

	BUPcomp.count();
	if (right<n && v[right]>v[max])
	{
		max = right;
	}
	if (max != i)
	{
		swap(&v[i], &v[max]);
		BUPatr.count(3);
		heapify(v, n, max, BUPcomp, BUPatr);

	}
}

void buildheap(int v[], int n)
{
	Operation BUPcomp = p.createOperation("BottomUpComparatie", n);
	Operation BUPatr = p.createOperation("BottomUpAtribuire", n);
	for (int i = n / 2 - 1; i >= 0; i--)
	{
		heapify(v, n, i, BUPcomp, BUPatr);
	}
}





 void heapincreasekey(int v[], int i, int key, Operation TOPDcomp, Operation TOPDatr)
{
	TOPDcomp.count();
	if (key < v[i])
	{
		printf("Noua cheie e mai mica de cea curenta ");

	}
	v[i] = key;
	TOPDatr.count();
	while (i > 0 && v[(i - 1) / 2] < v[i])
	{
		TOPDcomp.count();
		swap(&v[i], &v[(i - 1) / 2]);
		TOPDatr.count(3);
		i = (i - 1) / 2;

	}
	if (i > 0)
	{
		TOPDcomp.count();

	}
	
}

void maxheapinsert(int v[], int key, int heapsize, Operation TOPDcomp, Operation TOPDatr)
{
	v[heapsize] = -INFINITY;
	TOPDatr.count();
	heapincreasekey(v, heapsize, key, TOPDcomp, TOPDatr);
}

void buildmaxheap(int v[], int n)//constructie top-down
{
	Operation TOPDcomp = p.createOperation("TOPDwoncomp", n);
	Operation TOPDatr = p.createOperation("TOPDownatr", n);
	for (int i = 1; i < n; i++)
	{
		maxheapinsert(v, v[i], i, TOPDcomp, TOPDatr);

	}
}




void heapsort(int v[], int n)
{
	Operation heapsortcomp = p.createOperation("heapsortcomp", n);
	Operation heapsortatr = p.createOperation("heapsortatr", n);
	buildheap(v,n);

	for (int i = n - 1; i >= 0; i--)
	{
		swap(&v[0], &v[i]);
		heapsortatr.count(3);
		heapify(v, i, 0, heapsortcomp, heapsortatr);
		heapsortatr.count();

	}
}

void buildheapdemo()//botoomup
{
	int v[] = { 2,5,3,9,7,6,10 };
	int n = sizeof(v) / sizeof(v[0]);
	buildheap(v, n);
	for (int i = 0; i < n; i++)
	{
		printf("%d ", v[i]);
	}
}

void demo()//heapsort
{
	int v[] = { 3,9,10,25,1,2 };
	int n = sizeof(v) / sizeof(v[0]);
	heapsort(v, n);
	for (int i = 0; i < n; i++)
	{
		printf("%d ", v[i]);

	}
}

void topdowndemo()
{
	int v[] = { 2,5,1,4,6,8,10 };
	int n = sizeof(v) / sizeof(v[0]);
	buildmaxheap(v,n);
	for (int i = 0; i < n; i++)
	{
		printf("%d  ", v[i]);
	}
}

void perf()
{
	int v[MAX_SIZE];
	int a[MAX_SIZE];
	for (int n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		for (int test = 1; test <= 5; test++)
		{
			FillRandomArray(v, n);
			for (int i = 0; i <= n; i++)
			{
				a[i] = v[i];
			}
			buildheap(v, n);
			heapsort(v, n);
			buildmaxheap(a, n);
			for (int i = 0; i < n; i++)
			{
				printf("%d ", v[i]);
			}
		}
	}
	p.showReport();
}

int main()
{
	//demo();
	//buildheapdemo();
	//printf("/n");
	//topdowndemo();
	perf();
	return 0;
}
