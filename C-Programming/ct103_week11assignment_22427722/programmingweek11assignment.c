#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//ciarn gray / 22427722/ 22 march 2023
typedef struct {
    char description[100];
    int n, s, e, w, in, out;
} location;

location locations[40];
int numLocations = 0;
char locationsFile[] = "/Users/ciarangray/Desktop/adventure_locations.txt";

FILE* openFileForReading(char* filename) {
    FILE* file_ptr = fopen(filename, "r");
    if (file_ptr == NULL) 
        printf("Could not open %s\n", filename);
    return file_ptr;
}

bool readLocations() {
    FILE* file_ptr = openFileForReading(locationsFile);
    if (file_ptr == NULL)
        return false;

    numLocations = 0;
    int readHeaderLines = 0;
    char line[200];
    while (fgets(line, 99, file_ptr) != NULL) {
        if (readHeaderLines<2)
            readHeaderLines++;
        else {
            numLocations++;
            location l;
            int locNum;
            sscanf(line, "%d\t%d\t%d\t%d\t%d\t%d\t%d\t%[^\t]\n", &locNum, &l.n, &l.s, &l.e, &l.w, &l.in, &l.out, l.description);
            locations[numLocations] = l; // the 1st location is 1 (not 0) so we can use 0 to mean 'nothing' in adventure_locations.txt
        }
    }

    return true;
}

int main() {
    if (readLocations()) {
        int currpos = 1; // currpos is the variable which changes for the id of the location that needs to printed out, which changes depending on the users input
        char input[20]; //string to hold the users input
            
            printf("Welcome to Galway Adventure, type help for help\n");
            scanf("%s", &input); //scan in users input

            while(strcmp(input, "quit") != 0) {  //while the user hhasnt typed in quit
                printf("%s\n", locations[currpos].description); //print whre the user currently is

            if(strcmp(input, "help") == 0) { //prints the help
                printf("Commands are: n, s, e, w, in, out, look, quit, help\n");
            }
            if(strcmp(input, "look") == 0) { //prints where the user currently is
                printf("%s\n",locations[currpos].description);
            }
            if(strcmp(input, "n") == 0) { //moves the user north , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].n == 0) {
                    printf("cant go that way\n");
                }
                else{
                    currpos = locations[currpos].n;
                }
            }
            if(strcmp(input, "s") == 0) {  //moves the user south , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].s == 0) {
                    printf("cant go that way\n");
                }
                else {
                    currpos = locations[currpos].s;
                }
            }
            if(strcmp(input, "e") == 0) { //moves the user east , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].e == 0) {
                    printf("cant go that way\n");
                }
                else {
                    currpos = locations[currpos].e;
                }
            }
            if(strcmp(input, "w") == 0) { //moves the user west , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].w == 0) {
                    printf("cant go that way\n");
                }
                else{
                    currpos = locations[currpos].w;
                }
            }
            if(strcmp(input, "in") == 0) { //moves the user in , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].in == 0) {
                    printf("cant go that way\n");
                }
                else {
                    currpos = locations[currpos].in;
                }
            }
            if(strcmp(input, "out") == 0) { //moves the user out , checks if possible, if not tellls the user they cant go there
                if(locations[currpos].out == 0) {
                    printf("cant go that way\n");
                }
                else {
                    currpos = locations[currpos].out;
                }
            }
            }
    }
}