#define _CRT_SECURE_NO_WARNINGS
#include<stdlib.h>
#include<stdio.h>
#include"Profiler.h"
#include<conio.h>
#include<string.h>


#define MAX_SIZE 10007
#define STEP_SIZE 100
#define NR_TESTS 5
#define m 3000


Profiler p("hashtable");


void initializarehash(int hash[], int n)
{
	for (int i = 0; i < n; i++)
	{
		hash[i] = -1;
	}
}

int auxhash(int key)
{
	int aux = key % MAX_SIZE;
	return aux;

}


int quadprobing(int key, int i)
{
	int x = auxhash(key);
	int aux = x + 2* i + 3 * i * i;
	return aux% MAX_SIZE;

}


int inserthash(int key, int hashtable[])
{
	int aux = MAX_SIZE;
	int i = 0;
	do 
	{
		int j = quadprobing(key, i);
		if (hashtable[j]== -1)
		{
			hashtable[j] = key;
			
			return j;
		}
		else
		{
			i++;
		}
	} while(i != aux);

	if(i==aux)
	{
		printf("\nFulltable");
	}
	return -1;
}

int hashsearch(int key, int hashtable[], double &effort)
{
	int i = 0;
	int j;
	int aux = MAX_SIZE;
	do {

		++effort;
		 j = quadprobing(key, i);
		if (hashtable[j] == key)
		{
			return j;

		}
		else
		{
			i++;

		}
	} while ((hashtable[j] != -1) && (i!=aux));

	return -1;

}


void demo()
{
	double aux = MAX_SIZE;
	int* t = (int*)malloc(sizeof(int) * aux);
	initializarehash(t, aux);
	double effortdemo = 0;
	int arr[10] = { 13,22,7,8,10,1,2,3,4,5 };
	for (int i = 0; i < 10; i++)
	{
		inserthash(arr[i], t);

	}
	for (int i = 0; i < 10; i++)
	{
		int found = hashsearch(arr[i], t, effortdemo);
		if (found == -1)
		{
			printf("\n No element found \n");

		}
		else {
			printf("Number found is %d \n", t[found]);
		}
	}
}

void perf()
{
	double maxeffortfound = 0;
	double avgeffortfound = 0;
	double maxeffortnotfound = 0;
	double avgeffortnotfound = 0;
	double alfa[5] = { 0.8,0.85,0.9,0.95,0.99 };
	int hashtable[MAX_SIZE];
	double totaleffort = 0;
	double totaleffortnf = 0;

	for (int i = 0; i< 5; i++)
	{
		for (int t = 0; t < NR_TESTS; t++)
		{
			float fillf = alfa[i];
			
			int n = floor(MAX_SIZE * fillf);
			initializarehash(hashtable, MAX_SIZE);

			int *arr = (int*)malloc(sizeof(int) * n);
			
			FillRandomArray(arr, n, 10, 50000, true, UNSORTED);

			for (int i = 0; i < n; i++)
			{
				inserthash(arr[i], hashtable);

			}

			int *foundarr = (int*)malloc(sizeof(int) * (m / 2));
			int *notfoundarr = (int*)malloc(sizeof(int) * (m / 2));

			for (int i = 0; i < m / 2; i++)
			{
				foundarr[i] = arr[rand() % n];

			}
			FillRandomArray(notfoundarr, m / 2, 10, 50000, true, UNSORTED);


			for (int i = 0; i < m / 2; i++)
			{
				double count = 0;
				hashsearch(foundarr[i], hashtable, count);

				totaleffort += count;

				if (maxeffortfound < count)
				{
					maxeffortfound = count;
				}
			}
			for (int i = 0; i < m / 2; i++)
			{
				double countnf = 0;
				hashsearch(notfoundarr[i], hashtable, countnf);

				totaleffortnf += countnf;

				if (maxeffortnotfound < countnf)
				{
					maxeffortnotfound = countnf;
				}
			}



		}
		avgeffortfound = totaleffort / (1500 * 5);//nr elemente ori nr teste
		avgeffortnotfound = totaleffortnf / (1500 * 5);

		printf(" effort for %.2f is : max %f avg %f\n", alfa[i], maxeffortfound, avgeffortfound);
		printf(" effort not found for %.2f is : max %f avg %f\n", alfa[i], maxeffortnotfound, avgeffortnotfound);


	}
}

int main()
{
	//demo();

	perf();
	return 0;
}
