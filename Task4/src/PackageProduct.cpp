#include "PackageProduct.h"
#include "Product.h"
#include "iostream"

using namespace std;

PackageProduct::PackageProduct(): Product()
{
	mAmount = 1;
	mColorCount = 1;
	mProductsNamesList = {"Default"};
}

PackageProduct::PackageProduct(int id, ShelfRow place, int weight, ProductType type, ExposureValue exposure,
		int amount, char* productNamesList, int colorcount)
	:Product(id, place, weight, type, exposure)
{
	mAmount = amount;
	mColorCount = colorcount;
	mProductsNamesList = productNamesList;
}

PackageProduct::~PackageProduct()
{
	delete[] mProductsNamesList;
}

int PackageProduct::calculatePrice()
{
	return mAmount + (mColorCount / 3);
}

void PackageProduct::print()
{
	Product::print();

	cout << "( " << endl;
	for(char* str = mProductsNamesList; str < mProductsNamesList + (sizeof(mProductsNamesList) / sizeof(*mProductsNamesList)); ++str)
	{
		cout << str << "," << endl;
	}

	cout << "(" << mAmount << "," << mColorCount << ")" << endl;

}
