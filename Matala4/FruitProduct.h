/*
 * FruitProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "Product.h"
#include "FarmProduct.h"

class FruitProduct: public FarmProduct
{

private:
	const int mSugarAmount;

public:
	FruitProduct() :mSugarAmount(0) {};
	FruitProduct(const string& name, int id, int shelf, char row, int weight,
			 ExposureValue exposure,int supplierNumber,
			int seasonsnumber, int sugarAmount);
	FruitProduct(const FruitProduct&);
	virtual ~FruitProduct();
	virtual int calculatePrice() const;
	virtual void print() const;

	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);
};


