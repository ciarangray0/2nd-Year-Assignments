#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    char description[100];
    int n, s, e, w, in, out, take, drop;
} location;

// valid player commands
enum command {
    ERROR,
    N, S, E, W, IN, OUT, TAKE, DROP,
    LOOK, HELP, QUIT, INVENTORY,
    END
};

// the same sequence of commands, as strings
char commands[][20] = {"error","n","s","e","w","in","out","look","help","quit","take","drop","inventory"};

location locations[50];
int numLocations = 0;
char locationsFile[] = "/Users/ciarangray/Desktop/adventure_locations.txt";
char objectsFile[] = "/Users/ciarangray/Desktop/adventure_objects.txt";

int playerLocationNum = 1;  // where the player currently is (ID number of a location struct)

FILE* openLocationFileForReading(char* filename) {
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
            int len = strlen(l.description);
            l.description[len-1] = '\0'; // remove \n from the string
            if (l.description[len-2]=='\r')
                l.description[len-2] = '\0'; // aslo remove \r from the string
            locations[numLocations] = l; // the 1st location is 1 (not 0) so we can use 0 to mean 'nothing' in adventure_locations.txt
        }
    }

    return true;
}

FILE* openObjectsFileForReading(char* filename) {
    FILE* file_ptr = fopen(filename, "r");
    if (file_ptr == NULL) 
        printf("Could not open %s\n", filename);
    return file_ptr;
}

bool readLocations() {
    FILE* file_ptr = openFileForReading(objectsFile);
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
            sscanf(line, "%d\t%d\t%d\t%[^\t]\n", &l.object, &l.locNum, &l.description);
            int len = strlen(l.description);
            l.description[len-1] = '\0'; // remove \n from the string
            if (l.description[len-2]=='\r')
                l.description[len-2] = '\0'; // aslo remove \r from the string
            locations[numLocations] = l; // the 1st location is 1 (not 0) so we can use 0 to mean 'nothing' in adventure_locations.txt
        }
    }

    return true;
}

command identifyCommand(char* txt) {
    for (int i=0; i<(int)END; i++) {
        if (strcmp(txt,commands[i])==0)
            return (command)i;
    }
    return ERROR;
}

bool tryMoveTo(int locationNum) {
    if (locationNum>0 && locationNum<=numLocations) {
        playerLocationNum = locationNum;
        return true;
    }

    printf("\nYou can't go that way.\n\n");
    return false;
}

int main() {
    if (readLocations()) {
        printf("Welcome to Galway Adventure. Type 'help' for help.\n");

        // game loop (one iteration per command from the player)
        char txt[200] = "";
        command cmd = ERROR;
        bool displayLocation = true;

        while (cmd!=QUIT) {
            location currLoc = locations[playerLocationNum];

            if (displayLocation) {
                printf("\n%s\n", currLoc.description);
                displayLocation = false; // (for next iteration, unless we LOOK or move)
            }

            // read and interpret user input
            printf("> ");
            gets(txt);
            cmd = identifyCommand(txt);
        
            switch (cmd) {
                case N:
                    displayLocation = tryMoveTo(currLoc.n);
                break;

                case S:
                    displayLocation = tryMoveTo(currLoc.s);
                break;

                case E:
                    displayLocation = tryMoveTo(currLoc.e);
                break;

                case W:
                    displayLocation = tryMoveTo(currLoc.w);
                break;

                case IN:
                    displayLocation = tryMoveTo(currLoc.in);
                break;

                case OUT:
                    displayLocation = tryMoveTo(currLoc.out);
                break;

                case LOOK:
                    displayLocation = true;
                break;

                case HELP:
                    printf("I know these commands:\n");
                    for (int i=1; i<(int)END; i++) {
                        if (i>1)
                            printf(", ");
                        printf("%s", commands[i]);
                    }
                    printf(".\n\n");
                break;

                case QUIT:
                    printf("Bye!\n");
                break;

                default:
                    printf("Huh?\n");
            }
        }
    }
}