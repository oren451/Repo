/*
 * Store.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "Store.h"
#include "FruitProduct.h"
#include "VegtableProduct.h"
#include "CheeseProduct.h"
#include "OtherMilkProduct.h"
#include "PackageProduct.h"
#include <typeinfo>

using namespace std;

Store::~Store() {
}

Store::Store(const string& storeName, int factor, int productsAmount, const Product* allProductArray):
													mStoreName(storeName),
													mFactor(factor),
													mAllProductArray()
{
	for(int i = 0;i < productsAmount; ++i)
	{
		int id = allProductArray[i].getId();
		if(isIdExist(id))
		{
			throw "Id Already Exist!!";
		}
		addProductToStore(&allProductArray[i]);

	}
}

void Store::addProductToStore(const Product* product) {

	int id = product->getId();
	if(isIdExist(id))
	{
		throw "Id Already Exist!!";
	}

	if(typeid(*product) == typeid(FruitProduct))
	{
		mAllProductArray.push_back(static_cast<FruitProduct*>(const_cast<Product*>(product)));
	}
	else if(typeid(*product) == typeid(VegtableProduct))
	{
		mAllProductArray.push_back(static_cast<VegtableProduct*>(const_cast<Product*>(product)));
	}
	else if(typeid(*product) == typeid(OtherMilkProduct))
	{
		mAllProductArray.push_back(static_cast<OtherMilkProduct*>(const_cast<Product*>(product)));
	}
	else if(typeid(*product) == typeid(CheeseProduct))
	{
		mAllProductArray.push_back(static_cast<CheeseProduct*>(const_cast<Product*>(product)));
	}
	else if(typeid(*product) == typeid(PackageProduct))
	{
		mAllProductArray.push_back(static_cast<PackageProduct*>(const_cast<Product*>(product)));
	}
	else
	{
		throw "Wrong type";
	}
}

int Store::calculateStoreValue() const{

	int summery = 0;
	for(int i = 0;i < (int)mAllProductArray.size(); ++i)
	{
		summery += mAllProductArray[i]->calculatePrice();
	}

	return summery;
}

void Store::printStoreDetails() const{

	cout << "Store Details Are:" << endl;
	for(int i = 0;i < (int)mAllProductArray.size(); ++i)
	{
		mAllProductArray[i]->print();
		cout << endl;
	}
}

bool Store::isIdExist(int id) const {

	for(int i = 0;i < (int)mAllProductArray.size(); ++i)
	{
		if(mAllProductArray[i]->getId() == id)
		{
			return true;
		}
	}

	return false;
}