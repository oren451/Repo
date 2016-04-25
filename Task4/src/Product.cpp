/*
 * Product.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "Product.h"
#include "iostream"

using namespace std;

Product::Product()
{
	mId = 0; //Static member to be unique
	mPlace();
	mExposure = Low;
	mType = NULL;
	mWeight = 0;
}

Product::Product(char* name, int id, ShelfRow place, int weight, ProductType type, ExposureValue exposure)
{
	mId = id;
	mPlace = place;
	mType = type;
	mExposure = exposure;
	mWeight = weight;
}

void Product::print()
{
	cout << mPlace.print() << " (" << mType << "," << mWeight << "," << mExposure << ") "
			<< mId << endl;
}







