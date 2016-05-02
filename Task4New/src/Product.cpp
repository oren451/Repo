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
/*
void Product::write(ostream& out) const
{
	out << "ID: " << mId << endl;
	out << "TYPE: " << mType << endl;
	out << "PLACE: " << mPlace.getRow() << "," << mPlace.getShelf() << endl;
	out << "WEIGHT: " << mWeight << endl;
	out << "EXPOSURE: " << mExposure << endl;
}

void Product::save(ofstream & out) const
{
	out << mId << " ";
	out << mType << " ";
	out << mRow << " ";
	out << mShelf << " ";
	out << mWeight << " ";
	out << mExposure << " ";
}

void Product::load(ifstream & in)
{
	in >> mId >> mType >> mRow >> mShelf >> mWeight >> mExposure;
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
	in.read((char*)&mPlace, sizeof(mPlace));
	in.read((char*)&mWeight, sizeof(mWeight));
	in.read((char*)&mExposure, sizeof(mExposure));
}
*/