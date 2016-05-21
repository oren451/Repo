/*
 * Product.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "Product.h"
#include <iostream>

using namespace std;

Product::Product(
		int id,
		int shelf,
		char row,
		int weight,
		ProductType type,
		ExposureValue exposure):
				mId(id),
				mShelf(shelf),
				mRow(row),
				mWeight(weight),
				mType(type),
				mExposure(exposure)
{

}

void Product::print() const
{
	cout << mRow << " " << mShelf << " (" << mType << "," << mWeight << "," << mExposure << ") "
			<< mId << " ";
}

Product::~Product() {
}

Product::Product(const Product &product):
						mId(product.mId),
						mShelf(product.mShelf),
						mRow(product.mRow),
						mWeight(product.mWeight),
						mType(product.mType),
						mExposure(product.mExposure)
{
}
void Product::saveBin(ofstream & out) const
{
	out.write((char*)&mId, sizeof(mId));
	out.write((char*)&mRow, sizeof(mRow));
	out.write((char*)&mShelf, sizeof(mShelf));
	out.write((char*)&mWeight, sizeof(mWeight));
	out.write((char*)&mExposure, sizeof(mExposure));
}

void Product::loadBin(ifstream & in)
{
	in.read((char*)&mId, sizeof(mId));
	in.read((char*)&mShelf, sizeof(mShelf));
	in.read((char*)&mRow, sizeof(mRow));
	in.read((char*)&mWeight, sizeof(mWeight));
	in.read((char*)&mExposure, sizeof(mExposure));
}