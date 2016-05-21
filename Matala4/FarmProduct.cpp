#include "FarmProduct.h"
#include <string>
#include <iostream>

using namespace std;

FarmProduct::FarmProduct(const string& name, int id, int shelf, char row, int weight,
		ExposureValue exposure,int supplierNumber,FarmType farmtype ,int seasonsnumber):
				Product(id, shelf, row, weight, FarmProductType, exposure),
				mFarmType(farmtype),
				mName(name), mSeasonsNumber(seasonsnumber), mSuppliersAmount(supplierNumber)

{
	if(seasonsnumber > 4 || seasonsnumber < 1)
	{
		throw "Wrong season number";
	}
}

FarmProduct::~FarmProduct() {
}

int FarmProduct::calculatePrice() const {
	return (5 - mSeasonsNumber) + (5 * mSuppliersAmount) + mFarmType;
}

FarmProduct::FarmProduct(const FarmProduct& farmproduct):
						Product(farmproduct),
						mFarmType(farmproduct.mFarmType),
						mName(farmproduct.mName),
						mSeasonsNumber(farmproduct.mSeasonsNumber),
						mSuppliersAmount(farmproduct.mSuppliersAmount)
{
}

void FarmProduct::print() const{
	Product::print();
	cout << " " << mName << " (" << mFarmType <<
			"," << mSeasonsNumber << "," << mSuppliersAmount << ")";
}


void FarmProduct::saveBin(ofstream& out) const {
	Product::saveBin(out);
	out.write((char*)&mName, sizeof(mName));
	out.write((char*)&mSuppliersAmount, sizeof(mSuppliersAmount));
	out.write((char*)&mFarmType, sizeof(mFarmType));
	out.write((char*)&mSeasonsNumber, sizeof(mSeasonsNumber));
}

void FarmProduct::loadBin(ifstream& in) {
	Product::loadBin(in);
	in.read((char*)&mName, sizeof(mName));
	in.read((char*)&mSuppliersAmount, sizeof(mSuppliersAmount));
	in.read((char*)&mFarmType, sizeof(mFarmType));
	in.read((char*)&mSeasonsNumber, sizeof(mSeasonsNumber));
}

