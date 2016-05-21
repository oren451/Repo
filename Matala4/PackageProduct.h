#pragma once
#include <string>
#include "Product.h"

using namespace std;

class PackageProduct: public Product
{
private:
	const int mAmount;
	string* mProductsNamesList;
	const int mColorCount;

public:
	PackageProduct() :mAmount(0), mColorCount(0) {};
	PackageProduct(int id, int shelf, char row, int weight, ExposureValue exposure,
			int amount, const string* productNamesList, int colorcount);
	virtual ~PackageProduct();
	virtual int calculatePrice() const;
	virtual void print() const;

	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);
};
