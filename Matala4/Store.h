/*
 * Store.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "Product.h"
#include <string>
#include <vector>
using namespace std;

class Store
{
private:
	const string mStoreName;
	int mFactor;
	vector<Product*> mAllProductArray;

public:
	Store() : mFactor(0){};
	Store(const string& storeName, int factor, int productsAmount, const Product* allProductArray);
	virtual ~Store();
	void addProductToStore(const Product* product);

	void setFactor(int factor) {
		mFactor = factor;
	}

	int calculateStoreValue() const;
	void printStoreDetails() const ;
	bool isIdExist(int id) const;

	void saveBin(ofstream& out) const;
	void loadBin(ifstream& in);
};
