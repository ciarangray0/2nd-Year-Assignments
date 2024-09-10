// CT102 Algorithms 2023
// quicksort with 2 partitions
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_LIMIT 5000

void quickSort (int[], int, int, int*, int*, int*);
int partition (int[], int, int, int*, int*, int*);
int partition2 (int[], int, int, int*, int*);
void swap (int *, int *, int*);

int main(void) {
    int arrA[ARRAY_LIMIT];
    int i, size = 0;
    FILE* fptr;
    
    // Read integers from file
    fptr = fopen("/Users/ciarangray/Desktop/5000intsUnsorted.txt", "r");
    if (fptr == NULL) {
        fprintf(stderr, "Error opening file\n");
        return 1;
    }
    printf("Reading integers from file...\n");
    for (i = 0; i < ARRAY_LIMIT && fscanf(fptr, "%d", &arrA[i]) != EOF; i++);
    fclose(fptr);
    size = i;
    
    // Sort the array using mergesort
    printf("Sorting the array...\n");
    clock_t t;
    t = clock();
    int comparisons = 0, swaps = 0, recursion_calls = 0;
    quickSort(arrA, 0, size - 1, &comparisons, &swaps, &recursion_calls);
    double timeTaken = ((double)t) / CLOCKS_PER_SEC;
    
    // Print the sorted array
    printf("Sorted array:\n");
    for (i = 0; i < size; i++) {
        printf("%d ", arrA[i]);
    }
    printf("\n");
    
    // Print some statistics
    printf("Time taken: %lf seconds\n", timeTaken);
    printf("Number of comparisons: %d\n", comparisons);
    printf("Number of moves: %d\n", swaps);
    printf("Number of recursive function calls: %d\n", recursion_calls);
    
    return 0;
}
// 3. Add code to time, count comparisons and swaps



void quickSort(int arrA[], int startval, int endval, int* comparisions, int* swaps, int* recursion_calls) {
	(*recursion_calls)++;
	if ( (endval - startval) < 1) {
		return;
	}
	else {
		int k = partition2(arrA, startval, endval, comparisions, swaps); 
		//now sort the two sub-arrays
		quickSort(arrA, startval, k - 1, comparisions, swaps, recursion_calls);  //left partition
		quickSort(arrA, k + 1, endval, comparisions, swaps, recursion_calls);   //right partition
	}
}

// partition using nested while loops
int partition (int arrA[], int startval, int endval, int* comparisions, int* swaps, int* recursion_calls)
{
    (*recursion_calls)++;
	int i = startval + 1;
	int k = endval;
	int pivot = arrA[startval];

	while (k >= i) {		
		while (arrA[i] <= pivot && i <= k) {
            (*comparisions)++;
			i++;
		}
		while (arrA[k] > pivot && k >= i) {
            (*comparisions)++;
			k--;
		}    
	    if (k > i){ //swap values at k and i
        (*swaps)++;
			swap(&arrA[i], &arrA[k], swaps);
		}       
	} 
	//out of this loop when k >= i not true
    (*swaps)++;
    swap(&arrA[startval], &arrA[k], swaps);

	return(k);
}

// better version of partition .. no nested loop
// pivot at startval as before
int partition2 (int arrA[], int startval, int endval, int* comparisions, int* swaps)
{
	int k;
	int pivot = arrA[startval];
	int i = startval;
  
	for (k = startval + 1; k <= endval; k++) { // k keeps incrementing to the end
    (*comparisions)++;
        if (arrA[k] <= pivot) {  
            i++;   // i only increments when there is a new value to add to the <= portion
            if (i != k) {
                (*swaps)++;
				swap (&arrA[i], &arrA[k], swaps);
			}
        }
    }
    (*swaps)++;
	swap (&arrA[i], &arrA[startval], swaps);  // put pivot in correct location i
	return(i);
}

// call with  ... swap(&arrA[i], &arrA[j]) to swap the values at positions i and j
void swap(int* a, int* b, int*swaps)
{
    //++cnt_swap_calls;
    (*swaps)++;
	int temp = *a;
    *a = *b;
    *b = temp;
}