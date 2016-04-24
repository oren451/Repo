/*
 * FarmProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

enum FarmType { Fruit, Vegtable };

class FarmProduct: public Product
{
private:
	int mSuppliersAmount;
	FarmType mFarmType;
	int mSeasonsNumber;

public:
	FarmProduct();
	FarmProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int supplierNumber,FarmType farmtype
			,int seasonsnumber);
	virtual ~FarmProduct();
	virtual int calculatePrice();
	virtual void print();
};

