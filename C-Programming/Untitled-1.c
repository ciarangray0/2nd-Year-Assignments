// Ciaran Gray, 22427722, 11.10.22
#include "studio.h"
int main()
{
    float sales1 = 0;
    float sales2 = 0;
    float sales3 = 0;
    float sales4 = 0;
    float sales5 = 0;
    float avgfloat = 0;

    printf("enter the total weekly sales in euros for week 1: ");
    scanf_s("%f", sales1);
    printf("enter the total weekly sales in euros for week 2: ");
    scanf_s("%f", sales2);
    printf("enter the total weekly sales in euros for week 3: ");
    scanf_s("%f", sales3);
    printf("enter the total weekly sales in euros for week 3: ");
    scanf_s("%f", sales4);
    printf("enter the total weekly sales in euros for week 4: ");
    scanf_s("%f", sales5);
    avgfloat = (sales1 + sales2 + sales3 + sales4 +sales5) % 5
    printf("average weekly sales = avgfloat")

}