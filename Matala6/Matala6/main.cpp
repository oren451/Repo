#include <iostream>
#include "CFile.h"
#include "CElement.h"

#define SIZE 3
using namespace std;

int main() {
    {
        CElement element(5);
        CElement element1(6);
        CElement element2(7);

        CFile<CElement> file("/tmp/ex6.txt");
        file.write(element);

        CElement cElement_array[SIZE] = { element, element1, element2};
        CFile<CElement> array_file("/tmp/exarray6.txt");
        array_file.write(cElement_array, SIZE);
    }
    {
        CFile<CElement> file("/tmp/ex6.txt");
        CElement new_element(file.read());
        cout << new_element.getNum();

        CFile<CElement> array_file("/tmp/exarray6.txt");
        CElement* new_cElement_array;
        array_file.read(&new_cElement_array, SIZE);

        for (int i = 0; i < SIZE; ++i) {
            cout << new_cElement_array[i].getNum() << endl;
        }
    }
    return 0;
}