################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/FarmProduct.cpp \
../src/FruitProduct.cpp \
../src/MilkProduct.cpp \
../src/MilkyCheeseProduct.cpp \
../src/MilkyDrinkProduct.cpp \
../src/NonMilkyProduct.cpp \
../src/PackageProduct.cpp \
../src/Product.cpp \
../src/ShelfRow.cpp \
../src/Store.cpp \
../src/Task4.cpp \
../src/VegtableProduct.cpp \
../src/YogurtProduct.cpp 

OBJS += \
./src/FarmProduct.o \
./src/FruitProduct.o \
./src/MilkProduct.o \
./src/MilkyCheeseProduct.o \
./src/MilkyDrinkProduct.o \
./src/NonMilkyProduct.o \
./src/PackageProduct.o \
./src/Product.o \
./src/ShelfRow.o \
./src/Store.o \
./src/Task4.o \
./src/VegtableProduct.o \
./src/YogurtProduct.o 

CPP_DEPS += \
./src/FarmProduct.d \
./src/FruitProduct.d \
./src/MilkProduct.d \
./src/MilkyCheeseProduct.d \
./src/MilkyDrinkProduct.d \
./src/NonMilkyProduct.d \
./src/PackageProduct.d \
./src/Product.d \
./src/ShelfRow.d \
./src/Store.d \
./src/Task4.d \
./src/VegtableProduct.d \
./src/YogurtProduct.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


