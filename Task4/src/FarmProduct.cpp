#include "Product.h"
#include "FarmProduct.h"
#include "string.h"
#include "iostream"

using namespace std;

FarmProduct::FarmProduct(): Product()
{
	mFarmType = Fruit;
	mSuppliersAmount = 0;
	mSeasonsNumber = 1;
}

FarmProduct::FarmProduct(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure,int supplierNumber,FarmType farmtype
		, int seasonsnumber): Product(id, place, weight, type, exposure)
{
	mName = NULL;
	strcpy(mName, name);
	mFarmType = farmtype;
	mSeasonsNumber = seasonsnumber;
	mSuppliersAmount = supplierNumber;
}

FarmProduct::~FarmProduct() {
}

int FarmProduct::calculatePrice() {
	return (5 - mSeasonsNumber) + (5 * mSuppliersAmount) + mFarmType;
}

void FarmProduct::print() {
	Product::print();
	cout << " " << mName << " (" << mFarmType <<
			"," << mSeasonsNumber << "," << mSuppliersAmount << endl;
}
