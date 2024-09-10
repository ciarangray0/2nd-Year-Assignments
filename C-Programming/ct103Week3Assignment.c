// Ciaran Gray, 22427722, 11.10.22
#include "stdio.h"
int main()
{
    float salesa ;
    float salesb ;
    float salesc ;
    float salesd ;
    float salese ;
    float avgsales ;

    printf("enter the total weekly sales in euros for week 1: ");
    scanf("%f", &salesa);
    printf("enter the total weekly sales in euros for week 2: ");
    scanf("%f", &salesb);
    printf("enter the total weekly sales in euros for week 3: ");
    scanf("%f", &salesc);
    printf("enter the total weekly sales in euros for week 3: ");
    scanf("%f", &salesd);
    printf("enter the total weekly sales in euros for week 4: ");
    scanf("%f", &salese);
    avgsales = (salesa + salesb + salesc + salesd +salese) / 5;
    printf("average weekly sales is %.2f/n", avgsales);
    return 0;
}