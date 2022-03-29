#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

int rootind;

typedef struct childrennode {
	int key;
	childrennode** kids;
	int nrchildren;
}repr2;

repr2* root;
repr2* treem[30];

typedef struct binarytree {
	int key;
	binarytree* child;
	binarytree* brother;

}bint;

bint* createnode(int key)
{
	bint* node = (bint*)malloc(sizeof(bint));
	node->key = key;
	node->brother = NULL;
	node->child = NULL;
	return node;
}

void printarr(int arr[], int size)
{
	for (int i = 0; i < size; i++)
	{
		printf("%d -> %d\n", i+1, arr[i]);

	}
}



void addkids(int i, int parent)
{
	treem[parent]->kids[treem[parent]->nrchildren] = treem[i];
	treem[parent]->nrchildren++;
}



repr2* maker2(int parents[], int size)
{
	int root = 0;

	for (int i = 0; i < size; i++)
	{
		treem[i] = (repr2*)malloc(sizeof(repr2));
		treem[i]->key = i + 1;
		treem[i]->nrchildren = 0;
		treem[i]->kids = (repr2**)malloc(sizeof(repr2));
	}

	for (int i = 0;i < size; i++)
	{
		if (parents[i] == -1)
		{
			root = i;
		}
		else
		{
			addkids(i, parents[i] - 1);

		}
	}

	return treem[root];

}




void printr2(childrennode* root, int level)
{
	if (root != NULL)
	{
		for (int i = 0; i < level; i++)
		{
			printf("  ");
		}
		printf("%d\n", root->key);
		for (int i = 0; i < root->nrchildren; i++)
		{
			printr2(root->kids[i], level + 1);

		}
		
	}
}





bint* maker3(repr2* root)
{
	if (root == NULL)
	{
		return NULL;
	}
	else
	{
		bint* node = createnode(root->key);
		int nrkids = root->nrchildren;
		if(nrkids>0)
		{ 
			node->child = maker3(root->kids[0]);
			bint* brotherlist;
			brotherlist = node->child;
			int i = 1;
			while (i < nrkids)
			{
				brotherlist->brother = maker3(root->kids[i]);
				if (brotherlist != NULL)
				{
					brotherlist = brotherlist->brother;

				}
				i++;
			}
		}
		return node;
	}
}




void prettyprint(bint* root, int level)
{
	if (root != NULL)
	{
		for (int i = 0;i < level; i++)
		{
			printf("  ");

		}
		printf("%d\n", root->key);
		prettyprint(root->child, level + 1);
		prettyprint(root->brother, level);

	}
}



int main()
{
	int parents[] = { 2,7,5,2,7,7,-1,5,2 };
	int size = 9;
	printarr(parents, size);
	root = maker2(parents, size);
	printf("Multi  way tree; \n0");
	printr2(root, 0);
	printf("\nBinary tree representation:\n");
	bint* rootbin = maker3(root);
	prettyprint(rootbin, 0);

}
