/*
 * ShelfRow.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

class ShelfRow {

private:
	int mShelf;
	char mRow;

public:
	ShelfRow();
	ShelfRow(int shelf, char row);
	virtual ~ShelfRow();
	void print();
};
