//============================================================================
// Name        : Task4New.cpp
// Author      : 
// Version     :
// Copyright   : 
// Description : Hello World in C++, Ansi-style
//============================================================================
#include "FruitProduct.h"
#include "VegtableProduct.h"
#include "CheeseProduct.h"
#include "OtherMilkProduct.h"
#include "PackageProduct.h"
#include "Store.h"
#include <iostream>
#include <typeinfo>

using namespace std;

static int IDGENERATOR = 0;

int main() {

	cout << "Welcome To Supermarket!" << endl;

	try
	{
		FruitProduct fruit("banana", ++IDGENERATOR, 1, 'a', 4, Low, 2,2, 50);
		fruit.print();
		cout << endl;

		VegtableProduct veg("banana", ++IDGENERATOR, 1, 'a', 4, Low, 2,2, 50);
		veg.print();
		cout << endl;

		CheeseProduct cheese(++IDGENERATOR, 1, 'a', 1, High, "swiss", 20, 2, 2);
		cheese.print();
		cout << endl;

		string str[2];
		str[0] = "a";
		str[1] = "b";
		OtherMilkProduct other(++IDGENERATOR ,5,'m',5,Low, "cake", 5,2,2, str);
		other.print();
		cout << endl;

		string s[2];
		s[0] = "c";
		s[1] = "d";
		PackageProduct pkg(++IDGENERATOR, 4, 'A', 12, Low, 2, s, 6);
		pkg.print();

		Store myStore("SuperShop", 3, 0, NULL);
		myStore.addProductToStore(&fruit);
		myStore.addProductToStore(&veg);
		myStore.addProductToStore(&cheese);
		myStore.addProductToStore(&other);
		myStore.addProductToStore(&pkg);

		int sum = myStore.calculateStoreValue();
		cout << endl;
		cout << "Store Value is: " << sum << endl;
		myStore.printStoreDetails();
	}
	catch(char const* message)
	{
		cout << endl;
		cout << message << endl;
	}

	return 0;
}
