#pragma once

class PackageProduct: public Product
{
private:

	int mAmount;
	char* mProductsNamesList[];
	int mColorCount;

public:

	PackageProduct();
	PackageProduct(int id, ShelfRow place, int weight, ProductType type, ExposureValue exposure,
			int amount, char* productNamesList, int colorcount);
	virtual ~PackageProduct();
	virtual int calculatePrice();
	virtual void print();
}

