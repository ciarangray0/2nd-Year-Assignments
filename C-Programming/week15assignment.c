#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    FILE *fptr = fopen("/Users/ciarangray/Desktop/week15.txt", "r");
    char c = fgetc(fptr);
    if (fptr == NULL){
        puts("Couldn't open file\n");
        }
        else {
            printf("Everything works fine\n");
            while (c != EOF){
                printf("%c", c);
                }
            }
            fclose(fptr);

FILE *fptr = fopen("/Users/ciarangray/Desktop/week15.txt", "r");
int year;
int month;
int day;
char region[25];
char rep[20];
char item[15];
int units;
float unitcost;
float totalcost;
int count = 0;
float totalincome = 0;
float avgincome = 0;
 {
    fscanf(fptr, "%d\t ", &day);
    fscanf(fptr, "%d\t ", &month);
    fscanf(fptr, "%d\t ", &year);
    fscanf(fptr, "%s\t", region);
    fscanf(fptr, "%s\t", rep);
    fscanf(fptr, "%s\t", item;
    fscanf(fptr, "%d\t", &units);
    fscanf(fptr, "%.2f\t", &unitcost);
    fscanf(fptr, "%.2f\t", &totalcost);

    printf("%d\t/%d\t/%d\t", day, month, year);
    printf("%s\t", region);
    printf("%s\t", rep);
    printf("%s\t", item);
    printf("%d\t", units);
    printf("%.2f\t", unitcost);
    printf("%.2f\t", totalcost);
}
}