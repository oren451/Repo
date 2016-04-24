/*
 * FarmProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#pragma once
#pragma once

using namespace std;

class FarmProduct: public Product {
private:
	int mSuppliersAmount;
	int mFarmType;
	int mSeasonsNumber;

public:
	FarmProduct();
	FarmProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int supplierNumber,FarmType farmtype
			,int supplierNumber,int farmtype, int seasonsnumber);
	virtual ~FarmProduct();
	virtual int calculatePrice();
	virtual void print();
};
