#pragma once
#pragma once
#pragma once
using namespace std;

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

