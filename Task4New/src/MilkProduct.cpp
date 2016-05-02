/*
 * MilkProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#include "Product.h"
#include "MilkProduct.h"
#include <string>
#include <iostream>

using namespace std;

MilkProduct::~MilkProduct() {
}

MilkProduct::MilkProduct(const string& name, int id, int shelf, char row, int weight,
		ExposureValue exposure,int fat, MilkType milktype, int colorcount)
:Product(id, shelf, row, weight, MilkProductType, exposure), mMilkType(milktype),mName(name),
 mColorCount(colorcount), mFat(fat)
{
	if(fat < 0 || fat > 100)
	{
		throw "Wrong fat value";
	}
}

int MilkProduct::calculatePrice() const{

	return (mColorCount - mFat) * mMilkType;
}

void MilkProduct::print() const{

	Product::print();
	cout << mName << " (" << mMilkType << "," << mColorCount << "," << mFat << ") ";
}

MilkProduct::MilkProduct(const MilkProduct& milkproduct):Product(milkproduct),
		mMilkType(milkproduct.mMilkType),mName(milkproduct.mName),
		mColorCount(milkproduct.mColorCount), mFat(milkproduct.mFat) {
}
/*
void MilkProduct::write(ostream& out) const {
	Product::write(out);
	out << "NAME: " << mName << endl;
	out << "SUPPLIER AMOUNT: " << mFat << endl;
	out << "FARM TYPE: " << mMilkType << endl;
	out << "SEASONS NUMBER: " << mColorCount << endl;
}

void MilkProduct::save(ofstream& out) const {
	Product::save(out);

	out << mName << " ";
	out << mFat << " ";
	out << mMilkType << " ";
	out << mColorCount << " ";
}

void MilkProduct::load(ifstream& in) {
	Product::load(in);
	in >> mName >> mFat >> mMilkType >> mColorCount;
}

void MilkProduct::saveBin(ofstream& out) const {
	Product::saveBin(out);
	out.write((char*)&mName, sizeof(mName));
	out.write((char*)&mFat, sizeof(mFat));
	out.write((char*)&mMilkType, sizeof(mMilkType));
	out.write((char*)&mColorCount, sizeof(mColorCount));
}

void MilkProduct::loadBin(ifstream& in) {
	Product::loadBin(out);
	in.read((char*)&mName, sizeof(mName));
	in.read((char*)&mFat, sizeof(mFat));
	in.read((char*)&mMilkType, sizeof(mMilkType));
	in.read((char*)&mColorCount, sizeof(mColorCount));
}
 */
