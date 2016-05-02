/*
 * CheeseProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "CheeseProduct.h"
#include "iostream"

using namespace std;

CheeseProduct::~CheeseProduct() {
}

CheeseProduct::CheeseProduct(int id, int shelf, char row, int weight,
		ExposureValue exposure, const string& name, int fat,
		int colorcount, int addition)
: MilkProduct(name, id, shelf, row, weight, exposure, fat, Cheese, colorcount)
, mAddition(addition)
{
}

int CheeseProduct::calculatePrice() const{

	return MilkProduct::calculatePrice() + mAddition;
}

CheeseProduct::CheeseProduct(const CheeseProduct& cheeseproduct):
		MilkProduct(cheeseproduct)
, mAddition(cheeseproduct.mAddition)
{
}

void CheeseProduct::print() const {

	MilkProduct::print();
	cout << " (" << mAddition << ")";
}

