/*
 * Product.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

#include "ShelfRow.h"

enum ProductType { FarmProduct, MilkProduct, PackageProduct };
enum ExposureValue { Low, Medium, High };

class Product {

protected:
	char* mName;
	int mId;
	ShelfRow mPlace;
	int mWeight;
	ProductType mType;
	ExposureValue mExposure;

public:
	Product();
	Product(char* name, int id, ShelfRow place, int weight, ProductType type, ExposureValue exposure);
	virtual ~Product();
	virtual int calculatePrice() = 0;
	virtual void print();

	void setWeight(int weight) {
		mWeight = weight;
	}
};
