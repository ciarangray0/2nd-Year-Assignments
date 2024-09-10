#include <stdio.h>
#include <string.h>

//ciaran gray, 22427722, 14/02/2023
// a struct for the date of the last transaction
typedef struct {
    int day, month, year;
} date;

// creating a struct to store all attributes for each person
typedef struct{
    char name[50];
    int accountNumber;
    double balance;
    date lastTrans;
} customer;

// declaring all functions
void getCustomerName(customer* cptr);
void getAccountNumber(customer* cptr);
void getLastTransDate(customer* cptr);
void getBalance(customer* cptr);
void printCustomer(customer* cptr);

// function scans name  as a string with a max of 50 chracters 
void getCustomerName(customer* cptr){
    printf("Enter customer name: ");
    scanf("%49s", cptr->name);
}
// scans users input and passes the value into a pointer
void getAccountNumber(customer* cptr){
    printf("Enter account number: ");
    scanf("%d", &cptr->accountNumber);
    scanf("%*c"); // Consume newline character left in input buffer
}

// scans the users input and passes the date into a pointer for each day, month and year
void getLastTransDate(customer* cptr){
    printf("Enter date of last transaction (dd mm yyyy): ");
    scanf("%d %d %d", &cptr->lastTrans.day, &cptr->lastTrans.month, &cptr->lastTrans.year);
}
// scans the users input and passes the value into a pointer for the balance 
void getBalance(customer* cptr){
    printf("Enter account balance: ");
    scanf("%lf", &cptr->balance);
    scanf("%*c"); // same as the above function, consumes newline character left in input buffer
}

// prints the entire account for each person, using pointers to do so for each different attribute, and the date stores the day month and year seperately
void printCustomer(customer* cptr){
    printf("%25s\t%13d\t%12.2lf\t%02d/%02d/%d\n", cptr->name, cptr->accountNumber, cptr->balance, cptr->lastTrans.day, cptr->lastTrans.month, cptr->lastTrans.year);
}

int main(){
    int i;
    customer customers[3];
    // calling all functions 3 times
    for (i = 0; i < 3; i++){
        getCustomerName(&customers[i]);
        getAccountNumber(&customers[i]);
        getLastTransDate(&customers[i]);
        getBalance(&customers[i]);
        printf("\n");
    }
    //printing all the attributes with a for loop that runs 3 times 
    printf("\n\n%25s\t%13s\t%12s\t%s\n\n", "Name", "Account Number", "Balance", "Date of Last Transaction");
    for (i = 0; i < 3; i++){
        printCustomer(&customers[i]);
    }
    return 0;
}
