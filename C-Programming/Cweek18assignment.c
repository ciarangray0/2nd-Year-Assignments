#include <stdio.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

#define   MAXSTRING   100
#define   MAXWORD    100000 

char dictionary[MAXWORD][MAXSTRING]; //array to store all the words within the 3 < x < 7 range
int getdictionary();
void guessinggame(int randomnumber);

int main() {
    getdictionary();
    srand(time(NULL)); //random number generator
    int randomnumber = rand() % getdictionary();
    guessinggame(randomnumber);
    return 0;
}

int getdictionary() {
    // fopen requests a file to be opened obtains a FILE pointer to access it
    FILE *file_ptr;
    file_ptr = fopen("/Users/ciarangray/Desktop/dictionaryN.txt", "r"); // open for reading
    int lines = 0;
    int index = 0;
    if (file_ptr == NULL)
        printf("Could not open dictionary.txt");
    else {
        char txt[MAXSTRING]; // string for reading each line into
        while (fgets(txt, MAXSTRING-1, file_ptr)!=NULL) {
            txt[strlen(txt)-1] = '\0'; // remove the \n which has also been read into the string!
                if (strlen(txt) > 3 && strlen(txt) < 7) { //copys all eligable words into the dictionary string
                    strcpy(dictionary[index], txt);
                    index++;
        }
        lines++;
        }
        }
        return index;
        fclose (file_ptr); // don't forget to close the file
}

void guessinggame(randomnumber) {
    int len = strlen(dictionary[randomnumber]);
    char letterguess;
    int guesses = 1;
    char hiddenword[len + 1];
    for (int i = 0; i < len; i++) { //hiddenword the same length of the word the user attempts to guess that will reveal a letter everytime its guessed
        hiddenword[i] = '_';
    }
    hiddenword[len] = '\0'; // adding in the /0 back at the end of the string
    //while loop for printing the hidden word and asking the user for a guess, as long as the word hasent yet been guessed or the user has used up all their guesses
    
    while (strcmp(hiddenword, dictionary[randomnumber]) != 0 && guesses < 10) {
        printf("\n%s", hiddenword);
        printf("\nGuess %d = ", guesses);
        scanf("%c", &letterguess);
        guesses ++;
    for (int k = 0; k < len; k++) {
        // if the guessed letter is in the word from the dictionary the position and letter itself is revealed in the hiddenword string
    if (letterguess == dictionary[randomnumber][k]) {
        hiddenword[k] = dictionary[randomnumber][k];
    }
    }
    }
    //once the entire word has been guessed this message will print, if it hasent the second message will print.
    if (strcmp(hiddenword, dictionary[randomnumber]) == 0) {
    printf("\nYou have correctly guessed the word %s!", hiddenword);
    }
    else {
        printf("\nYou were unable to guess the word %s", dictionary[randomnumber]);
    }
}