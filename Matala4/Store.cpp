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


void Store::saveBin(ofstream& out) const
{
	char type[2];
	int length = mStoreName.length();
	int size = mAllProductArray.size();
	out.write((char*)&length, sizeof(length));
	out.write((char*)&mStoreName[0], length);
	out.write((char*)&mFactor, sizeof(mFactor));
	
	out.write((char*)&size, sizeof(size));
	for (int i = 0; i < mAllProductArray.size(); i++)
	{
		strncpy(type, typeid(*mAllProductArray[i]).name() + 6, 2);
		out.write((char*)type, sizeof(type));
		mAllProductArray[i]->saveBin(out);
	}
	
}
void Store::loadBin(ifstream& in)
{
	int length;
	int size;
	char type[2];
	in.read((char *)&length, sizeof(length));
	in.read((char*)&mStoreName[0], length);
	in.read((char*)&mFactor, sizeof(mFactor));
	
	in.read((char *)&size, sizeof(size));
	
	for (int i = 0; i < size; i++)
	{
		in.read(type, sizeof(type));
		if (strncmp(type, "PackageProduct", 2) == 0)
			mAllProductArray.push_back(new PackageProduct());
		else if (strncmp(type, "FruitProduct", 2) == 0)
			mAllProductArray.push_back(new FruitProduct());
		else if (strncmp(type, "VegtableProduct", 2) == 0)
			mAllProductArray.push_back(new VegtableProduct());
		else if (strncmp(type, "OtherMilkProduct", 2) == 0)
			mAllProductArray.push_back(new OtherMilkProduct());
		else if (strncmp(type, "CheeseProduct", 2) == 0)
			mAllProductArray.push_back(new CheeseProduct());
		
		mAllProductArray[i]->loadBin(in);
	}
}