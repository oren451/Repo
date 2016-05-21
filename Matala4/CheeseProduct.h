/*
 * MilkyCheeseProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "MilkProduct.h"
#include <string>

class CheeseProduct : public MilkProduct
{
private:
	const int mAddition;

public:
	CheeseProduct() :mAddition(0) {};
	CheeseProduct(int id, int shelf, char row, int weight,
		ExposureValue exposure, const string& name, int fat,
		int colorcount, int addition);
	CheeseProduct(const CheeseProduct &cheeseproduct);
	virtual ~CheeseProduct();
	virtual int calculatePrice() const;
	virtual void print() const;

	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);
};

