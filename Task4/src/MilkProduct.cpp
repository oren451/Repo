/*
 * MilkProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "Product.h"

using namespace std;

MilkProduct::MilkProduct(): Product() {

	mFat = 0;
	mMilkType = Cheese;
	mColorCount = 1;
	mName = new char[7];
	strcpy(mName, "Default");
}

MilkProduct::~MilkProduct() {
}

MilkProduct::MilkProduct(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure,int fat, MilkType milktype, int colorcount)
		:Product(id, place, weight, type, exposure)
{
	mFat = fat;
	mMilkType = type;
	mName = NULL;
	strcpy(mName, name);
	mColorCount = colorcount;
}

int MilkProduct::calculatePrice() {

	return (mColorCount - mFat) * mMilkType;
}

void MilkProduct::print() {

	Product::print();
	cout << mName << " (" << mMilkType << "," << mColorCount << "," << mFat << ")" << endl;
}
