/*
 * OtherMilkProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "OtherMilkProduct.h"
#include <iostream>
#include <string>

using namespace std;

OtherMilkProduct::OtherMilkProduct(int id, int shelf, char row, int weight,
		ExposureValue exposure, const string& name, int fat,
		int colorcount, int amountOfNonMilkAdditions, const string* nonMilkNames):
							MilkProduct(name, id, shelf, row, weight, exposure, fat, Other,colorcount),
							mAmountOfNonMilkAdditions(amountOfNonMilkAdditions)
{
	mNonMilkNames = new string[mAmountOfNonMilkAdditions];
	for (int i=0; i<mAmountOfNonMilkAdditions;++i)
	{
		mNonMilkNames[i] = nonMilkNames[i];
	}
}

OtherMilkProduct::~OtherMilkProduct() {
	// TODO Auto-generated destructor stub
}

int OtherMilkProduct::calculatePrice() const {
	return MilkProduct::calculatePrice() + (mAmountOfNonMilkAdditions * 5);
}

OtherMilkProduct::OtherMilkProduct(const OtherMilkProduct& othermilkproduct)
:MilkProduct(othermilkproduct),
 mAmountOfNonMilkAdditions(othermilkproduct.mAmountOfNonMilkAdditions)
{
	mNonMilkNames = new string[mAmountOfNonMilkAdditions];
	for (int i = 0;i  < mAmountOfNonMilkAdditions; ++i )
	{
		mNonMilkNames[i] = othermilkproduct.mNonMilkNames[i];
	}
}

void OtherMilkProduct::print() const {

	MilkProduct::print();
	for(int i = 0; i < mAmountOfNonMilkAdditions; ++i)
	{
		cout << mNonMilkNames[i] << ",";
	}

	cout << "(" << mAmountOfNonMilkAdditions << ")";
}

/*
void OtherMilkProduct::write(ostream& out) const
{
	MilkProduct::write(out);
	out << "AMOUNT OF NON MILK ADDITIONS: " << mAmountOfNonMilkAdditions << endl;
}

void OtherMilkProduct::save(ofstream & out) const
{
	MilkProduct::save(out);
	out << mAmountOfNonMilkAdditions << " ";
}

void OtherMilkProduct::load(ifstream & in)
{
	MilkProduct::load(in);
	in >> mAmountOfNonMilkAdditions;
}

void OtherMilkProduct::saveBin(ofstream & out) const
{
	MilkProduct::saveBin(out);
	out.write((char*)&mAmountOfNonMilkAdditions, sizeof(mAmountOfNonMilkAdditions));
}

void OtherMilkProduct::loadBin(ifstream & in)
{
	MilkProduct::loadBin(in);
	in.read((char*)&mAmountOfNonMilkAdditions, sizeof(mAmountOfNonMilkAdditions));
}
 */