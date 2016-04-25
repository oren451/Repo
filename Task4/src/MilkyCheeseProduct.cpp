/*
 * MilkyCheeseProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */

using namespace std;

MilkyCheeseProduct::MilkyCheeseProduct(): MilkProduct() {

	mAddition = 0;
}

MilkyCheeseProduct::~MilkyCheeseProduct() {
}

MilkyCheeseProduct::MilkyCheeseProduct(char* name, int id, ShelfRow place,
		int weight, ProductType type, ExposureValue exposure, int fat,
		MilkType milktype, int colorcount, int addition)
		:MilkProduct(name, id, place, weight, type, exposure, fat, milktype, colorcount)
{
	mAddition = addition;
}

int MilkyCheeseProduct::calculatePrice() {

	return MilkProduct::calculatePrice() + mAddition;
}

void MilkyCheeseProduct::print() {

	MilkProduct::print();
	cout << " (" << mAddition << ")" << endl;
}
