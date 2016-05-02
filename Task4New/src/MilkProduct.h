/*
 * MilkProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "Product.h"
#include <string>

enum MilkType { Cheese, Other};

class MilkProduct: public Product
{
private:
	const MilkType mMilkType;
	const string mName;
	const int mColorCount;
	const int mFat;

public:
	MilkProduct(const string& name, int id, int shelf, char row, int weight,
			ExposureValue exposure,int fat, MilkType milktype, int colorcount);
	MilkProduct(const MilkProduct &milkproduct);
	virtual ~MilkProduct();
	virtual int calculatePrice() const;
	virtual void print() const;
/*
	virtual void write(ostream& out) const;
	virtual void read(istream& in);
	virtual void save(ofstream& out) const;
	virtual void load(ifstream& in);
	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);*/
};



