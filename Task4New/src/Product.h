/*
 * Product.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

#include <iostream>
#include <fstream>

enum ProductType { FarmProductType, MilkProductType, PackageProductType };
enum ExposureValue { Low, Medium, High };

using namespace std;

class Product {

private:
	int mId;
	int mShelf;
	char mRow;
	int mWeight;
	ProductType mType;
	ExposureValue mExposure;

public:
	Product(int id, int shelf, char row, int weight, ProductType type, ExposureValue exposure);
	Product(const Product &product);
	virtual ~Product();
	virtual int calculatePrice() const = 0;
	virtual void print() const;

	void setWeight(int weight) {
		mWeight = weight;
	}
/*
	virtual void write(ostream& out) const;
	virtual void read(istream& in);
	virtual void save(ofstream& out) const;
	virtual void load(ifstream& in);
	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);
*/
	int getId() const {
		return mId;
	}

	int getShelf() const {
		return mShelf;
	}

	void setShelf(int shelf) {
		mShelf = shelf;
	}
};

