/*
 * Product.h
 *
 *  Created on: Apr 20, 2016
 *      Author: orenk
 */
#pragma once
#include "iostream"
using namespace std;

static enum ProductType { FarmProduct, MilkProduct, PackageProduct };
static enum ExposureValue { High, Medium, Low };

class Product {
private:
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
