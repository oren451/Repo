#include "ShelfRow.h"

using namespace std;

ShelfRow::ShelfRow() {
	mRow = 'A';
	mShelf = 1;
}

ShelfRow::ShelfRow(int shelf, char row) {

	mRow = row;
	mShelf = shelf;
}

ShelfRow::~ShelfRow() {
}

void ShelfRow::print() {

	cin << mRow << " " << mShelf << endl;
}


