/*
 * NonMilkyProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "NonMilkyProduct.h"

using namespace std;

NonMilkyProduct::NonMilkyProduct(): MilkProduct() {

	mAmountOfNonMilkAdditions = 1;
	mNonMilkNames = {"Default"};
}

NonMilkyProduct::NonMilkyProduct(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure, int fat, MilkType milktype,
		int colorcount, int amountOfNonMilkAdditions, char* nonMilkNames[]):
					MilkProduct(name, id, place, weight, type, exposure, fat, milktype,
							colorcount)
{
	mAmountOfNonMilkAdditions = amountOfNonMilkAdditions;

}

NonMilkyProduct::~NonMilkyProduct() {
	// TODO Auto-generated destructor stub
}

int NonMilkyProduct::calculatePrice() {
	return MilkProduct::calculatePrice() + (mAmountOfNonMilkAdditions * 5);
}

void NonMilkyProduct::print() {

	MilkProduct::print();
	cout << "(" << endl;
	for(char* str = mNonMilkNames;
			str < mNonMilkNames + (sizeof(mNonMilkNames) / sizeof(*mNonMilkNames)); ++str)
	{
		cout << str << "," << endl;
	}

	cout << "(" << mAmountOfNonMilkAdditions << ")" << endl;
}
