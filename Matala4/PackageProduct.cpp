#include "PackageProduct.h"
#include "Product.h"
#include "iostream"

using namespace std;

PackageProduct::PackageProduct(int id, int shelf, char row, int weight, ExposureValue exposure,
		int amount, const string* productNamesList, int colorcount)
:Product(id, shelf, row, weight, PackageProductType, exposure),
 mAmount(amount),
 mColorCount(colorcount)
{
	mProductsNamesList = new string[mAmount];
	for (int i = 0; i < mAmount; ++i)
	{
		mProductsNamesList[i] = productNamesList[i];
	}
}

PackageProduct::~PackageProduct()
{
}

int PackageProduct::calculatePrice() const
{
	return mAmount + (mColorCount / 3);
}

void PackageProduct::print() const
{
	Product::print();

	for(int i = 0; i < mAmount; ++i)
	{
		cout << mProductsNamesList[i] << ",";
	}

	cout << " (" << mAmount << "," << mColorCount << ")";

}

void PackageProduct::saveBin(ofstream& out) const
{
	Product::saveBin(out);
	out.write((char*)&mAmount, sizeof(mAmount));
	out.write((char*)&mColorCount, sizeof(mColorCount));
	out.write((char*)&mProductsNamesList, sizeof(mProductsNamesList));
}
void PackageProduct::loadBin(ifstream& in)
{
	Product::loadBin(in);
	in.read((char*)&mAmount, sizeof(mAmount));
	in.read((char*)&mColorCount, sizeof(mColorCount));
	in.read((char*)&mProductsNamesList, sizeof(mProductsNamesList));
}

