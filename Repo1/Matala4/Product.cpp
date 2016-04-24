/*
 * Product.cpp
 *
 *  Created on: Apr 20, 2016
 *      Author: orenk
 */

#pragma once

using namespace std;

Product::Product() {
	mExposure = NULL;
	mId = 0;
	mName = "DefaultName";
	mType = NULL;
	mWeight = 0;
	mPlace();
}

Product::~Product() {
}

Product::Product(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure) {

	mName = NULL;
	mId = id;
	strcpy(mName, name);
	mPlace = place;
	mWeight = weight;
	mType = type;
	mExposure = exposure;
}

void Product::print() {

	cin << mPlace.print() << "(" << mWeight << ","
			<< mType << "," << mExposure << ") " << mId << endl;
}
