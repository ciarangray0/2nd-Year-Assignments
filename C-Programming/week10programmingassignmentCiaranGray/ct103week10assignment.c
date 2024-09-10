#include <stdio.h>
#include <stdlib.h>
//ciaran Gray 15/03/2023 22427722

int is_leap(int year);
int new_years_day(int year);
int no_of_days(int year, int month);
int first_day(int year, int month);
void print_month(int firstday, int numofdays);

int main(int argc, char *argv[]) {
	int year = atoi(argv[1]);
	int month = atoi(argv[2]);
	int lastmonth = atoi(argv[3]);
	
	is_leap(year); //calling all the functions
	new_years_day(year);
	no_of_days(year, month);
	first_day(year, month);

	for(int i = month; i <= lastmonth; i++) { // calling the function to print the calander
		print_month(month, year);
		month++;

		if(month > 12) { //in case the calander needs to change into a new year
			month = 1;
			year++;
		}
	}
}

int is_leap(int year) {
	if (year % 400 == 0) {
		return 1;
	}
	else if (year % 100 == 0) {
		return 0;
	}
	if (year % 4 == 0) {
		return 1;
	}
	return 0;
}

int new_years_day(int year) { // this function gets the date of the first of any given year by looping through each year and adding 365 to the day variable and 1/0 depending if its a leap year until the desired year is reached
	int day = 1;
	for (int i = 1900; i < year; i++) {
		day = (day + 365 + is_leap(i));
	}
	return (day % 7);
}

int no_of_days(int year, int month) { // this function gets the number of days in each month and will either add on 1 or 0 to febuary depending if its a leap year
	if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
		return 31;
	}
	else if((month != 2)) {
		return 30;
	}
	return (28 + is_leap(year));
}

int first_day(int year, int month) {
	int firstDay = new_years_day(year); // this function gets the date of the first of jan of that year, then calculates the first of the required month of that year by getting the number of days from jan 1st to the first of the month
	for (int i = 1; i < month; i++) {
		firstDay = firstDay + no_of_days(year, i);
	}
	return (firstDay % 7);
}

void print_month(int month, int year) {
	printf("\n%d/%d", month, year);
	printf("\n Sun  Mon  Tue  Wed  Thu  Fri  Sat\n");
	int firstday = first_day(year, month);
	int numofdays = no_of_days(year, month);

	for(int i =0; i < firstday; i++) { // to start the printing of the dates on the first of the month
		printf("     ");
	}
	int currday =1; // current day of week
	int endofweek = firstday; // endofweek is a counter to match the currrent date with the correct day
	for (currday = 1; currday <= numofdays; currday++) { // for loop that prints the dates with a if statement that moves the dates to a new line everytime it reaches sunday
		printf("  %d  ", currday);
		endofweek++;
		if(endofweek == 7) {
			printf("\n\n");
			endofweek = 0;
		}
	}
}