/*
 * ShelfRow.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "ShelfRow.h"
#include "iostream"

using namespace std;

ShelfRow::ShelfRow() {

	mRow = 'A';
	mShelf = 1;
}

ShelfRow::ShelfRow(int shelf, char row)
{
	mShelf = shelf;
	mRow = row;
}

ShelfRow::~ShelfRow() {
}

void ShelfRow::print()
{
	cout << mRow << " " << mShelf << endl;
}



