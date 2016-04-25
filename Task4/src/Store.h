/*
 * Store.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

class Store
{
private:
	char* mStoreName;
	int mFactor;
	int mProductsAmount;
	Product* mAllProductArray[];

public:
	Store();
	Store(char* storeName, int factor, int productsAmount, Product* allProductArray[]);
	virtual ~Store();
	void addProductToStore(Product& product);

	void setFactor(int factor) {
		mFactor = factor;
	}

	int calculateStoreValue();
	void printStoreDetails();
};

