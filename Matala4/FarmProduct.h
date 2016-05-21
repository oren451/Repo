/*
 * FarmProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "Product.h"
#include <string>

enum FarmType { Fruit, Vegtable };

using namespace std;

class FarmProduct: public Product
{
private:
	const FarmType mFarmType;
	const string mName;
	const int mSeasonsNumber;
	const int mSuppliersAmount;

public:
	FarmProduct() : mSeasonsNumber(0), mSuppliersAmount(0), mFarmType(Fruit) {};
	FarmProduct(const string& name, int id, int shelf, char row, int weight,
			ExposureValue exposure,int supplierNumber,FarmType farmtype ,int seasonsnumber);
	FarmProduct(const FarmProduct &farmproduct);
	virtual ~FarmProduct();
	virtual int calculatePrice() const;
	virtual void print() const;

	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);
};


