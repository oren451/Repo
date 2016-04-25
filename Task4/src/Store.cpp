/*
 * Store.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

using namespace std;

Store::Store() {
	mFactor = 0;
	mProductsAmount = 0;
	mAllProductArray = NULL;
	mStoreName = new char[7];
	strcpy(mStoreName, "Default");
}

Store::~Store() {
}

Store::Store(char* storeName, int factor, int productsAmount,
		Product* allProductArray[])
{
	mFactor = factor;
	for(int i = 0;i < mProductsAmount; ++i)
	{
		if(typeof(mAllProductArray[i]) == FruitProduct)
		{
			addProductToStore((FruitProduct)mAllProductArray[i]&);
		}
		else if(typeof(mAllProductArray[i]) == VegtableProduct)
		{
			addProductToStore((VegtableProduct)mAllProductArray[i]&);
		}
		else if(typeof(mAllProductArray[i]) == NonMilkyProduct)
		{
			addProductToStore((NonMilkyProduct)mAllProductArray[i]&);
		}
		else if(typeof(mAllProductArray[i]) == MilkyCheeseProduct)
		{
			addProductToStore((MilkyCheeseProduct)mAllProductArray[i]&);
		}
		else
		{
			addProductToStore((MilkProduct)mAllProductArray[i]&);
		}
	}
	mProductsAmount = productsAmount;
}

void Store::addProductToStore(Product& product) {

	//mAllProductArray =
}

int Store::calculateStoreValue() {

	int summery = 0;
	for(int i = 0;i < mProductsAmount; ++i)
	{
		if(typeof(mAllProductArray[i]) == FruitProduct)
		{
			summery += (FruitProduct)mAllProductArray[i]->calculatePrice();
		}
		else if(typeof(mAllProductArray[i]) == VegtableProduct)
		{
			summery += (VegtableProduct)mAllProductArray[i]->calculatePrice();
		}
		else if(typeof(mAllProductArray[i]) == NonMilkyProduct)
		{
			summery += (NonMilkyProduct)mAllProductArray[i]->calculatePrice();
		}
		else if(typeof(mAllProductArray[i]) == MilkyCheeseProduct)
		{
			summery += (MilkyCheeseProduct)mAllProductArray[i]->calculatePrice();
		}
		else
		{
			summery += (MilkProduct)mAllProductArray[i]->calculatePrice();
		}
	}
}

void Store::printStoreDetails() {

	cout << "Store Details Are:" << endl;
	cout <<
}
