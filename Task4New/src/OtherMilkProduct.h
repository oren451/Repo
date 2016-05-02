/*
 * NonMilkyProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include <string>
#include "MilkProduct.h"

class OtherMilkProduct: public MilkProduct {

private:
	const int mAmountOfNonMilkAdditions;
	string* mNonMilkNames;

public:
	OtherMilkProduct(int id, int shelf, char row, int weight,
			ExposureValue exposure, const string& name, int fat,
			int colorcount, int amountOfNonMilkAdditions, const string* nonMilkNames);
	OtherMilkProduct(const OtherMilkProduct &othermilkproduct);
	virtual ~OtherMilkProduct();
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
