#include<iostream>
#include<stdio.h>
#include "Profiler.h"


#define MAX_SIZE 1000
#define STEP_SIZE 100
#define NR_TESTS 5

Profiler p("quick and select");

//heapsort
//heap build bottom up pt  a putea folosi la heapsort
void swap(int* a, int* b)
{
	int aux;
	aux =*a;
	*a =*b;
	*b = aux;
}
//bottom up
void heapify(int v[], int n, int i, Operation heapsortcomp, Operation heapsortatr)//bottom up
{
	int max = i;
	int left = 2 * i + 1;
	int right = 2 * i + 2;


	heapsortcomp.count();
	if (left<n && v[left]>v[max])
	{
		max = left;
	}
	heapsortcomp.count();
	if (right<n && v[right]>v[max])
	{
		max = right;
	}
	if (max != i)
	{
		swap(&v[i], &v[max]);
		heapsortatr.count();
		heapify(v, n, max, heapsortcomp, heapsortatr);

	}
}

void buildheap(int v[], int n,Operation heapsortcomp,Operation heapsortatr)
{
	
	for (int i = n / 2 - 1; i >= 0; i--)
	{
		heapify(v, n, i, heapsortcomp, heapsortatr);
	}
}
//final bottom up

//heapsort
void heapsort(int v[], int n)
{
	Operation heapsortcomp = p.createOperation("heapsortcomp", n);
	Operation heapsortatr = p.createOperation("heapsortatr", n);
	buildheap(v, n,heapsortcomp,heapsortatr);

	for (int i = n - 1; i >= 0; i--)
	{
		swap(&v[0], &v[i]);
		heapsortatr.count(3);
		heapify(v, i, 0, heapsortcomp, heapsortatr);
		

	}
}
//final heapsort

//quick sort
int partition(int arr[], int left, int right, int n)
{
	Operation quickcmp = p.createOperation("quickcmp", n);
	Operation quickatr = p.createOperation("quickatr", n);

	int pivot = arr[right];
	quickatr.count();

	int j = left - 1;

	for (int i = left; i <= right - 1; i++)
	{
		quickcmp.count();
		if (pivot > arr[i])
		{
			j++;
			swap(&arr[i], &arr[j]);
			quickatr.count(3);

		}

	}

	swap(&arr[right], &arr[j + 1]);
	quickatr.count(3);
	return (j + 1);

}



void quicksort(int arr[], int left, int right, int n)
{
	if (left < right)
	{
		int pivot;
		pivot = partition(arr, left,right,n);

		quicksort(arr, left, pivot - 1,n);
		quicksort(arr,  pivot + 1, right,n);

	}

}
//final quicksort


//quick-select
int random(int left, int right)
{
	int rnumber = (rand() % (right - left + 1)) + right;
	return rnumber;
}

int randompartition(int arr[], int left, int right,int n)
{
	int rdm = random(left, right);
	swap(&arr[left], &arr[right]);
	return partition(arr, left, right, n);
}

int quickselect(int arr[], int left, int right,int i, int n)
{
	if (left == right)
	{
		return arr[left];

	}
	int rpart = randompartition(arr, left, right, n);
	int k = rpart - left + 1;
	if (i == k)
	{
		return arr[rpart];
	}
	else if (i < k)
	{
		quickselect(arr, left, rpart-1, i, n);
	}
	else
	{
		quickselect(arr,rpart+1,right,i-k,n);
	}

}

void demoheapsort()
{
	int v[] = { 2,5,3,9,7,6,10 };
	int n = sizeof(v) / sizeof(v[0]);
	heapsort(v, n);
	for (int i = 0; i < n; i++)
	{
		printf("%d", v[i]);
	}
}

void demoquicksort()
{
	int v[] = { 2,5,3,9,7,6,10 };
	int n = sizeof(v) / sizeof(v[0]);
	quicksort(v, 0,n-1,n);
	for (int i = 0; i < n; i++)
	{
		printf("%d", v[i]);
	}
}


void quickselectdemo()
{
	int v[] = { 1,7,6,8,2,9,3,11,25,5 };
	int n = sizeof(v) / sizeof(v[0]);
	printf("\n%d\n", quickselect(v, 0, n - 1, 4, n));
}

void perf(int order)
{
	int v[MAX_SIZE];
	int a[MAX_SIZE];


	for (int n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		for (int test = 0; test <= NR_TESTS; test++)
		{
			FillRandomArray(v, n);
			for (int i = 0; i <= n; i++)
			{
				a[i] = v[i];
				
			}
			heapsort(v, n);
			quicksort(a, 0,n-1,n);
			

		}
	}

	p.divideValues("heapsortcomp", NR_TESTS);
	p.divideValues("heapsortatr", NR_TESTS);

	p.divideValues("quickcmp", NR_TESTS);
	p.divideValues("quickatr", NR_TESTS);

	p.createGroup("heap/quick comp", "heapsortcomp", "quickcmp");
	p.createGroup("heap/quick atr", "heapsortatr", "quickatr");


	p.showReport();

}

void perfworst(int order)
{
	int v[MAX_SIZE];


	for (int n = STEP_SIZE; n <= MAX_SIZE; n += STEP_SIZE)
	{
		for (int test = 0; test <= NR_TESTS; test++)
		{
			FillRandomArray(v, n);
			quicksort(v, 0, n - 1, n);


		}
	}


	p.divideValues("quickcmp", NR_TESTS);
	p.divideValues("quickatr", NR_TESTS);

	p.createGroup("Total", "quickcmp", "quickatr");


	p.showReport();

}

void perfall()
{
	perf(UNSORTED);
	p.reset("worst case");
	perfworst(ASCENDING);
}


int main()
{
	//demoheapsort();
	//demoquicksort();
	//quickselectdemo();
	perfall();
	return 0;
}