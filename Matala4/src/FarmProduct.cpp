/*
 * FarmProduct.cpp
 *
 *  Created on: Apr 11, 2016
 *      Author: orenk
 */

#include "FarmProduct.h"

using namespace std;

FarmProduct::FarmProduct() {
}

FarmProduct::~FarmProduct() {
}

int FarmProduct::calculatePrice() {
	return (5 - mSeasonsNumber) + 5 * mSupplierNumber + mFarmType;
}
