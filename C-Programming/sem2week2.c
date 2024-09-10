#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int shipX = 0;
int shipY = 0;
void randomsearch(int gridsize, int x, int y, int guesstries);
void gridsearch(int gridsize, int x, int y, int gridtries);

int main() {
int gridsize = 11;
int x = 0;
int y = 0;
int guesstries = 0;
int gridtries = 0;
long lt = time(NULL);
srand(lt);
    //srand(lt) is used to generate a random seed for the rand() random number everytime so that the coordinates will be different for every attempt
    shipX = rand() % 12;
    shipY = rand() % 12;
    // the other two functions are called here
    randomsearch(gridsize, x, y, guesstries);
    gridsearch(gridsize, 1, 1, gridtries);
}

void randomsearch(int gridsize, int x, int y, int guesstries) {
    // using the srand(lt) function to produce a random seed for the random integer generator rand() so that the guesses will be random everytime, every time a random number is guessed an attempt is stored in the guesstries integer
    while (1) {
        long lt = time(NULL);
        srand(lt);
        x = rand() % 12;
        y = rand() % 12;
        guesstries ++;
        
        if (x == shipX && y == shipY) {
            printf("It took %d tries to randomly find that the ship's coordinates are %d,%d\n", guesstries, x, y);
        }
        break;
    }
}

void gridsearch(int gridsize, int x, int y, int gridtries) {
    // once the coordinates are found the function will print this
    if (x == shipX && y == shipY) {
        printf("It took %d attempts to locate the ship through the grid search at %d,%d\n", gridtries, x, y);
    }
    //this will begin searching through the grid for the ship starting at  0,0, working down the y-axis first , it recursively calls itself again at x+1, y until x and y are equal to shipX and shipY, it also keeps count of the number of attempts in gridtries integer
    if(x > gridsize) {
        return;
    }
    else if(y > gridsize) {
        gridsearch(gridsize, 0, y + 1, gridtries + 1);
    }
    else {
        gridsearch(gridsize, x + 1, y, gridtries + 1);
    }
}