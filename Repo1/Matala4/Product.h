/*
 * Product.h
 *
 *  Created on: Apr 20, 2016
 *      Author: orenk
 */
#pragma once
#pragma once
#pragma once
#pragma once

using namespace std;

#pragma once
#pragma once

static ProductType PODUCTTYPE;
static ExposureValue EXPOSURE_VALUE;

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
